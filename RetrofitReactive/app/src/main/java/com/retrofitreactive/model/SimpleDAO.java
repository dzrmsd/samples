package com.retrofitreactive.model;

import android.util.Log;


import java.util.concurrent.Callable;


public class SimpleDAO {
    public Callable<Comprobante> save(Comprobante comp) {
        return new Callable<Comprobante>() {
            @Override
            public Comprobante call() {
               //Simulando que se guarda en algun lugar de la base de datos
                return comp;
            }
        };
    }
}
