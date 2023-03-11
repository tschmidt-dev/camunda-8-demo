package com.kraft.rbcamundademo;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class DemoController {
    private final static String DECISION_VARIABLE = "retire";

    private final ZeebeClient client;
    private final TaskPersistence taskPersistence;

    @PostMapping("/start")
    public long startProcess() {
        ProcessInstanceEvent processInstance = client.newCreateInstanceCommand()
                .bpmnProcessId("process1")
                .latestVersion()
                .send().join();

        return processInstance.getProcessInstanceKey();
    }

    @PostMapping("/complete-task/{jobKey}")
    public void completeTask(@PathVariable long jobKey, @RequestParam Boolean retire) {
        Task taskToComplete = taskPersistence.getTasks().stream()
                .filter(task -> task.jobKey() == jobKey)
                .findFirst().orElseThrow(() -> new RuntimeException("Task not found"));

        var vars = Map.of(DECISION_VARIABLE, retire);
        client.newSetVariablesCommand(taskToComplete.processInstanceKey()).variables(vars).send().join();
        client.newCompleteCommand(taskToComplete.jobKey()).send().join();
        taskPersistence.removeTask(taskToComplete);
    }
}
