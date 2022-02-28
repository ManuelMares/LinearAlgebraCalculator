package Methods.Inverse;
import Classes.*;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Matrix_Inverse;
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Methods.CreateMatrix.CreateMatrix;
import Classes.Utilities.Inputs;


public class InverseMatrix {
    Recursion   recursion = new Recursion();
    Matrix_Inverse matrix;

    public void Main(){
        Create_Matrix();
        Set_StepByStep();
        Reduce_Matrix();
        Conclusion();
    }

    

    private void    Create_Matrix(){
        Printer.Title2("Get Matrix Inverse");
        Printer.Subtitle2("Let's start by indicate the size of the matrix");
        double[][] entries = CreateMatrix.Square();
        matrix = new Matrix_Inverse("A", entries);
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
    private void    Conclusion(){
        Printer.Subtitle("Conclusion"); 
        if(matrix.IsValid()){
            String message ="The inverse matrix is";
            Printer.Matrix(matrix, message);
        }else{
            System.out.print("The given matrix DOES NOT HAVE an inverse matrix\n\n");
        }
        Printer.Title("End of the prorgam");
    }
    
    


    public Matrix Reduce(double[][] givenMatrix){
        matrix = new Matrix_Inverse("Matrix A", givenMatrix);
        if(MatrixIsSquare()){
            Set_StepByStep();
            Reduce_Matrix();      
            int inconsistentRow = matrix.CheckConsistentsy();
            if(matrix.Get_IsConsistent())
                return matrix;

        }
        return null;
    }
    public boolean MatrixIsSquare(){
        if(matrix.Get_SizeRows() == matrix.Get_SizeColumns())
            return true;
        return false;
    }

    
}