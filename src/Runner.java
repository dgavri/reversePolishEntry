import java.util.Deque;
import java.util.LinkedList;

public class Runner {

    public static void main(String[] args) {
        String expression = "2001+28/2*7-12*2";
        Deque<Character> operators = new LinkedList<>();
        Deque<String> reversePolishEntry = new LinkedList<>();
        Deque<String> temp = new LinkedList<>();
        String number = "";
        for (char c : expression.toCharArray()) {
            if (c != '+' && c != '-' && c != '*' && c != '/') {
                number += c;
            } else {
                reversePolishEntry.add(number);
                number = "";
                if (operators.size() == 0) {
                    operators.add(c);
                } else {
                    if ((operators.peekLast() == '*' || operators.peekLast() == '/')
                            && (c == '+' || c == '-')) {
                        while (operators.peekLast() != '-' && operators.peekLast() != '+') {
                            reversePolishEntry.add(operators.pollLast().toString());
                        }
                        operators.add(c);
                    } else operators.add(c);
                }
            }
        }
        reversePolishEntry.add(number);
        while (operators.size() > 0) {
            reversePolishEntry.add(operators.pollLast().toString());
        }
//        reversePolishEntry.forEach(System.out::println);
        int result = 0;
        for (String s : reversePolishEntry) {
            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")) {
                temp.add(s);
            } else {
                Integer secondNumber = Integer.parseInt(temp.pollLast());
                Integer firstNumber = Integer.parseInt(temp.pollLast());
                switch (s) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        result = firstNumber / secondNumber;
                        break;
                }
                temp.add(String.valueOf(result));
            }
        }
        System.out.println(result);
    }
}
