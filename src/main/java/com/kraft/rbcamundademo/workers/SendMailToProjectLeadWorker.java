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
public class SendMailToProjectLeadWorker {
    private final static String TIMEOUT_VARIABLE = "projectLeadTimeout";
    private final static String TIMEOUT = "PT1M";

    @JobWorker(type = "sendMailToProjectLead")
    public Map<String, Object> handle(ActivatedJob job) {
        log.info("send mail to line manager for process instance {}", job.getProcessInstanceKey());
        log.info("set timeout for following task to {}", TIMEOUT);

        return Map.of(TIMEOUT_VARIABLE, TIMEOUT);
    }
}
