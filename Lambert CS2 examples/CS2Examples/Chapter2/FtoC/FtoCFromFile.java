import java.io.*;
public class FtoCFromFile{
   public static void main (String[] args){
      double fahrenheit;
      double celsius;
      try{
         FileInputStream stream = new FileInputStream("in.dat");
         InputStreamReader reader = new InputStreamReader(stream);
         BufferedReader buffer = new BufferedReader(reader);

         FileOutputStream fileOutputStream = new FileOutputStream("out.dat");
         PrintWriter printWriter = new PrintWriter(fileOutputStream, true);
   
         fahrenheit = Double.parseDouble(buffer.readLine());
         celsius = (fahrenheit - 32.0) * 5.0 / 9.0;

         printWriter.println(fahrenheit + " degrees Fahrenheit equals " +  
                             celsius + " degrees Celsius.");

         buffer.close();
         printWriter.close();

      }catch(Exception e){
         System.err.println("Input error -- " + e.toString());
      }
   }
}