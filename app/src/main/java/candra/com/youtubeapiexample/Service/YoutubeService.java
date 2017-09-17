package candra.com.youtubeapiexample.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Candra Triyadi on 17/09/2017.
 */

public class YoutubeService {

    private static final String BASE_URL = "https://www.googleapis.com";
    private static Retrofit retrofit = null;

    private YoutubeService(){

    }

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
