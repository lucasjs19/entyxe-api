package com.entyxe.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.entyxe.repository")
@EntityScan("com.entyxe.domain.entity")
public class JpaConfig {
}
