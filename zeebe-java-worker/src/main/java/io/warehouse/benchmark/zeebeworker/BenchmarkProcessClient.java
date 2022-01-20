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

    @ZeebeWorker(type = "firstJob", autoComplete = true)
    public void koWelle(final JobClient client, final ActivatedJob job) {
        log.info("Type: {}, Key: {}", job.getType(), job.getKey());
    }

    @ZeebeWorker(type = "secondJob", autoComplete = true)
    public void secondJob(final JobClient client, final ActivatedJob job) {
        log.info("Type: {}, Key: {}", job.getType(), job.getKey());
    }

    @ZeebeWorker(type = "finish", autoComplete = true)
    public void finish(final JobClient client, final ActivatedJob job) {
        log.info("Type: {}, Key: {}", job.getType(), job.getKey());
    }

    private void completeJob(final JobClient client, final ActivatedJob job, final Map<String, Object> variables) {
        client.newCompleteCommand(job.getKey())
                .variables(variables)
                .send()
                .join(1, TimeUnit.MINUTES);
    }

}
