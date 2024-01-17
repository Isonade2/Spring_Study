package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello");
                return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String data, Model model){
        model.addAttribute("name",data);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        private String name2;
        public String getName() {
            return name;
        }
        public String getName2() {
            return name2;
        }
        public void setName(String name) {
            this.name = name;
            this.name2 = name;
        }
    }


}
