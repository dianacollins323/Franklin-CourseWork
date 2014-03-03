import java.io.*;
import java.util.Scanner;

/**
 * Write a description of class ReadFile here.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class ReadFile
{
    public static void main(String[] args)
    {
        try {
            ReadFile readNow = new ReadFile();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public ReadFile() throws FileNotFoundException
    {
        FileReader inFile = new FileReader("C:\\Documents and Settings\\David\\Desktop\\hw4Prob4.txt");
        Scanner readFile = new Scanner(inFile);
        String iniString = readFile.nextLine();
        String [] firstFrags = iniString.split(" ");
        int j = 0;
            
        try {
            while (j < firstFrags.length) {
                String [] secFrags = firstFrags[j].split("=");
                j++;
                for (int i = 0; i < secFrags.length; i++) {
                    System.out.print("Grade " + secFrags[i]);
                    i++;
                    System.out.println(" is " + secFrags[i]);
                }
            }
        }
        finally {
            readFile.close();
        }
    }
}
