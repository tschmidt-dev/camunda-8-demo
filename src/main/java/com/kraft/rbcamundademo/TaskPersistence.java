package com.kraft.rbcamundademo;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Log4j2
@Component
public class TaskPersistence {
    private final Set<Task> tasks = new HashSet<>();

    public Set<Task> getTasks() {
        log.info("Getting tasks: {}", tasks);
        return tasks;
    }

    public void addTask(Task task) {
        log.info("Adding task: {}", task);
        tasks.add(task);
    }

    public void removeTask(Task task) {
        log.info("Removing task: {}", task);
        tasks.remove(task);
    }

    public void cancelTasksForProcessInstance(long processInstanceKey) {
        log.info("Cancelling all tasks for process instance {}", processInstanceKey);
        tasks.removeIf(task -> task.processInstanceKey() == processInstanceKey);
        log.info("Remaining tasks: {}", tasks);
    }
}
