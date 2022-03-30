package Classes.Matrix;

import Classes.Utilities.Printer;
import Classes.Utilities.Vector;

import javax.swing.plaf.multi.MultiButtonUI;

import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Classes.Pivot_Augmented;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;

import GUI.Components.Containers.*;
import GUI.Controllers.MultiplicationUI;
import GUI.Controllers.ReduceEchelonUI;
import GUI.Controllers.ReduceUI;

public class Matrix_Multiplication extends Matrix{
   public double[][]          matrix2;
   public double[][]          ResultMatrix;
   public  Pivots_Augmented   pivots;
   private boolean            Compatibility;
   private MultiplicationUI   UI;

   public Matrix_Multiplication(double[][] m1, double[][] m2){
      super("matrix1", m1);
      matrix2           = m2;
      SetResultMatrixToZero();

      Compatibility     = true;
      UI                = new MultiplicationUI();
      pivots            = new Pivots_Augmented();
   }

   public SectionScrollUI Get_UI(){
      return UI.Get_UI();
   }
   

   public void SetResultMatrixToZero(){
      ResultMatrix = new double[matrix[0].length][matrix2.length];
   }
   public boolean CheckCompatibility(){
      if(matrix[0].length != matrix2.length)
         Compatibility = false;
         System.out.println("Compatibility: " + Compatibility);
      return Compatibility;
   }

   public void    Multiply_Matrices(){
      for (int index_m1Row = 0; index_m1Row < matrix.length; index_m1Row++) {
          for (int index_m2Column = 0; index_m2Column < matrix[0].length; index_m2Column++){
            UI.Print_Step(index_m1Row, index_m2Column);
            SetMatrixResultPosition(index_m1Row, index_m2Column);
          }
      }
   }
   
   public void    SetMatrixResultPosition(int index_m1Row, int index_m2Column){        
      double[] m1_Row = matrix[index_m1Row];
      double[] m2_Column = Vector.Get_Column(matrix2, index_m2Column);    
      double result = Vector.ByVector(m1_Row, m2_Column);

      ResultMatrix[index_m1Row][index_m2Column] = result;
   }

   













}