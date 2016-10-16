package org.jleaf.demo.model.example;

import org.treeleaf.db.model.example.Criteria;
import org.treeleaf.db.model.example.Example;

/**
 * @author leaf
 * @date 2016-10-13 16:16
 */
public class UserExample extends Example<UserExample.UserCriteria> {

    @Override
    protected UserCriteria createCriteriaInternal() {
        return new UserCriteria();
    }

    public static class UserCriteria extends Criteria {

        public UserCriteria andUsernameLike(String v) {
            return addCriterion("a.username like", '%' + v + '%', "username");
        }
    }

}
