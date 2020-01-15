package pers.dc.ols;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = "pers.dc.ols.mapper")
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"org.n3r.idworker","pers.dc.ols"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
