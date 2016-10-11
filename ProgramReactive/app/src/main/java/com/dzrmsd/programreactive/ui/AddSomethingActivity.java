package com.dzrmsd.programreactive.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.dzrmsd.programreactive.R;
import com.dzrmsd.programreactive.dao.TaskDAO;
import com.dzrmsd.programreactive.data.DBHelper;

public class AddSomethingActivity extends AppCompatActivity {

    EditText etTitleTask;
    EditText etBodyTask;
    Button btnAddTask;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = DBHelper.newInstance(getBaseContext());
        setContentView(R.layout.activity_add_something);
        etTitleTask = (EditText) findViewById(R.id.etTitleTask);
        etBodyTask = (EditText) findViewById(R.id.etDetailTask);
        btnAddTask = (Button) findViewById(R.id.addTask);
        btnAddTask.setOnClickListener( __ -> saveTask());
    }

    private void saveTask(){
        if(etTitleTask.getText().toString().isEmpty() && etBodyTask.getText().toString().isEmpty())
            return;
        new TaskDAO(getBaseContext()).saveNote(etTitleTask.getText().toString(), etBodyTask.getText().toString());
        this.finish();
    }
}
