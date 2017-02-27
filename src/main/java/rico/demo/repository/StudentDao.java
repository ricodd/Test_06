package rico.demo.repository;

import rico.demo.domain.Pager;
import rico.demo.domain.Student;

import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
public interface StudentDao {

    public List<Student> findAll();

    public Student findOne(Long id);

    public void saveOne(Student student);

    public void updateOne(Student student);

    public void deleteOne(Student student);

    public Pager findByPager(Pager pager);

    public int count();


}
