package com.tailorit.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Profile("dev")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.jndi")
    public JndiPropertyHolder primary() {
        return new JndiPropertyHolder();
    }

    private static class JndiPropertyHolder {
        private String jndiName;

        public String getJndiName() {
            return jndiName;
        }

        public void setJndiName(String jndiName) {
            this.jndiName = jndiName;
        }
    }
}