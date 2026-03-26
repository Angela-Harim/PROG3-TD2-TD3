package TD.example.hello_student.controller;

import TD.example.hello_student.entity.Student;
import TD.example.hello_student.exception.BadRequestException;
import TD.example.hello_student.service.StudentService;
import TD.example.hello_student.validator.StudentValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final StudentValidator studentValidator;

    // Injection des dépendances
    public StudentController(StudentService studentService, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> newStudents) {
        try {
            // 1. Déléguer la validation
            for (Student s : newStudents) {
                studentValidator.validate(s);
            }
            // 2. Déléguer la sauvegarde
            studentService.saveAll(newStudents);

            return ResponseEntity.ok(studentService.getAll());

        } catch (BadRequestException e) {
            // 3. Gérer l'exception spécifique
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}