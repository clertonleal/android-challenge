package clertonleal.com.simpleflickr.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import clertonleal.com.simpleflickr.R;

public class CommentHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.image_profile)
    public ImageView imageAvatar;

    @InjectView(R.id.text_author)
    public TextView textAuthor;

    @InjectView(R.id.text_comment)
    public TextView textComment;

    public CommentHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

}
