package net.homenet.service;

import net.homenet.domain.User;
import net.homenet.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    @Retryable(backoff = @Backoff(delay = 1000, maxDelay = 10000, multiplier = 2))
    public User registerUser(User user) {
        return userDao.registerUser(user);
    }
}
