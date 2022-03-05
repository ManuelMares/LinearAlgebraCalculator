package Classes.Matrix;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Pivot;
import Classes.Matrix.Pivots.Pivots;
//import Inputs; You don't need to import files in the same folder
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Vector;
import Classes.Utilities.Inputs;


public class Matrix_Reduce extends Matrix{      
   static Inputs     input     = new Inputs();
   static Recursion  recursion = new Recursion();
   static Printer    printer   = new Printer();
   static Vector     vector    = new Vector();

   public    Pivots     pivots;
   protected boolean    StepByStepStatus;


   //CONSTRUCTORS---------------------------------------------
   public Matrix_Reduce(String name, double[][] values){
      super(name, values);
      StepByStepStatus  = true;
      pivots            = new Pivots();
   }
   
   
   //PROPERTIES
   public void    Set_StepByStep(boolean status){
      StepByStepStatus = status;
   }
   public boolean Get_SteptByStep(){
      return StepByStepStatus;
   }
   public int     Check_Consistentsy(){
      int indexInconsistent = -1;
      isConsistent = true;
      for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
         if(!rowIsConsistent(indexRow)){
            indexInconsistent = indexRow;
            isConsistent = false;
         }

      }
      return indexInconsistent;
   }
   public boolean rowIsConsistent(int index){
      //two conditions for inconsistency: all var coeficients are zero, and the decimal result is not
      boolean coeficientsAreZero = true;
      boolean resultIsZero = true;

      double[] row = Get_Row(index);
      for (int indexElement = 0; indexElement < row.length; indexElement++) {
         if(indexElement == (row.length -1)){
            if(row[indexElement] != 0)
               resultIsZero = false;
         }else{
            if(row[indexElement] != 0)
               coeficientsAreZero =false;
         }
      }

      if( coeficientsAreZero && !resultIsZero){
         Printer.Subtitle("Conclusion"); 
         String message ="The identity matrix is";
         Printer.Matrix(matrix, message);
         Printer.Title("End of the prorgam");

         return false;
      }
      else
         return true;
   }
   public  void     Delete_RepetedRows(){
      int[] rowsToDelete = new int[0];
      for (int indexRow_Base = 0; indexRow_Base < sizeMatrix[0]; indexRow_Base++) {
         for (int indexRow_ToCompare = (indexRow_Base + 1); indexRow_ToCompare < sizeMatrix[0]; indexRow_ToCompare++) {
            if(vector.AreEqual(Get_Row(indexRow_Base), Get_Row(indexRow_ToCompare))){
               rowsToDelete = new int[rowsToDelete.length + 1];
               rowsToDelete[rowsToDelete.length - 1] = indexRow_ToCompare;
            }
         }
      }
      if(rowsToDelete.length > 0){
         Set_RowsToValue(rowsToDelete, 0.0);
         Printer.Subtitle2("Repeted rows");
         String message = "Repeted rows have been detected. \n The matrix's repeted rows have been set to zero.\nFinal Matrix";
         Printer.Matrix(matrix, message);
      }
   }

   //REDUCE   
   public void      ReduceMatrix_AllPivots(){
      boolean allPivotsReduced = true;
      Delete_RepetedRows();
      Get_PivotsRecursion();     
      allPivotsReduced = Try_ReduceMatrix();

      if(!allPivotsReduced){

         allPivotsReduced = true;
         String message = "\n======================== Extra steps ========================\nThe position of at least one pivot has changed. A new iteration of solutions needs to be executed.\n\n";
         System.out.print(StepByStepStatus ? message : "");
         Get_PivotsRecursion(); 
         Printer.Pivots1(pivots);     
         allPivotsReduced = Try_ReduceMatrix();
      }

   }
   public void      Get_PivotsRecursion(){
      pivots = pivots.Get_PivotsRecursion(Get_CopyMatrix());   
      Printer.Pivots1(pivots);   
   }

   protected boolean  Try_ReduceMatrix(){
      for (int index = 0; index < pivots.Get_Size(); index++) {
         Pivot newPivot = pivots.Get_Pivot(index);
         if(!newPivot.Get_IsFree()){
            if(!Try_Reduce_Column(index))
               return false;
         }
      }
      return true;
   }
   protected boolean  Try_Reduce_Column(int indexVar){
      boolean columnReduced =true;
      Pivot pivot = pivots.Get_Pivot(indexVar);
      double coeficient = pivot.Get_Coeficient();
      if( coeficient != 0){
         ClearColumn_Pivot(pivot);
         pivots.Update_Pivots(matrix);
      }else{            
         String isZero = "Pivot "+(indexVar + 1) + " has turn into a zero, so no operation is executed in this step \n";
         System.out.print(StepByStepStatus ? isZero : "");
         columnReduced = false;
      }
      return columnReduced;
   }
   public  double[]  Get_UnitarianRow(int[] pivotPosition){
      double pivotValue = GetElement(pivotPosition);
      double[] pivotRow = Get_Row(pivotPosition[0]);
      double[] unitarianRow = vector.ByScalar(pivotRow, (1/pivotValue));

      return unitarianRow;
   }
   public  void      ClearColumn_Pivot(Pivot pivot) {
      if(!pivot.Get_IsFree() && pivot.Get_Coeficient() != 0){
         int[] position = pivot.Get_Position();
         Printer.Subtitle2("Step " + pivot.Get_Name().charAt(1));         
         Printer.Pivot(pivot);
         String messageMatrix = "Dividing Row" + (position[0] + 1)+ " by " + pivot.Get_Coeficient() + " to get the unitarian row, the new matrix is:";
         
         double[] unitarianRow = Get_UnitarianRow(position);
         Set_RowToArray(position[0], unitarianRow);
         
         Printer.Matrix(matrix, messageMatrix);
         System.out.println("\nNow this unitarian row will be use to clean the pivot's column. The operations are:");
         ClearColumn(unitarianRow, position);
         
         Printer.Matrix(matrix, "The resulting matrix in step " + pivot.Get_Name().charAt(1) + " is:");
      }else{
         System.out.println("Error: The parameter indicated is exceeds the amount of variables in the matrix");
         System.out.println("In method ClearColumn");
      }
   }
   protected void    ClearColumn(double[] unitarianRow, int[] pivotPosition){
      //Loops through each row and cancels element in the same column with the unitarian row, except itself
      int counter = 1;
      for (int indexRow = pivotPosition[1]; indexRow < sizeMatrix[0]; indexRow++) {
         if( indexRow != pivotPosition[0] && matrix[indexRow][pivotPosition[1]] != 0){
            int[] positionToCancel = {indexRow, pivotPosition[1]};
            double factorToCancel = (-1) * GetElement(positionToCancel);
            String message = String.format("%d)R%d = R%d + (%.2f)*R%d.\n", counter, (positionToCancel[0]+1), (positionToCancel[0]+1), factorToCancel, (pivotPosition[0]+1));
            System.out.print(StepByStepStatus ? message : "");
            double[] arrayToAdd = vector.ByScalar(unitarianRow, factorToCancel);         
            ModifyMatrix_AddVectorTo_Row(indexRow, arrayToAdd);
            counter++;
         }
         
      }
   } 






















   
   
   








}