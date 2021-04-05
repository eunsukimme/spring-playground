package hello.hellospring.service;

import hello.hellospring.domain.User;
import hello.hellospring.repository.MemoryUserRepository;
import hello.hellospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     */
    public Long join(User user){
        validateDuplicateName(user);

        userRepository.save(user);
        return user.getId();
    }

    /**
     * 특정 id 의 유저 조회
     */
    public Optional<User> findOne(Long id){
        return userRepository.findById(id);
    }

    /**
     * 전체 유저 조회
     */
    public List<User> findUsers(){
        return userRepository.findAll();
    }


    /**
     * 이미 존재하는 이름의 회원이 있는지 검사
     */
    private void validateDuplicateName(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(_user -> { throw new IllegalStateException("이미 존재하는 회원입니다."); });
    }

}
