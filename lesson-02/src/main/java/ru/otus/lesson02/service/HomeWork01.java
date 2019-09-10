package ru.otus.lesson02.service;

import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;

public interface HomeWork01 {
    String askQuestion(String qv);
    void printResult(String fName, String lName);
    String readRes(Resource res);
    void startSurvey(String qv);
    String getFileData();
    MessageSource getMs();
}
