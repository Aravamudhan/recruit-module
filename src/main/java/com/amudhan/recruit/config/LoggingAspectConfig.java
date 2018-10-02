package com.amudhan.recruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.amudhan.recruit.aspect.logging.LoggingAspect;

/**
 * This is where the bean for logging aspect is created
 * 
 * @author amudhan
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfig {
  @Bean
  public LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }

}
