package com.kraft.rbcamundademo.workers;

import com.kraft.rbcamundademo.Task;
import com.kraft.rbcamundademo.TaskPersistence;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserTaskWorker {
    private final TaskPersistence taskPersistence;

    @JobWorker(type = "io.camunda.zeebe:userTask", autoComplete = false,
            timeout = 1000L * 60L * 60L * 24L * 365L)
    public void handleUserTaskCreation(ActivatedJob job) {
        var taskName = job.getCustomHeaders().getOrDefault("name", job.getElementId());
        long processInstanceKey = job.getProcessInstanceKey();

        log.info("Handling user task {} for process instance {}", taskName, processInstanceKey);

        taskPersistence.addTask(Task.builder()
                .jobKey(job.getKey())
                .timestamp(Instant.now().toEpochMilli())
                .name(taskName)
                .processInstanceKey(processInstanceKey)
                .build());
    }
}
