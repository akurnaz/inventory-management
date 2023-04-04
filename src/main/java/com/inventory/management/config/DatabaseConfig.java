package com.inventory.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.inventory.management.infra.secondary")
@EnableJpaAuditing(auditorAwareRef = "jpaAuditorAware")
@EnableTransactionManagement
public class DatabaseConfig {

}
