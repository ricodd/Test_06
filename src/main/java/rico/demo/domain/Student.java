package rico.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
@Entity
@Table(name = "spring_student")
@Data
@Log4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;


    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
