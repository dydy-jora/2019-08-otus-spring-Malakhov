package tst.ls_01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class Main {

    static HomeWork01 hw;

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        hw = (HomeWork01Impl) ctx.getBean("homeWork01");
        String csv = hw.getFileData();
        Resource res = ctx.getResource(csv);
        String resStr = hw.readRes(res);
        String message = "Как ваша фамилия? ";
        String lName = hw.askQuestion(message);
        message = "Как ваше имя? ";
        String fName = hw.askQuestion(message);
        System.out.println("Сейчас вам будут заданы несколько вопросов.");
        hw.startSurvey(resStr);
        hw.printResult(lName, fName);
		System.out.println("FINISH!!!");
    }
}
