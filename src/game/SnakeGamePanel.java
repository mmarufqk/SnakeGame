package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;


import utils.SoundLoader;
import utils.ImageLoader; 


public class SnakeGamePanel extends JPanel implements ActionListener {

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private final int UNIT_SIZE = 20; 

    private ArrayList<int[]> snakeBody;
    private int foodX;
    private int foodY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private int DELAY; 
    private Color snakeColor;
    private boolean hasBorder;

    private int score = 0;
    
    private boolean gameStarted = false; 
    private boolean gameOver = false;    
    private boolean isPaused = false;

    
    private String selectedSnakeColorName;

    // Variabel gambar ular
    private BufferedImage snakeHeadImage;
    private BufferedImage snakeBodyImage;    
    private BufferedImage snakeTailImage;
    
    // Variabel gambar makanan
    private BufferedImage[] foodImages; 
    private BufferedImage currentFoodImage; 
    private String[] foodNames = {"Apple", "Banana", "Blueberry", "Carrot", "Cherry", "Corn", "Orange", "Peach", "Pear"}; 
    
    private BufferedImage borderImage;

    // Variabel font custom
    private Font customFont;

    // Interface untuk update score
    public interface ScoreUpdateListener {
        void onScoreUpdate(int newScore);
        void onGameOver(int finalScore);
        void onGameRestarted();
    }
    private ScoreUpdateListener scoreUpdateListener;

    // Konstruktor
    public SnakeGamePanel(int panelWidth, int panelHeight, int initialDelay, String initialSnakeColorName, boolean initialHasBorder) {
        this.SCREEN_WIDTH = panelWidth;
        this.SCREEN_HEIGHT = panelHeight;

        this.DELAY = initialDelay;
        this.selectedSnakeColorName = initialSnakeColorName; 
        this.snakeColor = convertColorStringToColor(initialSnakeColorName); 
        this.hasBorder = initialHasBorder;

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        
        loadImages(); 
        loadFonts();
        loadFoodImages(); 
        loadBorderImage(); 

        
        resetGame();
    }

    
    // Listener update skor
    public void setScoreUpdateListener(ScoreUpdateListener listener) {
        this.scoreUpdateListener = listener;
    }

    // Konversi String warna ke warna
    private Color convertColorStringToColor(String colorName) {
        switch (colorName) {
            case "Red": return Color.RED;
            case "Yellow": return Color.YELLOW;
            case "Green": return Color.GREEN;
            case "Blue": return Color.BLUE;
            default: return Color.GREEN; 
        }
    }
    
    // Loads snake body, head, tail berdasarkan warna yang dipilih
    private void loadImages() {
        String baseDir = "/assets/images/";
        snakeHeadImage = ImageLoader.loadImage(baseDir + "SnakeHead" + selectedSnakeColorName + ".png", this);
        snakeBodyImage = ImageLoader.loadImage(baseDir + "SnakeBody" + selectedSnakeColorName + ".png", this);
        snakeTailImage = ImageLoader.loadImage(baseDir + "SnakeTail" + selectedSnakeColorName + ".png", this);
    }
    
    // Memuat berbagai gambar makanan
    private void loadFoodImages() {
        foodImages = ImageLoader.loadFoodImages(foodNames, this);
    }

    // Memuat gambar border
    private void loadBorderImage() {
        borderImage = ImageLoader.loadBorderImage(this);
    }
    
