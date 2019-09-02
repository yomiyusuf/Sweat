package com.yomi.sweat.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yomi.sweat.model.Program;
import com.yomi.sweat.repository.ProgramRepository;

import java.util.List;

public class RecommendationsViewModel extends AndroidViewModel {
    private ProgramRepository mProgramRepository;

    private boolean mDidRetrieveRecommendations;

    public RecommendationsViewModel(Application application) {
        super(application);
        mProgramRepository = ProgramRepository.getInstance(application);
        mDidRetrieveRecommendations = false;
    }

    public LiveData<List<Program>> getRecommendations(){
        return  mProgramRepository.getRecommendations();
    }

    public void requestRecommendations(){
        mProgramRepository.requestRecommendations();
    }

    public LiveData<Boolean> isRequestTimedOut() {
        return mProgramRepository.isRequestTimedOut();
    }

    public boolean didRetrieveRecommendations() {
        return mDidRetrieveRecommendations;
    }

    public void setmDidRetrieveRecommendations(boolean retrieveRecommendations) {
        this.mDidRetrieveRecommendations = retrieveRecommendations;
    }
}
