package com.yomi.sweat.network;

import com.yomi.sweat.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ProgramApi programApi = retrofit.create(ProgramApi.class);

    public static ProgramApi getProgramApi(){
        return programApi;
    }
}
