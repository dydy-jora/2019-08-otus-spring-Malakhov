package ru.otus.lesson02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import ru.otus.lesson02.service.HomeWork01;
import ru.otus.lesson02.service.HomeWork01Impl;

import java.util.Locale;

@ComponentScan
public class Main {

    static HomeWork01 hw;
    static String lang = "ru";
    static String country = "RU";

    public static void main(String[] args){
        Locale loc = new Locale(lang,country);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Main.class);
        context.refresh();
        hw = (HomeWork01Impl) context.getBean("homeWork01");
        String csv = hw.getFileData();
        Resource res = context.getResource(csv);
        String resStr = hw.readRes(res);
        String message = hw.getMs().getMessage("qv1", null, loc);//"Как ваша фамилия? ";
        String lName = hw.askQuestion(message);
        message = hw.getMs().getMessage("qv2", null, loc);//"Как ваше имя? ";
        String fName = hw.askQuestion(message);
        System.out.println(hw.getMs().getMessage("qv3", null, loc));//"Сейчас вам будут заданы несколько вопросов.");
        hw.startSurvey(resStr);
        hw.printResult(lName, fName);
    }


}
