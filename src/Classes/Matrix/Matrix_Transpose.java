package Classes.Matrix;

import Classes.Matrix.AbstractClasses.Matrix;
//import Inputs; You don't need to import files in the same folder
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Inputs;
import Classes.Utilities.Vector;


public class Matrix_Transpose extends Matrix{      
   private Matrix_Simple transposeMatrix;

   //CONSTRUCTOR---------------------------------------------
   public Matrix_Transpose(String name, double[][] values){
      super(name, values);
      int[] sizeTranspose = {values[0].length, values.length};
      transposeMatrix = new Matrix_Simple("Transpose", sizeTranspose);
   }

   public void Set_Transpose(){
      for (int index = 0; index < Get_SizeRows(); index++) {
         double[] row = Get_Row(index);
         transposeMatrix.Set_ColumnToArray(index, row);
      }      
   }

   public double[][] Get_Transpose(){
      return transposeMatrix.Get_CopyMatrix();
   }

   public Matrix_Simple Get_TransposeMatrix(){
      return transposeMatrix;
   }

}