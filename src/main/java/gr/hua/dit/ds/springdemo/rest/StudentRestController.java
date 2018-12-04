package gr.hua.dit.ds.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gr.hua.dit.ds.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	@GetMapping("/students")
	public List<Student>  getStudnets() {
		List<Student> theStudents = new ArrayList<>();
		theStudents.add(new Student("Nick","Cave"));
		theStudents.add(new Student("John","Travolta"));
		theStudents.add(new Student("Jason","Cannon"));
		
		return theStudents;
		
	}
	
}
