package Methods.Matrix.SolveMatrix;

import Shared.MatrixMethods;
import Shared.Validations;
import Variables.Dictionary;

public class SolveMatrix_ReduceEchelon {
    //IMPORTS
    static Validations validation = new Validations();
    static MatrixMethods matrixMethods = new MatrixMethods();

    public void Main(){
        double[][] matrix = getValidMatrix();
        
        System.out.println("------------SOLUTION STEP BY STEP:------------"); 
        double[][] solvedMatrix = Reduce_StepByStep(matrix); 
     
        
        System.out.println("\n\n------------CONCLUSION:------------\n"); 
        int amountPivots = matrixMethods.AugmentedMatrix_GetMaxNumberPivots(matrix.length, matrix[0].length);    
        Dictionary variablesValues = GetVariablesValues(solvedMatrix, amountPivots);
        PrintConclusions(solvedMatrix, amountPivots, variablesValues);
    }
    
    private void PrintConclusions(double[][] matrix, int amountPivots, Dictionary variablesValues) {
        //There are 4 options
        //1. Extra columns -->  free variables
        //2. Row with 0=0 --> Ignore it
        //3. Row with 0=cte --> Inconsisten system
        //4. all columns and rows have non zeros coeficients and values, then that is the unique solution

        //the third case has to be checked in every alternative, since it is independet of the amount of pivots or size of the matrix.

        //THE SYSTEM CAN HAVE EXTRA ROWS OR EXTRA COLUMNS, BUT NOT BOTH.
        
        int index_InconsistentRow = CheckConsistency(matrix);
        if(index_InconsistentRow >=0){
            System.out.print("A equation of the form 0=cte has been located in the matrix (row "+(index_InconsistentRow+1)+")."); 
            System.out.println("Hence the whole system is: \nINCONSISTNTE\n\n");
        }else{
            int[] freeVariables_Pivots = ListPivots_AreFreeVariables(matrix, variablesValues);
            if(freeVariables_Pivots != null && freeVariables_Pivots.length > 0){
                int basicVariables_Reduced = amountPivots - freeVariables_Pivots.length;
                PrintVariablesValues(matrix, basicVariables_Reduced, freeVariables_Pivots);
                PrintFreVariables(matrix, basicVariables_Reduced, freeVariables_Pivots);
            }else{
                PrintVariablesValues(matrix, amountPivots, freeVariables_Pivots);
                PrintFreVariables(matrix, amountPivots, freeVariables_Pivots);
            }
        }


    }

    private int[] ListPivots_AreFreeVariables(double[][] matrix, Dictionary variablesValues) {
        int[] listOfFreeVariables = new int[0];
        for (int indexColumn = 0; indexColumn < matrix[0].length -1; indexColumn++) {
            double[] column = matrixMethods.GetColumnFromMatrix(matrix, indexColumn);
            boolean isZero = matrixMethods.Array_isZero(column);
            if (isZero) {
                listOfFreeVariables = matrixMethods.Vector_AddElement(listOfFreeVariables, indexColumn);
            }
        }
        if(listOfFreeVariables != null && listOfFreeVariables.length >= 1){
            return listOfFreeVariables;
        }else{
            listOfFreeVariables = new int[0];
            for (int index = 0; index < (variablesValues.Size()); index++) {
                double[] zeroRow = new double[matrix[0].length];
                boolean areEqual = java.util.Arrays.equals(matrix[index], zeroRow);
                if(areEqual){
                    listOfFreeVariables = matrixMethods.Vector_AddElement(listOfFreeVariables, index);
                }
            }
            return listOfFreeVariables;
        }
    }

    private void PrintFreVariables(double[][] matrix, int amountPivots, int[] freeVariables_Pivots) {
        int amountFreeVariables = matrix[0].length - 1 - amountPivots;
        if(amountFreeVariables > 0){
            System.out.println("The follow list shows the Free Variables");
            System.out.print("{ ");
            for (int variable = 0; variable < amountFreeVariables; variable++) {
                if(freeVariables_Pivots != null && freeVariables_Pivots.length >= 1
                    && freeVariables_Pivots.length == (variable + 1)){
                        System.out.print("X"+ ( freeVariables_Pivots[variable] + 1 )+ ", ");
                }else{
                    System.out.print("X"+ ( amountPivots + variable + 1 ) + ", ");
                }
            }
            System.out.print("}\n");
        }
    }

