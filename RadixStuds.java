
// GetStudents.java
// Andrew Davison, ad@fivedots.coe.psu.ac.th, Jan 2022


import java.io.*;
import java.util.*;


public class RadixStuds
{

  public static void main(String[] args)
  {
    // if (args.length != 1) {
    //   System.out.println("Usage: java GetStudents <fnm>");
    //   System.exit(1);
    // }

    ArrayList<String> lines = readLines("C:\\Users\\60101\\Desktop\\mydev\\java_algo\\students.txt");
    if (lines == null)
      return;

    long[] studs = new long[lines.size()];
    for (int i=0; i < studs.length; i++){
       studs[i] = getLong(lines.get(i));
    }
    System.out.println("No. of students IDs: " + studs.length);
    System.out.println(Arrays.toString(studs));
    // radixSort(studs); 
    // System.out.println("  Sorted array: " + Arrays.toString(studs));
  }  // end of main()



  private static ArrayList<String> readLines(String fnm)
  {
    try (BufferedReader br = new BufferedReader(new FileReader(fnm))) {
      ArrayList<String> lines = new ArrayList<>();
      String line;
      while ((line = br.readLine()) != null)
        lines.add(line);

      System.out.println("Read " + lines.size() + " lines from " + fnm);
      return lines;
    } 
    catch (IOException e) 
    { System.out.println("Could not read " + fnm);  }

    return null;
  } // end of readLines()



  private static long getLong(String s)
  {
    long val = -1L;
    try {
      val = Long.parseLong(s); 
    }
    catch (NumberFormatException e)
    {  System.out.println("Could not parse: \"" + s + "\"");  }

    return val;
  }  // end of getLong()

  private static void radixSort(int arr[]) 
  { 
    int max = findMax(arr); 
    int numDigits = (int) Math.log10(max) + 1; 
                             // valid only if max > 0
    int placeVal = 1;
    while (numDigits-- > 0) {
      countSort(arr, placeVal);
      placeVal *= 10;
    }
  } 



  private static int findMax(int arr[]) 
  // return maximum value in arr[] 
  { 
    int max = arr[0]; 
    for (int i = 1; i < arr.length; i++) 
      if (arr[i] > max) 
        max = arr[i]; 
    return max; 
  } 


  private static void countSort(int arr[], int placeVal) 
  // counting sort of arr[] according to placeVal
  { 
    int n = arr.length; 

    int count[] = new int[10]; // k range is 0..9
    Arrays.fill(count, 0); 

    // count the values in arr[]
    for (int i = 0; i < n; i++) {
      int pos = (arr[i]/placeVal)%10;
      count[pos]++; 
    }

    /* modify count[i] so that it contains the 
       sum of earlier counts, which will be equivalent
       to the position of i in the sorted array 
    */
    for (int i = 1; i < count.length; i++) 
      count[i] += count[i-1]; 

    // build the sorted array in reverse order
    // so sort is stable
    int sorted[] = new int[n];  
    for (int i = n-1; i >= 0; i--) { 
      int pos = (arr[i]/placeVal)%10;
      sorted[count[pos] - 1] = arr[i]; 
      count[pos]--; 
    } 

    // copy the sorted array back to arr[] 
    for (int i = 0; i < n; i++) 
      arr[i] = sorted[i]; 
  } // end of countSort()




}  // end of GetStudents class