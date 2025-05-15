package com.demo.issues_handler.config;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    GitHub gitHub( @Value("${github.baseurl}") String baseUrl, @Value("${github.token}") String token) throws Exception {
        LOGGER.info("Creating GitHub client with base URL: {}", baseUrl);
        return new GitHubBuilder()
                        .withEndpoint(baseUrl)
                        .withOAuthToken(token).build();
    }

}
