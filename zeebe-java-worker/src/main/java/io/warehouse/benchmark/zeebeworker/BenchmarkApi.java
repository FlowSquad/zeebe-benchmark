package io.warehouse.benchmark.zeebeworker;

import io.camunda.zeebe.client.ZeebeClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@Tag(name = "Benchmark API")
@RequestMapping("/api/benchmark")
public class BenchmarkApi {

    private final ZeebeClient zeebeClient;

    @PostMapping("/start/benchmark/{count}/{async}/{parallel}")
    @Operation(summary = "Start Benchmark")
    public ResponseEntity<Void> startBenchmark(
            @PathVariable("count") final Integer count,
            @PathVariable() final Boolean async,
            @PathVariable("parallel") final Integer parallel) {

        final List<String> parallelAmount = IntStream.range(0, parallel).boxed().map(Objects::toString).collect(Collectors.toList());

        if (async) {
            IntStream.range(0, count).forEach(obj ->
                    this.zeebeClient.newCreateInstanceCommand()
                            .bpmnProcessId("benchmark")
                            .latestVersion()
                            .variables(Map.of("parallelAmount", parallelAmount))
                            .send()
            );
        } else {
            IntStream.range(0, count).forEach(obj ->
                    this.zeebeClient.newCreateInstanceCommand()
                            .bpmnProcessId("benchmark")
                            .latestVersion()
                            .variables(Map.of("parallelAmount", parallelAmount))
                            .send()
                            .join(1, TimeUnit.MINUTES)
            );
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/start/benchmarkjs/{count}/{async}/{parallel}")
    @Operation(summary = "Start Benchmark JS")
    public ResponseEntity<Void> startBenchmarkJS(
            @PathVariable("count") final Integer count,
            @PathVariable() final Boolean async,
            @PathVariable("parallel") final Integer parallel) {

        final List<String> parallelAmount = IntStream.range(0, parallel).boxed().map(Objects::toString).collect(Collectors.toList());

        if (async) {
            IntStream.range(0, count).forEach(obj ->
                    this.zeebeClient.newCreateInstanceCommand()
                            .bpmnProcessId("benchmarkJS")
                            .latestVersion()
                            .variables(Map.of("parallelAmount", parallelAmount))
                            .send()
            );
        } else {
            IntStream.range(0, count).forEach(obj ->
                    this.zeebeClient.newCreateInstanceCommand()
                            .bpmnProcessId("benchmarkJS")
                            .latestVersion()
                            .variables(Map.of("parallelAmount", parallelAmount))
                            .send()
                            .join(1, TimeUnit.MINUTES)
            );
        }
        return ResponseEntity.ok().build();
    }

}
