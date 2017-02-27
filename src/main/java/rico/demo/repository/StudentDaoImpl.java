package rico.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rico.demo.domain.Pager;
import rico.demo.domain.Student;

import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
@Repository
public class StudentDaoImpl extends BaseDao implements  StudentDao {

    @Override
    public List<Student> findAll() {
        return super.getSession().createQuery("from Student").list();
    }

    @Override
    public Student findOne(Long id) {

        return (Student) super.getSession().createQuery("from Student as s where s.id = ?").setParameter(0, id).uniqueResult();
    }

    @Override
    public void saveOne(Student student) {
        super.getSession().save(student);
    }

    @Override
    public void updateOne(Student student) {
        super.getSession().update(student);
    }

    @Override
    public void deleteOne(Student student) {
        super.getSession().delete(student);
    }

    @Override
    public Pager findByPager(Pager pager) {

        List<Student> list = super.getSession().createQuery("from Student")
                .setFirstResult((pager.getPagerNO() - 1) * pager.getPagerSize())
                .setMaxResults(pager.getPagerSize()).list();
        pager.setList(list);
        return pager;
    }

    @Override
    public int count() {
        int count = ((Number)super.getSession().createQuery("select count(*) from Student").uniqueResult()).intValue();
        return count;
    }
}
