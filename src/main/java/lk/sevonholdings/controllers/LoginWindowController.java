package lk.sevonholdings.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.UserService;

import java.io.IOException;
import java.net.URL;

public class LoginWindowController {
//    Manager,perera,1234
//    Owner,kamal,1234
//    Receptionist,nimal,1234

    public TextField txtfldusername; //username typing text field
    public PasswordField txtfldpassword; //password typing text field
    public AnchorPane maincontext; //main anchorpane
    UserService userService;

    private Stage stage;

    public void initialize() {
        this.userService= ServiceFactory.getInstance().getService(ServiceTypes.USERSERVICE);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {  //if login button clicked
        if(userService.validateToLoginManagerWindow(txtfldusername.getText(), txtfldpassword.getText()).get().equals("Manager")){
            Stage stgLoginWindow = (Stage)maincontext.getScene().getWindow();
            URL resourse = this.getClass().getResource("/view/managerwindow/MngrMainForm.fxml");
            Parent load = null;
            try {
                load = FXMLLoader.load(resourse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stgLoginWindow.setScene(new Scene(load));
            stgLoginWindow.show();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) { //if cancel button clicked

    }

    public void managerOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void OwnerOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void RepOInAction(ActionEvent actionEvent) throws IOException {

    }

    private void closeLoginWindow(){  //close LoginWindow Stage
        Stage stgLoginWindow = (Stage)maincontext.getScene().getWindow();
        stgLoginWindow.close();
    }
}
