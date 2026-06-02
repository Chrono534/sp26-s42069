import edu.princeton.cs.algs4.StdIn;

/**
 * Simple Arithmetic Class.
 *
 * @author Josh Hug
 *
 */
public class Arithmetic
{

    /**
     * Computes product of two ints.
     *
     * @param a Value 1
     * @param b Value 2
     * @return Product of a and b
     *
     */
    public static int product(int a, int b)
    {
        return a * b;
    }

    /**
     * Computes sum of two ints (incorrectly).
     *
     * @param a Value 1
     * @param b Value 2
     * @return Sum of a and b
     *
     */
    public static int sum(int a, int b)
    {
        return a + b;
    }

    public static int maxMinDiff(List<Integer> L)
    {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int current = 0;

        for ( int i = 0; i < L.size(); i++ )
        {
            current = L.get(i);
            if ( current < min )
                min = current;
            if ( current > max )
                max = current;
        }

        return max - min;
    }
    public static Map<String, List<String>> listOfFollowers(List<String> followers)
    {
        Map<String, List<String>> returnMap = new HashMap<>();

        for ( int i = 0; i + 1 <= followers.size(); i++ ) {
            String current = followers.get(i);
            String next = followers.get(i + 1);
            if ( !returnMap.containsKey(current) )
                returnMap.put(current, new ArrayList<>());

            returnMap.get(current).add(next);
        }

        return returnMap;
    }

}

    public static void main(String[] args) {
        System.out.println("Give me a number! (no decimals, please)");
        int num1 = StdIn.readInt();
        System.out.println("Give me another number! (still no decimals)");
        int num2 = StdIn.readInt();

        System.out.println("The product of " + num1 + " and " + num2 + " is: " + Arithmetic.product(num1, num2));
        System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum(num1, num2));
        IO.println("tset");
    }



