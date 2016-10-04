package com.retrofitreactive.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.retrofitreactive.pojos.Comprobante;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class Save {

    @NonNull
    private static <T> Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            subscriber.onNext(func.call());
                        } catch (Exception ex) {
                            Log.e("This", "Error reading from the database", ex);
                        }
                    }
                });
    }
}
