package lk.sevonholdings.controllers.managerwindow.forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import lk.sevonholdings.dao.exception.ConstraintViolationException;
import lk.sevonholdings.dto.ProductDTO;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.ProductService;
import lk.sevonholdings.view.custom.Impl.UserValidateImpl;
import lk.sevonholdings.view.custom.Uservalidate;
import lk.sevonholdings.view.tm.ProductsTm;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


public class MngrProductFormController {
    public TableView<ProductsTm> tablbiscuits;
    public TableColumn colbiscuitsid;
    public TableColumn colpicture;
    public TableColumn colrange;
    public TableColumn colbrand;
    public TableColumn colsize;
    public TableColumn colcbfvolume;
    public TableColumn colsupplierpric;
    public TableColumn coldistributorpric;
    public TableColumn colmrp;
    public ImageView imgview;
    public TextField txtfldimgpath;
    public TextField txtfldbiscuitid;
    public TextField txtfldrange;
    public TextField txtfldbrand;
    public TextField txtfldsize;
    //public TextField txtfldcbfvolume;
    public TextField txtfldsupplierprice;
    public TextField txtflddistributorprice;
    public TextField txtfldmrp;
    public AnchorPane mainankrpan;
    public Button btnadd;
    public ProgressBar progrsbar;
    public AnchorPane ankrpanloading;
    public ProgressBar progrsbarload;
    public AnchorPane navigatebtnscntexts;
    public Label txtfldcbfvolume;
    public AnchorPane cbfvolumecontextpane;
    public TextField txtfldcbflength;
    public TextField txtfldcbfwidth;
    public TextField txtfldcbfheight;
    public Button btncbfaddtolabel;
    public Button btnaddcbfvolumetocontext;
    public Button btnsave;
    public AnchorPane addbiscuitmidcontextpane;
    public AnchorPane editpricescontextpane;
    public Text notifyregaxchecktxt;
    public TextField utxtfldbiscuitid;
    public TextField utxtflddistributorprice;
    public TextField utxtfldmrprice;
    public TextField utxtfldsupplierprice;
    public Button ubtnupdate;
    public Text unotifyregaxchecktxt;
    public TextField dtxtflddelete;
    public Button btndelete;
    public Text dnotifyregaxchecktxt;
    public AnchorPane deletebiscuitpane;
    public Button btnaddimg;
    public TextField txtfldtotalcbfvolume;

    double totalcbfvolume;
    Uservalidate userValidate;
    String imgpath;
    public ProductService productService;

    List<ProductDTO> blist;
    ObservableList<ProductsTm> obbList;

    public void initialize(){

        this.productService= ServiceFactory.getInstance().getService(ServiceTypes.PRODUCT); //create instance to access product service

        userValidate = new UserValidateImpl();
        colbiscuitsid.setCellValueFactory(new PropertyValueFactory<>("BiscuitsNo"));
        colpicture.setCellValueFactory(new PropertyValueFactory<>("ImagePath"));
        colrange.setCellValueFactory(new PropertyValueFactory<>("BiscuitRange"));
        colbrand.setCellValueFactory(new PropertyValueFactory<>("BiscuitBrand"));
        colsize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colcbfvolume.setCellValueFactory(new PropertyValueFactory<>("CBFVolume"));
        colsupplierpric.setCellValueFactory(new PropertyValueFactory<>("SupplierPrice"));
        coldistributorpric.setCellValueFactory(new PropertyValueFactory<>("DistributorPrice"));
        colmrp.setCellValueFactory(new PropertyValueFactory<>("MRPrice"));

        loadDataToTable();
    }

    private void loadBiscuitID() {
        String id = productService.findLastProductNo();
        String[] array = id.split("(?=\\d)(?<!\\d)");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        txtfldbiscuitid.setText("B"+finalizeOrderId);
        btnsave.setDisable(true);
    }


