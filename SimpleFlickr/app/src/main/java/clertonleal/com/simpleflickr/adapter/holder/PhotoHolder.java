package clertonleal.com.simpleflickr.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.entity.Photo;
import clertonleal.com.simpleflickr.listeners.OnPhotoClickListener;

public class PhotoHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.image_photo)
    public ImageView dribbbleImage;

    @InjectView(R.id.text_photo_information)
    public TextView dribbbleTittle;

    @InjectView(R.id.layout_loading)
    public LinearLayout layoutProgress;

    public OnPhotoClickListener onPhotoClickListener;
    public Photo photo;

    public PhotoHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        itemView.setOnClickListener(v -> {
            if (onPhotoClickListener != null) {
                onPhotoClickListener.click(photo);
            }
        });
    }

}
