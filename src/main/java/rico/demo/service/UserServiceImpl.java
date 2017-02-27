package rico.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rico.demo.domain.User;
import rico.demo.repository.UserDao;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String loginName, String loginPassword) {
        return this.userDao.selectUser(loginName, loginPassword);
    }

    @Override
    public void doNewUser(String loginName, String loginPassword) {
        User user = new User();
        user.setLoginName(loginName);
        user.setLoginPassword(loginPassword);


        this.userDao.insertUser(user);
    }

    @Override
    public List<User> getUserList() {
        return this.userDao.selectUserList();
    }
}
