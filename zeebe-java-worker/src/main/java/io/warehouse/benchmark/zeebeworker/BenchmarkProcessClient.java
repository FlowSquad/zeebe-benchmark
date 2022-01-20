package io.warehouse.benchmark.zeebeworker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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


}
