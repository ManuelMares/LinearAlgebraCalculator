import java.util.Scanner;
import MainMenu.MainMenu;
import Methods.Matrix.MatrixMain;
import Shared.Validations;

public class LinearAlgebraCalculator{
   //IMPORTS
   MainMenu menu = new MainMenu();
   static Validations validator = new Validations();
   static Scanner scan = new Scanner (System.in);

   //VARIABLES
   static boolean method_SystemEquations;
   static boolean method_Matrix;
   static boolean method_Vectors;
   static int methodSelected;
   static String[] menuOptions = {"Work with System of Equations", "Work with Matrix", "Work with Lists of Vectors"};
   
   
   
   public static void main (String args[]){ 
      MatrixMain matrixMain = new MatrixMain();
        
      //PRESENTATION
      System.out.println("--------Welcome to the Linear Algebra Calculator V1.0--------");
      System.out.println("This is a personal project for a calculator made without any other math libraries besides 'Math' included in natively in J.");
      System.out.print("All the included solving methods have been coded specifically for this application and are not based in any other source of knowledge rather than the class");
      System.out.println(" of Linear Algebra - 2415 at NMSU \n\n");      
      System.out.println("To begin, please select one of the following methods.");

      //Prints menu and validate input
      methodSelected = validator.ShowAndValidate_Menu(menuOptions);
        
      
      switch(methodSelected){
         case 1:
            matrixMain.Start();
         break;
         case 2:
            matrixMain.Start();
         break;
         case 3:
            matrixMain.Start();
         break;
         default:          
            SelectMethod();
         break;
      }           
   }
   
   public static int SelectMethod() {
      boolean validOption = false;
      int option = -1;
      while(  validOption == false ){
         System.out.println("Work with System of Equations:       press 1");
         System.out.println("Work with Matrix:                    press 2");
         System.out.println("Work with Lists of Vectors:          press 3");
         option = scan.nextInt();
         if( option >= 1 && option <= 3 ){
            validOption = true;
         }else{
            System.out.println("\n\nInvalid option. Please select one option form the menu: ");
         }
      }
      return option;
   }      
      
      
        
   

       
}