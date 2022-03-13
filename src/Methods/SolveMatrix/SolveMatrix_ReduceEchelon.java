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
    
    private void    Reduce_Matrix(){
        matrix.ReduceMatrix_AllPivots();
    }
    private void    Set_MatrixStatus(){
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
            if(matrix.pivots.check_LinearlyIndependence(matrix.Get_CopyMatrix())){   
                String[][] content = {{"YES", "YES", "NO"}};  
                Printer.Table(header, categories, content, cellSize);
            }else{
                String[][] content = {{"YES", "NO", "YES"}};  
                Printer.Table(header, categories, content, cellSize);
            }
        }
        else{            
            String[][] content = {{"NO, because of row " + (inconsistentRow+1), "NA", "NA"}};  
            Printer.Table(header, categories, content, cellSize);
        }
        Printer.Title("End of the prorgam");
    }
    

    public Matrix Reduce(double[][] givenMatrix){
        matrix = new Matrix_ReduceEchelon("Matrix A", givenMatrix);
        Reduce_Matrix();
        Set_MatrixStatus();        
        int inconsistentRow = matrix.CheckConsistentsy();
        if(matrix.Get_IsConsistent())
            return matrix;
        else
            return null;
    }


}
