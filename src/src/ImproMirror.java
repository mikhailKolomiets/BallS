/**
 * Created by mihail on 08.05.16.
 */
public class ImproMirror {
    int speed = 20;
    int fuel;
    int money;
    String message;

    public ImproMirror(int speed, int fuel, int money) {
        this.speed = speed;
        this.fuel = fuel;
        this.money = money;
    }

    public void victorine() {
        if (money < Jucle.levelUp) {
            int vin = (int) Math.floor(Math.random() * 10);
            int amount;
            switch (vin) {
                case 1:
                    speed += 5;
                    message = "You won speed +5 !";
                    break;
                case 2:
                    speed += 2;
                    message = "You won speed +2 !";
                    break;
                case 3:
                    amount = 200 + Jucle.fuelDown * 100;
                    fuel = fuel > Jucle.fuelCapasity - amount ? Jucle.fuelCapasity : fuel + amount;
                    message = "You won fuel +" + amount + " !";
                    break;
                case 4:
                    amount = 5 * Jucle.moneyUp;
                    money += amount;
                    message = "You won money +" + amount + " !";
                    break;
                case 5:
                    money += Jucle.moneyUp;
                    message = "You won money +" + Jucle.moneyUp + " !";
                    break;
                case 6:
                    money -= Jucle.moneyUp;
                    message = "You lose money -" + Jucle.moneyUp + " !";
                    break;
                case 7:
                    amount = 5 * Jucle.moneyUp;
                    money -= amount;
                    message = "You lose money -" + amount + " !";
                    break;
                case 8:
                    amount = 10 * Jucle.fuelDown;
                    money -= amount;
                    message = "You lose money -" + amount + " !";
                    break;
                case 9:
                    amount = 200 * Jucle.fuelDown;
                    fuel -= amount ;
                    message = "You lose fuel: -" + amount + " !";
                    break;
                default:
                    speed--;
                    message = "You lose speed -1 !";
                    break;
            }
            message += " Left " + (Jucle.levelUp - money) + " for Up.";
        } else {
            switch (Jucle.levelUp) {
                case 50:
                    levelUp(150, 2, 2000, 1);
                    message = "You got 2 level!";
                    money -= 50;
                    break;
                case 150:
                    levelUp(300, 5, 3000, 2);
                    message = "You got 3 level!";
                    money -= 150;
                    break;
                case 300:
                    levelUp(800, 7, 3000, 2);
                    message = "You got 4 level!";
                    money -= 300;
                    break;
                case 800:
                    levelUp(2000, 10, 5000, 3);
                    message = "You got 5 level!";
                    money -= 800;
                    break;
                case 2000:
                    levelUp(5000, 15, 10000, 4);
                    message = "You got 6 level!";
                    money -= 2000;
                    break;
                case 5000:
                    levelUp(10000, 25, 12000, 5);
                    message = "You got 7 level!";
                    money -= 5000;
                    break;
                case 10000:
                    levelUp(20000, 10, 5000, 5);
                    Jucle.finalMessage = "You win!";
                    message = "You WIN!!!";
                    break;

            }
            Jucle.level ++;
        }

    }

    public void update(int speed, int fuel, int money) {
        this.money = money;
        this.fuel = fuel;
        //System.out.println(speed + " " + fuel + " " + money);
    }

    private void levelUp(int levelUp, int moneyUp, int fuelCapacity, int fuelDown) {
        Jucle.levelUp = levelUp;
        Jucle.moneyUp = moneyUp;
        Jucle.fuelCapasity = fuelCapacity;
        Jucle.fuelDown = fuelDown;
    }

}
