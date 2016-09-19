import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tuxu on 14.09.2016.
 */
public class console_calc {
    public static void main(String[] args) throws IOException {
        double d1,d2;
        String znak;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("первое число :");
        try {
                d1 = Double.parseDouble(bufferedReader.readLine());
                System.out.print("второе число :");
                d2 = Double.parseDouble(bufferedReader.readLine());
                System.out.print("оператор( + - * / ^ ) :");
                znak = bufferedReader.readLine();
            if (znak.equals("+")) {
                System.out.println(d1 + "+" + d2 + "=" + (d1 + d2));
            }else if (znak.equals("-")) {
                System.out.println(d1 + "-" + d2 + "=" + (d1 - d2));
            }else if (znak.equals("*")) {
                System.out.println(d1 + "*" + d2 + "=" + (d1 * d2));
            }else if (znak.equals("/")) {
                System.out.println(d1 + "/" + d2 + "=" + (d1 / d2));
            }else if (znak.equals("^")) {
                System.out.println(d1 + "^" + d2 + "=" + (Math.pow(d1,d2)));
            }else System.out.println("недопустимый оператор");
        }catch (NumberFormatException e){
        System.out.println("неверное число");
        }
    }
}
