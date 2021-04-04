package hello.hellospring.controllers;

import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
}
