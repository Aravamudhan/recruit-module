package com.amudhan.recruit;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RecruitModuleApplication {

  private static final Logger log = LoggerFactory.getLogger(RecruitModuleApplication.class);

  public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(RecruitModuleApplication.class);
    Map<String, Object> defProperties = new HashMap<>();
    defProperties.put("spring.profiles.default", "dev");
    app.setDefaultProperties(defProperties);
    Environment env = app.run(args).getEnvironment();
    String protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
      protocol = "https";
    }
    log.info(
        "\n----------------------------------------------------------\n\t"
            + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
            + "External: \t{}://{}:{}\n\t"
            + "Profile(s): \t{}\n----------------------------------------------------------",
        env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"),
        protocol, InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
        env.getActiveProfiles());
  }
}
