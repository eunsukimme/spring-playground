package hello.hellospring.repository;

import hello.hellospring.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryUserRepositoryTest {

    MemoryUserRepository userRepository = new MemoryUserRepository();

    @AfterEach
    public void afterEach(){
        userRepository.clearStore();
    }

    @Test
    public void save(){
        User user = new User();
        user.setName("stranger");

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    public void findByName(){
        User user1 = new User();
        user1.setName("user1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("user2");
        userRepository.save(user2);

        User result = userRepository.findByName("user1").get();

        assertThat(user1).isEqualTo(result);
    }

    @Test
    public void findAll() {
        User user1 = new User();
        user1.setName("user1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("user2");
        userRepository.save(user2);

        List<User> result = userRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
