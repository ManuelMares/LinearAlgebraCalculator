package Shared;
//import Validations; You don't need to import files in the same folder


public  class MatrixMethods{      
   static Validations validation = new Validations();


   //GET NEW MATRIX

   public double[][] Matrix_GetNew(String nameMatrix){
      int[] sizeMatrix = Matrix_GetSize(nameMatrix);
      System.out.println("Now determine its values (row, column):");
      double[][] matrix = Matrix_GetInputs(nameMatrix, sizeMatrix);
      return matrix;
   }
   
   public static int[] Matrix_GetSize(String name){
      int[] size = {0,0};
      size[0] = validation.validateInput_BiggerThan("Please indicate the amount of ROWS in the matrix " + name, 1);
      size[1] = validation.validateInput_BiggerThan("Please indicate the amount of COLUMNS in the matrix " + name, 1);
      System.out.println("Size of Matrix " + name + " is: [" + size[0] + "," + size[1] + "].");
      return size;
   }   
   
   public double[][] Matrix_GetInputs(String nameMatrix, int[] sizeMatrix){
      double[][] matrix = new double[sizeMatrix[0]][sizeMatrix[1]];
      for (int indexI = 0; indexI < sizeMatrix[0]; indexI++) {
         for(int indexJ = 0; indexJ < sizeMatrix[1]; indexJ++){
            System.out.println("Please input value (" + (indexI+1) +"," + (indexJ+1) + ") of matrix " + nameMatrix + ".");
            matrix[indexI][indexJ] = validation.validateInput_isDouble();
         }
      }

      return matrix;
   }



   //REORDER MATRIX ROWS
   public double[][] ReorderMatrixRows(double[][] matrix, int[] rowsOrder){
      double[][] reorderedMatrix = CopyMatrix(matrix);
      int counter = 0;
      for (int element : rowsOrder) {
         if( element >= 0 ){
            reorderedMatrix[counter] = matrix[element];
            counter++;
         }
      }
      return reorderedMatrix;
   }





   //GET PIVOTS
   //This method get the list of pivots.
   //if a column contains a column without pivot, that's -1
   //the program will generate a new random list of pivots (just for reference of rows)
   //and the matrix will reorder the rows acording to this list.
   //then it will try to find the new pivots
   //the while cicle will try this up to 8 times before leaving
   public int[] Matrix_GetPivotIndices_RandomTries(double[][] matrix){
      int amountPivotValues = AugmentedMatrix_GetMaxNumberPivots(matrix.length, matrix[0].length);
      int[] listPivots = CreateArray_Int(amountPivotValues, -1);  
      int[] finalListPivots = CreateArray_Int(amountPivotValues, -1); 
      double[][] originalMatrix = CopyMatrix(matrix); 
      boolean validList = false;  
      int counter = 0 ;   

      while( !validList ){
         listPivots = Matrix_GetPivotIndices(matrix, listPivots);
         int invalidColumn = validation.Array_IndexOf(listPivots, -1);
         if(invalidColumn < 0 ){
            validList = true;
            double[] valuesPivots = matrix_GetValuesOfIndeces(matrix, listPivots);
            finalListPivots = matrix_IndecesOfValues(originalMatrix, valuesPivots);
         }else if(counter > 10){
            finalListPivots = listPivots;
            validList = true;
         }else{
            listPivots = Array_RandomNumbers((listPivots.length));
            matrix = ReorderMatrixRows(matrix, listPivots);
         }
         counter++;
      }
      
      return finalListPivots;

   }



   public int[] matrix_IndecesOfValues(double[][] matrix, double[] pivotvalues){
      int[] newList = CreateArray_Int((pivotvalues.length), -1);
      for (int index = 0; index < newList.length; index++) {
         double[] column = GetColumnFromMatrix(matrix, index);
         newList[index] = validation.Array_IndexOf(column, pivotvalues[index]);         
      }
      return newList;
   }

   public double[] matrix_GetValuesOfIndeces(double[][] matrix, int[] indeces){
      double[] pivotValues = CreateArray_Double(indeces.length, -1.0);
      for (int index = 0; index < indeces.length; index++) {
         pivotValues[index] = matrix[indeces[index]][index];
      }
      return pivotValues;
   }

   public int[] Array_RandomNumbers(int size){
      int maxValue = size;
      int [] list = CreateArray_Int(size, -1);
      int [] memory = CreateArray_Int(size, -1);
      int  counter = 0;
      for (int element : list) {
         boolean isNewValue = false;
         int rnd = 0;
         while ( !isNewValue ) {
            rnd = (int) (Math.random()*(maxValue) + 0 );
            int isValid = validation.Array_IndexOf(memory, rnd);
            if(isValid < 0 ){
               isNewValue = true;
            }
         }
         memory[counter] = rnd;
         list[counter] = rnd;
         counter++;
      }
      return list;
   }
   
   private int[] Matrix_GetPivotIndices(double[][] matrix, int[] pivotIndices){
      pivotIndices = CreateArray_Int((pivotIndices.length) , -1);  
      //Populates the list of pivotIndices
      for (int columnsIndex = 0; columnsIndex < (pivotIndices.length); columnsIndex++) {
         double[] columnMatrix = GetColumnFromMatrix(matrix, columnsIndex);
         pivotIndices[columnsIndex] = GetIndexPivot_Column(columnMatrix, pivotIndices);
      }

      return pivotIndices;
   }

   public int GetIndexPivot_Column(double[] columnMatrix, int[] pivotIndices){
      boolean validPivot =false;
      int counter = 0;
      int pivotIndex = -1;
      while(!validPivot){
         //if the value is -not 0-, and it such -row index is not listed-
         if(columnMatrix[counter] !=0 &&
            validation.Array_IndexOf(pivotIndices, counter) == -1){
            pivotIndex = counter;
            validPivot = true;
         }
         //exit if no pivot is found
         if(counter == (columnMatrix.length-1)){
            validPivot = true;
         }
         counter++;
      }
      return pivotIndex;
   }

