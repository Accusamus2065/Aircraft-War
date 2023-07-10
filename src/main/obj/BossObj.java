package main.obj;

import main.GameWin;
import main.utils.GameUtils;

import java.awt.*;

public class BossObj extends GameObj {
    int life = 20;

    public BossObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        if (x > 550 || x < -50) {
            speed = -speed;
        }
        x += speed;
        for (ShellObj shellObj : GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                shellObj.setX(-100);
                shellObj.setY(100);
                GameUtils.removeList.add(shellObj);
                life--;
            }
            if (life <= 0) {
                GameWin.state = 4;
            }
        }
        gImage.setColor(Color.white);
        gImage.fillRect(20, 40, 200, 10);
        gImage.setColor(Color.red);
        gImage.fillRect(20, 40, life * 200 / 20, 10);

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
