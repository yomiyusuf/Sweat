package com.yomi.sweat.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yomi.sweat.R;
import com.yomi.sweat.viewModel.RecommendationsViewModel;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private RecommendationsViewModel mRecommendationVM;
    RecyclerView recyclerView;
    ProgramListAdapter adapter;
    LinearLayout shimmer;
    LinearLayout errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        initViews();
        mRecommendationVM = ViewModelProviders.of(this).get(RecommendationsViewModel.class);
        initRecyclerView();
        subscribeObservers();
        requestRecommendations();
    }

    private void initViews(){
        shimmer = findViewById(R.id.progress_shimmer);
        errorView = findViewById(R.id.error_view);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rv_programs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(60));

        adapter = new ProgramListAdapter(MainActivity.this, program -> {
            Toast.makeText(this, program.getName(), Toast.LENGTH_SHORT).show();
        });
        LinearLayoutManager ll = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        ll.setInitialPrefetchItemCount(10);
        recyclerView.setLayoutManager(ll);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
    }

    private void subscribeObservers(){
        mRecommendationVM.getRecommendations().observe(this, programs -> {
            if(programs != null) {
                hideProgressShimmer();
                adapter.setData(programs);
                mRecommendationVM.setmDidRetrieveRecommendations(true);
            }
        });

        mRecommendationVM.isRequestTimedOut().observe(this, timedOut -> {
            if (timedOut && !mRecommendationVM.didRetrieveRecommendations()){
                hideProgressShimmer();
                displayError();
            }
        });
    }

    private void requestRecommendations(){
        showShimmer();
        mRecommendationVM.requestRecommendations();
    }

    private void displayError() {
        errorView.setVisibility(View.VISIBLE);
        findViewById(R.id.btn_error_retry).setOnClickListener(view -> {
            requestRecommendations();
            hideError();
        });
    }

    private void hideError() {
        errorView.setVisibility(View.GONE);
    }

    private void showShimmer() {
        shimmer.setVisibility(View.VISIBLE);
    }

    private void hideProgressShimmer() {
        shimmer.setVisibility(View.GONE);
    }
}
