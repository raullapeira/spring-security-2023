package com.example.securingweb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.securingweb.entity.Course;
import com.example.securingweb.entity.Student;
import com.example.securingweb.repository.CourseRepository;
import com.example.securingweb.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {
 
    @Autowired
    private StudentRepository studentRepository;
     
    @Autowired
    private CourseRepository courseRepository;
     
    @GetMapping("/new")
    public ModelAndView formAddStudent() {
        Student student = new Student();
        ModelAndView mav = new ModelAndView("students/new");
        mav.addObject("student", student);
         
        return mav;    
    }  
    
    
     
    @PostMapping("/add")
    public ModelAndView addStudent(Student student)
    {
    	studentRepository.save(student);
    	return this.showAll();
    }
    
    @PostMapping("/addCourses")
    public ModelAndView addCourses(
    		@RequestParam(name="courses_add[]", required=false)  Long[] coursesToAdd, 
    		@RequestParam(name="courses_remove[]",required=false)  Long[] coursesToRemove,
    		@RequestParam("student_id") Long studentId)
    {
    	
    	Optional<Student> findStudent=studentRepository.findById(studentId);
    	if(findStudent.isPresent()) {
    		Student student=findStudent.get();
    		if(coursesToAdd!=null)
    		for(Long courseId:coursesToAdd) {
    	    	Optional<Course> findCourse=courseRepository.findById(courseId);
    	    	if(findCourse.isPresent()) {
    	    		student.getCourses().add(findCourse.get());
    	    	}
    		}
    		if(coursesToRemove!=null)
    		for(Long courseId:coursesToRemove) {
    	    	Optional<Course> findCourse=courseRepository.findById(courseId);
    	    	if(findCourse.isPresent()) {
    	    		student.getCourses().remove(findCourse.get());
    	    	}
    		}
    		studentRepository.save(student);
    	}
    	return this.editStudent(studentId);
    }

    @GetMapping("/showAll")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("students/show_all");
         
        List<Student> students = (List<Student>) studentRepository.findAll();
         
        mav.addObject("students", students);
         
        return mav;
    }  
    @GetMapping("/{id}")
    public ModelAndView editStudent(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("students/edit");
         
        Student student = studentRepository.findById(id).get();
        mav.addObject("student", student);
        mav.addObject("notJoniedCourses", courseRepository.getNotJoniedCourses(id));
        
        return mav;
    }  
}