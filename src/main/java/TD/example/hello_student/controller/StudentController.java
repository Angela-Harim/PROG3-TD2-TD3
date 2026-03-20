package TD.example.hello_student.controller;

import TD.example.hello_student.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>();


    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("X-Error-Message", "Missing Name")
                    .body("Erreur : Le nom est obligatoire !");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Welcome " + name);
    }


    @PostMapping("/students")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> newStudents) {
        this.students.addAll(newStudents);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(this.students);
    }


    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String acceptHeader) {

        // CAS 1 : Pas de header -> 400 Bad Request
        if (acceptHeader == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Vous devez préciser le header 'Accept'");
        }


        if (!acceptHeader.equals("application/json") && !acceptHeader.equals("text/plain")) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .build();
        }


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.students);
    }
}