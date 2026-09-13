package assignment.demo;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.scene.media.MediaPlayer;

public class HelloController {
    boolean not_end = true;
    static boolean training_mode = false;
    music_controll music = new music_controll();
    int temp_select = music.get_now_play();
    private String[] path_collects;
    GameState player1_gameState = new GameState();
    GameState player2_gameState = new GameState();
    GameState_PVE player1_in_PVE = new GameState_PVE();
    GameState_PVE bot = new GameState_PVE();
    public Set<KeyCode> activeKeys_PVE;
    // 創建一個random_time物件
    Timeline random_move_time = new Timeline();
    Timeline record_attack_5s = new Timeline();
    Timeline record_defense_rate = new Timeline();
    Timeline show_game_times = new Timeline();

    public int record_time = 0;
    static int attack_time = 0;
    static int explode_time = 0;
    static int player_defense_time = 0;
    static int show_time = 0;
    //通用////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView black;

    //開始介面/////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView interface_image;

    @FXML
    private Label interface_label;

    //大廳////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView setting_icon;//設定圖示

    @FXML
    private ImageView back_setting_icon;//返回圖示

    @FXML
    private ImageView back_PVP_icon;//返回圖示

    @FXML
    private Button PVP_button;//PVP

    @FXML
    private Button PVE_button;//PVE

    @FXML
    private Button training_button;//訓練場

    @FXML
    private Button custom_button;//服裝

    @FXML
    private Button OK_into_PVP;//進入PVP

    @FXML
    private ImageView PVP_introduce;

    //設定/////////////////////////////////////////////////////////////////////////////
    @FXML
    private Label music_label;//音樂

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
    private ImageView setting_background_icon;//設定背景

    @FXML
    private ImageView left_choose_music;//設定背景

    @FXML
    private ImageView right_choose_music;//設定背景

    @FXML
    private Label music_name;//設定背景

    //PVP////////////////////////////////////////////////////////////////////////////
    @FXML
    private VBox player1_up_bar;

    @FXML
    private VBox player1_down_bar;

    @FXML
    private HBox player1_left_bar;

    @FXML
    private HBox player1_right_bar;

    @FXML
    private VBox player2_up_bar;

    @FXML
    private VBox player2_down_bar;

    @FXML
    private HBox player2_left_bar;

    @FXML
    private HBox player2_right_bar;

    @FXML
    private ImageView  player_1_up_bomb;

    @FXML
    private ImageView player_1_down_bomb;

    @FXML
    private ImageView player_1_left_bomb;

    @FXML
    private ImageView player_1_right_bomb;

    @FXML
    private ImageView player_1_up_warning;

    @FXML
    private ImageView player_1_down_warning;

    @FXML
    private ImageView player_1_left_warning;

    @FXML
    private ImageView player_1_right_warning;

    @FXML
    private ImageView player_1_up_explode;

    @FXML
    private ImageView player_1_down_explode;

    @FXML
    private ImageView player_1_left_explode;

    @FXML
    private ImageView player_1_right_explode;

    @FXML
    private ImageView player_2_up_bomb;

    @FXML
    private ImageView player_2_down_bomb;

    @FXML
    private ImageView player_2_left_bomb;

    @FXML
    private ImageView player_2_right_bomb;

    @FXML
    private ImageView player_2_up_warning;

    @FXML
    private ImageView player_2_down_warning;

    @FXML
    private ImageView player_2_left_warning;

    @FXML
    private ImageView player_2_right_warning;

    @FXML
    private ImageView player_2_up_explode;

    @FXML
    private ImageView player_2_down_explode;

    @FXML
    private ImageView player_2_left_explode;

    @FXML
    private ImageView player_2_right_explode;

    @FXML
    private ImageView player_1_normal;

    @FXML
    private ImageView player_1_get_hurt;

    @FXML
    private ImageView player_2_normal;

    @FXML
    private ImageView player_2_get_hurt;

    @FXML
    private StackPane player_1_HP;

    @FXML
    private StackPane player_2_HP;

    @FXML
    private AnchorPane PVP_pane;

    @FXML
    private Circle player_1_CD;

    @FXML
    private Circle player_2_CD;

    @FXML
    private ImageView player_1_win;

    @FXML
    private ImageView player_2_win;

    @FXML
    private ImageView player_1_loss;

    @FXML
    private ImageView player_2_loss;

    @FXML
    private ImageView player_1_W_or_L_background;

    @FXML
    private ImageView player_2_W_or_L_background;

    @FXML
    private Button PVP_result_back_button;

    //PVE、訓練場////////////////////////////////////////////////////////////////////////

    @FXML
    private Slider choose_difficulty;

    @FXML
    private ImageView easy_play;

    @FXML
    private ImageView normal_play;

    @FXML
    private ImageView hard_play;

    @FXML
    private ImageView easy_image;

    @FXML
    private ImageView normal_image;

    @FXML
    private ImageView hard_image;

    @FXML
    private Label difficulty_label;

    @FXML
    private VBox PVE_UP_LINE;

    @FXML
    private VBox PVE_DOWN_LINE;

    @FXML
    private HBox PVE_LEFT_LINE;

    @FXML
    private HBox PVE_RIGHT_LINE;

    @FXML
    private ImageView PVE_UP_BOMB;

    @FXML
    private ImageView PVE_DOWN_BOMB;

    @FXML
    private ImageView PVE_LEFT_BOMB;

    @FXML
    private ImageView PVE_RIGHT_BOMB;

    @FXML
    private ImageView PVE_UP_WARNING;

    @FXML
    private ImageView PVE_DOWN_WARNING;

    @FXML
    private ImageView PVE_LEFT_WARNING;

    @FXML
    private ImageView PVE_RIGHT_WARNING;

    @FXML
    private ImageView PVE_UP_EXPLODE;

    @FXML
    private ImageView PVE_DOWN_EXPLODE;

    @FXML
    private ImageView PVE_LEFT_EXPLODE;

    @FXML
    private ImageView PVE_RIGHT_EXPLODE;

    @FXML
    private ImageView PVE_normal;

    @FXML
    private ImageView PVE_get_hurt;

    //395
    @FXML
    private Pane PVE_player_HP;

    //395
    @FXML
    private Pane PVE_BOT_HP;

    @FXML
    private Circle PVE_CD;

    @FXML
    private Button OK_into_PVE;

    @FXML
    private ImageView back_PVE_icon;

    @FXML
    private ImageView PVE_win;

    @FXML
    private ImageView PVE_lose;

    @FXML
    private ImageView training_guide;

    @FXML
    private ImageView PVE_guide;

    //訓練場////////////////////////////////////////////////////////////////////////
    @FXML
    private Label defend_time;

    @FXML
    private Label defend_percentage;

    @FXML
    private Label attack_times;

    @FXML
    private Button OK_into_training;

    @FXML
    protected ImageView back_training_icon;

    private Set<KeyCode> activeKeys;

