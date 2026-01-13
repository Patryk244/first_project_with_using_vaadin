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
import org.springframework.dao.DataIntegrityViolationException;

@Route("/persons")
@PageTitle("Manage people")
public class PersonView extends VerticalLayout {

    private final Logger logger = LoggerFactory.getLogger(PersonView.class);
    private PersonService personService;
    private Span errorSpan = new Span();
    private Grid<Person> grid = new Grid<>();

    private TextField taskName = new TextField("First Name");
    private TextField taskLastName = new TextField("Last Name");
    private TextField email = new TextField("Email");

    PersonView(PersonService personService) {
        this.personService = personService;
        setPadding(false);
        setSpacing(false);
        setSizeFull();
        addClassName("person-view-container");

        initHeader();
        initForm();
        initGrid();

    }

    private void initHeader() {
        Div header = new Div();
        header.addClassName("view-header");
        header.add(new H1("Manage People"));
        add(header);
    }

    private void initForm() {

        Div container = new Div();
        container.addClassName("container");
        errorSpan.addClassName("error_message");
        errorSpan.setVisible(false);
        taskName.setWidthFull();
        taskLastName.setWidthFull();
        email.setWidthFull();

        Button save = new Button("Add Person");
        save.addClassName("submit");
        save.setWidthFull();

        container.add(taskName, taskLastName, email, errorSpan,  save);
        add(container);

        save.addClickListener(e -> preparedDataForPersonToDatabase());
    }

    private void initGrid() {
        grid.addColumn(Person::getFirstName).setHeader("First Name").setAutoWidth(true);
        grid.addColumn(Person::getLastName).setHeader("Last Name").setAutoWidth(true);
        grid.addColumn(Person::getEmail).setHeader("Email").setAutoWidth(true);

        grid.setItemsPageable(pageable -> personService
                .getAllPersons(pageable)
                .getContent()
        );

        grid.setSizeFull();
        add(grid);
        setFlexGrow(1, grid);
    }

    private void preparedDataForPersonToDatabase() {
        Person person = new Person();
        person.setFirstName(taskName.getValue().trim());
        person.setLastName(taskLastName.getValue().trim());
        person.setEmail(email.getValue().trim());

        try {
            personService.savePerson(person);
            grid.getDataProvider().refreshAll();
            taskName.clear();
            taskLastName.clear();
            email.clear();
        } catch (DataIntegrityViolationException e) {
            errorSpan.setText("This email already exists!");
            errorSpan.setVisible(true);
            //logger.error("Save error");
        } catch (Exception e) {
            errorSpan.setText("Invalid catch");
            errorSpan.setVisible(true);
            logger.info(e.getLocalizedMessage());
            //logger.error("Error saving data: {}", e.getMessage());
        }
    }
}