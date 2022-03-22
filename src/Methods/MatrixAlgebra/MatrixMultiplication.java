package Methods.MatrixAlgebra;
import Classes.*;
import Classes.Matrix.Matrix_Simple;
import Classes.Utilities.Inputs;
import Classes.Utilities.Printer;
import Classes.Utilities.Vector;
import Methods.Controller.GetMatrix;

public class MatrixMultiplication {
    Matrix_Simple matrix1;
    Matrix_Simple matrix2;
    
    Matrix_Simple resultMatrix;


    
    public void Main(){
        CreateMatrices();
        if(CheckCompatibility()){
            SetResultMatrixToZero();
            Multiply_Matrices();
            PrintResult();
        }else{
            PrintNotCompatible();
        }
    }    


    private void    CreateMatrices() {
        String message = "Create new Matrix A";
        Printer.Title2(message);
        //double[][] values1 = CreateMatrix.Free();
        //matrix1 = new Matrix_Simple("A", values1);
        
        message = "Create new Matrix B";
        Printer.Title2(message);
        //double[][] values2 = CreateMatrix.Free();
        //matrix2 = new Matrix_Simple("B", values2);
    }
    private boolean CheckCompatibility(){
        if(matrix1.Get_SizeColumns() == matrix2.Get_SizeRows())
            return true;
        return false;
    }
    private void    SetResultMatrixToZero(){
        double[][] m = new double[matrix1.Get_SizeRows()][matrix2.Get_SizeColumns()];
        resultMatrix = new Matrix_Simple("Result", m);
    }
    private void    Multiply_Matrices(){
        for (int index_m1Row = 0; index_m1Row < matrix1.Get_SizeRows(); index_m1Row++) {
            for (int index_m2Column = 0; index_m2Column < matrix2.Get_SizeColumns(); index_m2Column++){
                SetMatrixResultPosition(index_m1Row, index_m2Column);
            }
        }


    }
    private void    SetMatrixResultPosition(int index_m1Row, int index_m2Column){        
        double[] m1_Row = matrix1.Get_Row(index_m1Row);
        double[] m2_Column = matrix2.Get_Column(index_m2Column);
        int[] position = {index_m1Row, index_m2Column};        
        double result = Vector.ByVector(m1_Row, m2_Column);
        
        resultMatrix.Set_ElementToValue(position, result); 
    }
    private void    PrintResult() {
        String message = "The resultant matrix (" +resultMatrix.Get_SizeRows() + ","+ resultMatrix.Get_SizeColumns() +") is";
        Printer.Matrix(resultMatrix, message);
    }
    private void    PrintNotCompatible() {
        String message = "The given matrices are not compatible";
        Printer.Title2(message);

        System.out.println("Remember, for a multiplication of matrices to be define:\n     the number of columns in the first matrix hast to match the number of rows in the second matrix.");
        
        System.out.println("Matrix A Size: (" + matrix1.Get_SizeRows() + "," + matrix2.Get_SizeColumns() + ").");
        System.out.println("Matrix B Size: (" + matrix2.Get_SizeRows() + "," + matrix2.Get_SizeColumns() + ").");
    }


    public Matrix_Simple Multiply_GivenMatrices(double[][] m1, double[][] m2){        
        matrix1 = new Matrix_Simple("A", m1);
        matrix2 = new Matrix_Simple("B", m2);        
        if(CheckCompatibility()){
            SetResultMatrixToZero();
            Multiply_Matrices();
            return resultMatrix;
        }else{
            PrintNotCompatible();
        }
        return null;
    }


}