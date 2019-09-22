package tst.ls_01.service;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import tst.ls_01.dao.DaoHelper;

public interface HomeWork01 {
    String getFileData();
    MessageSource getMs();
    String askQuestion(String qv);
    String readRes(Resource res);
    void startSurvey(String qv);
    void runme(AnnotationConfigApplicationContext context, DaoHelper helper);
}
