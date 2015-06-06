package clertonleal.com.simpleflickr.activity;

import android.os.Bundle;

import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.util.BundleKeys;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class PhotoZoomActivity extends BaseActivity {

    @InjectView(R.id.image_photo_zoom)
    ImageViewTouch imageView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_zoom_photo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String photoUrl = getBundle().getString(BundleKeys.PHOTO_URL);
        imageView.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        Picasso.with(this).load(photoUrl).placeholder(R.drawable.photo_holder).into(imageView);
    }

    @Override
    protected void injectMembers() {}

}
