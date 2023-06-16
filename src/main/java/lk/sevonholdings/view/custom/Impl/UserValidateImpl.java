package lk.sevonholdings.view.custom.Impl;

import javafx.scene.layout.AnchorPane;
import lk.sevonholdings.view.custom.Uservalidate;

import java.sql.Date;
import java.sql.Time;

public class UserValidateImpl implements Uservalidate {

    @Override
    public boolean isUserNotAction(AnchorPane pane) {
        return false;
    }

    @Override
    public Date getSQLDateNow() {
       java.util.Date utildate = new java.util.Date();
        Date sqldate  = new Date(utildate.getTime());
        return sqldate;
    }

    @Override
    public Time getSQLTimeNow() {
        java.util.Date utildate = new java.util.Date();
        Time sqltime = new Time(utildate.getTime());
        return sqltime;
    }

    @Override
    public void SendSMS(int phonenumber, String Massege) {

    }

    @Override
    public String cssStringFocusedTxtFldColor() {
        return ("-fx-font-size: 20px;-fx-background-color:red ");
    }

    @Override
    public String cssStringDefaultTxtFldColor() {
        return ("}#textfields {\n" +
                "    -fx-background-color:\n" +
                "            #021138,\n" +
                "            linear-gradient(#415f6e, #30454b);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 19px;\n" +
                "}\n" +
                "#textfields Text {\n" +
                "    -fx-effect: dropshadow( one-pass-box , rgba(255, 255, 255, 0.8), 0, 0.0 , 0 , 0);\n" +
                "}\n" +
                "#textfields:focused {\n" +
                "    -fx-background-color:\n" +
                "            #011a3d,\n" +
                "            linear-gradient(#517486, #30454b);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;");
    }

    @Override
    public boolean checkEnteredEnglishLetters(String w){
        return w.matches("[a-zA-Z]+");
    }

    @Override
    public boolean checkEnteredEnglishLettersWithNumbers(String w) {
        return w.matches("^[A-Za-z0-9_]+$");
    }

    @Override
    public boolean checkEnteredPositiveDecimalOrNormalNumbers(String w) {
        return w.matches("^\\d{0,10}(\\.\\d{0,2})?$");
    }

    @Override
    public boolean checkEnteredAddress(String w) {
        return w.matches("^[#.0-9a-zA-Z\\s,-]+$");
    }

    @Override
    public boolean checkEnteredPhoneNumber(String w) {
        return w.matches("\\(?\\d+\\)?[-.\\s]?\\d+[-.\\s]?\\d+");
    }
}
