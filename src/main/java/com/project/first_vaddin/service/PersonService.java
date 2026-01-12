package com.project.first_vaddin.service;

import com.project.first_vaddin.domain.Person;
import com.project.first_vaddin.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Iterable<Person> getAllPersons() {
        //return personRepository.findAll(Pageable able);
        return null;
    }
}
