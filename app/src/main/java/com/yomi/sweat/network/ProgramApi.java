package com.yomi.sweat.network;

import com.yomi.sweat.model.Program;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProgramApi {
    @GET("b/5d706bc5fc5937640ce331bc")
    Call<List<Program>> getPrograms();
}
