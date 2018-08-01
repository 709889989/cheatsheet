package com.retrofit.demo.api;

import com.retrofit.demo.Uid;
import com.retrofit.demo.adapter.CustomCall;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface GitHubApi {
    @GET("/hello")
    CustomCall<Uid> listRepos();
}
