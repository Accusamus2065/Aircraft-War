package main.obj;

import main.GameWin;
import main.utils.GameUtils;

import java.awt.*;

public class EnemyObj extends GameObj {
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public EnemyObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        if (this.getRec().intersects(this.frame.planeObj.getRec())) {
            GameWin.state = 3;
        }
        if (y > 600) {
            this.x = -200;
            this.y = 200;
             GameUtils.removeList.add(this);
        }
        y += speed;
        for (ShellObj shellObj : GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                ExplodeObj explodeObj = new ExplodeObj(x, y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                shellObj.setX(-100);
                shellObj.setY(100);
                this.x = -200;
                this.y = 200;
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
                GameWin.score += 1;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}