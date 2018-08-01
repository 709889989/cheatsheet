package com.retrofit.demo.config;

import com.google.gson.GsonBuilder;
import com.retrofit.demo.adapter.CustomCallAdapterFactory;
import com.retrofit.demo.api.GitHubApi;
import com.retrofit.demo.interceptor.LoggingInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

    @Bean
    public GitHubApi gitHubService(){

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8888/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create()))
                .addCallAdapterFactory(CustomCallAdapterFactory.INSTANCE)
                .client(okHttpClient.build())
                .build();

        GitHubApi service = retrofit.create(GitHubApi.class);
        return service;
    }

}
