package rico.demo.service;

import rico.demo.domain.Pager;
import rico.demo.domain.Student;

import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
public interface StudentService {

    public List<Student> getStudentList();

    public void createStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(Long id);

    public Student getStudent(Long id);

    public Pager getStudentList(String pagerNO);
}
