package Methods.Matrix.SolveMatrix;

import Classes.Matrix;
import Classes.Validations;
import Variables.Dictionary;

public class SolveMatrix_ReduceEchelon {
    //IMPORTS
    static Validations validation = new Validations();
    static Matrix matrix;
    private boolean stepByStep;

    public void Main(){
        matrix = new Matrix("Matrix A");
        CreateNewMatrix();
        SetStepByStep();
        PrepareMatrix();
        if(stepByStep){
            matrix.PrintPivots();
            Reduce_GaussJordan_SteByStep();
        }
        else
            Reduce_GaussJordan();        
        System.out.println("======================== Conclusion ========================");
        matrix.SetPivotsResults();
        PrintConclusion();        
        matrix.pivots.PrintPivots();
        matrix.pivots.PrintPivots_CompleteStatus();
        System.out.println("======================== END OF THE PROGRAM ======================== \n\n\n");

        //iteration try for pivots
    }

    public Matrix ReduceMatrix(double[][] GivenMatrix){
        matrix = new Matrix("New Matrix", GivenMatrix);
        PrepareMatrix();
        if(stepByStep){
            matrix.PrintPivots();
            Reduce_GaussJordan_SteByStep();
        }
        else
            Reduce_GaussJordan(); 
            
        matrix.TryGetPivots();       
        PrintConclusion();
        return matrix;
    }

    public void CreateNewMatrix(){
        System.out.println("\n\n\nLet's start by indicate the size of the matrix");
        int AmountRows = validation.validateInput_BiggerThan("Please indicate the amount of rows in the matrix", 2);
        int AmountColumns = validation.validateInput_BiggerThan("Please indicate the amount of columns in the matrix", 2);
        int[] size = {AmountRows, AmountColumns};
        matrix.SetSize(size);
        matrix.PrintMatrix("\nThe matrix input is:\n");
        matrix.setMatrix_Inputs();
        matrix.PrintMatrix("\nThe matrix input is:");
    }
    
    private void SetStepByStep() {
        System.out.println("\n\n\nPlease indicate if you want a detailed Step-by-Step solution");
        boolean status = validation.validateInput_YesNo();
        matrix.SetStepByStep(status);
        stepByStep = status;
    }

    private void PrintConclusion(){
        matrix.PrintMatrix("\nReduce Echelon matrix:");
        int inconsistentRow = matrix.CheckConsistentsy();
        boolean isConsistent = (inconsistentRow == 0) ? true: false;
        boolean isLI = matrix.IsLinearlyIndependent();

        //if consistent, print variables
        //               and parametric form

        if(matrix.isConsistent){
            if(matrix.IsLinearlyIndependent()){
                String topDivisor = "-------------------------------";
                System.out.printf("\n|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "It is Consistent", "It is Linearly Independent", "It is Linearly Dependent");
                System.out.printf("|%-31s|%-31s|%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "YES", "YES", "NO");
                System.out.printf("|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
            }else{
                String topDivisor = "---------------------------";
                System.out.printf("\n|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "It is Consistent", "It is Linearly Independent", "It is Linearly Dependent");
                System.out.printf("|%-31s|%-31s|%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "YES", "NO", "YES");
                System.out.printf("|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
            }
            matrix.printVariablesValues();
            matrix.printVariables_ParametricForm();
        }
        else{            
            String topDivisor = "---------------------------";
            System.out.printf("\n|%-30s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
            System.out.printf("|%-30s|%-31s|%-31s|\n", "It is Consistent", "It is Linearly Independent", "It is Linearly Dependent");
            System.out.printf("|%-30s|%-31s|%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
            System.out.printf("|%-30s|%-31s|%-31s|\n", "NO", "NA", "NA");
            System.out.printf("|%-30s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
        }
    }

    private void PrepareMatrix(){
        matrix.DeleteRepetedRows();
        matrix.TryGetPivots();
    }

    private void Reduce_GaussJordan(){
        for (int indexVar = 0; indexVar < matrix.pivots.size; indexVar++){
            if(!matrix.pivots.areFree[indexVar])
                ReduceColumn(indexVar);            
        }
    }
    
    private void Reduce_GaussJordan_SteByStep(){
        System.out.println("======================== Solution step by step ========================");
        for (int indexVar = 0; indexVar < matrix.pivots.size; indexVar++) {            
            System.out.println("~~~~~~~~~~~~~~~ Step " +(indexVar + 1) +  " ~~~~~~~~~~~~~~~");
            if(!matrix.pivots.areFree[indexVar]){
                ReduceColumn_StepByStep(indexVar);
            }else{
                System.out.println("Pivot "+(indexVar + 1) + " is a free variable. Hence, there is nothing to do in this step. \n");
            }
        }
    }

    private void ReduceColumn_StepByStep(int indexVar){
        if(matrix.GetElement(matrix.pivots.positions[indexVar]) != 0){
            String varName = "X"+ (indexVar + 1);
            String position = "(" + (matrix.pivots.positions[indexVar][0]+1) + "," + (matrix.pivots.positions[indexVar][1]+1) + ")";
            double value = matrix.GetElement(matrix.pivots.positions[indexVar]);

            System.out.println("Pivot "+ varName + ",    Position = " + position+ ",   Value = " + value);
            matrix.usePivotTo_ClearColumn(varName);
            System.out.println("\n");
        }else{
            System.out.println("Pivot "+(indexVar + 1) + " has turn into a zero, so no operation is executed in this step \n");
        }
    }

    private void ReduceColumn(int indexVar){
        if(matrix.GetElement(matrix.pivots.positions[indexVar]) != 0){
            String varName = "X"+ (indexVar + 1);
            matrix.usePivotTo_ClearColumn(varName);
        }
    }

}
