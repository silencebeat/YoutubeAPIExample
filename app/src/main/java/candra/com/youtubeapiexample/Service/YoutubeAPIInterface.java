package candra.com.youtubeapiexample.Service;

import java.util.Map;

import candra.com.youtubeapiexample.Model.Videos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Candra Triyadi on 17/09/2017.
 */

public interface YoutubeAPIInterface {

    @GET("/youtube/v3/search")
    Call<Videos> getVideos(@QueryMap Map<String, String> params);
}
