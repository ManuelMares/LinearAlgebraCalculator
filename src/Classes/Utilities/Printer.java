package Classes.Utilities;
import Classes.Matrix.AbstractClasses.Matrix;

public class Printer {
    public static void     Matrix(double[][] matrix, String message){
        if(message.length() > 0)
            System.out.println(message);    
        for (double[] row : matrix) 
            Vector(row, "", "|");
            
        System.out.print("\n");  
    }
    public static void     Matrix(Matrix matrix, String message){
        if(message.length() > 0)
            System.out.println(message);  
        for (int indexRow = 0; indexRow < matrix.Get_SizeRows(); indexRow++) {
            double[] row = matrix.Get_Row(indexRow);
            Vector(row, "", "|");
        }
        System.out.print("\n");  
    }
    public static void     MatrixStatus(String header, String[] categories, String[][] content, int cellSize){
        Table(header, categories, content, cellSize);
    }
    public static void     Pivots(Pivots pivots){
        String header = "\n\nDetailed report of variables' status...";
        String[] categories = {"Name", "Position", "Coeficients", "Status"};
        int cellSize = 12;
        String[][] content = Get_PivotsRows(pivots, cellSize);
        Table(header, categories, content, cellSize);
    }
    private static String[][] Get_PivotsRows(Pivots pivots, int cellSize){
        String[][] content = new String[pivots.size][4];
        for (int index = 0; index < pivots.size; index++) {
            boolean isFree= pivots.GetPivot_isFree(index);
            int[]   position = pivots.GetPivot_Position(index);
            double  value = pivots.GetPivot_Value(index);

            String  stringName =pivots.GetPivot_Name(index);
            String  stringNa ="N/A";
            String  stringStatus = isFree ? "free": "Basic";
            String  stringPosition = "(" + (position[0]+1) +","+ (position[1]+1) + ")";
            String  stringValue = String.format("%.2f", value);

            if( isFree ){
                String[] cellRow = {stringName, stringNa, stringNa, stringStatus};
                content[index] = cellRow;
            }else{
                String[] cellRow = {stringName, stringPosition, stringValue, stringStatus};
                content[index] = cellRow;
            }
        }
        return content;
    }

    public static void     Title(String message){
        String simbol = "/";
        Times(simbol, 150);
        System.out.printf("\n%2s%100s%48s\n", "//", message.toUpperCase(), "//");
        Times(simbol, 150);
    }
    public static void     Title2(String message){
        String divisor = "======================================================";
        System.out.printf("%s%s%s\n", divisor,  message.toUpperCase(), divisor);
    }
    public static void     Subtitle(String message){
        String divisor = "-----------------------------------------------------------------";
        System.out.printf("%s%s%s\n", divisor, message, divisor);
    }
    public static void     Subtitle2(String message){
        String divisor = "~~~~~~~~~~~~~~~~";
        System.out.printf("%s%s%s\n", divisor, message, divisor);
    }


    public static void     Table(String header, String[] categories, String[][] content, int cellSize){
        System.out.println(header);
        Table_DivisorRow(cellSize, categories.length);
        Table_row(cellSize, categories);
        Table_DivisorRow(cellSize, categories.length);
        for (String[] cellRow : content) {
            Table_row(cellSize, cellRow);
        }
        Table_DivisorRow(cellSize, categories.length);
    }
    public static void     Table_DivisorRow(int lengthCell, int numCells){
        System.out.print("|");
        for (int index = 0; index < numCells; index++) {
            Times("-", lengthCell);
            System.out.print("|");
        }
        System.out.print("\n");
    }
    public static void     Table_row(int lengthCell, String[] content){
        System.out.print("|");
        for (String element : content) {
            String format = String.format("%-" + lengthCell + "s|", element);
            System.out.print(format);
        }
        System.out.print("\n");
    }
    
    public static void     Vector(double[] row){
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(double[] row, String message){
        if(message.length() > 0)
            System.out.println(message);
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(double[] row, String message, String holdingSimbol){
        if(message.length() > 0)
            System.out.println(message);
        System.out.printf("%s", holdingSimbol);
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.printf("   %s\n", holdingSimbol);
    }
    public static void     Vector(int[] row){
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(int[] row, String message){
        if(message.length() > 0)
            System.out.println(message);
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(int[] row, String message, String holdingSimbol){
        if(message.length() > 0)
            System.out.println(message);
        System.out.printf("%s", holdingSimbol);
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.printf("   %s\n", holdingSimbol);
    }
     
    public static void     Times(String string, int times){
        for (int index = 0; index < times; index++) {
            System.out.print(string);
        }
    }
}
