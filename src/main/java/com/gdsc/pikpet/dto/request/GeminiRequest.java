package com.gdsc.pikpet.dto.request;

import java.util.List;

import java.util.List;

import java.util.List;

public record GeminiRequest(List<Content> contents) {

    public record Content(List<Part> parts) {}

    public sealed interface Part permits TextPart, InlineDataPart {}

    public record TextPart(String text) implements Part {}

    public record InlineDataPart(InlineData inline_data) implements Part {
        public record InlineData(String mime_type, String data) {}
    }
}
