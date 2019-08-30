package com.yomi.sweat.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yomi.sweat.model.Program;
import com.yomi.sweat.repository.ProgramRepository;

import java.util.List;

public class RecommendationsViewModel extends ViewModel {
    private ProgramRepository mProgramRepository;

    public RecommendationsViewModel() {
        mProgramRepository = ProgramRepository.getInstance();
    }

    public LiveData<List<Program>> getRecommendations(){
        return  mProgramRepository.getRecommendations();
    }
}
