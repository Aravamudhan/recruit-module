package com.amudhan.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RecruitModuleApplication {

  public static void main(String[] args) {
    SpringApplication.run(RecruitModuleApplication.class, args);
  }
}
