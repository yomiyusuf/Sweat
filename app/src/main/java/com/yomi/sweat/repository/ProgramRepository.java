package com.yomi.sweat.repository;

import androidx.lifecycle.LiveData;

import com.yomi.sweat.model.Program;
import com.yomi.sweat.network.ProgramApiClient;

import java.util.List;

public class ProgramRepository {
    public static ProgramRepository instance;
    private ProgramApiClient mProgramApiClient;

    public static ProgramRepository getInstance(){
        if (instance == null){
            instance = new ProgramRepository();
        }
        return instance;
    }

    private ProgramRepository(){
        mProgramApiClient = ProgramApiClient.getInstance();
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
