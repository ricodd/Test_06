package rico.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rico.demo.domain.Pager;
import rico.demo.domain.Student;
import rico.demo.repository.StudentDao;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
@Service
@Transactional
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getStudentList() {
        return this.studentDao.findAll();
    }

    @Override
    public void createStudent(Student student) {
        this.studentDao.saveOne(student);
    }

    @Override
    public void updateStudent(Student student) {
        this.studentDao.updateOne(student);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("---delete a student.---");
        Student student = new Student();
        student.setId(id);
        this.studentDao.deleteOne(student);
    }

    @Override
    public Student getStudent(Long id) {
        return this.studentDao.findOne(id);
    }

    @Override
    public Pager getStudentList(String pagerNO) {
        log.info("pagerNO = " + pagerNO);
        int count = this.studentDao.count();
        log.info("count = " + count);
        Pager pager = new Pager(pagerNO,count);
        return this.studentDao.findByPager(pager);
    }
}
