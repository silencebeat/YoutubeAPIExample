package candra.com.youtubeapiexample.Presenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import candra.com.youtubeapiexample.Model.Videos;
import candra.com.youtubeapiexample.R;
import candra.com.youtubeapiexample.Service.YoutubeAPIInterface;
import candra.com.youtubeapiexample.Service.YoutubeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Candra Triyadi on 17/09/2017.
 */

public class YoutubePresenter extends Observable {

    YoutubeAPIInterface apiInterface;
    String YOUTUBE_KEY = "";
    Context context;

    public YoutubePresenter(Context context){
        this.context = context;
        YOUTUBE_KEY = context.getResources().getString(R.string.YOUTUBE_API_KEY);
        apiInterface = YoutubeService.getClient().create(YoutubeAPIInterface.class);
    }

    public void getVideos(String channelID){
        Map<String, String> params = new HashMap<>();
        params.put("key", YOUTUBE_KEY);
        params.put("part","snippet");
        params.put("order","date");
        params.put("maxResults", "50");
        params.put("channelId", channelID);

        Call<Videos> videosCall = apiInterface.getVideos(params);
        videosCall.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videos = response.body();
                notifyToObservers(videos);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                notifyToObservers(null);
            }
        });
    }

    private void notifyToObservers(Videos videos){
        setChanged();
        notifyObservers(videos);
    }
}
