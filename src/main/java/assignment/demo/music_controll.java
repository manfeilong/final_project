package assignment.demo;

import javafx.scene.media.MediaPlayer;

import java.io.File;

public class music_controll {

    public static int now_play = 0;
    public static int volume = 50;
    public static boolean is_playing = false;
    public static javafx.scene.media.Media media=new javafx.scene.media.Media(new File("src/main/resources/game_audio/cruising-down.mp3").toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(media);


    music_controll(){
        //播放音樂
        //String path = "src/main/resources/sound_effect/1.mp3";
        String []  path_collect= new String[6];
        path_collect[0] = "src/main/resources/game_audio/cruising-down.mp3";
        path_collect[1] = "src/main/resources/game_audio/push-onward.mp3";
        path_collect[2] = "src/main/resources/game_audio/pixel-wild.mp3";
        path_collect[3] = "src/main/resources/game_audio/8-bit-march.mp3";
        path_collect[4] = "src/main/resources/game_audio/8-bit-game.mp3";
        path_collect[5] = "src/main/resources/game_audio/8-bit-arcade.mp3";

        //check file exist
        // 創建一個File對象
        File file = new File(path_collect[now_play]); // 替換為你的檔案路徑

        // 使用exists()方法檢查檔案是否存在
        if (file.exists()) {
            System.out.println("The file exists.");
        } else {
            System.out.println("The file does not exist.");
        }
        media= new javafx.scene.media.Media(new File(path_collect[now_play]).toURI().toString());
    }


    void change_volume(int i) {
        mediaPlayer.setVolume((double) i /100);

    }

    void stop_music() {
        //停止音樂
        mediaPlayer.stop();
    }

    void play_music() {
        //播放音樂
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    int get_now_play() {
        //播放音樂
        return now_play;
    }

    void set_now_play(int i) {
        //播放音樂
        String []  path_collect= new String[6];
        path_collect[0] = "src/main/resources/game_audio/cruising-down.mp3";
        path_collect[1] = "src/main/resources/game_audio/push-onward.mp3";
        path_collect[2] = "src/main/resources/game_audio/pixel-wild.mp3";
        path_collect[3] = "src/main/resources/game_audio/8-bit-march.mp3";
        path_collect[4] = "src/main/resources/game_audio/8-bit-game.mp3";
        path_collect[5] = "src/main/resources/game_audio/8-bit-arcade.mp3";
        now_play = i;
        media= new javafx.scene.media.Media(new File(path_collect[now_play]).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    //url("src/main/resources/images/interface_background.png")
    public void  music_function(int i) {
        now_play = i;
        //播放音樂
        //String path = "src/main/resources/sound_effect/1.mp3";
        String []  path_collect= new String[6];
        path_collect[0] = "src/main/resources/game_audio/cruising-down.mp3";
        path_collect[1] = "src/main/resources/game_audio/push-onward.mp3";
        path_collect[2] = "src/main/resources/game_audio/pixel-wild.mp3";
        path_collect[3] = "src/main/resources/game_audio/8-bit-march.mp3";
        path_collect[4] = "src/main/resources/game_audio/8-bit-game.mp3";
        path_collect[5] = "src/main/resources/game_audio/8-bit-arcade.mp3";

        //check file exist
        // 創建一個File對象
        File file = new File(path_collect[now_play]); // 替換為你的檔案路徑

        // 使用exists()方法檢查檔案是否存在
        if (file.exists()) {
            System.out.println("The file exists.");
        } else {
            System.out.println("The file does not exist.");
        }
        media= new javafx.scene.media.Media(new File(path_collect[now_play]).toURI().toString());
        //MediaPlayer mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setVolume((double) volume /100);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);



    }


}
