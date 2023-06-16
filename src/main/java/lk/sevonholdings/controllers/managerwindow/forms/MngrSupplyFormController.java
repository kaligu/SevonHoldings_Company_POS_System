package lk.sevonholdings.controllers.managerwindow.forms;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.sevonholdings.dto.*;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.ProductService;
import lk.sevonholdings.service.custom.SupplierService;
import lk.sevonholdings.view.custom.Impl.UserValidateImpl;
import lk.sevonholdings.view.custom.Uservalidate;
import lk.sevonholdings.view.tm.ProductsTm;
import lk.sevonholdings.view.tm.ReturnsToMalibanTm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MngrSupplyFormController {
    public AnchorPane panenavigatebutons;
    public AnchorPane selectpanebiscuit;
    public ScrollPane selecttblpane;
    public TableView tablbiscuits;
    public TableColumn colbiscuitsid;
    public TableColumn colpicture;
    public TableColumn colrange;
    public TableColumn colbrandx;
    public TableColumn colsizex;
    public TableColumn colcbfvolume;
    public TableColumn colsupplierpric;
    public TableColumn coldistributorpric;
    public TableColumn colmrp;
    public TextField txtfldqty;
    public Text txtorderid;
    public TableView createtbl;
    public TableColumn ccolorderid;
    public TableColumn ccolbiscuitno;
    public TableColumn ccolbrand;
    public TableColumn ccolsize;
    public TableColumn ccolqty;
    public TableColumn ccolsuppliercost;
    public TableColumn ccoldistributorcost;
    public TableColumn ccolmrp;
    public Text mrplbl;
    public Text distributorcostlbl;
    public Text suppliercostlbl;
    public AnchorPane confirmcreateorderwindow;

    public TableView tablbiscuits1;
    public TableColumn colbiscuitsid1;
    public TableColumn colpicture1;
    public TableColumn colrange1;
    public TableColumn colbrandx1;
    public TableColumn colsizex1;
    public TableColumn colcbfvolume1;
    public TableColumn colsupplierpric1;
    public TableColumn coldistributorpric1;
    public TableColumn colmrp1;
    public TextField txtfldqty1;
    public Text txtorderid1;
    public TableView createtbl1;
    public TableColumn ccolorderid1;
    public TableColumn ccolbiscuitno1;
    public TableColumn ccolbrand1;
    public TableColumn ccolsize1;
    public TableColumn ccolqty1;
    public TableColumn ccolsuppliercost1;
    public TableColumn ccoldistributorcost1;
    public TableColumn ccolmrp1;
    public Text mrplbl1;
    public Text distributorcostlbl1;
    public Text suppliercostlbl1;
    public AnchorPane confirmcreateorderwindow1;
    public AnchorPane panecreateorder;
    public AnchorPane paneorderview;
    public AnchorPane panereturnview;
    public AnchorPane panecreatereturn;

    List<ProductDTO> barray;
    int selectedbiscuitindex;
    int selectedbiscuitindex1;

    ObservableList<SupplierOrderDetailDTO> obbListcretaingtbl;
    ArrayList<SupplierOrderDetailDTO> cbarray;

    ObservableList<ReturnsToMalibanTm> obbListcretaingtbl1;
    ArrayList<ReturnsToMalibanTm> cbarray1;

    Uservalidate userValidate;
    ProductService productService;
    SupplierService supplierService;

    public void initialize(){
        this.productService= ServiceFactory.getInstance().getService(ServiceTypes.PRODUCT); //create instance to access product service
        this.supplierService= ServiceFactory.getInstance().getService(ServiceTypes.Supplier); //create instance to access product service
        visibleFalseAllPanes();
        panenavigatebutons.setVisible(true);
        userValidate = new UserValidateImpl();
        loadMalibanOrderID();
        //loadMalibanOrderID1();
        cbarray = new ArrayList<>();
        cbarray1 = new ArrayList<>();
        obbListcretaingtbl = FXCollections.observableArrayList();
        obbListcretaingtbl1 = FXCollections.observableArrayList();

        colbiscuitsid1.setCellValueFactory(new PropertyValueFactory<>("BiscuitsNo"));
        colpicture1.setCellValueFactory(new PropertyValueFactory<>("ImagePath"));
        colrange1.setCellValueFactory(new PropertyValueFactory<>("BiscuitRange"));
        colbrandx1.setCellValueFactory(new PropertyValueFactory<>("BiscuitBrand"));
        colsizex1.setCellValueFactory(new PropertyValueFactory<>("size"));
        colcbfvolume1.setCellValueFactory(new PropertyValueFactory<>("CBFVolume"));
        colsupplierpric1.setCellValueFactory(new PropertyValueFactory<>("SupplierPrice"));
        coldistributorpric1.setCellValueFactory(new PropertyValueFactory<>("DistributorPrice"));
        colmrp1.setCellValueFactory(new PropertyValueFactory<>("MRPrice"));

        ccolorderid1.setCellValueFactory(new PropertyValueFactory<>("ReturnId"));
        ccolbiscuitno1.setCellValueFactory(new PropertyValueFactory<>("BiscuitNo"));
        ccolbrand1.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        ccolsize1.setCellValueFactory(new PropertyValueFactory<>("Size"));
        ccolqty1.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        ccolsuppliercost1.setCellValueFactory(new PropertyValueFactory<>("SupplierCost"));
        ccoldistributorcost1.setCellValueFactory(new PropertyValueFactory<>("DistributorCost"));
        ccolmrp1.setCellValueFactory(new PropertyValueFactory<>("MRP"));

        colbiscuitsid.setCellValueFactory(new PropertyValueFactory<>("BiscuitsNo"));
        colpicture.setCellValueFactory(new PropertyValueFactory<>("ImagePath"));
        colrange.setCellValueFactory(new PropertyValueFactory<>("BiscuitRange"));
        colbrandx.setCellValueFactory(new PropertyValueFactory<>("BiscuitBrand"));
        colsizex.setCellValueFactory(new PropertyValueFactory<>("size"));
        colcbfvolume.setCellValueFactory(new PropertyValueFactory<>("CBFVolume"));
        colsupplierpric.setCellValueFactory(new PropertyValueFactory<>("SupplierPrice"));
        coldistributorpric.setCellValueFactory(new PropertyValueFactory<>("DistributorPrice"));
        colmrp.setCellValueFactory(new PropertyValueFactory<>("MRPrice"));

        ccolorderid.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        ccolbiscuitno.setCellValueFactory(new PropertyValueFactory<>("BiscuitNo"));
        ccolbrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        ccolsize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        ccolqty.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        ccolsuppliercost.setCellValueFactory(new PropertyValueFactory<>("SupplierCost"));
        ccoldistributorcost.setCellValueFactory(new PropertyValueFactory<>("DistributorCost"));
        ccolmrp.setCellValueFactory(new PropertyValueFactory<>("MRP"));

        loadDataToTable();
       // loadDataToTable1();
    }

    private void visibleFalseAllPanes(){
        panenavigatebutons.setVisible(false);
        panecreateorder.setVisible(false);
        paneorderview.setVisible(false);
        panereturnview.setVisible(false);
        panecreatereturn.setVisible(false);
    }

    private void loadMalibanOrderID1() {
     /*   String id = null;
        id = supplyService.GetLastMalibanReturntID();
        String[] array = id.split("-");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        txtorderid1.setText("MR-"+finalizeOrderId);*/
    }

    private void loadDataToTable1() {
     /*   ObservableList<ProductsTm> obbList = FXCollections.observableArrayList();
        barray= productService.findAllProducts();
        for (ProductDTO c : barray) {
            FileInputStream input = null;
            try {
                input = new FileInputStream(c.getImagePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image img = new Image(input);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(120);
            imageView.setFitHeight(60);
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
        }
        tablbiscuits1.setItems(obbList);*/
    }

    private void loadMalibanOrderID() {
       String id = null;
        id = supplierService.GetLastMalibanOrderID();
        String[] array = id.split("-");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        txtorderid.setText("MO-"+finalizeOrderId);
    }

    private void loadDataToTable() {
        ObservableList<ProductsTm> obbList = FXCollections.observableArrayList();
        barray= productService.findAllProducts();
        for (ProductDTO c : barray) {
            FileInputStream input = null;
            try {
                input = new FileInputStream(c.getImagePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image img = new Image(input);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(120);
            imageView.setFitHeight(60);
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
        }
        tablbiscuits.setItems(obbList);

    }

    public void btncancelorderconfirmwindowOnaction(ActionEvent actionEvent) {
        confirmcreateorderwindow.setVisible(false);
    }

    public void btnOrderCretateNPrintOnAction(ActionEvent actionEvent) throws SQLException {
        SupplierOrderDTO om = new SupplierOrderDTO(
                txtorderid.getText(),
                userValidate.getSQLDateNow(),
                userValidate.getSQLTimeNow(),
                Double.parseDouble(suppliercostlbl.getText()),
                Double.parseDouble(distributorcostlbl.getText()),
                Double.parseDouble(mrplbl.getText()),
                "NotApproved"
        );

        if( supplierService.insertAllDataIntoMalibanOrdersDB(cbarray,om) ){
            new Alert(Alert.AlertType.CONFIRMATION, "New Order Added!").show();
            confirmcreateorderwindow.setVisible(false);
            loadMalibanOrderID();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Order not created successfully!").show();
        }

    }

    public void btnOrderCreateOnlyOnAction(ActionEvent actionEvent) throws SQLException {
        SupplierOrderDTO om = new SupplierOrderDTO(
                txtorderid.getText(),
                userValidate.getSQLDateNow(),
                userValidate.getSQLTimeNow(),
                Double.parseDouble(suppliercostlbl.getText()),
                Double.parseDouble(distributorcostlbl.getText()),
                Double.parseDouble(mrplbl.getText()),
                "NotApproved"
        );

        if( supplierService.insertAllDataIntoMalibanOrdersDB(cbarray,om) ){
            new Alert(Alert.AlertType.CONFIRMATION, "New Order Added!").show();
            confirmcreateorderwindow.setVisible(false);
            loadMalibanOrderID();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Order not created successfully!").show();
        }
    }

    public void btnaddtolistOnAction(ActionEvent actionEvent) {
        createtbl.getItems().clear();
        SupplierOrderDetailDTO otm = new SupplierOrderDetailDTO(
                txtorderid.getText(),
                barray.get(selectedbiscuitindex).getBiscuitsNo(),
                barray.get(selectedbiscuitindex).getBiscuitRange(),
                barray.get(selectedbiscuitindex).getBiscuitBrand(),
                barray.get(selectedbiscuitindex).getSize(),
                Integer.parseInt(txtfldqty.getText()),
                (barray.get(selectedbiscuitindex).getSupplierPrice()*Double.parseDouble(txtfldqty.getText())),
                (barray.get(selectedbiscuitindex).getDistributorPrice()*Double.parseDouble(txtfldqty.getText())),
                (barray.get(selectedbiscuitindex).getMRPrice()*Double.parseDouble(txtfldqty.getText()))
        );
        cbarray.add(otm);
        obbListcretaingtbl.setAll(cbarray);
        createtbl.setItems(obbListcretaingtbl);
        double sc=0;
        double dc=0;
        double mrp=0;
        for(SupplierOrderDetailDTO t:cbarray){
            sc=sc+t.getSupplierCost();
            dc=dc+t.getDistributorCost();
            mrp=mrp+t.getMRP();
            suppliercostlbl.setText(String.valueOf(sc));
            distributorcostlbl.setText(String.valueOf(dc));
            mrplbl.setText(String.valueOf(mrp));
        }
    }

    public void backtonhomeOnAction(MouseEvent mouseEvent) {
        visibleFalseAllPanes();
        panenavigatebutons.setVisible(true);
    }

    public void btncreateNsendOnAction(ActionEvent actionEvent) {
    }

    public void btnClearAllRowsOnAction(ActionEvent actionEvent) {
        cbarray.clear();
        obbListcretaingtbl.setAll(cbarray);
        createtbl.setItems(obbListcretaingtbl);
    }

    public void btnclearselectedrowOnAction(ActionEvent actionEvent) {
        cbarray.remove(createtbl.getSelectionModel().getSelectedIndex());
        obbListcretaingtbl.setAll(cbarray);
        createtbl.setItems(obbListcretaingtbl);
    }

    public void tblctOnAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void MainViewReturnListOnAction(ActionEvent actionEvent) {
        visibleFalseAllPanes();
        paneorderview.setVisible(true);
    }

    public void MainDeleteReturnListOnAction(ActionEvent actionEvent) {
    }

    public void MainModifyReturnListOnAction(ActionEvent actionEvent) {
    }

    public void MainCreateReturnListOnAction(ActionEvent actionEvent) {
        visibleFalseAllPanes();
        panecreatereturn.setVisible(true);
    }

    public void MainVieworderListOnaction(ActionEvent actionEvent) {
        visibleFalseAllPanes();
        paneorderview.setVisible(true);
    }

    public void MainDeleteOrderListOnAction(ActionEvent actionEvent) {
    }

    public void MainModifyOrderlistOnAction(ActionEvent actionEvent) {
    }

    public void mainCreateOrderListOnAcition(ActionEvent actionEvent) {
        visibleFalseAllPanes();
        panecreateorder.setVisible(true);
    }

    public void selectbiscuittblOnAction(MouseEvent mouseEvent) {
        selectedbiscuitindex=tablbiscuits.getSelectionModel().getSelectedIndex();
    }

    public void CreatedMalibanOrderSaveOnDB(ActionEvent actionEvent) {
        confirmcreateorderwindow.setVisible(true);
    }

    public void selectgOnActionwtablbiscuits(MouseEvent mouseEvent) {
    }

    public void tblctOnAction1(SortEvent<TableView> tableViewSortEvent) {
    }

    public void btnclearselectedrowOnAction1(ActionEvent actionEvent) {
        cbarray1.remove(createtbl1.getSelectionModel().getSelectedIndex());
        obbListcretaingtbl1.setAll(cbarray1);
        createtbl1.setItems(obbListcretaingtbl1);
    }

    public void btnClearAllRowsOnAction1(ActionEvent actionEvent) {
        cbarray1.clear();
        obbListcretaingtbl1.setAll(cbarray1);
        createtbl.setItems(obbListcretaingtbl1);
    }

    public void CreatedMalibanOrderSaveOnDB1(ActionEvent actionEvent) {
        confirmcreateorderwindow1.setVisible(true);
    }

    public void selectbiscuittblOnAction1(MouseEvent mouseEvent) {
        selectedbiscuitindex1=tablbiscuits1.getSelectionModel().getSelectedIndex();
    }

    public void btnaddtolistOnAction1(ActionEvent actionEvent) {
        ReturnsToMalibanTm otm = new ReturnsToMalibanTm(
                txtorderid1.getText(),
                barray.get(selectedbiscuitindex).getBiscuitsNo(),
                barray.get(selectedbiscuitindex).getBiscuitRange(),
                barray.get(selectedbiscuitindex).getBiscuitBrand(),
                barray.get(selectedbiscuitindex).getSize(),
                Integer.parseInt(txtfldqty1.getText()),
                (barray.get(selectedbiscuitindex).getSupplierPrice()*Double.parseDouble(txtfldqty1.getText())),
                (barray.get(selectedbiscuitindex).getDistributorPrice()*Double.parseDouble(txtfldqty1.getText())),
                (barray.get(selectedbiscuitindex).getMRPrice()*Double.parseDouble(txtfldqty1.getText()))
        );
        cbarray1.add(otm);
        obbListcretaingtbl1.setAll(cbarray1);
        createtbl1.setItems(obbListcretaingtbl1);
        double sc=0;
        double dc=0;
        double mrp=0;
        for(ReturnsToMalibanTm t:cbarray1){
            sc=sc+t.getSupplierCost();
            dc=dc+t.getDistributorCost();
            mrp=mrp+t.getMRP();
            suppliercostlbl1.setText(String.valueOf(sc));
            distributorcostlbl1.setText(String.valueOf(dc));
            mrplbl1.setText(String.valueOf(mrp));
        }
    }

    public void btnOrderCreateOnlyOnAction1(ActionEvent actionEvent) {
        OrdersToMalibanDTO om = new OrdersToMalibanDTO(
                txtorderid1.getText(),
                userValidate.getSQLDateNow(),
                userValidate.getSQLTimeNow(),
                Double.parseDouble(suppliercostlbl1.getText()),
                Double.parseDouble(distributorcostlbl1.getText()),
                Double.parseDouble(mrplbl1.getText()),
                "NotApproved"
        );

      /*  try {
            if( MalibanSupplyModel.insertAllDataIntoMalibanReturnsDB(cbarray1,om) ){
                new Alert(Alert.AlertType.CONFIRMATION, "New Order Added!").show();
                confirmcreateorderwindow1.setVisible(false);
                loadMalibanOrderID1();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void btnOrderCretateNPrintOnAction1(ActionEvent actionEvent) {
        ReturnsToMalibanDTO om = new ReturnsToMalibanDTO(
                txtorderid1.getText(),
                userValidate.getSQLDateNow(),
                userValidate.getSQLTimeNow(),
                Double.parseDouble(suppliercostlbl1.getText()),
                Double.parseDouble(distributorcostlbl1.getText()),
                Double.parseDouble(mrplbl1.getText()),
                "NotApproved"
        );
/*
        try {
            if( MalibanSupplyModel.insertAllDataIntoMalibanReturnsDB(cbarray1,om) ){
                new Alert(Alert.AlertType.CONFIRMATION, "New Order Added!").show();
                confirmcreateorderwindow1.setVisible(false);
                loadMalibanOrderID1();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void btncancelorderconfirmwindowOnaction1(ActionEvent actionEvent) {
        confirmcreateorderwindow1.setVisible(false);
    }
}
