package io.warehouse.benchmark.zeebeworker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class BenchmarkProcessClient {

    @ZeebeWorker(type = "firstJob")
    public void koWelle(final JobClient client, final ActivatedJob job) {
        log.info("Type: {}, Key: {}", job.getType(), job.getKey());
        this.completeJob(client, job, Map.of());
    }

    @ZeebeWorker(type = "secondJob")
    public void secondJob(final JobClient client, final ActivatedJob job) {
        log.info("Type: {}, Key: {}", job.getType(), job.getKey());
        this.completeJob(client, job, Map.of());
    }

    @ZeebeWorker(type = "finish")
    public void finish(final JobClient client, final ActivatedJob job) {
        log.info("Type: {}, Key: {}", job.getType(), job.getKey());
        this.completeJob(client, job, Map.of());
    }

    private void completeJob(final JobClient client, final ActivatedJob job, final Map<String, Object> variables) {
        client.newCompleteCommand(job.getKey())
                .variables(variables)
                .send()
                .join(1, TimeUnit.MINUTES);
    }

}
