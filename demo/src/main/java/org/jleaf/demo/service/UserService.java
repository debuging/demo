package org.jleaf.demo.service;

import org.jleaf.demo.model.User;
import org.treeleaf.common.bean.PageResult;
import org.treeleaf.common.bean.Pageable;

/**
 * Created by leaf on 2016/10/13 013.
 */
public interface UserService {

    PageResult<User> findAll(Pageable pageable);

    User findById(String id);
}
