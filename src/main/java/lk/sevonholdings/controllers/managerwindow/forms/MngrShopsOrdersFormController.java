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
import lk.sevonholdings.dto.ProductDTO;
import lk.sevonholdings.view.custom.Impl.UserValidateImpl;
import lk.sevonholdings.view.custom.Uservalidate;
import lk.sevonholdings.view.tm.OrderToMalibanTm;
import lk.sevonholdings.view.tm.ProductsTm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MngrShopsOrdersFormController {
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

    ArrayList<ProductDTO> barray;
    int selectedbiscuitindex;
    int selectedbiscuitindex1;

    ObservableList<OrderToMalibanTm> obbListcretaingtbl;
    ArrayList<OrderToMalibanTm> cbarray;

    Uservalidate userValidate;

    public void initialize(){
        visibleFalseAllPanes();
        panenavigatebutons.setVisible(true);
        userValidate = new UserValidateImpl();
        //loadMalibanOrderID();
        loadMalibanOrderID1();
        cbarray = new ArrayList<>();
        obbListcretaingtbl = FXCollections.observableArrayList();


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

        // loadDataToTable();
        loadDataToTable1();
    }

    private void visibleFalseAllPanes(){
        panenavigatebutons.setVisible(false);
        panecreateorder.setVisible(false);
        paneorderview.setVisible(false);;
    }

    private void loadMalibanOrderID1() {
      /*  String id = null;
        try {
            id =OrdersFromShopsModel.GetLastMalibanOrdertID();//return id
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] array = id.split("-");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        txtorderid.setText("OS-"+finalizeOrderId);*/
    }

    private void loadDataToTable1() {
        ObservableList<ProductsTm> obbList = FXCollections.observableArrayList();
     /*   try {
            barray= BiscuitModel.loadAllBiscuits();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
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

    private void loadMalibanOrderID() {
     /*   String id = null;
        try {
            id = OrdersFromShopsModel.GetLastMalibanOrdertID();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] array = id.split("-");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        txtorderid.setText("OS-"+finalizeOrderId);*/
    }

    public void btncancelorderconfirmwindowOnaction(ActionEvent actionEvent) {
        confirmcreateorderwindow.setVisible(false);
    }

    public void btnOrderCretateNPrintOnAction(ActionEvent actionEvent) {
        /*
        Orderfromshopto om = new Orderfromshopto(
                txtorderid.getText(),
                "ggg",
                "vvv",
                "vvv",
                "vv",
                userValidate.getSQLDateNow(),
                userValidate.getSQLTimeNow(),
                userValidate.getSQLDateNow(),
                "NotApproved"
        );

        try {
            if( MalibanSupplyModel.insertAllDataIntoMalibanOrdersDB(cbarray,om) ){
                new Alert(Alert.AlertType.CONFIRMATION, "New Order Added!").show();
                confirmcreateorderwindow.setVisible(false);
                loadMalibanOrderID();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        */
    }

    public void btnOrderCreateOnlyOnAction(ActionEvent actionEvent) {
     /*   Orderfromshopto om = new Orderfromshopto(
                txtorderid.getText(),
                "fff",
                "ggg",
                "vvv",
                "ggg",
                Date.valueOf("2008-2-15"),
                Time.valueOf("04:11:12"),
                Date.valueOf("2008-2-15"),
                "ffff"
        );
    /*    try {
            if( OrdersFromShopsModel.insertAllDataIntoMalibanOrdersDB(cbarray,om) ){
                new Alert(Alert.AlertType.CONFIRMATION, "New Order Added!").show();
                confirmcreateorderwindow.setVisible(false);
                loadMalibanOrderID();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void btnaddtolistOnAction(ActionEvent actionEvent) {
        OrderToMalibanTm otm = new OrderToMalibanTm(
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
        for(OrderToMalibanTm t:cbarray){
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


    public void MainVieworderListOnaction(ActionEvent actionEvent) {
        visibleFalseAllPanes();
        paneorderview.setVisible(true);
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

    public void getOrderOnaction(ActionEvent actionEvent) {
     /*   final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        Stage stage = new Stage();
        stage.setTitle("report");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();*/
    }

 /*   private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HashMap hm=new HashMap<>();
                InputStream inputStream = null;
                JasperPrint jasperPrint=null;
                inputStream = this.getClass().getClassLoader().getResourceAsStream("lk/sevonholdings/reports/AllViewShopOrder.jrxml");
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

    public void getOrderdetailOnAction(ActionEvent actionEvent) {
       /* final SwingNode swingNode = new SwingNode();
        createSwingContent1(swingNode);
        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        Stage stage = new Stage();
        stage.setTitle("Swing in JavaFX");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();*/
    }
/*
    private void createSwingContent1(final SwingNode swingNode) {
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HashMap hm=new HashMap<>();
                InputStream inputStream = null;
                JasperPrint jasperPrint=null;
                inputStream = this.getClass().getClassLoader().getResourceAsStream("lk/sevonholdings/reports/AllViewOfJasperReportDetailsJR.jrxml");
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
}
