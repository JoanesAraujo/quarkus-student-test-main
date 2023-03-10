package acc.br.resource;

import acc.br.model.Student;
import acc.br.repository.StudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
public class StudentResourceTest {

    @Inject
    StudentResource studentResource;

    @InjectMock
    StudentRepository studentRepository;

    Student student;


    @BeforeEach
    void setUp() {
        student = new Student();
        student.setNome("Joanes");
        student.setFilial("RE");
    }

    @Test
    void getAllStudent() {

        List<Student> studentList =  new ArrayList<>();
        studentList.add(new Student(1L,"Edjane","RE"));
        studentList.add(new Student(2L,"Victoria","RJ"));
        studentList.add(new Student(3L,"Marcos","BH"));

        Mockito.when(studentRepository.listAll()).thenReturn(studentList);

        Response response = studentResource.getAllStudent();

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        List<Student> studentList1 = (List<Student>) response.getEntity();

        assertEquals(studentList1.size(),3);
    }

    @Test
    void getCSStudentList() {
    }

    @Test
    void getStudentById() {
        Student student1 = new Student(5L,"Jesse", "POA");

        Mockito.when(studentRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(student1);

        Response response = studentResource.getStudentById(5L);

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        Student student2 = (Student) response.getEntity();

        assertEquals(student2.getNome(),"Jesse");
        assertEquals(student2.getFilial(),"POA");

    }

    @Test
    void getStudentByIdNull() {

        Mockito.when(studentRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(null);

        Response response = studentResource.getStudentById(5L);

        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());


    }
    @Test
    void createStudent() {
        Student s = new Student(1L,"Edjane", "RE");

        Mockito.doNothing().when(studentRepository).persist(s);
        Mockito.when(studentRepository.isPersistent(s)).thenReturn(true);

        Response response = studentResource.createStudent(s);

        assertNotNull(response);
        assertNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
        assertNotNull(response.getLocation());

    }

    @Test
    void updateStudent() {
        Student student1 = new Student(5L,"Jesse", "POA");
        Mockito.when(studentRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(student1);

        Student newUpdateStudent = new Student();
        newUpdateStudent.setNome("Jesse Menezes");
        Response response = studentResource.updateStudent(newUpdateStudent, 5L);

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        Student student2 = (Student) response.getEntity();

        assertEquals(student2.getNome(),"Jesse Menezes");
        assertEquals(student2.getFilial(), "POA");


    }


}
