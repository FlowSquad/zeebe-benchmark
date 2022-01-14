package io.warehouse.benchmark.zeebeworker;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(resources = {
        "classpath:bpmn/Benchmark.bpmn",
        "classpath:bpmn/BenchmarkJS.bpmn"})
public class ZeebeWorkerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ZeebeWorkerApplication.class, args);
    }

}
