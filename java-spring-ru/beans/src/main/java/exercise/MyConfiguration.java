package exercise;


import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@Configuration
public class MyConfiguration {

    @Bean
    @RequestScope
    public Daytime getDaytime() {
        var currentHour = LocalDateTime.now().getHour();
        if (currentHour >= 6 && currentHour < 22) {
            return new Day();
        } else {
            return new Night();
        }
    }
}
