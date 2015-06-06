package clertonleal.com.simpleflickr.entity;

import java.io.Serializable;

public class Photo implements Serializable {

    private String id;

    private String title;

    private String owner;

    private String secret;

    private String server;

    private Integer farm;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getPhotoUrl() {
        return "https://farm" + getFarm() + ".staticflickr.com/" + getServer() +"/" + getId() + "_" + getSecret() + ".jpg";
    }

    public String getLargePhotoUrl() {
        return "https://farm" + getFarm() + ".staticflickr.com/" + getServer() +"/" + getId() + "_" + getSecret() + "_b" + ".jpg";
    }
}
