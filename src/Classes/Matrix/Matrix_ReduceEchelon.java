package Classes.Matrix;

import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Classes.Pivot_Augmented;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;
import Classes.Utilities.Printer;
import Classes.Utilities.Vector;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Controllers.ReduceUI;


public class Matrix_ReduceEchelon extends Matrix{
   public    Pivots_Augmented   pivots;
   protected boolean            StepByStepStatus;
   private   ReduceUI           reduceEchelonUI;


   //CONSTRUCTORS---------------------------------------------
   public Matrix_ReduceEchelon(String name, double[][] values){
      super(name, values);
      StepByStepStatus  = true;
      pivots            = new Pivots_Augmented();
      reduceEchelonUI   = new ReduceUI();
   }   
   public SectionScrollUI Get_UI(){
      return reduceEchelonUI.Get_UI();
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
         return false;
      }
      else
         return true;
   }
   
   public  void     Delete_RepetedRows(){
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
         reduceEchelonUI.Delete_RepeatedRow(matrix);
      }
   }





   //REDUCE   
   public void    ReduceMatrix_AllPivots(){
      Delete_RepetedRows();
      boolean allPivotsReduced = Set_PivotsRecursion();
      if(!allPivotsReduced){
         reduceEchelonUI.Set_NewSolutionTry(true);         
         allPivotsReduced = Set_PivotsRecursion();
      }
      reduceEchelonUI.Set_NewSolutionTry(false);
      reduceEchelonUI.Print_Pivots(pivots, "Printing pivots");  
   }
   public boolean    Set_PivotsRecursion(){
      pivots = pivots.Get_PivotsRecursion_Augmented(Get_CopyMatrix()); 
      reduceEchelonUI.Print_Pivots(pivots, "Printing pivots");
      boolean reduccion = Try_ReduceMatrix();            
      reduceEchelonUI.Print_Matrix(matrix, "Reduced Matrix");
      return reduccion;
   }


   public void    Get_PivotsRecursion(){
      pivots = pivots.Get_PivotsRecursion_Augmented(Get_CopyMatrix());
      
   }

   protected boolean    Try_ReduceMatrix(){
      for (int index = 0; index < pivots.Get_Size(); index++) {
         Pivot_Augmented newPivot = pivots.Get_Pivot(index);
         if(!newPivot.Get_IsFree()){
            if(!Try_Reduce_Column(index))
               return false;
         }
      }
      return true;
   }
   protected boolean    Try_Reduce_Column(int indexVar){
      Pivot_Augmented pivot = pivots.Get_Pivot(indexVar);
      double coeficient = pivot.Get_Coeficient();
      if( coeficient != 0){
         ClearColumn_Pivot(pivot);
         pivots.Update_Pivots(matrix);
         //reduceEchelonUI.Print_Matrix(matrix, "Reduced Matrix");
         return true;
      }else{            
         reduceEchelonUI.Print_PivotHasTurnIntoZero(indexVar + 1);
         return false;
      }
   }
   public    double[]   Get_UnitarianRow(int[] pivotPosition){
      double pivotValue = GetElement(pivotPosition);
      double[] pivotRow = Get_Row(pivotPosition[0]);
      double[] unitarianRow = Vector.ByScalar(pivotRow, (1/pivotValue));

      return unitarianRow;
   }
   public    void       ClearColumn_Pivot(Pivot_Augmented pivot) {
      if(!pivot.Get_IsFree() && pivot.Get_Coeficient() != 0){         
         int[] position = pivot.Get_Position();
         double[] unitarianRow = Get_UnitarianRow(position);
         
         reduceEchelonUI.Print_Step_ClearColumn(pivot, unitarianRow);
         Set_RowToArray(position[0], unitarianRow);         
         ClearColumn(unitarianRow, position);
      }else{
         System.out.println("Error: The parameter indicated is exceeds the amount of variables in the matrix");
         System.out.println("In method ClearColumn");
      }
   }
   protected void       ClearColumn(double[] unitarianRow, int[] pivotPosition){
      //Loops through each row and cancels element in the same column with the unitarian row, except itself
      int counter = 1;
      for (int indexRow = 0; indexRow < sizeMatrix[0]; indexRow++) {
         if( indexRow != pivotPosition[0] && matrix[indexRow][pivotPosition[1]] != 0){
            if(counter == 1)
               reduceEchelonUI.Print_RowOperationsWillBeExecuted();

            int[] positionToCancel = {indexRow, pivotPosition[1]};
            double factorToCancel = (-1) * GetElement(positionToCancel);  
            reduceEchelonUI.Print_RowOperation(counter, (positionToCancel[0]+1), (pivotPosition[0]+1), factorToCancel);

            double[] arrayToAdd = Vector.ByScalar(unitarianRow, factorToCancel);         
            ModifyMatrix_AddVectorTo_Row(indexRow, arrayToAdd);
            counter++;
            
            reduceEchelonUI.Print_Matrix(matrix, "Reduced Matrix");
         }         
      }
      if(counter == 1)
         reduceEchelonUI.Print_NoRowOperationsExecuted();
   } 






















   
   
   








}