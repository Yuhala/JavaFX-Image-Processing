/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.morphology;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import processors.Binarizer;

/**
 *
 * @author peterson
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane h_bina, h_eros, h_dila, h_open, h_close, h_wth,
            h_bth, h_skele, h_home, h_mcq, h_test;
    @FXML
    private JFXButton btn_bina, btn_eros, btn_dila, btn_open, btn_close,
            btn_wth, btn_bth, btn_skele, btn_mcq, btn_test;
    //Binarisation controls
    @FXML
    private JFXSlider bina_level1, bina_level2;

    @FXML
    private ImageView bina_img1, bina_img2;

    @FXML
    private JFXButton bina_choose, bina_op, bina_save;
    //Erosion Controls
    @FXML
    private JFXSlider eros_level1, eros_level2, eros_order;

    @FXML
    private JFXButton eros_choose, eros_op, eros_save;

    @FXML
    private JFXRadioButton eros_zeroes, eros_sym, eros_csym;

    private BufferedImage bimg;
    private File imgFile;
    private ArrayList<AnchorPane> windows = new ArrayList<AnchorPane>();

    @FXML
    private void handleButtonAction(ActionEvent event) throws Throwable {
        this.showWindow((Node) event.getTarget());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        windows.add(h_eros);
        windows.add(h_bina);
        windows.add(h_dila);
        windows.add(h_open);
        windows.add(h_close);
        windows.add(h_home);
        windows.add(h_wth);
        windows.add(h_bth);
        windows.add(h_skele);
        windows.add(h_mcq);
        windows.add(h_test);
    }

    public void showWindow(Node target) throws Throwable {
        AnchorPane pane = new AnchorPane();
        try {
            for (int i = 0; i < windows.size(); i++) {
                windows.get(i).setVisible(false);
            }
        } catch (Exception e) {
            e.getCause();
        }

        if (target == btn_bina) {
            h_bina.setVisible(true);
        } else if (target == btn_eros) {
            h_eros.setVisible(true);
        } else if (target == btn_dila) {
            h_dila.setVisible(true);
        } else if (target == btn_open) {
            h_open.setVisible(true);
        } else if (target == btn_close) {
            h_close.setVisible(true);
        } else if (target == btn_wth) {
            h_wth.setVisible(true);
        } else if (target == btn_bth) {
            h_bth.setVisible(true);
        } else if (target == btn_mcq) {
            h_mcq.setVisible(true);
        } else if (target == btn_test) {
            h_test.setVisible(true);
        } else if (target == btn_skele) {
            h_skele.setVisible(true);
        }

    }

    public void quit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void choosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterbmp
                = new FileChooser.ExtensionFilter("bmp files (*.bmp)", "*.bmp");
        fileChooser.getExtensionFilters()
                .addAll(extFilterpng, extFilterjpg, extFilterbmp, extFilterPNG, extFilterJPG);

        //Show open file dialog
        this.imgFile = fileChooser.showOpenDialog(null);
        if (this.imgFile == null) {
            showDialog("No Image Selected","Please select an image!");
            return;
        }

        try {
            this.bimg = ImageIO.read(this.imgFile);

            Image image = SwingFXUtils.toFXImage(this.bimg, null);
            if (event.getTarget() == bina_choose) {
                bina_img1.setImage(image);
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageMorphology.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveImage(ActionEvent event) {

    }

    public void processImage(ActionEvent event) {
        if (event.getTarget() == bina_op) {
            this.binarize();
        }

    }

    public void binarize() {
        if (this.imgFile == null) {
            showDialog("No Image Selected","Please select an image!");
            return;
        }

        int level1 = (int) this.bina_level1.getValue();
        int level2 = (int) this.bina_level2.getValue();

        Binarizer bin = new Binarizer(this.bimg, level1, level2);
        Image image = SwingFXUtils.toFXImage(bin.binarise(this.bimg), null);
        bina_img2.setImage(image);
        try {
            this.bimg = ImageIO.read(this.imgFile);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void erode() {
    }

    public void dilate() {
    }

    public void open() {
    }

    public void close() {
    }

    public void wth() {
    }

    public void bth() {
    }

    public void skeletize() {
    }
    public void showDialog(String title,String message){
     Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(title);
            alert.setContentText(message);

            alert.showAndWait();
    
    }
 

}
