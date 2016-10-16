package org.jleaf.demo.controller;

import org.jleaf.demo.model.User;
import org.jleaf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.treeleaf.common.bean.PageResult;
import org.treeleaf.common.bean.Pageable;
import org.treeleaf.web.Html;
import org.treeleaf.web.Json;
import org.treeleaf.web.Redirect;

/**
 * @author leaf
 * @date 2016-10-13 15:29
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("info")
    Json info(String id) {
        User user = userService.findById(id);
        return new Json(user);
    }

    @RequestMapping("index")
    Html index() {
        PageResult<User> pageResult = userService.findAll(new Pageable(1, 10));
        return new Html(pageResult);
    }

    @RequestMapping("redirect")
    Redirect redirect() {
        return new Redirect("/user/index");
    }
}
