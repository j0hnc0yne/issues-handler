package com.demo.issues_handler.model;

import java.util.Map;

public record IssuesData(int total, Map<String, Integer> labelCounts) {
}
