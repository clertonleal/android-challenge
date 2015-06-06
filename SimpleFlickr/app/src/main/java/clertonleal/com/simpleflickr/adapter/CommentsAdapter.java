package clertonleal.com.simpleflickr.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import clertonleal.com.simpleflickr.R;
import clertonleal.com.simpleflickr.adapter.holder.CommentHolder;
import clertonleal.com.simpleflickr.entity.Comment;

public class CommentsAdapter extends android.support.v7.widget.RecyclerView.Adapter<CommentHolder> {

    @Inject
    Resources resources;

    @Inject
    Context context;

    @Inject
    LayoutInflater layoutInflater;

    private final List<Comment> comments = new ArrayList<>();

    @Inject
    public CommentsAdapter() {}

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(layoutInflater.inflate(R.layout.row_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        Comment comment = comments.get(position);
        Picasso.with(context).load(comment.getProfileIconUrl()).placeholder(R.drawable.ic_camera).into(holder.imageAvatar);
        holder.textAuthor.setText(comment.getAuthorName());
        holder.textComment.setText(Html.fromHtml(comment.getContent()));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void addComments(List<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
        notifyDataSetChanged();
    }

}
