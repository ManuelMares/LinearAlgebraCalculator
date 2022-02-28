package Methods.SolveMatrix;
import Classes.*;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Matrix_ReduceEchelon;
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Methods.CreateMatrix.CreateMatrix;
import Classes.Utilities.Inputs;


public class SolveMatrix_ReduceEchelon {
    Recursion   recursion = new Recursion();
    Matrix_ReduceEchelon matrix;

    public void Main(){
        Create_Matrix();
        Set_StepByStep();
        Reduce_Matrix();
        Set_MatrixStatus();
        Conclusion();
    }

    private void    Create_Matrix(){
        Printer.Title2("Solve matrix  by reduction method");
        Printer.Subtitle2("Let's start by indicate the size of the matrix");
        double[][] entries = CreateMatrix.Free();
        matrix = new Matrix_ReduceEchelon("A", entries);
    }
    private void    Set_StepByStep() {
        Printer.Subtitle2("step by step options");
        String message = "Please indicate if you want a detailed Step-by-Step solution\n";
        boolean status = Inputs.YesNo(message);
        matrix.Set_StepByStep(status);
    }
    private void    Reduce_Matrix(){
        matrix.ReduceMatrix_AllPivots();
    }
    private void    Set_MatrixStatus(){
        matrix.Set_PivotsResults();
        matrix.CheckConsistentsy();
    }
    private void    Conclusion(){
        Printer.Subtitle("Conclusion");        
        int inconsistentRow = matrix.CheckConsistentsy();
        String header = "Matrix Status";
        String[] categories = {"It is Consistent", "It is Linearly Independent", "It is Linearly Dependent"};
        int cellSize = 31;
        
        Printer.Matrix(matrix.Get_CopyMatrix(), "\nReduce Echelon matrix:");
        if(matrix.Get_IsConsistent()){
            if(matrix.IsLinearlyIndependent()){   
                String[][] content = {{"YES", "YES", "NO"}};  
                Printer.Table(header, categories, content, cellSize);
            }else{
                String[][] content = {{"YES", "NO", "YES"}};  
                Printer.Table(header, categories, content, cellSize);
            }
            matrix.print_VariablesValues();
        }
        else{            
            String[][] content = {{"NO, because of row " + (inconsistentRow+1), "NA", "NA"}};  
            Printer.Table(header, categories, content, cellSize);
        }
        Printer.Title("End of the prorgam");
    }
    

    public Matrix Reduce(double[][] givenMatrix){
        matrix = new Matrix_ReduceEchelon("Matrix A", givenMatrix);
        Set_StepByStep();
        Reduce_Matrix();
        Set_MatrixStatus();        
        int inconsistentRow = matrix.CheckConsistentsy();
        if(matrix.Get_IsConsistent())
            return matrix;
        else
            return null;
    }


}
