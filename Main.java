import java.io.*;
public class Main{
  public static void main(String [ ] args){
    try{
      XMLFileManager program = new XMLFileManager(args[0]);
      program.executeProgram();
    }catch(Exception e){
      System.out.println("missing input file");
    }
  }
}
