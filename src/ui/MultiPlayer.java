package ui;

import game.SnakeGamePanel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import utils.FontLoader;
import utils.SoundLoader;

public class MultiPlayer extends javax.swing.JFrame {
    
    private BufferedImage GameBackgroundImage;
    
    private SnakeGamePanel player1GamePanel; 
    private SnakeGamePanel player2GamePanel; 
    
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

    
    public MultiPlayer() {
        this("Pemain 1", "Pemain 2", 100, "Green", "Blue", true); 

    }
    
    public MultiPlayer(String player1Name, String player2Name, int gameSpeedDelay,String p1SnakeColor, String p2SnakeColor, boolean borderOption) {
        initComponents();
        this.GameBackgroundImage = utils.ImageLoader.loadMultiPlayerBackgroundImage(this.Background);
        this.namePlayer1 = player1Name;
        this.namePlayer2 = player2Name;
        this.gameSpeedDelay = gameSpeedDelay; 
        this.player1SnakeColor = p1SnakeColor;
        this.player2SnakeColor = p2SnakeColor;
        this.hasBorder = borderOption;
        setLocationRelativeTo(null);

        if (!SoundLoader.isBackgroundMusicPlaying()) {
            SoundLoader.playBackgroundMusic();
        }
        
        
        Font customFont = new Font("Times New Roman", Font.PLAIN, 14); 
        try {
            InputStream is = getClass().getResourceAsStream("assets/fonts/EASyText.ttf");
            if (is != null) {
                customFont = Font.createFont(Font.TRUETYPE_FONT, is);
                customFont = customFont.deriveFont(Font.PLAIN, 14f);
            }
        } catch (IOException | FontFormatException e) {
            System.err.println("Gagal memuat font kustom: " + e.getMessage());
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
        int player1Height = 480;
        int player2Width = 380;
        int player2Height = 480;
        
        player1GamePanel = new SnakeGamePanel(player1Width, player1Height, this.gameSpeedDelay, this.player1SnakeColor, this.hasBorder);
        player2GamePanel = new SnakeGamePanel(player2Width, player2Height, this.gameSpeedDelay, this.player2SnakeColor, this.hasBorder);

        
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

        
        player1GamePanel.setScoreUpdateListener(new SnakeGamePanel.ScoreUpdateListener() {
            @Override
            public void onScoreUpdate(int newScore) {
                SkorPlayer1.setText(String.valueOf(newScore));
            }
            @Override
            public void onGameOver(int finalScore) { 
                SkorPlayer1.setText(String.valueOf(finalScore)); 
                player1IsGameOver = true; 
                handleGameEnd(); 
            }
            @Override
            public void onGameRestarted() { 
                SkorPlayer1.setText("0");
                player1IsGameOver = false;
            }
        });

        player2GamePanel.setScoreUpdateListener(new SnakeGamePanel.ScoreUpdateListener() {
            @Override
            public void onScoreUpdate(int newScore) {
                SkorPlayer2.setText(String.valueOf(newScore));
            }
            @Override
            public void onGameOver(int finalScore) { 
                SkorPlayer2.setText(String.valueOf(finalScore)); 
                player2IsGameOver = true; 
            }
            @Override
            public void onGameRestarted() { 
                SkorPlayer2.setText("0");
                player2IsGameOver = false;
            }
        });
        
        
        gameEndCheckerTimer = new Timer(50, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameFinished && (player1IsGameOver || player2IsGameOver)) {
                    
                    handleGameEnd();
                }
            }
        });
        
        this.addKeyListener(new MyMultiplayerKeyAdapter());
        this.setFocusable(true); 
        this.requestFocusInWindow();

        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                MultiPlayer.this.requestFocusInWindow(); 
            }
            @Override
            public void windowClosing(WindowEvent e) {
                
                if (player1GamePanel.isRunning()) {
                    player1GamePanel.stopGame();
                }
                if (player2GamePanel.isRunning()) {
                    player2GamePanel.stopGame();
                }
                SoundLoader.stopBackgroundMusic();
                SoundLoader.closeAllSounds();    
            }    
        }
        
        );
        
        
        pack();
    }
    
    private String getSpeedLabel(int delay) {
        if (delay <= 75) return "Fast";
        if (delay <= 100) return "Normal";
        return "Slow";
    }
    
        private void handleGameEnd() {
        if (gameFinished) { 
            return;
        }

       
        player1GamePanel.stopGame();
        player2GamePanel.stopGame();
        
        
        if (gameEndCheckerTimer != null && gameEndCheckerTimer.isRunning()) {
            gameEndCheckerTimer.stop();
        }

        
        ButtonPause.setEnabled(false); 
        ButtonRestart.setEnabled(true);
        ButtonBack.setEnabled(true);   
        
        String message = "";
        String title = "Game Selesai!";

        
        if (player1IsGameOver && player2IsGameOver) {
            message = "Kedua Pemain Menabrak Dinding Secara Bersamaan!\nHasil: Seri!";
            title = "Permainan Seri!";
        } else if (player1IsGameOver) {
            
            message = namePlayer1 + " Menabrak Dinding!\n" + namePlayer2 + " Memenangkan Permainan!";
            title = "Pemain Kalah!";
        } else if (player2IsGameOver) {
            
            message = namePlayer2 + " Menabrak Dinding!\n" + namePlayer1 + " Memenangkan Permainan!";
            title = "Pemain Kalah!";
        }
        
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        gameFinished = true;
    }
    private class MyMultiplayerKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            
           
           
            if (keyCode == KeyEvent.VK_SPACE && !isPaused) { 
                
                if (!player1GamePanel.isGameStarted() && !player1GamePanel.isRunning() && !player1GamePanel.isGameOver() &&
                    !player2GamePanel.isGameStarted() && !player2GamePanel.isRunning() && !player2GamePanel.isGameOver()) {
                    
                    player1GamePanel.startGame();
                    player2GamePanel.startGame();
                
                    ButtonPause.setEnabled(true);
                    ButtonRestart.setEnabled(true);
                    isPaused = false; 
                } 
                
                else if (player1IsGameOver || player2IsGameOver) { 
                    player1GamePanel.resetGame();
                    player2GamePanel.resetGame();
                    player1IsGameOver = false; 
                    player2IsGameOver = false; 
                    isPaused = false; 
                    ButtonPause.setText("Pause"); 
                    ButtonPause.setEnabled(true); 
                    ButtonRestart.setEnabled(true);
                }
            }

            
            
            if (player1GamePanel.isRunning() && !player1IsGameOver) {
                switch (keyCode) {
                    case KeyEvent.VK_W: player1GamePanel.setDirection('U'); break;
                    case KeyEvent.VK_A: player1GamePanel.setDirection('L'); break;
                    case KeyEvent.VK_S: player1GamePanel.setDirection('D'); break;
                    case KeyEvent.VK_D: player1GamePanel.setDirection('R'); break;
                }
            }

            
            
            if (player2GamePanel.isRunning() && !player2IsGameOver) {
                switch (keyCode) {
                    case KeyEvent.VK_UP: player2GamePanel.setDirection('U'); break;
                    case KeyEvent.VK_LEFT: player2GamePanel.setDirection('L'); break;
                    case KeyEvent.VK_DOWN: player2GamePanel.setDirection('D'); break;
                    case KeyEvent.VK_RIGHT: player2GamePanel.setDirection('R'); break;
                }
            }
            MultiPlayer.this.requestFocusInWindow();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGap(0, 485, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(ButtonPause, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ButtonRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(HeaderPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PlayerArea2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(PlayerArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPause, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonRestart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(812, 616));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPauseActionPerformed
    
        if ((player1GamePanel.isGameStarted() || player2GamePanel.isGameStarted()) &&
            (!player1IsGameOver && !player2IsGameOver)) {
            if (isPaused) {
                player1GamePanel.resumeGame();
                player2GamePanel.resumeGame();
                ButtonPause.setText("Pause");
                isPaused = false;
            } else {
                player1GamePanel.stopGame(); 
                player2GamePanel.stopGame(); 
                ButtonPause.setText("Resume");
                isPaused = true;
            }
            MultiPlayer.this.requestFocusInWindow(); 
        }
        
        
        
    }//GEN-LAST:event_ButtonPauseActionPerformed

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
                int dialogResult = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin kembali ke menu utama? Progres kedua game akan hilang.", "Konfirmasi Kembali",
                JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            // Hentikan kedua game jika sedang berjalan atau sudah dimulai
            if (player1GamePanel.isRunning() || player1GamePanel.isGameStarted()) {
                player1GamePanel.stopGame();
            }
            if (player2GamePanel.isRunning() || player2GamePanel.isGameStarted()) {
                player2GamePanel.stopGame();
            }
            
            this.dispose();
            
            MultiPlayerMenu MultiPlayerMenu = new MultiPlayerMenu();
            MultiPlayerMenu.setVisible(true);
            
        }
                MultiPlayer.this.requestFocusInWindow(); 


            SoundLoader.stopBackgroundMusic();
                SoundLoader.closeAllSounds();

    }//GEN-LAST:event_ButtonBackActionPerformed

    private void ButtonRestartActionPerformed(java.awt.event.ActionEvent evt) {
        int dialogResult = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin memulai ulang kedua game?", "Konfirmasi Restart",
                JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            player1GamePanel.resetGame();
            player2GamePanel.resetGame();
            player1IsGameOver = false; 
            player2IsGameOver = false;
            isPaused = false; 
            ButtonPause.setText("Pause"); 
            ButtonPause.setEnabled(true); 
            ButtonRestart.setEnabled(true); 
            MultiPlayer.this.requestFocusInWindow();
        }
                MultiPlayer.this.requestFocusInWindow(); 

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
