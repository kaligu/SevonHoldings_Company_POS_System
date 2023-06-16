package lk.sevonholdings.controllers.managerwindow.forms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lk.sevonholdings.dto.WarehouseDTO;
import lk.sevonholdings.service.ServiceFactory;
import lk.sevonholdings.service.ServiceTypes;
import lk.sevonholdings.service.custom.WarehouseService;
import lk.sevonholdings.view.tm.LiveStocksByRoomsUsingRoomIdTm1;
import lk.sevonholdings.view.tm.LiveStocksByRoomsUsingRoomIdTm2;
import lk.sevonholdings.view.tm.PopupWarehouseRoomTm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MngrLiveStocksByRoomFormController {
    public AnchorPane smwlsbrcmaincontext;
    public AnchorPane smwlsbrcloadingcontext;
    public ProgressBar smwlsbrcprogrsbar;
    public HBox hbox1pbar;
    public HBox hbox2pbar;
    public HBox hbox3pbar;
    public HBox hbox4pbar;
    public HBox hbox5pbar;
    public HBox hbox1lbl;
    public HBox hbox2lbl;
    public HBox hbox3lbl;
    public HBox hbox4lbl;
    public HBox hbox5lbl;
    public AnchorPane smwlsbrcpopupcontext;
    public TableView smwlsbrctbl;
    public TableColumn colbiscuitno;
    public TableColumn colbiscuitimage;
    public TableColumn colbrand;
    public TableColumn colsize;
    public TableColumn colqty;
    public TableColumn colmfd;
    public TableColumn colexd;

    private ArrayList<ProgressBar> pbararray = new ArrayList<>();
    private ArrayList<Label> lblarray = new ArrayList<>();
    private ArrayList<String> lblidarray = new ArrayList<>();
    //private ArrayList<PopupWarehouseRoomTm> wtarray;
    private int count;
    WarehouseService warehouseService;

    public void initialize() throws SQLException, ClassNotFoundException, InterruptedException {
        this.warehouseService= ServiceFactory.getInstance().getService(ServiceTypes.WAREHOUSE);

        colbiscuitno.setCellValueFactory(new PropertyValueFactory<>("Biscuit_No"));
        colbiscuitimage.setCellValueFactory(new PropertyValueFactory<>("ImagePath"));
        colsize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("AvailableQty"));
        colexd.setCellValueFactory(new PropertyValueFactory<>("EXD"));
        colmfd.setCellValueFactory(new PropertyValueFactory<>("MFD"));
        colbrand.setCellValueFactory(new PropertyValueFactory<>("Biscuit_Brand"));

        smwlsbrcLoadData();
    }

    void smwlsbrcLoadData() throws SQLException, ClassNotFoundException, InterruptedException {
        smwlsbrcloadingcontext.setVisible(true);
        smwlsbrcmaincontext.setVisible(true);
        List<WarehouseDTO> warray = warehouseService.findAllWarehouse();
        Task<PopupWarehouseRoomTm> task = new Task<PopupWarehouseRoomTm>() {
            @Override
            protected PopupWarehouseRoomTm call() throws Exception {
                int count = 0;
                int countprogress=0;
                for (WarehouseDTO w:warray){
                    Thread.sleep(400);
                    Label lbl = new Label();
                    ProgressBar pbar = new ProgressBar();
                    PopupWarehouseRoomTm wt = new PopupWarehouseRoomTm(
                            w.getRoom_Id(),
                            w.getDescription(),
                            w.getMaximum_Room_Volume(),
                            w.getFilled_Room_Volume(),
                            w.getAvailability(),
                            pbar,
                            lbl
                    );
                    countprogress++;
                    updateProgress(countprogress, warray.size());
                    updateValue(wt);
                }
                return null ;
            }
        };
        smwlsbrcprogrsbar.progressProperty().bind(task.progressProperty());
        task.valueProperty().addListener(new ChangeListener<PopupWarehouseRoomTm>() {
            @Override
            public void changed(ObservableValue<? extends PopupWarehouseRoomTm> observable, PopupWarehouseRoomTm oldValue, PopupWarehouseRoomTm newValue) {
                if(newValue!=null){
                    setPbarToArray(newValue , newValue.getRoom_Id() , newValue.getMaximum_Room_Volume() , newValue.getFilled_Room_Volume() , newValue.getAvailability() );
                }

            }
        });
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                smwlsbrcloadingcontext.setVisible(false);
            }
        });

        new Thread(task).start();

       /* smwlsbrcloadingcontext.setVisible(true);
        smwlsbrcmaincontext.setVisible(true);

        ArrayList<WarehouseRoom> warray= WarehouseRoomModel.loadAllRooms();
        Task<PopupWarehouseRoomTm> task = new Task<PopupWarehouseRoomTm>() {
            @Override
            protected PopupWarehouseRoomTm call() throws Exception {
                int count = 0;
                int countprogress=0;
                for (WarehouseRoom w:warray){
                    Thread.sleep(400);
                    Label lbl = new Label();
                    ProgressBar pbar = new ProgressBar();
                    PopupWarehouseRoomTm wt = new PopupWarehouseRoomTm(
                            w.getRoom_Id(),
                            w.getDescription(),
                            w.getMaximum_Room_Volume(),
                            w.getFilled_Room_Volume(),
                            w.getAvailability(),
                            pbar,
                            lbl
                    );
                    countprogress++;
                    updateProgress(countprogress, warray.size());
                    updateValue(wt);
                }
                return null ;
            }
        };
        smwlsbrcprogrsbar.progressProperty().bind(task.progressProperty());
        task.valueProperty().addListener(new ChangeListener<PopupWarehouseRoomTm>() {
            @Override
            public void changed(ObservableValue<? extends PopupWarehouseRoomTm> observable, PopupWarehouseRoomTm oldValue, PopupWarehouseRoomTm newValue) {
                if(newValue!=null){
                    setPbarToArray(newValue , newValue.getRoom_Id() , newValue.getMaximum_Room_Volume() , newValue.getFilled_Room_Volume() , newValue.getAvailability() );
                }

            }
        });
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                smwlsbrcloadingcontext.setVisible(false);
            }
        });

        new Thread(task).start(); */

    }

    private void setPbarToArray(PopupWarehouseRoomTm w, String id , double max , double fill , String availability){
        lblarray.add(new Label(id));
        lblarray.get(count).setStyle( "-fx-opacity:1;-fx-font-size:18; -fx-border-color:black;-fx-text-alignment:center;-fx-text-fill:white; -fx-pref-width:80; -fx-pref-height:120;-fx-max-height:126; -fx-max-width:80; -fx-border-width:1;");
        pbararray.add(new ProgressBar(fill/max));
        if(pbararray.get(count).progressProperty().getValue()<0.2){
            // lblarray.get(count).setStyle( "-fx-background-color:black;-fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-max-height:126; -fx-max-width:80; -fx-border-width:2;");
            pbararray.get(count).setStyle( "-fx-accent: red;-fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-max-height:126; -fx-max-width:80; -fx-border-width:1;");
        }else if(pbararray.get(count).progressProperty().getValue()>0.8){
            pbararray.get(count).setStyle( " -fx-accent: green;-fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-max-height:126; -fx-max-width:80; -fx-border-width:1;");
        }else{
            pbararray.get(count).setStyle( " -fx-accent: blue;-fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-max-height:126; -fx-max-width:80; -fx-border-width:1;");
        }

        //smwlsbrcpopupcontext.setVisible(false);

        Image image = new Image("assets/ZSj7.gif");
        lblarray.get(count).setCursor(new ImageCursor(image,
                image.getWidth()/2 ,
                image.getHeight()/2 ));
        lblarray.get(count).setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ObservableList<LiveStocksByRoomsUsingRoomIdTm2> nobl = FXCollections.observableArrayList();
                ArrayList<LiveStocksByRoomsUsingRoomIdTm1> nlist = null;
                nlist = warehouseService.showLiveStocksByRoomsUsingRoomId(w.getRoom_Id());
                for (LiveStocksByRoomsUsingRoomIdTm1 c : nlist) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    FileInputStream input = null;
                    try {
                        input = new FileInputStream(c.getImagePath());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Image img = new Image(input);
                    ImageView imageView = new ImageView(img);
                    imageView.setFitWidth(180);
                    imageView.setFitHeight(85);
                    LiveStocksByRoomsUsingRoomIdTm2 btm = new LiveStocksByRoomsUsingRoomIdTm2(
                            c.getBiscuit_No(),
                            imageView,
                            c.getBiscuit_Brand(),
                            c.getSize(),
                            c.getAvailableQty(),
                            c.getMFD(),
                            c.getEXD()
                    );
                    nobl.add(btm);
                    smwlsbrctbl.setItems(nobl);
                    smwlsbrcpopupcontext.setVisible(true);
                    smwlsbrcpopupcontext.setVisible(true);
                }
                smwlsbrcpopupcontext.setVisible(true);
            }
        });
        addPbarAddToContext();
        //smwlsbrcpopupcontext.setOnMouseExited(event -> smwlsbrcpopupcontext.setVisible(false));
    }

    private void addPbarAddToContext(){
        hbox1lbl.getChildren().add(lblarray.get(count));
        hbox1pbar.getChildren().add(pbararray.get(count));
        if(pbararray.size()<5) {

        }else if(pbararray.size()>=6&&pbararray.size()<11){
            // hbox2.getChildren().add(pbararray.get(count));
        }
        count++;
    }

    public void onActionClose(MouseEvent mouseEvent) {
        smwlsbrcpopupcontext.setVisible(false);
    }
}