    private void loadDataToTable() {
        ankrpanloading.setVisible(true);
        blist = productService.findAllProducts();
        obbList = FXCollections.observableArrayList();
        for (ProductDTO c : blist) {
            FileInputStream input = null;
            try {
                input = new FileInputStream(c.getImagePath());
            } catch (FileNotFoundException e) {
                throw new ConstraintViolationException(e);  //throw runtime exception
            }
            Image img = new Image(input);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(180);
            imageView.setFitHeight(85);
            ProductsTm btm = new ProductsTm(
                    c.getBiscuitsNo(),
                    imageView,
                    c.getBiscuitRange(),
                    c.getBiscuitBrand(),
                    c.getSize(),
                    c.getCBFVolume(),
                    c.getSupplierPrice(),
                    c.getDistributorPrice(),
                    c.getMRPrice()
            );
            obbList.add(btm);
            tablbiscuits.setItems(obbList);
        }
        ankrpanloading.setVisible(false);
    }

    public void btnAddImageOnAction(ActionEvent actionEvent) {
        unlockSaveBtn();
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter extfilterjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extfilterpng = new FileChooser.ExtensionFilter("PNG files (*.png)","*.PNG");
        filechooser.getExtensionFilters().add(extfilterpng);
        filechooser.getExtensionFilters().add(extfilterjpg);
        File file = filechooser.showOpenDialog(null);
        if(file==null){
        }else{
            imgpath = StringEscapeUtils.escapeJava(file.getAbsolutePath()) ; //auto add escape characters to print string in java
            txtfldimgpath.setText(imgpath);
        }

    }

    public void btnsaveOnAction(ActionEvent actionEvent) {
        ProductDTO pDto = new ProductDTO(
                txtfldbiscuitid.getText(),
                txtfldimgpath.getText(),
                txtfldrange.getText(),
                txtfldbrand.getText(),
                txtfldsize.getText(),
                Double.parseDouble(String.valueOf(txtfldtotalcbfvolume.getText())),
                Double.parseDouble(String.valueOf(txtfldsupplierprice.getText())),
                Double.parseDouble(String.valueOf(txtflddistributorprice.getText())),
                Double.parseDouble(String.valueOf(txtfldmrp.getText()))
        );
        if(productService.saveProduct(pDto)){
            new Alert(Alert.AlertType.INFORMATION,"New Product addeed Successfully.").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Product not Added!.").show();
        }
        tablbiscuits.getItems().clear();
        blist.clear();
        obbList.clear();
        txtfldbrand.clear();
        txtfldsupplierprice.clear();
        txtfldrange.clear();
        txtflddistributorprice.clear();
        txtfldsize.clear();
        txtfldmrp.clear();
        txtfldtotalcbfvolume.clear();
        txtfldimgpath.clear();
        loadDataToTable();
    }

    public void onActionMeasureCbf(ActionEvent actionEvent) {
        unlockSaveBtn();
        btncbfaddtolabel.setDisable(true);
        cbfvolumecontextpane.setVisible(true);
    }

    public void onActionAddTotalCbf(ActionEvent actionEvent) {
        totalcbfvolume = (Double.parseDouble(txtfldcbflength.getText())+Double.parseDouble(txtfldcbfwidth.getText())+Double.parseDouble(txtfldcbfheight.getText()));
        txtfldtotalcbfvolume.setText(String.valueOf(totalcbfvolume));
        cbfvolumecontextpane.setVisible(false);
        txtfldsupplierprice.requestFocus();
        txtfldcbflength.clear();
        txtfldcbfwidth.clear();
        txtfldcbfheight.clear();
    }

    public void onActionBackFromCbf(MouseEvent mouseEvent) {
        cbfvolumecontextpane.setVisible(false);
    }

    public void onActiontxtfldcbflength(ActionEvent actionEvent) {
        txtfldcbfwidth.requestFocus();
    }

    public void onActiontxtfldcbfwidth(ActionEvent actionEvent) {
        txtfldcbfheight.requestFocus();
    }

    public void onActiontxtfldcbfheight(ActionEvent actionEvent) {
        btncbfaddtolabel.fire();
    }


    public void onActiontxtfldrange(ActionEvent actionEvent) {
        txtfldsize.requestFocus();
    }

    public void onActiontxtfldsize(ActionEvent actionEvent) {
        btnaddcbfvolumetocontext.fire();
    }

