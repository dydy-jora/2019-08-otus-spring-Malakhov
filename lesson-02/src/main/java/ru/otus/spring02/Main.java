package ru.otus.spring02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.spring02.domain.Person;
import ru.otus.spring02.service.PersonService;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = null;
        context = new AnnotationConfigApplicationContext();
        context.register(Main.class);
        context.refresh();
        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
