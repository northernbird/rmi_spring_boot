package edu.self;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@RestController // Webアプリのリクエストを受け付けるクラスであることの指定
//@EnableAutoConfiguration // 様々な設定を自動的に有効化
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
@SpringBootApplication
public class App {
//    @RequestMapping("/") // URLのパスの指定
//    public String index() {
//        return "Hello Spring Boot!";
//    }

    public static void main(String[] args) {
        // Spring Bootによるアプリケーションを起動するための処理です。
        SpringApplication.run(App.class, args);
    }
}