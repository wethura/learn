package com.wethura.spring.controller;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.DoubleHistogram;
import io.opentelemetry.semconv.HttpAttributes;
import io.opentelemetry.semconv.UrlAttributes;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableWebSecurity
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HttpServletRequest request;

    public static final DoubleHistogram dhg = GlobalOpenTelemetry.getMeter("my-demo")
            .histogramBuilder("http.server.duration")
            .setUnit("ms")
            .setDescription("Duration of HTTP server requests.(demo)")
            .setExplicitBucketBoundariesAdvice(
                    Arrays.asList(0.005, 0.01, 0.025, 0.05, 0.075, 0.1, 0.25, 0.5, 0.75, 1.0, 2.5, 5.0, 7.5, 10.0))
            .build();


    @GetMapping("/permit")
    public Map<String, Object> permit() {

        Attributes attributes = Attributes.of(HttpAttributes.HTTP_REQUEST_METHOD, request.getMethod(),
                        UrlAttributes.URL_SCHEME, request.getScheme(),
                        HttpAttributes.HTTP_ROUTE, request.getServletPath())
                .toBuilder().build();
        dhg.record(1, attributes);

        final HashMap<String, Object> map = new HashMap<>();

        final Iterator<String> iterator = request.getHeaderNames().asIterator();
        while (iterator.hasNext()) {

            final String next = iterator.next();
            final StringBuilder builder = new StringBuilder();

            final Enumeration<String> headers = request.getHeaders(next);
            while (headers.hasMoreElements()) {
                builder.append(String.format(" %s", headers.nextElement()));
            }

            map.put(next, builder.toString());
        }

        map.put("remote addr", request.getRemoteAddr());
        map.put("remote host", request.getRemoteHost());
        map.put("remote port", request.getRemotePort());

        return map;
    }

    @GetMapping("/permit/{id}")
    public Map<String, Object> permitId(@PathVariable String id) {
        final Map<String, Object> permit = permit();
        permit.put("pid", id);

        return permit;
    }

    @GetMapping("/no_permit")
    public String no_permit() {
        return "Hello World!";
    }

    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        return "Sleep finished!";
    }
}
