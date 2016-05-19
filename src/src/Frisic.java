import java.awt.*;

/**
 * Created by mihail on 08.05.16.
 */
public class Frisic {
    boolean isExist;
    Point frameAlign = new Point();
    static Point point, fuel, impro, fuelSqrt, antiSpeedSqrt;
    private int distric = 10;


    public boolean being() {
        if (!isExist) {
            point = new Point((int) Math.floor(Math.random() * Math.abs(frameAlign.x - 30)) + 10,
                    (int) Math.floor(Math.random() * Math.abs(frameAlign.y - 60)) + 40);
            this.isExist = true;
        }
        return true;
    }

    public Point beingFuel() {
        if (fuel == null && (int) Math.floor(Math.random() * 300 * Jucle.fuelDown) == 1) {
            fuel = new Point((int) Math.floor(Math.random() * Math.abs(frameAlign.x - 20)) + 10,
                    (int) Math.floor(Math.random() * Math.abs(frameAlign.y - 50)) + 40);
        }
        return fuel;
    }

    public Point beingAntiSpeedSqrt() {
        if (antiSpeedSqrt == null && (int) Math.floor(Math.random() * 700) == 1) {
            antiSpeedSqrt = new Point((int) Math.floor(Math.random() * Math.abs(frameAlign.x - 20)) + 10,
                    (int) Math.floor(Math.random() * Math.abs(frameAlign.y - 50)) + 40);
        }
        return antiSpeedSqrt;
    }

    public int deleteFuel(int ballPointX, int ballPointY) {
        if (fuel != null)
            if (Math.abs(ballPointX - fuel.x) <= distric &&
                    Math.abs(ballPointY - fuel.y) <= distric) {
                fuel = null;
                return 1;
            }
        return 0;
    }

    public int delete(int ballPointX, int ballPointY) {
        if (Math.abs(ballPointX - point.x) <= distric &&
                Math.abs(ballPointY - point.y) <= distric) {
            this.isExist = false;
            point = null;
            Jucle.time = Jucle.time.plusSeconds(5);
            return Jucle.moneyUp;
        } else return 0;
    }

    public Point beingImpro(){
        if (impro == null && (int) Math.floor(Math.random() * 600 ) == 555) {
            impro = new Point((int) Math.floor(Math.random() * Math.abs(frameAlign.x - 40)) + 10,
                    (int) Math.floor(Math.random() * Math.abs(frameAlign.y - 60)) + 40);
        }
        return impro;
    }

    public int deleteImpro(int ballPointX, int ballPointY) {
        if (impro != null)
            if (Math.abs(ballPointX - impro.x) < distric &&
                    Math.abs(ballPointY - impro.y) < distric) {
                impro = null;
                return 1;
            }
        return 0;
    }





    Frisic(int x, int y) {
        frameAlign.setLocation(x, y);
    }
}
