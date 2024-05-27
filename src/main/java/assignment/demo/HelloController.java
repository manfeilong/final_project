package assignment.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {



    @FXML
    private ImageView setting_icon;

    @FXML
    private ImageView back_setting_icon;

    //pvp button function
    @FXML
    private Button PVP_button;

    @FXML
    private Button PVE_button;

    @FXML
    private Button training_button;

    @FXML
    private Button custom_button;

    @FXML
    private ImageView setting_background_icon;


    @FXML
    protected void  on_setting_image_Click() throws IOException {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), setting_icon);
        rotateTransition.setByAngle(90); // 設置旋轉角度
        rotateTransition.setCycleCount(1); // 設置旋轉次數
        rotateTransition.play(); // 播放動畫
        //(改變畫面動畫)
        // 創建時間軸
        Timeline timeline = new Timeline();
        // 添加關鍵幀，持續時間為2秒
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);

        KeyValue keyValue1 = new KeyValue(PVP_button.opacityProperty(), 0);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.5), keyValue1);

        KeyValue keyValue2 = new KeyValue(PVE_button.opacityProperty(), 0);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), keyValue2);

        KeyValue keyValue3 = new KeyValue(training_button.opacityProperty(), 0);
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.5), keyValue3);

        KeyValue keyValue4 = new KeyValue(custom_button.opacityProperty(), 0);
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.5), keyValue4);

        timeline.getKeyFrames().addAll(keyFrame,keyFrame1,keyFrame2,keyFrame3,keyFrame4);
        timeline.play();

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("setting.fxml"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 獲取舞台
            Stage stage = (Stage) ((javafx.scene.Node) setting_icon).getScene().getWindow();
            // 設置新的場景
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("Original Scene");
            // 顯示舞台
            stage.show();

        }));
        timeline1.play();


    }
//in setting.fxml funcction

    @FXML
    private Label music_label;

    @FXML
    private Label background_label;

    @FXML
    private Slider music_volumeSlider;

    @FXML
    private Slider background_volumeSlider;

    @FXML
    private ImageView black_setting;

    @FXML
    private ImageView mute_music;

    @FXML
    private ImageView mute_background;

    @FXML
    private ImageView music_icon;

    @FXML
    private ImageView background_icon;





    @FXML
    public void volume_background() {

        // 初始化 Label 的文本為 Slider 的當前值
        background_label.setText(String.format("%.0f", background_volumeSlider.getValue()));
        if (background_volumeSlider.getValue() == 0) {
            mute_background.setVisible(true);
            background_icon.setVisible(false);

        } else {
            mute_background.setVisible(false);
            background_icon.setVisible(true);
        }
    }

    @FXML
    public void volume_music() {
        /*
        // 綁定 Slider 的值屬性到 Label 的文本屬性
        music_volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            music_label.setText(String.format("%.2f", newValue));
        });
        */
        // 初始化 Label 的文本為 Slider 的當前值
        music_label.setText(String.format("%.0f", music_volumeSlider.getValue()));

        if (music_volumeSlider.getValue() == 0) {
            mute_music.setVisible(true);
            music_icon.setVisible(false);

        } else {
            mute_music.setVisible(false);
            music_icon.setVisible(true);
        }
    }




    @FXML
    protected void  on_setting_image_Click_back() throws IOException {
        /*
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), back_setting_icon);
        rotateTransition.setByAngle(90); // 設置旋轉角度
        rotateTransition.setCycleCount(1); // 設置旋轉次數
        rotateTransition.play(); // 播放動畫


         */

        Timeline timeline = new Timeline();
        // 添加關鍵幀，持續時間為2秒
        KeyValue keyValue = new KeyValue(black_setting.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 獲取舞台
            Stage stage = (Stage) (back_setting_icon).getScene().getWindow();
            // 設置新的場景
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("Original Scene");
            // 顯示舞台
            stage.show();
        }));
        timeline1.play();
    }








    @FXML
    private ImageView black;


    @FXML
    protected void  on_PVP_button_Click(){
        // 創建時間軸
        Timeline timeline = new Timeline();

        // 添加關鍵幀，持續時間為2秒
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        System.out.println("PVP button clicked");

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("PVP.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 獲取舞台
            Stage stage = (Stage) ((javafx.scene.Node) PVP_button).getScene().getWindow();
            // 設置新的場景
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("Original Scene");
            // 顯示舞台
            stage.show();

        }));
        timeline2.play();





        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            black.opacityProperty().setValue(0);
        }));
        timeline1.play();
    }

    @FXML
    protected void  on_PVE_button_Click(){
        // 創建時間軸
        Timeline timeline = new Timeline();

        // 添加關鍵幀，持續時間為2秒
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        System.out.println("PVE button clicked");
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            black.opacityProperty().setValue(0);
        }));
        timeline1.play();

    }


    @FXML
    protected void  on_training_button_Click(){
        // 創建時間軸
        Timeline timeline = new Timeline();

        // 添加關鍵幀，持續時間為2秒
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        System.out.println("training button clicked");
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            black.opacityProperty().setValue(0);
        }));
        timeline1.play();
    }




}