package Methods.Matrix.SolveMatrix;
import Classes.*;
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Inputs;
import Variables.Dictionary;


public class SolveMatrix_ReduceEchelon {
    //IMPORTS
    static  Inputs      input = new Inputs();
    static  Recursion   recursion = new Recursion();
    static  Printer     printer = new Printer();
    static  Vector      vector = new Vector();
    static  Matrix_ReduceEchelon matrix;

    public void Main(){
        matrix = new Matrix_ReduceEchelon("Matrix A");
        Create_Matrix();
        Set_StepByStep();
        Reduce_Matrix();
        Set_MatrixStatus();
        Conclusion();
    }

    private void    Create_Matrix(){
        printer.Title("Solve matrix  by reduction method");
        printer.Subtitle2("Let's start by indicate the size of the matrix");
        int AmountRows = input.BiggerThan("Please indicate the amount of rows in the matrix", 2);
        int AmountColumns = input.BiggerThan("Please indicate the amount of columns in the matrix", 2);
        int[] size = {AmountRows, AmountColumns};
        matrix.Set_Size(size);
        printer.Matrix(matrix.Get_CopyMatrix(), "\nThe matrix base is:");
        matrix.Set_MatrixInputs();
        printer.Matrix(matrix.Get_CopyMatrix(), "\nThe input Matrix is:");
    }
    private void    Set_StepByStep() {
        printer.Subtitle2("step by step options");
        String message = "Please indicate if you want a detailed Step-by-Step solution\n";
        boolean status = input.YesNo(message);
        matrix.Set_StepByStep(status);
    }
    private void    Reduce_Matrix(){
        matrix.ReduceMatrix_AllPivots();
    }
    private void    Conclusion(){
        System.out.print("======================== Conclusion ========================");        
        int inconsistentRow = matrix.CheckConsistentsy();
        String header = "Matrix Status";
        String[] categories = {"It is Consistent", "It is Linearly Independent", "It is Linearly Dependent"};
        int cellSize = 31;
        
        printer.Matrix(matrix.Get_CopyMatrix(), "\nReduce Echelon matrix:");
        if(matrix.Get_IsConsistent()){
            if(matrix.IsLinearlyIndependent()){   
                String[][] content = {{"YES", "YES", "NO"}};  
                printer.Table(header, categories, content, cellSize);
            }else{
                String[][] content = {{"YES", "NO", "YES"}};  
                printer.Table(header, categories, content, cellSize);
            }
            matrix.print_VariablesValues();
        }
        else{            
            String[][] content = {{"NO, because of row " + (inconsistentRow+1), "NA", "NA"}};  
            printer.Table(header, categories, content, cellSize);
        }

        System.out.print("======================== END OF THE PROGRAM ======================== \n\n\n");
    }
    private void    Set_MatrixStatus(){
        matrix.Set_PivotsResults();
        matrix.CheckConsistentsy();
    }
   
}
