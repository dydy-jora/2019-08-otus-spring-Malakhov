import org.springframework.core.io.Resource;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tst.ls_01.HomeWork01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HomeWork01ImplTest {

    public int result;
    public String fileData;

    HomeWork01ImplTest(String fileName){
        this.fileData = fileName;
    }
    @BeforeTest
    void init() {
        this.result = 123;
        this.fileData = "/data.csv";
    }
    @Test()
    String getFileData(){
        return fileData;
    }
    /* спрашиваем имя или фамилию */
    @Parameters("Как вас зовут?")
    @Test()
    String askQuestion(@Optional("Как вас зовут?") String qv) {
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
    @Parameters({"Малахов","Александр"})
    @Test
    void printResult(@Optional("Александр") String fName, @Optional("Малахов") String lName) {
        System.out.println(String.format("Уважаемый, %s ",fName)+
                String.format("%s, по результатам опроса вы набрали ",lName)+
                String.format("%d баллов.", result));
    }
    /* читаем ресурс с вопросами */
    @Test()
    String readRes(Resource res) {
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
    @Parameters({"вопрос 1;ответ A:ответ B:ответ C;ответ B;4\nвопрос 2;ответ 2;ответ 2;1\nвопрос 3;ответ 3;ответ 3;1\nвопрос 4;ответ 4:ответ 6:ответ 8:ответ 10:ответ 12;ответ 12;9\nвопрос 5;ответ 5;ответ 5;1"})
    @Test
    void startSurvey(@Optional("вопрос 1;ответ A:ответ B:ответ C;ответ B;4") String qv) {
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
//            try {
                ret = "OK";//new BufferedReader(new InputStreamReader(System.in)).readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            if (ret.equals(ra.trim()))
                result += Integer.parseInt(r);
        }
    }
}
