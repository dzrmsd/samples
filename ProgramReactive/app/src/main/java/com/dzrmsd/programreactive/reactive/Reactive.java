package com.dzrmsd.programreactive.reactive;


import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Reactive {

    private static Scheduler scheduler;

    public static Scheduler SuscribeScheduler() {
        if (scheduler == null) scheduler = Schedulers.io();
        return scheduler;
    }


    public static void reactive() {
        Observable<String> observable = Observable.just("Hello, world!");
        observable.subscribeOn(SuscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> System.out.println(s));


        /*map es un operador que ayuda a la modificacion de los datos antes de que lleguen al suscriptor
        * de modo que todo sea mas limpio
        * */
        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));

        /*un Observable podría ser una consulta a la db, el suscriber tomar los datos y mostrarlos en pantalla*/
        /*Observable.from() tiene una colección de artículos y emite cada uno a la vez
        * flatMap() toma las emisiones de un Observable y devuelve las emisiones de otro Observable para ocupar su lugar
        * */

        Observable.just("Hello!")
                .map(input -> {
                    throw new RuntimeException();
                })
                .subscribe(
                        System.out::println,
                        error -> System.out.println("Error!")
                );

    }
}
