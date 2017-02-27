package rico.demo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rico.demo.domain.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
@Repository
public class UserDaoImpl extends BaseDao implements  UserDao{


    @Override
    public List<User> selectUserList() {
        return super.getSession().createQuery("from User").list();
    }

    @Override
    public User selectUser(String loginName, String loginPassword) {
        return (User) super.getSession()
                .createQuery("from User as u where u.loginName = ? and u.loginPassword = ?")
                .setParameter(0, loginName).setParameter(1, loginPassword)
                .uniqueResult();
    }

    @Override
    public void insertUser(User user) {
        super.getSession().save(user);
    }
}
