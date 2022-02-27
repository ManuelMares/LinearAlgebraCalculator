package Methods.CreateMatrix;
import Classes.Utilities.Printer;
import Classes.Utilities.Inputs;

public class CreateMatrix{

    public static double[][] Free(){
        double[][] matrix;
        
        int AmountRows = Inputs.BiggerThan("Please indicate the amount of rows in the matrix", 2);
        int AmountColumns = Inputs.BiggerThan("Please indicate the amount of columns in the matrix", 2);
        matrix = new double[AmountRows][AmountColumns];
        GetValues(matrix);

        String message = "The given matrix is:";
        Printer.Matrix(matrix, message);
        return matrix;
    }

    public static double[][] Square(){
        double[][] matrix;
        
        int size = Inputs.BiggerThan("Please indicate the size of the SQUARE matrix", 2);
        matrix = new double[size][size];
        GetValues(matrix);
        
        String message = "The given matrix is:";
        Printer.Matrix(matrix, message);
        return matrix;
    }

    public static void GetValues(double[][] matrix){
        for (int indexRow = 0; indexRow < matrix.length; indexRow++) {            
            for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
                String message = "Indicate Element (" + (indexRow + 1) + "," + (indexColumn + 1) +"):";
                matrix[indexRow][indexColumn] = Inputs.IsDouble(message);                
            }
        }
    }
    
}
