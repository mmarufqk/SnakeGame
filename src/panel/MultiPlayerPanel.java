package panel;

import javax.swing.JPanel;
import game.SnakeGame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import utils.FontLoader;
import utils.SoundLoader;

public class MultiPlayerPanel extends JPanel {
    private BufferedImage GameBackgroundImage;
    private SnakeGame player1GamePanel;
    private SnakeGame player2GamePanel;
    private boolean player1IsGameOver = false;
    private boolean player2IsGameOver = false;
    private boolean isPaused = false;
    private boolean gameFinished = false;
    private String namePlayer1;
    private String namePlayer2;
    private int gameSpeedDelay;
    private String player1SnakeColor;
    private String player2SnakeColor;
    private boolean hasBorder;
    private Timer gameEndCheckerTimer;

    public MultiPlayerPanel() {
        this("Pemain 1", "Pemain 2", 100, "Green", "Blue", true);
    }

    public MultiPlayerPanel(String player1Name, String player2Name, int gameSpeedDelay, String p1SnakeColor, String p2SnakeColor, boolean borderOption) {
        initComponents();
        this.GameBackgroundImage = utils.ImageLoader.loadMultiPlayerBackgroundImage(this.Background);
        this.namePlayer1 = player1Name;
        this.namePlayer2 = player2Name;
        this.gameSpeedDelay = gameSpeedDelay;
        this.player1SnakeColor = p1SnakeColor;
        this.player2SnakeColor = p2SnakeColor;
        this.hasBorder = borderOption;

        if (!SoundLoader.isBackgroundMusicPlaying()) {
            SoundLoader.playBackgroundMusic();
        }

        FontLoader.applyFont(LabelSkorPlayer1, 16f);
        FontLoader.applyFont(SkorPlayer1, 16f);
        FontLoader.applyFont(NamePlayer1, 16f);
        NamePlayer1.setText("Nama : " + this.namePlayer1);
        FontLoader.applyFont(LabelSkorPlayer2, 16f);
        FontLoader.applyFont(SkorPlayer2, 16f);
        FontLoader.applyFont(NamePlayer2, 16f);
        NamePlayer2.setText("Nama : " + this.namePlayer2);
        FontLoader.applyFont(LabelProperties, 16f);
        LabelProperties.setText("Speed: " + getSpeedLabel(this.gameSpeedDelay) +
                ", Color 1: " + this.player1SnakeColor +
                ", Color 2: " + this.player2SnakeColor +
                ", Border: " + (this.hasBorder ? "Yes" : "No"));
        FontLoader.applyFont(ButtonBack, 12f);
        FontLoader.applyFont(ButtonPause, 12f);
        FontLoader.applyFont(ButtonRestart, 12f);

        int player1Width = 380;
        int player1Height = 420;
        int player2Width = 380;
        int player2Height = 420;
        player1GamePanel = new SnakeGame(player1Width, player1Height, this.gameSpeedDelay, this.player1SnakeColor, this.hasBorder);
        player2GamePanel = new SnakeGame(player2Width, player2Height, this.gameSpeedDelay, this.player2SnakeColor, this.hasBorder);
        PlayerArea1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        PlayerArea1.add(player1GamePanel, gbc);
        PlayerArea2.setLayout(new GridBagLayout());
        PlayerArea2.add(player2GamePanel, gbc);
        player1GamePanel.setScoreUpdateListener(new SnakeGame.ScoreUpdateListener() {
            @Override
            public void onScoreUpdate(int score) {
                SkorPlayer1.setText(String.valueOf(score));
            }
            @Override
            public void onGameOver(int finalScore) {
                if (!player1IsGameOver) {
                    player1IsGameOver = true;
                    player1GamePanel.setGameOver();
                }
            }
            @Override
            public void onGameRestarted() {
                SkorPlayer1.setText("0");
                player1IsGameOver = false;
                gameFinished = false;
                ButtonPause.setEnabled(true);
                ButtonRestart.setEnabled(true);
            }
        });
        player2GamePanel.setScoreUpdateListener(new SnakeGame.ScoreUpdateListener() {
            @Override
            public void onScoreUpdate(int score) {
                SkorPlayer2.setText(String.valueOf(score));
            }
            @Override
            public void onGameOver(int finalScore) {
                if (!player2IsGameOver) {
                    player2IsGameOver = true;
                    player2GamePanel.setGameOver();
                }
            }
            @Override
            public void onGameRestarted() {
                SkorPlayer2.setText("0");
                player2IsGameOver = false;
                gameFinished = false;
                ButtonPause.setEnabled(true);
                ButtonRestart.setEnabled(true);
            }
        });
        gameEndCheckerTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1IsGameOver && player2IsGameOver && !gameFinished) {
                    handleGameEnd();
                }
            }
        });
        gameEndCheckerTimer.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(new MyMultiplayerKeyAdapter());
        ButtonPause.setEnabled(false); 
        ButtonRestart.setEnabled(true); 
        ButtonBack.setEnabled(true);
        ButtonPause.setFocusable(false);
        ButtonRestart.setFocusable(false);
        ButtonBack.setFocusable(false);
        jTextField1.setFocusable(false);
        requestFocusInWindow();
        player1GamePanel.requestFocusInWindow();
        player2GamePanel.requestFocusInWindow();
    }

    private String getSpeedLabel(int delay) {
        if (delay <= 75) return "Fast";
        if (delay <= 100) return "Normal";
        return "Slow";
    }

    private void handleGameEnd() {
        System.out.println("[DEBUG] handleGameEnd dipanggil, gameFinished=" + gameFinished + ", p1=" + player1IsGameOver + ", p2=" + player2IsGameOver);
        if (gameFinished) return;
        if (gameEndCheckerTimer != null && gameEndCheckerTimer.isRunning()) {
            gameEndCheckerTimer.stop();
        }
        ButtonPause.setEnabled(false);
        ButtonRestart.setEnabled(true);
        ButtonBack.setEnabled(true);
        int score1 = player1GamePanel.getScore();
        int score2 = player2GamePanel.getScore();
        player1GamePanel.setGameOver();
        player2GamePanel.setGameOver();
        String message;
        String title = "Game Over!";
        if (score1 > score2) {
            message = "Kiri menang! Skor: " + score1 + " vs " + score2;
        } else if (score2 > score1) {
            message = "Kanan menang! Skor: " + score2 + " vs " + score1;
        } else {
            message = "Seri! Skor: " + score1 + " vs " + score2;
        }
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        utils.CustomConfirmDialog dialog = new utils.CustomConfirmDialog(parentWindow, message, title, false);
        dialog.showDialog();
        gameFinished = true;
    }

    private class MyMultiplayerKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_SPACE) {
                if (player1GamePanel.isGameOver() && player2GamePanel.isGameOver()) {
                    player1IsGameOver = false;
                    player2IsGameOver = false;
                    gameFinished = false;
                    player1GamePanel.resetGame();
                    player2GamePanel.resetGame();
                    ButtonPause.setText("Pause");
                    isPaused = false;
                    ButtonPause.setEnabled(false);
                    ButtonRestart.setEnabled(true);
                    if (gameEndCheckerTimer != null && !gameEndCheckerTimer.isRunning()) {
                        gameEndCheckerTimer.start();
                    }
                    player1GamePanel.requestFocusInWindow();
                    player2GamePanel.requestFocusInWindow();
                    MultiPlayerPanel.this.requestFocusInWindow();
                    return;
                }
                if (isPaused && (player1GamePanel.isGameStarted() || player2GamePanel.isGameStarted()) && !(player1GamePanel.isGameOver() && player2GamePanel.isGameOver())) {
                    player1GamePanel.resumeGame();
                    player2GamePanel.resumeGame();
                    ButtonPause.setText("Pause");
                    isPaused = false;
                    player1GamePanel.requestFocusInWindow();
                    player2GamePanel.requestFocusInWindow();
                    MultiPlayerPanel.this.requestFocusInWindow();
                    return;
                }
                if ((player1GamePanel.isRunning() || player2GamePanel.isRunning()) && !isPaused) {
                    player1GamePanel.stopGame();
                    player2GamePanel.stopGame();
                    ButtonPause.setText("Resume");
                    isPaused = true;
                    MultiPlayerPanel.this.requestFocusInWindow();
                    return;
                }                
                if (!player1GamePanel.isGameStarted() && !player1GamePanel.isRunning() && !player1GamePanel.isGameOver() &&
                    !player2GamePanel.isGameStarted() && !player2GamePanel.isRunning() && !player2GamePanel.isGameOver()) {
                    player1GamePanel.startGame();
                    player2GamePanel.startGame();
                    ButtonPause.setEnabled(true);
                    ButtonPause.setText("Pause");
                    isPaused = false;
                    player1GamePanel.requestFocusInWindow();
                    player2GamePanel.requestFocusInWindow();
                    MultiPlayerPanel.this.requestFocusInWindow();
                    return;
                }
            }
            if ((player1GamePanel.isRunning() && !isPaused)) {
                switch (keyCode) {
                    case KeyEvent.VK_W: player1GamePanel.setDirection('U'); break;
                    case KeyEvent.VK_S: player1GamePanel.setDirection('D'); break;
                    case KeyEvent.VK_A: player1GamePanel.setDirection('L'); break;
                    case KeyEvent.VK_D: player1GamePanel.setDirection('R'); break;
                }
            }
            if ((player2GamePanel.isRunning() && !isPaused)) {
                switch (keyCode) {
                    case KeyEvent.VK_UP: player2GamePanel.setDirection('U'); break;
                    case KeyEvent.VK_DOWN: player2GamePanel.setDirection('D'); break;
                    case KeyEvent.VK_LEFT: player2GamePanel.setDirection('L'); break;
                    case KeyEvent.VK_RIGHT: player2GamePanel.setDirection('R'); break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        Background = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (GameBackgroundImage != null) {
                    g.drawImage(GameBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        }
        ;
        HeaderPlayer1 = new javax.swing.JPanel();
        LabelSkorPlayer1 = new javax.swing.JLabel();
        SkorPlayer1 = new javax.swing.JLabel();
        NamePlayer1 = new javax.swing.JLabel();
        PlayerArea1 = new javax.swing.JPanel();
        PlayerArea2 = new javax.swing.JPanel();
        HeaderPlayer2 = new javax.swing.JPanel();
        LabelSkorPlayer2 = new javax.swing.JLabel();
        SkorPlayer2 = new javax.swing.JLabel();
        NamePlayer2 = new javax.swing.JLabel();
        ButtonRestart = new javax.swing.JButton();
        ButtonPause = new javax.swing.JButton();
        ButtonBack = new javax.swing.JButton();
        HeaderPlayer3 = new javax.swing.JPanel();
        LabelProperties = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        Background.setBackground(new java.awt.Color(102, 102, 102));
        Background.setPreferredSize(new java.awt.Dimension(800, 600));

        HeaderPlayer1.setBackground(new java.awt.Color(0, 0, 0));
        HeaderPlayer1.setPreferredSize(new java.awt.Dimension(800, 50));

        LabelSkorPlayer1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LabelSkorPlayer1.setForeground(new java.awt.Color(255, 255, 255));
        LabelSkorPlayer1.setText("Skor :");

        SkorPlayer1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        SkorPlayer1.setForeground(new java.awt.Color(255, 255, 255));
        SkorPlayer1.setText("0");

        NamePlayer1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NamePlayer1.setForeground(new java.awt.Color(255, 255, 255));
        NamePlayer1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NamePlayer1.setText("Nama");

        javax.swing.GroupLayout HeaderPlayer1Layout = new javax.swing.GroupLayout(HeaderPlayer1);
        HeaderPlayer1.setLayout(HeaderPlayer1Layout);
        HeaderPlayer1Layout.setHorizontalGroup(
            HeaderPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPlayer1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(LabelSkorPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SkorPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NamePlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        HeaderPlayer1Layout.setVerticalGroup(
            HeaderPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelSkorPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SkorPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(NamePlayer1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        PlayerArea1.setOpaque(false);
        PlayerArea1.setPreferredSize(new java.awt.Dimension(300, 500));

        javax.swing.GroupLayout PlayerArea1Layout = new javax.swing.GroupLayout(PlayerArea1);
        PlayerArea1.setLayout(PlayerArea1Layout);
        PlayerArea1Layout.setHorizontalGroup(
            PlayerArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PlayerArea1Layout.setVerticalGroup(
            PlayerArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        PlayerArea2.setOpaque(false);
        PlayerArea2.setPreferredSize(new java.awt.Dimension(380, 510));

        javax.swing.GroupLayout PlayerArea2Layout = new javax.swing.GroupLayout(PlayerArea2);
        PlayerArea2.setLayout(PlayerArea2Layout);
        PlayerArea2Layout.setHorizontalGroup(
            PlayerArea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PlayerArea2Layout.setVerticalGroup(
            PlayerArea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        HeaderPlayer2.setBackground(new java.awt.Color(0, 0, 0));
        HeaderPlayer2.setPreferredSize(new java.awt.Dimension(800, 50));

        LabelSkorPlayer2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LabelSkorPlayer2.setForeground(new java.awt.Color(255, 255, 255));
        LabelSkorPlayer2.setText("Skor :");

        SkorPlayer2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        SkorPlayer2.setForeground(new java.awt.Color(255, 255, 255));
        SkorPlayer2.setText("0");

        NamePlayer2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NamePlayer2.setForeground(new java.awt.Color(255, 255, 255));
        NamePlayer2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NamePlayer2.setText("Nama");

        javax.swing.GroupLayout HeaderPlayer2Layout = new javax.swing.GroupLayout(HeaderPlayer2);
        HeaderPlayer2.setLayout(HeaderPlayer2Layout);
        HeaderPlayer2Layout.setHorizontalGroup(
            HeaderPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPlayer2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(LabelSkorPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SkorPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(NamePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        HeaderPlayer2Layout.setVerticalGroup(
            HeaderPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelSkorPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SkorPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(NamePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ButtonRestart.setBackground(new java.awt.Color(234, 121, 57));
        ButtonRestart.setForeground(new java.awt.Color(255, 255, 255));
        ButtonRestart.setText("Restart");
        ButtonRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRestartActionPerformed(evt);
            }
        });

        ButtonPause.setText("Pause");
        ButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPauseActionPerformed(evt);
            }
        });

        ButtonBack.setBackground(new java.awt.Color(252, 3, 78));
        ButtonBack.setForeground(new java.awt.Color(255, 255, 255));
        ButtonBack.setText("Back");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        HeaderPlayer3.setBackground(new java.awt.Color(0, 0, 0));
        HeaderPlayer3.setPreferredSize(new java.awt.Dimension(800, 50));

        LabelProperties.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LabelProperties.setForeground(new java.awt.Color(255, 255, 255));
        LabelProperties.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelProperties.setText("Speed : Speed, Colour 1 : Green, Colour 2 : Blue, WIth Border");

        javax.swing.GroupLayout HeaderPlayer3Layout = new javax.swing.GroupLayout(HeaderPlayer3);
        HeaderPlayer3.setLayout(HeaderPlayer3Layout);
        HeaderPlayer3Layout.setHorizontalGroup(
            HeaderPlayer3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderPlayer3Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(LabelProperties, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        HeaderPlayer3Layout.setVerticalGroup(
            HeaderPlayer3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPlayer3Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(LabelProperties, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PlayerArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                    .addComponent(HeaderPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(PlayerArea2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(HeaderPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
            .addComponent(HeaderPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(ButtonPause, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ButtonRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(HeaderPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(HeaderPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(HeaderPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PlayerArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(PlayerArea2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPause, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonRestart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {
        if (!isPaused) {
            player1GamePanel.stopGame();
            player2GamePanel.stopGame();
            isPaused = true;
            ButtonPause.setText("Resume");
        } else {
            player1GamePanel.resumeGame();
            player2GamePanel.resumeGame();
            isPaused = false;
            ButtonPause.setText("Pause");
        }
        requestFocusInWindow(); 
    }

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playBackButtonSound();
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        utils.CustomConfirmDialog dialog = new utils.CustomConfirmDialog(parentWindow,
            "Are you sure you want to return to the main menu? Progress will be lost.", "Confirm Back");
        boolean dialogResult = dialog.showDialog();

        if (dialogResult) {
            if ((player1GamePanel != null && player1GamePanel.isRunning()) ||
                (player2GamePanel != null && player2GamePanel.isRunning())) {
                if (player1GamePanel != null) player1GamePanel.stopGame();
                if (player2GamePanel != null) player2GamePanel.stopGame();
            }
            java.awt.Container parent = this.getTopLevelAncestor();
            if (parent instanceof javax.swing.JFrame) {
                javax.swing.JFrame frame = (javax.swing.JFrame) parent;
                frame.setContentPane(new panel.MultiPlayerMenuPanel());
                frame.revalidate();
                frame.repaint();
            }
            SoundLoader.stopBackgroundMusic();
        } else {
            MultiPlayerPanel.this.requestFocusInWindow();
        }
    }

    private void ButtonRestartActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        utils.CustomConfirmDialog dialog = new utils.CustomConfirmDialog(parentWindow,
            "Are you sure you want to restart the game?", "Confirm Restart");
        boolean dialogResult = dialog.showDialog();
        if (dialogResult) {
            player1IsGameOver = false;
            player2IsGameOver = false;
            gameFinished = false;
            player1GamePanel.resetGame();
            player2GamePanel.resetGame();
            ButtonPause.setEnabled(false); 
            ButtonRestart.setEnabled(true);
            ButtonPause.setText("Pause");
            isPaused = false;
            if (gameEndCheckerTimer != null && !gameEndCheckerTimer.isRunning()) {
                gameEndCheckerTimer.start();
            }
            player1GamePanel.requestFocusInWindow();
            player2GamePanel.requestFocusInWindow();
            MultiPlayerPanel.this.requestFocusInWindow();
        } else {
            MultiPlayerPanel.this.requestFocusInWindow();
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        java.awt.EventQueue.invokeLater(() -> {
            boolean focused = requestFocusInWindow();
            System.out.println("[DEBUG] MultiPlayerPanel requestFocusInWindow: " + focused);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonPause;
    private javax.swing.JButton ButtonRestart;
    private javax.swing.JPanel HeaderPlayer1;
    private javax.swing.JPanel HeaderPlayer2;
    private javax.swing.JPanel HeaderPlayer3;
    private javax.swing.JLabel LabelProperties;
    private javax.swing.JLabel LabelSkorPlayer1;
    private javax.swing.JLabel LabelSkorPlayer2;
    private javax.swing.JLabel NamePlayer1;
    private javax.swing.JLabel NamePlayer2;
    private javax.swing.JPanel PlayerArea1;
    private javax.swing.JPanel PlayerArea2;
    private javax.swing.JLabel SkorPlayer1;
    private javax.swing.JLabel SkorPlayer2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
