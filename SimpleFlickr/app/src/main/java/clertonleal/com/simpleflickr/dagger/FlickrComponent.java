package clertonleal.com.simpleflickr.dagger;


import javax.inject.Singleton;

import clertonleal.com.simpleflickr.activity.MainActivity;
import clertonleal.com.simpleflickr.activity.PhotoActivity;
import dagger.Component;

@Singleton
@Component(modules = FlickrModule.class)
public interface FlickrComponent {

    void inject(MainActivity mainActivity);
    void inject(PhotoActivity photoActivity);

}
