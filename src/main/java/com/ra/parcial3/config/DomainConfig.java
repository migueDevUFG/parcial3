package com.ra.parcial3.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.ra.parcial3.domain")
@EnableJpaRepositories("com.ra.parcial3.repos")
@EnableTransactionManagement
public class DomainConfig {
}
