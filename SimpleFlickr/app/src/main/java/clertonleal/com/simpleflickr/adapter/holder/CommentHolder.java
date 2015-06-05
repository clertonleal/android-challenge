package clertonleal.com.simpleflickr.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import clertonleal.com.simpleflickr.R;

public class CommentHolder extends RecyclerView.ViewHolder {

    public ImageView imageAvatar;
    public TextView textAuthor;
    public TextView textComment;

    public CommentHolder(View itemView) {
        super(itemView);
        imageAvatar = (ImageView) itemView.findViewById(R.id.image_profile);
        textAuthor = (TextView) itemView.findViewById(R.id.text_author);
        textComment = (TextView) itemView.findViewById(R.id.text_comment);
    }

}
