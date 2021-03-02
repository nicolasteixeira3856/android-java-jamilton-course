package com.nicolas.zenos.taskslist.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nicolas.zenos.taskslist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDao {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TaskDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getReadableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Task task) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("name", task.getTaskName());
            write.insert(DbHelper.TASKS_TABLE, null, cv);
            Log.i("INFO", "Tarefa salva com sucesso");
            return true;
        } catch (Exception e) {
            Log.i("INFO", "Erro ao salvar tarefa");
            return false;
        }

    }

    @Override
    public boolean update(Task task) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", task.getTaskName());
            String[] args = {task.getId().toString()};
            write.update(DbHelper.TASKS_TABLE, cv, "id=?", args);
            Log.i("INFO", "Tarefa atualizada com sucesso");
            return true;
        } catch (Exception e) {
            Log.i("INFO", "Erro ao atualizada tarefa");
            return false;
        }
    }

    @Override
    public boolean delete(Task task) {
        try {
            String[] args = {task.getId().toString()};
            write.delete(DbHelper.TASKS_TABLE, "id=?", args);
            Log.i("INFO", "Tarefa removida com sucesso");
            return true;
        } catch (Exception e) {
            Log.i("INFO", "Erro ao remover tarefa");
            return false;
        }
    }

    @Override
    public List<Task> list() {
        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TASKS_TABLE + " ;";
        Cursor c = write.rawQuery(sql, null);

        while (c.moveToNext()){
            Task task = new Task();

            Long id = c.getLong(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));

            task.setId(id);
            task.setTaskName(name);

            tasks.add(task);
            //Log.i("tarefaDao", String.valueOf(task.getId()));
            //Log.i("tarefaDao", task.getTaskName());
        }

        return tasks;
    }
}
