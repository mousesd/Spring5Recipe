package net.homenet.repository;

import net.homenet.domain.User;

public interface UserDao {
    User registerUser(User user);
}
