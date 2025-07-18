package panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import utils.CustomConfirmDialog;
import game.SnakeGame;
import utils.SoundLoader;
import utils.FontLoader;

public class SinglePlayerPanel extends JPanel {
    private BufferedImage GameBackgroundImage;
    private SnakeGame gamePanel;
    private String playerName;
    private String selectedSpeed;
    private String selectedMap;
    private String selectedSnakeColor;
    private boolean isPaused = false;

    public SinglePlayerPanel() {
        this("Player", "Medium", "With Border", "Green");
    }

    public SinglePlayerPanel(String name, String speed, String map, String snakeColor) {
        this.playerName = name;
        this.selectedSpeed = speed;
        this.selectedMap = map;
        this.selectedSnakeColor = snakeColor;
        initComponents();
        this.GameBackgroundImage = utils.ImageLoader.loadSinglePlayerBackgroundImage(this.Background);

        if (!SoundLoader.isBackgroundMusicPlaying()) {
            SoundLoader.playBackgroundMusic();
        }

        FontLoader.applyFont(LabelSkor, 14f);
        FontLoader.applyFont(Skor, 14f);
        FontLoader.applyFont(LabelName, 14f);
        FontLoader.applyFont(LabelColour, 14f);
        FontLoader.applyFont(LabelSpeed, 14f);
        FontLoader.applyFont(LabelBorder, 14f);
        FontLoader.applyFont(ButtonBack, 16f);
        FontLoader.applyFont(ButtonPause, 16f);
        FontLoader.applyFont(ButtonRestart, 16f);

        LabelName.setText(this.playerName);
        LabelSpeed.setText("Speed : " + this.selectedSpeed);
        LabelColour.setText("Colour : " + this.selectedSnakeColor);
        LabelBorder.setText(this.selectedMap);

        int gamePanelWidth = 740;
        int gamePanelHeight = 400;

        gamePanel = new SnakeGame(
            gamePanelWidth,
            gamePanelHeight,
            convertSpeedToDelay(this.selectedSpeed),
            this.selectedSnakeColor,
            this.selectedMap.equals("With Border")
        );

        gamePanel.setScoreUpdateListener(new SnakeGame.ScoreUpdateListener() {
            @Override
            public void onScoreUpdate(int newScore) {
                Skor.setText(String.valueOf(newScore));
            }

            @Override
            public void onGameOver(int finalScore) {
                ButtonPause.setEnabled(false);
                ButtonRestart.setEnabled(true);
                ButtonPause.setText("Pause");
                isPaused = false;
                SinglePlayerPanel.this.requestFocusInWindow();
            }

            @Override
            public void onGameRestarted() {
                Skor.setText("0");
                ButtonPause.setEnabled(true);
                ButtonRestart.setEnabled(true);
                ButtonPause.setText("Pause");
                isPaused = false;
                SinglePlayerPanel.this.requestFocusInWindow();
            }
        });

        PlayerArea.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        PlayerArea.add(gamePanel, gbc);

        setFocusable(true);
        addKeyListener(new MyKeyListener());

        ButtonPause.setText("Pause");
        ButtonPause.setEnabled(false);
        ButtonRestart.setEnabled(true);
        SwingUtilities.invokeLater(() -> SinglePlayerPanel.this.requestFocusInWindow());
    }

