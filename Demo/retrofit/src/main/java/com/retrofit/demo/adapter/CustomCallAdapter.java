package com.retrofit.demo.adapter;

import retrofit2.Call;
import retrofit2.CallAdapter;

import java.lang.reflect.Type;

public class CustomCallAdapter<R> implements CallAdapter<R, CustomCall> {

    private final Type responseType;

    CustomCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public CustomCall<R> adapt(Call<R> call) {
        // 由 CustomCall 决定如何使用
        return new CustomCall<>(call);
    }
}

