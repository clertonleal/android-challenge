package clertonleal.com.simpleflickr.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.adapter.CommentsAdapter;
import clertonleal.com.simpleflickr.entity.Comment;
import clertonleal.com.simpleflickr.entity.PhotoDetails;
import clertonleal.com.simpleflickr.service.FlickrService;
import clertonleal.com.simpleflickr.util.BundleKeys;
import clertonleal.com.simpleflickr.util.FlickrPicasso;
import rx.android.schedulers.AndroidSchedulers;

public class PhotoActivity extends BaseActivity {

    @InjectView(R.id.image_photo)
    ImageView imagePhoto;

    @InjectView(R.id.text_photo_information)
    TextView textNumberComments;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.image_profile)
    ImageView imageProfile;

    @InjectView(R.id.text_author_name)
    TextView textAuthorName;

    @InjectView(R.id.text_photo_title)
    TextView textPhotoTitle;

    @InjectView(R.id.list)
    SuperRecyclerView recyclerView;

    @Inject
    FlickrService flickrService;

    @Inject
    CommentsAdapter adapter;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_photo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String photoId = getBundle().getString(BundleKeys.PHOTO_ID);
        configureRecycleView();

        compositeSubscription.add(flickrService.retrievePhoto(photoId).
                subscribe(photo -> {
                    showPhoto(photo);
                    configureToolbar(photo);
                }));

        compositeSubscription.add(flickrService.retrieveComments(photoId).
                subscribe(this::showComments));
    }

    private void configureRecycleView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void showComments(List<Comment> comments) {
        recyclerView.setAdapter(adapter);
        adapter.addComments(comments);
    }

    private void showPhoto(PhotoDetails photo) {
        FlickrPicasso.with(this, photo.getPhotoUrl()).into(imagePhoto);
        textPhotoTitle.setText(photo.getTitle().getContent());
        FlickrPicasso.with(this, photo.getOwner().getProfileIconUrl()).into(imageProfile);
        textAuthorName.setText(photo.getOwner().getUserName());
        textNumberComments.setText(photo.getComments().getContent() + " " + getResources().getString(R.string.comments));
    }

    private void configureToolbar(PhotoDetails photo) {
        setSupportActionBar(toolbar);
        setTitle(photo.getTitle().getContent());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void injectMembers() {
        flickrComponent().inject(this);
    }
}
