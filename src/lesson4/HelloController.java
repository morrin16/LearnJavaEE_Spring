package lesson4;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloController
{


@RequestMapping (value = "/login", method = RequestMethod.POST)
protected void login(Model model)
{
    model.addAttribute("bla-bla");
    
}


} //HelloController
