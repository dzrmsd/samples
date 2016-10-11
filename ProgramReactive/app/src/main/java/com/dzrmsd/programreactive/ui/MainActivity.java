package com.dzrmsd.programreactive.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.dzrmsd.programreactive.R;
import com.dzrmsd.programreactive.dao.TaskDAO;
import com.dzrmsd.programreactive.pojos.Task;
import com.dzrmsd.programreactive.reactive.Reactive;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    TextView textView;

    @Override
    protected void onResume() {
        bind();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tvAllFinders);
        btnAdd = (Button) findViewById(R.id.addForm);
        btnAdd.setOnClickListener(___ -> showActivityForm());
    }

    public void showActivityForm() {
        final Intent showForm = new Intent(this, AddSomethingActivity.class);
        startActivity(showForm);
    }

    private void bind() {
        Reactive.reactive();
        new TaskDAO(getBaseContext()).getTaskList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccess,
                        this::onError
                );
    }

    private void onSuccess(final List<Task> list) {
        textView.setText("");
        for (Task task : list) {
            textView.append((task.getTitle() + "\n" + task.getContent() + "\n"));
        }
    }

    private void onError(final Throwable throwable) {
        textView.setText("Ocurrio un error " + throwable.getMessage());
        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
    }
}
