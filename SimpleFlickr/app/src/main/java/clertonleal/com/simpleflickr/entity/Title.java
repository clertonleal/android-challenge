package clertonleal.com.simpleflickr.entity;

import com.google.gson.annotations.SerializedName;

public class Title {

    @SerializedName("_content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
