package clertonleal.com.simpleflickr.service;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import clertonleal.com.simpleflickr.entity.Comment;
import clertonleal.com.simpleflickr.network.FlickrNetwork;
import clertonleal.com.simpleflickr.util.Flickr;
import retrofit.RestAdapter;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FlickrServiceTest {

    private FlickrNetwork flickrNetwork;
    private Integer actualPage = 1;

    @Before
    public void setUp() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.flickr.com/services/rest/").build();
        flickrNetwork = restAdapter.create(FlickrNetwork.class);
    }

    @Test
    public void recentPhotos() {
        FlickrService flickrService = new FlickrService(flickrNetwork);
        flickrService.retrieveRecentPhotos(actualPage).subscribe(page -> {
            assertNotNull(page);
            assertEquals(page.getPage(), actualPage);
            assertEquals(page.getPerPage(), Flickr.PHOTOS_PER_PAGE);
            assertNotNull(page.getPhoto());
            assertEquals(page.getPhoto().size(), Flickr.PHOTOS_PER_PAGE.intValue());
        });
    }

    @Test
    public void popularPhotos() {
        FlickrService flickrService = new FlickrService(flickrNetwork);
        flickrService.retrievePopularPhotos(actualPage).subscribe(page -> {
            assertNotNull(page);
            assertEquals(page.getPage(), actualPage);
            assertEquals(page.getPerPage(), Flickr.PHOTOS_PER_PAGE);
            assertNotNull(page.getPhoto());
            assertEquals(page.getPhoto().size(), Flickr.PHOTOS_PER_PAGE.intValue());
        });
    }

    @Test
    public void photoComments() {
        FlickrService flickrService = new FlickrService(flickrNetwork);
        String testPhotoId = "7820416426";

        flickrService.retrieveComments(testPhotoId).subscribe(comments -> {
            assertNotNull(comments);
            assertThat(comments.size(), greaterThan(0));
            Comment comment = comments.get(0);
            assertNotNull(comment.getAuthorName());
            assertNotNull(comment.getContent());
        });
    }

}
