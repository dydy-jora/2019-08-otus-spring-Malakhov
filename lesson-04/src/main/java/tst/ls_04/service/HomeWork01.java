package tst.ls_04.service;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import tst.ls_04.dao.DaoHelper;

public interface HomeWork01 {
    String getFileData();
    String askQuestion(String qv);
    String readRes(Resource res);
    void startSurvey(String qv);
    void runme(AnnotationConfigApplicationContext context);
}
