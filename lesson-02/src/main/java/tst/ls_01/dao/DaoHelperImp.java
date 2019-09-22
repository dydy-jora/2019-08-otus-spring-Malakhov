package tst.ls_01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DaoHelperImp implements DaoHelper {
//    public int result;
    @Value("${fileName}")
    public String fileData;
    @Value("${msg1}")
    public String msg1;
    @Value("${msg2}")
    public String msg2;
    @Value("${msg3}")
    public String msg3;
    @Value("${msg4}")
    private char[] msg4;
    @Value("${msg5}")
    private char[] msg5;

    @Autowired
    private MessageSource ms;

    public DaoHelperImp(MessageSource msg){
        this.ms = msg;
    }

    /* задаем вопросы */
    @Override
    public void showQuestion(String vopros) {
        System.out.println(vopros);
    }

    /* выводим варианты ответов */
    @Override
    public void showAnswers(String[] vo) {
        System.out.println(msg4);
        for (String ss : vo)
            System.out.println(ss);
    }

    /* получаем ответы на вопросы */
    @Override
    public String readAnswer() {
        String ret = "";
        try {
            ret = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

     /* выводим результат опроса */
    @Override
    public void printResult(String fName, String lName, int result) {
        System.out.println(String.format("Уважаемый, %s ",fName)+
                String.format("%s, по результатам опроса вы набрали ",lName)+
                String.format("%d баллов.", result));
    }
}
