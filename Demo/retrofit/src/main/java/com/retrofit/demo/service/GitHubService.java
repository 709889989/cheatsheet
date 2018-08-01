package com.retrofit.demo.service;

import com.retrofit.demo.Uid;
import com.retrofit.demo.api.GitHubApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

@Service
public class GitHubService {
    @Autowired
    private GitHubApi gitHubApi;

    public Uid listRepos(){
        return gitHubApi.listRepos().get();
    }
}
