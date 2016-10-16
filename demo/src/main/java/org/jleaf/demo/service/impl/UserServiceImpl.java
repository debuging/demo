package org.jleaf.demo.service.impl;

import org.jleaf.demo.model.User;
import org.jleaf.demo.model.example.UserExample;
import org.jleaf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.treeleaf.common.bean.PageResult;
import org.treeleaf.common.bean.Pageable;
import org.treeleaf.db.DBModelOperator;
import org.treeleaf.db.DBOperator;

import java.util.List;

/**
 * @author leaf
 * @date 2016-10-13 16:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DBModelOperator operator;

    @Override
    public PageResult<User> findAll(Pageable pageable) {
        UserExample userExample = new UserExample();
        userExample.setPageable(pageable);
        List<User> list = operator.findByExample(userExample, User.class);
        long total = operator.countByExample(userExample, User.class);
        return new PageResult<>(pageable, list, total);
    }

    @Override
    public User findById(String id) {
        return operator.findById(id, User.class);
    }
}
