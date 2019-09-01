package com.yomi.sweat.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yomi.sweat.model.Program;
import com.yomi.sweat.repository.ProgramRepository;

import java.util.List;

public class RecommendationsViewModel extends ViewModel {
    private ProgramRepository mProgramRepository;

    private boolean mDidRetrieveRecommendations;

    public RecommendationsViewModel() {

        mProgramRepository = ProgramRepository.getInstance();
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
