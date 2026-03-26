package TD.example.hello_student.service;

import TD.example.hello_student.entity.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<Student> studentsInMemory = new ArrayList<>();

    public void saveAll(List<Student> students) {
        this.studentsInMemory.addAll(students);
    }

    public List<Student> getAll() {
        return this.studentsInMemory;
    }
}
