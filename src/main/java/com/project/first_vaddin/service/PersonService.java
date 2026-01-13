package com.project.first_vaddin.service;

import com.project.first_vaddin.domain.Person;
import com.project.first_vaddin.repository.PersonRepository;
import com.project.first_vaddin.repository.PersonRepositoryCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonRepositoryCrud personRepositoryCrud;

    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Person savePerson(Person person) {
        return personRepositoryCrud.save(person);
    }
}
