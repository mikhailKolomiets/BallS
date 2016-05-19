import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by mihail on 07.05.16.
 */
public class Jucle {

    public static ArrayList<Integer> records = new ArrayList<>();
    private static Point step = new Point(300, 200);
    private static int money = 0;
    public static int fuelCapasity = 2000;
    public static int fuelDown = 1;
    public static int moneyUp = 1;
    public static int levelUp = 50;
    public static String finalMessage = "Fuel is Empty!";
    public static int level = 1;
    public static LocalTime time = LocalTime.now().plusSeconds(60);



    public static void main(String[] args) throws Exception {
        boolean getFristic = false;
        LocalTime antiSpeedTime = time;
        System.out.println("Start point");
        for (int attempt = 1; attempt < 100; attempt++) {

            Map game = new Map();
            game.setLocation(step);

            ImproMirror mirror = new ImproMirror(game.maxSpeed, game.fuelCapacity, game.gamePoint);
            Frisic frisic = new Frisic(game.getSize().width, game.getSize().height);

            for (int go = 1; game.fuelCapacity > 0 && time.isAfter(LocalTime.now()) && levelUp < 11111; go++) {
                money = game.gamePoint;
                /**
                 * Speed ball
                 */
                game.maxSpeed = mirror.speed - game.old / 10000;
                Thread.sleep(50);
                frisic.being();
                game.frisic = frisic.point;
                if (Frisic.fuel == null)
                    game.fuel = frisic.beingFuel();

                if (Frisic.impro == null)
                    game.impro = frisic.beingImpro();
                if (Frisic.antiSpeedSqrt == null) {
                    game.antiSpeed = frisic.beingAntiSpeedSqrt();
                    antiSpeedTime = LocalTime.now().plusSeconds(15 * level);
                }
                else if (antiSpeedTime.isBefore(LocalTime.now())){
                    Frisic.antiSpeedSqrt = null;
                }
                /**
                 * Take fristics ball
                 */
                if (getFristic) {
                    getFristic = false;
                    money += frisic.delete(game.xpoint, game.ypoint);
                    game.fuelCapacity += (frisic.deleteFuel(game.xpoint, game.ypoint) == 1 ?
                            fueling(game.fuelCapacity) : 0);

                    if (frisic.deleteImpro(game.xpoint, game.ypoint) == 1) {
                        mirror.update(game.maxSpeed, game.fuelCapacity, money);
                        mirror.victorine();
                        game.maxSpeed = mirror.speed;
                        game.fuelCapacity = mirror.fuel;
                        money = mirror.money;
                        game.win = mirror.message;
                        go = 1;
                    }
                }
                /**
                 * Move main ball
                 */
                if (game.target.getX() != game.xpoint || game.target.getY() != game.ypoint) {
                    if (game.setMove) {
                        getFristic = true;
                        step = mov(game.maxSpeed, game.target.x, game.target.y, game.xpoint, game.ypoint);
                        game.moveTo(game.xpoint + step.x, game.ypoint + step.y);
                    }
                    if (Math.abs(game.target.getX() - game.xpoint) < Math.abs(step.x) / 2 + 1 ||
                            Math.abs(game.target.getY() - game.ypoint) < Math.abs(step.y) / 2 + 1) {
                        game.moveTo(game.target.x, game.target.y);
                        game.setMove = false;
                    }
                }


                if (game.win.length() > 0 && go % 70 == 0) {
                    game.win = moveString(game.win);
                    go += 67;
                }
                textColor(game.fuelCapacity);
                game.gamePoint = money;
                game.repaint();
                for (; game.pauzed; )
                    Thread.sleep(300);


                if (go % 10 == 1)
                    game.records = getRecords(game.old);

                if (time.isBefore(LocalTime.now().minusNanos(300)))
                    finalMessage = "Time is Empty!";
            }

            JOptionPane.showMessageDialog(game, finalMessage);
            records.add(game.old);
            finalMessage = "Fuel is Empty!";
            step.setLocation(game.getLocation());
            game.setVisible(false);
            fuelCapasity = 2000;
            level = fuelDown = moneyUp = 1;
            levelUp = 50;
            time = LocalTime.now().plusSeconds(60);
        }
    }

    private static Point mov(int speed, int toX, int toY, int fromX, int fromY) {
        int x = toX - fromX;
        int y = toY - fromY;
        double z = Math.sqrt(x * x + y * y);
        double degre = z / speed;
        x = (int) (x / degre);
        y = (int) (y / degre);
        return new Point(x, y);
    }

    private static int fueling(int fuel) {
        int fuelIn = money * 100;
        if (fuel + fuelIn > fuelCapasity) {
            money -= 20 - fuel / 100;
            return fuelCapasity - fuel;
        } else {
            int ret = money * 100;
            money = 0;
            return ret;
        }

    }

    private static void textColor(int fuel) {
        if (fuel < fuelCapasity / 5) {
            Map.color = Color.red;
        } else if (fuel > fuelCapasity / 2) {
            Map.color = Color.black;
        } else {
            Map.color = Color.blue;
        }
    }

    private static String moveString(String string) {
        if (string.length() > 1)
            return string.substring(1, string.length());
        else
            return "";
    }

    private static String getRecords(int old) {
        String ret = "";
        records.add(old);
        records.sort((x, y) -> Integer.compare(y, x));
        if (records.get(0) == 0) {
            records.remove(0);
            return "                                    SPASE - pause  ";
        }
        if (records.get(0).equals(old))
            ret = "NEW RECORD! ";
        int i = 1;
        for (int value : records) {
            if (value != old)
                ret += i + " " + value + " - ";
            else
                ret += "you " + value + " - ";
            i++;
        }
        records.remove(new Integer(old));
        if (ret.length() < 63)
            return ret.substring(0, ret.length() - 2);
        else
            return ret.substring(0, 61);
    }
}
