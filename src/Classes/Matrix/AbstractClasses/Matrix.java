package Classes.Matrix.AbstractClasses;
import Classes.Vector;
import Classes.Utilities.Inputs;

public abstract class Matrix{
   static Inputs input = new Inputs();
   static Vector vector = new Vector();
 
   protected double[][] matrix;
   protected String     nameMatrix;
   protected int[]      sizeMatrix;
   protected boolean    isConsistent; 
 
   //CONSTRUCTORS---------------------------------------------
   public Matrix(String name, double[][] values){
      int[] size = {values.length, values[1].length };
      Set_IsConsistent(true);
      Set_Size(size);
      Set_Name(name);
      Set_MatrixToMatrix(values);
   }
   
 
   //PROPERTIES
   public void    Set_Name(String name){
      nameMatrix = name;
   }
   public String  Get_Name(){
      return nameMatrix;
   }
   public void    Set_Size(){
      try {
         sizeMatrix[0] = matrix.length;
         sizeMatrix[1] = matrix[0].length;
      } catch (NullPointerException e) {
         System.out.printf("Exception: %s\n", e);
         sizeMatrix = new int[2];
      }
   }
   public void    Set_Size(int[] size){
      sizeMatrix = new int[2];
      matrix = new double[size[0]][size[1]];
      Set_Size();
   }
   public int[]   Get_Size(){
      return sizeMatrix;
   }
   public int     Get_SizeRows(){
      return sizeMatrix[0];
   }
   public int     Get_SizeColumns(){
      return sizeMatrix[1];
   }
   public void    Set_IsConsistent(boolean status){
      isConsistent = status;
   }
   public boolean Get_IsConsistent(){
      return isConsistent;
   } 


