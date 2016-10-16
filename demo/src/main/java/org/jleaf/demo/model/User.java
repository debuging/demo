package org.jleaf.demo.model;

import org.treeleaf.db.meta.annotation.Column;
import org.treeleaf.db.meta.annotation.Table;
import org.treeleaf.db.model.Model;

/**
 * @author leaf
 * @date 2016-10-13 15:32
 */
@Table("t_user")
public class User extends Model {

    @Column(primaryKey = true)
    private String id;

    @Column
    private String username;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }
}
