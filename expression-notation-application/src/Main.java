import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    static boolean isValidChoice = false;
    static String exp;
    static String tryAgain;
    static boolean isValidExpression;

    static int choice;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        printMenu();


        THIS:
        while (!isValidChoice) {
            System.out.print("\n\t\t\tEnter Choice: ");
            try {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        isValidChoice = true;
                        do {
                            System.out.println('\u000c');
                            System.out.print("\nEnter the Infix Expression: ");
                            exp = input.next().replaceAll("\\s", "");
                            isValidExpression = isInfixValid(exp);
                            if (isValidExpression) {
                                printResult("Infix to Postfix", exp.toUpperCase(), infixToPostFix(exp).toUpperCase());
                                input.nextLine();
                                while (true) {
                                    System.out.print("\n   Try Again (Y/N): ");
                                    tryAgain = input.nextLine();
                                    if (tryAgain.equalsIgnoreCase("Y")) {
                                        break;
                                    } else if (tryAgain.equalsIgnoreCase("N")) {
                                        isValidChoice = false;
                                        System.out.println("\u000c");
                                        printMenu();
                                        continue THIS;
                                    } else {
                                        System.out.println("\n\tInvalid Option.");
                                    }
                                }
                            } else {
                                PressAnyKey();
                            }
                        } while (true);
                    case 2:
                        isValidChoice = true;
                        do {
                            System.out.println('\u000c');
                            System.out.print("\nEnter the Infix Expression: ");
                            exp = input.next().replaceAll("\\s", "");
                            isValidExpression = isInfixValid(exp);
                            if (isValidExpression) {
                                printResult("Infix to Prefix", exp.toUpperCase(), infixToPrefix(exp).toUpperCase());
                                input.nextLine();
                                while (true) {
                                    System.out.print("\n\tTry Again (Y/N): ");
                                    tryAgain = input.nextLine();
                                    if (tryAgain.equalsIgnoreCase("Y")) {
                                        break;
                                    } else if (tryAgain.equalsIgnoreCase("N")) {
                                        isValidChoice = false;
                                        printMenu();
                                        continue THIS;
                                    } else {
                                        System.out.println("\n\tInvalid Option.");
                                    }
                                }
                            } else {
                                PressAnyKey();
                            }
                        } while (true);
                    case 3:
                        isValidChoice = true;
                        do {
                            System.out.println('\u000c');
                            System.out.print("\nEnter the Postfix Expression: ");
                            exp = input.next().replaceAll("\\s", "");
                            isValidExpression = isPostfixValid(exp);
                            if (isValidExpression) {
                                System.out.println("\n\n-------------------------------------------");
                                System.out.println("\t\t\t   Postfix to Infix");
                                System.out.println("-------------------------------------------");
                                System.out.println("\n  Postfix Expression: " + exp.toUpperCase() + "\n");
                                System.out.println("Infix Expression: " + postfixToInfix(exp) + "\n");
                                System.out.println("-------------------------------------------");
                                input.nextLine();
                                while (true) {
                                    System.out.print("\n\tTry Again (Y/N): ");
                                    tryAgain = input.nextLine();
                                    if (tryAgain.equalsIgnoreCase("Y")) {
                                        break;
                                    } else if (tryAgain.equalsIgnoreCase("N")) {
                                        isValidChoice = false;
                                        printMenu();
                                        continue THIS;
                                    } else {
                                        System.out.println("\n\tInvalid Option.");
                                    }
                                }
                            } else {
                                PressAnyKey();
                            }
                        } while (true);
                    case 0:
                        isValidChoice = true;
                        try {
                            System.out.println("\n\t\tExiting the program now...");
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("\t\tSuccess! Program Ends.");
                        System.exit(0);
                    default:
                        System.out.println("\n\t\t\tInvalid Option. Please try again.");
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("\n\t\t\tInvalid input. Please enter an integer.");
                input.nextLine();
            }
        }
        input.close();
    }

    public static void printMenu() {
        System.out.println("------------------------------------------");
        System.out.println("\n\t\t\tStack Application \n\t\t\t\tConversion \n\t\t\t\t  Menu");
        System.out.println("\n\t\t\t[1] Infix to Postfix");
        System.out.println("\n\t\t\t[2] Infix to Prefix");
        System.out.println("\n\t\t\t[3] Postfix to Infix");
        System.out.println("\n\t\t\t[0] Stop");
        System.out.println("\n------------------------------------------");
    }


