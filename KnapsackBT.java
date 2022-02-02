
// KnapsackBT.java
// Andrew Davison, ad@fivedots.coe.psu.ac.th, Jan 2022

/* The 0-1 knapsack problem involves a set of items, each with a weight and a cost. 

   The task is to determine which items to include in the 'knapsack' so that their 
   total weight is less than or equal to a given maximum, and the total cost of the
   items is as large as possible.

   https://en.wikipedia.org/wiki/Knapsack_problem


   This is a BACKTRACKING solution to the 0-1 knapsack problem; 
   it does **NOT** use Dynamic Programming.

   Usage:

      > javac KnapsackBT.java
      
      > java KnapsackBT

      Max allowed weight: 11.0
      Weights: [1, 2, 5, 6, 7]
      Costs: [1, 6, 18, 22, 28]
      
      Best cost combination: 40
        Item 2; w: 5; c: 18
        Item 3; w: 6; c: 22
      Total weight: 11
      Elapsed time: 0.01 secs
*/


import java.util.*;



public class KnapsackBT
{
  private static double maxWeight = 11;   // Maximum allowed weight
  private static int[] w = {1,2,5,6,7};       // The weights of the items
  private static int[] c = {1,6,18,22,28};    // The costs of the items

  // stores final results
  private static boolean[] bestItems = new boolean[w.length];
  private static int bestCost = 0;  



  public static void main(String[] args)
  {
    // starting conditions
    System.out.println("Max allowed weight: " + maxWeight);
    System.out.println("Weights: " + Arrays.toString(w));
    System.out.println("Costs: " + Arrays.toString(c));

    boolean[] useItems = new  boolean[w.length];  // all false

    long start = System.currentTimeMillis();

    search(0, useItems);

    //  after the search, report the results
    System.out.println("\nBest cost combination: " + bestCost);
    int totWeight = 0;
    for(int i=0; i < w.length; i++) {
      if (bestItems[i]) {
        System.out.println("  Item " + i + "; w: " + w[i] + "; c: " + c[i]);
        totWeight += w[i];
      }
    }
    System.out.println("Total weight: " + totWeight);

    long now = System.currentTimeMillis();
    System.out.println("Elapsed time: " + ((now - start) / 1000.0) + " secs");

  }  // end of main()



  private static void search(int i, boolean[] useItems)
  {
    if (i == w.length)
      checkItems(useItems);
    else {   // try two possibilities
      useItems[i] = true;
      search(i+1, useItems);
      useItems[i] = false;    // order means that ith item choice is false at end
      search(i+1, useItems);  // of else block
    }
  } /* Search */



  private static void checkItems(boolean[] useItems)
  {
    // calculate total weight and cost of used items
    int totWeight = 0;
    int totCost = 0;
    for(int i=0; i < w.length; i++) {
      if (useItems[i]) {
        totCost += c[i];
        totWeight += w[i];
      }
    }

    if (totWeight > maxWeight)  // too heavy
      return;

    if (totCost > bestCost) {   // store results globally
      bestCost = totCost;
      for(int i=0; i < w.length; i++)
         bestItems[i] = useItems[i];
    }
  } /* checkItems */


}  // end of KnapsackBT class