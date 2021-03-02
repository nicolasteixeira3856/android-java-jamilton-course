package com.nicolas.zenos.taskslist.helper;

import com.nicolas.zenos.taskslist.model.Task;

import java.util.List;

public interface ITaskDao {
    public boolean save(Task task);
    public boolean update(Task task);
    public boolean delete(Task task);
    public List<Task> list();
}
