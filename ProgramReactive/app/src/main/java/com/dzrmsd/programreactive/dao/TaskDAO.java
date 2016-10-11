package com.dzrmsd.programreactive.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.dzrmsd.programreactive.data.DBHelper;
import com.dzrmsd.programreactive.pojos.Task;
import com.dzrmsd.programreactive.reactive.Reactive;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


public class TaskDAO {

    /**
     * Observer   -> Observador: Se suscribe al Observable reaccionando ante los elementos que emite el Observable
     *  No bloquea ningún hilo mientras espera a que el observable emita elementos
     * Observable -> Observable: Emite elementos
     * **/

    /**
     *Observer: Cuenta con tres métodos: onCompleted, onError y onNext
     * onCompleted notifica al Observable que ha terminado de enviar elementos
     * onError: notifica al Observer de que se produjo un error
     * onNext provee al Observer con nuevos elementos
     *
     *
     * Observable: Más complejo que el Observable
     * subscribeOn especifica en que hilo se va a ejecutar el observable mientras que
     * observeOn especifica en donde se va a ejecutar el código del observer
     * */

    private Context context;
    private DBHelper dbHelper;

    public TaskDAO(Context context){
        this.context = context;
        this.dbHelper = DBHelper.newInstance(context);
    }

    public Observable<Task> getTask(String id){
        return Observable.defer(
                () -> Observable.just(getPojoTask(id)))
                .subscribeOn(Reactive.SuscribeScheduler());
    }

    public Observable<List<Task>> getTaskList(){
        return Observable.defer(() -> Observable.just(getListTask()))
                .subscribeOn(Reactive.SuscribeScheduler());
    }

    private List<Task> getListTask(){
        List<Task> taskList = new ArrayList<>();
        String[] columns = new String[]{"_id", "title", "content"};
        Cursor cursor = dbHelper.getReadableDatabase().query("tasks", columns, null, null, null, null, null );

        while(cursor.moveToNext()){
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setTitle(cursor.getString(1));
            task.setContent(cursor.getString(2));
            taskList.add(task);
        }
        cursor.close();
        return taskList;
    }

    private Task getPojoTask(String id){
        Task task = new Task();
        String[] columns = new String[]{"title", "content"};
        Cursor cursor = dbHelper.getReadableDatabase().query("tasks", columns, "_id = ?", new String[]{id}, null, null, null );
        while(cursor.moveToNext()){
            task.setId(Integer.parseInt(id));
            task.setTitle(cursor.getString(0));
            task.setContent(cursor.getString(1));
        }
        cursor.close();
        return task;
    }

    public long saveNote(String title, String body){
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", body);
        return dbHelper.getWritableDatabase().insert("tasks", null, values);
    }
}
