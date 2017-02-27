package rico.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Rico.Chen on 2017/2/18.
 */
@Entity
@Table(name = "spring_user")
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String loginName;
    private String loginPassword;

}
