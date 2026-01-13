package com.project.first_vaddin.repository;

import com.project.first_vaddin.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryCrud extends CrudRepository<Person,Long> {
}
