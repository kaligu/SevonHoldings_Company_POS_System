package lk.sevonholdings.controllers.managerwindow;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.sevonholdings.dto.Biscuits_brand_size_qtyDTO;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.WarehouseService;
import lk.sevonholdings.util.Navigation;
import lk.sevonholdings.util.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MngrMainFormController {
    public Button btnpick;
    public Button btnorders;
    public Button btnshopview;
    public ImageView imgnotifybtnchatbox;
    public ImageView imgbellbtnchatbox;
    public ImageView btncalculator;
    public ImageView panesideimg;
    public AnchorPane navigatemaincontext;
    public AnchorPane chatboxpane;
    public AnchorPane homemidpane;
    public Button btnstocks;
    public Button btnreptargets;
    public Button btnsupply;
    public Button btnlivebiscuits;
    public Button btnwarehouse;
    public ImageView btnclosewindow;
    public ImageView btnmaxwindow;
    public ImageView btnminwindow;
    public Button btnproducts;
    public Button btnchatbox;
    public Button btnhome;
    public AnchorPane mainankrpane;
    public AnchorPane titlebar;
    public PieChart piechart;
    private String clickedcolourblue;
    private String notclickedcolourblue;
    private String notclickedcolourpurple;
    private String clickedcolourpurple;
    private boolean ismaximised = false;
    private double x=0;
    private double y=0;
    WarehouseService warehouseService;


    public void initialize(){
        this.warehouseService= ServiceFactory.getInstance().getService(ServiceTypes.WAREHOUSE);
        loadpiechart();
        //clicked & notclicked button css initialize to string variable
        this.clickedcolourblue = "-fx-background-color:\n            #000000,\n            linear-gradient(#395cab, #223768),\n            linear-gradient(#728cbe, #263e75),\n            linear-gradient(#7ebcea, #6a7998);\n    -fx-background-insets: 0,1,2,3;\n    -fx-background-radius: 3,2,2,2;\n    -fx-padding: 12 30 12 30;\n    -fx-text-fill: white;\n    -fx-font-size: 18px;\n    -fx-font-weight:bold;";
        this.notclickedcolourblue = "#bluebutton {\n    -fx-background-color:\n            #000000,\n            linear-gradient(#7ebcea, #2f4b8f),\n            linear-gradient(#426ab7, #263e75),\n            linear-gradient(#395cab, #223768);\n    -fx-background-insets: 0,1,2,3;\n    -fx-background-radius: 3,2,2,2;\n    -fx-padding: 12 30 12 30;\n    -fx-text-fill: white;\n    -fx-font-size: 18px;\n}\n#bluebutton Text {\n    -fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 0, 0.0 , 0 , 1);\n}\n#bluebutton:hover {\n    -fx-background-color:\n            #000000,\n            linear-gradient(#395cab, #223768),\n            linear-gradient(#426ab7, #263e75),\n            linear-gradient(#7ebcea, #2f4b8f);\n    -fx-background-insets: 0,1,2,3;\n    -fx-background-radius: 3,2,2,2;\n    -fx-padding: 12 30 12 30;\n    -fx-text-fill: white;\n    -fx-font-size: 22px;\n    -fx-font-weight:bold;\n}";
        this.notclickedcolourpurple = "-fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#d5a4f3, #652b96),\n" +
                "            linear-gradient(#9362b4, #531981),\n" +
                "            linear-gradient(#7024ab, #81498c);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 18px;;\n}";
        this.clickedcolourpurple = "-fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#7024ab, #652b96),\n" +
                "            linear-gradient(#9362b4, #531981),\n" +
                "            linear-gradient(#d5a4f3, #3f105b);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight:bold;";

    }

    private void loadpiechart() {
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList();
        piechart.getData().clear();
        ArrayList<Biscuits_brand_size_qtyDTO> blist = warehouseService.showLiveStocks_OnlyBiscuitNameAndQty();
        for (Biscuits_brand_size_qtyDTO b:blist) {
            piechartdata.add(new PieChart.Data((b.getBiscuit_Brand()+" "+b.getSize()+"-("+b.getAvailableQty()+")"),b.getAvailableQty()));
        }
        piechart.setData(piechartdata);
        piechart.setLegendVisible(false);

        piechartdata.stream().forEach(pieData -> {         //action method
            pieData.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
                Bounds b1 = pieData.getNode().getBoundsInLocal();
                double newX = (b1.getWidth()) / 2 + b1.getMinX();
                double newY = (b1.getHeight()) / 2 + b1.getMinY();
                // Make sure pie wedge location is reset
                pieData.getNode().setTranslateX(0);
                pieData.getNode().setTranslateY(0);
                TranslateTransition tt = new TranslateTransition(
                        Duration.millis(1400), pieData.getNode());
                tt.setByX(newX);
                tt.setByY(newY);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();
            });
        });
    }

    public void setFormPickInOut(MouseEvent mouseEvent) {


    }

    public void setFormOrders(ActionEvent actionEvent) {
    }

    public void setFormShops(ActionEvent actionEvent) {
    }

    public void OnActionbtnfileexplorer(MouseEvent mouseEvent) {
    }

    public void OnActionbtncaleder(MouseEvent mouseEvent) {
    }

    public void OnActionbtncalculator(MouseEvent mouseEvent) {
    }

    public void btnclstablepopupcontext(MouseEvent mouseEvent) {
    }

    public void mainankepaneMousePressedAction(MouseEvent mouseEvent) {
        if(!ismaximised) {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        }
    }

    public void mainankepaneMouseDraggedAction(MouseEvent mouseEvent) {
        if(!ismaximised) {
            getStageOnThisAnkrPane().setY(mouseEvent.getScreenY() - y);
            getStageOnThisAnkrPane().setX(mouseEvent.getScreenX() - x);
        }
    }

    public void btnsendToOwnerOnAction(ActionEvent actionEvent) {
    }

    public void btnsendTorepOnAction(ActionEvent actionEvent) {
    }

    public void OnActionnewmsgownerconfirmationpane(ActionEvent actionEvent) {
    }

    public void OnActionnewmsgmanagerconfirmationpane(ActionEvent actionEvent) {
    }

    public void setFormHome(ActionEvent actionEvent) {
    }

    public void setFormLiveStocks(ActionEvent actionEvent) {
    }

    public void setFormRepTargets(ActionEvent actionEvent) {
    }

    public void setFormSupply(ActionEvent actionEvent) {
    }

    public void setFormLiveBiscuits(ActionEvent actionEvent) {
    }

    public void setFormWarehouse(ActionEvent actionEvent) {
    }

    public void setFormLogin(ActionEvent actionEvent) {
    }

    public void setOnMouseDraggedtitlebar(MouseEvent mouseEvent) { //set ankerpane x and y axis
        getStageOnThisAnkrPane().setY(mouseEvent.getScreenY() - y);
        getStageOnThisAnkrPane().setX(mouseEvent.getScreenX() - x);
    }

    public void setOnMousePressedtitlebar(MouseEvent mouseEvent) { //when pressed titlebar get x y values
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    public void exitActionClosebtn(MouseEvent mouseEvent) {
        btnclosewindow.setImage(new Image("assets/greyclose.png"));
    }

    public void enterActionClosebtn(MouseEvent mouseEvent) {
        btnclosewindow.setImage(new Image("assets/redclose.png"));
    }

    public void btncloseOnAction(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //close alert
        alert.setTitle("SevonHoldings System");
        alert.setHeaderText("Close Confirmation");
        alert.setContentText("Are you sure you want to close Manager window?");
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent() || result.get() == ButtonType.OK) {
            getStageOnThisAnkrPane().close();
        }
    }

    private Stage getStageOnThisAnkrPane(){
        Stage primarystage = (Stage) mainankrpane.getScene().getWindow();
        return primarystage;
    }

    public void btnmaxOnAction(MouseEvent mouseEvent) {
        double oldstagewidth = getStageOnThisAnkrPane().getWidth();
        double oldstageheight = getStageOnThisAnkrPane().getHeight();


        double oldbtnclslayoutx = btnclosewindow.getLayoutX();
        double oldbtnmaxlayoutx = btnmaxwindow.getLayoutX();
        double oldbtnminloayoutx = btnminwindow.getLayoutX();

        double oldnaviankrwidth = mainankrpane.getWidth();
        double oldnaviankrheight = mainankrpane.getHeight();

        if(getStageOnThisAnkrPane().isMaximized()){

            ismaximised=false;
            getStageOnThisAnkrPane().setMaximized(false);
        }else{
            ismaximised=true;
            getStageOnThisAnkrPane().setMaximized(true);
        }

        double newstagewidth = getStageOnThisAnkrPane().getWidth();

        titlebar.setPrefWidth(newstagewidth+10);
        titlebar.setMaxWidth(newstagewidth+10);


        btnclosewindow.setLayoutX(newstagewidth-(oldstagewidth-oldbtnclslayoutx));
        btnmaxwindow.setLayoutX(newstagewidth-(oldstagewidth-oldbtnmaxlayoutx));
        btnminwindow.setLayoutX(newstagewidth-(oldstagewidth-oldbtnminloayoutx));
        mainankrpane.setMaxWidth(getStageOnThisAnkrPane().getWidth()-(oldstagewidth-oldnaviankrwidth));
        mainankrpane.setMaxHeight(getStageOnThisAnkrPane().getHeight()-(oldstageheight-oldnaviankrheight));
    }

    public void enterActionMaximizebtn(MouseEvent mouseEvent) {
        if(ismaximised) { //if maximised window
            btnmaxwindow.setImage(new Image("assets/redmin.png"));
        }else{
            btnmaxwindow.setImage(new Image("assets/max.gif"));
        }
    }

    public void exitActionMaximizebtn(MouseEvent mouseEvent) {
        if(ismaximised) { //if maximised window
            btnmaxwindow.setImage(new Image("assets/greymin.png"));
        }else{
            btnmaxwindow.setImage(new Image("assets/max.gif"));
        }
    }

    public void btnminOnAction(MouseEvent mouseEvent) {
        getStageOnThisAnkrPane().setIconified(true); //minimize window
    }

    public void enterActionMinimizebtn(MouseEvent mouseEvent) {
        btnminwindow.setImage(new Image("assets/redminimize.png"));
    }

    public void exitActionMinimizebtn(MouseEvent mouseEvent) {
        btnminwindow.setImage(new Image("assets/greyminimize.png"));
    }

    public void btnhomeOnAction(MouseEvent mouseEvent) {
    }

    public void enterActionhomebtn(MouseEvent mouseEvent) {
    }

    public void exitActionhomebtn(MouseEvent mouseEvent) {
    }

    public void setFormProducts(ActionEvent actionEvent) {
    }

    public void setFormChatbox(ActionEvent actionEvent) {
    }

    public void setOnHomeScene(ActionEvent actionEvent) {
        this.goToNavigate();
        this.setResetButtonColourBlue();
        this.btnhome.setStyle(this.clickedcolourpurple);
        this.homemidpane.setVisible(true);
    }

    private void setResetButtonColourBlue() {
        this.btnhome.setStyle(this.notclickedcolourpurple);
        this.btnpick.setStyle(this.notclickedcolourblue);
        this.btnorders.setStyle(this.notclickedcolourblue);
        this.btnchatbox.setStyle(this.notclickedcolourblue);
        this.btnsupply.setStyle(this.notclickedcolourblue);
        this.btnreptargets.setStyle(this.notclickedcolourblue);
        this.btnwarehouse.setStyle(this.notclickedcolourblue);
        this.btnlivebiscuits.setStyle(this.notclickedcolourblue);
        this.btnchatbox.setStyle(this.notclickedcolourblue);
        this.btnshopview.setStyle(this.notclickedcolourblue);
        this.btnstocks.setStyle(this.notclickedcolourblue);
        this.btnproducts.setStyle(this.notclickedcolourblue);
    }

    private void goToNavigate() {
        this.panesideimg.setVisible(false);
        this.homemidpane.setVisible(false);
        this.chatboxpane.setVisible(false);
        this.navigatemaincontext.setVisible(false);
    }

    public void showStocksOnAction(ActionEvent actionEvent) throws IOException {
        goToNavigate();
        navigatemaincontext.setVisible(true);
        setResetButtonColourBlue();
        btnstocks.setStyle(clickedcolourblue);
        Navigation.navigate(Routes.Mnger_LiveStocksByRoom_Form , navigatemaincontext);
    }

    public void setOnTargetScene(ActionEvent actionEvent) {

    }

    public void setOnSupplyScene(ActionEvent actionEvent) throws IOException {
        goToNavigate();
        navigatemaincontext.setVisible(true);
        setResetButtonColourBlue();
        btnsupply.setStyle(clickedcolourblue);
        Navigation.navigate(Routes.Mnger_Supply_Form, navigatemaincontext);
    }

    public void setOnLiveBiscuits(ActionEvent actionEvent) throws IOException {
        goToNavigate();
        navigatemaincontext.setVisible(true);
        setResetButtonColourBlue();
        btnlivebiscuits.setStyle(clickedcolourblue);
        Navigation.navigate(Routes.Mngr_LiveStocksbyExpire_Form, navigatemaincontext);
    }

    public void setOnWarehouseScene(ActionEvent actionEvent) throws IOException {
        this.goToNavigate();
        this.navigatemaincontext.setVisible(true);
        this.setResetButtonColourBlue();
        this.btnwarehouse.setStyle(this.clickedcolourblue);
        Navigation.navigate(Routes.Mngr_Room_Form, this.navigatemaincontext);
    }

    public void setOnProductsScene(ActionEvent actionEvent) throws IOException {
        this.goToNavigate();
        this.navigatemaincontext.setVisible(true);
        this.setResetButtonColourBlue();
        this.btnproducts.setStyle(this.clickedcolourblue);
        Navigation.navigate(Routes.Mngr_PRODUCT_FORM, this.navigatemaincontext);
    }

    public void OnActionbtnchatbox(MouseEvent mouseEvent) {
    }

    public void setOnShopView(ActionEvent actionEvent) throws IOException {
        goToNavigate();
        navigatemaincontext.setVisible(true);
        setResetButtonColourBlue();
        btnshopview.setStyle(clickedcolourblue);
        Navigation.navigate(Routes.Mnger_Shop_Form, navigatemaincontext);
    }

    public void setOnShopOrders(ActionEvent actionEvent) throws IOException {
        goToNavigate();
        navigatemaincontext.setVisible(true);
        setResetButtonColourBlue();
        btnorders.setStyle(clickedcolourblue);
        Navigation.navigate(Routes.Mnger_ShopOrder_Form, navigatemaincontext);
    }

    public void setOnscenePickInOutx(ActionEvent actionEvent) {
    }

    public void setOnscenePickInOut(MouseEvent mouseEvent) {
    }
}
