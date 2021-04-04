package hello.hellospring.service;

import hello.hellospring.domain.User;
import hello.hellospring.repository.MemoryUserRepository;
import hello.hellospring.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        this.userRepository = new MemoryUserRepository();
        this.userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach(){
        userRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        User user1 = new User();
        user1.setName("user1");

        // when
        Long savedId = userService.join(user1);

        // then
        User foundUser = userService.findOne(savedId).get();
        assertThat(foundUser.getName()).isEqualTo(user1.getName());
    }

    @Test
    void 중복_회원_예외(){
        // given
        User user1 = new User();
        user1.setName("user1");
        userService.join(user1);

        User user2 = new User();
        user2.setName("user1");

        // when
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> userService.join(user2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            userService.join(user2);
//            fail("예외가 발생해야 합니다.");
//        }
//        catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @Test
    void findOne() {
    }

    @Test
    void findUsers() {
    }
}