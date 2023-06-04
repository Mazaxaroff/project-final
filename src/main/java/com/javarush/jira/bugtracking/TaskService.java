package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.mapper.TaskMapper;
import com.javarush.jira.bugtracking.internal.model.Task;
import com.javarush.jira.bugtracking.internal.model.UserBelong;
import com.javarush.jira.bugtracking.internal.repository.TaskRepository;
import com.javarush.jira.bugtracking.internal.repository.UserBelongRepository;
import com.javarush.jira.bugtracking.to.ObjectType;
import com.javarush.jira.bugtracking.to.TaskTo;
import com.javarush.jira.login.User;
import com.javarush.jira.login.internal.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends BugtrackingService<Task, TaskTo, TaskRepository> {

    private final UserRepository userRepository;
    private final UserBelongRepository userBelongRepository;

    public TaskService(TaskRepository repository, TaskMapper mapper, UserRepository userRepository, UserBelongRepository userBelongRepository) {
        super(repository, mapper);
        this.userRepository = userRepository;
        this.userBelongRepository = userBelongRepository;
    }

    public List<TaskTo> getAll() {
        return mapper.toToList(repository.getAll());
    }

    public void addTagToTask(Long taskId, String tag) {
        Task task = repository.getExisted(taskId);
        task.getTags().add(tag);
        repository.save(task);
    }

    public void deleteTagFromTask(Long taskId, String tag){
        Task task = repository.getExisted(taskId);
        if(task.getTags().contains(tag)){
            task.getTags().remove(tag);
        }
        repository.save(task);

    }

    public void addTaskToUser(Long userId, Long taskId) {
        User user = userRepository.getExisted(userId);
        UserBelong userBelong = new UserBelong();

        if (!userBelong.getUserId().equals(userId)) {
            userBelong.setUserId(userId);
            userBelong.setObjectId(taskId);
            userBelong.setObjectType(ObjectType.TASK);
            if (!user.getRoles().isEmpty()) {
                userBelong.setUserTypeCode(user.getRoles().stream().findAny().get().name());
            }
        }
        userBelongRepository.save(userBelong);
    }
}
