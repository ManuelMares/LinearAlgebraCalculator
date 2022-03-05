package Classes.Matrix.Pivots;
import Classes.Matrix.Matrix_Simple;
import Classes.Matrix.AbstractClasses.Matrix;
//import Inputs; You don't need to import files in the same folder
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Vector;
import Classes.Utilities.Inputs;


public class Get_PivotsRecursion extends Get_Pivots{
   static Recursion recursion = new Recursion();
   static int[]     matrixOrder = new int[SizeRows];

    
   public static Pivots Main(double[][] matrix, boolean stepByStep){
      Reset_Pivots();
      Set_Variables(matrix, stepByStep);      
      Set_PivotsRecursion();
      Adjust_PivotsOrder();
      
      return Pivots;
   }
   
   private static void Adjust_PivotsOrder(){
      Pivot tempPivot;
      System.out.println("\n");
      for (int index = 0; index < Pivots.Get_Size(); index++) {
         tempPivot = Adjust_PivotRow(Pivots.Get_Pivot(index));
         Pivots.Set_Pivot(tempPivot, index); 
      }
   }
   private static Pivot Adjust_PivotRow(Pivot pivot){
      Pivot tmpPivot = pivot;
      if(!pivot.Get_IsFree()){
         int[] position = tmpPivot.Get_Position();
         position[0] = matrixOrder[position[0]];
         tmpPivot.Set_Position(position);
      }
      return tmpPivot;
   }

   public static void    Set_PivotsRecursion(){      
      boolean allPivotsFound = TrySet_AllBasicPivots();
      Matrix_Simple MatrixCopy = new Matrix_Simple("copy", Matrix.Get_CopyMatrix());
      int counter = 0;
      if(!allPivotsFound){
         int[][] arrayPermutations = recursion.Get_ArrayOfPermutations(SizeRows);
         for (int[] array : arrayPermutations){
            counter++;
            System.out.println("NEW CYCLE " +counter);
            Matrix.Set_MatrixToMatrix( MatrixCopy.Get_CopyMatrix());
            Matrix.ReaArrangeMatrix_GivenOrder(array);            
            allPivotsFound = TrySet_AllBasicPivots();

            if(allPivotsFound){
               break;            
            }
            matrixOrder = Vector.CopyVector(array);
         }
      }       
   }
   public static boolean TrySet_AllBasicPivots(){      
      Set_Pivots();
      if(Pivots.Count_BasicVariables() == AmountBasicVariables)
         return true;
      return false;
   }



}