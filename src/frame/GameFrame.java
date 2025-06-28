package frame;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameFrame.class.getName());

    public GameFrame() {
        initComponents();
    }

    public GameFrame(javax.swing.JPanel panel) {
        this();
        setMainPanel(panel);
    }

    public void setMainPanel(javax.swing.JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusTraversalPolicyProvider(true);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setTitle("Snake Game - Final Project Alprokom 2");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/icon.png")));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(812, 607));
        setLocationRelativeTo(null);
    }
    // </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new GameFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
