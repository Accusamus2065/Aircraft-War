package main;

import main.obj.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static main.utils.GameUtils.*;

public class GameWin extends JFrame {
    //Game Status: 0.not started, 1.in-game, 2.pause, 3.failed to pass, 4.passed successfully
    public static int state = 0;
    public static int score = 0;
    Image offScreenImage = null;
    int width = 600;
    int height = 600;
    //repaint times
    int count = 1;
    int enemyCount = 0;

    //background object
    BgObj bgObj = new BgObj(bgImg, 0, -2000, 2);
    //self-plane object
    public PlaneObj planeObj = new PlaneObj(planeImg, 290, 550, 20, 30, 0, this);
    public BossObj bossObj = null;

    public void launch() {
        //Set the window visible
        this.setVisible(true);
        //Set the window size
        this.setSize(width, height);
        //Set the window position
        this.setLocationRelativeTo(null);
        //Set the window unable to be resized
        this.setResizable(false);
        //Set the terminate game method
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Set the window title
        this.setTitle("Aircraft War");

        gameObjList.add(bgObj);
        gameObjList.add(planeObj);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && state == 0) {
                    state = 1;
                    repaint();
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    switch (state) {
                        case 1 -> state = 2;
                        case 2 -> state = 1;
                    }
                }
            }
        });

        while (state != 3 && state != 4) {
            if (state == 1) {
                createObj();
            }
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(width, height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0, 0, width, height);
        if (state == 0) {
            gImage.drawImage(bgImg, 0, 0, null);
            gImage.drawImage(bossImg, 220, 120, null);
            gImage.drawImage(explodeImg, 270, 350, null);
            drawWord(gImage, "Click to Start", Color.yellow, 40, 170, 300);
        }
        if (state == 1) {
            gameObjList.addAll(explodeObjList);
            for (main.obj.GameObj gameObj : gameObjList) {
                gameObj.paintSelf(gImage);
            }
            gameObjList.removeAll(removeList);
        }
        if (state == 2) {
            gImage.drawImage(bgImg, 0, 0, null);
            drawWord(gImage, "Pause", Color.white, 50, 230, 330);
        }
        if (state == 3) {
            gImage.drawImage(explodeImg, planeObj.getX() - 35, planeObj.getY() - 50, null);
            drawWord(gImage, "Game Over", Color.red, 50, 165, 330);
        }
        if (state == 4) {
            gImage.drawImage(explodeImg, bossObj.getX() + 30, bossObj.getY(), null);
            drawWord(gImage, "Game Pass", Color.green, 50, 165, 330);
        }
        drawWord(gImage, "Score " + score, Color.green, 25, 30, 100);
        g.drawImage(offScreenImage, 0, 0, null);
        count++;
    }

    void createObj() {
        //self-bullet
        if (count % 15 == 0) {
            shellObjList.add(new ShellObj(shellImg, planeObj.getX() + 3, planeObj.getY() - 16, 14, 29, 5, this));
            gameObjList.add(shellObjList.get(shellObjList.size() - 1));
        }
        //enemies
        if (count % 15 == 0) {
            enemyObjList.add(new EnemyObj(enemyImg, (int) (Math.random() * 12 * 50 - 10), 0, 49, 36, 5, this));
            gameObjList.add(enemyObjList.get(enemyObjList.size() - 1));
            enemyCount++;
        }
        if (count % 15 == 0 && bossObj != null) {
            bulletObjList.add(new BulletObj(bulletImg, bossObj.getX() + 76, bossObj.getY() + 85, 15, 25, 5, this));
            gameObjList.add(bulletObjList.get(bulletObjList.size() - 1));
        }
        if (enemyCount > 50 && bossObj == null) {
            bossObj = new BossObj(bossImg, 250, 35, 155, 100, 5, this);
            gameObjList.add(bossObj);
        }
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
