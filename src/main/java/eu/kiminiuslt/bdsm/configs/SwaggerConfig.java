package eu.kiminiuslt.bdsm.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(getInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("eu.kiminiuslt.bdsm"))
        .build();
  }

  private static ApiInfo getInfo() {
    return new ApiInfo(
        "BDSM RestFull Api Documentation",
        "This is simple documentation using swsagger and springFox",
        "1.0.0",
        "Terms of service not exist",
        new Contact("Mykolas Kiminius", "www.bdsm.eu", "info@bdsm.eu"),
        "BDSM licence",
        "No content",
        Collections.emptyList());
  }
}
