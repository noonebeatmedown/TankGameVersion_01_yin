package org.example.tankGame;

import sun.net.www.content.image.png;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {

    Hero hero = null;

    Vector<EnemyTank> enemyTanks = null;
    Vector<Node> nodes = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    // AePlayWave aePlayWave = new AePlayWave("src\\7301.wav");
    AePlayWave aePlayWave2 = new AePlayWave("src\\wood-crack-1-105890.wav");
    AePlayWave aePlayWave3 = new AePlayWave("src\\bubble-pop-100784.wav");

    public MyPanel(String key) {
        // nodes = Record.getNodeAndEnemyTank();
        hero = new Hero(600, 100);
        enemyTanks = new Vector<>();
        Record.setEnemyTanks(enemyTanks);

        switch(key) {
            case "1": // new game-- need to reset the enemy tank num
                Record.setAllEnemyTankNum(0);
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    enemyTank.setEnemyTanks(enemyTanks); //!!!
                    enemyTank.setDirect(2);
                    new Thread(enemyTank).start();
                    Bullet bullet = new Bullet(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.bullets.add(bullet);
                    new Thread(bullet).start();
                    enemyTanks.add(enemyTank);

                }
                break;
            case "2":
                nodes = Record.getNodeAndEnemyTank();
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setEnemyTanks(enemyTanks); //!!!
                    enemyTank.setDirect(node.getDirect());
                    new Thread(enemyTank).start();
                    Bullet bullet = new Bullet(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.bullets.add(bullet);
                    new Thread(bullet).start();
                    enemyTanks.add(enemyTank);

                }
                break;
            default:
                System.out.println("Invalid input....");
        }



        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosion-gif-transparent-13.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosion-gif-transparent-14.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosion-gif-transparent-20.png"));

        //aePlayWave.play();

    }

    public void hitHero() {

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.bullets.size(); j++) {
                Bullet bullet = enemyTank.bullets.get(j);
                if (hero.isLive && bullet.isLive) {
                    hitTank(bullet, hero);

                }
            }

        }

    }

    public void hitEnemyTank() {
        for (int j = 0; j < hero.bullets.size(); j++) {
            Bullet bullet = hero.bullets.get(j);
            if(bullet != null && bullet.isLive) {
                for(int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(bullet, enemyTank);

                }

            }
        }
    }


    public void hitTank(Bullet b, Tank enemyTank) { // because of only one bullet is being compare,so might happened something wierd which some bullets are useless
        switch(enemyTank.getDirect()) {
            case 0:
            case 2:
                if(b.x > enemyTank.getX() && b.x < enemyTank.getX() + 40
                && b.y > enemyTank.getY() && b.y < enemyTank.getY() + 60) {
                    b.isLive = false;
                    enemyTank.isLive = false;
                    aePlayWave2.play();
                    enemyTanks.remove(enemyTank); // prevent enemyTank is transparent but still can be detected.
                    if (enemyTank instanceof EnemyTank) {
                        Record.addEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);

                }
                break;
            case 1:
            case 3:
                if(b.x > enemyTank.getX() && b.x < enemyTank.getX() + 60
                        && b.y > enemyTank.getY() && b.y < enemyTank.getY() + 40) {
                    b.isLive = false;
                    enemyTank.isLive = false;
                    aePlayWave2.play();
                    enemyTanks.remove(enemyTank); // prevent enemyTank is transparent but still can be detected.
                    if (enemyTank instanceof EnemyTank) {
                        Record.addEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);

                }
                break;

        }

    }

    public void showInfo(Graphics g) {

        g.setColor(Color.BLACK);
        Font comics = new Font("", Font.BOLD, 25);
        g.setFont(comics);
        g.drawString("Number of Died Enemy Tank", 1020, 30);
        drawTank(1020, 60, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString(Record.getAllEnemyTankNum() + "", 1080, 100);


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);
        //
        if (hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }


//        if(hero.bullet != null && hero.bullet.isLive) {
//            g.draw3DRect(hero.bullet.x, hero.bullet.y, 1, 1, false);
//        }

        for (int i = 0; i < hero.bullets.size(); i++) {
            Bullet bullet = hero.bullets.get(i);
            if(bullet != null && bullet.isLive) {
                g.draw3DRect(bullet.x, bullet.y, 1, 1, false);
            } else {
                hero.bullets.remove(bullet);
            }

        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            try { // to make first bomb appear
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();

            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(),1 );
                for (int j = 0; j < enemyTank.bullets.size(); j++) {
                    Bullet bullet = enemyTank.bullets.get(j);
                    if (bullet.isLive) {
                        g.draw3DRect(bullet.x, bullet.y, 1,1, false);
                    } else {
                        enemyTank.bullets.remove(bullet);
                    }
                }
            }


        }



    }

    public void drawTank(int x, int y, Graphics g, int direction, int type) {

        switch (type) {
            case 0:
                g.setColor(Color.yellow);
                break;
            case 1:
                g.setColor(Color.pink);
                break;

        }

        switch (direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y , 10, 60, false);
                g.fill3DRect(x + 10, y + 10 , 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x , y + 30 , 60, 10, false);
                g.fill3DRect(x + 10, y + 10 , 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 20, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y , 10, 60, false);
                g.fill3DRect(x + 10, y + 10 , 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x , y + 30 , 60, 10, false);
                g.fill3DRect(x + 10, y + 10 , 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 20, y + 20, x, y + 20);
                break;
            default:
                System.out.println("nb");
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent .VK_LEFT) {
            hero.setDirect(3);
            hero.moveLeft();
        }

//        if (e.getKeyCode() == KeyEvent.VK_J) {
//            if (hero.bullet == null || !(hero.bullet.isLive)) {
//                hero.shotEnemyTank();
//            }
//        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            if (hero.bullets.size() <= 5) {
                aePlayWave3.play();
                hero.shotEnemyTank();
            }
        }



        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hitEnemyTank();
            hitHero();

            this.repaint();
        }
    }
}
