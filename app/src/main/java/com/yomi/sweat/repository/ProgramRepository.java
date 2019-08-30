package com.yomi.sweat.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yomi.sweat.model.Program;

import java.util.List;

public class ProgramRepository {
    public static ProgramRepository instance;
    private MutableLiveData<List<Program>> mPrograms;

    public static ProgramRepository getInstance(){
        if (instance == null){
            instance = new ProgramRepository();
        }
        return instance;
    }

    private ProgramRepository(){
        mPrograms = new MutableLiveData<>();
    }

    public LiveData<List<Program>> getRecommendations(){
        return mPrograms;
    }
}
