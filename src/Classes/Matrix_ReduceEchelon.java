package Classes;
import Classes.AbstractClasses.Matrix;
//import Inputs; You don't need to import files in the same folder
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Inputs;


public class Matrix_ReduceEchelon extends Matrix{      
   static Inputs input = new Inputs();
   static Recursion recursion = new Recursion();
   static Printer printer = new Printer();
   static Vector vector = new Vector();

   private Pivots     pivots;
   private boolean    StepByStepStatus;


   //CONSTRUCTORS---------------------------------------------
   public Matrix_ReduceEchelon(String name, double[][] values){
      super(name);
      StepByStepStatus = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      Set_MatrixToMatrix(values);
   }
   public Matrix_ReduceEchelon(String name, int[] size, double value){
      super(name);
      StepByStepStatus = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size[0]][size[1]];
      Set_MatrixToValue(value);
   }
   public Matrix_ReduceEchelon(String name, int size, double value){
      super(name);
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size][size];
      Set_MatrixToValue(value);
   }
   public Matrix_ReduceEchelon(String name, int[] size){
      super(name);
      StepByStepStatus = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size[0]][size[1]];
   }
   public Matrix_ReduceEchelon(String name, int size){
      super(name);
      StepByStepStatus = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size][size];
   }
   public Matrix_ReduceEchelon(String name){
      super(name);
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = null;
      StepByStepStatus = true;
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
   public boolean IsLinearlyIndependent(){
      //A system is lineraly independet if it does not have free variables
      boolean isLI = true;
      for (int index = 0; index < pivots.size; index++) {
         if(pivots.GetPivot_isFree(index))
            return !isLI;
      }
      return isLI;
   }


   //PIVOTS
   public void    Get_PivotsRecursion(){      
      //This method will get the pivots. If it is not possible to find all of the index, it will randomly rearrage it and try again.


      //There will be as many variables as columns. Not every columns and row needs to have a pivot. NOT EVERY ROW HAS A VAR
      //To get them, we will get as many vars as (columns - 1)
      // there will be as many Basic variables as the minimum between rows and (columns - 1)
      //The rest will be free variable all the way up to the max between rows and (colums - 1)
      int amountVariables = (sizeMatrix[1] -1);
      int amountBasicVariables = (sizeMatrix[0] < amountVariables) ? sizeMatrix[0] : amountVariables;

      pivots.SetPivotsToZero();
      int pivotsFound = Set_AvailablePivots(amountVariables);
      Set_FreeVariables(amountVariables);

      //While we cannot find all the pivotes, or there have been 10 tries already
      int[][] arrayPermutations = recursion.Get_ArrayOfPermutations(sizeMatrix[0]);
      int numberTries = 0;
      for (int[] array : arrayPermutations) {
         pivots.SetPivotsToZero();
         ReaArrangeMatrix_GivenOrder(array);

         pivotsFound = Set_AvailablePivots(amountVariables);
         Set_FreeVariables(amountVariables);
         numberTries++;
         if(pivotsFound == amountBasicVariables)
            break;
      }

      if(numberTries > 0){
         
         printer.Subtitle2("Permutations");
         String message = "The matrix's row order has been permutated in order to try to find all available pivots\n";
         printer.Matrix(matrix, message);
      }
      
   }
   public int     Set_AvailablePivots(int amountVariables){
      int pivotsFound = 0;
      for (int indexColumn = 0; indexColumn < (sizeMatrix[1] - 1); indexColumn++) {
         //if the pivot was already added
         if(GetPivot_Column(indexColumn)){
            pivotsFound++;
         }else{
            pivots.AddFreeVariable( "X" + (pivots.size + 1) );            
         }
      }      

      return pivotsFound;
   }
   public void    Set_FreeVariables(int amountVariables){
      if(pivots.size < amountVariables){
         for (int indexFreeVar = pivots.size; indexFreeVar < amountVariables; indexFreeVar++) {
            pivots.AddFreeVariable( "X" + (pivots.size + 1) );  
         }
      }
   }
   public int     Get_AmountPivots(){
      return pivots.size;
   }
   public void    Print_PivotsCompleteStatus(){
      pivots.PrintPivots_CompleteStatus();
   }
   public Pivots  Get_Pivot(int indexPivot){
      Pivots newPivot = pivots.GetPivot(indexPivot);
      return newPivot;
   }
   

   //UNITATIAN ROW
   public  double[] Get_UnitarianRow(int[] pivotPosition){
      double pivotValue = GetElement(pivotPosition);
      double[] pivotRow = Get_Row(pivotPosition[0]);
      double[] unitarianRow = vector.ByScalar(pivotRow, (1/pivotValue));

      return unitarianRow;
   }
   public  void     ClearColumn_Pivot(String pivotName) {
      if(pivotName != null ){
         int[] pivotPosition = pivots.GetPivot_Position(pivotName);
         double pivotValue = GetElement(pivotPosition);
         if(pivotValue != 0){
            String messageMatrix = "Dividing Row" + (pivotPosition[0] + 1)+ " by " + pivotValue + " to get the unitarian row, the new matrix is:";
            double[] unitarianRow = Get_UnitarianRow(pivotPosition);
            Set_RowToArray(pivotPosition[0], unitarianRow);
            if(StepByStepStatus){
               printer.Matrix(matrix, messageMatrix);
               System.out.println("\nNow this unitarian row will be use to clean the pivot's column. The operations are:");
            }
            ClearColumn(unitarianRow, pivotPosition);
            if(StepByStepStatus)
               printer.Matrix(matrix, "The resulting matrix in step " + pivotName.charAt(1) + " is:");
         }
      }else{
         System.out.println("Error: The parameter indicated is exceeds the amount of variables in the matrix");
         System.out.println("In method ClearColumn");
      }
   }
   private void     ClearColumn(double[] unitarianRow, int[] pivotPosition){
      //Loops through each row and cancels element in the same column with the unitarian row, except itself
      int counter = 1;
      for (int indexRow = 0; indexRow < sizeMatrix[0]; indexRow++) {
         if( indexRow != pivotPosition[0] && matrix[indexRow][pivotPosition[1]] != 0){
            int[] positionToCancel = {indexRow, pivotPosition[1]};
            double factorToCancel = (-1) * GetElement(positionToCancel);
            String message = String.format("%d)R%d*(%.2f) added to R%d.\n", counter, (pivotPosition[0]+1), factorToCancel, (positionToCancel[0]+1));
            System.out.print(StepByStepStatus ? message : "");
            double[] arrayToAdd = vector.ByScalar(unitarianRow, factorToCancel);         
            ModifyMatrix_AddVectorTo_Row(indexRow, arrayToAdd);
            counter++;
         }
         
      }
   } 
   private boolean  GetPivot_Column(int indexColumn) {
      if(indexColumn >= 0 && indexColumn <= sizeMatrix[1]){
         // gets the column as an array. Then loops through it looking for the first non-zero value.
         //With such index, it checks if it is already listed as a pivot. If so, it check next number. If not, it stops.
         boolean hasPivot = false;
         double[] column = Get_Column(indexColumn);
         int indexRow = 0;
         while (!hasPivot && indexRow < column.length) {
            //if the value is not zero and its index (row) is not listed yet
            if( column[indexRow] != 0  && !pivots.Validate_RowHasPivot(indexRow)){
               int[] position = {indexRow, indexColumn};
               double pivotValue = column[indexRow];
               String pivotName = "X" + (pivots.size + 1);
               double pivotResult = matrix[indexRow][sizeMatrix[1] - 1]; 
               pivots.AddPivot(pivotName, position, pivotValue, pivotResult, false);
               hasPivot = true;
               return hasPivot;
            }
            indexRow++;
         }
         return hasPivot;

      }else{
         System.out.println("Error: the index of the column does not exist in the matrix: " + indexColumn + ". Amoun of columns: "+ sizeMatrix[1]);
         System.out.println("in method GetPivot_Column");
      }
      return false;

   }


   //GET VALUES
   public  void     Set_PivotsResults(){
      double[] newResults = new double[pivots.size];
      for (int index = 0; pivots.size < newResults.length; index++) {
         int[] position = pivots.GetPivot_Position(index);
         double result = matrix[position[0]][sizeMatrix[1] - 1];
         pivots.setResult(position[0], result);
      }
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
         printer.Subtitle2("Repeted rows");
         String message = "Repeted rows have been detected. \n The matrix's repeted rows have been set to zero.\nFinal Matrix";
         printer.Matrix(matrix, message);
      }
   }





   //REDUCE   
   public void      ReduceMatrix_AllPivots(){
      boolean allPivotsReduced = true;
      Delete_RepetedRows();
      Get_PivotsRecursion();      
      allPivotsReduced = ReduceMatrix_OnlyNonZeroPivots(allPivotsReduced);

      while(!allPivotsReduced){
         allPivotsReduced = true;
         String message = "\n======================== Extra steps ========================\nThe position of at least one pivot has changed. A new iteration of solutions needs to be executed.\n\n";
         System.out.print(StepByStepStatus ? message : "");
         Delete_RepetedRows();
         Get_PivotsRecursion();      
         allPivotsReduced = ReduceMatrix_OnlyNonZeroPivots(allPivotsReduced);
      }

   }
   private boolean  ReduceMatrix_OnlyNonZeroPivots(boolean allPivotsReduced){
      //This method iterates the pivots, and reduces the column of those pivots that are not free
      //It returns a false if a pivot has turned into zero and therefore, it's column has not been cancelled
      //It allows to repet this method until complete
      
      String header = "======================== Solution step by step ========================\n";
      System.out.print(StepByStepStatus ? header : "");

      for (int indexVar = 0; indexVar < Get_AmountPivots(); indexVar++){
         String stepDivisor = "~~~~~~~~~~~~~~~ Step " +(indexVar + 1) +  " ~~~~~~~~~~~~~~~\n";
         String isFree      = "Pivot "+(indexVar + 1) + " is a free variable. Hence, there is nothing to do in this step. \n";
         System.out.print(StepByStepStatus ? stepDivisor : "");

         Pivots newPivot = pivots.GetPivot(indexVar);
         if(!newPivot.GetPivot_isFree(0))
            allPivotsReduced = Reduce_Column(indexVar, allPivotsReduced);     
         else
            System.out.print(StepByStepStatus ? isFree : "");
      }
      return allPivotsReduced;
   }
   private boolean  Reduce_Column(int indexVar, boolean allPivotsReduced){
      Pivots newPivot = Get_Pivot(indexVar);
      double value = GetElement(newPivot.GetPivot_Position(0));
      if(newPivot.size > 0 &&  Math.abs(value) > 0.00001){
         String varName = "X"+ (indexVar + 1);
         String position = "(" + (newPivot.GetPivot_Position(0)[0] +1) + "," + (newPivot.GetPivot_Position(0)[1]+1) + ")";
         String pivotDetails = "Pivot "+ varName + ",    Position = " + position+ ",   Value = " + value + "\n";
         System.out.print(StepByStepStatus ? pivotDetails : "");
         ClearColumn_Pivot(varName);
         System.out.print(StepByStepStatus ? "\n" : "");
      }else{            
         String isZero = "Pivot "+(indexVar + 1) + " has turn into a zero, so no operation is executed in this step \n";
         System.out.print(StepByStepStatus ? isZero : "");
         allPivotsReduced = false;
      }
      return allPivotsReduced;
   }




















   //PRINT
   public void Print_Pivots(){
      printer.Pivots(pivots);
   }
   public void print_VariablesValues() { 
      System.out.println("\nList of variable's values");
      int indexVariable=0;
      while(indexVariable<(sizeMatrix[1]-1)){  
         if (!pivots.GetPivot_isFree(indexVariable)) {
            print_BasicVariableValue(indexVariable);
         }
         indexVariable++;
      }
      print_FreeVars();
   }
   public void print_BasicVariableValue(int indexBasicVariable){
      //This method prints the basic variables in terms of the free ones
      //x = result + c1[freeVar1] + c2[freeVar] + ....
      String varName = "X" + (indexBasicVariable+1);
      int[] position = pivots.GetPivot_Position(varName);
      double decimalValue = matrix[position[0]][sizeMatrix[1] -1];

      //prints the var and its decimal value
      System.out.printf("%S= %7.2f", varName, decimalValue );       

      //prints the value in terms of free variables
      for (int indexVariable = 0; indexVariable < (sizeMatrix[1]-1); indexVariable++) {
         if(pivots.GetPivot_isFree(indexVariable)){
            double coeficientFreeVar = matrix[position[0]][indexVariable]; 
            //While finding X, this values have to change sign
            if(coeficientFreeVar < 0)
            //its real value is negative
               System.out.printf(" +%7.2fX%d ", Math.abs(coeficientFreeVar), (indexVariable+1));
            else               
            //its real value is psitive
               System.out.printf(" -%7.2fX%d ", Math.abs(coeficientFreeVar), (indexVariable+1));
         }
      }

      System.out.print("\n");
   }
   public void print_FreeVars(){
      for (int indexFreeVar = 0; indexFreeVar < pivots.size; indexFreeVar++) {
         if(pivots.GetPivot_isFree(indexFreeVar)){
            String name = pivots.GetPivot_Name(indexFreeVar);
            System.out.printf("%S= %7s is free\n", name, name );  
         }
      }
   }
   
   
   
   








}