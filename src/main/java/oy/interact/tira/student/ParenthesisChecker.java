package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * TODO: Student: Implement this method which checks if the given string has matching opening and closing
    * parentheses. It should check for matching parentheses:

    *   Lorem ipsum ( dolor sit {  amet, [ consectetur adipiscing ] elit, sed } do eiusmod tempor ) incididunt ut...,
    * 
    * that can be found for example in Java source code and JSON structures.
    * 
    * If the string has issues with parentheses, you should throw a {@code ParenthesisException} with a
    * descriptive message and error code. Error codes are already defined for you in the ParenthesesException
    * class to be used.
    * 
    * NOTE: If the string contains quotation marks ("like this"), the text between quotation marks 
    * MUST be ignored. Why? In structured text, the rule of the parentheses applies only to the structured
    * text but not the text in quotation marks. It is totally valid to have JSON:
    * 
    * {
    *    "somekey": "Some value [ with that opening bracket only" 
    * }
    *
    * and that is perfectly ok and acceptable, also in source code like Java.
    *
    * Note that the exception thrown must include correct line and column numbers of the
    * place where it became obvious that there are incorrect parenthesis in the input text.
    *
    * What is to be tested about the incoming string:
    *
    * - If a quotation mark was found, all characters until the next quotation mark must be ignored.
    *   And obviously, before and after, all charactes must be checked if they are parenthesis chars.
    * - When an opening parenthesis is found in the string, it is successfully pushed to the stack (push may fail).
    * - When a closing parenthesis is found in the string, chech that a matching opening parenthesis is popped from the stack.
    * - If the stack was empty, this indicates an error, too many opening parentheses (or too few closing ones).
    *   Note that you can check if the stack is empty before calling pop() and throw an exception.
    * - When the string has been handled, and if the stack still has parentheses, there are too few closing parentheses.
    * 
    * @param stack The stack object used in checking the parentheses from the string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both opening and closing).
    * @throws ParenthesesException if the parentheses did not match as intended.
    * @throws StackAllocationException If the stack cannot be allocated or reallocated if necessary.
    */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      
      int line = 1;
      int column = 1;
      boolean isQuote = false;
      int count = 0;

      for(int index = 0; index < fromString.length(); index++) {
         char currentCharacter = fromString.charAt(index);

         if(currentCharacter == '\n') {
            line++;
            column = 1;
         }

         if(currentCharacter == '"') {
            isQuote = !isQuote;
         }

         if(isQuote == false) {
         

            if(currentCharacter == '[' || currentCharacter == '(' || currentCharacter == '{') {
               stack.push(currentCharacter);
               count++;
            }



            if(currentCharacter == ']' || currentCharacter == '}' || currentCharacter == ')') {
               count++;
               if(stack.isEmpty()) {
                  throw new ParenthesesException("Too many closing parentheses", line, column, ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
               }

               char popped = stack.pop();
               switch(currentCharacter) {
                  case ']': {
                     if(popped == '{' || popped == '(') {
                        throw new ParenthesesException("Parentheses didn't match.", line, column, ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                     }
                     break;
                  }

                  case '}': {
                     if(popped == '[' || popped == '(') {
                        throw new ParenthesesException("Parentheses didn't match.", line, column, ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                     }
                     break;
                  }

                  case ')': {
                     if(popped == '[' || popped == '{') {
                        throw new ParenthesesException("Parentheses didn't match.", line, column, ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                     }
                     break;
                  }
               }
            }
         }
         
         if(currentCharacter != '\n') {
            column++;
         }
      }

      if(!stack.isEmpty()) {
         throw new ParenthesesException("Too many opening parenthesis.", line, column, ParenthesesException.TOO_MANY_OPENING_PARENTHESES);
      }
      
      return count;
   }
}
