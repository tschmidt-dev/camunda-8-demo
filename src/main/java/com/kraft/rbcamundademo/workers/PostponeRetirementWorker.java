package com.kraft.rbcamundademo.workers;

import com.kraft.rbcamundademo.TaskPersistence;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class PostponeRetirementWorker {
    private final TaskPersistence taskPersistence;

    @JobWorker(type = "postponeRetirement")
    public void handlePostponeRetirement(ActivatedJob job) {
        log.info("postpone retirement for process instance {}", job.getProcessInstanceKey());
        taskPersistence.cancelTasksForProcessInstance(job.getProcessInstanceKey());
    }
}
