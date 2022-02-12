package Methods.Matrix;
import java.util.Scanner;

import Classes.Validations;
import Methods.Matrix.SolveMatrix.SolveMatrix_ReduceEchelon;


public class MatrixMain{     
   //imports
   static Scanner scan = new Scanner (System.in);
   static Validations validator = new Validations();
   static SolveMatrix_ReduceEchelon solveMatrix_ReduceEchelon = new SolveMatrix_ReduceEchelon();
   
   //variables
   String[] menuOptions = {"Matrix algebra", "Solve Matrix"};
   public static void main (String args[]){ }
   
   public void Start(){
      int MethodSelected = -1;
   
      System.out.println("\n\nWelcome to method of Matrix. \nPlease select what you want to do with the matrix method");
      MethodSelected = validator.ShowAndValidate_Menu(menuOptions);
      
      switch(MethodSelected){
         case 1:
            solveMatrix_ReduceEchelon.Main();
         break;
         case 2:
            solveMatrix_ReduceEchelon.Main();
         break;
         case 3:
         default:          
         break;
      } 
   }   
   
   
   public static int SelectMethod() {
      boolean validOption = false;
      int option = -1;
      while(  validOption == false ){
         System.out.println("Matrix Algebra:       press 1");
         System.out.println("Solve Matrix:         press 2");
         option = scan.nextInt();
         if( option >= 1 && option <= 2 ){
            validOption = true;
         }else{
            System.out.println("\n\nInvalid option. Please select one option form the menu: ");
         }
      }
      return option;
   }      
      
      
   public static void MetodoPrueba(){
      System.out.print("It's a working!");
   }
   
}