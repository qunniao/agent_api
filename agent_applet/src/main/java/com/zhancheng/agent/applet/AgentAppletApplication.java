package com.zhancheng.agent.applet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author BianShuHeng
 */
@ComponentScan(value = "com.zhancheng")
@SpringBootApplication
public class AgentAppletApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AgentAppletApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(AgentAppletApplication.class);
    }
}
