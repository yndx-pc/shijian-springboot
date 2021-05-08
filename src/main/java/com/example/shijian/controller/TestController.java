package com.example.shijian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    PrintWriter printWriter = null;

    @RequestMapping("/test")
    @ResponseBody
    public void index(HttpServletResponse response) throws JsonProcessingException {
        response.setContentType("application/json");

        response.setCharacterEncoding("utf-8");
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from food_frugal_foods");
        printWriter.write(new ObjectMapper().writeValueAsString(list));
    }
}
