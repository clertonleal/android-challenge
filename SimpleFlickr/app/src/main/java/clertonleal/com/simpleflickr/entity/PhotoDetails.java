package clertonleal.com.simpleflickr.entity;

public class PhotoDetails {

    private String id;

    private String secret;

    private String server;

    private Integer farm;

    private Owner owner;

    private Title title;

    private Description description;

    private String views;

    private Comment comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getFarm() {
        return farm;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }

    public String getPhotoUrl() {
        return "https://farm" + getFarm() + ".staticflickr.com/" + getServer() +"/" + getId() + "_" + getSecret() + ".jpg";
    }

    public String getLargePhotoUrl() {
        return "https://farm" + getFarm() + ".staticflickr.com/" + getServer() +"/" + getId() + "_" + getSecret() + "_b" + ".jpg";
    }

}
