package Classes.Utilities;
import java.util.InputMismatchException;
import java.util.Scanner;

import Classes.Exceptions.InvalidAnswerException;
import Classes.Exceptions.InvalidNumberException;


public class Inputs{  
   static Printer printer = new Printer();
   static Scanner scan = new Scanner (System.in);   
   
   //VALIDATE MENU
   public int     ShowAndValidate_Menu(String[] options, String message){
      printer.Subtitle2(message);
      int selection = -1;
      try{
         if(options.length > 0){
            DisplayMenuOptions(options);
            String instruction = "Please select an option from the menu: ";
            selection = InRange(instruction, 0, options.length);
         }
      }catch(NullPointerException ex){
         System.out.printf("Exception: %s\n",ex);
      }      
      return selection; 
   }
   public void    DisplayMenuOptions(String[] options){
      int indexMenu = 0;
      for(String option : options){               
         System.out.printf("%d)     %s\n",(indexMenu+1), option);
         indexMenu++;
      }
      System.out.print("\n");
   }
   
     
   
   //INPUTS VALUE
   public double  InRange(String message, double minValue, double maxValue){
      boolean valid = false;
      double input = -1;
      while(!valid){
         try{
            System.out.println(message);
            input = scan.nextInt();
            if(input > maxValue  ||  input < minValue){
               throw new InvalidNumberException("The number has to be bigger than " + minValue + " and smaller than" + maxValue);
            }
            valid = true;
         }catch(InvalidNumberException ex){
            System.out.printf("Exception: %s\n", ex);
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be an integer.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return input;  
   }
   public int     InRange(String message, int minValue, int maxValue){
      boolean valid = false;
      int input = -1;
      while(!valid){
         try{
            System.out.println(message);
            input = scan.nextInt();
            if(input > maxValue  ||  input < minValue){
               throw new InvalidNumberException("The number has to be bigger than " + minValue + " and smaller than" + maxValue);
            }
            valid = true;
         }catch(InvalidNumberException ex){
            System.out.printf("Exception: %s\n", ex);
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be an integer.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return input;  
   }
   public double  Positive(){
      double doubleNum = 0;
      double input = BiggerThan("Please input a positive double", doubleNum);
      return input;  
   }
   public int     Positive(String message){
      int input = BiggerThan(message, 0);
      return input;  
   }
   public double  BiggerThan(String message, double minValue){
      boolean valid = false;
      double input = -1;
      while(!valid){
         try{
            System.out.println(message);
            input = scan.nextInt();
            if(input < minValue){
               throw new InvalidNumberException("The number has to be bigger than " + minValue);
            }
            valid = true;
         }catch(InvalidNumberException ex){
            System.out.printf("Exception: %s\n", ex);
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be an integer.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return input;  
   }
   public int     BiggerThan(String message, int minValue){
      boolean valid = false;
      int input = -1;
      while(!valid){
         try{
            System.out.println(message);
            input = scan.nextInt();
            if(input < minValue){
               throw new InvalidNumberException("The number has to be bigger than " + minValue);
            }
            valid = true;
         }catch(InvalidNumberException ex){
            System.out.printf("Exception: %s\n", ex);
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be an integer.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return input;  
   }
   

   //INPUTS TYPE
   public double  IsDouble(String message){
      boolean valid = false;
      double input = -1;
      while(!valid){
         try{
            System.out.println(message);
            input = scan.nextDouble();
            valid = true;
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be a double.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return input;  
   }
   public String  IsString(String message){
      boolean valid = false;
      String input = "";
      while(!valid){
         try{
            System.out.println(message);
            input = scan.nextLine();
            valid = true;
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be a double.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return input;  
   }
   public int     IsInt(String message){
      boolean valid = false;
      int input = 0;
      while(!valid){
         try{
            System.out.println(message);
            input = scan.nextInt();
            valid = true;
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be an integer.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return input;  
   }
   public boolean YesNo(String message){
      boolean answer = false;
      boolean valid = false;
      String input = "";
      while(!valid){
         try{
            System.out.print(message);
            input = scan.next().toLowerCase();
            if(input.charAt(0) == 'y'){
               return true;
            }
            if(input.charAt(0) == 'n'){
               return false;
            }
            throw new InvalidAnswerException("The answer options are {Yes, yes, Y, y, or No, no, N, n}");
         }catch(InvalidAnswerException ex){
            System.out.printf("Exception: %s\n", ex);
         }catch(InputMismatchException ex){
            System.out.printf("Exception: %s\nThe input must be a word.\n", ex);
            //This statement is necesary to clean the input
            scan.next();
         }
      }
      return answer;  
   }

}