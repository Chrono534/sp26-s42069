public class StarTriangle5 {
   /**
     * Prints a right-aligned triangle of stars ('*') with 5 lines.
     * The first row contains 1 star, the second 2 stars, and so on. 
     */
   public static void starTriangle5() {
      // TODO: Fill in this function

      int i = 0;
      while ( i < 5 )
      {
         IO.println(" ".repeat(5 - i) + "*".repeat(i + 1));
         i++;
      }

   }
   
   public static void main(String[] args) {
      starTriangle5();
   }
}