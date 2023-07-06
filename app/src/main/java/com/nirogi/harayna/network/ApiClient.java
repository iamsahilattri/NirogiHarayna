package com.nirogi.harayna.network;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {


    private static final String URL_BASE= "https://mobiledost.jk.gov.in:81/api/";
    private static final String URL_BASE_STAGING= "https://mobiledost.jk.gov.in:81/api/";
    private static final String URL_BASE_DPRO= "https://jkdirinf.jk.gov.in/api/";
    private static final String URL_BASE_SARAL_TOKEN_JAVA= "http://164.100.137.55:8080/saral/";



    public static Retrofit getClientAuthentication() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        client.readTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("token", "token@123")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        client.addInterceptor(interceptor);
        client.retryOnConnectionFailure(true);
        return new Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create()).client(client.build()).build();
    }

    public static Retrofit getClientAuthenticationWithHeader(String headerOne,String headerTwo) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        client.readTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("client_id", headerOne)
                        .header("clientIDJKIndustries", headerTwo)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        client.addInterceptor(interceptor);
        client.retryOnConnectionFailure(true);
        return new Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create()).client(client.build()).build();
    }
    public static Retrofit getClientAuthenticationDPRO() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);
        client.retryOnConnectionFailure(true);
        return new Retrofit.Builder().baseUrl(URL_BASE_DPRO).addConverterFactory(GsonConverterFactory.create()).client(client.build()).build();
    }

    public static Retrofit getClientAuthenticationJavaToken() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);
        httpClient.retryOnConnectionFailure(true);
        return new Retrofit.Builder().baseUrl(URL_BASE_SARAL_TOKEN_JAVA).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
    }
}