    // Memuat fonts
    private void loadFonts() {
         try {
            InputStream is = getClass().getResourceAsStream("/assets/fonts/EASyText.ttf");
            if (is == null) {
                throw new IOException("Font file not found: EASyText.ttf");
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            customFont = customFont.deriveFont(Font.PLAIN, 20);    
        } catch (IOException | FontFormatException e) {
            System.err.println("Gagal memuat font kustom: " + e.getMessage());
            e.printStackTrace();
            customFont = new Font("Ink Free", Font.BOLD, 20);    
            JOptionPane.showMessageDialog(this, "Gagal memuat font kustom. Menggunakan font default.", "Error Font", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Method reset game
    public void resetGame() {
        snakeBody = new ArrayList<>();
        
        int startX = hasBorder ? UNIT_SIZE * 5 : (SCREEN_WIDTH / 2 / UNIT_SIZE) * UNIT_SIZE;
        int startY = hasBorder ? UNIT_SIZE * 5 : (SCREEN_HEIGHT / 2 / UNIT_SIZE) * UNIT_SIZE;

        snakeBody.add(new int[]{startX, startY}); 
        snakeBody.add(new int[]{startX - UNIT_SIZE, startY}); 
        snakeBody.add(new int[]{startX - 2 * UNIT_SIZE, startY});
        direction = 'R'; 
        score = 0;
        gameOver = false;
        running = false; 
        gameStarted = false; 
        isPaused = false; 

        spawnFood(); 

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(this.DELAY, this); 

        if (scoreUpdateListener != null) {
            scoreUpdateListener.onScoreUpdate(score);
            scoreUpdateListener.onGameRestarted(); 
        }
        repaint(); 
    }

    // Method start game
    public void startGame() { 
        if (!gameStarted && !running && !gameOver) { 
            gameStarted = true; 
            running = true;      
            isPaused = false;    
            timer.start();       
            repaint();           
        }
    }
    
    // Method resume game
    public void resumeGame() { 
        if (gameStarted && !running && !gameOver && isPaused) { 
            running = true;    
            isPaused = false; 
            timer.start();    
            repaint();        
        }
    }
    
    // Method stop (pause) game
    public void stopGame() { 
        if (running && !gameOver) { 
            running = false; 
            isPaused = true; 
            if (timer != null && timer.isRunning()) {
                timer.stop(); 
            }
            repaint(); 
        }
    }

    
    // method memeriksa apakah game berjalan
    public boolean isRunning() { 
        return running;
    }

    // method memeriksa apakah game sudah berjalan
    public boolean isGameStarted() { 
        return gameStarted;
    }

    // method memeriksa apakah game over
    public boolean isGameOver() { 
        return gameOver;
    }
    
    // method memeriksa apakah game berhenti
    public boolean isPaused() { 
        return isPaused;
    }
    
    // method atur arah snake
    public void setDirection(char newDirection) { 
        if (running && !isPaused) {
            switch (newDirection) {
                case 'U':
                    if (this.direction != 'D') this.direction = 'U';
                    break;
                case 'D':
                    if (this.direction != 'U') this.direction = 'D';
                    break;
                case 'L':
                    if (this.direction != 'R') this.direction = 'L';
                    break;
                case 'R':
                    if (this.direction != 'L') this.direction = 'R';
                    break;
            }
        }
    }

    // method spawn random food
    private void spawnFood() {
        Random random = new Random();
        boolean collisionWithSnake;
        int minX = hasBorder ? UNIT_SIZE : 0;
        int maxX = hasBorder ? SCREEN_WIDTH - 2 * UNIT_SIZE : SCREEN_WIDTH - UNIT_SIZE;
        int minY = hasBorder ? UNIT_SIZE : 0;
        int maxY = hasBorder ? SCREEN_HEIGHT - 2 * UNIT_SIZE : SCREEN_HEIGHT - UNIT_SIZE;

        do {
            foodX = minX + random.nextInt((maxX - minX) / UNIT_SIZE + 1) * UNIT_SIZE; 
            foodY = minY + random.nextInt((maxY - minY) / UNIT_SIZE + 1) * UNIT_SIZE; 

            collisionWithSnake = false;
            for (int[] segment : snakeBody) {
                if (segment[0] == foodX && segment[1] == foodY) {
                    collisionWithSnake = true;
                    break;
                }
            }
        } while (collisionWithSnake);

        if (foodImages != null && foodImages.length > 0) {
            currentFoodImage = foodImages[random.nextInt(foodImages.length)];
        } else {
            currentFoodImage = null;
            System.err.println("Tidak ada gambar makanan yang tersedia untuk ditampilkan.");
        }
    }

    // method move snake
    private void move() {
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            snakeBody.set(i, snakeBody.get(i - 1).clone());        
        }

        int[] head = snakeBody.get(0);
        switch (direction) {
            case 'U':
                head[1] -= UNIT_SIZE;
                break;
            case 'D':
                head[1] += UNIT_SIZE;
                break;
            case 'L':
                head[0] -= UNIT_SIZE;
                break;
            case 'R':
                head[0] += UNIT_SIZE;
                break;
        }

        checkFood();            
        checkCollisions();
    }

    // method untuk memeriksa apakah apakah kepala snake = food
    private void checkFood() {
        int[] head = snakeBody.get(0);
        if (head[0] == foodX && head[1] == foodY) {
            score++;
            if (scoreUpdateListener != null) {
                scoreUpdateListener.onScoreUpdate(score);        
            }
            snakeBody.add(snakeBody.get(snakeBody.size() - 1).clone());        
            spawnFood();
            SoundLoader.playEatingSound(); 
        }
    }

    // method check collisions
    private void checkCollisions() {
        int[] head = snakeBody.get(0);

        for (int i = snakeBody.size() - 1; i > 0; i--) {
            if (head[0] == snakeBody.get(i)[0] && head[1] == snakeBody.get(i)[1]) {
                gameOver = true;
                break;
            }
        }

        if (hasBorder) {
            if (head[0] < UNIT_SIZE || head[0] >= SCREEN_WIDTH - UNIT_SIZE ||
                head[1] < UNIT_SIZE || head[1] >= SCREEN_HEIGHT - UNIT_SIZE) {
                gameOver = true;
            }
        } else {
            if (head[0] < 0) head[0] = SCREEN_WIDTH - UNIT_SIZE;
            else if (head[0] >= SCREEN_WIDTH) head[0] = 0;
            if (head[1] < 0) head[1] = SCREEN_HEIGHT - UNIT_SIZE;
            else if (head[1] >= SCREEN_HEIGHT) head[1] = 0;
        }

        if (gameOver) {
            stopGame();
            SoundLoader.playGameOverSound();    

            if (scoreUpdateListener != null) {
                scoreUpdateListener.onGameOver(score);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);        
        draw(g);
    }
    
    // main method drawing
    private void draw(Graphics g) {
        
        g.setColor(new Color(20, 20, 20));
        if (hasBorder) {
             g.fillRect(UNIT_SIZE, UNIT_SIZE, SCREEN_WIDTH - 2 * UNIT_SIZE, SCREEN_HEIGHT - 2 * UNIT_SIZE);
        } else {
             g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); 
        }

        if (hasBorder) {
            if (borderImage != null) {
                for (int x = 0; x < SCREEN_WIDTH; x += UNIT_SIZE) {
                    g.drawImage(borderImage, x, 0, UNIT_SIZE, UNIT_SIZE, null);
                }
                for (int x = 0; x < SCREEN_WIDTH; x += UNIT_SIZE) {
                    g.drawImage(borderImage, x, SCREEN_HEIGHT - UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, null);
                }
                for (int y = UNIT_SIZE; y < SCREEN_HEIGHT - UNIT_SIZE; y += UNIT_SIZE) { 
                    g.drawImage(borderImage, 0, y, UNIT_SIZE, UNIT_SIZE, null);
                }
                for (int y = UNIT_SIZE; y < SCREEN_HEIGHT - UNIT_SIZE; y += UNIT_SIZE) { 
                    g.drawImage(borderImage, SCREEN_WIDTH - UNIT_SIZE, y, UNIT_SIZE, UNIT_SIZE, null);
                }
            } else {
                g.setColor(new Color(59, 59, 59)); 
                g.fillRect(0, 0, SCREEN_WIDTH, UNIT_SIZE);
                g.fillRect(0, SCREEN_HEIGHT - UNIT_SIZE, SCREEN_WIDTH, UNIT_SIZE);
                g.fillRect(0, 0, UNIT_SIZE, SCREEN_HEIGHT);
                g.fillRect(SCREEN_WIDTH - UNIT_SIZE, 0, UNIT_SIZE, SCREEN_HEIGHT);
            }
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        }   

        
        if (!gameStarted && !gameOver) {
            drawStartScreen(g); 
        } else if (gameOver) {
            drawGameOverScreen(g); 
        } else if (isPaused) { 
            drawGameContent(g); 
            drawPausedScreen(g); 
        } else if (running) { 
            drawGameContent(g); 
        }
    }
    
    // Menggambar elemen ular dan makanan pada panel game.
    // Menangani penggambaran kepala, badan, dan ekor ular dengan rotasi yang sesuai,
    private void drawGameContent(Graphics g) {
        if (currentFoodImage != null) {
            g.drawImage(currentFoodImage, foodX, foodY, UNIT_SIZE, UNIT_SIZE, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
        }

        for (int i = 0; i < snakeBody.size(); i++) {
            int currentX = snakeBody.get(i)[0];
            int currentY = snakeBody.get(i)[1];

            if (i == 0) { 
                if (snakeHeadImage != null) {
                    Graphics2D g2dHead = (Graphics2D) g.create();
                    double headRotation = 0;
                    if (direction == 'R') headRotation = Math.toRadians(0);
                    else if (direction == 'D') headRotation = Math.toRadians(90);
                    else if (direction == 'L') headRotation = Math.toRadians(180);
                    else if (direction == 'U') headRotation = Math.toRadians(270);

                    g2dHead.translate(currentX + UNIT_SIZE / 2, currentY + UNIT_SIZE / 2);
                    g2dHead.rotate(headRotation);
                    g2dHead.drawImage(snakeHeadImage, -UNIT_SIZE / 2, -UNIT_SIZE / 2, UNIT_SIZE, UNIT_SIZE, null);
                    g2dHead.dispose();
                } else {
                    g.setColor(this.snakeColor.brighter());
                    g.fillRect(currentX, currentY, UNIT_SIZE, UNIT_SIZE);
                }
            } else if (i == snakeBody.size() - 1) { 
                if (snakeTailImage != null) {
                    Graphics2D g2dTail = (Graphics2D) g.create();
                    int prevX = snakeBody.get(i - 1)[0];
                    int prevY = snakeBody.get(i - 1)[1];

                    double tailRotation = 0;
                    if (currentX < prevX) { 
                        tailRotation = Math.toRadians(0);
                    } else if (currentX > prevX) { 
                        tailRotation = Math.toRadians(180);
                    } else if (currentY < prevY) { 
                        tailRotation = Math.toRadians(90);
                    } else if (currentY > prevY) { 
                        tailRotation = Math.toRadians(270);
                    }

                    g2dTail.translate(currentX + UNIT_SIZE / 2, currentY + UNIT_SIZE / 2);
                    g2dTail.rotate(tailRotation);
                    g2dTail.drawImage(snakeTailImage, -UNIT_SIZE / 2, -UNIT_SIZE / 2, UNIT_SIZE, UNIT_SIZE, null);
                    g2dTail.dispose();
                } else {
                    g.setColor(this.snakeColor.darker());
                    g.fillRect(currentX, currentY, UNIT_SIZE, UNIT_SIZE);
                }
            } else { 
                if (snakeBodyImage != null) {
                    Graphics2D g2dBody = (Graphics2D) g.create(); 
                    int prevX = snakeBody.get(i - 1)[0];
                    int prevY = snakeBody.get(i - 1)[1];
                    
                    double bodyRotation = 0;
                    if (currentX == prevX) { 
                        bodyRotation = Math.toRadians(90); 
                    } else { 
                        bodyRotation = Math.toRadians(0); 
                    }

                    g2dBody.translate(currentX + UNIT_SIZE / 2, currentY + UNIT_SIZE / 2);
                    g2dBody.rotate(bodyRotation);
                    g2dBody.drawImage(snakeBodyImage, -UNIT_SIZE / 2, -UNIT_SIZE / 2, UNIT_SIZE, UNIT_SIZE, null);
                    g2dBody.dispose();
                } else {
                    g.setColor(this.snakeColor);
                    g.fillRect(currentX, currentY, UNIT_SIZE, UNIT_SIZE);
                }
            }
        }
    }

    // Menggambar pesan layar awal, meminta pemain menekan spasi untuk memulai
    private void drawStartScreen(Graphics g) {
        g.setColor(Color.WHITE);
        
        Font startFont = customFont.deriveFont(Font.BOLD, 35f); 
        g.setFont(startFont);
        
        String message = "Press Space to Start";
        int maxWidthForMainMessage = SCREEN_WIDTH - 100; 
        int startYForMainMessage = SCREEN_HEIGHT / 2 - 50; 

        int nextY = drawWrappedCenteredString(g, message, startYForMainMessage, maxWidthForMainMessage);

        Font instructionFont = customFont.deriveFont(Font.PLAIN, 15f); 
        g.setFont(instructionFont);
        
        String instruction = "Use Arrow Keys to Move";
        int maxWidthForInstruction = SCREEN_WIDTH - 100; 
        int startYForInstructionMessage = nextY + 30; 

        drawWrappedCenteredString(g, instruction, startYForInstructionMessage, maxWidthForInstruction);
    }

    // Menggambar layar game over, menampilkan pesan "Game Over!", skor akhir, dan instruksi untuk memulai ulang permainan.
    private void drawGameOverScreen(Graphics g) {
        g.setColor(Color.RED);
        Font gameOverMainFont = customFont.deriveFont(Font.BOLD, 35f); 
        g.setFont(gameOverMainFont);
        
        String gameOverMessage = "Game Over!";
        int maxWidthForGameOverMessage = SCREEN_WIDTH - 100; 
        int startYForGameOverMessage = SCREEN_HEIGHT / 2 - 60; 

        int nextY = drawWrappedCenteredString(g, gameOverMessage, startYForGameOverMessage, maxWidthForGameOverMessage);

        g.setColor(Color.WHITE);
        Font scoreFont = customFont.deriveFont(Font.BOLD, 15f);
        g.setFont(scoreFont);
        FontMetrics metrics = getFontMetrics(scoreFont); 
        String scoreMessage = "Score: " + score;
        int startYForScoreMessage = nextY + 30; 

        g.drawString(scoreMessage, (SCREEN_WIDTH - metrics.stringWidth(scoreMessage)) / 2, startYForScoreMessage);

        Font restartFont = customFont.deriveFont(Font.BOLD, 15f);
        g.setFont(restartFont);
        
        String restartMessage = "Press Space to Play Again";
        int maxWidthForRestart = SCREEN_WIDTH - 100; 
        int startYForRestartMessage = startYForScoreMessage + metrics.getHeight() + 30; 

        drawWrappedCenteredString(g, restartMessage, startYForRestartMessage, maxWidthForRestart);
    }

    // Menggambar overlay "GAME PAUSED" ketika permainan dijeda
    private void drawPausedScreen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g.setColor(Color.YELLOW);
        Font pauseFont = customFont.deriveFont(Font.BOLD, 35f); 
        g.setFont(pauseFont);
        
        String pauseMessage = "GAME PAUSED";
        int maxWidthForPauseMessage = SCREEN_WIDTH - 100; 
        int startYForPauseMessage = SCREEN_HEIGHT / 2 - 60; 

        int nextY = drawWrappedCenteredString(g, pauseMessage, startYForPauseMessage, maxWidthForPauseMessage);

        g.setColor(Color.WHITE);
        Font instructionFont = customFont.deriveFont(Font.PLAIN, 15f);
        g.setFont(instructionFont);
        
        String resumeInstruction = "Press 'Pause' button or Space to Resume";
        int maxWidthForInstruction = SCREEN_WIDTH - 100; 
        int startYForInstructionMessage = nextY + 30; 

        drawWrappedCenteredString(g, resumeInstruction, startYForInstructionMessage, maxWidthForInstruction);
    }

    // Metode pembantu untuk menggambar string yang dibungkus dalam lebar maksimum tertentu dan diposisikan secara horizontal di tengah panel
    private int drawWrappedCenteredString(Graphics g, String text, int startY, int maxWidth) {
        Font currentFont = g.getFont();
        FontMetrics metrics = g.getFontMetrics(currentFont);

        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        int lineHeight = metrics.getHeight();
        int currentY = startY;

        for (String word : words) {
            String testLine = currentLine.length() == 0 ? word : currentLine.toString() + " " + word;
            int testLineWidth = metrics.stringWidth(testLine);
            
            if (testLineWidth < maxWidth) {
                if (currentLine.length() == 0) {
                    currentLine.append(word);
                } else {
                    currentLine.append(" ").append(word);
                }
            } else {
                int x = (SCREEN_WIDTH - metrics.stringWidth(currentLine.toString())) / 2;
                g.drawString(currentLine.toString(), x, currentY);
                currentY += lineHeight;
                currentLine = new StringBuilder(word);
            }
        }
        if (currentLine.length() > 0) {
            int x = (SCREEN_WIDTH - metrics.stringWidth(currentLine.toString())) / 2;
            g.drawString(currentLine.toString(), x, currentY);
        }
        return currentY; 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !gameOver) {    
            move();            
        }
        repaint();    
    }
}