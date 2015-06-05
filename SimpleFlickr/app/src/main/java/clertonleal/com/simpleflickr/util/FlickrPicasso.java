package clertonleal.com.simpleflickr.util;

import android.content.Context;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import clertonleal.com.simpleflickr.R;

public class FlickrPicasso {

    static public RequestCreator with(Context context, String url) {
        return Picasso.with(context).load(url).placeholder(R.drawable.dribbble_loading).error(R.drawable.internet_error);
    }

}
