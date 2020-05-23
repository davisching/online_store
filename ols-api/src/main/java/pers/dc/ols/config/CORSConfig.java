package pers.dc.ols.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfig {

    public CORSConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://172.17.49.24:8080");
        config.addAllowedOrigin("http://172.17.49.24");
//        config.addAllowedOrigin("http://106.15.194.214:8080");
//        config.addAllowedOrigin("http://106.15.194.214");
        config.addAllowedOrigin("http://34.80.79.144:8080");
        config.addAllowedOrigin("http://34.80.79.144");
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true); // 是否支持 cookie
        config.addAllowedMethod("*"); //允许所有请求
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}