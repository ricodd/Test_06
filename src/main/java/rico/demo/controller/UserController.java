package rico.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import rico.demo.domain.User;
import rico.demo.service.UserService;

import javax.servlet.http.HttpSession;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/index.html")
    public String toIndexPage( @SessionAttribute(name = "loginName", required = false)String loginName,
                               ModelMap modelMap) {
        modelMap.put("loginName", loginName);

        List<User> userList = this.userService.getUserList();
        if(userList != null) {
            modelMap.put("userList", userList);
        }
        return "index";
    }

    @RequestMapping("/register.html")
    public String toRegisterPage() {
        return "register";
    }

    @RequestMapping("/login.html")
    public String toLoginPage() {
        return "login";
    }



    @RequestMapping(value = "/doRegister.html", method = RequestMethod.POST)
    public String doRegister(String loginName, String loginPassword, String repeatLoginPassword, ModelMap modelMap,
                             HttpSession session) {
        log.info("--controller called register--");
        if(!loginPassword.equals(repeatLoginPassword)) {
            modelMap.put("errorMessage", "注册失败，登录密码和重复登录密码不一致。");
            return "failed";
        }

        this.userService.doNewUser(loginName, loginPassword);
        session.setAttribute("loginName", loginName);
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/doLogin.html", method = RequestMethod.POST)
    public String doLogin(String loginName, String loginPassword, ModelMap modelMap, HttpSession session) {
        log.info("--controller called login--");
        User user = this.userService.getUser(loginName, loginPassword);
        if(user == null) {
            modelMap.put("errorMessage", "登录失败，登录名或登录密码错误。");
            return "failed";
        }
        session.setAttribute("loginName", loginName);
        return "redirect:/index.html";
       /* return "index";*/
    }

}
