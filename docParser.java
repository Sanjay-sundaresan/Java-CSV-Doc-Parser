//Sanjay Sundaresan - 19BPS1113
import java.util.*;
import java.io.*; 
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
class docParser{
    public static void main(String[] args){
         System.out.println("Sanjay Sundaresan 19BPS1113 - Digital Assignment 2 - CSV/DOC Parser");
         int c = 0;
         System.out.println("1. Read .txt file.");
         System.out.println("2. Read doc file.");
         System.out.print("Choose an option: ");
         Scanner scan = new Scanner(System.in);
         c = scan.nextInt();
         switch(c){
             case 1:
                File obj = new File("/home/sanjay/JAVA/THEORY/DA 2/test.txt");
                Scanner line = null;
                int wc = 0, lc = 0; //Initializing wordcount and linecount to 0
                try{
                    line = new Scanner(obj);
                    line.useDelimiter("\n"); //Reading a line of the doc
                    while(line.hasNext()){
                        Scanner word = new Scanner(line.nextLine()); //Reading in every word in the line 
                        lc++;
                        while(word.hasNext()){
                            String w = word.next();
                            wc++;
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace(); 
                }
                System.out.println("The number of lines = " + lc);
                System.out.println("The number of words = " + wc);
                break;
            
            case 2:
                //File dobj = new File("/home/sanjay/JAVA/THEORY/DA 2/test.doc");
                String dobj = "/home/sanjay/JAVA/THEORY/DA 2/test.doc";
                Path path = Paths.get(dobj);
                try{
                    BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
                    StringBuffer buffer = new StringBuffer();
                    int ch = 0;
                    while((ch = reader.read())!=-1) {
                        buffer.append((char)ch+reader.readLine());
                    }
                    System.out.println("Contents of the file: "+buffer.toString()); //Checking if the file can be read 
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
         }
    }
}