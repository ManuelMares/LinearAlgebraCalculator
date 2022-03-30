package Classes.Matrix;
import Classes.Utilities.Vector;

import javax.swing.JLabel;

import GUI.Components.Containers.*;
import GUI.Components.Tables.MatrixUI;
import GUI.Components.Tables.PivotsUI;
import GUI.Components.Text.*;
import GUI.Controllers.InverseUI;
import Methods.Controller.GetMatrix;
import Classes.Utilities.Colors;
import Classes.Utilities.Printer;


public class Matrix_Inverse extends Matrix_ReduceEchelon{  
   private boolean hasInverse = true;

   public Matrix_Inverse(String name, double[][] values){
      super(name, values);
      //int[] size = {500,500};
      //UI.Set_Size(size);
   }
   



   public void      ReduceMatrix(){
      Delete_RepetedRows();
      Get_PivotsNoAugmented();
      AugmentMatrix_Identity();     
      hasInverse = Try_ReduceMatrix();   
        
      //MatrixUI matrixUI = new MatrixUI(matrix, "Reduced Matrix");
      //UI.Add_Component(matrixUI);   

      DecreaseMatrix_Identity();
   }
   

   private void   Get_PivotsNoAugmented(){
      Add_Column();
      Get_PivotsRecursion();
      Delete_Column(matrix[0].length - 1);

      //PivotsUI pivotsUI = new PivotsUI(pivots, "Pivots");
      //UI.Add_Component(pivotsUI);
   }

   private void   AugmentMatrix_Identity(){
      double[][] identity = GetMatrix.Identity(Get_NumRows());
      Annex_Matrix(identity);
      String message = "augmented matrix";
      Printer.Matrix(matrix, message);

      
      //MatrixUI matrixUI = new MatrixUI(matrix, message);
      //UI.Add_Component(matrixUI);
   }
   private void   DecreaseMatrixInverse(){
      int index = Get_SizeRows();
      for (; index <=( Get_SizeColumns() - 1) ; ) {
         Delete_Column(index);
      }
   }
   
   private void    DecreaseMatrix_Identity(){
      for (int counter = 0; counter < Get_SizeRows(); counter++) {
         Delete_Column(0);
      }
      
      MatrixUI matrixUI = new MatrixUI(matrix, "SOLUTION");
      reduceEchelonUI.Add_Component(matrixUI);
   }
   public void Annex_Matrix(double[][] identity){
      for(int indexColumn = 0;  indexColumn < identity[1].length; indexColumn++){
         double[] column = Vector.Get_Column(identity, indexColumn);
         Add_Column(column);
      }
   }
   public double[][] Get_Identity(){
      if(sizeMatrix[0] == sizeMatrix[1]){
         double[][] identity = new double[sizeMatrix[0]][sizeMatrix[0]];

         for (int index = 0; index < matrix.length; index++) {
            identity[index][index] = 1;
         }
         return identity;
      }
      return null;
   }

   public boolean IsValid(){
      return hasInverse;
   }



}