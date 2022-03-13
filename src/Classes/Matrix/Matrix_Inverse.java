package Classes.Matrix;
import Classes.Utilities.Vector;
import Methods.CreateMatrix.CreateMatrix;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Classes.Pivot_Augmented;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;
//import Inputs; You don't need to import files in the same folder
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Vector;


public class Matrix_Inverse extends Matrix_ReduceEchelon{    
   private boolean hasInverse = true;

   //CONSTRUCTORS---------------------------------------------
   public Matrix_Inverse(String name, double[][] values){
      super(name, values);
   }
   


   public void      ReduceMatrix(){
      Delete_RepetedRows();
      Get_PivotsNoAugmented();
      AugmentMatrix_Identity();     
      hasInverse = Try_ReduceMatrix();      
      DecreaseMatrix_Identity();
   }

   private void   Get_PivotsNoAugmented(){
      Add_Column();
      Get_PivotsRecursion();
      Delete_Column(matrix[0].length - 1);
   }
   private void   AugmentMatrix_Identity(){
      double[][] identity = CreateMatrix.Identity(Get_NumRows());
      Annex_Matrix(identity);
      String message = "augmented matrix";
      Printer.Matrix(matrix, message);
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