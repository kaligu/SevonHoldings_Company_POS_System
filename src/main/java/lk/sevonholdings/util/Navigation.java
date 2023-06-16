package lk.sevonholdings.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Navigation {
    private static AnchorPane maincontext;

    public static void navigate(Routes routes, AnchorPane maincontext) throws IOException {
        Navigation.maincontext = maincontext;
        Navigation.maincontext.getChildren().clear();

        switch (routes){
            case MainMngrWindow:
                setUi("/view/managerwindow/MngrMainForm.fxml");
                break;
        }
        switch (routes){
            case LOGINWINDOW:
                setUi("/view/LoginWindow.fxml");
                break;
        }
        switch (routes){
            case Mngr_PRODUCT_FORM:
                setUi("/view/managerwindow/forms/MngrProductForm.fxml");
                break;
        }
        switch (routes){
            case Mngr_Room_Form:
                setUi("/view/managerwindow/forms/MngrRoomForm.fxml");
                break;
        }
        switch (routes){
            case Mnger_LiveStocksByRoom_Form:
                setUi("/view/managerwindow/forms/MngrLiveStocksByRoomForm.fxml");
                break;
        }
        switch (routes){
            case Mngr_LiveStocksbyExpire_Form:
                setUi("/view/managerwindow/forms/MngrLiveStocksByExpireForm.fxml");
                break;
        }
        switch (routes){
            case Mnger_Shop_Form:
                setUi("/view/managerwindow/forms/MngrShopsForm.fxml");
                break;
        }
        switch (routes){
            case Mnger_ShopOrder_Form:
                setUi("/view/managerwindow/forms/MngrShopsOrdersForm.fxml");
                break;
        }
        switch (routes){
            case Mnger_Supply_Form:
                setUi("/view/managerwindow/forms/MngrSupplyForm.fxml");
                break;
        }
    }

    private static void setUi(String location) throws IOException {
        Navigation.maincontext.getChildren().add(FXMLLoader.load(Routes.class.getResource("" +location)));
    }
}
