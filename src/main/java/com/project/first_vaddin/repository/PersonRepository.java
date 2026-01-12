package com.project.first_vaddin.repository;

import com.project.first_vaddin.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
   // List<Person> findAll();
}