    private int CheckConsistency(double[][] matrix) {
        int inconsistentIndex = -1;  
        int counterRows = 0;      
        for (double[] row : matrix) {
            boolean rowFinished = false;
            int index = -1;
            int counterColumns = 0;
            while (!rowFinished) {
                if (row[counterColumns] != 0) {
                    index = counterColumns;
                    rowFinished = true;
                }
                if(counterColumns == (row.length -1 )){
                    rowFinished = true;
                }
                counterColumns++;
            }
            if(index == ( row.length - 1 )){
                inconsistentIndex = counterRows;
            }
            counterRows++;
        }
        return inconsistentIndex;
    }

    private void PrintVariablesValues(double[][] matrix, int amountPivots, int[] freeVariables_Pivots){
        //Its going to look for a pivot in each column < amountPivots
        //Every column is either zero, or has a unique non-zero value that is its pivot
        System.out.println("The value of the variables are:");
        int[] pivotIndices = matrixMethods.Matrix_GetPivotIndices_RandomTries(matrix);
        if(pivotIndices != null & pivotIndices.length >= 1){
            int counterVAriable = 1;
            int indexColumn = 0;
            for (int pivotIndex : pivotIndices) {
                if(pivotIndex >= 0 ){
                    System.out.print("X" + ( indexColumn + 1) + " : ");
                    System.out.print(matrix[indexColumn][matrix[0].length - 1]);
                    //this for goes from after the pivot  till second to last element ( < [].length -1)
                    int counterFreeVar = 1;
                    for (int freeVar = (amountPivots); freeVar < (matrix[0].length-1); freeVar++) {
                        //the if checks if a pivot is a free variable. Other wise it start naming F.V with an index bigger than the amoun of pivots.
                        if(freeVariables_Pivots != null && freeVariables_Pivots.length >= 1 && 
                        freeVariables_Pivots.length == counterFreeVar){
                            System.out.print(" + " + (-matrix[indexColumn][freeVar]) + "X" + (counterFreeVar+1));
                        }else{
                            System.out.print(" + " + (-matrix[indexColumn][freeVar]) + "X" + (freeVar+1));
                        }
                        counterFreeVar++;
                    }
                    System.out.print("\n");

                    //the counter does not increase if there is not a valid pivot
                    counterVAriable++;
                }
                indexColumn++;
            }
        }
        
    }

    private Dictionary GetVariablesValues(double[][] matrix, int amountPivots) {    
        Dictionary variablesValues = new Dictionary();
        for (int counter = 0; counter < amountPivots; counter++) {
            double[] pair = new double[2];
            pair[0]= matrix[counter][counter];
            pair[1]= matrix[counter][matrix[0].length - 1];
            variablesValues.AddElement(pair);
        }
        return variablesValues;
    }

    public double[][] Reduce_StepByStep(double[][] matrix){    
        //first the matrix is reordered to best find the pivot values
        double[][] orderedMatrix = reArragenMatrix(matrix);  
        matrixMethods.PrintMatrix("The rows were rearanged to find the pivots:", orderedMatrix);   

        //Now the program gets as many pivots as possible
        int[] newlistPivots = matrixMethods.Matrix_GetPivotIndices_RandomTries(orderedMatrix);
        matrixMethods.PrintArray(newlistPivots, "Following the column order, the pivots are in the rows:");

        //
        double[][] newMatrix = reduceMatrix_GaussJordan(orderedMatrix, newlistPivots);
        matrixMethods.PrintMatrix("\n\nThe final matrix is:", newMatrix);

        return newMatrix;

    }

    public double[][] reArragenMatrix(double[][] matrix){
        //Gets the pivot of as many columns as posible
        int[] zeroColumns = matrixMethods.Matrix_CheckForZeroColumn(matrix);
        //Reorder the rows in the matrix so the pivots are located in the main diagonal.
        int[] listPivots = matrixMethods.Matrix_GetPivotIndices_RandomTries(matrix);
        double[][] orderedMatrix = matrixMethods.ReorderMatrixRows(matrix, listPivots);
        if(zeroColumns != null && zeroColumns.length >= 0){
            System.out.print("Existence of zero columns was detected\n");            
        }else{
        }
        
        return orderedMatrix;
    }