    private int convertSpeedToDelay(String speed) {
        switch (speed) {
            case "Slow": return 150;
            case "Fast": return 50;
            case "Medium":
            default: return 100;
        }
    }

    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                if (gamePanel.isGameOver()) {
                    gamePanel.resetGame();
                    ButtonPause.setText("Pause");
                    isPaused = false;
                    ButtonPause.setEnabled(false);
                    gamePanel.requestFocusInWindow();
                    SinglePlayerPanel.this.requestFocusInWindow();
                    return;
                }
                if (isPaused && gamePanel.isGameStarted() && !gamePanel.isGameOver()) {
                    gamePanel.resumeGame();
                    ButtonPause.setText("Pause");
                    isPaused = false;
                    gamePanel.requestFocusInWindow();
                    SinglePlayerPanel.this.requestFocusInWindow();
                    return;
                }
                if (gamePanel.isRunning() && !isPaused) {
                    gamePanel.stopGame();
                    ButtonPause.setText("Resume");
                    isPaused = true;
                    SinglePlayerPanel.this.requestFocusInWindow();
                    return;
                }
                if (!gamePanel.isGameStarted() && !gamePanel.isRunning() && !gamePanel.isGameOver()) {
                    gamePanel.startGame();
                    ButtonPause.setEnabled(true); 
                    gamePanel.requestFocusInWindow();
                    SinglePlayerPanel.this.requestFocusInWindow();
                    return;
                }
            }
            if (gamePanel.isRunning() && !isPaused) {
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        gamePanel.setDirection('L');
                        break;
                    case KeyEvent.VK_RIGHT:
                        gamePanel.setDirection('R');
                        break;
                    case KeyEvent.VK_UP:
                        gamePanel.setDirection('U');
                        break;
                    case KeyEvent.VK_DOWN:
                        gamePanel.setDirection('D');
                        break;
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonPause;
    private javax.swing.JButton ButtonRestart;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel LabelBorder;
    private javax.swing.JLabel LabelColour;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelSkor;
    private javax.swing.JLabel LabelSpeed;
    private javax.swing.JPanel PlayerArea;
    private javax.swing.JLabel Skor;
    // End of variables declaration//GEN-END:variables

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (GameBackgroundImage != null) {
                    g.drawImage(GameBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        Header = new javax.swing.JPanel();
        LabelName = new javax.swing.JLabel();
        LabelSkor = new javax.swing.JLabel();
        Skor = new javax.swing.JLabel();
        LabelSpeed = new javax.swing.JLabel();
        LabelColour = new javax.swing.JLabel();
        LabelBorder = new javax.swing.JLabel();
        PlayerArea = new javax.swing.JPanel();
        ButtonBack = new javax.swing.JButton();
        ButtonPause = new javax.swing.JButton();
        ButtonRestart = new javax.swing.JButton();

        Background.setBackground(new java.awt.Color(102, 102, 102));
        Background.setPreferredSize(new java.awt.Dimension(800, 600));

        Header.setBackground(new java.awt.Color(0, 0, 0));
        Header.setPreferredSize(new java.awt.Dimension(800, 50));

        LabelName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelName.setForeground(new java.awt.Color(255, 255, 255));
        LabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelName.setText("Nama");

        LabelSkor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LabelSkor.setForeground(new java.awt.Color(255, 255, 255));
        LabelSkor.setText("Score :");

        Skor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Skor.setForeground(new java.awt.Color(255, 255, 255));
        Skor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Skor.setText("0");

        LabelSpeed.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LabelSpeed.setForeground(new java.awt.Color(255, 255, 255));
        LabelSpeed.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelSpeed.setText("Speed : Normal");

        LabelColour.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LabelColour.setForeground(new java.awt.Color(255, 255, 255));
        LabelColour.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelColour.setText("Colour : Green");

        LabelBorder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LabelBorder.setForeground(new java.awt.Color(255, 255, 255));
        LabelBorder.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelBorder.setText("With Border");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addComponent(LabelSkor, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Skor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LabelSpeed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LabelColour, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, HeaderLayout.createSequentialGroup()
                        .addComponent(LabelSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelColour)))
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Skor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LabelSkor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PlayerArea.setBackground(new java.awt.Color(0, 0, 0));
        PlayerArea.setOpaque(false);
        PlayerArea.setPreferredSize(new java.awt.Dimension(0, 520));

        javax.swing.GroupLayout PlayerAreaLayout = new javax.swing.GroupLayout(PlayerArea);
        PlayerArea.setLayout(PlayerAreaLayout);
        PlayerAreaLayout.setHorizontalGroup(
            PlayerAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PlayerAreaLayout.setVerticalGroup(
            PlayerAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        ButtonBack.setBackground(new java.awt.Color(252, 3, 78));
        ButtonBack.setForeground(new java.awt.Color(255, 255, 255));
        ButtonBack.setText("Back");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        ButtonPause.setText("Pause");
        ButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPauseActionPerformed(evt);
            }
        });

        ButtonRestart.setBackground(new java.awt.Color(234, 121, 57));
        ButtonRestart.setForeground(new java.awt.Color(255, 255, 255));
        ButtonRestart.setText("Restart");
        ButtonRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRestartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PlayerArea, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addGap(0, 134, Short.MAX_VALUE)
                        .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(ButtonPause, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(ButtonRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 143, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(Header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PlayerArea, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPause, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonRestart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);
        CustomConfirmDialog dialog = new CustomConfirmDialog(parentWindow,
            "Are you sure you want to return to the main menu? Progress will be lost.", "Confirm Back");
        boolean dialogResult = dialog.showDialog();

        if (dialogResult) {
            if (gamePanel.isRunning() || gamePanel.isGameStarted()) {
                gamePanel.stopGame();
            }
            java.awt.Container parent = this.getTopLevelAncestor();
            if (parent instanceof JFrame) {
                JFrame frame = (JFrame) parent;
                frame.setContentPane(new panel.SinglePlayerMenuPanel());
                frame.revalidate();
                frame.repaint();
            }
            SoundLoader.stopBackgroundMusic();
        } else {
            SinglePlayerPanel.this.requestFocusInWindow();
        }
    }//GEN-LAST:event_ButtonBackActionPerformed

    private void ButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPauseActionPerformed
        if (gamePanel.isGameStarted() && !gamePanel.isGameOver()) {
            if (isPaused) {
                gamePanel.resumeGame();
                ButtonPause.setText("Pause");
                isPaused = false;
                gamePanel.requestFocusInWindow();
            } else if (gamePanel.isRunning()) {
                gamePanel.stopGame();
                ButtonPause.setText("Resume");
                isPaused = true;
                SinglePlayerPanel.this.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_ButtonPauseActionPerformed

    private void ButtonRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRestartActionPerformed
        java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);
        CustomConfirmDialog dialog = new CustomConfirmDialog(parentWindow,
            "Are you sure you want to restart the game?", "Confirm Restart");
        boolean dialogResult = dialog.showDialog();

        if (dialogResult) {
            gamePanel.resetGame();
            isPaused = false;
            ButtonPause.setText("Pause");
            ButtonPause.setEnabled(false);
            gamePanel.requestFocusInWindow(); 
            SinglePlayerPanel.this.requestFocusInWindow();
        } else {
            SinglePlayerPanel.this.requestFocusInWindow();
        }
    }//GEN-LAST:event_ButtonRestartActionPerformed
}
