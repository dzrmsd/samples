package com.retrofitreactive.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.retrofitreactive.R;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consumesWebService();
    }

    public void consumesWebService(){
        Gson gson = FactoryService.registerSerializer();
        Service service = FactoryService.create(gson);
        Pojo pojo = setPojo();
        new SavePojo().Observable(pojo)
                .flatMap(service -> service.generateFacturaRX(service))//Because call another observable althought exist trouble registering the error
                .subscribeOn(Schedulers.io())//In io because is like a thread pool
                .observeOn(AndroidSchedulers.mainThread()) // Register in the main thread because will see on the view
                .subscribe(success -> response(success),
                        throwable -> onErrorFactura(throwable)
                )
        ;
    }

    void response(Response response){
        //Get the values of response
    }

    void onErrorFactura(Throwable t){
        t.printStackTrace();
    }

    Pojo setPojo(){
        Pojo pojo = new Pojo();
        return pojo;
    }
}
