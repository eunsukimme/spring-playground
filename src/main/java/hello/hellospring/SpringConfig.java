package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
//        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
//        return new MemoryUserRepository();
//        return new JdbcUserRepository(dataSource);
//        return new JdbcTemplateUserRepository(dataSource);
        return new JpaUserRepository(em);
    }
}
