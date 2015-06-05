package clertonleal.com.simpleflickr.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PhotoComments {

    @SerializedName("photo_id")
    private String photoId;

    @SerializedName("comment")
    private List<Comment> comments = new ArrayList<>();

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
