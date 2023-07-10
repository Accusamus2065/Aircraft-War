package main.utils;

import main.obj.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {
    //background image
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
     //boss image
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.png");
    //explode image
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.gif");
    //self-plane image
    public static Image planeImg = Toolkit.getDefaultToolkit().getImage("imgs/plane.png");
    //self-bullet image
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/shell.png");
    //enemy bullet image
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/bullet.png");
    //enemy image
    public static Image enemyImg = Toolkit.getDefaultToolkit().getImage("imgs/enemy.png");

    //collection of allObjs
    public static List<GameObj> gameObjList = new ArrayList<>();
    //collection of deletedObjs
    public static List<GameObj> removeList = new ArrayList<>();
    //collection of bullets
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //collection of enemy bullets
    public static List<BulletObj> bulletObjList = new ArrayList<>();
    //collection of enemies
    public static List<EnemyObj> enemyObjList = new ArrayList<>();
    //collection of explosion
    public static List<ExplodeObj> explodeObjList = new ArrayList<>();

    //drawWord Tool
    public static void drawWord(Graphics gImage, String str, Color color, int size, int x, int y) {
        gImage.setColor(color);
        gImage.setFont(new Font("Time New Roman", Font.BOLD, size));
        gImage.drawString(str, x, y);
    }
}
