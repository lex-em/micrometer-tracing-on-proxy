## Repository description

This repository does not contain any useful practices and only reproduce the bug https://github.com/micrometer-metrics/tracing/issues/544

## Reproducing
- Run spring application com.example.micrometer.tracing.on.proxy.App in debug mode
- Set breakpoint at io.micrometer.tracing.annotation.CustomSpanAspect:21
- Open http://localhost:8080/swagger-ui.html
- Open apple-controller -> GET /apple -> Try it out -> Execute
- Debug CustomSpanAspect.newSpanMethod()

You can also run jaeger from docker-compose.yml, uncomment the contents of application.yml from the repository root, rerun App, do the same things and found traces in jaeger search http://localhost:16686/

Jaeger Interpretation:
- In normal situation there 2 spans
- Comment out CustomSpanAspect bean in TracingConfig and rerun App
- Call the App again from swagger-ui and check the traces in jeager
- There will be a trace with one wrong span (compare with a trace with 2 spans)