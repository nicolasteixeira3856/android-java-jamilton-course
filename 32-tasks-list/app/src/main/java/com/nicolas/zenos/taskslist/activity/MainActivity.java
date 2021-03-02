package com.nicolas.zenos.taskslist.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nicolas.zenos.taskslist.R;
import com.nicolas.zenos.taskslist.activity.AddTaskActivity;
import com.nicolas.zenos.taskslist.adapter.TaskAdapter;
import com.nicolas.zenos.taskslist.helper.DbHelper;
import com.nicolas.zenos.taskslist.helper.RecyclerItemClickListener;
import com.nicolas.zenos.taskslist.helper.TaskDAO;
import com.nicolas.zenos.taskslist.model.Task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        //ClickListener
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Task taskSelected = taskList.get(position);
                                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                                intent.putExtra("taskSelected", taskSelected);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Task taskSeleceted = taskList.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                                dialog.setTitle("Confirmar exclusão?");
                                dialog.setMessage("Deseja excluir a tarefa: " + taskSeleceted.getTaskName() + "?");

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                                        if (taskDAO.delete(taskSeleceted)) {
                                            loadTaskList();
                                            Toast.makeText(getApplicationContext(), "Tarefa deletada com sucesso", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Erro ao deletar tarefa", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                dialog.setNegativeButton("Não", null);

                                dialog.create();
                                dialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        loadTaskList();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadTaskList () {
        // Lista de tarefas
        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
        taskList = taskDAO.list();

        // Adapter
        taskAdapter = new TaskAdapter(taskList);

        // Configurar recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(taskAdapter);
    }
}