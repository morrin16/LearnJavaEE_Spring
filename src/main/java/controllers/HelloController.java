package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloController
{

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected void login(Model model)
    {
        model.addAttribute("bla-bla");

    }

} //HelloController
