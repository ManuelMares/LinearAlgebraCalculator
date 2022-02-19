package Methods.Matrix.SolveMatrix;

import Classes.*;
import Variables.Dictionary;

public class SolveMatrix_ReduceEchelon {
    //IMPORTS
    static Validations validation = new Validations();
    static Matrix matrix;
    private boolean stepByStep;
    private boolean cancelledPivot =false; 

    public void Main(){
        matrix = new Matrix("Matrix A");
        CreateNewMatrix();
        SetStepByStep();
        PrepareMatrix();
        if(stepByStep){
            matrix.PrintPivots();
            Reduce_GaussJordan_SteByStep();
            if(cancelledPivot){
                System.out.println("\n\n======================== Extra steps ========================");
                System.out.println("The position of at least one pivot has changed. A new iteration of solutions needs to be executed.");
                PrepareMatrix();
                Reduce_GaussJordan();
                cancelledPivot = true;
            }
        }
        else{
            Reduce_GaussJordan();
            if(cancelledPivot){
                PrepareMatrix();
                Reduce_GaussJordan();
                cancelledPivot = true;
            }
        }
        System.out.println("======================== Conclusion ========================");
        matrix.SetPivotsResults();
        PrintConclusion();        
        System.out.println("======================== END OF THE PROGRAM ======================== \n\n\n");

        //iteration try for pivots
    }

    public Matrix ReduceGivenMatrix(double[][] GivenMatrix){
        matrix = new Matrix("New Matrix", GivenMatrix);
        PrepareMatrix();
        if(stepByStep){
            matrix.PrintPivots();
            Reduce_GaussJordan_SteByStep();
        }
        else
            Reduce_GaussJordan();             
        matrix.SetPivotsResults();;       
        PrintConclusion();
        return matrix;
    }

    public Matrix ReduceMatrix(){
        matrix = new Matrix("Matrix A");
        CreateNewMatrix();
        PrepareMatrix();
        if(stepByStep){
            matrix.PrintPivots();
            Reduce_GaussJordan_SteByStep();
        }
        else
            Reduce_GaussJordan();             
        matrix.SetPivotsResults();;       
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
        //matrix.PrintPivotsCompleteStatus();
        int inconsistentRow = matrix.CheckConsistentsy();

        if(matrix.isConsistent){
            if(matrix.IsLinearlyIndependent()){
                String topDivisor = "-------------------------------";
                System.out.printf("\n|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "It is Consistent", "It is Linearly Independent", "It is Linearly Dependent");
                System.out.printf("|%-31s|%-31s|%-31s|\n", topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "YES", "YES", "NO");
                System.out.printf("|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor);
            }else{
                String topDivisor = "-------------------------------";
                System.out.printf("\n|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "It is Consistent", "It is Linearly Independent", "It is Linearly Dependent");
                System.out.printf("|%-31s|%-31s|%-31s|\n", topDivisor, topDivisor, topDivisor);
                System.out.printf("|%-31s|%-31s|%-31s|\n", "YES", "NO", "YES");
                System.out.printf("|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor);
            }
            matrix.printVariablesValues();
            matrix.printVariables_ParametricForm();
        }
        else{            
            String topDivisor = "-------------------------------";
            System.out.printf("\n|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor);
            System.out.printf("|%-31s|%-31s|%-31s|\n", "It is Consistent", "It is Linearly Independent", "It is Linearly Dependent");
            System.out.printf("|%-31s|%-31s|%-31s|\n", topDivisor, topDivisor, topDivisor);
            System.out.printf("|%-31s|%-31s|%-31s|\n", "NO, because of row " + (inconsistentRow+1), "NA", "NA");
            System.out.printf("|%-31s-%-31s-%-31s|\n", topDivisor, topDivisor, topDivisor);
        }
    }

    private void PrepareMatrix(){
        matrix.DeleteRepetedRows();
        matrix.TryGetPivots();
    }

    private void Reduce_GaussJordan(){
        for (int indexVar = 0; indexVar < matrix.GetAmountPivots(); indexVar++){
            Pivots newPivot = matrix.GetPivot(indexVar);
            if(!newPivot.GetPivot_isFree(0))
                ReduceColumn(indexVar);            
        }
    }
    
    private void Reduce_GaussJordan_SteByStep(){
        System.out.println("======================== Solution step by step ========================");
        for (int indexVar = 0; indexVar < matrix.GetAmountPivots(); indexVar++) {            
            System.out.println("~~~~~~~~~~~~~~~ Step " +(indexVar + 1) +  " ~~~~~~~~~~~~~~~");
            Pivots newPivot = matrix.GetPivot(indexVar);
            if(!newPivot.GetPivot_isFree(0)){
                ReduceColumn_StepByStep(indexVar);
            }else{
                System.out.println("Pivot "+(indexVar + 1) + " is a free variable. Hence, there is nothing to do in this step. \n");
            }
        }
    }

    private void ReduceColumn_StepByStep(int indexVar){
        Pivots newPivot = matrix.GetPivot(indexVar);
        double value = matrix.GetElement(newPivot.GetPivot_Position(0));
        if(newPivot.size > 0 &&  Math.abs(value) > 0.00001 ){
            String varName = "X"+ (indexVar + 1);
            String position = "(" + (newPivot.GetPivot_Position(0)[0] +1) + "," + (newPivot.GetPivot_Position(0)[1]+1) + ")";

            System.out.println("Pivot "+ varName + ",    Position = " + position+ ",   Value = " + value);
            matrix.usePivotTo_ClearColumn(varName);
            System.out.println("\n");
        }else{
            System.out.println("Pivot "+(indexVar + 1) + " has turn into a zero, so no operation is executed in this step \n");
            cancelledPivot = true;
        }
    }

    private void ReduceColumn(int indexVar){
        Pivots newPivot = matrix.GetPivot(indexVar);
        double value = matrix.GetElement(newPivot.GetPivot_Position(0));
        if(newPivot.size > 0 &&  Math.abs(value) > 0.00001){
            String varName = "X"+ (indexVar + 1);
            matrix.usePivotTo_ClearColumn(varName);
        }else{            
            cancelledPivot = true;
        }
    }

}
