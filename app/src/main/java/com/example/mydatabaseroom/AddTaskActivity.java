package com.example.mydatabaseroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.PrimaryKey;

import com.example.mydatabaseroom.database.DatabaseClient;
import com.example.mydatabaseroom.entity.Task;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextTask, editTextDesc, editTextFinishBy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_activity);


        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesk = editTextDesc.getText().toString().trim();
        final String sFinish = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()) {
            editTextTask.setError("task required");
            editTextTask.requestFocus();
            return;
        }
        if (sDesk.isEmpty()) {
            editTextDesc.setError("desk required");
            editTextDesc.requestFocus();
            return;
        }
        if (sFinish.isEmpty()) {
            editTextFinishBy.setError("finish is required");
            editTextFinishBy.requestFocus();
            return;
        }
        class SaveTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                //creating a task
                Task task = new Task();
                task.setTask(sTask);
                task.setDesk(sDesk);
                task.setFinishBy(sFinish);
                task.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveTask s = new SaveTask();
        s.execute();
    }
}
