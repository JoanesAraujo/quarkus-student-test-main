package acc.br.resource;


import acc.br.model.Student;
import acc.br.repository.StudentRepository;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class StudentResource {

    @Inject
    StudentRepository studentRepository;

    @GET
    public Response getAllStudent() {
        List<Student> studentList = studentRepository.listAll();
        return Response.ok(studentList).build();
    }

    @GET
    @Path("filial/{filial}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCSStudentList(@PathParam("filial") String filial) {
        List<Student> studentList = studentRepository.list("filial", filial);
        return Response.ok(studentList).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id){
        Student student = studentRepository.findById(id);
        if(student != null)
            return Response.ok(student).build();
        else
            return Response.status(Response.Status.NO_CONTENT).build();
    }
    @POST
    @Transactional
    public Response createStudent(@RequestBody Student student){
        studentRepository.persist(student);
        if(studentRepository.isPersistent(student))
            return Response.created(URI.create("/student/" + student.getId())).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Path("update/{id}")
    public Response updateStudent(@RequestBody Student studentUpdate, @PathParam("id") Long id) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setNome(studentUpdate.getNome());
            return Response.ok(student).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response updateStudent(@PathParam("id") Long id) {
        boolean isDeleted = studentRepository.deleteById(id);
        if (isDeleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
