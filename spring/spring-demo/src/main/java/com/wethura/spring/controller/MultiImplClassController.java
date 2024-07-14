package com.wethura.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wethura.spring.controller.model.InsertParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sola
 **/
@RestController
@RequestMapping("/multi_impl")
public class MultiImplClassController {

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public String test(@RequestBody InsertParams info) throws JsonProcessingException {
        System.out.println(info);

        final String value = mapper.writeValueAsString(info);

        System.out.println(value);

        final Object parsedObj = mapper.readerFor(InsertParams.class).readValue(value);
        System.out.println(parsedObj);

        return "success";
    }
}
