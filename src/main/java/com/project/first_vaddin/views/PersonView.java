package com.project.first_vaddin.views;

import com.project.first_vaddin.domain.Person;
import com.project.first_vaddin.service.PersonService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/persons")
@PageTitle("Mange people")
public class PersonView extends VerticalLayout {
     PersonView(PersonService personService) {
       var grid = new Grid<Person>();
       grid.addColumn(Person::getFirstName).setHeader("First Name");
       grid.addColumn(Person::getLastName).setHeader("Last Name");
       grid.addColumn(Person::getEmail).setHeader("Email");

       grid.setItemsPageable(pageable -> personService
               .getAllPersons(pageable)
               .getContent()
       );
       setSizeFull();
       grid.setSizeFull();
       add(grid);
    }
}
