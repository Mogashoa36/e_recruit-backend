package recruit.recruit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recruit.recruit.dao.UserDao;
import recruit.recruit.entity.User;

import java.util.Optional;

@Service
public class UserService {



    @Autowired
    private UserDao userDao;

    public User registerUser(User user) {
        Optional<User> existingUser = userDao.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        return userDao.save(user);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
