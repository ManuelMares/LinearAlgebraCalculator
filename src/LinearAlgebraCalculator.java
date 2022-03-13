import java.util.Scanner;
import Classes.Utilities.Inputs;
import Classes.Utilities.Printer;
import Methods.Inverse.InverseMatrix;
import Methods.MatrixAlgebra.MatrixMultiplication;
import Methods.SolveMatrix.SolveMatrix_Reduce;
import Methods.SolveMatrix.SolveMatrix_ReduceEchelon;
import Methods.Transpose.Transpose;
import Methods.LuFactorization.LuFactorization;

public class LinearAlgebraCalculator{
   //IMPORTS
   static Inputs input = new Inputs();
   static Scanner scan = new Scanner (System.in);
   static Printer printer = new Printer();

   //VARIABLES
   static boolean method_SystemEquations;
   static boolean method_Matrix;
   static boolean method_Vectors;
   static int methodSelected;
   static String[] menuOptions = {"Reduce Echelon", "Reduce", "Multiply Matrices", "Invert Matrix", "Factorization", "Transpose"};
   
   
   
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
            SolveMatrix_Reduce solveMatrix_Reduce = new SolveMatrix_Reduce();
            solveMatrix_Reduce.Main();
         break;
         case 3:
            MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
            matrixMultiplication.Main();
         break;
         case 4:
            InverseMatrix inverseMatrix = new InverseMatrix();
            inverseMatrix.Main();
         break;
         case 5:
            LuFactorization luFactorization = new LuFactorization();
            luFactorization.Main();
         break;
         case 6:
            Transpose transpose = new Transpose();
            transpose.Main();
         break;
         default:          
         break;
      }           
   }
   
}