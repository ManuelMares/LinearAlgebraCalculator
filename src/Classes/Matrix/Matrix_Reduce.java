package Classes.Matrix;

import Classes.Utilities.Printer;
import Classes.Utilities.Vector;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Classes.Pivot_Augmented;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;

import GUI.Components.Containers.*;
import GUI.Controllers.ReduceUI;

public class Matrix_Reduce extends Matrix{
   public  Pivots_Augmented   pivots;
   private boolean            StepByStepStatus;
   private ReduceUI           reduceUI;

   public Matrix_Reduce(String name, double[][] values){
      super(name, values);
      reduceUI          = new ReduceUI();
      StepByStepStatus  = true;
      pivots            = new Pivots_Augmented();
   }
   public SectionScrollUI Get_UI(){
      return reduceUI.Get_UI();
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

      if( coeficientsAreZero && !resultIsZero)
         return false;
      else
         return true;

   }
   
   public void     Delete_RepetedRows(){
      int[] rowsToDelete = new int[0];
      for (int indexRow_Base = 0; indexRow_Base < sizeMatrix[0]; indexRow_Base++) {
         for (int indexRow_ToCompare = (indexRow_Base + 1); indexRow_ToCompare < sizeMatrix[0]; indexRow_ToCompare++) {
            if(Vector.AreEqual(Get_Row(indexRow_Base), Get_Row(indexRow_ToCompare))){
               rowsToDelete = new int[rowsToDelete.length + 1];
               rowsToDelete[rowsToDelete.length - 1] = indexRow_ToCompare;
            }
         }
      }
      if(rowsToDelete.length > 0){
         Set_RowsToValue(rowsToDelete, 0.0);
         reduceUI.Delete_RepeatedRow(matrix);
      }
   }



   //REDUCE   
   public void       ReduceMatrix_AllPivots(){
      Delete_RepetedRows();
      boolean allPivotsReduced = Set_PivotsRecursion();
      if(!allPivotsReduced){         
         reduceUI.Set_NewSolutionTry(true);         
         allPivotsReduced = Set_PivotsRecursion();
      }
      reduceUI.Set_NewSolutionTry(false);
      reduceUI.Print_Pivots(pivots, "Printing pivots");
   }
   public boolean    Set_PivotsRecursion(){
      pivots = pivots.Get_PivotsRecursion_Augmented(Get_CopyMatrix());
      reduceUI.Print_Pivots(pivots, "Printing pivots");
      boolean reduccion = Try_ReduceMatrix();            
      reduceUI.Print_Matrix(matrix, "Reduced Matrix");
      return reduccion;
   }
      

   private boolean   Try_ReduceMatrix(){
      for (int index = 0; index < pivots.Get_Size(); index++) {
         Pivot_Augmented newPivot = pivots.Get_Pivot(index);
         if(!newPivot.Get_IsFree()){
            if(!Try_Reduce_Column(index))
               return false;
         }
      }
      return true;
   }
   private boolean   Try_Reduce_Column(int indexVar){
      Pivot_Augmented pivot = pivots.Get_Pivot(indexVar);
      double coeficient = pivot.Get_Coeficient();
      if( coeficient != 0){
         ClearColumn_Pivot(pivot);
         pivots.Update_Pivots(matrix);
         return true;
      }else{
         reduceUI.Print_PivotHasTurnIntoZero(indexVar + 1);
         return false;
      }
   }
   private void      ClearColumn_Pivot(Pivot_Augmented pivot) {
      try {
         int[] position = pivot.Get_Position();
         double[] unitarianRow = Get_UnitarianRow(position);
         
         reduceUI.Print_Step_ClearColumn(pivot, unitarianRow);
         Set_RowToArray(position[0], unitarianRow);         
         ClearColumn(unitarianRow, position);
      }catch (Exception e) {
         System.out.println(e);
         System.out.printf("Wrong pivot: %s, %s\n", pivot.Get_Name(), pivot.Get_IsFree() ? "Free": "Basic");
      }
   }
   private void      ClearColumn(double[] unitarianRow, int[] pivotPosition){
      //Loops through each row and cancels element in the same column with the unitarian row, except itself
      int step = 1;
      for (int indexRow = pivotPosition[1]; indexRow < sizeMatrix[0]; indexRow++) {
         if( indexRow != pivotPosition[0] && matrix[indexRow][pivotPosition[1]] != 0){
            if(step == 1)
               reduceUI.Print_RowOperationsWillBeExecuted();

            int[] positionToCancel = {indexRow, pivotPosition[1]};
            double factorToCancel = (-1) * GetElement(positionToCancel);  
            reduceUI.Print_RowOperation(step, (positionToCancel[0]+1), (pivotPosition[0]+1), factorToCancel);

            double[] arrayToAdd = Vector.ByScalar(unitarianRow, factorToCancel);         
            ModifyMatrix_AddVectorTo_Row(indexRow, arrayToAdd);
            step++;
            
            reduceUI.Print_Matrix(matrix, "Reduced Matrix");
         }
         
      }
      if(step == 1)
         reduceUI.Print_NoRowOperationsExecuted();
   } 






















   
   
   








}