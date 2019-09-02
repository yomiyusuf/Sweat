package com.yomi.sweat.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.yomi.sweat.model.Program;
import com.yomi.sweat.network.ProgramApiClient;

import java.util.List;

public class ProgramRepository {
    Context mContext;
    public static ProgramRepository instance;
    private ProgramApiClient mProgramApiClient;

    public static ProgramRepository getInstance(Context context){
        if (instance == null){
            instance = new ProgramRepository(context);
        }
        return instance;
    }

    private ProgramRepository(Context context){
        mContext = context;
        mProgramApiClient = ProgramApiClient.getInstance(mContext);
    }

    public LiveData<List<Program>> getRecommendations(){
        return mProgramApiClient.getRecommendations();
    }

    public void requestRecommendations(){
        mProgramApiClient.requestRecommendations();
    }

    public LiveData<Boolean> isRequestTimedOut() {
        return mProgramApiClient.isRequestTimedOut();
    }
}
