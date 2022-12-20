package io.comwork.todo.api.config;

import io.comwork.todo.api.utils.EnvironmentPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;


@Configuration
public class PropertiesConfiguration {
    @Bean("properties")
    public Properties generateProperties() throws IOException {
        EnvironmentPropertiesFactoryBean factory = new EnvironmentPropertiesFactoryBean("application.properties");
        return factory.getObject();
    }
}
