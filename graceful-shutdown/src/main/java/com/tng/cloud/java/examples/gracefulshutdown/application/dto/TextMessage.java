package com.tng.cloud.java.examples.gracefulshutdown.application.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TextMessage {
    private final String message;
}
