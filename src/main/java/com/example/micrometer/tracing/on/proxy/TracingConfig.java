package com.example.micrometer.tracing.on.proxy;

import io.micrometer.tracing.annotation.CustomSpanAspect;
import io.micrometer.tracing.annotation.MethodInvocationProcessor;
import io.micrometer.tracing.annotation.SpanAspect;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpProperties;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpTracingConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {
    @Bean
    public SpanAspect customSpanASpect(MethodInvocationProcessor methodInvocationProcessor) {
        return new CustomSpanAspect(methodInvocationProcessor);
    }

    @Bean
    public OtlpGrpcSpanExporter otlpHttpSpanExporter(
        OtlpProperties properties,
        OtlpTracingConnectionDetails connectionDetails
    ) {
        var builder = OtlpGrpcSpanExporter.builder()
            .setEndpoint(connectionDetails.getUrl())
            .setTimeout(properties.getTimeout());
        properties.getHeaders().forEach(builder::addHeader);
        return builder.build();
    }
}
