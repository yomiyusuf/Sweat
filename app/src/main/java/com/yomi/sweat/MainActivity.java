package com.yomi.sweat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yomi.sweat.model.Program;
import com.yomi.sweat.network.ProgramApi;
import com.yomi.sweat.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener((v) -> testRetro());
    }

    private void testRetro(){
        ProgramApi api = ServiceGenerator.getProgramApi();

        Call<List<Program>> response = api.getPrograms();
        Timber.e(response.request().toString());

        response.enqueue(new Callback<List<Program>>() {
            @Override
            public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
                Timber.e("Server response: %s", response.toString());
            }

            @Override
            public void onFailure(Call<List<Program>> call, Throwable t) {
                Timber.e("Server error is: %s", response.toString());
            }
        });
    }
}
