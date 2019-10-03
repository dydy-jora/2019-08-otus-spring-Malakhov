package tst.ls_04;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tst.ls_04.service.HomeWork01;
import tst.ls_04.service.HomeWork01Impl;

@SpringBootApplication
public class Lesson04Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Lesson04Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Lesson04Application.class);
		context.refresh();
		HomeWork01 hw = (HomeWork01Impl) context.getBean("homeWork01");
		hw.runme(context);
		System.exit(0);
	}
}
