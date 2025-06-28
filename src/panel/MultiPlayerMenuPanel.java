package panel;

import java.awt.image.BufferedImage;
import utils.SoundLoader;
import utils.CustomConfirmDialog;
import javax.swing.ImageIcon;
import utils.FontLoader;
import javax.swing.JPanel;

public class MultiPlayerMenuPanel extends JPanel {
    private BufferedImage multiPlayerBackground;
    private BufferedImage currentPreviewImage1;
    private BufferedImage currentPreviewImage2;
    
    public MultiPlayerMenuPanel() {
        initComponents();
        this.multiPlayerBackground = utils.ImageLoader.loadMultiPlayerBackgroundImage(this.Background);
        FontLoader.applyFont(LabelMultiPlayer, 32f);
        FontLoader.applyFont(LabelPlayer1, 16f);
        FontLoader.applyFont(LabelPlayer2, 16f);
        FontLoader.applyFont(LabelSpeed, 16f);
        FontLoader.applyFont(LabelMap, 16f);
        FontLoader.applyFont(LabelSnakeColour1, 16f);
        FontLoader.applyFont(LabelSnakeColour2, 16f);
        FontLoader.applyFont(LabelPreview, 16f);
        FontLoader.applyFont(PanelPreview, 16f); 
        FontLoader.applyFont(ButtonStart, 16f);
        FontLoader.applyFont(ButtonBack, 16f);
        FontLoader.applyFont(FieldName1, 12f);
        FontLoader.applyFont(FieldName2, 12f);
        FontLoader.applyFont(ComboSpeed, 12f);
        FontLoader.applyFont(ComboBorder, 12f);
        FontLoader.applyFont(ComboColour1, 12f);
        FontLoader.applyFont(ComboColour2, 12f);
        updatePreviewImages();
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
                if (multiPlayerBackground != null) {
                    g.drawImage(multiPlayerBackground, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        Header = new javax.swing.JPanel();
        LabelMultiPlayer = new javax.swing.JLabel();
        ButtonBack = new javax.swing.JButton();
        ComboSpeed = new javax.swing.JComboBox<>();
        ComboBorder = new javax.swing.JComboBox<>();
        ComboColour1 = new javax.swing.JComboBox<>();
        ComboColour2 = new javax.swing.JComboBox<>();
        LabelPlayer1 = new javax.swing.JLabel();
        LabelPlayer2 = new javax.swing.JLabel();
        LabelMap = new javax.swing.JLabel();
        LabelSpeed = new javax.swing.JLabel();
        LabelSnakeColour1 = new javax.swing.JLabel();
        LabelSnakeColour2 = new javax.swing.JLabel();
        LabelPreview = new javax.swing.JLabel();
        FieldName1 = new javax.swing.JTextField();
        FieldName2 = new javax.swing.JTextField();
        PanelPreview = new javax.swing.JPanel();
        LabelPreviewImage1 = new javax.swing.JLabel();
        LabelPreviewImage2 = new javax.swing.JLabel();
        ButtonStart = new javax.swing.JButton();

        Background.setBackground(new java.awt.Color(0, 0, 0));
        Background.setPreferredSize(new java.awt.Dimension(800, 600));

        Header.setBackground(new java.awt.Color(102, 102, 102));
        Header.setPreferredSize(new java.awt.Dimension(800, 50));

        LabelMultiPlayer.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        LabelMultiPlayer.setForeground(new java.awt.Color(0, 0, 0));
        LabelMultiPlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelMultiPlayer.setText("Multi Player");

        ButtonBack.setBackground(new java.awt.Color(255, 0, 51));
        ButtonBack.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        ButtonBack.setText("<");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(LabelMultiPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addGap(291, 291, 291))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(ButtonBack))
                    .addComponent(LabelMultiPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        ComboSpeed.setBackground(new java.awt.Color(102, 102, 102));
        ComboSpeed.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboSpeed.setForeground(new java.awt.Color(0, 0, 0));
        ComboSpeed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Slow", "Normal", "Fast " }));
        ComboSpeed.setSelectedIndex(1);
        ComboSpeed.setMinimumSize(new java.awt.Dimension(116, 22));
        ComboSpeed.setPreferredSize(new java.awt.Dimension(116, 22));
        ComboSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSpeedActionPerformed(evt);
            }
        });

