package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.internal.model.UserBelong;
import com.javarush.jira.bugtracking.internal.repository.UserBelongRepository;
import com.javarush.jira.bugtracking.to.ObjectType;
import com.javarush.jira.login.User;
import com.javarush.jira.login.internal.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBelongService {
    private final UserBelongRepository userBelongRepository;
    private final UserRepository userRepository;

    public UserBelongService(UserBelongRepository userBelongRepository, UserRepository userRepository) {
        this.userBelongRepository = userBelongRepository;
        this.userRepository = userRepository;
    }

    public void addTaskToUser(Long userId, Long taskId) {
        User user = userRepository.getExisted(userId);
        UserBelong userBelong = new UserBelong();

        userBelong.setUserId(userId);
        userBelong.setObjectId(taskId);
        userBelong.setObjectType(ObjectType.TASK);
        if (!user.getRoles().isEmpty()) {
            userBelong.setUserTypeCode(user.getRoles().stream().findAny().get().name());
        }
        userBelongRepository.save(userBelong);
    }
}