   public int NoAugmentedMatrix_GetMaxNumberPivots(int amountRows, int amountColumns){
      int amountPivotValues = (amountRows < amountColumns ) ? amountRows : amountColumns;
      return amountPivotValues;
   }

   public int AugmentedMatrix_GetMaxNumberPivots(int amountRows, int amountColumns){
      int amountPivotValues = (amountRows < (amountColumns-1) ) ? amountRows : (amountColumns-1);
      return amountPivotValues;
   }



   public double[] GetColumnFromMatrix(double[][] matrix, int columnIndex){
      double[] column = new double[matrix.length];
      int counter = 0;
      for (double[] row : matrix) {
         column[counter] = row[columnIndex];
         counter++;
      }
      return column;
   }
   
   public int[] CreateArray_Int(int size, int defaultValue){
      int[] array = new int[size];
      for (int i = 0; i < size; i++) {
         array[i] = defaultValue;
      }        
      return array;     
   }
   
   public double[] CreateArray_Double(int size, double defaultValue){
      double[] array = new double[size];
      for (int i = 0; i < size; i++) {
         array[i] = defaultValue;
      }        
      return array;     
   }

   public double[][] CopyMatrix(double[][] matrix){
      double[][] newMatrix = new double[matrix.length][matrix[0].length];
      int counterRows = 0;
      for (double[] row : matrix) {
         int counterColumns = 0;
         for (double element : row) {
            newMatrix[counterRows][counterColumns] = element;
            counterColumns++;
         }
         counterRows++;
      }
      return newMatrix;
   }









   //PRINT METHODS
   public void PrintMatrix( String title, double[][] matrix ){
      System.out.println(title);
      
      for (int indexI = 0; indexI < matrix.length; indexI++) {
         System.out.print("|  ");
         for(int indexJ = 0; indexJ < matrix[1].length; indexJ++){
            System.out.print("  " + matrix[indexI][indexJ] + "  ");
         }
         System.out.println("  |");
      }
   }
   
   public boolean PrintArray_Aumented(int[] array, String message){
      System.out.println(message);
      System.out.print("{ ");
      for (int i : array) {
         System.out.print("  " + i + "  ");
      }
      System.out.print(" }. \n");
      return true;
   }

   public boolean PrintArray(int[] array, String message){
      System.out.println(message);
      System.out.print("{ ");
      for (int i : array) {
         System.out.print("  " + (i+1) + "  ");
      }
      System.out.print(" }. \n");
      return true;
   }
   
   public boolean PrintArray(double[] array, String message){
      System.out.println(message);
      System.out.print("{ ");
      for (double i : array) {
         System.out.print("  " + i + "  ");
      }
      System.out.print(" }.\n");
      return true;
   }

   public double[] getUnitarianRow(double[][] matrix, int indexColumn, int indexPivot) {
      //This method a specific row of a a matrix based on the position of the index, and return that row divided by the pivot value
      double [] arrayUnitarianRow = new double[matrix[0].length];
      int counter = 0;
      for (double element : matrix[indexPivot]) {
         arrayUnitarianRow[counter] = element / matrix[indexPivot][indexColumn];
         counter++;
      }
      return arrayUnitarianRow;
   }

   public double[] vectorByScalar(double[] vector, double scalar){
      double[] newVector = new double[vector.length];
      int counter = 0;
      for (double element : vector) {
         newVector[counter] = element * scalar;
         counter++;
      }
      return newVector;
   }

   public double[] Vector_CreateCopy(double[] vector){
      double[] newVector = new double[vector.length];
      int counter = 0;
      for (double element : vector) {
         newVector[counter] = element;
         counter++;
      }
      return newVector;
   }

   public double[] Vector_AddElement(double[] vector, double newElement){
      double[] newVector = new double[0];
      if(vector == null ){
         newVector = new double[1];
         newVector[1] = newElement;
      }else{
         newVector = new double[vector.length + 1];
         int counter = 0;
         for (double element : vector) {
            newVector[counter] = element;
            counter++;
         }
         newVector[counter] = newElement;
      }
      return newVector;
   }
   
   public int[] Vector_AddElement(int[] vector, int newElement){
      int[] newVector = new int[0];
      if(vector == null ){
         newVector = new int[1];
         newVector[0] = newElement;
      }else{
         newVector = new int[vector.length + 1];
         int counter = 0;
         for (int element : vector) {
            newVector[counter] = element;
            counter++;
         }
         newVector[counter] = newElement;
      }
      return newVector;
   }

   public double[] Vector_addTogether(double[] vector1, double[] vector2){
      double[] newVector = new double[vector1.length];
      if(vector1.length == vector2.length){
         for (int element = 0; element < vector1.length; element++) {
            newVector[element] = vector1[element] +  vector2[element];
         }
      }
      return newVector;
   }

   public int[] Matrix_CheckForZeroColumn(double[][] matrix) {
      int[] zeroColumns = null;
      for (int index = 0; index < matrix.length; index++) {
         double[] column = GetColumnFromMatrix(matrix, index);
         zeroColumns = (Array_isZero(column)) ? zeroColumns=Vector_AddElement(zeroColumns, index): zeroColumns;
      }
      if(zeroColumns != null && zeroColumns.length >= 0){
         return zeroColumns;
      }else{
         return zeroColumns;
      }
   }

   public boolean Array_isZero(double[] vector){
      boolean isZero = true;
      for (double element : vector) {
         if(element != 0){
            isZero = false;
         }
      }
      return isZero;
   }


}