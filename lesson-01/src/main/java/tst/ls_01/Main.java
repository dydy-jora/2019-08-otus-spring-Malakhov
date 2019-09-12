package tst.ls_01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class Main {

    static HomeWork01 hw;

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        hw = (HomeWork01Impl)ctx.getBean("homeWork01");
        hw.runme(ctx);
    }
}
