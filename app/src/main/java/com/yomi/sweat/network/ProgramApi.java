package com.yomi.sweat.network;

import com.yomi.sweat.model.Program;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProgramApi {
    @GET("bins/r8zb7")
    Call<List<Program>> getPrograms();
}
