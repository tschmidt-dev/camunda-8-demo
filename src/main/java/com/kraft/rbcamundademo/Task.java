package com.kraft.rbcamundademo;

import lombok.Builder;

@Builder
public record Task(long jobKey, long timestamp, String name, long processInstanceKey) {
}
