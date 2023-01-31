package acc.br.repository;


import acc.br.model.Student;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
public class StudentRepositoryTest {

    @Inject
    StudentRepository studentRepository;


    @Test
    void listAll(){
        List<Student> studentList = studentRepository.listAll();

        assertFalse(studentList.isEmpty());
        assertEquals(studentList.size(), 10);
        assertEquals(studentList.get(0).getNome(), "Joanes");
    }

    @Test
    void findById(){
        Student student = studentRepository.findById(6L);

        assertNotNull(student);
        assertEquals(student.getId(), 6L);
        assertEquals(student.getNome(), "Mauro");
        assertEquals(student.getFilial(), "RJ");
    }

    @Test
    void getStudentListByFilial(){
        List<Student> studentList = studentRepository.getStudentListByFilial("RE");

        assertFalse(studentList.isEmpty());
        assertEquals(studentList.size(),2);
    }
}
