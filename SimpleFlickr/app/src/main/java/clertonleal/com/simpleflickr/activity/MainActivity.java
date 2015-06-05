package clertonleal.com.simpleflickr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.InjectView;
import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.adapter.PhotoAdapter;
import clertonleal.com.simpleflickr.service.ConnectionService;
import clertonleal.com.simpleflickr.service.FlickrService;
import clertonleal.com.simpleflickr.util.BundleKeys;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.list)
    RecyclerView recyclerView;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout refreshLayout;

    @InjectView(R.id.layout_empty_view)
    LinearLayout emptyView;

    @InjectView(R.id.image_refresh)
    ImageView imageRefresh;

    @Inject
    FlickrService flickrService;

    @Inject
    ConnectionService connectionService;

    @Inject
    PhotoAdapter photoAdapter;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureRecycleView();
        configureToolbar();
        setListeners();
        if (connectionService.hasConnection()) {
            showProgressDialog(R.string.loading_shots);
            loadNewPage(1);
        } else {
            showEmptyView(true);
        }
    }

    private void showEmptyView(boolean show) {
        if (show) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void configureToolbar() {
        toolbar.inflateMenu(R.menu.home_menu);
        toolbar.setTitle(R.string.app_name);
    }

    private void setListeners() {
        photoAdapter.setPageLoadListener(this::loadNewPage);
        refreshLayout.setOnRefreshListener(this::resetShots);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        imageRefresh.setOnClickListener(v -> resetShots());

        photoAdapter.setOnShotClickListener(photo -> {
            final Intent intent = new Intent(this, PhotoActivity.class);
            intent.putExtra(BundleKeys.PHOTO_ID, photo.getId());
            startActivity(intent);
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                refreshLayout.setEnabled(linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });
    }

    private void loadNewPage(int pageNumber) {
        compositeSubscription.add(flickrService.retrieveRecentPhotos(pageNumber).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(page -> {
                    showEmptyView(false);
                    photoAdapter.addPagePhotos(page);
                    cancelProgressDialog();
                    if (refreshLayout.isEnabled()) {
                        refreshLayout.setRefreshing(false);
                    }
                }, throwable -> {
                    log(throwable);
                    cancelProgressDialog();
                }));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            resetShots();
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetShots() {
        showProgressDialog(R.string.loading_shots);
        photoAdapter.cleanShots();
        linearLayoutManager.scrollToPosition(0);
        loadNewPage(1);
    }

    private void configureRecycleView() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(photoAdapter);
    }

    @Override
    protected void injectMembers() {
        flickrComponent().inject(this);
    }
}
