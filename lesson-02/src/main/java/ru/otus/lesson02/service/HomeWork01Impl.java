package ru.otus.lesson02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import ru.otus.lesson02.service.HomeWork01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HomeWork01Impl implements HomeWork01 {

    public int result;
    @Value("${fileName}")
    public String fileData;
    @Autowired
    private MessageSource ms;

    public HomeWork01Impl(){ }

    /* получаем имя файла конфигурации */
    public String getFileData(){
        return fileData;
    }

    public MessageSource getMs(){
        return this.ms;
    }

    /* спрашиваем имя или фамилию */
    public String askQuestion( String qv) {
        System.out.println(qv);
        String ret = "";
        try {
            ret = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    /* выводим результат опроса */
    public void printResult(String fName, String lName) {
        System.out.println(String.format("Уважаемый, %s ",fName)+
                String.format("%s, по результатам опроса вы набрали ",lName)+
                String.format("%d баллов.", result));
    }
    /* читаем ресурс с вопросами */
    public String readRes(Resource res) {
        if (res == null) return null;
        String str = "";
        try {
            str = new Scanner(res.getInputStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    /* проводим опрос */
    public void startSurvey(String qv) {
        String[] narr = qv.split("\n");
        for (String s:narr) {
            String[] arr = s.split(";");
            String vopros = arr[0].trim();
            String otvet = arr[1].trim();
            String[] os = otvet.split(":");
            String ra = arr[2].trim();
            String r = arr[3].trim();
            System.out.println("Вопрос: " + vopros + "\nВарианты ответов: ");
            for (String ss : os)
                System.out.println(ss);
            String ret = "";
            try {
                ret = new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (ret.equals(ra.trim()))
                result += Integer.parseInt(r);
        }
    }

	public void runme(ApplicationContext ctx) {
        String csv = this.getFileData();
        Resource res = ctx.getResource(csv);
        String resStr = this.readRes(res);
        String message = "Как ваша фамилия? ";
        String lName = this.askQuestion(message);
        message = "Как ваше имя? ";
        String fName = this.askQuestion(message);
        System.out.println("Сейчас вам будут заданы несколько вопросов.");
        this.startSurvey(resStr);
        this.printResult(lName, fName);
	}
}
