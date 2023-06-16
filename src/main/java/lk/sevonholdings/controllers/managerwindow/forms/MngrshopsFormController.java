package lk.sevonholdings.controllers.managerwindow.forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.sevonholdings.dto.ShopDTO;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.ShopService;
import lk.sevonholdings.view.custom.Impl.UserValidateImpl;
import lk.sevonholdings.view.custom.Uservalidate;

import java.sql.Date;
import java.sql.SQLException;

public class MngrshopsFormController {
    public AnchorPane navigatebtnscntexts;
    public AnchorPane paneaddroom;
    public TextField atxtfldescription;
    public TextField atxtfldroomid;
    public TextField atxtfldmaxroomvolume;
    public TextField atxtfldavailability;
    public TextField atxtfldfilledroomvolume;
    public Button btnsave;
    public Text notifyregaxchecktxt;
    public Text notifyregaxchecktxa;
    public AnchorPane panemodifyroom;
    public TextField dtxtfldescription1;
    public TextField dtxtfldroomid1;
    public TextField dtxtfldmaxroomvolume1;
    public TextField dtxtfldavailability1;
    public TextField dtxtfldfilledroomvolume1;
    public Button ubtnupdate;
    public Text notifyregaxchecktxtm;
    public AnchorPane panedeleteroom;
    public TextField dtxtfldroomid;
    public Button btndelete;
    public Text notifyregaxchecktxtd;

    public TableView tblshops;
    public TableColumn colshopid;
    public TableColumn colshopname;
    public TableColumn colshopcontact;
    public TableColumn colregrepid;
    public TableColumn colshoplocation;
    public TableColumn colregdate;
    public TextField atxtfldshopname;
    public TextField atxtfldshopid;
    public TextField atxtfldshopcontact;
    public TextField txtfldrerepid;
    public TextField atxtfldshoplocation;
    public TextField atxtfldshopname1;
    public TextField atxtfldshopid1;
    public TextField atxtfldshopcontact1;
    public TextField txtfldrerepid1;
    public TextField atxtfldshoplocation1;
    public TextField dtxtfldshopiddelete;

    private ShopService shopService;
    Uservalidate uservalidate;

    public void initialize(){
        uservalidate = new UserValidateImpl();
        VisibleOffAllPanes();
        navigatebtnscntexts.setVisible(true);
        this.shopService= ServiceFactory.getInstance().getService(ServiceTypes.SHOP);
        colshopid.setCellValueFactory(new PropertyValueFactory<>("Shop_Id"));
        colshopname.setCellValueFactory(new PropertyValueFactory<>("Shop_Name"));
        colshopcontact.setCellValueFactory(new PropertyValueFactory<>("Shop_Contact"));
        colregrepid.setCellValueFactory(new PropertyValueFactory<>("Shop_Location"));
        colshoplocation.setCellValueFactory(new PropertyValueFactory<>("Reg_Rep_Id"));
        colregdate.setCellValueFactory(new PropertyValueFactory<>("Reg_Date"));

        loadDataToTable();
    }

    private void loadShopID() {
        String id = null;
        id = shopService.findLastShopNo();
        String[] array = id.split("(?=\\d)(?<!\\d)");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        atxtfldshopid.setText("S"+finalizeOrderId);
        atxtfldshopid.requestFocus();
    }

    private void loadDataToTable() {
        ObservableList<ShopDTO> obl = FXCollections.observableArrayList();
        obl.setAll(shopService.findAllShops());
        tblshops.setItems(obl);
    }
    public void VisibleOffAllPanes(){
        navigatebtnscntexts.setVisible(false);
        paneaddroom.setVisible(false);
        panedeleteroom.setVisible(false);
        panemodifyroom.setVisible(false);
    }
    public void onActionbtnAddRoom(ActionEvent actionEvent) {
        loadShopID();
        VisibleOffAllPanes();
        paneaddroom.setVisible(true);
    }

    public void onActionbtnModifyRoom(ActionEvent actionEvent) {
        VisibleOffAllPanes();
        panemodifyroom.setVisible(true);
    }

    public void onActionbtnDeleteRoom(ActionEvent actionEvent) {
        VisibleOffAllPanes();
        panedeleteroom.setVisible(true);
    }

    public void atxtfldescriptiononAction(ActionEvent actionEvent) {
        atxtfldmaxroomvolume.requestFocus();
    }

    public void onActionatxtfldroomid(ActionEvent actionEvent) {
        atxtfldescription.requestFocus();
    }

    public void atxtfldmaxroomvolumeOnAction(ActionEvent actionEvent) {
        atxtfldfilledroomvolume.requestFocus();
    }

    public void atxtfldavailabilityOnAction(ActionEvent actionEvent) {
        btnsave.fire();
    }

    public void atxtfldfilledroomvolumeOnAction(ActionEvent actionEvent) {
        atxtfldavailability.requestFocus();
    }

