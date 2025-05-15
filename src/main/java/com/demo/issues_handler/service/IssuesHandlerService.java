package com.demo.issues_handler.service;

import com.demo.issues_handler.exception.IssuesException;
import com.demo.issues_handler.exception.RepoNotFoundException;
import com.demo.issues_handler.model.IssuesData;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IssuesHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssuesHandlerService.class);

    private final GitHub gitHub;

    public IssuesHandlerService(GitHub gitHub) {
        this.gitHub = gitHub;
    }

    public IssuesData getIssuesData(String owner, String repo, boolean openOnly) throws IssuesException {
        LOGGER.debug("Fetching issues data for repo: {}/{}", owner, repo);
        try {
            List<GHIssue> issues = gitHub.getRepository(owner + "/" + repo).getIssues(openOnly ? GHIssueState.OPEN : GHIssueState.ALL);
            Map<String, Integer> labelCounts = issues.stream()
                    .flatMap(issue -> issue.getLabels().stream())
                    .collect(Collectors.toMap(
                            GHLabel::getName,
                            label -> 1,
                            Integer::sum));
            return new IssuesData(issues.size(), labelCounts);
        } catch (org.kohsuke.github.GHFileNotFoundException e) {
            LOGGER.info("Repo does not exist: {}/{}", owner, repo, e);
            throw new RepoNotFoundException("Repo does not exist");
        }catch (IOException e) {
            LOGGER.error("Error fetching issues data", e);
            throw new IssuesException(e);
        }
    }

}
