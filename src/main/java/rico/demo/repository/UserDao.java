package rico.demo.repository;

import rico.demo.domain.User;

import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
public interface UserDao {

    public List<User> selectUserList();

    public User selectUser(String loginName, String loginPassword);

    public void insertUser(User user);
}
