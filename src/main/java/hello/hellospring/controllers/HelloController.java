package hello.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-spring")
    public String HelloSpring(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-spring";
    }

    @GetMapping("api-string")
    @ResponseBody
    public String ApiString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("api-json")
    @ResponseBody
    public Hello ApiJson(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
