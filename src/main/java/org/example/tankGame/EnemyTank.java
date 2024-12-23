package org.example.tankGame;

import java.util.Vector;

@SuppressWarnings("all")
public class EnemyTank extends Tank implements Runnable{
    Vector<Bullet> bullets = null;

    Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
        bullets = new Vector<>();
    }

    // to collect the enemy tanks and compare are they overlapped
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public boolean isTouchEnemyTank() {
        // the current enemy tank to compare with enemy tanks
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //if enemy tank direction up and down
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 40)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 60)
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //left coordinate comparison
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //right coordinate comparison
                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // if enemy tank direction right and left
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 60)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 40)
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {

                            //left coordinate comparison
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //right coordinate comparison
                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: //the current enemy tank is right
                // this.getX() + 60, this.getY()
                // this.getX() + 60, this.getY() + 40
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //if enemy tank direction up and down
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 40)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 60)
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //up coordinate comparison
                            // this.getX() + 60, this.getY()
                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY()<= enemyTank.getY() + 60) {
                                return true;
                            }
                            //down coordinate comparison
                            // this.getX() + 60, this.getY() + 40
                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 40 &&
                                    this.getY() + 40 >= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // if enemy tank direction right and left
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 60)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 40)
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {

                            //up coordinate comparison
                            // this.getX() + 60, this.getY()
                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //dowm coordinate comparison
                            // this.getX() + 60, this.getY() + 40
                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 60 &&
                                    this.getY() + 40>= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: // down
                // this.getX(), this.getY() + 60
                // this.getX() + 40, this.getY() + 60
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //if enemy tank direction up and down
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 40)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 60)
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //down left coordinate comparison
                            // this.getX(), this.getY() + 60
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 60 >= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //down right coordinate comparison
                            // this.getX() + 40, this.getY() + 60
                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 40 &&
                                    this.getY() + 60 >= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // if enemy tank direction right and left
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 60)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 40)
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {

                            //down left coordinate comparison
                            // this.getX(), this.getY() + 60
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() + 60 >= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //down right coordinate comparison
                            // this.getX() + 40, this.getY() + 60
                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 60 &&
                                    this.getY() + 60>= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: // left
                // this.getX(), this.getY()
                // this.getX(), this.getY() + 40
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //if enemy tank direction up and down
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 40)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 60)
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //up left coordinate comparison
                            // this.getX(), this.getY()
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //down left coordinate comparison
                            // this.getX(), this.getY() + 40
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 40 >= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // if enemy tank direction right and left
                        // enemy tank (enemyTank.getX(), enemyTank.getX() + 60)
                        // enemy tank (enemyTank.getY(), enemyTank.getX() + 40)
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {

                            //up left coordinate comparison
                            // this.getX(), this.getY()
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //down left coordinate comparison
                            // this.getX(), this.getY() + 40
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() + 40>= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;


        }
        return false;


    }

    @Override
    public void run() {
        while (true) {

            if (isLive && bullets.size() == 0) {

                Bullet bullet = null;

                switch (getDirect()) {
                    case 0:
                        bullet = new Bullet(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        bullet = new Bullet(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        bullet = new Bullet(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        bullet = new Bullet(getX(), getY() + 20, 3);
                        break;
                }

                bullets.add(bullet);
                new Thread(bullet).start();
            }

            switch(getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }


                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    moveLeft();
                    break;
            }


            setDirect((int)(Math.random() * 4));


            if(!isLive) {
                break;
            }


        }
    }
}
