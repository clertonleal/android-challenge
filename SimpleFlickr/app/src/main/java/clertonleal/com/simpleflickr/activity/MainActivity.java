package clertonleal.com.simpleflickr.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import javax.inject.Inject;

import butterknife.InjectView;
import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.adapter.PhotoAdapter;
import clertonleal.com.simpleflickr.entity.Photo;
import clertonleal.com.simpleflickr.service.ConnectionService;
import clertonleal.com.simpleflickr.service.FlickrService;
import clertonleal.com.simpleflickr.util.BundleKeys;
import clertonleal.com.simpleflickr.util.Flickr;
import clertonleal.com.simpleflickr.util.FlickrPhotos;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.list)
    SuperRecyclerView recyclerView;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

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

    int actualPage = 1;

    FlickrPhotos flickrPhotos = FlickrPhotos.POPULARS;

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
            loadInitialPage();
        } else {
            showEmptyView(true);
        }
    }

    private void configureRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setRefreshingColorResources(R.color.primary_color, R.color.primary_color,
                                                    R.color.primary_color, R.color.primary_color);
    }

    private void configureToolbar() {
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setTitle(R.string.app_name);
    }

    private void setListeners() {
        recyclerView.setupMoreListener(this::onLoadMore, Flickr.LOAD_MORE_PHOTOS);
        recyclerView.setRefreshListener(this::loadInitialPage);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        photoAdapter.setOnPhotoClickListener(this::openPhotoDetail);
        imageRefresh.setOnClickListener(v -> {
            showEmptyView(false);
            loadInitialPage();
        });
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

    private void openPhotoDetail(Photo photo) {
        final Intent intent = new Intent(this, PhotoActivity.class);
        intent.putExtra(BundleKeys.PHOTO_ID, photo.getId());
        startActivity(intent);
    }

    private void onLoadMore(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        actualPage++;
        loadNewPage(actualPage);
    }

    private void loadInitialPage() {
        actualPage = 1;
        photoAdapter.cleanShots();
        recyclerView.showProgress();
        compositeSubscription.add(flickrService.retrievePhotosByType(actualPage, flickrPhotos).
                subscribe(page -> {
                    photoAdapter.addPagePhotos(page);
                    recyclerView.setAdapter(photoAdapter);
                }, this::log));
    }

    private void loadNewPage(int pageNumber) {
        compositeSubscription.add(flickrService.retrievePhotosByType(pageNumber, flickrPhotos).
                subscribe(page -> {
                    recyclerView.hideMoreProgress();
                    photoAdapter.addPagePhotos(page);
                }, this::log));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_show_popular) {
            flickrPhotos = FlickrPhotos.POPULARS;
            loadInitialPage();
        } else if (item.getItemId() == R.id.action_show_news) {
            flickrPhotos = FlickrPhotos.NEWS;
            loadInitialPage();
        } else if (item.getItemId() == R.id.action_refresh) {
            loadInitialPage();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void injectMembers() {
        flickrComponent().inject(this);
    }
}
