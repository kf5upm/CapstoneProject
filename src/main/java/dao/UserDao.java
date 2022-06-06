package dao;

import entities.User;

public class UserDao extends AbstractDAO<User> {
    
    public UserDao() {
        super(User.class);
    }
}
