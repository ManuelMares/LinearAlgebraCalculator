package Classes;
import java.util.Scanner;


public class Validations{  
   static Scanner scan = new Scanner (System.in);
   
   
   
   
   public int ShowAndValidate_Menu(String[] options){
      if(options.length > 0){
         boolean validOption = false;
         int selection = -1;
         while(  validOption == false ){
            //Prints menu and validates entrance
            selection = validateInput_isInt(options);

            //Validates that it is a valid option
            if( selection >= 1 && selection <= options.length ){
               validOption = true;
            }else{
               System.out.println("\n\nInvalid option. Please select one option form the menu: ");
            }
         }
         return selection; 
      } else{
            System.out.println("This menu does not have any option available yet. Please contact support");   
            return -1;   
      } 
   }
   
   public void listMenu(String[] options){
      int indexMenu = 0;
      for(String option : options){               
         System.out.println((indexMenu+1) + "    " + option);
         indexMenu++;
      }
   }
   
   
   
   
   
   
   
   //INPUTS VALUE
   public static int validateInput_Positive(String message){
      boolean valid = false;
      int input = -1;
      while(!valid){
         System.out.println(message);
         input = scan.nextInt();
         if(input > 0){
            valid = true;
         }else{
            System.out.println("\nError: input must be bigger than 0.");
         }
      }
      return input;  
   }
   
   
   public int validateInput_BiggerThan(String message, int minValue){
      boolean valid = false;
      int input = -1;
      while(!valid){
         System.out.println(message);
         input = scan.nextInt();
         if(input > minValue){
            valid = true;
         }else{
            System.out.println("\nError: input must be bigger than " + minValue + ".");
         }
      }
      return input;  
   }
   

   public int validateInput_isInt(String[] options){
      //print menu and validates input
      int input = -1;
      boolean isNumeric = false;
      while( !isNumeric ){
         listMenu(options);
         if( scan.hasNextInt() ){
            input = scan.nextInt();
            isNumeric = true;
         } else {
            System.out.println("\n\nInvalid option. Please indicate one index from the menu ");
            isNumeric = false;
            scan.next();//deletes the memory of what there is in the input. Otherwise it would loop infinitely
         }
      }
      return input;
   }
   
   public int validateInput_isInt(){
      //print menu and validates input
      int input = -1;
      boolean isNumeric = false;
      while( !isNumeric ){
         if( scan.hasNextInt() ){
            input = scan.nextInt();
            isNumeric = true;
         } else {
            System.out.println("\n\nInvalid option. Please indicate one index from the menu ");
            isNumeric = false;
            scan.next();//deletes the memory of what there is in the input. Otherwise it would loop infinitely
         }
      }
      return input;
   }

   
   public double validateInput_isDouble(String message){
      double input = -1;
      boolean isdouble = false;
      System.out.println(message);
      while( !isdouble ){
         if( scan.hasNextDouble() ){
            input = scan.nextDouble();
            isdouble = true;
         } else {
            System.out.println("\n\nThe las input was not a valid decimal value. Please try with a different value.");
            isdouble = false; //for some reason, code crashes without this.
            scan.next();//deletes the memory of what there is in the input. Otherwise it would loop infinitely
         }
      }
      return input;
   }

   public double validateInput_isDouble(){
      double input = -1;
      boolean isdouble = false;
      while( !isdouble ){
         if( scan.hasNextDouble() ){
            input = scan.nextDouble();
            isdouble = true;
         } else {
            System.out.println("\n\nThe las input was not a valid decimal value. Please try with a different value.");
            isdouble = false; //for some reason, code crashes without this.
            scan.next();//deletes the memory of what there is in the input. Otherwise it would loop infinitely
         }
      }
      return input;
   }
   
   
   public boolean validateInput_YesNo(){
      String input = "";
      boolean isValid = false;
      boolean answerYesNo = false;
      while( !isValid ){
         input = scan.next();
         if( input.length() >= 1 && 
            (input.charAt(0) == 'Y' || input.charAt(0) == 'y' )){
            isValid = true;
            answerYesNo = true;
         } else if( input.length() >= 1 && 
            (input.charAt(0) == 'N'  || input.charAt(0) == 'n' )){
            isValid = true;
            answerYesNo = false;
         }else {
            System.out.println("\n\nPlease indicate [Y]/[y] to agree or [N]/[n] to disagree. You can also type [yes] or [no].");
            isValid = false; //for some reason, code crashes without this.
         }
      }
      return answerYesNo;
   }


   public int Array_IndexOf(int[] array, int value){
      int index = -1;
      boolean isFound = false;
      int counter = 0;
      while( !isFound ){
         if( array[counter] == value ){
            index = counter;
            isFound =true;
         }
         if(counter == (array.length -1)){
            isFound = true;
         }
         counter++;
      }
      return index;
   }
   
   public int Array_IndexOf(double[] array, double value){
      int index = -1;
      boolean isFound = false;
      int counter = 0;
      while( !isFound ){
         if( array[counter] == value ){
            index = counter;
            isFound =true;
         }
         if(counter == (array.length -1)){
            isFound = true;
         }
         counter++;
      }
      return index;
   }

}