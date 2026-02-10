package com.github.tripflow.infrastructure.config;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BpmnConfig {

/*    @Bean
    public CommandLineRunner deployWorkflows(ZeebeClient client) {
        log.info("Deploying BPMN process - trip-flow...");
        return args -> {
            var event = client.newDeployResourceCommand()
                    .addResourceFromClasspath("bpmn/trip-flow.bpmn")
                    .send()
                    .join();

            log.info("Deploy result: {}", event);
        };
    }*/
}
