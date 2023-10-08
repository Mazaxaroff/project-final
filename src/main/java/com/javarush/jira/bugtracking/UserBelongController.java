package com.javarush.jira.bugtracking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UserBelongController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Transactional
public class UserBelongController {
    static final String REST_URL = "/api/user-belong";
    private UserBelongService userBelongService;

    @PostMapping("/{taskId}")
    public void addTaskToUser(@RequestParam Long userId, @PathVariable("taskId") Long taskId) {
        log.info("to task {} was subscribed user {}", taskId, userId);
        userBelongService.addTaskToUser(userId, taskId);
    }
}
