package com.vergilyn.sample.springboot.configuration;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfo {

    private String name;
    private Integer port;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
