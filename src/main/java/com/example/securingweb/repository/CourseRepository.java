package com.example.securingweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.securingweb.entity.Course;
import com.example.securingweb.entity.Student;
import com.example.securingweb.entity.StudentCoursing;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    // Métodos personalizados del repositorio, si es necesario
    @Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT sc.id FROM Student s JOIN s.courses sc WHERE s.id = :id)")
	public List<Course> getNotJoniedCourses(@Param("id") Long id);

    @Query("SELECT NEW com.example.securingweb.entity.StudentCoursing(s.id, s.name,s.email, c.id, c.name) FROM Student s JOIN s.courses c")
    //@Query("SELECT new StudentCoursing(s.id,s.name,s.email,c.id,c.name) FROM Student s LEFT JOIN Course c")
	public List<StudentCoursing> getStudentsCoursing();

}
