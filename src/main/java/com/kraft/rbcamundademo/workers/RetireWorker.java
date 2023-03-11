package com.kraft.rbcamundademo.workers;

import com.kraft.rbcamundademo.TaskPersistence;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class RetireWorker {
    private final TaskPersistence taskPersistence;

    @JobWorker(type = "retire")
    public void handleRetire(ActivatedJob job) {
        log.info("retire for process instance {}", job.getProcessInstanceKey());
        taskPersistence.cancelTasksForProcessInstance(job.getProcessInstanceKey());
    }
}
