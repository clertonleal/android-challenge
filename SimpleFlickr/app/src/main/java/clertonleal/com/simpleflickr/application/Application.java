package clertonleal.com.simpleflickr.application;

import clertonleal.com.simpleflickr.dagger.DaggerFlickrComponent;
import clertonleal.com.simpleflickr.dagger.FlickrModule;
import clertonleal.com.simpleflickr.dagger.FlickrComponent;

public class Application extends android.app.Application {


    private static FlickrComponent flickrComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        flickrComponent = DaggerFlickrComponent.
                builder().
                flickrModule(new FlickrModule(this)).
                build();
    }

    public static FlickrComponent getFlickrComponent(){
        return flickrComponent;
    }
}
