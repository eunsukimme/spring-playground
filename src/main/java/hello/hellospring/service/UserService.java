package hello.hellospring.service;

import hello.hellospring.domain.User;
import hello.hellospring.repository.MemoryUserRepository;
import hello.hellospring.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    UserService(MemoryUserRepository userRepository){
        this.userRepository = new MemoryUserRepository();
    }

    /**
     * 회원가입
     * @param user
     * @return user의 id
     */
    public Long join(User user){
        validateDuplicateName(user);

        userRepository.save(user);
        return user.getId();
    }

    /**
     * 특정 id 의 유저 조회
     * @param id
     * @return user
     */
    public Optional<User> findOne(Long id){
        return userRepository.findById(id);
    }

    /**
     * 전체 유저 조회
     * @return user list
     */
    public List<User> findUsers(){
        return userRepository.findAll();
    }


    /**
     * 이미 존재하는 이름의 회원이 있는지 검사
     * @param user
     */
    private void validateDuplicateName(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(_user -> { throw new IllegalStateException("이미 존재하는 회원입니다."); });
    }

}
