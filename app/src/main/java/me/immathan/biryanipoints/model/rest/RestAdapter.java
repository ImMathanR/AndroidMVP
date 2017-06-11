package me.immathan.biryanipoints.model.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mathan-GG on 09-Jun-17.
 */

public class RestAdapter {

    private static final String API_BASE = "https://gist.githubusercontent.com/ImMathanR/";

    private static ApiClient mApiClient;

    private static final class SingleTonHelper {
        private static final RestAdapter INSTANCE = new RestAdapter();
    }

    private RestAdapter() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiClient = retrofit.create(ApiClient.class);
    }

    public static RestAdapter getInstance() {
        return SingleTonHelper.INSTANCE;
    }

    public ApiClient getApiClient() {
        return mApiClient;
    }

}
