package com.yomi.sweat.network;

import android.content.Context;
import android.net.ConnectivityManager;

import com.yomi.sweat.util.Constants;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);

    public static ProgramApi getProgramApi(Context context){
        long cacheSize = (long) (10 * 1024 * 1024);
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        httpClient.interceptors().clear();
        httpClient.cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (hasNetWork(context)){
                            //If connected to internet, get 5 seconds old cache. Older than that, discard
                            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
                        } else {
                            //If not connected to internet, get cache saved 3 days old
                            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 3).build();
                        }
                        return chain.proceed(request);
                    }
                }).build();

        httpClient.addInterceptor(logging);
        retrofitBuilder.client(httpClient.build());
        retrofit = retrofitBuilder.build();
        return retrofit.create(ProgramApi.class);
    }

    private static boolean hasNetWork(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
