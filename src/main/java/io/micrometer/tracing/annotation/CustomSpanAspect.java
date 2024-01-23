package io.micrometer.tracing.annotation;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CustomSpanAspect extends SpanAspect {
    private final MethodInvocationProcessor methodInvocationProcessor;

    public CustomSpanAspect(MethodInvocationProcessor methodInvocationProcessor) {
        super(methodInvocationProcessor);
        this.methodInvocationProcessor = methodInvocationProcessor;
    }

    @Override
    public Object newSpanMethod(ProceedingJoinPoint pjp) throws Throwable {
        Method method = getMethod(pjp);
        NewSpan newSpan = method.getAnnotation(NewSpan.class); // newSpan is null because of method is part of proxy
        newSpan = MethodUtils.getAnnotation(method, NewSpan.class, true, true); // newSpan is not null due to MethodUtils applying deep search
        return methodInvocationProcessor.process(new SpanAspectMethodInvocation(pjp, method), newSpan, null);
    }

    // original method
    private Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        return pjp.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
    }
}
