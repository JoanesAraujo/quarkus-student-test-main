package acc.br.repository;

import acc.br.model.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
    public List<Student> getStudentListByFilial(String filial){
        return list("Select s from Student s where s.filial=?1", filial);

    }
}
