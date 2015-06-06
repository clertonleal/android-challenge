package clertonleal.com.simpleflickr.network;


import clertonleal.com.simpleflickr.entity.CommentsWrapper;
import clertonleal.com.simpleflickr.entity.PageWrapper;
import clertonleal.com.simpleflickr.entity.PhotoDetails;
import clertonleal.com.simpleflickr.entity.PhotoWrapper;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface FlickrNetwork {

    @GET("/")
    Observable<PageWrapper> retrievePage(@Query("method") String method,
                                     @Query("api_key") String apiKey,
                                     @Query("format") String format,
                                     @Query("nojsoncallback") Integer jsonCallback,
                                     @Query("per_page") Integer perPage,
                                     @Query("page") Integer page);


    @GET("/")
    Observable<PhotoWrapper> retrievePhotoDetails(@Query("method") String method,
                                       @Query("api_key") String apiKey,
                                       @Query("format") String format,
                                       @Query("nojsoncallback") Integer jsonCallback,
                                       @Query("photo_id") String photoId);

    @GET("/")
    Observable<CommentsWrapper> retrievePhotoComments(@Query("method") String method,
                                                  @Query("api_key") String apiKey,
                                                  @Query("format") String format,
                                                  @Query("nojsoncallback") Integer jsonCallback,
                                                  @Query("photo_id") String photoId);
}