    public HelloController() {
        activeKeys = new HashSet<>();
        activeKeys_PVE = new HashSet<>();
        path_collects = new String[6];
        path_collects[0] = "cruising-down";
        path_collects[1] = "push-onward";
        path_collects[2] = "pixel-wild";
        path_collects[3] = "8-bit-march";
        path_collects[4] = "8-bit-game";
        path_collects[5] = "8-bit-arcade";
    }
    //通用//
    //音效
    void play_explode_sound() {
        //播放爆炸音效
        String path = "src/main/resources/sound_effect/boom.mp3";
        File file = new File(path);
        if (file.exists()) {
            System.out.println("The file exists.");
        }
        else {
            System.out.println("The file does not exist.");
        }
        javafx.scene.media.Media media = new javafx.scene.media.Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    void play_defense_sound() {
        //播放防守音效
        String path = "src/main/resources/sound_effect/remove_bomb.mp3";
        File file = new File(path);
        if (file.exists()) {
            System.out.println("The file exists.");
        }
        else {
            System.out.println("The file does not exist.");
        }
        javafx.scene.media.Media media = new javafx.scene.media.Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    void end_sound() {
        //播放結束音效
        String path = "src/main/resources/sound_effect/Ohhh.mp3";
        File file = new File(path);
        if (file.exists()) {
            System.out.println("The file exists.");
        }
        else {
            System.out.println("The file does not exist.");
        }
        javafx.scene.media.Media media = new javafx.scene.media.Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }
    //CD時間
    void C_D_change(Circle player_CD) {
        player_CD.setFill(Color.RED);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
            player_CD.setFill(Color.web("#00ff1a"));
        }));
        timeline.play();
    }
    //開始介面//
    @FXML
    protected void on_interface_image_click(){
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(interface_image.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        interface_label.setVisible(false);
        interface_label.setDisable(true);
        interface_label.setMouseTransparent(true);
        interface_image.setDisable(true);
        interface_image.setMouseTransparent(true);
        music.play_music();
    }
    //主畫面//
    //到設定
    @FXML
    protected void  on_setting_image_Click() throws IOException {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), setting_icon);
        rotateTransition.setByAngle(90); // 設置旋轉角度
        rotateTransition.setCycleCount(1); // 設置旋轉次數
        rotateTransition.play(); // 播放動畫
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
        KeyValue keyValue1 = new KeyValue(PVP_button.opacityProperty(), 0);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.5), keyValue1);
        KeyValue keyValue2 = new KeyValue(PVE_button.opacityProperty(), 0);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), keyValue2);
        KeyValue keyValue3 = new KeyValue(training_button.opacityProperty(), 0);
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.5), keyValue3);
        //KeyValue keyValue4 = new KeyValue(custom_button.opacityProperty(), 0);
        //KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.5), keyValue4);
        timeline.getKeyFrames().addAll(keyFrame,keyFrame1,keyFrame2,keyFrame3);
        timeline.play();
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("setting.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((javafx.scene.Node) setting_icon).getScene().getWindow();
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("蹦蹦炸彈");
            stage.show();
        }));
        timeline1.play();
    }
    //到PVP
    //顯示遊戲介紹
    @FXML
    protected void  on_PVP_button_Click(){
        PVP_introduce.setVisible(true);
        OK_into_PVP.setVisible(true);
    }
    //正式進入遊戲
    @FXML
    protected void  on_OK_into_PVP(){
        PVP_introduce.setVisible(false);
        OK_into_PVP.setVisible(false);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        System.out.println("PVP button clicked");
        String winVideoUriPath = new File("src/main/resources/sound_effect/3_2_1.mp4").toURI().toString();//win影片的Uri
        javafx.scene.media.Media winVideoMedia = new javafx.scene.media.Media(winVideoUriPath);//win影片的Media
        MediaPlayer winVideoPlayer = new MediaPlayer(winVideoMedia);//win影片的MediaPlayer
        winVideoPlayer.setMute(true);//靜音
        MediaView winVideoMediaView = new MediaView(winVideoPlayer);//win影片的MediaView
        winVideoPlayer.play();//播放
        Parent root1= null;
        try {
            root1 = new StackPane(winVideoMediaView);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) ((javafx.scene.Node) OK_into_PVP).getScene().getWindow();
        stage1.setScene(new Scene(root1, 960, 540));
        stage1.setTitle("蹦蹦炸彈");
        stage1.show();
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(3.2), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("PVP.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((javafx.scene.Node) winVideoMediaView).getScene().getWindow();
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("蹦蹦炸彈");
            stage.show();
            root.requestFocus();
        }));
        timeline2.play();
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            black.opacityProperty().setValue(0);
        }));
        timeline1.play();
    }
    //PVE難度調整
    static double PVE_difficulty = 0.5;

    //到地獄魔王級模式

    @FXML
    private ImageView crazy_player_image;

    static boolean crazy_mode=false;

    //地獄模式
    @FXML
    protected void  on_super_mode_Click(){
        System.out.println("super clicked");
        crazy_mode=true;
        PVE_difficulty =0.15;
        System.out.println("super Hard");
        String winVideoUriPath = new File("src/main/resources/sound_effect/3_2_1.mp4").toURI().toString();//win影片的Uri
        javafx.scene.media.Media winVideoMedia = new javafx.scene.media.Media(winVideoUriPath);//win影片的Media
        MediaPlayer winVideoPlayer = new MediaPlayer(winVideoMedia);//win影片的MediaPlayer
        winVideoPlayer.setMute(true);//靜音
        MediaView winVideoMediaView = new MediaView(winVideoPlayer);//win影片的MediaView
        winVideoPlayer.play();//播放

        Parent root1= null;
        try {
            root1 = new StackPane(winVideoMediaView);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) ((javafx.scene.Node) OK_into_PVP).getScene().getWindow();
        stage1.setScene(new Scene(root1, 960, 540));
        stage1.setTitle("蹦蹦炸彈");
        stage1.show();
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(3.2), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("PVE.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((javafx.scene.Node) winVideoMediaView).getScene().getWindow();
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("蹦蹦炸彈");
            stage.show();
            root.requestFocus();
        }));
        timeline2.play();

        //crazy_player_image.setVisible(true);
    }

    //到PVE
    //顯示遊戲介紹
    @FXML
    protected void  on_PVE_button_Click(){
        PVE_guide.setVisible(true);
        OK_into_PVE.setVisible(true);
        //畫面漸黑
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        System.out.println("PVE button clicked");
    }

    //進入難度
    @FXML
    protected void  on_OK_into_PVE(){
        PVE_guide.setVisible(false);
        OK_into_PVE.setVisible(false);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("difficulty.fxml"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) OK_into_PVE).getScene().getWindow();
        stage.setScene(new Scene(root, 960, 540));
        stage.setTitle("蹦蹦炸彈");
        stage.show();
        root.requestFocus();

    }




    @FXML
    private void on_difficulty_slider_switch() {
        int now_state = (int) choose_difficulty.getValue();
        easy_play.setVisible(false);
        normal_play.setVisible(false);
        hard_play.setVisible(false);
        easy_image.setVisible(false);
        normal_image.setVisible(false);
        hard_image.setVisible(false);
        if (now_state == 0) {
            System.out.println("Easy");
            choose_difficulty.getStylesheets().clear();
            choose_difficulty.getStylesheets().add(getClass().getResource("/assignment/demo/easy_slider.css").toExternalForm());
            easy_play.setVisible(true);
            easy_image.setVisible(true);
            difficulty_label.setText("Easy");
        } else if (now_state == 1) {
            System.out.println("Medium");
            choose_difficulty.getStylesheets().clear();
            choose_difficulty.getStylesheets().add(getClass().getResource("/assignment/demo/normal_slider.css").toExternalForm());
            normal_play.setVisible(true);
            normal_image.setVisible(true);
            difficulty_label.setText("Normal");
        } else if (now_state == 2) {
            System.out.println("Hard");
            choose_difficulty.getStylesheets().clear();
            choose_difficulty.getStylesheets().add(getClass().getResource("/assignment/demo/hard_slider.css").toExternalForm());
            hard_play.setVisible(true);
            hard_image.setVisible(true);
            difficulty_label.setText("Hard");
        } else {
            System.out.println("Error");
        }
    }



    @FXML
    private void on_easy_PVE_click(){
        PVE_difficulty =1.5;
        System.out.println("Easy");
        into_PVE();
    }
    @FXML
    private void on_normal_PVE_click(){
        PVE_difficulty =0.5;
        System.out.println("Normal");
        into_PVE();
    }
    @FXML
    private void on_hard_PVE_click(){
        PVE_difficulty =0.3;
        System.out.println("Hard");
        into_PVE();
    }

    //進入PVE
    public void into_PVE(){

        String winVideoUriPath = new File("src/main/resources/sound_effect/3_2_1.mp4").toURI().toString();//win影片的Uri
        javafx.scene.media.Media winVideoMedia = new javafx.scene.media.Media(winVideoUriPath);//win影片的Media
        MediaPlayer winVideoPlayer = new MediaPlayer(winVideoMedia);//win影片的MediaPlayer
        winVideoPlayer.setMute(true);//靜音
        MediaView winVideoMediaView = new MediaView(winVideoPlayer);//win影片的MediaView
        winVideoPlayer.play();//播放

        Parent root1= null;
        try {
            root1 = new StackPane(winVideoMediaView);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) ((javafx.scene.Node) difficulty_label).getScene().getWindow();
        stage1.setScene(new Scene(root1, 960, 540));
        stage1.setTitle("蹦蹦炸彈");
        stage1.show();
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(3.2), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("PVE.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((javafx.scene.Node) winVideoMediaView).getScene().getWindow();
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("蹦蹦炸彈");
            stage.show();
            root.requestFocus();
        }));
        timeline2.play();
    }



    //到訓練場
    //顯示遊戲介紹
    @FXML
    protected void  on_training_button_Click(){
        training_guide.setVisible(true);
        OK_into_training.setVisible(true);
        training_mode=true;
        System.out.println("training button clicked"+training_mode);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(black.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        System.out.println("training button clicked");
    }
    //正式進入training遊戲
    @FXML
    protected void  on_OK_into_training(){
        training_guide.setVisible(false);
        OK_into_training.setVisible(false);
        String winVideoUriPath = new File("src/main/resources/sound_effect/3_2_1.mp4").toURI().toString();//win影片的Uri
        javafx.scene.media.Media winVideoMedia = new javafx.scene.media.Media(winVideoUriPath);//win影片的Media
        MediaPlayer winVideoPlayer = new MediaPlayer(winVideoMedia);//win影片的MediaPlayer
        winVideoPlayer.setMute(true);//靜音
        MediaView winVideoMediaView = new MediaView(winVideoPlayer);//win影片的MediaView
        winVideoPlayer.play();//播放
        Parent root1= null;
        try {
            root1 = new StackPane(winVideoMediaView);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = (Stage) ((javafx.scene.Node) OK_into_training).getScene().getWindow();
        stage1.setScene(new Scene(root1, 960, 540));
        stage1.setTitle("蹦蹦炸彈");
        stage1.show();
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(3.2), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("training.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((javafx.scene.Node) winVideoMediaView).getScene().getWindow();
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("蹦蹦炸彈");
            stage.show();
            root.requestFocus();
        }));
        timeline2.play();
    }
    //設定//
    //調整背景音樂音量
    @FXML
    public void volume_background() {
        // 初始化 Label 的文本為 Slider 的當前值
        background_label.setText(String.format("%.0f", background_volumeSlider.getValue()));
        music.change_volume((int) background_volumeSlider.getValue());
        if (background_volumeSlider.getValue() == 0) {
            mute_background.setVisible(true);
            background_icon.setVisible(false);
        }
        else {
            mute_background.setVisible(false);
            background_icon.setVisible(true);
        }
    }
    //調整音效音量
    @FXML
    public void volume_music() {
        music_label.setText(String.format("%.0f", music_volumeSlider.getValue()));
        if (music_volumeSlider.getValue() == 0) {
            mute_music.setVisible(true);
            music_icon.setVisible(false);
        }
        else {
            mute_music.setVisible(false);
            music_icon.setVisible(true);
        }
    }
    //更換音樂(右)
    @FXML
    protected void right_choose() {
        temp_select++;
        if (temp_select == 6) {
            temp_select = 0;
        }
        music.stop_music();
        music.set_now_play(temp_select);
        music_name.setText(path_collects[temp_select]);
        System.out.println(temp_select);
        music.play_music();
    }
    //更換音樂(左)
    @FXML
    protected void left_choose() {
        temp_select--;
        if (temp_select < 0) {
            temp_select = 5;
        }
        music.stop_music();
        music.set_now_play(temp_select);
        music_name.setText(path_collects[temp_select]);
        System.out.println(temp_select);
        music.play_music();
    }
    //回主畫面
    @FXML
    protected void  on_setting_image_Click_back() throws IOException {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(black_setting.opacityProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) (back_setting_icon).getScene().getWindow();
            //將裡面的interface_image移除
            stage.setScene(new Scene(root, 960, 540));
            //取得裡面的anchorplane
            AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
            //取得裡面的interface_image
            ImageView interface_image = (ImageView) anchorPane.getChildren().get(1);
            //取得裡面的interface_label
            Label interface_label = (Label) anchorPane.getChildren().get(2);
            //將interface_image移除
            anchorPane.getChildren().remove(interface_image);
            //將interface_label移除
            anchorPane.getChildren().remove(interface_label);
            stage.setTitle("蹦蹦炸彈");
            // 顯示舞台
            stage.show();
        }));
        timeline1.play();
    }
    //PVP//
    //受傷
    void get_hurt_image(ImageView player, ImageView player_get_hurt,int player_1_or_2) {
        player.setVisible(false);
        player_get_hurt.setVisible(true);
        if(not_end) {
            if (player_1_or_2 == 1) {
                //將VBOX顏色改變
                player1_up_bar.setStyle("-fx-background-color: #ff0000");
                player1_down_bar.setStyle("-fx-background-color: #ff0000");
                player1_left_bar.setStyle("-fx-background-color: #ff0000");
                player1_right_bar.setStyle("-fx-background-color: #ff0000");
            }
            else {
                //將VBOX顏色改變
                player2_up_bar.setStyle("-fx-background-color: #ff0000");
                player2_down_bar.setStyle("-fx-background-color: #ff0000");
                player2_left_bar.setStyle("-fx-background-color: #ff0000");
                player2_right_bar.setStyle("-fx-background-color: #ff0000");
            }
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.4), attack -> {
            player.setVisible(true);
            player_get_hurt.setVisible(false);
            if (player_1_or_2 == 1) {
                //將VBOX顏色改變
                player1_up_bar.setStyle("-fx-background-color: #000065");
                player1_down_bar.setStyle("-fx-background-color: #000065");
                player1_left_bar.setStyle("-fx-background-color: #000065");
                player1_right_bar.setStyle("-fx-background-color: #000065");
            }
            else {
                //將VBOX顏色改變
                player2_up_bar.setStyle("-fx-background-color: #000065");
                player2_down_bar.setStyle("-fx-background-color: #000065");
                player2_left_bar.setStyle("-fx-background-color: #000065");
                player2_right_bar.setStyle("-fx-background-color: #000065");
            }
        }));
        timeline.play();
    }
    //結果(外框)
    void set_result_bar (int player_1_or_2_loss) {
        if (player_1_or_2_loss == 1) {
            //將VBOX顏色改變
            player1_up_bar.setStyle("-fx-background-color: #ff0000");
            player1_down_bar.setStyle("-fx-background-color: #ff0000");
            player1_left_bar.setStyle("-fx-background-color: #ff0000");
            player1_right_bar.setStyle("-fx-background-color: #ff0000");
            player2_up_bar.setStyle("-fx-background-color: #00ff00");
            player2_down_bar.setStyle("-fx-background-color: #00ff00");
            player2_left_bar.setStyle("-fx-background-color: #00ff00");
            player2_right_bar.setStyle("-fx-background-color: #00ff00");
        }
        else {
            //將VBOX顏色改變
            player1_up_bar.setStyle("-fx-background-color: #00ff00");
            player1_down_bar.setStyle("-fx-background-color: #00ff00");
            player1_left_bar.setStyle("-fx-background-color: #00ff00");
            player1_right_bar.setStyle("-fx-background-color: #00ff00");
            player2_up_bar.setStyle("-fx-background-color: #ff0000");
            player2_down_bar.setStyle("-fx-background-color: #ff0000");
            player2_left_bar.setStyle("-fx-background-color: #ff0000");
            player2_right_bar.setStyle("-fx-background-color: #ff0000");
        }
    }
    //結果(win,lose)
    void show_game_result(ImageView winner, ImageView loser, ImageView win_or_loss_background_left, ImageView win_or_loss_background_right) {
        winner.setVisible(true);
        loser.setVisible(true);
        win_or_loss_background_left.setVisible(true);
        win_or_loss_background_right.setVisible(true);
        PVP_result_back_button.setVisible(true);
        end_sound();
    }
    //GAMESTATE
    class GameState {
        private boolean right_has_boomb;
        private boolean left_has_boomb;
        private boolean down_has_boomb;
        private boolean up_has_boomb;
        private int HP;
        private Timer up_timer;
        private Timeline T_bomb_up;
        private Timeline T_waring_up;
        private Timer down_timer;
        private Timeline T_bomb_down;
        private Timeline T_waring_down;
        private Timer left_timer;
        private Timeline T_bomb_left;
        private Timeline T_waring_left;
        private Timer right_timer;
        private Timeline T_bomb_right;
        private Timeline T_waring_right;
        private boolean Timer_UP_Running ;
        private boolean Timer_DOWN_Running ;
        private boolean Timer_LEFT_Running ;
        private boolean Timer_RIGHT_Running ;
        private boolean attack ;
        public GameState() {
            this.HP = 380;
            this.up_has_boomb = false;
            this.down_has_boomb = false;
            this.left_has_boomb = false;
            this.right_has_boomb = false;
            this.Timer_UP_Running = false;
            this.Timer_DOWN_Running = false;
            this.Timer_LEFT_Running = false;
            this.Timer_RIGHT_Running = false;
            this.attack = true;

        }

        public void setRight_has_boomb(boolean right_has_boomb) {
            this.right_has_boomb = right_has_boomb;
        }

        public void setLeft_has_boomb(boolean left_has_boomb) {
            this.left_has_boomb = left_has_boomb;
        }

        public void setDown_has_boomb(boolean down_has_boomb) {
            this.down_has_boomb = down_has_boomb;
        }

        public void setUp_has_boomb(boolean up_has_boomb) {
            this.up_has_boomb = up_has_boomb;
        }

        public boolean isRight_has_boomb() {
            return right_has_boomb;
        }

        public boolean isLeft_has_boomb() {
            return left_has_boomb;
        }

        public boolean isDown_has_boomb() {
            return down_has_boomb;
        }

        public boolean isUp_has_boomb() {
            return up_has_boomb;
        }

        public void setTimer_UP_Running1(boolean timer_UP_Running) {
            this.Timer_UP_Running = timer_UP_Running;
            if (Timer_UP_Running) {
                player_1_up_bomb.setVisible(true);
                up_timer = new Timer();
                up_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_UP_Running = false;
                        System.out.println("Timer ended up");
                        player_1_up_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        player_1_HP.setPrefWidth(HP);
                        player_1_up_warning.opacityProperty().setValue(0);
                        player_1_up_explode.setVisible(true);
                        //播放mp3
                        player1_gameState.up_has_boomb = false;
                        get_hurt_image(player_1_normal, player_1_get_hurt,1);
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_2_win, player_1_loss, player_2_W_or_L_background, player_1_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_1_normal.setVisible(false);
                                player_1_get_hurt.setVisible(true);
                                set_result_bar(1);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_up = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_1_up_explode.setVisible(false);
                }));
                T_bomb_up.play();
                player_1_up_warning.setVisible(true);
                player_1_up_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_1_up_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_up = new Timeline(keyFrame);
                T_waring_up .play();
                System.out.println("Timer started up");
            }
            else {
                up_timer.cancel();
                play_defense_sound();
                Timer_UP_Running = false;
                T_bomb_up.stop();
                T_waring_up.stop();
                T_bomb_up.playFromStart();
                T_waring_up.playFromStart();
                player_1_up_warning.setVisible(false);
                player_1_up_explode.setVisible(false);
                player_1_up_bomb.setVisible(false);
                player1_gameState.up_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_DOWN_Running1(boolean timer_DOWN_Running) {
            this.Timer_DOWN_Running = timer_DOWN_Running;
            if (Timer_DOWN_Running) {
                player_1_down_bomb.setVisible(true);
                down_timer = new Timer();
                down_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_DOWN_Running = false;
                        System.out.println("Timer ended up");
                        player_1_down_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image(player_1_normal, player_1_get_hurt,1);
                        player_1_HP.setPrefWidth(HP);
                        player_1_down_warning.opacityProperty().setValue(0);
                        player_1_down_explode.setVisible(true);
                        player1_gameState.down_has_boomb = false;
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_2_win, player_1_loss, player_2_W_or_L_background, player_1_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_1_normal.setVisible(false);
                                player_1_get_hurt.setVisible(true);
                                set_result_bar(1);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_down = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_1_down_explode.setVisible(false);
                }));
                T_bomb_down.play();
                player_1_down_warning.setVisible(true);
                player_1_down_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_1_down_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_down = new Timeline(keyFrame);
                T_waring_down .play();
                System.out.println("Timer started up");
            }
            else {
                down_timer.cancel();
                play_defense_sound();
                Timer_DOWN_Running = false;
                T_bomb_down.stop();
                T_waring_down.stop();
                T_bomb_down.playFromStart();
                T_waring_down.playFromStart();
                player_1_down_warning.setVisible(false);
                player_1_down_explode.setVisible(false);
                player_1_down_bomb.setVisible(false);
                player1_gameState.down_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_LEFT_Running1(boolean timer_LEFT_Running) {
            this.Timer_LEFT_Running = timer_LEFT_Running;
            if (Timer_LEFT_Running) {
                player_1_left_bomb.setVisible(true);
                left_timer = new Timer();
                left_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_LEFT_Running = false;
                        System.out.println("Timer ended up");
                        player_1_left_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image(player_1_normal, player_1_get_hurt,1);
                        player_1_HP.setPrefWidth(HP);
                        player_1_left_warning.opacityProperty().setValue(0);
                        player_1_left_explode.setVisible(true);
                        player1_gameState.left_has_boomb = false;
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_2_win, player_1_loss, player_2_W_or_L_background, player_1_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_1_normal.setVisible(false);
                                player_1_get_hurt.setVisible(true);
                                set_result_bar(1);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_left = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_1_left_explode.setVisible(false);
                }));
                T_bomb_left.play();
                player_1_left_warning.setVisible(true);
                player_1_left_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_1_left_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_left = new Timeline(keyFrame);
                T_waring_left .play();
                System.out.println("Timer started up");
            }
            else {
                left_timer.cancel();
                play_defense_sound();
                Timer_LEFT_Running = false;
                T_bomb_left.stop();
                T_waring_left.stop();
                T_bomb_left.playFromStart();
                T_waring_left.playFromStart();
                player_1_left_warning.setVisible(false);
                player_1_left_explode.setVisible(false);
                player_1_left_bomb.setVisible(false);
                player1_gameState.left_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_RIGHT_Running1(boolean timer_RIGHT_Running) {
            this.Timer_RIGHT_Running = timer_RIGHT_Running;
            if (Timer_RIGHT_Running) {
                player_1_right_bomb.setVisible(true);
                right_timer = new Timer();
                right_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_RIGHT_Running = false;
                        System.out.println("Timer ended up");
                        player_1_right_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image(player_1_normal, player_1_get_hurt,1);
                        player_1_HP.setPrefWidth(HP);
                        player_1_right_warning.opacityProperty().setValue(0);
                        player_1_right_explode.setVisible(true);
                        player1_gameState.right_has_boomb = false;
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_2_win, player_1_loss, player_2_W_or_L_background, player_1_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_1_normal.setVisible(false);
                                player_1_get_hurt.setVisible(true);
                                set_result_bar(1);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_right = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_1_right_explode.setVisible(false);
                }));
                T_bomb_right.play();
                player_1_right_warning.setVisible(true);
                player_1_right_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_1_right_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_right = new Timeline(keyFrame);
                T_waring_right .play();
                System.out.println("Timer started up");
            }
            else {
                right_timer.cancel();
                play_defense_sound();
                Timer_RIGHT_Running = false;
                T_bomb_right.stop();
                T_waring_right.stop();
                T_bomb_right.playFromStart();
                T_waring_right.playFromStart();
                player_1_right_warning.setVisible(false);
                player_1_right_explode.setVisible(false);
                player_1_right_bomb.setVisible(false);
                player1_gameState.right_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_UP_Running2(boolean timer_UP_Running) {
            this.Timer_UP_Running = timer_UP_Running;
            if (Timer_UP_Running) {
                player_2_up_bomb.setVisible(true);
                up_timer = new Timer();
                up_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_UP_Running = false;
                        System.out.println("Timer ended up");
                        player_2_up_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image(player_2_normal, player_2_get_hurt,2);
                        player_2_HP.setPrefWidth(HP);
                        player_2_up_warning.opacityProperty().setValue(0);
                        player_2_up_explode.setVisible(true);
                        player2_gameState.up_has_boomb = false;
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_1_win, player_2_loss, player_1_W_or_L_background, player_2_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_2_normal.setVisible(false);
                                player_2_get_hurt.setVisible(true);
                                set_result_bar(2);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_up = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_2_up_explode.setVisible(false);
                }));
                T_bomb_up.play();
                player_2_up_warning.setVisible(true);
                player_2_up_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_2_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_2_up_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_up = new Timeline(keyFrame);
                T_waring_up .play();
                System.out.println("Timer started up");
            }
            else {
                up_timer.cancel();
                play_defense_sound();
                Timer_UP_Running = false;
                T_bomb_up.stop();
                T_waring_up.stop();
                T_bomb_up.playFromStart();
                T_waring_up.playFromStart();
                player_2_up_warning.setVisible(false);
                player_2_up_explode.setVisible(false);
                player_2_up_bomb.setVisible(false);
                player2_gameState.up_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_DOWN_Running2(boolean timer_DOWN_Running) {
            this.Timer_DOWN_Running = timer_DOWN_Running;
            if (Timer_DOWN_Running) {
                player_2_down_bomb.setVisible(true);
                down_timer = new Timer();
                down_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_DOWN_Running = false;
                        System.out.println("Timer ended up");
                        player_2_down_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image(player_2_normal, player_2_get_hurt,2);
                        player_2_HP.setPrefWidth(HP);
                        player_2_down_warning.opacityProperty().setValue(0);
                        player_2_down_explode.setVisible(true);
                        player2_gameState.down_has_boomb = false;
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_1_win, player_2_loss, player_1_W_or_L_background, player_2_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_2_normal.setVisible(false);
                                player_2_get_hurt.setVisible(true);
                                set_result_bar(2);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_down = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_2_down_explode.setVisible(false);
                }));
                T_bomb_down.play();
                player_2_down_warning.setVisible(true);
                player_2_down_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_2_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_2_down_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_down = new Timeline(keyFrame);
                T_waring_down .play();
                System.out.println("Timer started up");
            }
            else {
                down_timer.cancel();
                play_defense_sound();
                Timer_DOWN_Running = false;
                T_bomb_down.stop();
                T_waring_down.stop();
                T_bomb_down.playFromStart();
                T_waring_down.playFromStart();
                player_2_down_warning.setVisible(false);
                player_2_down_explode.setVisible(false);
                player_2_down_bomb.setVisible(false);
                player2_gameState.down_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_LEFT_Running2(boolean timer_LEFT_Running) {
            this.Timer_LEFT_Running = timer_LEFT_Running;
            if (Timer_LEFT_Running) {
                player_2_left_bomb.setVisible(true);
                left_timer = new Timer();
                left_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_LEFT_Running = false;
                        System.out.println("Timer ended up");
                        player_2_left_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image(player_2_normal, player_2_get_hurt,2);
                        player_2_HP.setPrefWidth(HP);
                        player_2_left_warning.opacityProperty().setValue(0);
                        player_2_left_explode.setVisible(true);
                        player2_gameState.left_has_boomb = false;
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_1_win, player_2_loss, player_1_W_or_L_background, player_2_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_2_normal.setVisible(false);
                                player_2_get_hurt.setVisible(true);
                                set_result_bar(2);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_left = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_2_left_explode.setVisible(false);
                }));
                T_bomb_left.play();
                player_2_left_warning.setVisible(true);
                player_2_left_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_2_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_2_left_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_left = new Timeline(keyFrame);
                T_waring_left .play();
                System.out.println("Timer started up");
            }
            else {
                left_timer.cancel();
                play_defense_sound();
                Timer_LEFT_Running = false;
                T_bomb_left.stop();
                T_waring_left.stop();
                T_bomb_left.playFromStart();
                T_waring_left.playFromStart();
                player_2_left_warning.setVisible(false);
                player_2_left_explode.setVisible(false);
                player_2_left_bomb.setVisible(false);
                player2_gameState.left_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_RIGHT_Running2(boolean timer_RIGHT_Running) {
            this.Timer_RIGHT_Running = timer_RIGHT_Running;
            if (Timer_RIGHT_Running) {
                player_2_right_bomb.setVisible(true);
                right_timer = new Timer();
                right_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_RIGHT_Running = false;
                        System.out.println("Timer ended up");
                        player_2_right_bomb.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image(player_2_normal, player_2_get_hurt,2);
                        player_2_HP.setPrefWidth(HP);
                        player_2_right_warning.opacityProperty().setValue(0);
                        player_2_right_explode.setVisible(true);
                        player2_gameState.right_has_boomb = false;
                        if (HP<=0&&not_end) {
                            not_end=false;
                            player1_gameState.attack = false;
                            player2_gameState.attack = false;
                            show_game_result(player_1_win, player_2_loss, player_1_W_or_L_background, player_2_W_or_L_background);
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), attack -> {
                                player_2_normal.setVisible(false);
                                player_2_get_hurt.setVisible(true);
                                set_result_bar(2);
                            }));
                            timeline.play();

                        }
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_right = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    player_2_right_explode.setVisible(false);
                }));
                T_bomb_right.play();
                player_2_right_warning.setVisible(true);
                player_2_right_warning.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_2_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(player_2_right_warning.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_right = new Timeline(keyFrame);
                T_waring_right .play();
                System.out.println("Timer started up");
            }
            else {
                right_timer.cancel();
                play_defense_sound();
                Timer_RIGHT_Running = false;
                T_bomb_right.stop();
                T_waring_right.stop();
                T_bomb_right.playFromStart();
                T_waring_right.playFromStart();
                player_2_right_warning.setVisible(false);
                player_2_right_explode.setVisible(false);
                player_2_right_bomb.setVisible(false);
                player2_gameState.right_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
    }
    //按鍵控制
    @FXML
    protected void handleKeyPress_PVP(KeyEvent event) {
        activeKeys.add(event.getCode());
    }
    @FXML
    protected void handleKeyRelease_PVP(KeyEvent event) {
        for (KeyCode code : activeKeys){
            switch (code) {
                case W:
                    //有bomb
                    if (player1_gameState.isUp_has_boomb()) {
                        if (player1_gameState.attack) {
                            player1_gameState.setUp_has_boomb(false);
                            player1_gameState.setTimer_UP_Running1(false);
                            System.out.println("player1 delete boomb");
                        }
                    }
                    //沒bomb要設定->對面已經有bomb跟對面沒有bomb
                    else {
                        if (!player2_gameState.Timer_UP_Running) {
                            //設定對面的timer跟倒數
                            if (player1_gameState.attack) {
                                player2_gameState.setTimer_UP_Running2(true);
                                player2_gameState.setUp_has_boomb(true);
                                System.out.println("player2 up has boomb");
                                player1_gameState.attack=false;
                                C_D_change(player_1_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player1_gameState.attack = true;
                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                case A:
                    //有bomb
                    if (player1_gameState.isLeft_has_boomb()) {
                        if (player1_gameState.attack) {
                            player1_gameState.setLeft_has_boomb(false);
                            player1_gameState.setTimer_LEFT_Running1(false);
                            System.out.println("player1 delete boomb");
                        }
                    }
                    //沒bomb要設定->對面已經有bomb跟對面沒有bomb
                    else {
                        if (!player2_gameState.Timer_LEFT_Running){
                            //設定對面的timer跟倒數
                            if(player1_gameState.attack) {
                                player2_gameState.setTimer_LEFT_Running2(true);
                                player2_gameState.setLeft_has_boomb(true);
                                System.out.println("player2 up has boomb");
                                player1_gameState.attack=false;
                                C_D_change(player_1_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player1_gameState.attack = true;
                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                case S:
                    //有bomb
                    if (player1_gameState.isDown_has_boomb()) {
                        if (player1_gameState.attack) {
                            player1_gameState.setDown_has_boomb(false);
                            player1_gameState.setTimer_DOWN_Running1(false);
                            System.out.println("player1 delete boomb");
                        }
                    }
                    //沒bomb要設定->對面已經有bomb跟對面沒有bomb
                    else {
                        if (!player2_gameState.Timer_DOWN_Running){
                            //設定對面的timer跟倒數
                            if(player1_gameState.attack) {
                                player2_gameState.setTimer_DOWN_Running2(true);
                                player2_gameState.setDown_has_boomb(true);
                                System.out.println("player2 up has boomb");
                                player1_gameState.attack=false;
                                C_D_change(player_1_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player1_gameState.attack = true;

                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                case D:
                    //有bomb
                    if (player1_gameState.isRight_has_boomb()) {
                        if (player1_gameState.attack) {
                            player1_gameState.setRight_has_boomb(false);
                            player1_gameState.setTimer_RIGHT_Running1(false);
                            System.out.println("player1 delete boomb");
                        }
                    }
                    //沒bomb要設定->對面已經有bomb跟對面沒有bomb
                    else {
                        if (!player2_gameState.Timer_RIGHT_Running){
                            //設定對面的timer跟倒數
                            if(player1_gameState.attack) {
                                player2_gameState.setTimer_RIGHT_Running2(true);
                                player2_gameState.setRight_has_boomb(true);
                                System.out.println("player2 up has boomb");
                                player1_gameState.attack=false;
                                C_D_change(player_1_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player1_gameState.attack = true;

                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                case UP:
                    if (player2_gameState.isUp_has_boomb()) {
                        if (player2_gameState.attack) {
                            player2_gameState.setUp_has_boomb(false);
                            player2_gameState.setTimer_UP_Running2(false);
                            System.out.println("player2 delete boomb");
                        }
                    }
                    else {
                        if (!player1_gameState.Timer_UP_Running){
                            //設定對面的timer跟倒數
                            if (player2_gameState.attack) {
                                player1_gameState.setTimer_UP_Running1(true);
                                player1_gameState.setUp_has_boomb(true);
                                System.out.println("player1 up has boomb");
                                player2_gameState.attack=false;
                                C_D_change(player_2_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player2_gameState.attack = true;


                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                case LEFT:
                    if (player2_gameState.isLeft_has_boomb()) {
                        if (player2_gameState.attack) {
                            player2_gameState.setLeft_has_boomb(false);
                            player2_gameState.setTimer_LEFT_Running2(false);
                            System.out.println("player2 delete boomb");
                        }
                    }
                    else {
                        if (!player1_gameState.Timer_LEFT_Running){
                            //設定對面的timer跟倒數
                            if(player2_gameState.attack){
                                player1_gameState.setTimer_LEFT_Running1(true);
                                player1_gameState.setLeft_has_boomb(true);
                                System.out.println("player1 up has boomb");
                                player2_gameState.attack=false;
                                C_D_change(player_2_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player2_gameState.attack = true;

                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                case DOWN:
                    if (player2_gameState.isDown_has_boomb()) {
                        if (player2_gameState.attack) {
                            player2_gameState.setDown_has_boomb(false);
                            player2_gameState.setTimer_DOWN_Running2(false);
                            System.out.println("player2 delete boomb");
                        }
                    }
                    else {
                        if (!player1_gameState.Timer_DOWN_Running){
                            //設定對面的timer跟倒數
                            if(player2_gameState.attack) {
                                player1_gameState.setTimer_DOWN_Running1(true);
                                player1_gameState.setDown_has_boomb(true);
                                System.out.println("player1 up has boomb");
                                player2_gameState.attack=false;
                                C_D_change(player_2_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player2_gameState.attack = true;
                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                case RIGHT:
                    if (player2_gameState.isRight_has_boomb()) {
                        if (player2_gameState.attack) {
                            player2_gameState.setRight_has_boomb(false);
                            player2_gameState.setTimer_RIGHT_Running2(false);
                            System.out.println("player2 delete boomb");
                        }
                    }
                    else {
                        if (!player1_gameState.Timer_RIGHT_Running){
                            //設定對面的timer跟倒數
                            if(player2_gameState.attack) {
                                player1_gameState.setTimer_RIGHT_Running1(true);
                                player1_gameState.setRight_has_boomb(true);
                                System.out.println("player1 up has boomb");
                                player2_gameState.attack=false;
                                C_D_change(player_2_CD);
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    player2_gameState.attack = true;

                                }));
                                timeline.play();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        activeKeys.remove(event.getCode());
    }
    //回主畫面
    //右上角返回
    @FXML
    protected void  on_PVP_image_Click_back() throws IOException {
        PVE_difficulty=0.5;
        Timeline timeline = new Timeline();
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 獲取舞台
            Stage stage = (Stage) (back_PVP_icon).getScene().getWindow();
            // 設置新的場景
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("蹦蹦炸彈");
            //取得裡面的anchorplane
            AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
            //取得裡面的interface_image
            ImageView interface_image = (ImageView) anchorPane.getChildren().get(1);
            //取得裡面的interface_label
            Label interface_label = (Label) anchorPane.getChildren().get(2);
            //將interface_image移除
            anchorPane.getChildren().remove(interface_image);
            //將interface_label移除
            anchorPane.getChildren().remove(interface_label);
            // 顯示舞台
            stage.show();
        }));
        timeline1.play();
    }
    //結果顯示OK返回
    @FXML
    protected void  on_PVP_result_back() throws IOException {
        Timeline timeline = new Timeline();
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) (PVP_result_back_button).getScene().getWindow();
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("蹦蹦炸彈");
            stage.show();
        }));
        timeline1.play();
    }
    //PVE,training//共用
    //GAMESTATE
    boolean judge_over=false;
    class GameState_PVE {
        private boolean right_has_boomb;
        private boolean left_has_boomb;
        private boolean down_has_boomb;
        private boolean up_has_boomb;
        private int HP;
        private Timer up_timer;
        private Timeline T_bomb_up;
        private Timeline T_waring_up;
        private Timer down_timer;
        private Timeline T_bomb_down;
        private Timeline T_waring_down;
        private Timer left_timer;
        private Timeline T_bomb_left;
        private Timeline T_waring_left;
        private Timer right_timer;
        private Timeline T_bomb_right;
        private Timeline T_waring_right;
        private boolean Timer_UP_Running ;
        private boolean Timer_DOWN_Running ;
        private boolean Timer_LEFT_Running ;
        private boolean Timer_RIGHT_Running ;
        private boolean attack ;
        public GameState_PVE() {
            this.HP = 390;
            this.up_has_boomb = false;
            this.down_has_boomb = false;
            this.left_has_boomb = false;
            this.right_has_boomb = false;
            this.Timer_UP_Running = false;
            this.Timer_DOWN_Running = false;
            this.Timer_LEFT_Running = false;
            this.Timer_RIGHT_Running = false;
            this.attack = true;

        }
        public void setHP_for_training(){
            this.HP = 99999999;
        }
        public void setRight_has_boomb(boolean right_has_boomb) {
            this.right_has_boomb = right_has_boomb;
        }

        public void setLeft_has_boomb(boolean left_has_boomb) {
            this.left_has_boomb = left_has_boomb;
        }

        public void setDown_has_boomb(boolean down_has_boomb) {
            this.down_has_boomb = down_has_boomb;
        }

        public void setUp_has_boomb(boolean up_has_boomb) {
            this.up_has_boomb = up_has_boomb;
        }

        public boolean isRight_has_boomb() {
            return right_has_boomb;
        }

        public boolean isLeft_has_boomb() {
            return left_has_boomb;
        }

        public boolean isDown_has_boomb() {
            return down_has_boomb;
        }

        public boolean isUp_has_boomb() {
            return up_has_boomb;
        }
        //**********************************額外用function
        //結束畫面
        public void  show_game_result_PVE(boolean win_or_loss) {
            if (!win_or_loss) {
                PVE_get_hurt.setVisible(true);
                PVE_normal.setVisible(false);
                PVE_lose.setVisible(true);
                PVE_UP_LINE.setStyle("-fx-background-color: #ff0000");
                PVE_DOWN_LINE.setStyle("-fx-background-color: #ff0000");
                PVE_LEFT_LINE.setStyle("-fx-background-color: #ff0000");
                PVE_RIGHT_LINE.setStyle("-fx-background-color: #ff0000");
            }
            else {
                PVE_UP_LINE.setStyle("-fx-background-color: #00ff00");
                PVE_DOWN_LINE.setStyle("-fx-background-color: #00ff00");
                PVE_LEFT_LINE.setStyle("-fx-background-color: #00ff00");
                PVE_RIGHT_LINE.setStyle("-fx-background-color: #00ff00");
                PVE_win.setVisible(true);
            }
            PVE_difficulty=0.5;
            bot.attack=false;
            player1_in_PVE.attack=false;
            end_sound();
        }
        //受傷player
        void get_hurt_image_PVE() {
            PVE_get_hurt.setVisible(true);
            PVE_normal.setVisible(false);
            PVE_UP_LINE.setStyle("-fx-background-color: #ff0000");
            PVE_DOWN_LINE.setStyle("-fx-background-color: #ff0000");
            PVE_LEFT_LINE.setStyle("-fx-background-color: #ff0000");
            PVE_RIGHT_LINE.setStyle("-fx-background-color: #ff0000");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.4), attack -> {
                PVE_get_hurt.setVisible(false);
                PVE_normal.setVisible(true);
                PVE_UP_LINE.setStyle("-fx-background-color: #000065");
                PVE_DOWN_LINE.setStyle("-fx-background-color: #000065");
                PVE_LEFT_LINE.setStyle("-fx-background-color: #000065");
                PVE_RIGHT_LINE.setStyle("-fx-background-color: #000065");
            }));
            timeline.play();
        }
        //********************************************************************
        //player攻擊和防守
        public void setTimer_UP_Running1(boolean timer_UP_Running) {
            this.Timer_UP_Running = timer_UP_Running;
            if (Timer_UP_Running) {
                PVE_UP_BOMB.setVisible(true);
                up_timer = new Timer();
                up_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_UP_Running = false;
                        PVE_UP_BOMB.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        explode_time++;
                        get_hurt_image_PVE();
                        if (HP<=0) {
                            //player loss
                            random_move_time.stop();
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), attack -> {
                                if (!judge_over){
                                    show_game_result_PVE(false);
                                    judge_over=true;
                                }
                            }));
                            timeline.play();
                        }
                        PVE_player_HP.setPrefWidth(HP);
                        PVE_UP_WARNING.opacityProperty().setValue(0);
                        PVE_UP_EXPLODE.setVisible(true);
                        player1_in_PVE.up_has_boomb = false;

                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_up = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    PVE_UP_EXPLODE.setVisible(false);
                }));
                T_bomb_up.play();
                PVE_UP_WARNING.setVisible(true);
                PVE_UP_WARNING.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(PVE_UP_WARNING.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_up = new Timeline(keyFrame);
                T_waring_up.play();
                System.out.println("Timer started up");
            }
            else {
                up_timer.cancel();
                play_defense_sound();
                Timer_UP_Running = false;
                T_bomb_up.stop();
                T_waring_up.stop();
                T_bomb_up.playFromStart();
                T_waring_up.playFromStart();
                PVE_UP_WARNING.setVisible(false);
                PVE_UP_EXPLODE.setVisible(false);
                PVE_UP_BOMB.setVisible(false);
                player1_in_PVE.up_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_DOWN_Running1(boolean timer_DOWN_Running) {
            this.Timer_DOWN_Running = timer_DOWN_Running;
            if (Timer_DOWN_Running) {
                PVE_DOWN_BOMB.setVisible(true);
                down_timer = new Timer();
                down_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_DOWN_Running = false;
                        PVE_DOWN_BOMB.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image_PVE();
                        explode_time++;
                        if (HP<=0) {
                            //player loss
                            random_move_time.stop();
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), attack -> {
                                if (!judge_over){
                                    show_game_result_PVE(false);
                                    judge_over=true;
                                }
                            }));
                            timeline.play();
                        }
                        PVE_player_HP.setPrefWidth(HP);
                        PVE_DOWN_WARNING.opacityProperty().setValue(0);
                        PVE_DOWN_EXPLODE.setVisible(true);
                        player1_in_PVE.down_has_boomb = false;
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_down = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    PVE_DOWN_EXPLODE.setVisible(false);
                }));
                T_bomb_down.play();
                PVE_DOWN_WARNING.setVisible(true);
                PVE_DOWN_WARNING.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(PVE_DOWN_WARNING.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_down = new Timeline(keyFrame);
                T_waring_down.play();
                System.out.println("Timer started up");
            }
            else {
                down_timer.cancel();
                play_defense_sound();
                Timer_DOWN_Running = false;
                T_bomb_down.stop();
                T_waring_down.stop();
                T_bomb_down.playFromStart();
                T_waring_down.playFromStart();
                PVE_DOWN_WARNING.setVisible(false);
                PVE_DOWN_EXPLODE.setVisible(false);
                PVE_DOWN_BOMB.setVisible(false);
                player1_in_PVE.down_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_LEFT_Running1(boolean timer_LEFT_Running) {
            this.Timer_LEFT_Running = timer_LEFT_Running;
            if (Timer_LEFT_Running) {
                PVE_LEFT_BOMB.setVisible(true);
                left_timer = new Timer();
                left_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_LEFT_Running = false;
                        PVE_LEFT_BOMB.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image_PVE();
                        explode_time++;
                        if (HP <= 0) {
                            //player loss
                            random_move_time.stop();
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), attack -> {
                                if (!judge_over){
                                    show_game_result_PVE(false);
                                    judge_over=true;
                                }
                            }));
                            timeline.play();
                        }
                        PVE_player_HP.setPrefWidth(HP);
                        PVE_LEFT_WARNING.opacityProperty().setValue(0);
                        PVE_LEFT_EXPLODE.setVisible(true);
                        player1_in_PVE.left_has_boomb = false;
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_left = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    PVE_LEFT_EXPLODE.setVisible(false);
                }));
                T_bomb_left.play();
                PVE_LEFT_WARNING.setVisible(true);
                PVE_LEFT_WARNING.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(PVE_LEFT_WARNING.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_left = new Timeline(keyFrame);
                T_waring_left.play();
                System.out.println("Timer started up");
            } else {
                left_timer.cancel();
                play_defense_sound();
                Timer_LEFT_Running = false;
                T_bomb_left.stop();
                T_waring_left.stop();
                T_bomb_left.playFromStart();
                T_waring_left.playFromStart();
                PVE_LEFT_WARNING.setVisible(false);
                PVE_LEFT_EXPLODE.setVisible(false);
                PVE_LEFT_BOMB.setVisible(false);
                player1_in_PVE.down_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void setTimer_RIGHT_Running1(boolean timer_RIGHT_Running) {
            this.Timer_RIGHT_Running = timer_RIGHT_Running;
            if (Timer_RIGHT_Running) {
                PVE_RIGHT_BOMB.setVisible(true);
                right_timer = new Timer();
                right_timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Timer_RIGHT_Running = false;
                        //System.out.println("Timer ended up");
                        PVE_RIGHT_BOMB.setVisible(false);
                        HP -= 38;
                        play_explode_sound();
                        get_hurt_image_PVE();
                        explode_time++;
                        if (HP <= 0) {
                            //player loss
                            random_move_time.stop();
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), attack -> {
                                if (!judge_over){
                                    show_game_result_PVE(false);
                                    judge_over=true;
                                }
                            }));
                            timeline.play();
                        }
                        PVE_player_HP.setPrefWidth(HP);
                        PVE_RIGHT_WARNING.opacityProperty().setValue(0);
                        PVE_RIGHT_EXPLODE.setVisible(true);
                        player1_in_PVE.right_has_boomb = false;
                    }
                }, 1020); // 倒數計時器設定為3秒
                T_bomb_right = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> {
                    PVE_RIGHT_EXPLODE.setVisible(false);
                }));
                T_bomb_right.play();
                PVE_RIGHT_WARNING.setVisible(true);
                PVE_RIGHT_WARNING.opacityProperty().setValue(0);
                // 創建一個KeyValue，表示在3秒後，player_1_up_warning的透明度變為0
                KeyValue keyValue = new KeyValue(PVE_RIGHT_WARNING.opacityProperty(), 1);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                T_waring_right = new Timeline(keyFrame);
                T_waring_right.play();
                System.out.println("Timer started up");
            }
            else {
                right_timer.cancel();
                play_defense_sound();
                Timer_RIGHT_Running = false;
                T_bomb_right.stop();
                T_waring_right.stop();
                T_bomb_right.playFromStart();
                T_waring_right.playFromStart();
                PVE_RIGHT_WARNING.setVisible(false);
                PVE_RIGHT_EXPLODE.setVisible(false);
                PVE_RIGHT_BOMB.setVisible(false);
                player1_in_PVE.right_has_boomb = false;
                System.out.println("Timer cancelled up");
            }
        }
        public void minus_bot_HP() {
            HP -= 39;
            PVE_BOT_HP.setPrefWidth(HP);
            bot.up_has_boomb = false;
            if (HP<=0) {
                random_move_time.stop();
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                    random_move_time.pause();
                }));
                timeline.play();

                Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), attack -> {
                    if (!judge_over){
                        show_game_result_PVE(true);
                        judge_over=true;
                    }
                }));
                timeline1.play();
            }
        }
    }
    //電腦數據
    void random_bot_move(){
        Random rand = new Random();
        int n = rand.nextInt(4);
        switch (n){
            case 0:
                //up
                if (!bot.Timer_UP_Running) {
                    //bot.setTimer_UP_Running2(true);
                    //bot.setUp_has_boomb(true);
                    System.out.println("bot up has boomb");

                    if (!player1_in_PVE.Timer_UP_Running){

                        //設定對面的timer跟倒數
                        //attack看cd時間
                        if (bot.attack) {
                            player1_in_PVE.setTimer_UP_Running1(true);
                            player1_in_PVE.setUp_has_boomb(true);
                            System.out.println("player1 up has boomb");

                        }
                    }
                }
                break;
            case 1:
                if (!bot.Timer_DOWN_Running) {
                    //bot.setTimer_DOWN_Running2(true);
                    //bot.setDown_has_boomb(true);
                    System.out.println("bot down has boomb");

                    if (!player1_in_PVE.Timer_DOWN_Running){
                        //設定對面的timer跟倒數
                        //attack看cd時間
                        if (bot.attack) {
                            player1_in_PVE.setTimer_DOWN_Running1(true);
                            player1_in_PVE.setDown_has_boomb(true);
                            System.out.println("player1 down has boomb");
                        }
                    }
                }
                break;
            case 2:
                if (!bot.Timer_LEFT_Running) {
                    //bot.setTimer_LEFT_Running2(true);
                    //bot.setLeft_has_boomb(true);
                    System.out.println("bot left has boomb");

                    if (!player1_in_PVE.Timer_LEFT_Running){
                        //設定對面的timer跟倒數
                        //attack看cd時間
                        if (bot.attack) {
                            player1_in_PVE.setTimer_LEFT_Running1(true);
                            player1_in_PVE.setLeft_has_boomb(true);
                            System.out.println("player1 left has boomb");
                        }
                    }
                }
                break;
            case 3:
                if (!bot.Timer_RIGHT_Running) {
                    //bot.setTimer_RIGHT_Running2(true);
                    //bot.setRight_has_boomb(true);
                    System.out.println("bot right has boomb");

                    if (!player1_in_PVE.Timer_RIGHT_Running) {
                        //設定對面的timer跟倒數
                        //attack看cd時間
                        if (bot.attack) {
                            player1_in_PVE.setTimer_RIGHT_Running1(true);
                            player1_in_PVE.setRight_has_boomb(true);
                            System.out.println("player1 right has boomb");
                        }
                    }
                }
                break;
        }
    }
    //設置血量
    @FXML
    protected void handleKeyPress_PVE(KeyEvent event) {
        activeKeys_PVE.add(event.getCode());
        System.out.println("training mode:" + training_mode);
        if(training_mode) {
            player1_in_PVE.setHP_for_training();
            bot.setHP_for_training();
        }
    }
    //按鍵控制
    @FXML
    protected void handleKeyRelease_PVE(KeyEvent event) {
        //*************************************************************
        //random執行一次
        if (record_time<=0){
            // 創建一個新的KeyFrame，並將其添加到Timeline中
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(PVE_difficulty), event1 -> {
                // 在這裡添加您希望在每個時間間隔執行的代碼
                System.out.println("This line is printed every second.");
                random_bot_move();
            });
            //記錄每5秒進攻次數
            if (training_mode){
                // 創建一個新的KeyFrame，並將其添加到Timeline中
                KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(5), event1 -> {
                    // 在這裡添加您希望在每個時間間隔執行的代碼
                    attack_times.setText(attack_time+"次");
                    attack_time = 0;
                });
                // 將KeyFrame添加到Timeline
                record_attack_5s.getKeyFrames().add(keyFrame1);


                // rate change
                KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(1), event1 -> {
                    // 在這裡添加您希望在每個時間間隔執行的代碼
                    double rate = ((double)player_defense_time/((double)explode_time+(double)player_defense_time))*100;
                    String formatted_rate = String.format("%.2f", rate);
                    defend_percentage.setText(formatted_rate+"%");
                });
                // 將KeyFrame添加到Timeline
                record_defense_rate.getKeyFrames().add(keyFrame2);

                KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), event1 -> {
                    // 在這裡添加您希望在每個時間間隔執行的代碼
                    show_time++;
                    defend_time.setText(show_time+"秒");
                });
                // 將KeyFrame添加到Timeline
                show_game_times.getKeyFrames().add(keyFrame3);

            }
            if (crazy_mode){
                crazy_player_image.setVisible(true);
            }
            //crazy_player_image.setVisible(true);
            //crazy_mode=true;
            player_defense_time=0;
            explode_time=0;
            show_time=0;
            attack_time=0;

            // 將KeyFrame添加到Timeline
            random_move_time.getKeyFrames().add(keyFrame);

            // 設置Timeline的循環次數為無窮大，這樣它就會一直運行下去
            random_move_time.setCycleCount(Timeline.INDEFINITE);
            random_move_time.play();
            record_attack_5s.setCycleCount(Timeline.INDEFINITE);
            record_attack_5s.play();
            record_defense_rate.setCycleCount(Timeline.INDEFINITE);
            record_defense_rate.play();
            show_game_times.setCycleCount(Timeline.INDEFINITE);
            show_game_times.play();
            //記得做stop
            System.out.println("start");
            record_time++;
        }
        //********************************************************************
        //key events
        for (KeyCode code : activeKeys_PVE){

            switch (code) {
                case W:
                    System.out.println("W released");
                    //有bomb刪除掉
                    if (player1_in_PVE.isUp_has_boomb()) {//*******
                        if (player1_in_PVE.attack) {
                            player_defense_time++;
                            player1_in_PVE.setUp_has_boomb(false);//*******
                            player1_in_PVE.setTimer_UP_Running1(false);//******
                            System.out.println("player delete boomb");
                        }
                    }
                    //沒bomb要設定->機率讓對面扣血
                    //50%觸發
                    else {
                        Random rand = new Random();
                        int randomNum = rand.nextInt(100);  // 生成一個0到99的隨機數字
                        if(player1_in_PVE.attack){
                            //紀錄次數
                            attack_time++;
                            play_explode_sound();
                            C_D_change(PVE_CD);
                            player1_in_PVE.attack = false;
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                player1_in_PVE.attack = true;
                            }));
                            timeline.play();
                            if (randomNum < 50) {
                                System.out.println("爆炸");
                                Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    bot.minus_bot_HP();
                                }));
                                timeline1.play();

                            } else {
                                System.out.println("不觸發程式");
                            }
                        }
                    }
                    break;
                case A:
                    //有bomb
                    if (player1_in_PVE.isLeft_has_boomb()) {
                        if (player1_in_PVE.attack) {
                            player_defense_time++;
                            player1_in_PVE.setLeft_has_boomb(false);
                            player1_in_PVE.setTimer_LEFT_Running1(false);
                            System.out.println("player1 delete boomb");
                        }
                    }
                    //沒bomb要設定->機率讓對面扣血
                    //50%觸發
                    else {
                        Random rand = new Random();
                        int randomNum = rand.nextInt(100);  // 生成一個0到99的隨機數字
                        if(player1_in_PVE.attack){
                            attack_time++;
                            play_explode_sound();
                            C_D_change(PVE_CD);
                            player1_in_PVE.attack = false;
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                player1_in_PVE.attack = true;
                            }));
                            timeline.play();
                            if (randomNum < 50) {
                                System.out.println("爆炸");
                                Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    bot.minus_bot_HP();
                                }));
                                timeline1.play();

                            } else {
                                System.out.println("不觸發程式");
                            }
                        }
                    }
                    break;
                case S:
                    //有bomb
                    if (player1_in_PVE.isDown_has_boomb()) {
                        if (player1_in_PVE.attack) {
                            player_defense_time++;
                            player1_in_PVE.setDown_has_boomb(false);
                            player1_in_PVE.setTimer_DOWN_Running1(false);
                            System.out.println("player1 delete boomb");
                        }
                    }
                    //沒bomb要設定->機率讓對面扣血
                    //50%觸發
                    else {
                        Random rand = new Random();
                        int randomNum = rand.nextInt(100);  // 生成一個0到99的隨機數字
                        if(player1_in_PVE.attack){
                            attack_time++;
                            play_explode_sound();
                            C_D_change(PVE_CD);
                            player1_in_PVE.attack = false;
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                player1_in_PVE.attack = true;
                            }));
                            timeline.play();
                            if (randomNum < 50) {
                                System.out.println("爆炸");
                                Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    bot.minus_bot_HP();
                                }));
                                timeline1.play();

                            } else {
                                System.out.println("不觸發程式");
                            }
                        }
                    }
                    break;
                case D:
                    //有bomb
                    if (player1_in_PVE.isRight_has_boomb()) {
                        if (player1_in_PVE.attack) {
                            player_defense_time++;
                            player1_in_PVE.setRight_has_boomb(false);
                            player1_in_PVE.setTimer_RIGHT_Running1(false);
                            System.out.println("player1 delete boomb");
                        }
                    }
                    //沒bomb要設定->機率讓對面扣血
                    //50%觸發
                    else {
                        Random rand = new Random();
                        int randomNum = rand.nextInt(100);  // 生成一個0到99的隨機數字
                        if(player1_in_PVE.attack){
                            attack_time++;
                            play_explode_sound();
                            C_D_change(PVE_CD);
                            player1_in_PVE.attack = false;
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                player1_in_PVE.attack = true;
                            }));
                            timeline.play();
                            if (randomNum < 50) {
                                System.out.println("爆炸");
                                Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), attack -> {
                                    bot.minus_bot_HP();
                                }));
                                timeline1.play();
                            } else {
                                System.out.println("不觸發程式");
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        activeKeys_PVE.remove(event.getCode());
    }
    //PVE回主畫面
    @FXML
    protected void  on_PVE_image_Click_back() throws IOException {
        random_move_time.pause();
        crazy_player_image.setVisible(false);
        attack_time = 0;
        explode_time = 0;
        player_defense_time = 0;
        show_time = 0;
        PVE_difficulty=0.5;
        training_mode=false;
        crazy_mode=false;
        Timeline timeline = new Timeline();
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 獲取舞台
            Stage stage = (Stage) (back_PVE_icon).getScene().getWindow();
            // 設置新的場景
            stage.setScene(new Scene(root, 960, 540));
            //取得裡面的anchorplane
            AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
            //取得裡面的interface_image
            ImageView interface_image = (ImageView) anchorPane.getChildren().get(1);
            //取得裡面的interface_label
            Label interface_label = (Label) anchorPane.getChildren().get(2);
            //將interface_image移除
            anchorPane.getChildren().remove(interface_image);
            //將interface_label移除
            anchorPane.getChildren().remove(interface_label);
            stage.setTitle("蹦蹦炸彈");
            // 顯示舞台
            stage.show();
        }));
        timeline1.play();
    }
    //training 回主畫面
    @FXML
    protected void  on_Training_image_Click_back() throws IOException {
        random_move_time.pause();
        record_defense_rate.pause();
        record_attack_5s.pause();
        show_game_times.pause();
        //crazy_player_image.setVisible(false);
        attack_time = 0;
        explode_time = 0;
        player_defense_time = 0;
        show_time = 0;
        PVE_difficulty=0.5;
        training_mode=false;
        crazy_mode=false;
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 獲取舞台
            Stage stage = (Stage) (back_training_icon).getScene().getWindow();
            // 設置新的場景
            stage.setScene(new Scene(root, 960, 540));
            //取得裡面的anchorplane
            AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
            //取得裡面的interface_image
            ImageView interface_image = (ImageView) anchorPane.getChildren().get(1);
            //取得裡面的interface_label
            Label interface_label = (Label) anchorPane.getChildren().get(2);
            //將interface_image移除
            anchorPane.getChildren().remove(interface_image);
            //將interface_label移除
            anchorPane.getChildren().remove(interface_label);
            stage.setTitle("蹦蹦炸彈");
            // 顯示舞台
            stage.show();
        }));
        timeline1.play();
    }
}