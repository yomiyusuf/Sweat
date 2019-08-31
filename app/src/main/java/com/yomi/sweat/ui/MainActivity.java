package com.yomi.sweat.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.yomi.sweat.R;
import com.yomi.sweat.model.Program;
import com.yomi.sweat.network.ProgramApi;
import com.yomi.sweat.network.ServiceGenerator;
import com.yomi.sweat.ui.BaseActivity;
import com.yomi.sweat.ui.views.ProgramCard;
import com.yomi.sweat.viewModel.RecommendationsViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private RecommendationsViewModel mRecommendationVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mRecommendationVM = ViewModelProviders.of(this).get(RecommendationsViewModel.class);
        subscribeObservers();
        testRetro();
    }

    private void subscribeObservers(){
        mRecommendationVM.getRecommendations().observe(this, programs -> {

        });
    }

    private void testRetro(){
        ProgramApi api = ServiceGenerator.getProgramApi();

        Call<List<Program>> response = api.getPrograms();
        Timber.e(response.request().toString());

        response.enqueue(new Callback<List<Program>>() {
            @Override
            public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
                Timber.e("Server response: %s", response.toString());
                if(response.code() == 200){
                    List<Program> programs = new ArrayList<>(response.body());
                    LinearLayout ll = findViewById(R.id.ttt);
                    for(Program program: programs){
                        //Timber.e(program.getName() + " - " + program.getTrainer().getName());
                        ProgramCard pc = new ProgramCard(MainActivity.this);
                        pc.setViews(program);
                        ll.addView(pc);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Program>> call, Throwable t) {
                Timber.e("Server error is: %s", response.toString());
            }
        });
    }
}
