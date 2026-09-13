# 蹦蹦炸彈 (Bomb Bomb)

JavaFX 製作的雙人對戰小遊戲，中央大學 Java 程式設計期末專題（第 B18 組）。

不是傳統走迷宮的炸彈超人 —— 兩位玩家各自守著上下左右四條線道，按方向鍵把炸彈丟到對手對應的線道，或是拆掉自己線道上的炸彈。炸彈有倒數，時間到就爆炸扣血；出手後有 0.5 秒冷卻，搶節奏是勝負關鍵。

## 遊戲模式

| 模式 | 說明 |
|---|---|
| **PVP** | 雙人同機對戰，左右各一位玩家 |
| **PVE** | 單人對電腦，可選簡單 / 普通 / 困難三種難度 |
| **Training** | 練習模式，熟悉線道與拆彈時機 |

## 操作方式

| | 上 | 左 | 下 | 右 |
|---|---|---|---|---|
| **玩家一**（PVP / PVE） | `W` | `A` | `S` | `D` |
| **玩家二**（PVP） | `↑` | `←` | `↓` | `→` |

按下方向鍵的行為會依線道狀態而定：

- 自己該線道**有炸彈** → 拆彈
- 自己該線道**沒炸彈** → 往對手同一條線道送一顆炸彈

兩種動作都會觸發 0.5 秒的攻擊冷卻。

## 執行方式

需要 **JDK 19**（`pom.xml` 的 source/target 設為 19）。

```bash
./mvnw javafx:run
```

Windows 上若 `JAVA_HOME` 沒有指向 JDK 19，先設定：

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-19"
```

也可以直接用 Maven：

```bash
mvn clean javafx:run
```

## 技術架構

- **JavaFX 19** — UI 全部用 FXML 描述，搭配 CSS 做主選單、難度選單與滑桿樣式
- **`HelloApplication`** — 進入點，載入 `hello-view.fxml` 主選單
- **`HelloController`** — 遊戲主邏輯（約 2600 行）：線道狀態機、炸彈計時器、爆炸判定、AI 行為、勝負結算
- **`music_controll`** — 背景音樂與音效控制，設定頁可調音量與靜音
- **`javafx.animation.Timeline`** — 炸彈倒數、冷卻、動畫全部用 Timeline 驅動

```
src/main/
├── java/assignment/demo/
│   ├── HelloApplication.java     進入點
│   ├── HelloController.java      遊戲主邏輯
│   └── music_controll.java       音樂控制
└── resources/
    ├── assignment/demo/          FXML 版面與 CSS
    ├── images/                   角色、炸彈、爆炸、勝負畫面
    ├── game_audio/               背景音樂
    └── sound_effect/             音效
```

## 已知問題

主選單的視窗圖示用的是相對路徑 `file:src/main/resources/images/bomb.png`，所以必須從專案根目錄啟動，換到別的工作目錄圖示會載不出來（不影響遊戲執行）。
