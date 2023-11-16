package com.example.securingweb.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.securingweb.entity.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student,Long>{

}
