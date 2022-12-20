package io.comwork.todo.api.utils;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

public class EnvironmentPropertiesFactoryBean extends PropertiesFactoryBean {
    public EnvironmentPropertiesFactoryBean(String location) {
        this(new Resource[] {new ClassPathResource(location,
                EnvironmentPropertiesFactoryBean.class.getClass().getClassLoader())});
    }
    public EnvironmentPropertiesFactoryBean(Resource[] locations) {
        super.setIgnoreResourceNotFound(true);
        super.setLocations(locations);
    }
    private void setValueIfNotEmpty(Object key, Properties properties, String value) {
        if (isNotBlank(value)) {
            properties.put(key, value);
        }
    }
    /**
     * Check if an environment variable exist for each keys of the merged properties files results if
     * this env var exists, replace the value of the property with its value
     */
    private void overrideProperty(Properties properties, Object k) {
        String keyStr = k.toString();
        String envKey = keyStr.toUpperCase().replace(".", "_");
        setValueIfNotEmpty(k, properties, System.getenv(envKey));
        setValueIfNotEmpty(k, properties, System.getenv(keyStr));
        setValueIfNotEmpty(k, properties, System.getProperty(envKey));
        setValueIfNotEmpty(k, properties, System.getProperty(keyStr));
    }
    @Override
    protected Properties mergeProperties() throws IOException {
        Properties result = super.mergeProperties();
        result.keySet().stream().forEach(k -> overrideProperty(result, k));
        return result;
    }
    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        props.keySet().stream().forEach(k -> overrideProperty(props, k));
    }
}

