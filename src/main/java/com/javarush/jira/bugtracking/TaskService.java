package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.repository.TaskRepository;
import com.javarush.jira.bugtracking.to.TaskTo;
import com.javarush.jira.common.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskService extends BugtrackingService<Task, TaskTo, TaskRepository> {


    public TaskService(TaskRepository repository, BaseMapper<Task, TaskTo> mapper) {
        super(repository, mapper);
    }

    public List<TaskTo> getAll() {
        return mapper.toToList(repository.getAll());
    }

    public Set<String> getAllTags(Long taskID) {
        Task task = repository.getExisted(taskID);
        return task.getTags();
    }

    public void addTagToTask(Long taskId, String tag) {
        Task task = repository.getExisted(taskId);
        task.getTags().add(tag);
        repository.save(task);
    }

    public void deleteTagFromTask(Long taskId, String tag) {
        Task task = repository.getExisted(taskId);
        if (task.getTags().contains(tag)) {
            task.getTags().remove(tag);
        }
        repository.save(task);
    }

    public void deleteAllTags(Long taskId) {
        Task task = repository.getExisted(taskId);
        task.getTags().clear();
        repository.save(task);
    }

}
