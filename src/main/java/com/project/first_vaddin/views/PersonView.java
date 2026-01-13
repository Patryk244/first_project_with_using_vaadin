package com.project.first_vaddin.views;

import com.project.first_vaddin.domain.Person;
import com.project.first_vaddin.service.PersonService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.*;

@Route("/persons")
@PageTitle("Mange people")
public class PersonView extends VerticalLayout {

    private final Logger logger = LoggerFactory.getLogger(PersonView.class);
    private PersonService personService;
    private Span span = new Span();


    private Grid<Person> grid = new Grid<>();
    private TextField taskName = new TextField();
    private TextField taskLastName = new TextField();
    private TextField email = new TextField();

    PersonView(PersonService personService) {
        this.personService = personService;
        logger.info("Constructor");

        addingPersonView();
        createdGrid();

        span.setVisible(false);
        span.addClassName("error_message");
    }

    private void createdGrid() {

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

    private void addingPersonView() {
        logger.info("Inside addingPersonView");
        Div container = new Div();
        container.addClassName("container");
        H1 welcome = new H1("Welcome to our app to manage tasks");
        welcome.addClassName("welcome");
        add(welcome);

        taskName.setLabel("FirstName");
        taskName.addClassName("firstName");

        taskLastName.setLabel("LastName");
        taskLastName.addClassName("taskLastName");

        email.setLabel("Email");
        email.addClassName("email");

        Button save = new Button("Save");
        save.addClassName("submit");

        container.add(taskName, taskLastName, email, save, span);
        add(container);

        save.addClickListener(e -> {
            preparedDataForPersonToDatabase(taskName.getValue().trim(), taskLastName.getValue().trim(), email.getValue().trim());
        });
    }

    private void preparedDataForPersonToDatabase(String firstname, String lastname, String emailValue) {
        logger.info("Inside preparedDataForPersonToDatabase");
        Person person = new Person();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setEmail(emailValue);

        try {
            logger.info("Try saving person");
            personService.savePerson(person);

            grid.getDataProvider().refreshAll();

            taskName.clear();
            taskLastName.clear();
            email.clear();


        } catch (ConstraintViolationException e) {
            span.setVisible(true);
            logger.warn("Not able to save person");
            span.setText("Error");
        } catch (Exception e) {
            logger.warn("Error saving person\n {}", e.getMessage());
        }
        logger.info("person has been saved successfully");
    }
}