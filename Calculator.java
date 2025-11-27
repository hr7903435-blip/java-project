import java.util.Scanner;

public class Calculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double sub(double a, double b) {
        return a - b;
    }

    public static double mul(double a, double b) {
        return a * b;
    }

    public static double div(double a, double b) {
        return a / b;
    }

    public static double mod(double a, double b) {
        return a % b;
    }

    public static double power(double a, double b) {
        return Math.pow(a, b);
    }

    public static double squareRoot(double a) {
        return Math.sqrt(a);
    }

    public static long factorial(int n) {
        long f = 1;
        for (int i = 1; i <= n; i++) f *= i;
        return f;
    }

    public static double percentage(double total, double percent) {
        return (total * percent) / 100;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Add  2.Sub  3.Mul  4.Div  5.Mod  6.Power  7.Sqrt  8.Factorial  9.Percentage");
        int ch = sc.nextInt();

        if (ch == 7) {
            double a = sc.nextDouble();
            System.out.println(squareRoot(a));
        } else if (ch == 8) {
            int n = sc.nextInt();
            System.out.println(factorial(n));
        } else if (ch == 9) {
            double t = sc.nextDouble();
            double p = sc.nextDouble();
            System.out.println(percentage(t, p));
        } else {
            double a = sc.nextDouble();
            double b = sc.nextDouble();

            switch (ch) {
                case 1: System.out.println(add(a, b)); break;
                case 2: System.out.println(sub(a, b)); break;
                case 3: System.out.println(mul(a, b)); break;
                case 4: System.out.println(div(a, b)); break;
                case 5: System.out.println(mod(a, b)); break;
                case 6: System.out.println(power(a, b)); break;
                default: System.out.println("Invalid");
            }
        }
    }
}