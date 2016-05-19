
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.LocalTime;

/**
 * Created by mihail on 07.05.16.
 */
public class Map extends JFrame {
    int xpoint = 0;
    int ypoint = 0;
    int maxSpeed = 20;
    int gamePoint, old, cashTime, fuelCapacity = 200;
    private long pauseTime;
    Point target = new Point();
    boolean setMove, pauzed;
    String info, infoDown, time, win = "", records = "";
    Point frisic, fuel, impro, antiSpeed;
    public static Color color = Color.cyan;
    Color back = new Color(0x2EC478);
   // private int antiBonusSqrt = 100, bonusSqrt = 100;

    Map() {
        super("Version 1.4");
        setSize(900, 600);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        xpoint = getSize().width / 2;
        ypoint = getSize().height / 2;
        target.setLocation(xpoint, ypoint);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                target.setLocation(e.getX(), e.getY());
                setMove = true;
            }
        });

        addKeyListener(new KeyAdapter() {
            int speedMoove = 20;

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        target.setLocation(xpoint, ypoint - speedMoove);
                        setMove = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        target.setLocation(xpoint, ypoint + speedMoove);
                        setMove = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        target.setLocation(xpoint - speedMoove, ypoint);
                        setMove = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        target.setLocation(xpoint + speedMoove, ypoint);
                        setMove = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        pauzed = !pauzed;
                        if (!pauzed)
                            Jucle.time = LocalTime.now().plusSeconds(pauseTime);
                }
            }
        });

    }


    public void paint(Graphics g) {
        info = "Money: " + gamePoint + " $. Old: " + old + ". Fuel: " + fuelCapacity + ". Speed: " + maxSpeed +
                ".      " + win;
        if (gamePoint > 0)
            infoDown = "Level: " + Jucle.level + " (" + gamePoint * 100 / Jucle.levelUp + "%)";
        else
            infoDown = "Level: " + Jucle.level + " (0%)";

        g.setColor(back);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (antiSpeed != null) {
            g.setColor(Color.PINK);
            //antiBonusSqrt += antiBonusSqrt + (Jucle.level - 1) * 50;
            g.fillRect(antiSpeed.x - 50, antiSpeed.y - 50, 100, 100);
        }
        g.setColor(Color.blue);
        g.fillOval(xpoint - 10, ypoint - 10, 20, 20);

        g.setColor(color);
        g.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        g.drawString(info, 0, 40);
        g.drawString(infoDown, 0, 590);
        cashTime = Jucle.time.toSecondOfDay() - LocalTime.now().toSecondOfDay();
        info = cashTime % 60 > 9 ? "" : "0";
        time = "Time: " + (cashTime > 59 ?
                "" + cashTime / 60 + " : " + info + cashTime % 60 : info + cashTime);
        g.drawString(time, 750, 590);
        g.drawString(records, 150, 590);

        if (pauzed) {
            pauseTime = cashTime;
            g.setColor(Color.PINK);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 55));
            g.drawString("Pause", 350, 300);

        }


        if (frisic != null) {
            g.setColor(Color.orange);
            g.fillOval(frisic.x - 5, frisic.y - 5, 10, 10);
        }

        if (fuel != null) {
            g.setColor(Color.blue);
            g.fillOval(fuel.x -5 , fuel.y - 5 , 10, 10);
        }
        if (impro != null) {
            g.setColor(Color.red);
            g.fillOval(impro.x - 5, impro.y - 5, 10, 10);
        }

    }


    void moveTo(int xpoint, int ypoint) {
        old += Math.sqrt(Math.pow(xpoint-this.xpoint, 2) + Math.pow(ypoint - this.ypoint, 2));
        this.xpoint = xpoint;
        this.ypoint = ypoint;
        this.fuelCapacity -= Jucle.fuelDown;
    }
}