        ComboBorder.setBackground(new java.awt.Color(102, 102, 102));
        ComboBorder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboBorder.setForeground(new java.awt.Color(0, 0, 0));
        ComboBorder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Without Border", "With Border" }));
        ComboBorder.setSelectedIndex(1);
        ComboBorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBorderActionPerformed(evt);
            }
        });

        ComboColour1.setBackground(new java.awt.Color(102, 102, 102));
        ComboColour1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboColour1.setForeground(new java.awt.Color(0, 0, 0));
        ComboColour1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Yellow", "Green", "Blue" }));
        ComboColour1.setSelectedIndex(2);
        ComboColour1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboColour1ActionPerformed(evt);
            }
        });

        ComboColour2.setBackground(new java.awt.Color(102, 102, 102));
        ComboColour2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ComboColour2.setForeground(new java.awt.Color(0, 0, 0));
        ComboColour2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Yellow", "Green", "Blue" }));
        ComboColour2.setSelectedIndex(3);
        ComboColour2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboColour2ActionPerformed(evt);
            }
        });

        LabelPlayer1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelPlayer1.setForeground(new java.awt.Color(255, 255, 255));
        LabelPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelPlayer1.setText("Player 1");

        LabelPlayer2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelPlayer2.setForeground(new java.awt.Color(255, 255, 255));
        LabelPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelPlayer2.setText("Player 2 :");

        LabelMap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelMap.setForeground(new java.awt.Color(255, 255, 255));
        LabelMap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelMap.setText("Maps :");

        LabelSpeed.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelSpeed.setForeground(new java.awt.Color(255, 255, 255));
        LabelSpeed.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelSpeed.setText("Speed :");

        LabelSnakeColour1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelSnakeColour1.setForeground(new java.awt.Color(255, 255, 255));
        LabelSnakeColour1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelSnakeColour1.setText("Snake 1 Colour :");

        LabelSnakeColour2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelSnakeColour2.setForeground(new java.awt.Color(255, 255, 255));
        LabelSnakeColour2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelSnakeColour2.setText("Snake 2 Colour :");

        LabelPreview.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        LabelPreview.setForeground(new java.awt.Color(255, 255, 255));
        LabelPreview.setText("Preview");

        FieldName1.setBackground(new java.awt.Color(102, 102, 102));
        FieldName1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        FieldName1.setForeground(new java.awt.Color(0, 0, 0));
        FieldName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FieldName1.setText("Enter player 1 name.");
        FieldName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FieldName1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                FieldName1FocusLost(evt);
            }
        });

        FieldName2.setBackground(new java.awt.Color(102, 102, 102));
        FieldName2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        FieldName2.setForeground(new java.awt.Color(0, 0, 0));
        FieldName2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FieldName2.setText("Enter player 2 name.");
        FieldName2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FieldName2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                FieldName2FocusLost(evt);
            }
        });

        PanelPreview.setBackground(new java.awt.Color(102, 102, 102));
        PanelPreview.setPreferredSize(new java.awt.Dimension(300, 150));

        LabelPreviewImage1.setForeground(new java.awt.Color(0, 0, 0));
        LabelPreviewImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelPreviewImage1.setText("Image1");

        LabelPreviewImage2.setForeground(new java.awt.Color(0, 0, 0));
        LabelPreviewImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelPreviewImage2.setText("Image 2");

        javax.swing.GroupLayout PanelPreviewLayout = new javax.swing.GroupLayout(PanelPreview);
        PanelPreview.setLayout(PanelPreviewLayout);
        PanelPreviewLayout.setHorizontalGroup(
            PanelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPreviewLayout.createSequentialGroup()
                .addComponent(LabelPreviewImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(LabelPreviewImage2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        );
        PanelPreviewLayout.setVerticalGroup(
            PanelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelPreviewImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(LabelPreviewImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ButtonStart.setBackground(new java.awt.Color(102, 102, 102));
        ButtonStart.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ButtonStart.setForeground(new java.awt.Color(0, 0, 0));
        ButtonStart.setText("Start the Match!");
        ButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(0, 69, Short.MAX_VALUE)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(LabelSpeed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(LabelMap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0)
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ComboBorder, 0, 168, Short.MAX_VALUE)
                                    .addComponent(ComboSpeed, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(LabelPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(FieldName1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(BackgroundLayout.createSequentialGroup()
                                        .addComponent(LabelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17))
                                    .addComponent(PanelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)))
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(LabelPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(BackgroundLayout.createSequentialGroup()
                                    .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LabelSnakeColour1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LabelSnakeColour2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ComboColour2, 0, 160, Short.MAX_VALUE)
                                        .addComponent(ComboColour1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(FieldName2))
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(ButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
                .addContainerGap())
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 30, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LabelPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(LabelPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FieldName2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldName1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelMap, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBorder, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ComboSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ComboColour1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LabelSnakeColour1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelSnakeColour2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboColour2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(LabelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(ButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playBackButtonSound();
        SoundLoader.stopGameMenuSound(); 
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof frame.GameFrame) {
            ((frame.GameFrame) window).setContentPane(new panel.MainMenuPanel());
            window.revalidate();
        }
    }

    private void ButtonStartActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playButtonEnterSound();
        String player1Name = FieldName1.getText().trim();
        String player2Name = FieldName2.getText().trim();
        // Input validation
        if (player1Name.isEmpty() || player1Name.equals("Enter player 1 name.")) {
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            CustomConfirmDialog dialog = new CustomConfirmDialog(parentWindow, "Player 1 name cannot be empty!", "Input Error", false);
            dialog.showDialog();
            FieldName1.requestFocus();
            return;
        }
        if (player2Name.isEmpty() || player2Name.equals("Enter player 2 name.")) {
            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            CustomConfirmDialog dialog = new CustomConfirmDialog(parentWindow, "Player 2 name cannot be empty!", "Input Error", false);
            dialog.showDialog();
            FieldName2.requestFocus();
            return;
        }
        int selectedSpeedDelay = 0;
        String speedString = (String) ComboSpeed.getSelectedItem();
        if (speedString != null) {
            switch (speedString) {
                case "Slow":
                    selectedSpeedDelay = 150;
                    break;
                case "Normal":
                    selectedSpeedDelay = 100;
                    break;
                case "Fast":
                    selectedSpeedDelay = 70;
                    break;
                case "Very Fast":
                    selectedSpeedDelay = 40;
                    break;
                default:
                    selectedSpeedDelay = 100;
                    break;
            }
        }
        String player1Colour = (String) ComboColour1.getSelectedItem();
        if (player1Colour == null) {
            player1Colour = "Green";
        }
        String player2Colour = (String) ComboColour2.getSelectedItem();
        if (player2Colour == null) {
            player2Colour = "Blue";
        }
        boolean hasBorder = true;
        String borderOptionString = (String) ComboBorder.getSelectedItem();
        if (borderOptionString != null) {
            hasBorder = borderOptionString.equals("With Border");
        }
        SoundLoader.stopGameMenuSound();
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof frame.GameFrame) {
            panel.MultiPlayerPanel mpPanel = new panel.MultiPlayerPanel(player1Name, player2Name, selectedSpeedDelay, player1Colour, player2Colour, hasBorder);
            mpPanel.setFocusable(true);
            ((frame.GameFrame) window).setContentPane(mpPanel);
            window.revalidate();
            window.repaint();
            javax.swing.SwingUtilities.invokeLater(() -> {
                mpPanel.requestFocusInWindow();
                mpPanel.grabFocus();
            });
        }
    }

    private void ComboSpeedActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playComboBoxSound();
    }

    private void ComboBorderActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playComboBoxSound();
        updatePreviewImages();
    }

    private void ComboColour1ActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playComboBoxSound();
        updatePreviewImages();
    }

    private void ComboColour2ActionPerformed(java.awt.event.ActionEvent evt) {
        SoundLoader.playComboBoxSound();
        updatePreviewImages();
    }

    private void FieldName1FocusGained(java.awt.event.FocusEvent evt) {
        if (FieldName1.getText().equals("Enter player 1 name.")) {
            FieldName1.setText("");
        }
    }

    private void FieldName1FocusLost(java.awt.event.FocusEvent evt) {
        if (FieldName1.getText().trim().isEmpty()) {
            FieldName1.setText("Enter player 1 name.");
        }
    }

    private void FieldName2FocusGained(java.awt.event.FocusEvent evt) {
        if (FieldName2.getText().equals("Enter player 2 name.")) {
            FieldName2.setText("");
        }
    }

    private void FieldName2FocusLost(java.awt.event.FocusEvent evt) {
        if (FieldName2.getText().trim().isEmpty()) {
            FieldName2.setText("Enter player 2 name.");
        }
    }

    private void updatePreviewImages() {
        String player1Color = (String) ComboColour1.getSelectedItem();
        String player2Color = (String) ComboColour2.getSelectedItem();
        String borderOption = (String) ComboBorder.getSelectedItem();
        updateSinglePreview(LabelPreviewImage1, player1Color, borderOption, PanelPreview,
                            currentPreviewImage1, "P1 Image Not Found", "Select P1 Options",
                            PanelPreview.getWidth() / 2, PanelPreview.getHeight());
        updateSinglePreview(LabelPreviewImage2, player2Color, borderOption, PanelPreview,
                            currentPreviewImage2, "P2 Image Not Found", "Select P2 Options",
                            PanelPreview.getWidth() / 2, PanelPreview.getHeight());
    }

    private void updateSinglePreview(javax.swing.JLabel targetLabel, String selectedColor, String selectedBorderOption, javax.swing.JPanel panelContext, BufferedImage currentImageRef, String notFoundText, String selectOptionsText, int defaultPanelWidth, int defaultPanelHeight) {
        if (selectedColor != null && selectedBorderOption != null) {
            currentImageRef = utils.ImageLoader.loadPreviewImage(selectedColor, selectedBorderOption, panelContext);
            if (currentImageRef != null) {
                int targetWidth = targetLabel.getWidth();
                int targetHeight = targetLabel.getHeight();
                if (targetWidth <= 0 || targetHeight <= 0) {
                    targetWidth = defaultPanelWidth;
                    targetHeight = defaultPanelHeight;
                }
                if (targetWidth <= 0) targetWidth = 150;
                if (targetHeight <= 0) targetHeight = 150;
                if (targetWidth > 0 && targetHeight > 0) {
                    targetLabel.setIcon(new ImageIcon(currentImageRef.getScaledInstance(
                        targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH)));
                    targetLabel.setText("");
                } else {
                    targetLabel.setIcon(new ImageIcon(currentImageRef));
                    targetLabel.setText("");
                    System.err.println("Warning: Could not scale preview image for " + selectedColor + " " + selectedBorderOption + ". Displaying original size.");
                }
            } else {
                targetLabel.setIcon(null);
                targetLabel.setText(notFoundText);
            }
        } else {
            targetLabel.setIcon(null);
            targetLabel.setText(selectOptionsText);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonStart;
    private javax.swing.JComboBox<String> ComboBorder;
    private javax.swing.JComboBox<String> ComboColour1;
    private javax.swing.JComboBox<String> ComboColour2;
    private javax.swing.JComboBox<String> ComboSpeed;
    private javax.swing.JTextField FieldName1;
    private javax.swing.JTextField FieldName2;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel LabelMap;
    private javax.swing.JLabel LabelMultiPlayer;
    private javax.swing.JLabel LabelPlayer1;
    private javax.swing.JLabel LabelPlayer2;
    private javax.swing.JLabel LabelPreview;
    private javax.swing.JLabel LabelPreviewImage1;
    private javax.swing.JLabel LabelPreviewImage2;
    private javax.swing.JLabel LabelSnakeColour1;
    private javax.swing.JLabel LabelSnakeColour2;
    private javax.swing.JLabel LabelSpeed;
    private javax.swing.JPanel PanelPreview;
    // End of variables declaration//GEN-END:variables
}
