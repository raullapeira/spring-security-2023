package com.example.securingweb.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.securingweb.entity.Course;
import com.example.securingweb.entity.Student;
import com.example.securingweb.entity.StudentCoursing;
import com.example.securingweb.repository.CourseRepository;
import com.example.securingweb.repository.StudentRepository;

@Controller
@RequestMapping("/courses")
public class CourseController {
 
    @Autowired
    private CourseRepository courseRepository;
     
     
    @GetMapping("/new")
    public ModelAndView formAddCourse() {
        Course course = new Course();
        ModelAndView mav = new ModelAndView("courses/new");
        mav.addObject("course", course);
         
        return mav;    
    }  
    
    
     
    @PostMapping("/add")
    public ModelAndView addCourse(Course course)
    {
    	courseRepository.save(course);
    	return this.showAll();
    }

    @GetMapping("/showAll")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("courses/show_all");
         
        List<Course> courses = (List<Course>) courseRepository.findAll();
         
        mav.addObject("courses", courses);
         
        return mav;
    }  
    @GetMapping("/showAllStudents")
    public ModelAndView showAllStudents() {
        ModelAndView mav = new ModelAndView("courses/show_all_students");
         
        List<StudentCoursing> courses = (List<StudentCoursing>) courseRepository.getStudentsCoursing();
        
        Map<String, List<String>> studentsInCourse = courses.stream()
                .collect(Collectors.groupingBy(StudentCoursing::getCourseName,
                        Collectors.mapping(StudentCoursing::getStudentName, Collectors.toList())));

        mav.addObject("studentsInCourse", studentsInCourse);
         
        return mav;
    }  
    /*
    @GetMapping("/{id}")
    public ModelAndView editStudent(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("students/edit");
         
        List<Student> students = (List<Student>) studentRepository.findAll();
         
        mav.addObject("students", students);
         
        return mav;
    }  
    */
}