package com.project.first_vaddin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
@StyleSheet("styles.css")
public class FirstVaddinApplication implements AppShellConfigurator {

    static void main(String[] args) {
        SpringApplication.run(FirstVaddinApplication.class, args);
    }
}
