package com.javarush.jira.bugtracking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = TaskController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Transactional
public class TaskController {
    static final String REST_URL = "/api/tasks";
    private TaskService taskService;

    @PostMapping("/{taskId}/add-tag")
    public void addTagToTask(@PathVariable("taskId") Long taskId, @RequestParam String tag) {
        log.info("to task {} was added tag {}", taskId, tag);
        taskService.addTagToTask(taskId, tag);
    }

    @GetMapping("/{taskId}/tags")
    public Set<String> getAllTags(@PathVariable("taskId") Long taskId) {
        log.info("get all tags from task {}", taskId);
        return taskService.getAllTags(taskId);
    }

    @DeleteMapping("/{taskId}/delete-tag")
    public void deleteTag(@PathVariable("taskId") Long taskId, @RequestParam String tag) {
        log.info("to task {} was deleted tag {}", taskId, tag);
        taskService.deleteTagFromTask(taskId, tag);
    }

    @DeleteMapping("/{taskId}/delete-all-tags")
    public void deleteAllTag(@PathVariable("taskId") Long taskId) {
        log.info("to task {} was deleted all tags", taskId);
        taskService.deleteAllTags(taskId);
    }


}