    public void btnsaveOnAction(ActionEvent actionEvent) {
        ShopDTO s = new ShopDTO(
                atxtfldshopid.getText(),
                atxtfldshopname.getText(),
                atxtfldshopcontact.getText(),
                atxtfldshoplocation.getText(),
                txtfldrerepid.getText(),
                Date.valueOf(String.valueOf(uservalidate.getSQLDateNow())),
                "active"
        );
/*
        try {
            boolean isAdded = ShopData.save(s);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "New Shop Added!").show();
                tblshops.getItems().clear();
                loadDataToTable();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING, e+"Try Again!").show();
        }*/
    }

    public void atxtfldescriptionCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLetters(atxtfldescription.getText())){
            atxtfldescription.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldescription.requestFocus();
        }else{
            atxtfldescription.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldescription.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldroomidCheckRegax(KeyEvent keyEvent) {
    }

    public void atxtfldmaxroomvolumeCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredPositiveDecimalOrNormalNumbers(atxtfldmaxroomvolume.getText())){
            atxtfldmaxroomvolume.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldmaxroomvolume.requestFocus();
        }else{
            atxtfldmaxroomvolume.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldmaxroomvolume.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldavailabilityCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLetters(atxtfldavailability.getText())){
            atxtfldavailability.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldavailability.requestFocus();
        }else{
            atxtfldavailability.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldavailability.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldfilledroomvolumeCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredPositiveDecimalOrNormalNumbers(atxtfldfilledroomvolume.getText())){
            atxtfldfilledroomvolume.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldfilledroomvolume.requestFocus();
        }else{
            atxtfldfilledroomvolume.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldfilledroomvolume.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void OnActionGotoBack(MouseEvent mouseEvent) {
        VisibleOffAllPanes();
        navigatebtnscntexts.setVisible(true);
    }

    public void dtxtfldescriptiononAction(ActionEvent actionEvent) {
        dtxtfldavailability1.requestFocus();
    }

    public void onActiondtxtfldroomid(ActionEvent actionEvent) {

    }

    public void dtxtfldmaxroomvolumeOnAction(ActionEvent actionEvent) {
        dtxtfldfilledroomvolume1.requestFocus();
    }

    public void dtxtfldavailabilityOnAction(ActionEvent actionEvent) {
        dtxtfldmaxroomvolume1.requestFocus();
    }

    public void dtxtfldfilledroomvolumeOnAction(ActionEvent actionEvent) {
        ubtnupdate.fire();
    }

    public void dtxtfldescriptionCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLetters(dtxtfldescription1.getText())){
            dtxtfldescription1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxtm.setVisible(false);
            dtxtfldescription1.requestFocus();
        }else{
            dtxtfldescription1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            dtxtfldescription1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxtm.setVisible(true);
        }
    }

    public void dtxtfldroomidCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(dtxtfldshopiddelete.getText())){
            dtxtfldshopiddelete.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btndelete.setDisable(false);
            notifyregaxchecktxtm.setVisible(false);
            dtxtfldshopiddelete.requestFocus();
        }else{
            dtxtfldshopiddelete.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            dtxtfldshopiddelete.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btndelete.setDisable(true);
            notifyregaxchecktxtm.setVisible(true);
        }
    }

    public void dtxtfldmaxroomvolumeCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredPositiveDecimalOrNormalNumbers(dtxtfldmaxroomvolume1.getText())){
            dtxtfldmaxroomvolume1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxtm.setVisible(false);
            dtxtfldmaxroomvolume1.requestFocus();
        }else{
            dtxtfldmaxroomvolume1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            dtxtfldmaxroomvolume1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxtm.setVisible(true);
        }
    }

    public void dtxtfldavailabilityCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLetters(dtxtfldavailability1.getText())){
            dtxtfldavailability1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxtm.setVisible(false);
            dtxtfldavailability1.requestFocus();
        }else{
            dtxtfldavailability1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            dtxtfldavailability1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxtm.setVisible(true);
        }
    }

    public void dtxtfldfilledroomvolumeCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredPositiveDecimalOrNormalNumbers(dtxtfldfilledroomvolume1.getText())){
            dtxtfldfilledroomvolume1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxtm.setVisible(false);
            dtxtfldfilledroomvolume1.requestFocus();
        }else{
            dtxtfldfilledroomvolume1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            dtxtfldfilledroomvolume1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxtm.setVisible(true);
        }
    }

    public void btnupdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ShopDTO s = new ShopDTO(
                atxtfldshopid1.getText(),
                atxtfldshopname1.getText(),
                atxtfldshopcontact1.getText(),
                atxtfldshoplocation1.getText(),
                txtfldrerepid1.getText(),
                Date.valueOf(String.valueOf(uservalidate.getSQLDateNow())),
                "active"
        );
