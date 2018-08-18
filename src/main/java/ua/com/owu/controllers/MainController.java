package ua.com.owu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.controllers.components.UserValidator;
import ua.com.owu.controllers.models.User;
import ua.com.owu.controllers.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    @Qualifier("userService1")
    private UserService service;


    @Autowired
    private JavaMailSender sender;

    public void send(String email, MultipartFile file) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("sprnig boot mail sender");
        helper.setText("visit our  <a href='https://owu.com.ua'>site</a>", true);

        helper.addAttachment(file.getOriginalFilename(), file);
        sender.send(message);


    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Natalia");
        model.addAttribute("presented", true);

        List<User> list = service.findAll();
        model.addAttribute("users", list);
        return "home";
    }


    @Autowired
    private UserValidator userValidator;

    @PostMapping("/")
    public String save(
            User user,

            BindingResult bindingResult,
            Model model
    ) {


        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {


            String complexError = "";
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                String defaultMessage = error.getDefaultMessage();
                complexError += defaultMessage + " ";
            }


            model.addAttribute("presented", false);
            model.addAttribute("message", complexError);

            return "home";
        }

        service.save(user);
        return "redirect:/";
    }


    @PostMapping("/addAvatar")
    public String addAvatar(@RequestParam int choosenUser,
                            @RequestParam MultipartFile avatar,
                            @RequestParam String email
    ) throws IOException, MessagingException {
        send(email, avatar);
        System.out.println(choosenUser);


        String path = System.getProperty("user.dir") +
                File.separator
                + "src"
                + File.separator
                + "main"
                + File.separator
                + "resources"
                + File.separator
                + "static"
                + File.separator;


        avatar.transferTo(new File(path + avatar.getOriginalFilename()));
        User user = service.findById(choosenUser);
        user.setAvatar(avatar.getOriginalFilename());
        service.save(user);

        System.out.println(user);

        return "redirect:/";
    }


}
