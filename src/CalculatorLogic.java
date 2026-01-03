import java.util.Stack;

public class CalculatorLogic {

    public double evaluate(String expr) {
        try {
            return evaluateExpression(expr);
        } catch (Exception e) {
            throw new RuntimeException("Invalid Expression");
        }
    }

    private double evaluateExpression(String expr) {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (Character.isWhitespace(ch)) continue;

            // Number
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() &&
                        (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(sb.toString()));
            }

            // Function
            else if (Character.isLetter(ch)) {
                StringBuilder fn = new StringBuilder();
                while (i < expr.length() && Character.isLetter(expr.charAt(i))) {
                    fn.append(expr.charAt(i++));
                }
                i--; // step back
                ops.push(fn.charAt(0)); // s,c,t,l,q
            }

            else if (ch == '(') {
                ops.push(ch);
            }

            else if (ch == ')') {
                while (ops.peek() != '(') {
                    applyOp(values, ops.pop());
                }
                ops.pop(); // remove '('

                if (!ops.isEmpty() && Character.isLetter(ops.peek())) {
                    applyOp(values, ops.pop());
                }
            }

            else if (isOperator(ch)) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
                    applyOp(values, ops.pop());
                }
                ops.push(ch);
            }
        }

        while (!ops.isEmpty()) {
            applyOp(values, ops.pop());
        }

        return values.pop();
    }

    private void applyOp(Stack<Double> values, char op) {
        if (op == 's') values.push(Math.sin(values.pop()));
        else if (op == 'c') values.push(Math.cos(values.pop()));
        else if (op == 't') values.push(Math.tan(values.pop()));
        else if (op == 'l') values.push(Math.log(values.pop()));
        else if (op == 'q') values.push(Math.sqrt(values.pop()));
        else {
            double b = values.pop();
            double a = values.pop();
            switch (op) {
                case '+': values.push(a + b); break;
                case '-': values.push(a - b); break;
                case '*': values.push(a * b); break;
                case '/': values.push(a / b); break;
                case '^': values.push(Math.pow(a, b)); break;
            }
        }
    }

    private boolean isOperator(char c) {
        return "+-*/^".indexOf(c) >= 0;
    }

    private int precedence(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        if (c == '^') return 3;
        return 0;
    }
}

