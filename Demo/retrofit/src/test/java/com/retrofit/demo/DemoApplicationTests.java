package com.retrofit.demo;

import com.retrofit.demo.adapter.CustomCall;
import com.retrofit.demo.api.GitHubApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private GitHubApi gitHubService;

	@Test
	public void contextLoads() throws IOException {
		CustomCall<Uid> call = gitHubService.listRepos();
		Uid uid = call.get();
        System.out.println(uid);
	}
}
