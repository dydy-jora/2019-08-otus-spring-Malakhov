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
import tst.ls_01.dao.DaoHelper;

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
    @Autowired
    private DaoHelper helper;

    public HomeWork01Impl(MessageSource msb){
        this.ms = msb;
    }

    /* получаем имя файла конфигурации */
    @Override
    public String getFileData(){
        return this.fileData;
    }

    public MessageSource getMs(){
        return this.ms;
    }

    /* спрашиваем имя или фамилию */
    @Override
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

    /* читаем ресурс с вопросами */
    @Override
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

    /* проводим опрос */
    @Override
    public void startSurvey(String qv) {
        String[] narr = qv.split("\n");
        for (String s:narr) {
            String[] arr = s.split(";");
            String vopros = arr[0].trim();
            String otvet = arr[1].trim();
            String[] os = otvet.split(":");
            String ra = arr[2].trim();
            String r = arr[3].trim();
            helper.showQuestion(vopros);
            helper.showAnswers(os);
            String ret = helper.readAnswer();
            
            if (ret.equals(ra.trim()))
                this.result += Integer.parseInt(r);
        }
    }
    /* запускаем опрос */
    @Override
	public void runme(AnnotationConfigApplicationContext context) {
        this.helper = helper;
        String csv = this.getFileData();
        Resource res = context.getResource(csv);
        String resStr = this.readRes(res);
        String message = msg1;
        String lName = this.askQuestion(message);
        message = msg2;
        String fName = this.askQuestion(message);
        System.out.println(msg3);
        this.startSurvey(resStr);
        helper.printResult(lName, fName, result);
	} 
}
