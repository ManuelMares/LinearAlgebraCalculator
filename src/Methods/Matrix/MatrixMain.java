package Methods.Matrix;
import java.util.Scanner;

import Classes.Utilities.Inputs;
import Classes.Utilities.Printer;
import Methods.Matrix.SolveMatrix.SolveMatrix_ReduceEchelon;


public class MatrixMain{     
   //imports
   static Scanner scan = new Scanner (System.in);
   static Inputs input = new Inputs();
   static Printer printer = new Printer();
   static SolveMatrix_ReduceEchelon solveMatrix_ReduceEchelon = new SolveMatrix_ReduceEchelon();
   
   //variables
   String[] menuOptions = {"Matrix algebra", "Solve Matrix"};
   
   public void Start(){
      solveMatrix_ReduceEchelon.Main();
   }   
   
   
}