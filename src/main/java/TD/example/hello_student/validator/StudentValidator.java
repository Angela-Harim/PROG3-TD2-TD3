package TD.example.hello_student.validator;

import TD.example.hello_student.entity.Student;
import TD.example.hello_student.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class StudentValidator {
    public void validate(Student student) {
        if (student.getReference() == null || student.getReference().isBlank()) {
            throw new BadRequestException("NewStudent.reference cannot be null");
        }
        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            throw new BadRequestException("NewStudent.firstName cannot be null");
        }
        if (student.getLastName() == null || student.getLastName().isBlank()) {
            throw new BadRequestException("NewStudent.lastName cannot be null");
        }
    }
}
