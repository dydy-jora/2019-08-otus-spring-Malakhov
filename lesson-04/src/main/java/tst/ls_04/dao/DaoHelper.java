package tst.ls_04.dao;

public interface DaoHelper {
    /* задаем вопросы */
    void showQuestion(String vopros);

    /* выводим варианты ответов */
    void showAnswers(String[] vo);

    /* получаем ответы на вопросы */
    String readAnswer();

    /* выводим результат опроса */
    void printResult(String fName, String lName, int result);

}
