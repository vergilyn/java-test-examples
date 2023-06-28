package com.vergilyn.examples.spock;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class ListService {

    public List<Object> list(){
        return JSON.parseArray("[\n" +
                "  {\n" +
                "    \"failureNum\": 1,\n" +
                "    \"messageType\": 110,\n" +
                "    \"ruleId\": 10302,\n" +
                "    \"successNum\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"failureNum\": 4,\n" +
                "    \"messageType\": 131,\n" +
                "    \"ruleId\": 10854,\n" +
                "    \"successNum\": 54\n" +
                "  },\n" +
                "  {\n" +
                "    \"failureNum\": 0,\n" +
                "    \"messageType\": 131,\n" +
                "    \"ruleId\": 10851,\n" +
                "    \"successNum\": 6\n" +
                "  },\n" +
                "  {\n" +
                "    \"failureNum\": 0,\n" +
                "    \"messageType\": 131,\n" +
                "    \"ruleId\": 10853,\n" +
                "    \"successNum\": 5\n" +
                "  },\n" +
                "  {\n" +
                "    \"failureNum\": 0,\n" +
                "    \"messageType\": 100,\n" +
                "    \"ruleId\": 10302,\n" +
                "    \"successNum\": 17\n" +
                "  }\n" +
                "]");
    }
}
