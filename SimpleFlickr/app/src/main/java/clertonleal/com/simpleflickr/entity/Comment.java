package clertonleal.com.simpleflickr.entity;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private String id;

    private String author;

    @SerializedName("authorname")
    private String authorName;

    @SerializedName("iconserver")
    private String iconServer;

    @SerializedName("iconfarm")
    private String iconFarm;

    @SerializedName("_content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIconServer() {
        return iconServer;
    }

    public void setIconServer(String iconServer) {
        this.iconServer = iconServer;
    }

    public String getIconFarm() {
        return iconFarm;
    }

    public void setIconFarm(String iconFarm) {
        this.iconFarm = iconFarm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProfileIconUrl() {
        return "http://farm" + getIconFarm() + ".staticflickr.com/" + getIconServer() + "/buddyicons/" + getAuthor() + ".jpg";
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
