package GUI.Components.Tables;

import Classes.Matrix.AbstractClasses.Matrix;
import java.awt.Color;

public class MatrixUI extends Table {
    //Constructors
    public MatrixUI(Matrix matrix, String title){       
        Set_Layout(title);
        Set_TableParameters(matrix);
    }
    public MatrixUI(Matrix matrix, String title, Color color){         
        Set_Layout(title);
        Set_BackgroundColor(color);
        Set_TableParameters(matrix);
    }
    public MatrixUI(double[][] matrix, String title){      
        Set_Layout(title);        
        Set_TableParameters(matrix);
    }
    public MatrixUI(double[][] matrix, String title, Color color){      
        Set_Layout(title);   
        Set_BackgroundColor(color);  
        Set_TableParameters(matrix);
    }
    
    //Table properties    
    private void Set_TableParameters(Matrix matrix){        
        content    = Get_InputsStrings (matrix);
        categories = Get_Categories    (matrix.Get_SizeColumns());
        Set_Table();
    }
    private void Set_TableParameters(double[][] matrix){
        content    = Get_InputsStrings (matrix);
        categories = Get_Categories    (matrix[0].length);
        Set_Table();
    }
    
    private String[][]  Get_InputsStrings (Matrix matrix){
        String[][] matrixStrings = new String[matrix.Get_NumRows()][matrix.Get_NumColumns()];
        for (int indexRow = 0; indexRow < matrix.Get_NumRows(); indexRow++) {
            for (int indexColumn = 0; indexColumn < matrix.Get_NumColumns(); indexColumn++) {
                int[] position = {indexRow, indexColumn};
                double value = matrix.GetElement(position);
                if(value == 0)
                    matrixStrings[indexRow][indexColumn] = String.format("0.00");
                else
                    matrixStrings[indexRow][indexColumn] = String.format("%.2f", value);
            }
        }
        return matrixStrings;
    }
    private String[][]  Get_InputsStrings (double[][] matrix){
        int sizeRows = matrix.length;
        int sizeColumns = matrix[0].length;
        String[][] matrixStrings = new String[sizeRows][sizeColumns];
        for (int indexRow = 0; indexRow < sizeRows; indexRow++) {
            for (int indexColumn = 0; indexColumn < sizeColumns; indexColumn++) {
                double value = matrix[indexRow][indexColumn];
                if(value == 0)
                    matrixStrings[indexRow][indexColumn] = String.format("0.00");
                else
                    matrixStrings[indexRow][indexColumn] = String.format("%.2f", matrix[indexRow][indexColumn]);
                    
            }
        }
        return matrixStrings;
    }
    private String[]    Get_Categories(int numColumns){
        String[] titles = new String[numColumns];
        for (int index = 0; index < titles.length; index++) {
            if(index == titles.length - 1)
                titles[index] = "Results";
            else
                titles[index] = "X" + (index+1);
        }
        return titles;
    }

}
