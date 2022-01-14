# Zeebe Bug Test

Short description of how to reproduce the bug.

1. Configure and Start the Java Worker
2. Go to localhost:8081/swagger-ui.html
3. Execute one of the tests
4. Test Start Benchmark
    - count 1
    - async false
    - parallel 10000
    - -> leads to unexpected pauses in the worker
    - -> count 10000 and parallel 1 does not

5. Test Start Benchmark JS with the same config
    - No error
    
-> Before that you have to set the appropriate Zeebe config in the workers of course


