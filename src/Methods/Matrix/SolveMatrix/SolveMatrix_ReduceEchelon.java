package Methods.Matrix.SolveMatrix;

import Classes.Matrix;
import Classes.Validations;
import Variables.Dictionary;

public class SolveMatrix_ReduceEchelon {
    //IMPORTS
    static Validations validation = new Validations();
    static Matrix matrix = new Matrix("Matrix A");

    public void Main(){
        System.out.println("\n\n\nLet's start by indicate the size of the matrix");
        CreateNewMatrix();
        PrepareMatrix();
        Reduce_GaussJordan();
        System.out.println("======================== Conclusion ========================");
        PrintConclusion();

        //iteration try for pivots
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
        matrix.PrintPivots();
    }

    private void Reduce_GaussJordan(){
        System.out.println("======================== Solution step by step ========================");
        for (int indexVar = 0; indexVar < matrix.pivots.size; indexVar++) {            
            System.out.println("~~~~~~~~~~~~~~~ Step " +(indexVar + 1) +  " ~~~~~~~~~~~~~~~");
            if(!matrix.pivots.areFree[indexVar]){
                ReduceColumn(indexVar);
            }else{
                System.out.println("Pivot "+(indexVar + 1) + " is a free variable. Hence, there is nothing to do in this step. \n");
            }
        }
    }

    private void ReduceColumn(int indexVar){
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

    public void CreateNewMatrix(){
        int AmountRows = -1;
        while(AmountRows < 2){
            System.out.println("Please indicate the amount of rows in the matrix");
            AmountRows = validation.validateInput_isInt();
        }
        int AmountColumns = -1;
        while(AmountColumns < 2){
            System.out.println("Please indicate the amount of columns in the matrix");
            AmountColumns = validation.validateInput_isInt();
        }
        int[] size = {AmountRows, AmountColumns};
        matrix.SetSize(size);
        matrix.PrintMatrix("\nThe matrix input is:\n");
        matrix.setMatrix_Inputs();
        matrix.PrintMatrix("\nThe matrix input is:");
    }
    
}
