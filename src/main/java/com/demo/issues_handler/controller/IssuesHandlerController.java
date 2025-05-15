package com.demo.issues_handler.controller;

import com.demo.issues_handler.exception.IssuesException;
import com.demo.issues_handler.model.IssuesData;
import com.demo.issues_handler.service.IssuesHandlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssuesHandlerController {

    private final IssuesHandlerService issuesHandlerService;

    public IssuesHandlerController(IssuesHandlerService issuesHandlerService) {
        this.issuesHandlerService = issuesHandlerService;
    }

    @GetMapping("/issuesdata/{owner}/{repo}")
    public IssuesData getIssues(@PathVariable String owner, @PathVariable String repo,
                                @RequestParam(required = false, defaultValue = "true") boolean openOnly)
                                        throws IssuesException {
        return issuesHandlerService.getIssuesData(owner, repo, openOnly);
    }

}
