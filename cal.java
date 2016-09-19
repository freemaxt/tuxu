import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tuxu on 15.09.2016.
 */
public class cal {

    public static void main(String[] args) {
        try {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] stroka = (preobroz(bufferedReader.readLine())).split(" ");
        bufferedReader.close();
        ArrayList<String> stack = new ArrayList<>();
        String result = "";

        for (String ss : stroka) {
            if (metod4islo(ss)) {             // число записывается в результат
                result += ss + " ";
            } else {                          // пришло не число
                if (ss.equals("(") || stack.size() == 0 || (stack.size() > 0 && stack.get(stack.size() - 1).equals("("))) {// пришла cкобка или стек пуст или стек с со скобкой
                    stack.add(ss);
                } else if (stack.size() > 0) {// стек не пуст
                    if (ss.equals(")")) {     // пришла закрывающая скобка - выталкивание стека до откр. скобки
                        while (true) {
                            if (stack.get(stack.size() - 1).equals("(")) {
                                stack.remove(stack.size() - 1);
                                break;
                            } else {
                                result += stack.get(stack.size() - 1) + " ";
                                stack.remove(stack.size() - 1);
                            }
                        }
                    } else {
                        if (prioritet(ss, stack.get(stack.size() - 1))) {   // приоритет оператора выше запись в стек
                            stack.add(ss);
                        } else {                                             //проритет равен или меньше
                            while (true) {
                                if ((stack.size() == 0) || ((stack.get(stack.size() - 1).equals("(")) || (prioritet(ss, stack.get(stack.size() - 1))))) {
                                    stack.add(ss);
                                    break;
                                } else {
                                    result += stack.get(stack.size() - 1) + " ";
                                    stack.remove(stack.size() - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        while (true) {                          // освобождение стека - запись в результат
            if (stack.size() == 0) {
                break;
            } else {
                result += stack.get(stack.size() - 1) + " ";
                stack.remove(stack.size() - 1);
            }
        }
        System.out.println("постфиксная запись "+result);
        String[] ress = result.split(" ");
        List list = Arrays.asList(ress);
        ArrayList<String> ar = new ArrayList(list);
        int i = 2;
        while (ar.size() != 1) {                    //цикл вычисления
            if (!metod4islo(ar.get(i))) {
                ar.set(i - 2, operators(ar.get(i - 2), ar.get(i - 1), ar.get(i)));
                ar.remove(i - 1);
                ar.remove(i - 1);
                i--;
            } else i++;
        }
            System.out.println(ar.get(0));

        }catch (IndexOutOfBoundsException x){
            System.out.println("некорректный ввод");
        }catch (NumberFormatException x){
            System.out.println(" - неверный символ");
        }catch (IOException x){
            System.out.println("Ошибка ввода-вывода");
        }
    }

    private static boolean metod4islo(String st) throws NumberFormatException {// проверка на число
        try {
            Integer.parseInt(st);
            return true;
        }catch (NumberFormatException e){

             if (!st.equals("(") && !st.equals(")") && !st.equals("-") && !st.equals("+") && !st.equals("/") && !st.equals("*") && !st.equals("^") ){
                 System.out.print(st);
                throw new NumberFormatException();
            }
            return false;
        }
    }

    private static String operators(String st1,String st2,String st3) { // резлультат операции
        int result=0;
        switch (st3.charAt(0)){
            case '+': result = Integer.parseInt(st1)+Integer.parseInt(st2);break;
            case '-': result = Integer.parseInt(st1)-Integer.parseInt(st2);break;
            case '*': result = Integer.parseInt(st1)*Integer.parseInt(st2);break;
            case '/': result = Integer.parseInt(st1)/Integer.parseInt(st2);break;
            case '^': result = (int)Math.pow(Integer.parseInt(st1),Integer.parseInt(st2));break;

        }
        return String.valueOf(result);
    }

    private static boolean prioritet (String s1 , String s2){           // приоритет операции
        if ( (s1.equals("*") || s1.equals("/")) && (s2.equals("+") || s2.equals("-")) ){
            return true;
        }
        else if  (s1.equals("^")) {
            if (s2.equals("^") || s2.equals("+") || s2.equals("-") || s2.equals("*") || s2.equals("/") ) {
                return true;
            }else return false;
        }
        else return false;
    }

    private static String  preobroz(String s){      //форматирование строки
           s = s.replaceAll("\\(", " ( ");
           s = s.replaceAll("\\)", " ) ");
           s = s.replaceAll("\\*", " * ");
           s = s.replaceAll("\\+", " + ");
           s = s.replaceAll("\\-", " - ");
           s = s.replaceAll("/","  / ");
           s = s.replaceAll("\\^", " ^ ");
           s = s.replaceAll(" +"," ");
        return s;
    }
}
