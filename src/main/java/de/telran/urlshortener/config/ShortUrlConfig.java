package de.telran.urlshortener.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "short-url")
@Getter
@Setter
public class ShortUrlConfig {
    private String allowedCharacters;
    private int keyLength;
}
