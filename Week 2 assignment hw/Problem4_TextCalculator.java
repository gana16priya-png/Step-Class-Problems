import java.util.*;

public class Problem4_TextCalculator {
    public static boolean validateExpression(String expr) {
        int parenCount = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!(Character.isDigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || c == ' ' || c == '(' || c == ')')) {
                return false;
            }
            if (c == '(') parenCount++;
            if (c == ')') parenCount--;
            if (parenCount < 0) return false;
        }
        return parenCount == 0;
    }
    public static int evaluate(String expr, StringBuilder steps) {
        // Handle parentheses
        while (expr.contains("(")) {
            int open = expr.lastIndexOf("(");
            int close = expr.indexOf(")", open);
            String inside = expr.substring(open + 1, close);
            int val = evaluateNoParen(inside, steps);
            expr = expr.substring(0, open) + val + expr.substring(close + 1);
            steps.append("After evaluating parentheses: ").append(expr).append("\n");
        }
        return evaluateNoParen(expr, steps);
    }
    private static int evaluateNoParen(String expr, StringBuilder steps) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        expr = expr.replaceAll("\\s+", "");
        for (int i = 0; i < expr.length(); ) {
            char c = expr.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                nums.add(Integer.parseInt(expr.substring(i, j)));
                i = j;
            } else {
                ops.add(c);
                i++;
            }
        }
        for (int i = 0; i < ops.size(); ) {
            char op = ops.get(i);
            if (op == '*' || op == '/') {
                int a = nums.get(i);
                int b = nums.get(i + 1);
                int result = (op == '*') ? a * b : a / b;
                nums.set(i, result);
                nums.remove(i + 1);
                ops.remove(i);
                steps.append("Step: ").append(expr).append(" -> ").append(result).append("\n");
            } else {
                i++;
            }
        }
        int result = nums.get(0);
        for (int i = 0; i < ops.size(); i++) {
            char op = ops.get(i);
            int b = nums.get(i + 1);
            if (op == '+') result += b;
            else result -= b;
            steps.append("Step: ").append(result).append("\n");
        }

        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter mathematical expression:");
        String expr = sc.nextLine();

        if (!validateExpression(expr)) {
            System.out.println("Invalid Expression!");
            return;
        }

        StringBuilder steps = new StringBuilder();
        steps.append("Original Expression: ").append(expr).append("\n");
        int result = evaluate(expr, steps);

        steps.append("Final Result: ").append(result).append("\n");
        System.out.println(steps.toString());
    }
}
