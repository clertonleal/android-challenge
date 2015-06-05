package clertonleal.com.simpleflickr.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.entity.Photo;
import clertonleal.com.simpleflickr.listeners.OnShotClickListener;

public class PhotoHolder extends RecyclerView.ViewHolder {

    public ImageView dribbbleImage;
    public TextView dribbbleTittle;
    public LinearLayout layoutProgress;
    public OnShotClickListener onShotClickListener;
    public Photo photo;

    public PhotoHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(v -> {
            if (onShotClickListener != null) {
                onShotClickListener.click(photo);
            }
        });
        dribbbleImage = (ImageView) itemView.findViewById(R.id.image_photo);
        dribbbleTittle = (TextView) itemView.findViewById(R.id.text_photo_information);
        layoutProgress = (LinearLayout) itemView.findViewById(R.id.layout_loading);
    }

}
