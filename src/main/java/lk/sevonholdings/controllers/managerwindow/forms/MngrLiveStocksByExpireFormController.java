package lk.sevonholdings.controllers.managerwindow.forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.WarehouseService;
import lk.sevonholdings.view.tm.SubManagerWindowLiveStocksByExpireControllerTm1;
import lk.sevonholdings.view.tm.SubManagerWindowLiveStocksByExpireControllerTm2;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class MngrLiveStocksByExpireFormController {
    public TableView smwlsbrctbl;
    public TableColumn colbiscuitno;
    public TableColumn colbiscuitimage;
    public TableColumn colbrand;
    public TableColumn colsize;
    public TableColumn colqty;
    public TableColumn colmfd;
    public TableColumn colexd;
    public TableColumn colprogress;
    public TableColumn colroomid;
    public TableColumn colremainingid;
    WarehouseService warehouseService;

    public void initialize() throws SQLException, ClassNotFoundException, InterruptedException {
        this.warehouseService= ServiceFactory.getInstance().getService(ServiceTypes.WAREHOUSE);

        colbiscuitno.setCellValueFactory(new PropertyValueFactory<>("Biscuit_No"));
        colbiscuitimage.setCellValueFactory(new PropertyValueFactory<>("ImagePath"));
        colbrand.setCellValueFactory(new PropertyValueFactory<>("Biscuit_Brand"));
        colsize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("AvailableQty"));
        colmfd.setCellValueFactory(new PropertyValueFactory<>("MFD"));
        colexd.setCellValueFactory(new PropertyValueFactory<>("EXD"));
        colprogress.setCellValueFactory(new PropertyValueFactory<>("Remaining_Value_Pbar"));
        colroomid.setCellValueFactory(new PropertyValueFactory<>("Room_Id"));
        colremainingid.setCellValueFactory(new PropertyValueFactory<>("Remaining_days_expired"));

        Thread ghk = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    smwlsbrcLoadData();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        ghk.start();

    }

    private void smwlsbrcLoadData() throws SQLException, ClassNotFoundException {
        ArrayList<SubManagerWindowLiveStocksByExpireControllerTm1> warray=warehouseService.showLiveStocksByExpire() ;
        ObservableList<SubManagerWindowLiveStocksByExpireControllerTm2> nobl = FXCollections.observableArrayList();
        Task<SubManagerWindowLiveStocksByExpireControllerTm2> task1 = new Task<SubManagerWindowLiveStocksByExpireControllerTm2>() {
            @Override
            protected SubManagerWindowLiveStocksByExpireControllerTm2 call() throws Exception {
                int count = 0;
                int countprogress=0;
                for (SubManagerWindowLiveStocksByExpireControllerTm1 w:warray){
                    FileInputStream input = new FileInputStream(w.getImagePath());
                    Image img = new Image(input);
                    ImageView imageView = new ImageView(img);
                    imageView.setFitWidth(180);
                    imageView.setFitHeight(85);

                    Thread.sleep(50);

                    ProgressBar pbar = new ProgressBar();
                    if(w.getRemaining_Value_Pbar()==1){
                        pbar.setStyle( "-fx-accent: red;");
                        pbar.setProgress(w.getRemaining_Value_Pbar());
                    }else if(w.getRemaining_Value_Pbar()>0.8){
                        pbar.setStyle( "-fx-accent: green;");
                        pbar.setProgress(w.getRemaining_Value_Pbar());
                    }else if(w.getRemaining_Value_Pbar()<0.2){
                        pbar.setStyle( "-fx-accent: yellow;");
                        pbar.setProgress(w.getRemaining_Value_Pbar());
                    }else{
                        pbar.setStyle( "-fx-accent: blue;");
                        pbar.setProgress(w.getRemaining_Value_Pbar());
                    }

                    SubManagerWindowLiveStocksByExpireControllerTm2 wt = new SubManagerWindowLiveStocksByExpireControllerTm2(
                            w.getBiscuit_No(),
                            imageView,
                            w.getBiscuit_Brand(),
                            w.getSize(),
                            w.getAvailableQty(),

                            w.getMFD(),
                            w.getEXD(),
                            w.getRoom_Id(),
                            w.getRemaining_days_expired(),
                            pbar
                    );
                    countprogress++;
                    updateProgress(countprogress, warray.size());
                    nobl.add(wt);
                    smwlsbrctbl.setItems(nobl);
                    updateValue(wt);
                }
                return null ;
            }
        };
        new Thread(task1).start();

    }
}