    public double[][] getValidMatrix(){
        boolean isValid = false;
        double[][] matrix = {};
        while ( !isValid ) {
            System.out.println("You have chosen to solve a matrix. \n First you need to define a matrix.");
            matrix = matrixMethods.Matrix_GetNew("A");
            System.out.println("You have defined the next matrix:");
            matrixMethods.PrintMatrix("A", matrix);
            System.out.println("Please confirm the Matrix by typing [Y]/[y], or press [N]/[n] to re-enter the Matrix's values");
            isValid = validation.validateInput_YesNo();
        }
        return matrix;
    }

    public double[][] reduceMatrix_GaussJordan(double[][] matrix, int[] indecesPivots){
        int indexColumn = 0;
        double[][] newMatrix = matrixMethods.CopyMatrix(matrix);
        for (int indexPivot : indecesPivots) {
            if(indexPivot < 0){
                System.out.println("\n~~~~~~~~~~~~~~Step "+(indexColumn+1)+"~~~~~~~~~~~~~~");   
                System.out.println("This column has no pivot. No operation is made in this step");   
            }else if(newMatrix[indexPivot][indexColumn] == 0 ){
                System.out.println("\n~~~~~~~~~~~~~~Step "+(indexColumn+1)+"~~~~~~~~~~~~~~");   
                System.out.println("Pivot position: ("+(indexPivot+1)+","+(indexColumn+1)+")"); 
                System.out.println("Pivot value: ("+newMatrix[indexPivot][indexColumn]+")"); 
                System.out.println("This pivot value is 0, thus no operation is done in this step");  
            }else{
                System.out.println("\n~~~~~~~~~~~~~~Step "+(indexColumn+1)+"~~~~~~~~~~~~~~");   
                double[] unitarianRow = matrixMethods.getUnitarianRow(newMatrix, indexColumn, indexPivot);
                System.out.println("Pivot position: ("+(indexPivot+1)+","+(indexColumn+1)+")"); 
                System.out.println("Pivot value: ("+newMatrix[indexPivot][indexColumn]+")"); 
                System.out.println("Then, divinding row "+(indexPivot+1)+" by "+newMatrix[indexPivot][indexColumn]); 
                matrixMethods.PrintArray(unitarianRow, "results in the new unitarian row:");
                newMatrix = matrix_ClearColumnWithPivot(newMatrix, unitarianRow, unitarianRow[indexColumn], indexColumn, indexPivot);
                newMatrix[indexPivot] = unitarianRow;
                matrixMethods.PrintMatrix("\nResults in:", newMatrix);
            }
            indexColumn++;
        }
        return newMatrix;
    }

    private double[][] matrix_ClearColumnWithPivot(double[][] matrix, double[] unitarianRow, double pivotValue, int indexColumn, int indexPivot) {
        double[][] newMatrix = matrixMethods.CopyMatrix(matrix);
        int counterRow = 0;
        System.out.println(" "); 
        for (double[] row : newMatrix) {
            double negativeScalar = -1*newMatrix[counterRow][indexColumn];            
            if(indexPivot != counterRow && negativeScalar !=0 && pivotValue != 0){
                System.out.println("*Instruction: Multiplying the unitarian row by " + negativeScalar +" and adding it to row "+(counterRow+1)); 
                newMatrix = AddRowsTogether(newMatrix, unitarianRow, counterRow, negativeScalar);
            }
            counterRow++;
        }
        return newMatrix;
    }

    public double[][] AddRowsTogether(double[][] matrix, double[] unitarianRow, int index_RowToModify, double negativeScalar ){
        double[] unitarianRow_Modified = matrixMethods.vectorByScalar(unitarianRow, negativeScalar);
        matrix[index_RowToModify] = matrixMethods.Vector_addTogether(unitarianRow_Modified, matrix[index_RowToModify]);
        return matrix;
    }

    
}
