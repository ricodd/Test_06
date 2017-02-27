package rico.demo.service;

import rico.demo.domain.User;

import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
public interface UserService {

    public User getUser(String loginName, String loginPassword);

    public void doNewUser(String loginName, String loginPassword);

    public List<User> getUserList();
}
