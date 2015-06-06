package clertonleal.com.simpleflickr.service;

import java.util.List;

import javax.inject.Inject;

import clertonleal.com.simpleflickr.entity.Comment;
import clertonleal.com.simpleflickr.entity.Page;
import clertonleal.com.simpleflickr.entity.PageWrapper;
import clertonleal.com.simpleflickr.entity.PhotoDetails;
import clertonleal.com.simpleflickr.entity.PhotoWrapper;
import clertonleal.com.simpleflickr.network.FlickrNetwork;
import clertonleal.com.simpleflickr.util.Flickr;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class FlickrService {

    @Inject
    FlickrNetwork flickrNetwork;

    @Inject
    public FlickrService() {}

    public Observable<Page> retrieveRecentPhotos(Integer page) {
        return flickrNetwork.retrievePage("flickr.photos.getRecent", Flickr.API_KEY, "json", 1, Flickr.PHOTOS_PER_PAGE, page).
                map(PageWrapper::getPhotos).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Page> retrievePopularPhotos(Integer page) {
        return flickrNetwork.retrievePage("flickr.interestingness.getList", Flickr.API_KEY, "json", 1, Flickr.PHOTOS_PER_PAGE, page).
                map(PageWrapper::getPhotos).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<PhotoDetails> retrievePhoto(String photoId) {
        return flickrNetwork.retrievePhotoDetails("flickr.photos.getInfo", Flickr.API_KEY, "json", 1, photoId).
                map(PhotoWrapper::getPhoto).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Comment>> retrieveComments(String photoId) {
        return flickrNetwork.retrievePhotoComments("flickr.photos.comments.getList", Flickr.API_KEY, "json", 1, photoId).
                map(wrapper -> wrapper.getComments().getComments()).observeOn(AndroidSchedulers.mainThread());
    }

}
