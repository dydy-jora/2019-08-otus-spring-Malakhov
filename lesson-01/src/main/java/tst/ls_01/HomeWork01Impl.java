package tst.ls_01;

import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HomeWork01Impl implements HomeWork01 {

    public int result;
    public String fileData;

    HomeWork01Impl(String fileName){
        this.fileData = fileName;
    }
    public String getFileData(){
        return fileData;
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
}
