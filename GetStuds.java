
// GetStudents.java
// Andrew Davison, ad@fivedots.coe.psu.ac.th, Jan 2022


import java.io.*;
import java.util.*;


public class GetStuds
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
    for (int i=0; i < studs.length; i++)
       studs[i] = getLong(lines.get(i));

    System.out.println("No. of students IDs: " + studs.length);
    System.out.println(Arrays.toString(studs));
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
      System.out.println("Could not parse: " + s + " : " + val);
    }
    catch (NumberFormatException e)
    {  System.out.println("Could not parse: \"" + s + "\"");  }

    return val;
  }  // end of getLong()



}  // end of GetStudents class