    public void onActiontxtfldsupplierprice(ActionEvent actionEvent) {
        txtflddistributorprice.requestFocus();
    }

    public void onActiontxtflddistributorprice(ActionEvent actionEvent) {
        txtfldmrp.requestFocus();
    }

    public void onActiontxtfldmrp(ActionEvent actionEvent) {
        btnaddimg.fire();
    }

    public void onActionbtnbackSelectBiscuitManage(MouseEvent mouseEvent) {
        deletebiscuitpane.setVisible(false);
        addbiscuitmidcontextpane.setVisible(false);
        editpricescontextpane.setVisible(false);
        navigatebtnscntexts.setVisible(true);
    }
    public void onActionbtnAddProduct(ActionEvent actionEvent) {
        loadBiscuitID();
        navigatebtnscntexts.setVisible(false);
        addbiscuitmidcontextpane.setVisible(true);
        txtfldbrand.requestFocus();
    }

    public void onActionChangePrices(ActionEvent actionEvent) {
        ubtnupdate.setDisable(true);
        navigatebtnscntexts.setVisible(false);
        editpricescontextpane.setVisible(true);
    }

    public void onActionbtnbackfromchangepricetonavigate(MouseEvent mouseEvent) {
        editpricescontextpane.setVisible(false);
        navigatebtnscntexts.setVisible(true);
    }

