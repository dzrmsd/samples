package com.retrofitreactive;

import com.google.gson.GsonBuilder;
import com.retrofitreactive.pojos.Comprobante;
import com.retrofitreactive.pojos.Concepto;
import com.retrofitreactive.pojos.Emisor;
import com.retrofitreactive.pojos.Receptor;
import com.retrofitreactive.pojos.Retencion;
import com.retrofitreactive.pojos.Traslados;
import com.retrofitreactive.serializer.ComprobanteSerializer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void observable(){

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) { System.out.println(s); }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };

        myObservable.subscribe(mySubscriber);

        Observable<String> myObservableSimple =
                Observable.just("Hello, world!");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservableSimple.subscribe(onNextAction);

        Observable.just("Hello, world!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

        Observable.just("Hello, world!")
                .subscribe(s -> System.out.println(s));

        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .subscribe(s -> System.out.println(s));

        Observable.just("Hello, world!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(i -> System.out.println(Integer.toString(i)));

        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .subscribe(i -> System.out.println(Integer.toString(i)));

        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));

    }


//    @Test
//    public void testGson(){
//        Emisor emisor = new Emisor();
//        emisor.setRfc("PPL961114GZ1");
//        emisor.setNombre("PHARMA PLUS SA DE CV");
//        emisor.setCodigoPostal(032400);
//        emisor.setCalle(" ");
//        emisor.setColonia(" ");
//        emisor.setReferencia(" ");
//        emisor.setMunicipio(" ");
//        emisor.setEstado(" ");
//        emisor.setPais("MX");
//        emisor.setLocalidad(" ");
//        emisor.setRegimen("SALARIOS");
//
//        Receptor receptor = new Receptor();
//        receptor.setRfc("RADS931123GR5");
//        receptor.setNombre("Sandy Ramos Diaz");
//        receptor.setNoExterior("33");
//        receptor.setNoInterior("S/N");
//        receptor.setCodigoPostal(52076);
//        receptor.setCalle("ORQUIDEAS");
//        receptor.setColonia("LAS FLORES");
//        receptor.setReferencia("RUMBO AL CAMPO");
//        receptor.setMunicipio(" XONACATLAN");
//        receptor.setEstado("MEXICO");
//        receptor.setPais("MX");
//        receptor.setLocalidad("SANTIAGO TEJOCOTILLOS");
//
//        Comprobante comprobante = new Comprobante();
//        comprobante.setFecha("01/01/01");
//        comprobante.setFormaPago("Una sola exhibicion");
//        comprobante.setSubtotal(156.23);
//        comprobante.setTotal(89658.596);
//        comprobante.setTipoDeComprobante("ingreso");
//        comprobante.setMetodoPago("CHEQUE");
//        comprobante.setLugarExpedicion("Zapopan, Jalisco");
//        comprobante.setSerie("AB");
//        comprobante.setFolio("000000002");
//        comprobante.setCondicionesPago("Pago en parcialidades");
//        comprobante.setDescuento(10.21);
//        comprobante.setMotivoDescuento("Descuento aplicable en 4 x 2");
//        comprobante.setTipoCambio("1");
//        comprobante.setMoneda("Peso");
//        comprobante.setNumCtaPago("0123456");
//        comprobante.setFolioFiscalOrig(" ");
//        comprobante.setSerieFolioFiscalOrig(" ");
//        comprobante.setFechaFolioFiscalOrig(" ");
//        comprobante.setMontoFolioFiscalOrig(" ");
//        comprobante.setNumCtaPago("No identificado");
//
//        comprobante.setEmisor(emisor);
//        comprobante.setReceptor(receptor);
//        List<Concepto> conceptos = new ArrayList<>();
//        conceptos.add(new Concepto(2, "Pieza", "notiene", "Producto prueba", 20.00, 40.00));
//        conceptos.add(new Concepto(3, "Pieza", "notiene", "Producto prueba", 20.00, 60.00));
//        conceptos.add(new Concepto(5, "Pieza", "notiene", "Producto prueba", 20.00, 100.00));
//        conceptos.add(new Concepto(10, "Pieza", "notiene", "Producto prueba", 20.00, 200.00));
//        comprobante.setArrayConceptos(conceptos);
//
//        List<Retencion> retenciones = new ArrayList<>();
//        retenciones.add(new Retencion(1000.123456, "ISR"));
//        retenciones.add(new Retencion(16.123456, "IVA"));
//
//        comprobante.setRetenciones(retenciones);
//
//        List<Traslados> traslados = new ArrayList<>();
//        traslados.add(new Traslados(1000.123456, "IEPS", 10.1));
//        traslados.add(new Traslados(16.123456, "IVA", 30.9));
//
//        comprobante.setTraslados(traslados);
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Comprobante.class, new ComprobanteSerializer());
//        System.out.println(gsonBuilder.setPrettyPrinting().create().toJson(comprobante));
//    }
}