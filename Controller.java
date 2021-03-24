package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.TabExpander;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button brush;

    @FXML
    private ColorPicker color;

    boolean toolSelected=false;
    GraphicsContext brushTool;



    @FXML
    private TextField bisize;

    @FXML
    private Canvas canvas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brushTool=canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e ->{
            double size =Double.parseDouble(bisize.getText());
            double x =e.getX()-size/2;
            double y =e.getY()-size/2;
            if(toolSelected && !bisize.getText().isEmpty()){
                brushTool.setFill(color.getValue());
                brushTool.fillRoundRect(x,y,size,size,size,size);
            }

        });


    }
    @FXML
    public void newcanvas(ActionEvent e) {
        TextField getCanvasWidth=new TextField();
        getCanvasWidth.setPromptText("Width");
        getCanvasWidth.setPrefWidth(150);
        getCanvasWidth.setAlignment(Pos.CENTER);

        TextField getCanvasheight=new TextField();
        getCanvasheight.setPromptText("Height");
        getCanvasheight.setPrefWidth(150);
        getCanvasheight.setAlignment(Pos.CENTER);

        Button createButton = new Button();
        createButton.setText("Create Canvas");

        VBox vBox =new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(getCanvasWidth,getCanvasheight,createButton);

        Stage createStage =new Stage();
        AnchorPane root = new AnchorPane();
        root.setPrefWidth(200);
        root.setPrefHeight(200);
        root.getChildren().add(vBox);


        Scene CanvasScene = new Scene(root);
        createStage.setTitle("CreateCanvas");
        createStage.setScene(CanvasScene);
        createStage.show();

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double canvasWidthReceived = Double.parseDouble(getCanvasWidth.getText());
                double canvasHeightReceived = Double.parseDouble(getCanvasheight.getText());
                canvas =new Canvas();
                canvas.setWidth(canvasWidthReceived);
                canvas.setHeight(canvasHeightReceived);
                vBox.getChildren().add(canvas);
                createStage.close();

            }
        });


    }
    @FXML
    public void toolselected(ActionEvent e){

        toolSelected=true;
    }
}

