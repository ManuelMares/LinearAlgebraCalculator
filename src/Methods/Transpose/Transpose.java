package Methods.Transpose;

import Classes.Matrix.Matrix_Transpose;
import Classes.Utilities.Printer;
import Methods.Controller.GetMatrix;

public class Transpose {
    Matrix_Transpose matrix_Transpose;

    public void Main(){
        Create_Matrix();
        Transpose_Matrix();
        Conclusion();
    }    
    
    private void Transpose_Matrix(){
        matrix_Transpose.Set_Transpose();
    }
    private void    Create_Matrix(){
        Printer.Title2("Get Transpose");
        Printer.Subtitle2("Let's start by indicate the size of the matrix");
        //double[][] inputs = CreateMatrix.Free();
        //matrix_Transpose = new Matrix_Transpose("A", inputs);
    }
    private void    Conclusion(){
        Printer.Subtitle("Conclusion"); 
        
        String message ="The transpose matrix is";
        Printer.Matrix(matrix_Transpose.Get_Transpose(), message);

        Printer.Title("End of the prorgam");
    }

    public double[][] Get_Transpose(){
        Transpose_Matrix();
        double[][] newMatrix = matrix_Transpose.Get_Transpose();
        String message ="The transpose matrix is";
        Printer.Matrix(newMatrix, message);

        return newMatrix;
    }
}
