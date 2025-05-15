package com.demo.issues_handler.model;

import java.util.Date;
import java.util.List;

public record IssueInfo (
        String issueNumber,
        String title,
        String body,
        String state,
        Date createdAt,
        List<String> labels
)
{ }
