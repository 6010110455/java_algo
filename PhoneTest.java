
// PhoneTest.java
// Andrew Davison, ad@fivedots.coe.psu.ac.th, Jan. 2022


/* Generate NUM_PHONES PhoneNum objects, repeating the test NUM_TESTS
   times, and record the number of collisions by PhoneNum's hashCode()
   function.

   Supply a hash constant as a command line argument, but this is not
   currently used by PhoneNUm, which uses its own HASH_CONSTANT value.

  ---------------
  Usage:

  > javac PhoneTest.java
  
  > java PhoneTest 17
    Hash constant: 17
    
    Average No. of collisions for 10000 phones after 10 runs: 48.7
    Collision%: 0.49%
*/


import java.util.*;



public class PhoneTest
{
  private static final int NUM_TESTS = 10;
  private static final int NUM_PHONES = 10000;

  private static Random rand = new Random();



  public static void main(String[] args)
  {
    if (args.length != 1) {
      System.out.println("Usage: java PhoneTest <hash constant>");
      System.exit(1);
    }

    int hashCon = Integer.parseInt(args[0]);  // not currently used
    System.out.println("Hash constant: " + hashCon);
  
    int collsTotal = 0;
    for (int i=0; i < NUM_TESTS; i++)
      collsTotal += runTest(i);

    double collAverage = (double)collsTotal/NUM_TESTS;
    System.out.println("\nAverage No. of collisions for " + NUM_PHONES + 
                       " phones after " + NUM_TESTS + " runs: " + collAverage);
    System.out.printf("Collision%%: %.2f%%\n", (collAverage * 100.0)/NUM_PHONES);
  }  // end of main()



  private static int runTest(int testNo)
  {
    ArrayList<Integer> hashes = new ArrayList<>();

    PhoneNum pn;
    int numCollisions = 0;
    for (int i=0; i < NUM_PHONES; i++) {
      pn = new PhoneNum( randInt(1000), randInt(1000), randInt(10000));
      int hashVal = pn.hashCode();
      if (hashes.contains(hashVal))
        numCollisions++;
      else
       hashes.add(hashVal);
    }
    // System.out.println("Test No.: " + testNo);
    // System.out.println("  No. of collisions: " + numCollisions);
    return numCollisions;
  }  // end of runTest()



  private static int randInt(int max)
  {  return randInt(0, max);  }


  private static int randInt(int min, int max)
  // a random int in the range [min, max):
  {  
    if (min > max) {
      int tmp = min;
      min = max;
      max = min;
    }
    return (rand.nextInt(max - min) + min);
  }

}