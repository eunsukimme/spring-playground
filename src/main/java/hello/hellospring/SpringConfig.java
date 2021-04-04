package hello.hellospring;

import hello.hellospring.repository.MemoryUserRepository;
import hello.hellospring.repository.UserRepository;
import hello.hellospring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new MemoryUserRepository();
    }
}
