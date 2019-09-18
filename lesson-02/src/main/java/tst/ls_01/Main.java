package tst.ls_01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import tst.ls_01.service.HomeWork01;
import tst.ls_01.service.HomeWork01Impl;

@ComponentScan
public class Main {

    static HomeWork01 hw;
    

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Main.class);
        context.refresh();

        hw = (HomeWork01Impl)context.getBean("homeWork01");
        hw.runme(context);
    }
}
