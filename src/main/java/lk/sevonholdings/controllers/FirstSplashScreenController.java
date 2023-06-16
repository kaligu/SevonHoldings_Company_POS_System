package lk.sevonholdings.controllers;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.scene.control.*;
import lk.sevonholdings.util.Navigation;
import lk.sevonholdings.util.Routes;

import java.io.IOException;

public class FirstSplashScreenController {
    public ProgressBar pbar; //start screen loading bar
    public AnchorPane pane; //main anchor pane
    public Text txtloading; //loading text
    public Text txtloadsteps; //loading steps text

    public void initialize() {
        load();
    }

    private void load() {
        Task pbartsk = new Task() {
            @Override
            protected Object call() throws Exception {
                int count=1;
                for(int i=1;i<=10;i++){
                    Thread.sleep(200);
                    updateProgress(count,10);
                    if(i==1){
                        txtloading.setText("Loading.");
                        txtloadsteps.setText("Initializing Application....");
                    }else if(i==2){
                        txtloading.setText("Loading. .");
                        txtloadsteps.setText("Initializing Application......");
                    }else if(i==3){
                        txtloading.setText("Loading. . .");
                        txtloadsteps.setText("Loading Internal Resources....");
                    }else if(i==4){
                        txtloading.setText("Loading. . . . .");
                        txtloadsteps.setText("Loading Internal Resources......");
                    }else if(i==5){
                        txtloading.setText("Loading.");
                        txtloadsteps.setText("Loading Images....");
                    }else if(i==6){
                        txtloading.setText("Loading. .");
                        txtloadsteps.setText("Loading Images......");
                    }else if(i==7){
                        txtloading.setText("Loading. . .");
                        txtloadsteps.setText("Loading UIs....");
                    }else if(i==8){
                        txtloading.setText("Loading. . . .");
                        txtloadsteps.setText("Loading UIs......");
                    }else if(i==9) {
                        txtloading.setText("Loading. . . . .");
                        txtloadsteps.setText("Finished....");
                    }
                    count++;
                }
                return null;
            }
        };
        pbar.progressProperty().bind(pbartsk.progressProperty());

        new Thread(pbartsk).start();

        pbartsk.setOnSucceeded(event -> {
            try {
                Navigation.navigate(Routes.LOGINWINDOW,pane); //navigate to login window
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

