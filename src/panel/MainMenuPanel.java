package panel;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import utils.FontLoader;
import utils.SoundLoader;

public class MainMenuPanel extends JPanel {
    private BufferedImage BackgroundMainMenu;

    public MainMenuPanel() {
        initComponents();
        this.BackgroundMainMenu = utils.ImageLoader.loadBackgroundImage(this.Background);
        FontLoader.applyFont(LabelFrame, 32f);
        FontLoader.applyFont(ButtonMultiPlayer, 16f);
        FontLoader.applyFont(ButtonSinglePlayer, 16f);
        FontLoader.applyFont(ButtonAbout, 16f);
        if (!SoundLoader.isBackgroundMusicPlaying()) {
            SoundLoader.playBackgroundMusic();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        Background = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (BackgroundMainMenu != null) {
                    g.drawImage(BackgroundMainMenu, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        ButtonSinglePlayer = new javax.swing.JButton();
        ButtonMultiPlayer = new javax.swing.JButton();
        ButtonAbout = new javax.swing.JButton();
        LabelFrame = new javax.swing.JLabel();

        Background.setBackground(new java.awt.Color(153, 153, 153));
        Background.setPreferredSize(new java.awt.Dimension(800, 600));

        ButtonSinglePlayer.setBackground(new java.awt.Color(102, 102, 102));
        ButtonSinglePlayer.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButtonSinglePlayer.setForeground(new java.awt.Color(255, 255, 255));
        ButtonSinglePlayer.setText("Single Player");
        ButtonSinglePlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ButtonSinglePlayer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ButtonSinglePlayer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonSinglePlayer.setMaximumSize(new java.awt.Dimension(90, 30));
        ButtonSinglePlayer.setPreferredSize(new java.awt.Dimension(60, 30));
        ButtonSinglePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSinglePlayerActionPerformed(evt);
            }
        });

        ButtonMultiPlayer.setBackground(new java.awt.Color(102, 102, 102));
        ButtonMultiPlayer.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButtonMultiPlayer.setForeground(new java.awt.Color(255, 255, 255));
        ButtonMultiPlayer.setText("Multi Player");
        ButtonMultiPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ButtonMultiPlayer.setMaximumSize(new java.awt.Dimension(90, 30));
        ButtonMultiPlayer.setPreferredSize(new java.awt.Dimension(90, 30));
        ButtonMultiPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonMultiPlayerActionPerformed(evt);
            }
        });

        ButtonAbout.setBackground(new java.awt.Color(102, 102, 102));
        ButtonAbout.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButtonAbout.setForeground(new java.awt.Color(255, 255, 255));
        ButtonAbout.setText("About");
        ButtonAbout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ButtonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAboutActionPerformed(evt);
            }
        });

        LabelFrame.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        LabelFrame.setForeground(new java.awt.Color(255, 255, 255));
        LabelFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelFrame.setText("SNAKE GAME");
        LabelFrame.setAlignmentX(0.5F);
        LabelFrame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonAbout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonMultiPlayer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonSinglePlayer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(LabelFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(ButtonSinglePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ButtonMultiPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(ButtonAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonMultiPlayerActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playButtonEnterSound();
        SoundLoader.stopBackgroundMusic();
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof frame.GameFrame) {
            ((frame.GameFrame) window).setContentPane(new panel.MultiPlayerMenuPanel());
            window.revalidate();
        }
    }

    private void ButtonAboutActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playButtonEnterSound();
        SoundLoader.stopBackgroundMusic();
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof frame.GameFrame) {
            ((frame.GameFrame) window).setContentPane(new panel.AboutPanel());
            window.revalidate();
        }
    }

    private void ButtonSinglePlayerActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playButtonEnterSound();
        SoundLoader.stopBackgroundMusic();
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof frame.GameFrame) {
            ((frame.GameFrame) window).setContentPane(new panel.SinglePlayerMenuPanel());
            window.revalidate();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton ButtonAbout;
    private javax.swing.JButton ButtonMultiPlayer;
    private javax.swing.JButton ButtonSinglePlayer;
    private javax.swing.JLabel LabelFrame;
    // End of variables declaration//GEN-END:variables
}
