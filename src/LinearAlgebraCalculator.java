import java.util.Scanner;
import Classes.Utilities.Inputs;
import Classes.Utilities.Printer;
import MainMenu.MainMenu;
import Methods.MatrixAlgebra.MatrixMultiplication;
import Methods.SolveMatrix.SolveMatrix_ReduceEchelon;

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
   static String[] menuOptions = {"Solve system", "Multiply Matrices", "Invert Matrix"};
   
   
   
   public static void main (String args[]){ 
        
      //PRESENTATION
      Printer.Title("Welcome to the Linear Algebra Calculator V1.0");
      System.out.println("This is a personal project for a calculator made without any other math libraries besides 'Math' included in natively in J.");
      System.out.print("All the included solving methods have been coded specifically for this application and are not based in any other source of knowledge rather than the class");
      System.out.println(" of Linear Algebra - 2415 at NMSU \n\n");      


      //Prints menu and validate 
      String title = "Calculator's menu";
      methodSelected = Inputs.ShowAndValidate_Menu(menuOptions, title);
        
      
      switch(methodSelected){
         case 1:
            SolveMatrix_ReduceEchelon solveMatrix_ReduceEchelon = new SolveMatrix_ReduceEchelon();
            solveMatrix_ReduceEchelon.Main();
         break;
         case 2:
            MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
            matrixMultiplication.Main();
         break;
         case 3:
            
         break;
         default:          
         break;
      }           
   }
   
}