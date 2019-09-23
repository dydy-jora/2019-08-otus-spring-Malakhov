package tst.ls_04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tst.ls_04.dao.DaoHelper;
import tst.ls_04.service.HomeWork01Impl;

@SpringBootApplication
public class Lesson04Application {

	private static HomeWork01Impl hw;
	private static DaoHelper helper;

	public static void main(String[] args) {
		SpringApplication.run(Lesson04Application.class, args);
		runApp();
	}

	public static void runApp() {
		System.out.println("App is runed.");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Lesson04Application.class);
        context.refresh();

        hw = (HomeWork01Impl)context.getBean("homeWork01");
        helper = (DaoHelper) context.getBean("helper");
        hw.runme(context, helper); 
	}
}