    public void txtfldbrandCheckRegax(KeyEvent keyEvent) {
        unlockSaveBtn();
        if(userValidate.checkEnteredEnglishLetters(txtfldbrand.getText())){
            txtfldbrand.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldbrand.requestFocus();
        }else{
            txtfldbrand.getStylesheets().add("Styles/button.css");
            txtfldbrand.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtfldrangeCheckRegax(KeyEvent keyEvent) {
        unlockSaveBtn();
        if(userValidate.checkEnteredEnglishLetters(txtfldrange.getText())){
            txtfldrange.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldrange.requestFocus();
        }else{
            txtfldrange.getStylesheets().add("Styles/button.css");
            txtfldrange.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtfldsizeCheckRegax(KeyEvent keyEvent) {
        unlockSaveBtn();
        if(userValidate.checkEnteredEnglishLettersWithNumbers(txtfldsize.getText())){
            txtfldsize.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldsize.requestFocus();
        }else{
            txtfldsize.getStylesheets().add("Styles/button.css");
            txtfldsize.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtfldsupplierpriceCheckRegax(KeyEvent keyEvent) {
        unlockSaveBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldsupplierprice.getText())){
            txtfldsupplierprice.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldsupplierprice.requestFocus();
        }else{
            txtfldsupplierprice.getStylesheets().add("Styles/button.css");
            txtfldsupplierprice.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtflddistributorpriceCheckRegax(KeyEvent keyEvent) {
        unlockSaveBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtflddistributorprice.getText())){
            txtflddistributorprice.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtflddistributorprice.requestFocus();
        }else{
            txtflddistributorprice.getStylesheets().add("Styles/button.css");
            txtflddistributorprice.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtfldmrpCheckRegax(KeyEvent keyEvent) {
        unlockSaveBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldmrp.getText())){
            txtfldmrp.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldmrp.requestFocus();
        }else{
            txtfldmrp.getStylesheets().add("Styles/button.css");
            txtfldmrp.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtfldcbflengthCheckRegax(KeyEvent keyEvent) {
        unlockAddBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldcbflength.getText())){
            txtfldcbflength.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldcbflength.requestFocus();
        }else{
            txtfldcbflength.getStylesheets().add("Styles/button.css");
            txtfldcbflength.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtfldcbfwidthCheckRegax(KeyEvent keyEvent) {
        unlockAddBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldcbfwidth.getText())){
            txtfldcbfwidth.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldcbfwidth.requestFocus();
        }else{
            txtfldcbfwidth.getStylesheets().add("Styles/button.css");
            txtfldcbfwidth.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void txtfldcbfheightCheckRegax(KeyEvent keyEvent) {
        unlockAddBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldcbfheight.getText())){
            txtfldcbfheight.setStyle(userValidate.cssStringDefaultTxtFldColor());
            notifyregaxchecktxt.setVisible(false);
            txtfldcbfheight.requestFocus();
        }else{
            txtfldcbfheight.getStylesheets().add("Styles/button.css");
            txtfldcbfheight.setStyle(userValidate.cssStringFocusedTxtFldColor());
            notifyregaxchecktxt.setVisible(true);
        }
    }

    public void onActiontxtfldbiscuitid(ActionEvent actionEvent) {
    }

    public void onActiontxtfldbrand(ActionEvent actionEvent) {
        txtfldrange.requestFocus();
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        if(productService.updateProductPrices(utxtfldbiscuitid.getText() , utxtfldsupplierprice.getText() , utxtflddistributorprice.getText() , utxtfldmrprice.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Product Updated Successfully.").show();
            tablbiscuits.getItems().clear();
            blist.clear();
            obbList.clear();
            loadDataToTable();
            utxtfldbiscuitid.clear();
            utxtfldsupplierprice.clear();
            utxtflddistributorprice.clear();
            utxtfldmrprice.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Updated,Tryagain!").show();
            utxtfldbiscuitid.requestFocus();
        }
    }

    public void utxtfldbiscuitidOnAction(ActionEvent actionEvent) {
        utxtfldsupplierprice.requestFocus();
    }

    public void utxtflddistributorpriceOnAction(ActionEvent actionEvent) {
        utxtfldmrprice.requestFocus();
    }

    public void utxtfldmrpriceOnAction(ActionEvent actionEvent) {
        ubtnupdate.fire();
    }

    public void utxtfldsupplierpriceOnAction(ActionEvent actionEvent) {
        utxtflddistributorprice.requestFocus();
    }

    public void utxtfldbiscuitidCheckRegax(KeyEvent keyEvent) {
        unlockUpdateBtn();
        if(userValidate.checkEnteredEnglishLettersWithNumbers(utxtfldbiscuitid.getText())){
            utxtfldbiscuitid.setStyle(userValidate.cssStringDefaultTxtFldColor());
            unotifyregaxchecktxt.setVisible(false);
            utxtfldbiscuitid.requestFocus();
        }else{
            utxtfldbiscuitid.getStylesheets().add("Styles/button.css");
            utxtfldbiscuitid.setStyle(userValidate.cssStringFocusedTxtFldColor());
            unotifyregaxchecktxt.setVisible(true);
        }
    }

    public void utxtflddistributorpriceCheckRegax(KeyEvent keyEvent) {
        unlockUpdateBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(utxtflddistributorprice.getText())){
            utxtflddistributorprice.setStyle(userValidate.cssStringDefaultTxtFldColor());
            unotifyregaxchecktxt.setVisible(false);
            utxtflddistributorprice.requestFocus();
        }else{
            utxtflddistributorprice.getStylesheets().add("Styles/button.css");
            utxtflddistributorprice.setStyle(userValidate.cssStringFocusedTxtFldColor());
            unotifyregaxchecktxt.setVisible(true);
        }
    }

    public void utxtfldmrpriceCheckRegax(KeyEvent keyEvent) {
        unlockUpdateBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(utxtfldmrprice.getText())){
            utxtfldmrprice.setStyle(userValidate.cssStringDefaultTxtFldColor());
            unotifyregaxchecktxt.setVisible(false);
            utxtfldmrprice.requestFocus();
        }else{
            utxtfldmrprice.getStylesheets().add("Styles/button.css");
            utxtfldmrprice.setStyle(userValidate.cssStringFocusedTxtFldColor());
            unotifyregaxchecktxt.setVisible(true);
        }
    }

    public void utxtfldsupplierpriceCheckRegax(KeyEvent keyEvent) {
        unlockUpdateBtn();
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(utxtfldsupplierprice.getText())){
            utxtfldsupplierprice.setStyle(userValidate.cssStringDefaultTxtFldColor());
            unotifyregaxchecktxt.setVisible(false);
            utxtfldsupplierprice.requestFocus();
        }else{
            utxtfldsupplierprice.getStylesheets().add("Styles/button.css");
            utxtfldsupplierprice.setStyle(userValidate.cssStringFocusedTxtFldColor());
            unotifyregaxchecktxt.setVisible(true);
        }
    }

    public void OnActionDelete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //close alert
        alert.setTitle("SevonHoldings System");
        alert.setHeaderText("Product Delete Confirmation");
        alert.setContentText("Are you sure you want to delete this product?");
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent() || result.get() == ButtonType.OK) {
            if(productService.deleteProductPK(dtxtflddelete.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Product deleted Successfully.").show();
                dtxtflddelete.clear();
                tablbiscuits.getItems().clear();
                blist.clear();
                obbList.clear();
                dtxtflddelete.clear();
                loadDataToTable();
            }else{
                new Alert(Alert.AlertType.WARNING,"Product not deleted!.").show();
                dtxtflddelete.requestFocus();
            }
        }
    }

    public void OnActiondtxtfldbiscuitid(ActionEvent actionEvent) {
    }

    public void dtxtfldbiscuitidcheckregax(KeyEvent keyEvent) {
        if(userValidate.checkEnteredEnglishLettersWithNumbers(dtxtflddelete.getText())){
            dtxtflddelete.setStyle(userValidate.cssStringDefaultTxtFldColor());
            btndelete.setDisable(false);
            dnotifyregaxchecktxt.setVisible(false);
            dtxtflddelete.requestFocus();
        }else{
            dtxtflddelete.getStylesheets().add("Styles/button.css");
            dtxtflddelete.setStyle(userValidate.cssStringFocusedTxtFldColor());
            btndelete.setDisable(true);
            dnotifyregaxchecktxt.setVisible(true);
        }
    }

    public void OnActionDeletebutonmain(ActionEvent actionEvent) {
        addbiscuitmidcontextpane.setVisible(false);
        editpricescontextpane.setVisible(false);
        navigatebtnscntexts.setVisible(false);
        deletebiscuitpane.setVisible(true);
    }

    private void unlockSaveBtn(){
        if(userValidate.checkEnteredEnglishLetters(txtfldbrand.getText())&&txtfldbrand.getLength()!=0){
            if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldsupplierprice.getText())&&txtfldsupplierprice.getLength()!=0){
                if(userValidate.checkEnteredEnglishLetters(txtfldrange.getText())&&txtfldrange.getLength()!=0){
                    if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtflddistributorprice.getText())&&txtflddistributorprice.getLength()!=0){
                        if(userValidate.checkEnteredEnglishLettersWithNumbers(txtfldsize.getText())&&txtfldsize.getLength()!=0){
                            if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldmrp.getText())&&txtfldmrp.getLength()!=0){
                                if(txtfldtotalcbfvolume.getLength()!=0 && txtfldimgpath.getLength()!=0){
                                    btnsave.setDisable(false);
                                    notifyregaxchecktxt.setVisible(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private void unlockAddBtn(){
        if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldcbflength.getText())&&txtfldcbflength.getLength()!=0){
            if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldcbfwidth.getText())&&txtfldcbfwidth.getLength()!=0){
                if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(txtfldcbfheight.getText())&&txtfldcbfheight.getLength()!=0){
                    btncbfaddtolabel.setDisable(false);
                }
            }
        }
    }
    private void unlockUpdateBtn(){
        if(userValidate.checkEnteredEnglishLettersWithNumbers(utxtfldbiscuitid.getText())&&utxtfldbiscuitid.getLength()!=0){
            if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(utxtfldsupplierprice.getText())&&utxtfldsupplierprice.getLength()!=0){
                if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(utxtflddistributorprice.getText())&&utxtflddistributorprice.getLength()!=0){
                    if(userValidate.checkEnteredPositiveDecimalOrNormalNumbers(utxtfldmrprice.getText())&&utxtfldmrprice.getLength()!=0){
                        ubtnupdate.setDisable(false);
                    }
                }
            }
        }
    }

    public void btnalltargetreportOnAction(ActionEvent actionEvent) {

/*
        HashMap hm=new HashMap<>();
        InputStream inputStream = this.getClass().getResourceAsStream("jreports/AllBiscuitsJR.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, hm, DBConnection.getDbConnection().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);//--> print karanawanam.
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } */
    }




}