/*
        boolean isAdded = ShopData.Update(s);
        if(isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
            tblshops.getItems().clear();
            loadDataToTable();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }*/
    }

    public void dtxtfldroomidOnAction(ActionEvent actionEvent) {

    }

    public void OnActionDelete(ActionEvent actionEvent) {
      /*  try {
            boolean isdeleted = ShopData.Delete(dtxtfldshopiddelete.getText());
            if(isdeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted!").show();
                tblshops.getItems().clear();
                loadDataToTable();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING, e+"Try Again!").show();
        }*/
    }

 /*   public void btnallroomjrViewOnAction(ActionEvent actionEvent) {
        final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        Stage stage = new Stage();
        stage.setTitle("Report");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HashMap hm=new HashMap<>();
                InputStream inputStream = null;
                JasperPrint jasperPrint=null;
                inputStream = this.getClass().getClassLoader().getResourceAsStream("lk/sevonholdings/reports/JRAllViewShops.jrxml");
                try {
                    JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
                    jasperPrint = JasperFillManager.fillReport(compileReport, hm, DBConnection.getInstance().getConnection());
                } catch (JRException | SQLException | ClassNotFoundException e) {
                    //throw new RuntimeException(e);
                }
                JPanel x = new JPanel();
                JRViewer vr =new JRViewer(jasperPrint);
                x.setLayout(new BorderLayout());
                x.repaint();
                x.add(vr);
                x.revalidate();
                swingNode.setContent(x);
            }
        });
    }*/

    public void atxtfldshopnameOnAction(ActionEvent actionEvent) {
        atxtfldshopcontact.requestFocus();
    }

    public void OnActionatxtfldshopid(ActionEvent actionEvent) {
        atxtfldshopname.requestFocus();
    }

    public void atxtfldshopcontactOnAction(ActionEvent actionEvent) {
        atxtfldshoplocation.requestFocus();
    }

    public void txtfldrerepidOnAction(ActionEvent actionEvent) {
        btnsave.fire();
    }

    public void atxtfldshoplocationOnAction(ActionEvent actionEvent) {
        if(uservalidate.checkEnteredAddress(atxtfldshoplocation.getText())){
            atxtfldshoplocation.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshoplocation.requestFocus();
        }else{
            atxtfldshoplocation.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshoplocation.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldshopnameCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(atxtfldshopname.getText())){
            atxtfldshopname.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshopname.requestFocus();
        }else{
            atxtfldshopname.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshopname.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldshopidCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(atxtfldshopid.getText())){
            atxtfldshopid.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshopid.requestFocus();
        }else{
            atxtfldshopid.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshopid.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldshopcontactCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredPhoneNumber(atxtfldshopcontact.getText())){
            atxtfldshopcontact.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshopcontact.requestFocus();
        }else{
            atxtfldshopcontact.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshopcontact.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void txtfldrerepidCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(txtfldrerepid.getText())){
            txtfldrerepid.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            txtfldrerepid.requestFocus();
        }else{
            txtfldrerepid.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            txtfldrerepid.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldshoplocationCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredAddress(atxtfldshoplocation.getText())){
            atxtfldshoplocation.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshoplocation.requestFocus();
        }else{
            atxtfldshoplocation.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshoplocation.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            btnsave.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void OnActionatxtfldshopidmm(ActionEvent actionEvent) {
    /*    try {
            ShopDTO s = ShopData.GetAllShopsByID(atxtfldshopid1.getText());
            atxtfldshopname1.setText(s.getShop_Name());
            atxtfldshopcontact1.setText(s.getShop_Contact());
            atxtfldshoplocation1.setText(s.getShop_Location());
            txtfldrerepid1.setText(s.getReg_Rep_Id());
            new Alert(Alert.AlertType.CONFIRMATION, "Data Filled!").show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e+"Try Again!").show();
        }*/
    }

    public void atxtfldshopnameCheckRegax1(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(atxtfldshopname1.getText())){
            atxtfldshopname1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshopname1.requestFocus();
        }else{
            atxtfldshopname1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshopname1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldshopidCheckRegax1(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(atxtfldshopid1.getText())){
            atxtfldshopid1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshopid1.requestFocus();
        }else{
            atxtfldshopid1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshopid1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldshopcontactCheckRegax1(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredPhoneNumber(atxtfldshopcontact1.getText())){
            atxtfldshopcontact1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshopcontact1.requestFocus();
        }else{
            atxtfldshopcontact1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshopcontact1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void txtfldrerepidCheckRegax1(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(txtfldrerepid1.getText())){
            txtfldrerepid1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            txtfldrerepid1.requestFocus();
        }else{
            txtfldrerepid1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            txtfldrerepid1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void atxtfldshoplocationCheckRegax1(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredAddress(atxtfldshoplocation1.getText())){
            atxtfldshoplocation1.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            ubtnupdate.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldshoplocation1.requestFocus();
        }else{
            atxtfldshoplocation1.getStylesheets().add("lk/sevonholdings/view/styles/button.css");
            atxtfldshoplocation1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxa.setVisible(true);
        }
    }

    public void btnallroomjrViewOnAction(ActionEvent actionEvent) {
    }
}
