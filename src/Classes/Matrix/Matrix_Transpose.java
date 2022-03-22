package Classes.Matrix;
import Classes.Matrix.AbstractClasses.Matrix;
import GUI.Controllers.TransposeUI;


public class Matrix_Transpose extends Matrix{      
   private Matrix_Simple transposeMatrix;
   private TransposeUI           transposeUI;

   //CONSTRUCTOR---------------------------------------------
   public Matrix_Transpose(String name, double[][] values){
      super(name, values);
      int[] sizeTranspose = {values[0].length, values.length};
      transposeMatrix = new Matrix_Simple("Transpose", sizeTranspose);
      transposeUI          = new TransposeUI();
   }
   
   public void Set_Transpose(){
      for (int index = 0; index < Get_SizeRows(); index++) {
         double[] row = Get_Row(index);
         transposeMatrix.Set_ColumnToArray(index, row);
      }      
      transposeUI.Print_Matrix(transposeMatrix.Get_CopyMatrix(), "Transposed matrix");
   }

   public double[][] Get_Transpose(){
      return transposeMatrix.Get_CopyMatrix();
   }

   public Matrix_Simple Get_TransposeMatrix(){
      return transposeMatrix;
   }

}