//    public static int getPrecedence(char character) {
//        return switch (character) {
//            case '+', '-' -> 1;
//            case '*', '/' -> 2;
//            case '^' -> 3;
//            default -> -1;
//        };
//    }

    public static int getPrecedence(char character) {
        int precedence;
        switch (character) {
            case '+':
            case '-':
                precedence = 1;
                break;
            case '*':
            case '/':
                precedence = 2;
                break;
            case '^':
                precedence = 3;
                break;
            default:
                precedence = -1;
        }
        return precedence;
    }

    public static boolean isOperand(char character) {
        return Character.isLetter(character);
    }

    public static boolean isOperator(char character) {
        return "+-*/^".contains(String.valueOf(character));
    }

    public static String infixToPostFix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (char character : infix.toCharArray()) {
            if (isOperand(character)) {
                postfix.append(character);
            } else if (isOperator(character)) {
                while (!stack.isEmpty() && getPrecedence(character) <= getPrecedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(character);
            } else if (character == '(') {
                stack.push(character);
            } else if (character == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return "";
                }
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    public static String infixToPrefix(String infix) {
        return reverseString(infixToPostFix(reverseString(infix)));
    }

    public static String postfixToInfix(String postfix) {
        Stack<String> stack = new Stack<>();
        for (char character : postfix.toCharArray()) {
            if (isOperand(character)) {
                stack.push(String.valueOf(character));
            } else if (isOperator(character)) {
                String operand2 = stack.pop();
                String operand1 = "";
                if (!stack.isEmpty()) {
                    operand1 = stack.pop();
                }
                stack.push("(" + operand1 + character + operand2 + ")");
            }
        }
        return stack.pop();
    }


    public static String reverseString(String any) {
        StringBuilder reversedString = new StringBuilder();
        for (int i = any.length() - 1; i >= 0; i--) {
            if (any.charAt(i) == '(') {
                reversedString.append(')');
            } else if (any.charAt(i) == ')') {
                reversedString.append('(');
            } else {
                reversedString.append(any.charAt(i));
            }
        }
        return reversedString.toString();
    }


    public static boolean isInfixValid(String infix) {
        Stack<Character> stack = new Stack<>();
        char[] expressionArray = infix.toCharArray();
        int operandCount = 0;
        int operatorCount = 0;

        for (char x : expressionArray) {
            if (Character.isDigit(x)) {
                System.out.println("\nInvalid Expression: Expression cannot contain digits");
                return false;
            } else if (isOperator(expressionArray[0])) {
                System.out.println("\nInvalid Expression: The Infix Expression should not begin with an Operator.");
                return false;
            } else if (isOperator(expressionArray[infix.length() - 1])) {
                System.out.println("\nInvalid Expression: The Infix Expression may only end with an Operand or Close Parentheses.");
                return false;
            } else if (Character.isLetter(x)) {
                operandCount++;
            } else if (isOperator(x)) {
                operatorCount++;
            } else if (x == '(') {
                stack.push(x);
            } else if (x == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    System.out.println("\nInvalid Expression: Unmatched Parentheses.");
                    return false;
                }
            } else if (infix.length() == 1 || infix.length() > 15) {
                System.out.println("\nInvalid Expression.");
                return false;
            }
        }

        if (operandCount <= operatorCount) {
            System.out.println("\nInvalid Expression: Operators are greater than Operands");
            return false;
        } else if (operandCount - operatorCount != 1) {
            System.out.println("\nInvalid Expression: Operands are greater than Operators");
            return false;
        }

        if (!stack.isEmpty()) {
            System.out.println("\nInvalid Expression: Unmatched Parentheses");
            return false;
        }

        return true;
    }


    public static void printResult(String title, String oldExp, String newExp) {
        System.out.println("\n\n-------------------------------------------");
        System.out.println("\t\t\t" + title);
        System.out.println("-------------------------------------------");
        System.out.println("\n  Infix Expression: " + oldExp + "\n");
        System.out.println("Postfix Expression: " + newExp + "\n");
        System.out.println("-------------------------------------------");
    }


    public static boolean isPostfixValid(String postfix) {
        char[] expressionArray = postfix.toCharArray();
        String[] parenthesis = {")", "("};
        int operandCount = 0;
        int operatorCount = 0;
        for (char x : postfix.toCharArray()) {
            if (Character.isDigit(x)) {
                System.out.println("\nInvalid Expression: Expression cannot contain digits");
                return false;
            } else if (isOperator(expressionArray[0])) {
                System.out.println("\nInvalid Expression: The Postfix Expression should not begin with an Operator.");
                return false;
            } else if (isOperand(expressionArray[postfix.length() - 1])) {
                System.out.println("\nInvalid Expression: The Postfix Expression may only end with an Operator.");
                return false;
            } else if (Arrays.asList(parenthesis).contains(String.valueOf(x))) {
                System.out.println("\nPostfix Expressions cannot contain parenthesis");
                return false;
            } else if (isOperand(x)) {
                operandCount++;
            } else if (isOperator(x)) {
                operatorCount++;
            }
        }
        if (operandCount <= operatorCount) {
            System.out.println("\nInvalid Expression: Operators are greater than Operands");
            return false;
        } else if (operandCount - operatorCount != 1) {
            System.out.println("\nInvalid Expression: Operands are greater than Operators");
            return false;
        }
        return true;
    }

    public static void PressAnyKey() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\nPress any key to continue...");
        try {
            input.readLine();
            System.out.println('\u000c');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}