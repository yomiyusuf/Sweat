package com.yomi.sweat.network;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yomi.sweat.AppExecutors;
import com.yomi.sweat.model.Program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.yomi.sweat.util.Constants.NETWORK_TIMEOUT;

public class ProgramApiClient {
    private Context mContext;
    private static ProgramApiClient instance;
    private MutableLiveData<List<Program>> mPrograms;
    private MutableLiveData<Boolean> isRequestTimedOut = new MutableLiveData<>();
    private RetrieveProgramsRunnable mRetrieveProgramsRunnable;

    public static ProgramApiClient getInstance(Context context){
        if(instance == null){
            instance = new ProgramApiClient(context);
        }
        return instance;
    }

    private ProgramApiClient(Context context){
        mContext = context;
        mPrograms = new MutableLiveData<>();
    }

    public LiveData<List<Program>> getRecommendations(){
        return mPrograms;
    }

    public LiveData<Boolean> isRequestTimedOut() {
        return isRequestTimedOut;
    }

    public void requestRecommendations(){
        if(mRetrieveProgramsRunnable != null){
            mRetrieveProgramsRunnable = null;
        }
        mRetrieveProgramsRunnable = new RetrieveProgramsRunnable();
        final Future handler = AppExecutors.get().networkIO().submit(mRetrieveProgramsRunnable);

        //Setting timeout for data refresh
        AppExecutors.get().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                isRequestTimedOut.postValue(true);
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveProgramsRunnable implements Runnable{

        boolean cancelRequest = false;
        @Override
        public void run() {
            try {
                Response response = getRecomendations().execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<Program> list = new ArrayList<Program>((List<Program>)response.body());
                    mPrograms.postValue(list);
                } else {
                    String error = response.errorBody().toString();
                    mPrograms.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mPrograms.postValue(null);
            }

        }

        private Call<List<Program>> getRecomendations(){
            return ServiceGenerator.getProgramApi(mContext).getPrograms();
        }

        private void cancelRequest(){
            cancelRequest = true;
        }
    }
}
