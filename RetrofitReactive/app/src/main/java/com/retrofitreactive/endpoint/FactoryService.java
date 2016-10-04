package com.retrofitreactive.endpoint;


import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactoryService {
    private FactoryService(){}



    public static Service create(Gson gson) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_SERVICE)
                //.client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(Service.class);
    }

    @NonNull
    public static Gson registerSerializer(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Comprobante.class, new ComprobanteSerializer());
        builder.registerTypeAdapter(FacturaResponse.class, new JsonDeserializer());
        return builder.create();
    }
}
