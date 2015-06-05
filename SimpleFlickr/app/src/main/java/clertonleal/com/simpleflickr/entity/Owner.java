package clertonleal.com.simpleflickr.entity;

import com.google.gson.annotations.SerializedName;

public class Owner {

    private String nsid;

    @SerializedName("username")
    private String userName;

    @SerializedName("iconserver")
    private String iconServer;

    @SerializedName("iconfarm")
    private String iconFarm;

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getProfileIconUrl() {
        return "http://farm" + getIconFarm() + ".staticflickr.com/" + getIconServer() + "/buddyicons/" + getNsid() + ".jpg";
    }
}