   //MATRIX
   public void    Set_MatrixInputs(){
      //This method request the entries of the matrix by asking to the user
      if(sizeMatrix != null && sizeMatrix.length == 2 ){
         for (int indexRow = 0; indexRow < sizeMatrix[0]; indexRow++) {
            Set_MatrixRowInputs(indexRow);            
         }
      }
   }
   public void    Set_MatrixToValue(double value){
      for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
         for (int indexColumn = 0; indexColumn < matrix.length; indexColumn++) {
            int[] positionElement = {indexRow, indexColumn};
            Set_ElementToValue(positionElement, value);
         }
      }
   }
   public void    Set_MatrixToMatrix(double[][] newMatrix){
      matrix = newMatrix;
   }
   public void    Set_MatrixRowInputs(int indexRow){
      for (int indexColumn = 0; indexColumn < sizeMatrix[1]; indexColumn++) {
         int[] position = {indexRow, indexColumn};
         Set_ElementToInput(position);        
      }
   }
   public double[][] Get_CopyMatrix(){
      double[][] newMatrix = new double[matrix.length][matrix[0].length];
      for (int indexRow = 0; indexRow < newMatrix.length; indexRow++) {
         newMatrix[indexRow] = vector.CopyVector(Get_Row(indexRow));
         
      }
      return newMatrix;
   }
      

   //COLUMNS AND ROWS
   public void    Add_Column(double[] values){
      double[][] newMatrix = new double[matrix.length][matrix[0].length + 1];
      int indexRow = 0;
      for (double[] row : matrix) {
         newMatrix[indexRow] = Vector.IncreaseVector(row);

         newMatrix[indexRow][newMatrix[0].length -1] = values[indexRow];
         indexRow++;
      }
      Set_MatrixToMatrix(newMatrix);
      Set_Size();
   }
   public void    Add_Column(double value){
      double[][] newMatrix = new double[matrix.length][matrix[0].length + 1];
      int indexRow = 0;
      for (double[] row : matrix) {
         newMatrix[indexRow] = Vector.IncreaseVector(row);

         newMatrix[indexRow][newMatrix[0].length -1] = value;
         indexRow++;
      }
      Set_MatrixToMatrix(newMatrix);
      Set_Size();
   }
   public void    Add_Column(){
      double[][] newMatrix = new double[matrix.length][matrix[0].length + 1];
      int indexRow = 0;
      double value = 0.0;
      for (double[] row : matrix) {
         newMatrix[indexRow] = Vector.IncreaseVector(row);

         newMatrix[indexRow][newMatrix[0].length -1] = value;
         indexRow++;
      }
      Set_MatrixToMatrix(newMatrix);
      Set_Size();
   }
   public void    Add_Row(double[] values){
      double[][] newMatrix = new double[matrix.length + 1][matrix[0].length];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double element : row) {
            int[] positionElement = {indexRow, indexColumn};
            Set_ElementToValue(positionElement, values[indexColumn]);
            indexColumn++;
         }
         indexRow++;
      }      
      Set_RowToArray(indexRow, values);
   }
   public void    Add_Row(double value){
      double[][] newMatrix = new double[matrix.length + 1][matrix[0].length];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double element : row) {
            int[] positionElement = {indexRow, indexColumn};
            Set_ElementToValue(positionElement, value);
            indexColumn++;
         }
         indexRow++;
         Set_RowToValue(indexRow, value);
      }
   }
   public void    Add_Row(){
      double[][] newMatrix = new double[matrix.length + 1][matrix[0].length];
      int indexRow = 0;
      for (double[] row : matrix) {
         int indexColumn = 0;
         for (double value : row) {
            int[] positionElement = {indexRow, indexColumn};
            Set_ElementToValue(positionElement, value);
            indexColumn++;
         }
         indexRow++;
      }
      Set_RowToValue(indexRow, 0.0);
   }
 
   public void    Delete_Column(int indexColumn){
      double[][] newMatrix = new double[matrix.length][matrix[0].length - 1];
      for (int indexRow = 0; indexRow < newMatrix.length; indexRow++) {
         newMatrix[indexRow] = Vector.DeleteElement(matrix[indexRow], indexColumn);
      }

      Set_MatrixToMatrix(newMatrix);
      Set_Size();
   }

   public void    Delete_Row(int indexRow){


   }
 
   public void    Set_RowsToValue(int[] listRows, double value){
      if(listRows != null && listRows.length >= 0){
         for (int index : listRows) {
            Set_RowToValue(index, value);
         }
      }
   }
   public void    Set_RowToArray(int indexRow, double[] values){
      if( indexRow >= 0 && matrix.length > indexRow ){
         if(values != null){
            for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
               int[] positionElement = {indexRow, indexColumn};
               Set_ElementToValue(positionElement, values[indexColumn]);
            }
         }else{
            System.out.println("Error: The array passed as a paremeter surpasses the amoun of row in matrix " + nameMatrix);
         }
      }else{
         System.out.println("Error: The row you are tryting to modify is beyond the limits of matrix " + nameMatrix);
      }
   }
   public void    Set_RowToValue(int indexRow, double value){
      if( indexRow >= 0 && matrix.length > indexRow ){
         for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
            int[] positionElement = {indexRow, indexColumn};
            Set_ElementToValue(positionElement, value);
         }
      }else{
         System.out.println("Error: The row you are tryting to modify is beyond the limits of matrix " + nameMatrix);
      }
   }
   public void    Set_ColumnToArray(int indexColumn, double[] values){      
      if( indexColumn >= 0 && matrix[0].length > indexColumn ){
         if(values != null && matrix[0].length>values.length){
            for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
               int[] positionElement = {indexRow, indexColumn};
               Set_ElementToValue(positionElement, values[indexRow]);
            }
         }else{
            System.out.println("Error: The array of values passed as parameter surpass the amount of columns in matrix "+ nameMatrix);
         }
      }else{
         System.out.println("Error: The column you are tryting to modify is beyond the limits of matrix "+nameMatrix);
      }
   }
   public void    Set_ColumnToValue(int indexColumn, double value){
      if( indexColumn >= 0 && matrix[0].length > indexColumn ){
         for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
            int[] positionElement = {indexRow, indexColumn};
            Set_ElementToValue(positionElement, value);
         }
      }else{
         System.out.println("Error: The column you are tryting to modify is beyond the limits of matrix "+nameMatrix);
      }
   }
     
   public double[] Get_Row(int indexRow){
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
   public double[] Get_Column(int indexColumn){
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


   //ELEMENTS
   public void    Set_ElementToInput(int[] position){
      String message = "Indicate entry (" + (position[0] + 1) + "," + (position[1] + 1) + "):";
      double value = Inputs.IsDouble(message);
      Set_ElementToValue(position, value);
   }
   public void    Set_ElementToValue(int[] position, double value){
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
   public double  GetElement(int[] position){
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
   
     
   //OPERATIONS
   public  void   ModifyMatrix_AddVectorTo_Column(int indexColumn, double[] array){
       if( array != null && matrix.length == array.length ){
          if(indexColumn >= 0 && matrix[0].length > indexColumn){
             double[] newColumn = Get_Column(indexColumn);
             //TODO: CHANGE FOR A VECTOR INSTANCE
             for (int index = 0; index < newColumn.length; index++) {
                newColumn[index] = newColumn[index] * array[index];
             }   
             Set_ColumnToArray(indexColumn, newColumn);
          }else{
             System.out.println("Error: The index given is not within the range of the matrix");
             System.out.println("In method ModifyMatrix_AddVectorTo_Column");
          }
       }else{
          System.out.println("Error: The given array does not have the adecuate length");
          System.out.println("In method ModifyMatrix_AddVectorTo_Column");
       }
 
    }
   public  void   ModifyMatrix_AddVectorTo_Row(int indexRow, double[] array){
      if( array != null && sizeMatrix[1] == array.length ){
         if(indexRow >= 0 && matrix.length > indexRow){
            double[] newRow = Get_Row(indexRow);
            for (int index = 0; index < newRow.length; index++) {
               newRow[index] = newRow[index] + array[index];
            }   
            Set_RowToArray(indexRow, newRow);
         }else{
            System.out.println("Error: The index given is not within the range of the matrix");
            System.out.println("In method ModifyMatrix_AddVectorTo_Row");
         }
      }else{
         System.out.println("Error: The given array does not have the adecuate length");
         System.out.println("In method ModifyMatrix_AddVectorTo_Row");
      }

   }
   public  void   ModifyMatrix_ColumnByScalar(int indexColumn, double scalar){
       if(indexColumn >= 0 && matrix[0].length > indexColumn){
          double[] newColumn = Get_Column(indexColumn);
          //TODO: CHANGE FOR A VECTOR INSTANCE
          for (int index = 0; index < newColumn.length; index++) {
             newColumn[index] = newColumn[index] * scalar;
          }   
          Set_ColumnToArray(indexColumn, newColumn);
       }else{
          System.out.println("Error: The index given is not within the range of the matrix");
          System.out.println("In method ModifyMatrix_ColumnByScalar");
       }
    }
   public  void   ModifyMatrix_RowByScalar(int indexRow, double scalar){
       if(indexRow >= 0 && matrix.length > indexRow){
          double[] newRow = Get_Row(indexRow);
          //TODO: CHANGE FOR A VECTOR INSTANCE
          for (int index = 0; index < newRow.length; index++) {
             newRow[index] = newRow[index] * scalar;
          }   
          Set_RowToArray(indexRow, newRow);
       }else{
          System.out.println("Error: The index given is not within the range of the matrix");
          System.out.println("In method ModifyMatrix_RowByScalar");
       }
    }
   public  void   ReaArrangeMatrix_GivenOrder(int[] array){
      if(array.length == sizeMatrix[0]){
         double[][] newMatrix = new double[sizeMatrix[0]][sizeMatrix[1]];
         int counter = 0;
         for (int indexRow : array) {
            newMatrix[counter] = Get_Row(indexRow);
            counter++;
         }
         Set_MatrixToMatrix(newMatrix);

      }else{
         System.out.println("Error: The given array does not match the matrix size");
         System.out.println("in method ReaArrangeMatrix_GivenOrder");
      }
   }
      
 
   //Inputs
   public void    Validate_HasColumnsZero(){
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

   //CONCLUSIONS
   public int     CheckConsistentsy(){
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
   
}
