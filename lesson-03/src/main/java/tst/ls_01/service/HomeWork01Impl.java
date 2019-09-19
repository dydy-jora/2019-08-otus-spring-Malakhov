package tst.ls_01.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

public class HomeWork01Impl implements HomeWork01 {

    public int result;
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

    public HomeWork01Impl(){ }

    /* получаем имя файла конфигурации */
    public String getFileData(){
        return this.fileData;
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
        Scanner scanner = null;
		try {
			scanner = new Scanner(res.getInputStream(), "UTF-8");
	        str = scanner.useDelimiter("\\A").next();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
        return str;
    }
    private void show_Question(String vopros) {
//    	System.out.println(msg5 + vopros);
    	System.out.println(vopros);
    }
    private void showAnswers(String[] os) {
    	System.out.println(msg4);
        for (String ss : os)
            System.out.println(ss);
    }
    private String readAnswer() {
    	String ret = "";
        try {
            ret = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
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
            show_Question(vopros);
            showAnswers(os);
            String ret = readAnswer();
            
            if (ret.equals(ra.trim()))
                this.result += Integer.parseInt(r);
        }
    }

	public void runme(AnnotationConfigApplicationContext context) {
		String csv = this.getFileData();
        Resource res = context.getResource(csv);
        String resStr = this.readRes(res);
        String message = msg1;
        String lName = this.askQuestion(message);
        message = msg2;
        String fName = this.askQuestion(message);
        System.out.println(msg3);
        this.startSurvey(resStr);
        this.printResult(lName, fName);
	} 
}
