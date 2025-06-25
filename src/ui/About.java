package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import utils.SoundLoader;
import utils.FontLoader;
import utils.ImageLoader;

public class About extends javax.swing.JFrame {
    private BufferedImage multiPlayerBackground;
    private BufferedImage yappingImage;
    private BufferedImage developerPhotoImage;
    
    public About() {
        initComponents();
        this.yappingImage = ImageLoader.loadYappingImage(this.Yapping);
        this.developerPhotoImage = ImageLoader.loadDeveloperPhotoImage(this.PanelDeveloperPhoto);
        this.multiPlayerBackground = utils.ImageLoader.loadMultiPlayerBackgroundImage(this.Background);
        setLocationRelativeTo(null);
        FontLoader.applyFont(LabelAbout, 32f);
        FontLoader.applyFont(LABEL, 10f);
        if (!SoundLoader.isGameMenuPlaying()) {
            SoundLoader.playGameMenuSound();
        }
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                About.this.requestFocusInWindow();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                
                SoundLoader.stopGameMenuSound();
                SoundLoader.closeAllSounds(); 
  
            }
        });
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                // Gunakan variabel yang baru Anda buat untuk menu ini
                if (multiPlayerBackground != null) {
                    g.drawImage(multiPlayerBackground, 0, 0, getWidth(), getHeight(), this);
                }
            }
        }
        ;
        Header = new javax.swing.JPanel();
        ButtonBack = new javax.swing.JButton();
        LabelAbout = new javax.swing.JLabel();
        Yapping = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (yappingImage != null) {
                    g.drawImage(yappingImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        PanelDeveloperPhoto = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (developerPhotoImage != null) {
                    g.drawImage(developerPhotoImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        LABEL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));

        Background.setBackground(new java.awt.Color(0, 0, 0));

        Header.setBackground(new java.awt.Color(102, 102, 102));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 0, 0)));

        ButtonBack.setBackground(new java.awt.Color(153, 0, 0));
        ButtonBack.setText("<");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        LabelAbout.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        LabelAbout.setForeground(new java.awt.Color(0, 0, 0));
        LabelAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelAbout.setText("ABOUT");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(LabelAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Yapping.setPreferredSize(new java.awt.Dimension(400, 300));

        javax.swing.GroupLayout YappingLayout = new javax.swing.GroupLayout(Yapping);
        Yapping.setLayout(YappingLayout);
        YappingLayout.setHorizontalGroup(
            YappingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE)
        );
        YappingLayout.setVerticalGroup(
            YappingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        PanelDeveloperPhoto.setPreferredSize(new java.awt.Dimension(300, 200));

        javax.swing.GroupLayout PanelDeveloperPhotoLayout = new javax.swing.GroupLayout(PanelDeveloperPhoto);
        PanelDeveloperPhoto.setLayout(PanelDeveloperPhotoLayout);
        PanelDeveloperPhotoLayout.setHorizontalGroup(
            PanelDeveloperPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        PanelDeveloperPhotoLayout.setVerticalGroup(
            PanelDeveloperPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        LABEL.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        LABEL.setForeground(new java.awt.Color(255, 255, 255));
        LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL.setText("this project was created by two people who were rejected by ITB");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(PanelDeveloperPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(LABEL))
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(Yapping, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(39, Short.MAX_VALUE))))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(PanelDeveloperPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Yapping, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LABEL)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        SoundLoader.playBackButtonSound();
        SoundLoader.stopGameMenuSound();
        SoundLoader.closeAllSounds(); 
        MainMenu snake = new MainMenu();
        snake.setVisible(true);       
        this.dispose();
    }//GEN-LAST:event_ButtonBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel LABEL;
    private javax.swing.JLabel LabelAbout;
    private javax.swing.JPanel PanelDeveloperPhoto;
    private javax.swing.JPanel Yapping;
    // End of variables declaration//GEN-END:variables
}
