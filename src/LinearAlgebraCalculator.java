import java.util.Scanner;

import Classes.Utilities.Inputs;
import Classes.Utilities.Printer;
import MainMenu.MainMenu;
import Methods.Matrix.MatrixMain;

public class LinearAlgebraCalculator{
   //IMPORTS
   MainMenu menu = new MainMenu();
   static Inputs input = new Inputs();
   static Scanner scan = new Scanner (System.in);
   static Printer printer = new Printer();

   //VARIABLES
   static boolean method_SystemEquations;
   static boolean method_Matrix;
   static boolean method_Vectors;
   static int methodSelected;
   static String[] menuOptions = {"Work with System of Equations", "Work with Matrix", "Work with Lists of Vectors"};
   
   
   
   public static void main (String args[]){ 
      MatrixMain matrixMain = new MatrixMain();
        
      //PRESENTATION
      printer.Title("Welcome to the Linear Algebra Calculator V1.0");
      System.out.println("This is a personal project for a calculator made without any other math libraries besides 'Math' included in natively in J.");
      System.out.print("All the included solving methods have been coded specifically for this application and are not based in any other source of knowledge rather than the class");
      System.out.println(" of Linear Algebra - 2415 at NMSU \n\n");      


      //Prints menu and validate 
      String title = "Calculator's menu";
      methodSelected = input.ShowAndValidate_Menu(menuOptions, title);
        
      
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
         break;
      }           
   }
   
}