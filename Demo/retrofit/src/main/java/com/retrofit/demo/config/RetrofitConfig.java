package com.retrofit.demo.config;

import com.google.gson.GsonBuilder;
import com.retrofit.demo.adapter.CustomCallAdapterFactory;
import com.retrofit.demo.api.GitHubApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Bean
    public GitHubApi gitHubService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8888/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create()))
                .addCallAdapterFactory(CustomCallAdapterFactory.INSTANCE)
                .build();

        GitHubApi service = retrofit.create(GitHubApi.class);
        return service;
    }

}
