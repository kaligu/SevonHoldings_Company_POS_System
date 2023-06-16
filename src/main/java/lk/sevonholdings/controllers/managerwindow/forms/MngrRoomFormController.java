package lk.sevonholdings.controllers.managerwindow.forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.sevonholdings.dto.WarehouseDTO;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.WarehouseService;
import lk.sevonholdings.view.custom.Impl.UserValidateImpl;
import lk.sevonholdings.view.custom.Uservalidate;

public class MngrRoomFormController {
    public TableView<WarehouseDTO> tblroom;
    public TableColumn colroomid;
    public TableColumn coldescription;
    public TableColumn colmaxroomvolume;
    public TableColumn colfilledroomvolume;
    public TableColumn colavailability;
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

    Uservalidate uservalidate;
    WarehouseService warehouseService;

    public void initialize(){
        uservalidate = new UserValidateImpl();
        VisibleOffAllPanes();
        navigatebtnscntexts.setVisible(true);
        this.warehouseService= ServiceFactory.getInstance().getService(ServiceTypes.WAREHOUSE);

        colroomid.setCellValueFactory(new PropertyValueFactory<>("Room_Id"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colmaxroomvolume.setCellValueFactory(new PropertyValueFactory<>("Maximum_Room_Volume"));
        colfilledroomvolume.setCellValueFactory(new PropertyValueFactory<>("Filled_Room_Volume"));
        colavailability.setCellValueFactory(new PropertyValueFactory<>("Availability"));

        loadDataToTable();
    }
    private void loadLastRoomID() {
        String id=warehouseService.findLastWarehouseRoomNo();
        String[] array = id.split("(?=\\d)(?<!\\d)");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        atxtfldroomid.setText("R"+finalizeOrderId);
        btnsave.setDisable(true);
        atxtfldescription.requestFocus();
    }
    private void loadDataToTable() {
        ObservableList<WarehouseDTO> obl = FXCollections.observableArrayList();
        obl.setAll(warehouseService.findAllWarehouse());
        tblroom.setItems(obl);
    }
    public void VisibleOffAllPanes(){
        navigatebtnscntexts.setVisible(false);
        paneaddroom.setVisible(false);
        panedeleteroom.setVisible(false);
        panemodifyroom.setVisible(false);
    }
    public void onActionbtnAddRoom(ActionEvent actionEvent) {
        loadLastRoomID();
        VisibleOffAllPanes();
        paneaddroom.setVisible(true);
        loadLastRoomID();
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
        WarehouseDTO room = new WarehouseDTO(
                atxtfldroomid.getText(),
                atxtfldescription.getText(),
                Double.parseDouble(atxtfldmaxroomvolume.getText()),
                Double.parseDouble(atxtfldfilledroomvolume.getText()),
                atxtfldavailability.getText()
        );
        if(warehouseService.saveWarehouse(room)){
            new Alert(Alert.AlertType.INFORMATION,"New Warehouse room addeed Successfully.").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Warehouse room not Added!.").show();
        }

    }

    public void atxtfldescriptionCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLetters(atxtfldescription.getText())){
            atxtfldescription.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btnsave.setDisable(false);
            notifyregaxchecktxa.setVisible(false);
            atxtfldescription.requestFocus();
        }else{
            atxtfldescription.getStylesheets().add("Styles/button.css");
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
            atxtfldmaxroomvolume.getStylesheets().add("Styles/button.css");
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
            atxtfldavailability.getStylesheets().add("Styles/button.css");
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
            atxtfldfilledroomvolume.getStylesheets().add("Styles/button.css");
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
      /*  try {
            WarehouseRoom r = WarehouseRoomModel.loadAllRoomsByID(dtxtfldroomid1.getText());
            dtxtfldescription1.setText(r.getDescription());
            dtxtfldavailability1.setText(r.getAvailability());
            dtxtfldmaxroomvolume1.setText(String.valueOf(r.getMaximum_Room_Volume()));
            dtxtfldfilledroomvolume1.setText(String.valueOf(r.getFilled_Room_Volume()));
            new Alert(Alert.AlertType.CONFIRMATION, "Data Filled!").show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e+"Try Again!").show();
        }*/
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
            dtxtfldescription1.getStylesheets().add("Styles/button.css");
            dtxtfldescription1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxtm.setVisible(true);
        }
    }

    public void dtxtfldroomidCheckRegax(KeyEvent keyEvent) {
        if(uservalidate.checkEnteredEnglishLettersWithNumbers(dtxtfldroomid.getText())){
            dtxtfldroomid.setStyle(uservalidate.cssStringDefaultTxtFldColor());
            btndelete.setDisable(false);
            notifyregaxchecktxtm.setVisible(false);
            dtxtfldroomid.requestFocus();
        }else{
            dtxtfldroomid.getStylesheets().add("Styles/button.css");
            dtxtfldroomid.setStyle(uservalidate.cssStringFocusedTxtFldColor());
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
            dtxtfldmaxroomvolume1.getStylesheets().add("Styles/button.css");
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
            dtxtfldavailability1.getStylesheets().add("Styles/button.css");
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
            dtxtfldfilledroomvolume1.getStylesheets().add("Styles/button.css");
            dtxtfldfilledroomvolume1.setStyle(uservalidate.cssStringFocusedTxtFldColor());
            ubtnupdate.setDisable(true);
            notifyregaxchecktxtm.setVisible(true);
        }
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        /*WarehouseRoom room = new WarehouseRoom(
                dtxtfldroomid1.getText(),
                dtxtfldescription1.getText(),
                Double.parseDouble(dtxtfldmaxroomvolume1.getText()),
                Double.parseDouble(dtxtfldfilledroomvolume1.getText()),
                dtxtfldavailability1.getText()
        );

        try {
            boolean isAdded = WarehouseRoomModel.update(room);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
                tblroom.getItems().clear();
                loadDataToTable();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING, e+"Try Again!").show();
        }*/
    }

    public void dtxtfldroomidOnAction(ActionEvent actionEvent) {

    }

    public void OnActionDelete(ActionEvent actionEvent) {
     /*   try {
            boolean isdeleted = WarehouseRoomModel.deleteroom(dtxtfldroomid.getText());
            if(isdeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted!").show();
                tblroom.getItems().clear();
                loadDataToTable();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING, e+"Try Again!").show();
        }*/
    }

    public void btnallroomjrViewOnAction(ActionEvent actionEvent) {
       /* final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        Stage stage = new Stage();
        stage.setTitle("Report");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();*/
    }

   /* private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HashMap hm=new HashMap<>();
                InputStream inputStream = null;
                JasperPrint jasperPrint=null;
                inputStream = this.getClass().getClassLoader().getResourceAsStream("lk/sevonholdings/reports/AllViewJRWarehouseroom.jrxml");
                try {
                    JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
                    jasperPrint = JasperFillManager.fillReport(compileReport, hm, DBConnection.getDbConnection().getConnection());
                } catch (JRException e) {
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
    }
    */
}
