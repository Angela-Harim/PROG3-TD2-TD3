package TD.example.hello_student.controller;

import TD.example.hello_student.entity.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {


    private final List<Student> students = new ArrayList<>();


    @GetMapping("/welcome")
    public String welcome(@RequestParam(name = "name") String name) {
        return "Welcome " + name;
    }


    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> newStudents) {

        this.students.addAll(newStudents);


        return students.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }


    @GetMapping("/students")
    public String getStudents(@RequestHeader(name = "Accept") String acceptHeader) {
        if ("text/plain".equals(acceptHeader)) {
            // Retourne les noms formatés en texte
            return students.stream()
                    .map(s -> s.getFirstName() + " " + s.getLastName())
                    .collect(Collectors.joining("\n"));
        } else {
            return "Format non supporté";
        }
    }
}
