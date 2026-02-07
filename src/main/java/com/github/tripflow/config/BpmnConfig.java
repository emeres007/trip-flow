package com.github.tripflow.config;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BpmnConfig {

    @Bean
    public CommandLineRunner deployWorkflows(ZeebeClient client) {
        return args -> {
            client.newDeployResourceCommand()
                    .addResourceFromClasspath("bpmn/trip-flow.bpmn")
                    .send()
                    .join();
        };
    }
}
