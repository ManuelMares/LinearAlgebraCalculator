package Methods;
import Classes.Utilities.Printer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Utilities.Colors;
import Classes.Utilities.Inputs;
import GUI.Components.ButtonUI;
import GUI.Components.Containers.*;
import GUI.Components.Text.TextUI;
import GUI.Components.Text.TitleUI;
import GUI.Inputs.Input_BiggerThan;
import GUI.Inputs.Input_Matrix;
import Methods.Controller.CreateMatrix;
import Methods.Controller.GetMatrix;
import Methods.Controller.GetSize;
import Methods.Controller.Options;
import Methods.Inverse.InverseMatrix;
import Methods.LuFactorization.LuFactorization;
import Methods.MatrixAlgebra.MatrixMultiplication;
import Methods.SolveMatrix.SolveMatrix_Reduce;
import Methods.SolveMatrix.SolveMatrix_ReduceEchelon;
import Methods.Transpose.Transpose;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;


public class Main {
   MainFrame frame;
   double[][] Matrix;
   double[][] Matrix2;
   CreateMatrix createMatrix;
   CreateMatrix createMatrix2;
   //VARIABLES
   boolean method_SystemEquations;
   boolean method_Matrix;
   boolean method_Vectors;
   int methodSelected;
   String[] menuOptions = {"Reduce Echelon", "Reduce", "Multiply Matrices", "Invert Matrix", "Factorization", "Transpose"};
   
    
    
   public Main (){ 
      frame =new MainFrame();      
      Set_Buttons();   
      frame.Set_Visible();          
   }  
   
   public void Set_Buttons(){      
      Consumer<Integer> tmp = option -> Get_Matrix(option);
      Options options = new Options(tmp);
      frame.Add_Component(options.Get_UI());
   }

   public void Get_Matrix(int option){
      frame.Clean_Frame();


      methodSelected = option + 1;
      Consumer<Integer> tmp = num -> Set_Matrix();
      createMatrix = new CreateMatrix(tmp, "Matrix A");
      Consumer<Integer> tmp2 = num -> Reset();
      ButtonUI button = new ButtonUI("Main menu ", tmp2, 0);
      frame.Add_Component(createMatrix.Get_Component());
      frame.Add_Component(button.Get_UI());
   }

   private void Start_Method() {
      Consumer<Integer> tmp2 = num -> Reset();
      ButtonUI button = new ButtonUI("Return ", tmp2, 0);
      switch(methodSelected){
         case 1:
            SolveMatrix_ReduceEchelon solveMatrix_ReduceEchelon = new SolveMatrix_ReduceEchelon();
            solveMatrix_ReduceEchelon.Main(Matrix);
            frame.Add_Component(solveMatrix_ReduceEchelon.Get_UI());
         break;
         case 2:
            SolveMatrix_Reduce solveMatrix_Reduce = new SolveMatrix_Reduce();
            solveMatrix_Reduce.Main(Matrix);
            frame.Add_Component(solveMatrix_Reduce.Get_UI());
         break;
         case 3:
            MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
            matrixMultiplication.Main(Matrix, Matrix2);
            frame.Add_Component(matrixMultiplication.Get_UI());
         break;
         case 4:
            InverseMatrix inverseMatrix = new InverseMatrix();
            inverseMatrix.Main(Matrix);
            frame.Add_Component(inverseMatrix.Get_UI());

         break;
         case 5:
            LuFactorization luFactorization = new LuFactorization();
            luFactorization.Main(Matrix);
            frame.Add_Component(luFactorization.Get_UI());
         break;
         case 6:
            Transpose transpose = new Transpose();
            transpose.Main();
         break;
         default:          
         break;
      } 
      
      frame.Add_Component(button.Get_UI());
   }

   public void Set_Matrix(){
      Matrix = createMatrix.Get_Matrix(); 
      frame.Clean_Frame(); 
      if(methodSelected == 3){
         Set_SecondM();
      } else{
         Start_Method();  
      }
   }

   public void Set_SecondM(){
      frame.Clean_Frame();
      Consumer<Integer> tmp = num -> Set_Matrix2();
      createMatrix2 = new CreateMatrix(tmp, "Matrix B");
      Consumer<Integer> tmp2 = num -> Reset();
      ButtonUI button = new ButtonUI("Main menu ", tmp2, 0);
      frame.Add_Component(createMatrix2.Get_Component());
      frame.Add_Component(button.Get_UI());
   }
   public void Set_Matrix2(){
      Matrix2 = createMatrix2.Get_Matrix(); 
      frame.Clean_Frame();  
      Start_Method();  
   }

   public void Reset(){
      System.out.println("Success");
      frame.Clean_Frame();    
      Set_Buttons();   
      frame.Set_Visible();  
   }

 
}
