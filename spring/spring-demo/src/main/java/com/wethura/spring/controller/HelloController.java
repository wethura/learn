package com.wethura.spring.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableWebSecurity
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/permit")
    public Map<String, Object> permit() {

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

    @GetMapping("/no_permit")
    public String no_permit() {
        return "Hello World!";
    }
}
