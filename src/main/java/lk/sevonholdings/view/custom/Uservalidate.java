package lk.sevonholdings.view.custom;

import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.Time;

public interface Uservalidate {
    boolean isUserNotAction(AnchorPane pane);
    Date getSQLDateNow();
    Time getSQLTimeNow();
    void SendSMS(int phonenumber, String Massege);

    String cssStringFocusedTxtFldColor();
    String cssStringDefaultTxtFldColor();

    boolean checkEnteredEnglishLetters(String w);
    boolean checkEnteredEnglishLettersWithNumbers(String w);
    boolean checkEnteredPositiveDecimalOrNormalNumbers(String w);
    boolean checkEnteredAddress(String w);
    boolean checkEnteredPhoneNumber(String w);
}
