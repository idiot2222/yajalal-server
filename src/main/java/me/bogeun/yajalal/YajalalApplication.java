package me.bogeun.yajalal;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@RequiredArgsConstructor
@SpringBootApplication
public class YajalalApplication implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(YajalalApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] beans = applicationContext.getBeanDefinitionNames();

        for (String bean : beans) {
            System.out.println(bean);
        }
    }
}
