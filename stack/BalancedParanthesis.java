import java.util.*;
import java.io.*;

class BalancedParanthesis {
  public static void main(String[] args) {
    String s = "(a + b)*[c  + (d * (e * f))]";

    System.out.println(checkBalanced(s) ? "Balanced" : "Not Balanced");
  }

  public static boolean isPair(char a, char b) {
    if (a == '(' && b == ')') return true;
    if (a == '[' && b == ']') return true;
    if (a == '{' && b == '}') return true;
    return false;
  }

  public static boolean checkBalanced(String s){
    char[] schar = s.toCharArray();
    Stack<Character> stack = new Stack<Character>();

    /** You can use this when you have just Paranthesis in the string
    /* if (s.length() % 2 != 0) {
    /*   return false;
    /*}
    */

    for (int i = 0; i < s.length(); i++) {
      if (schar[i] == '(' || schar[i] == '[' || schar[i] == '{') {
        stack.push(schar[i]);
      } else if (schar[i] == ')' || schar[i] == ']' || schar[i] == '}'){
        if (stack.empty() || !isPair(stack.pop(), schar[i])) {
          return false;
        }
      }
    }
    if (stack.empty()) {
      return true;
    } else {
      return false;
    }
  }

}
