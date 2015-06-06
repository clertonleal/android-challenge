package clertonleal.com.simpleflickr.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.adapter.holder.PhotoHolder;
import clertonleal.com.simpleflickr.entity.Page;
import clertonleal.com.simpleflickr.entity.Photo;
import clertonleal.com.simpleflickr.listeners.OnPhotoClickListener;

public class PhotoAdapter extends android.support.v7.widget.RecyclerView.Adapter<PhotoHolder> {

    @Inject
    Resources resources;

    @Inject
    Context context;

    @Inject
    LayoutInflater layoutInflater;

    private final List<Photo> photos = new ArrayList<>();
    private OnPhotoClickListener onPhotoClickListener;

    @Inject
    public PhotoAdapter() {}

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoHolder(layoutInflater.inflate(R.layout.card_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.onPhotoClickListener = onPhotoClickListener;
        holder.photo = photo;
        Picasso.with(context).load(photo.getPhotoUrl()).placeholder(R.drawable.photo_holder).into(holder.dribbbleImage);
        holder.dribbbleTittle.setText(photo.getTitle());
        holder.layoutProgress.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void addPagePhotos(Page newPage) {
        this.photos.addAll(newPage.getPhoto());
        notifyDataSetChanged();
    }

    public void cleanShots() {
        this.photos.clear();
    }

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }
}
