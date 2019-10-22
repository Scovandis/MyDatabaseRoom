package com.example.mydatabaseroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.mydatabaseroom.adapter.TaskAdapter;
import com.example.mydatabaseroom.database.DatabaseClient;
import com.example.mydatabaseroom.entity.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton buttonAdd;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_task);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAdd = findViewById(R.id.fab_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(mIntent);
            }
        });

        getTask();
    }

    private void getTask() {
        class GetTask extends AsyncTask<Void, Void, List<Task>>{

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> list) {
                super.onPostExecute(list);
                TaskAdapter adapter = new TaskAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);
            }
        }
        GetTask gt = new GetTask();
        gt.execute();
    }
}
