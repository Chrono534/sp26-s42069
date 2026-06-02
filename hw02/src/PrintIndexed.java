public class PrintIndexed {
   /**
     * Prints each character of a given string followed by the reverse of its index.
     * Example: printIndexed("hello") -> h4e3l2l1o0
     */
   public static void printIndexed(String s) {
      // TODO: Fill in this function
      int length = s.length() - 1;
      for ( int i = length; i >= 0; i--)
      {
         char current_char = s.charAt(length - i);
         IO.print(String.valueOf(current_char) + i);
      }
      IO.println("");
   }

   public static void main(String[] args) {
      printIndexed("hello");
      printIndexed("cat"); // should print c2a1t0
   }
}