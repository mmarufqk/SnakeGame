package panel;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import utils.FontLoader;
import utils.SoundLoader;

public class SinglePlayerMenuPanel extends JPanel {
    private BufferedImage singlePlayerBackground;
    private BufferedImage currentPreviewImage;

    public SinglePlayerMenuPanel() {
        initComponents();
        this.singlePlayerBackground = utils.ImageLoader.loadSinglePlayerBackgroundImage(this.Background);
        FontLoader.applyFont(LabelName, 16f);
        FontLoader.applyFont(LabelSinglePlayer, 32f);
        FontLoader.applyFont(LabelSpeed, 16f);
        FontLoader.applyFont(LabelMap, 16f);
        FontLoader.applyFont(LabelSnakeColour, 16f);
        FontLoader.applyFont(LabelPreview, 16f);
        FontLoader.applyFont(FieldName, 12f);
        FontLoader.applyFont(ComboSpeed, 12f);
        FontLoader.applyFont(ComboBorder, 12f);
        FontLoader.applyFont(ComboColour, 12f);
        FontLoader.applyFont(ButtonStart, 12f);
        updatePreviewImage();
        if (!SoundLoader.isGameMenuPlaying()) {
            SoundLoader.playGameMenuSound();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (singlePlayerBackground != null) {
                    g.drawImage(singlePlayerBackground, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        Header = new javax.swing.JPanel();
        ButtonBack = new javax.swing.JButton();
        LabelSinglePlayer = new javax.swing.JLabel();
        LabelName = new javax.swing.JLabel();
        LabelSpeed = new javax.swing.JLabel();
        LabelMap = new javax.swing.JLabel();
        LabelSnakeColour = new javax.swing.JLabel();
        FieldName = new javax.swing.JTextField();
        ComboSpeed = new javax.swing.JComboBox<>();
        ComboBorder = new javax.swing.JComboBox<>();
        ComboColour = new javax.swing.JComboBox<>();
        ButtonStart = new javax.swing.JButton();
        LabelPreview = new javax.swing.JLabel();
        PanelPreview = new javax.swing.JPanel();
        LabelPreviewImage = new javax.swing.JLabel();

        setName("SinglePlayerMenuPanel"); // NOI18N

        Background.setBackground(new java.awt.Color(0, 0, 0));
        Background.setPreferredSize(new java.awt.Dimension(800, 600));

        Header.setBackground(new java.awt.Color(102, 102, 102));

        ButtonBack.setBackground(new java.awt.Color(255, 0, 0));
        ButtonBack.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ButtonBack.setText("<");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        LabelSinglePlayer.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        LabelSinglePlayer.setForeground(new java.awt.Color(0, 0, 0));
        LabelSinglePlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelSinglePlayer.setText("Single Player");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(LabelSinglePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addGap(248, 248, 248))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(LabelSinglePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        LabelName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelName.setForeground(new java.awt.Color(255, 255, 255));
        LabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelName.setText("Name :");
        LabelName.setToolTipText("");

        LabelSpeed.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelSpeed.setForeground(new java.awt.Color(255, 255, 255));
        LabelSpeed.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelSpeed.setText("Snake Speed :");

        LabelMap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelMap.setForeground(new java.awt.Color(255, 255, 255));
        LabelMap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelMap.setText("Map :");

        LabelSnakeColour.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelSnakeColour.setForeground(new java.awt.Color(255, 255, 255));
        LabelSnakeColour.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelSnakeColour.setText("Snake Colour :");

        FieldName.setBackground(new java.awt.Color(102, 102, 102));
        FieldName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        FieldName.setForeground(new java.awt.Color(0, 0, 0));
        FieldName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FieldName.setText("Enter your name.");
        FieldName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FieldNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                FieldNameFocusLost(evt);
            }
        });

        ComboSpeed.setBackground(new java.awt.Color(102, 102, 102));
        ComboSpeed.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboSpeed.setForeground(new java.awt.Color(0, 0, 0));
        ComboSpeed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Slow", "Medium", "Fast" }));
        ComboSpeed.setSelectedIndex(1);
        ComboSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSpeedActionPerformed(evt);
            }
        });

        ComboBorder.setBackground(new java.awt.Color(102, 102, 102));
        ComboBorder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboBorder.setForeground(new java.awt.Color(0, 0, 0));
        ComboBorder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "With Border", "Without Border" }));
        ComboBorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBorderActionPerformed(evt);
            }
        });

        ComboColour.setBackground(new java.awt.Color(102, 102, 102));
        ComboColour.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboColour.setForeground(new java.awt.Color(0, 0, 0));
        ComboColour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Yellow", "Green", "Blue" }));
        ComboColour.setSelectedIndex(2);
        ComboColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboColourActionPerformed(evt);
            }
        });

        ButtonStart.setBackground(new java.awt.Color(102, 102, 102));
        ButtonStart.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ButtonStart.setForeground(new java.awt.Color(0, 0, 0));
        ButtonStart.setText("Start The Match!");
        ButtonStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonStartActionPerformed(evt);
            }
        });

        LabelPreview.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelPreview.setForeground(new java.awt.Color(255, 255, 255));
        LabelPreview.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelPreview.setText("Preview");

        PanelPreview.setBackground(new java.awt.Color(102, 102, 102));
        PanelPreview.setPreferredSize(new java.awt.Dimension(200, 200));

        LabelPreviewImage.setForeground(new java.awt.Color(0, 0, 0));
        LabelPreviewImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelPreviewImage.setText("gambar");

        javax.swing.GroupLayout PanelPreviewLayout = new javax.swing.GroupLayout(PanelPreview);
        PanelPreview.setLayout(PanelPreviewLayout);
        PanelPreviewLayout.setHorizontalGroup(
            PanelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelPreviewImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelPreviewLayout.setVerticalGroup(
            PanelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelPreviewImage, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ButtonStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FieldName)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelMap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(LabelSnakeColour, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LabelSpeed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBorder, 0, 130, Short.MAX_VALUE)
                                    .addComponent(ComboSpeed, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboColour, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LabelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(PanelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(LabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(FieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(LabelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelSpeed)
                            .addComponent(ComboSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboColour, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelSnakeColour))
                        .addGap(22, 22, 22)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBorder, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelMap))))
                .addGap(55, 55, 55)
                .addComponent(ButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonStartActionPerformed
        SoundLoader.playButtonEnterSound();
        String playerName = FieldName.getText();
        String selectedSpeed = (String) ComboSpeed.getSelectedItem();
        String selectedMap = (String) ComboBorder.getSelectedItem();
        String selectedSnakeColor = (String) ComboColour.getSelectedItem();
        if (playerName.trim().isEmpty() || playerName.equals("Masukkan Nama") || playerName.equals("Enter your name.")) {
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            utils.CustomConfirmDialog dialog = new utils.CustomConfirmDialog(parentWindow, "Please enter your name!", "Name Required", false);
            dialog.showDialog();
            FieldName.requestFocusInWindow();
            return;
        }
        SoundLoader.stopGameMenuSound();
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof frame.GameFrame) {
            ((frame.GameFrame) window).setContentPane(
                new panel.SinglePlayerPanel(playerName, selectedSpeed, selectedMap, selectedSnakeColor)
            );
            window.revalidate();
            window.repaint();
        }
    }//GEN-LAST:event_ButtonStartActionPerformed

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        SoundLoader.playBackButtonSound();
        SoundLoader.stopGameMenuSound();
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof frame.GameFrame) {
            ((frame.GameFrame) window).setContentPane(new panel.MainMenuPanel());
            window.revalidate();
        }
    }//GEN-LAST:event_ButtonBackActionPerformed

    private void FieldNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FieldNameFocusGained
        if (FieldName.getText().equals("Enter your name.")) {
            FieldName.setText("");
        }
    }//GEN-LAST:event_FieldNameFocusGained

    private void FieldNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FieldNameFocusLost
        if (FieldName.getText().trim().isEmpty()) {
            FieldName.setText("Enter your name.");
        }
    }//GEN-LAST:event_FieldNameFocusLost

    private void ComboSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSpeedActionPerformed
        SoundLoader.playComboBoxSound();
    }//GEN-LAST:event_ComboSpeedActionPerformed

    private void ComboColourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboColourActionPerformed
        SoundLoader.playComboBoxSound();
        updatePreviewImage();
    }//GEN-LAST:event_ComboColourActionPerformed

    private void ComboBorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBorderActionPerformed
        SoundLoader.playComboBoxSound();
        updatePreviewImage();
    }//GEN-LAST:event_ComboBorderActionPerformed

    private void updatePreviewImage() {
        String selectedColour = (String) ComboColour.getSelectedItem();
        String selectedBorder = (String) ComboBorder.getSelectedItem();
        if (selectedColour != null && selectedBorder != null) {
            currentPreviewImage = utils.ImageLoader.loadPreviewImage(selectedColour, selectedBorder, PanelPreview);
            if (currentPreviewImage != null) {
                int targetWidth = LabelPreviewImage.getWidth();
                int targetHeight = LabelPreviewImage.getHeight();
                if (targetWidth <= 0 || targetHeight <= 0) {
                    targetWidth = PanelPreview.getWidth();
                    targetHeight = PanelPreview.getHeight();
                }
                if (targetWidth <= 0) targetWidth = 200;
                if (targetHeight <= 0) targetHeight = 200;
                if (targetWidth > 0 && targetHeight > 0) {
                    ImageIcon scaledIcon = new ImageIcon(currentPreviewImage.getScaledInstance(
                        targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH));
                    LabelPreviewImage.setIcon(scaledIcon);
                    LabelPreviewImage.setText("");
                } else {
                    LabelPreviewImage.setIcon(new ImageIcon(currentPreviewImage));
                    LabelPreviewImage.setText("");
                }
            } else {
                LabelPreviewImage.setIcon(null);
                LabelPreviewImage.setText("Image Not Found");
            }
        } else {
            LabelPreviewImage.setIcon(null);
            LabelPreviewImage.setText("Select Options");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonStart;
    private javax.swing.JComboBox<String> ComboBorder;
    private javax.swing.JComboBox<String> ComboColour;
    private javax.swing.JComboBox<String> ComboSpeed;
    private javax.swing.JTextField FieldName;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel LabelMap;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelPreview;
    private javax.swing.JLabel LabelPreviewImage;
    private javax.swing.JLabel LabelSinglePlayer;
    private javax.swing.JLabel LabelSnakeColour;
    private javax.swing.JLabel LabelSpeed;
    private javax.swing.JPanel PanelPreview;
    // End of variables declaration//GEN-END:variables
}
