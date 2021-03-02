package com.nicolas.zenos.taskslist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.nicolas.zenos.taskslist.R;
import com.nicolas.zenos.taskslist.helper.TaskDAO;
import com.nicolas.zenos.taskslist.model.Task;

public class AddTaskActivity extends AppCompatActivity {

    private TextInputEditText inputTask;
    private Task taskSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        inputTask = findViewById(R.id.inputTextTask);

        // Recuperar o nome da task caso seja edição
        taskSelected = (Task) getIntent().getSerializableExtra("taskSelected");

        // Setar texto
        if (taskSelected != null) {
            inputTask.setText(taskSelected.getTaskName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveItem:
                TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                String taskName = inputTask.getText().toString();
                if (taskSelected != null) {
                    Task task = new Task();
                    task.setTaskName(taskName);
                    task.setId(taskSelected.getId());
                    //Update no banco
                    if (taskDAO.update(task)) {
                        Toast.makeText(getApplicationContext(), "Tarefa atualizada com sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao atualizar tarefa", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (!taskName.isEmpty()) {
                        Task task = new Task();
                        task.setTaskName(taskName);
                        if (taskDAO.save(task)) {
                            Toast.makeText(getApplicationContext(), "Tarefa salva com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Por favor preencha o campo", Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}