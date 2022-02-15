package Classes;
//import Validations; You don't need to import files in the same folder


public  class Matrix{      
   static Validations validation = new Validations();

   private double[][] matrix;
   public String nameMatrix;
   public int[] sizeMatrix;
   public Pivots pivots;
   public boolean isConsistent;
   public boolean showStepByStep;


   //CONSTRUCTORS---------------------------------------------
   public Matrix(String name, double[][] values){
      showStepByStep = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      SetMatrixTo_Matrix(values);
   }
   public Matrix(String name, int[] size, double value){
      showStepByStep = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size[0]][size[1]];
      SetMatrixTo_Value(value);
   }
   public Matrix(String name, int size, double value){
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size][size];
      SetMatrixTo_Value(value);
   }
   public Matrix(String name, int[] size){
      showStepByStep = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size[0]][size[1]];
   }
   public Matrix(String name, int size){
      showStepByStep = true;
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = new double[size][size];
   }
   public Matrix(String name){
      isConsistent = true;
      sizeMatrix = new int[2];
      nameMatrix = name;
      pivots = new Pivots();
      matrix = null;
      showStepByStep = true;
   }








   //PROPERTIES   
   public void Rename(String name){
      nameMatrix = name;
   }
   public String GetName(){
      return nameMatrix;
   }
   public void SetSize(int[] size){
      if(size != null && size.length == 2){
         matrix = new double[size[0]][size[1]];
         UpdateSize();
      }else{
         System.out.println("Error: The size is indicated in a uncompatible format");
         System.out.println("In method SetSize");
      }
   }
   private void UpdateSize(){
      if(sizeMatrix != null ){
         sizeMatrix = new int[2];
      }
      sizeMatrix[1] = matrix[0].length;
      sizeMatrix[0] = matrix.length;
   }
   public int[] GetSize(){
      return sizeMatrix;
   }
   public void SetStepByStep(boolean status){
      showStepByStep = status;
   }






   //ADD
   public void AddColumn(double[] values){
      double[][] newMatrix = new double[matrix.length][matrix[0].length + 1];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double element : row) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, element);
            indexColumn++;
         }
         int[] positionElement = {indexRow, indexColumn};
         SetElementTo_Value(positionElement, values[indexRow]);
         indexRow++;
      }
   }
   public void AddColumn(double value){
      double[][] newMatrix = new double[matrix.length][matrix[0].length + 1];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double element : row) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, element);
            indexColumn++;
         }
         int[] positionElement = {indexRow, indexColumn};
         SetElementTo_Value(positionElement, value);
         indexRow++;
      }
   }
   public void AddColumn(){
      double[][] newMatrix = new double[matrix.length][matrix[0].length + 1];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double value : row) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, value);
            indexColumn++;
         }
         int[] positionElement = {indexRow, indexColumn};
         SetElementTo_Value(positionElement, 0.0);
         indexRow++;
      }
   }

   public void AddRow(double[] values){
      double[][] newMatrix = new double[matrix.length + 1][matrix[0].length];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double element : row) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, values[indexColumn]);
            indexColumn++;
         }
         indexRow++;
      }      
      setRowTo_Array(indexRow, values);
   }
   public void AddRow(double value){
      double[][] newMatrix = new double[matrix.length + 1][matrix[0].length];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double element : row) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, value);
            indexColumn++;
         }
         indexRow++;
         setRowTo_Value(indexRow, value);
      }
   }
   public void AddRow(){
      double[][] newMatrix = new double[matrix.length + 1][matrix[0].length];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double value : row) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, value);
            indexColumn++;
         }
         indexRow++;
      }
      setRowTo_Value(indexRow, 0.0);
   }






   //DELETE
   public void DeleteColumn(int indexColumn){


   }
   public void DeleteRow(int indexRow){


   }





   //SET VALUES
   public void setMatrix_Inputs(){
      //This method request the entries of the matrix by asking to the user
      if(sizeMatrix != null && sizeMatrix.length == 2 ){
         for (int indexRow = 0; indexRow < sizeMatrix[0]; indexRow++) {
            setMatrixRow_Inputs(indexRow);            
         }
      }
   }

   public void setMatrixRow_Inputs(int indexRow){
      for (int indexColumn = 0; indexColumn < sizeMatrix[1]; indexColumn++) {
         String message = "Indicate entry (" + (indexRow + 1) + "," + (indexColumn + 1) + "):";
         double value = validation.validateInput_isDouble(message);
         int[] position = {indexRow, indexColumn};
         SetElementTo_Value(position, value);         
      }
   }


   public void SetMatrixTo_Value(double value){
      for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
         for (int indexColumn = 0; indexColumn < matrix.length; indexColumn++) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, value);
         }
      }
   }
   public void SetMatrixTo_Matrix(double[][] newMatrix){
      matrix = newMatrix;
   }
   
   public void setRowTo_Array(int indexRow, double[] values){
      if( indexRow >= 0 && matrix.length > indexRow ){
         if(values != null){
            for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
               int[] positionElement = {indexRow, indexColumn};
               SetElementTo_Value(positionElement, values[indexColumn]);
            }
         }else{
            System.out.println("Error: The array passed as a paremeter surpasses the amoun of row in matrix " + nameMatrix);
         }
      }else{
         System.out.println("Error: The row you are tryting to modify is beyond the limits of matrix " + nameMatrix);
      }
   }
   public void setRowTo_Value(int indexRow, double value){
      if( indexRow >= 0 && matrix.length > indexRow ){
         for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, value);
         }
      }else{
         System.out.println("Error: The row you are tryting to modify is beyond the limits of matrix " + nameMatrix);
      }
   }
   
   public void SetRowsTo_Value(int[] listRows, double value){
      if(listRows != null && listRows.length >= 0){
         for (int index : listRows) {
            setRowTo_Value(index, value);
         }
      }
   }

   public void setColumnTo_Array(int indexColumn, double[] values){      
      if( indexColumn >= 0 && matrix[0].length > indexColumn ){
         if(values != null && matrix[0].length>values.length){
            for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
               int[] positionElement = {indexRow, indexColumn};
               SetElementTo_Value(positionElement, values[indexRow]);
            }
         }else{
            System.out.println("Error: The array of values passed as parameter surpass the amount of columns in matrix "+ nameMatrix);
         }
      }else{
         System.out.println("Error: The column you are tryting to modify is beyond the limits of matrix "+nameMatrix);
      }
   }
   public void setColumnTo_Value(int indexColumn, double value){
      if( indexColumn >= 0 && matrix[0].length > indexColumn ){
         for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, value);
         }
      }else{
         System.out.println("Error: The column you are tryting to modify is beyond the limits of matrix "+nameMatrix);
      }
   }

   public void SetElementTo_Value(int[] position, double value){
      if(position != null && position.length == 2){
         if(position[0] >=0 && matrix.length > position[0]){
            if(position[1] >=0 && matrix[0].length > position[1]){
               matrix[position[0]][position[1]] = value;
            }else{
               System.out.print("Error: The position indicated was beyond the amount of columns in matrix " + nameMatrix);
            }
            matrix[position[0]][position[1]] = value;
         }else{
            System.out.print("Error: The position indicated was beyond the amount of rows in matrix " + nameMatrix);
         }
      }
      else{
         System.out.print("Error: Element has not been modify because the position is not a valid parameter");
      }
   }







   //GET
   public double[][] GetCopy_Matrix(){
      double[][] newMatrix = new double[matrix.length][matrix[0].length];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double[] column : matrix) {            
            int[] positionElement = {indexRow, indexColumn};
            SetElementTo_Value(positionElement, GetElement(positionElement));
            indexColumn++;
         }
         indexRow++;
      }
      return newMatrix;
   }

   public double GetElement(int[] position){
      double value = 0;
      if(position != null && position.length == 2){
         if(position[0] >=0 && matrix.length > position[0] &&
            position[1] >=0 && matrix[0].length > position[1] ){
               return matrix[position[0]][position[1]];
         }else{
            System.out.println("Error: The position requested is beyond the size of the matrix ");
            return value;
         }
      }else{
         System.out.println("Error: No value has been given because the position requested is not in a valid format of double ");
         return value;
      }
   }

   public double[] GetRow(int indexRow){
      double[] newRow = new double[sizeMatrix[1]];
      if( indexRow >= 0 && indexRow<sizeMatrix[0]){
         int indexColumn  = 0;
         for (double element : matrix[indexRow]) {
            int[] position = {indexRow, indexColumn};
            newRow[indexColumn] = GetElement(position);
            indexColumn++;
         } 
      }else{
         System.out.println("Error: The row requested is beyond the size of the matrix ");
      }
      return newRow;
   }

   public double[] GetColumn(int indexColumn){
      double[] newColumn = new double[sizeMatrix[0]];
      if(indexColumn >= 0 && indexColumn< sizeMatrix[1]){
         for (int indexRow = 0; indexRow < sizeMatrix[0]; indexRow++) {
            int[] position = {indexRow, indexColumn};
            newColumn[indexRow] = GetElement(position);
         }
      }else{
         System.out.println("Error: The column requested is beyond the size of the matrix ");
      }

      return newColumn;
   }



   
   //OPERATIONS
   public void ModifyMatrix_AddVectorTo_Column(int indexColumn, double[] array){
      if( array != null && matrix.length == array.length ){
         if(indexColumn >= 0 && matrix[0].length > indexColumn){
            double[] newColumn = GetColumn(indexColumn);
            //TODO: CHANGE FOR A VECTOR INSTANCE
            for (int index = 0; index < newColumn.length; index++) {
               newColumn[index] = newColumn[index] * array[index];
            }   
            setColumnTo_Array(indexColumn, newColumn);
         }else{
            System.out.println("Error: The index given is not within the range of the matrix");
            System.out.println("In method ModifyMatrix_AddVectorTo_Column");
         }
      }else{
         System.out.println("Error: The given array does not have the adecuate length");
         System.out.println("In method ModifyMatrix_AddVectorTo_Column");
      }

   }
   public void ModifyMatrix_ColumnByScalar(int indexColumn, double scalar){
      if(indexColumn >= 0 && matrix[0].length > indexColumn){
         double[] newColumn = GetColumn(indexColumn);
         //TODO: CHANGE FOR A VECTOR INSTANCE
         for (int index = 0; index < newColumn.length; index++) {
            newColumn[index] = newColumn[index] * scalar;
         }   
         setColumnTo_Array(indexColumn, newColumn);
      }else{
         System.out.println("Error: The index given is not within the range of the matrix");
         System.out.println("In method ModifyMatrix_ColumnByScalar");
      }
   }

   public void ModifyMatrix_AddVectorTo_Row(int indexRow, double[] array){
      if( array != null && sizeMatrix[1] == array.length ){
         if(indexRow >= 0 && matrix.length > indexRow){
            double[] newRow = GetRow(indexRow);
            for (int index = 0; index < newRow.length; index++) {
               newRow[index] = newRow[index] + array[index];
            }   
            setRowTo_Array(indexRow, newRow);
         }else{
            System.out.println("Error: The index given is not within the range of the matrix");
            System.out.println("In method ModifyMatrix_AddVectorTo_Row");
         }
      }else{
         System.out.println("Error: The given array does not have the adecuate length");
         System.out.println("In method ModifyMatrix_AddVectorTo_Row");
      }

   }
   public void ModifyMatrix_RowByScalar(int indexRow, double scalar){
      if(indexRow >= 0 && matrix.length > indexRow){
         double[] newRow = GetRow(indexRow);
         //TODO: CHANGE FOR A VECTOR INSTANCE
         for (int index = 0; index < newRow.length; index++) {
            newRow[index] = newRow[index] * scalar;
         }   
         setRowTo_Array(indexRow, newRow);
      }else{
         System.out.println("Error: The index given is not within the range of the matrix");
         System.out.println("In method ModifyMatrix_RowByScalar");
      }
   }

   public double[] Operate_ArrayByScalar(double[] vector, double scalar){
      double[] newVector = new double[vector.length];
      if(vector != null && vector.length > 0){
         if(scalar != 0){
            int counter = 0;
            for (double element : vector) {
               newVector[counter] = element * scalar;
               counter++;
            }
         }else{
            newVector = vector;
         }
      }
      else{
         System.out.println("Error: The vector is not valid" );
         System.out.println("In method Operate_RowByScalar");
      }
      return newVector;
   }


   public double[] GetUnitarianRow(int[] pivotPosition){
      double pivotValue = GetElement(pivotPosition);
      double[] pivotRow = GetRow(pivotPosition[0]);
      double[] unitarianRow = Operate_ArrayByScalar(pivotRow, (1/pivotValue));

      return unitarianRow;
   }
   public void usePivotTo_ClearColumn(String pivotName) {
      if(pivotName != null ){
         int[] pivotPosition = pivots.GetPivot_Position(pivotName);
         String message = "Dividing Row" + (pivotPosition[0] + 1)+ " by " + GetElement(pivotPosition) + " to get the unitarian row, the new matrix is:";
         double[] unitarianRow = GetUnitarianRow(pivotPosition);
         setRowTo_Array(pivotPosition[0], unitarianRow);
         if(showStepByStep){
            PrintMatrix(message);
            System.out.println("\nNow this unitarian row will be use to clean the pivot's column. The operations are:");
         }
         ClearColumn(unitarianRow, pivotPosition);
         if(showStepByStep)
            PrintMatrix("The resulting matrix in step " + pivotName.charAt(1) + " is:");
      }else{
         System.out.println("Error: The parameter indicated is exceeds the amount of variables in the matrix");
         System.out.println("In method ClearColumn");
      }
   }
   private void ClearColumn(double[] unitarianRow, int[] pivotPosition){
      //Loops through each row and cancels element in the same column with the unitarian row, except itself
      int counter = 1;
      for (int indexRow = 0; indexRow < sizeMatrix[0]; indexRow++) {
         if( indexRow != pivotPosition[0] && matrix[indexRow][pivotPosition[1]] != 0){
            int[] positionToCancel = {indexRow, pivotPosition[1]};
            double factorToCancel = (-1) * GetElement(positionToCancel);
            PrintRowOperationInstruction(counter,(pivotPosition[0]+1),factorToCancel, (positionToCancel[0]+1));
            double[] arrayToAdd = Operate_ArrayByScalar(unitarianRow, factorToCancel);         
            ModifyMatrix_AddVectorTo_Row(indexRow, arrayToAdd);
            counter++;
         }
         
      }
   } 



   //VALIDATIONS
   public void Validate_HasColumnsZero(){
      //TODO: This method return an array with the list of all the zero columns
      

   }

   public boolean Validate_ColumnIsZero(int indexColumn){
      //This method return a boolean indicating if the whole column is set to zero
      boolean isZero = true;
      if(indexColumn >= 0 && matrix[0].length > indexColumn){
         for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
            if(matrix[indexRow][indexColumn] != 0)
               isZero = false;            
         }
      }else{
         System.out.println("Error: the index given is not within the matrix length");
         System.out.println("In method Validate_ColumnIsZero");
      }
      return isZero;
   }

   public boolean Validate_RowIsZero(int indexRow){
      //This method returns a boolean indicating if the whole row is set to zero
      boolean isZero = true;
      if(indexRow >= 0 && matrix.length > indexRow){
         for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
            if(matrix[indexRow][indexColumn] != 0)
               isZero = false;            
         }
      }else{
         System.out.println("Error: the index given is not within the matrix length");
         System.out.println("In method Validate_RowIsZero");
      }
      return isZero;
   }

   private boolean GetPivot_Column(int indexColumn) {
      if(indexColumn >= 0 && indexColumn <= sizeMatrix[1]){
         // gets the column as an array. Then loops through it looking for the first non-zero value.
         //With such index, it checks if it is already listed as a pivot. If so, it check next number. If not, it stops.
         boolean hasPivot = false;
         double[] column = GetColumn(indexColumn);
         int indexRow = 0;
         while (!hasPivot && indexRow < column.length) {
            //if the value is not zero and its index (row) is not listed yet
            if( column[indexRow] != 0  && !pivots.Validate_RowHasPivot(indexRow)){
               int[] position = {indexRow, indexColumn};
               double pivotValue = column[indexRow];
               pivots.AddPivot(( "X" + (pivots.size + 1) ), position, pivotValue, false);
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







   //PIVOTS
   public void SetPivotsResults(){
      pivots.results = new double[pivots.size];
      int counter = 0;
      for (int[] positionPivot : pivots.positions) {
         int[] positionResult = {positionPivot[0], (sizeMatrix[1]-1)};
         pivots.results[counter] = GetElement(positionResult);
         counter++;
      }
   }

   public void TryGetPivots(){      
      //This method will get the pivots. If it is not possible to find all of the index, it will randomly rearrage it and try again.


      //There will be as many variables as the max num. of rows or columns (without the results)
      //To get them, we will get the actual amount of pivots of the smaller amount between rows and (columns - 1)
      //The rest will be free variable all the way up to the max between rows and (colums - 1)
      int amountVariables = (sizeMatrix[0] > (sizeMatrix[1]-1)) ? sizeMatrix[0] : (sizeMatrix[1]-1);
      int amountBasicVariables = (sizeMatrix[0] < (sizeMatrix[1]-1)) ? sizeMatrix[0] : (sizeMatrix[1]-1);

      pivots.SetPivotsToZero();
      int pivotsFound = setAvailablePivots(amountVariables);
      setFreeVariables(amountVariables);

      //While we cannot find all the pivotes, or there have been 10 tries already
      int numberTries = 0;
      while(pivotsFound<amountBasicVariables && numberTries < 10){
         pivots.SetPivotsToZero();
         ReaArrangeMatrix_Random();

         pivotsFound = setAvailablePivots(amountVariables);
         setFreeVariables(amountVariables);
         numberTries++;
      }

      if(numberTries > 0){
         PrintMatrix("The matrix was rearranged to find the pivots:");
      }
      
   }
   
   private void ReaArrangeMatrix_Random(){
      int[] rndmArray = newArray(sizeMatrix[0], -1);
      for (int index = 0; index < rndmArray.length; index++) {
         int max = sizeMatrix[0] -1;
         int min = 0;
         
         boolean isNew = false;
         while(!isNew){
            int newRndm = (int)(Math.random()*(max - min + 1) + min);
            //if the number is not in the array already
            if(ArrayContains(rndmArray, newRndm) < 0){
               rndmArray[index] = newRndm;
               isNew = true;               
            }
         }
      }
      ReaArrangeMatrix_GivenOrder(rndmArray);
   }

   private void ReaArrangeMatrix_GivenOrder(int[] array){
      if(array.length == sizeMatrix[0]){
         double[][] newMatrix = new double[sizeMatrix[0]][sizeMatrix[1]];
         int counter = 0;
         for (int indexRow : array) {
            newMatrix[counter] = GetRow(indexRow);
            counter++;
         }
         SetMatrixTo_Matrix(newMatrix);

      }else{
         System.out.println("Error: The given array does not match the matrix size");
         System.out.println("in method ReaArrangeMatrix_GivenOrder");
      }
   }

   public int setAvailablePivots(int amountVariables){
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

   public void setFreeVariables(int amountVariables){
      if(pivots.names.length < amountVariables){
         for (int indexFreeVar = pivots.names.length; indexFreeVar < amountVariables; indexFreeVar++) {
            pivots.AddFreeVariable( "X" + (pivots.size + 1) );  
         }
      }
   }

   public void DeleteRepetedRows(){
      int[] rowsToDelete = new int[0];
      for (int indexRow_Base = 0; indexRow_Base < sizeMatrix[0]; indexRow_Base++) {
         for (int indexRow_ToCompare = (indexRow_Base + 1); indexRow_ToCompare < sizeMatrix[0]; indexRow_ToCompare++) {
            if(ArraysAreEqual(GetRow(indexRow_Base), GetRow(indexRow_ToCompare))){
               rowsToDelete = new int[rowsToDelete.length + 1];
               rowsToDelete[rowsToDelete.length - 1] = indexRow_ToCompare;
            }
         }
      }
      if(rowsToDelete.length > 0){
         SetRowsTo_Value(rowsToDelete, 0.0);
         PrintMatrix("\nMatrix without repeted rows:");
      }
   }






   //CONCLUSIONS
   public int CheckConsistentsy(){
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

      double[] row = GetRow(index);
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
         if(pivots.areFree[index])
            return !isLI;
      }
      return isLI;
   }




   //ARRAY METHODS
   public int[] newArray(int length){
      int[] array = new int[length];
      for (int index = 0; index < array.length; index++) {
         array[index] = 0;
      }
      return array;
   }
   public int[] newArray(int length, int value){
      int[] array = new int[length];
      for (int index = 0; index < array.length; index++) {
         array[index] = value;
      }
      return array;
   }
   public double[] newArray(int length, double value){
      double[] array = new double[length];
      for (int index = 0; index < array.length; index++) {
         array[index] = value;
      }
      return array;
   }

   private int ArrayContains(int[] array, int value){
      if(array != null && array.length >= 0){
         for (int counter = 0; counter < array.length; counter++) {
            if(array[counter] == value){
               return counter;
            }
         }
      }
      return -1;
   }
   private int ArrayContains(double[] array, double value){
      if(array != null && array.length >= 0){
         for (int counter = 0; counter < array.length; counter++) {
            if(array[counter] == value){
               return counter;
            }
         }
      }
      return -1;
   }

   public boolean ArraysAreEqual(double[] arr1, double[] arr2){
      boolean areEqual = true;
      if(arr1 != null && arr1.length >0 && 
         arr2 != null && arr2.length > 0){
         for (int index = 0; index < arr2.length; index++) {
            if(arr1[index] != arr2[index]){
               return false;
            }
         }   
      }else{
         return false;
      }
      
      return areEqual;
   }
   public boolean ArraysAreEqual(int[] arr1, int[] arr2){
      boolean areEqual = true;
      if(arr1 != null && arr1.length >0 && 
         arr2 != null && arr2.length > 0){
         for (int index = 0; index < arr2.length; index++) {
            if(arr1[index] != arr2[index]){
               return false;
            }
         }   
      }else{
         return false;
      }
      
      return areEqual;
   }











   //PRINT

   public void PrintMatrix(String title){
      System.out.println(title);    
      for (int indexRow = 0; indexRow < sizeMatrix[0]; indexRow++) {
         System.out.print("|");
         PrintMatrix_Row(indexRow);
         System.out.println("   |");
      }
   }

   public void PrintMatrix_Row(int indexRow){
      for (int indexColumn = 0; indexColumn < sizeMatrix[1]; indexColumn++) {
         int[] position = {indexRow, indexColumn};
         double elementValue = GetElement(position); 
         if(elementValue != 0 )
            System.out.printf(" %6.2f", elementValue);
         else
            System.out.printf(" %6.2f", 0.0);
      }
   }

   public void PrintPivots(){
      pivots.PrintPivots();
   }

   public void printVariablesValues() {
      System.out.println("\nList of variable's values");
      int indexVariable=0;
      while(indexVariable<(sizeMatrix[1]-1)){  
         if (!pivots.areFree[indexVariable]) {
            print_BasicVariableValue(indexVariable);
         }
         indexVariable++;
      }
      printFreeVars();
   }

   public void print_BasicVariableValue(int indexBasicVariable){
      //This method prints the basic variables in terms of the free ones
      //x = result + c1[freeVar1] + c2[freeVar] + ....
      int[] position = pivots.GetPivot_Position("X" + (indexBasicVariable+1));
      String varName = pivots.names[indexBasicVariable];
      double decimalValue = matrix[position[0]][sizeMatrix[1] -1];

      //prints the var and its decimal value
      System.out.printf("%S= %7.2f", varName, decimalValue );       

      //prints the value in terms of free variables
      for (int indexVariable = 0; indexVariable < (sizeMatrix[1]-1); indexVariable++) {
         if(pivots.areFree[indexVariable]){
            double coeficientFreeVar = matrix[position[0]][indexVariable]; 
            if(coeficientFreeVar < 0)
               System.out.printf(" -%7.2fX%d ", coeficientFreeVar, (indexVariable+1));
            else               
               System.out.printf(" +%7.2fX%d ", coeficientFreeVar, (indexVariable+1));
         }
      }

      System.out.print("\n");
   }

   public void printFreeVars(){
      int indexFreeVar = 0;
      for (String name : pivots.names) {
         if(pivots.areFree[indexFreeVar]){
            System.out.printf("%S= %7s is free\n\n", name, name );  
         }
         indexFreeVar++;
      }
   }
   
   public void printArray(double[]array, String message){
      System.out.println(message);
      System.out.print("{");
      for (double element : array) {         
         System.out.print(element + ", ");
      }
      System.out.println("}");
   }
   
   public void printArray(int[]array, String message){
      System.out.println(message);
      System.out.print("{");
      for (int element : array) {         
         System.out.print(element + ", ");
      }
      System.out.println("}");
   }

   public void printVariables_ParametricForm() {
      //TODO: PRINT PARAMETRIC FORM
   }

   public void printColumn_Variables(int amountVariables){

   }

   public void printColumn(int indexColumn){

   }
   
   public void printColumn(int indexColumn, String parameterName){
      
   }
   
   

   //STEP BY STEP
   
   public void PrintRowOperationInstruction(int indexStep, int indexRow_Base, double factor, int indexRow_ToCancel){
      if(showStepByStep){
         //printf: 1) R2*(-5) added to R3.
         System.out.printf("%d)R%d*(%.2f) added to R%d.\n", indexStep, indexRow_Base, factor, indexRow_ToCancel );
      }      
   }






}