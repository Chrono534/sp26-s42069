public class Planet
{

    private int x;
    private int y;
    private int mass;

    Planet(int x, int y, int mass)
    {
        this.x = x;
        this.y = y;
        this.mass = mass;
    }

    public double distance_to(Planet other)
    {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    public static int totalMass(Planet[] planets)
    {
        int total = 0;

        for (Planet currentPlanet : planets)
        {
            total += currentPlanet.mass;
        }
        return total;
    }


    /*
     ** Returns a list containing the common items of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2)
    {
        List<Integer> retList = new ArrayList<>();

        for (Integer elem : L1)
        {
            if (L2.contains(elem) && !retList.contains(elem))
            {
                retList.add(elem);
            }
        }
        return retList;
    }

    /**
     * Capitalizes all strings in the given list in place
     */
    public static void capitalize(List<String> L)
    {
        for (int i = 0; i < L.size(); i++)
        {
            String s = L.get(i).toUpperCase();
            L.set(i, s);
        }
    }

    /**
     * Returns a map from each integer x in the list to a list (without duplicates)
     * of all integers in the list that are less than x.
     */
    public static Map<Integer, List<Integer>> buildLessThanMap(List<Integer> L)
    {
        Map<Integer, List<Integer>> retMap = new HashMap<>();

        for (Integer x : L)
        {
            retMap.putIfAbsent(x, new ArrayList<Integer>());
            retMap.computeIfPresent(x, (k, v) ->
            {
                L.forEach(n ->
                {
                    if (x > n && !v.contains(n))
                    {
                        v.add(n);
                    }
                });
                return v;
            });
        }
        return retMap;
    }

    /**
     * Returns a map from each integer x in the list to a list (without duplicates)
     * of all integers in the list that are less than x.
     */
    public static Map<Integer, List<Integer>> buildLessThanMap(List<Integer> L)
    {
        Map<Integer, List<Integer>> retMap = new HashMap<>();

        for (Integer x : L)
        {
            if (retMap.containsKey(x))
            {
                continue;
            }

            retMap.put(x, new ArrayList<Integer>());
            List<Integer> AL = retMap.get(x);

            for (Integer y : L)
            {
                if (y < x && !AL.contains(y))
                {
                    AL.add(y);
                }
            }
        }

    }

    ;

    Fill in
    the function
    below,
    which takes
    in a
    list of
    integers and
    returns a new
    array containing
    only the
    positive
    integers from
    the original
    list .

    /**
     * Returns an array containing only the positive integers from the given list
     */
    public static int[] filterPositive(List<Integer> L)
    {
        List<Integer> retList = new ArrayList<>();

        for (Integer I : L)
        {
            if (I >= 0)
            {
                retList.add(I);
            }
        }

        int[] al = new int[retList.size()];
        for (int i = 0; i < retList.size(); i++)
        {
            al[i] = retList.get(i);
        }

        return al;
    }


    def build_less_than_map(L):
    result =

    {
    }
            for
    x in
    L:
        if
    x not
    in result:
    result[x]=[]
        for
    y in
    L:
        if y<x:
        if
    y not
    in result[
    x]:
    result[x].

    append(y)
            return result
                    ---
    example:
    L =[4,1,3,3]
    m =

    build_less_than_map(L)

    m is:

    {
        1: [],
        3: [1],
        4: [1, 3]}
#
    L is
    a list
    of strings
            for
    i in

    range(len(L)):
    s =L[i]
    L[i]=s.upper()

    rlist =[]
        for
    x in
    L1:
        if
    x in
    L2 and
    x not
    in rlist:
        rlist.append(x)
        return rlist
     */
             3

    List Exercises
            (a) The code reference below shows the equivalent Java code for
    common List
    operations.
            List<String> lst = new ArrayList<>();
lst.add("zero");
lst.add("one");
lst.set(0,"zed");
System.out.println(lst.get(0));
System.out.println(lst.size());
if(lst.contains("one"))

    {
        System.out.println("one in lst");
    }
for(
    String elem :lst)

    {
        System.out.println(elem);
    }

    lst =[]
        lst.append("zero")
        lst.append("one")
    lst[0]="zed"

    print(lst[0])

    print(len(lst))
        if"one"
    in lst:

    print("one in lst")
for
    elem in
    lst:

    print(elem)

    Fill in
    the method
    below which
    takes in
    two lists
    of integers
    and returns
    a new
    list containing
    the common
    items of
    the two
    given lists.
    Do not
    use the
    retainAll method.
/** Returns a list containing the common items of the two given lists
 public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
 rlist = []
 for x in L1:
 if x in L2 and x not in rlist:
 rlist.append(x)
 return rlist
 import math
 class Planet:
 def __init__(self, x, y, mass):
 self.x = x
 self.y = y
 self.mass = mass
 def distance_to(self, other):
 return math.sqrt(
 (other.x - self.x)**2 +
 (other.y - self.y)**2)
 @staticmethod def total_mass(planets):
 total = 0
 for p in planets:
 total += p.mass
 return total
 p1 = Planet(5, 10, 100)
 p2 = Planet(1, 2, 200)
 p1.distance_to(p2)
 Planet.total_mass([p1, p2])*/
}

void main()
{
    Planet p1 = new Planet(5, 10, 100);
    Planet p2 = new Planet(1, 2, 200);

    IO.println(p1.distance_to(p2));
    Planet[] planets = {p1, p2};
    IO.println(Planet.totalMass(planets));
}