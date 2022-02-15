package Classes;

public class Pivots{
    public String[] names;
    public int[][] positions;
    public double[] values;
    public double[] results;
    public boolean[] areFree;
    public int size;

    public Pivots(){
        names = new String[0];
        positions = new int[0][0];
        values = new double[0];
        results = new double[0];
        areFree = new boolean[0];
        size = 0;
    }

    public void SetPivotsToZero(){
        names = new String[0];
        positions = new int[0][0];
        values = new double[0];
        areFree = new boolean[0];
        size = 0;
    }

    public void AddFreeVariable(String name){
        int[] position = {-1, -1};        
        AddPivot(name, position, 0, true);
    }

    public void AddPivot(String name, int[] position, double value, boolean isFree){
        String[] newNames = new String[1];
        int[][] newPositions = new int[1][2];
        double[] newValues = new double[1];
        boolean[] newAreFree = new boolean[1];
        int counter = 0;

        //this if is equal if(names != null && names.length > 1) = if(size > 0)
        boolean isnull = (names == null) ? true : false; 
        int length = names.length;
        if(names != null && names.length > 0 && size > 0){
            newNames = new String[names.length + 1];
            newPositions = new int[positions.length + 1][2];
            newValues = new double[values.length + 1];
            newAreFree = new boolean[areFree.length + 1];
            for (int index = 0; index < size; index++) {
                newNames[counter] = names[counter];
                newPositions[counter][0] = positions[counter][0];
                newPositions[counter][1] = positions[counter][1];
                newValues[counter] = values[counter];
                newAreFree[counter] = areFree[counter];
                counter++;
            }
        }

        newNames[counter] = name;
        newPositions[counter][0] = position[0];
        newPositions[counter][1] = position[1];
        newValues[counter] = value;
        newAreFree[counter] = isFree;

        names = newNames;
        positions = newPositions;
        values = newValues;
        areFree = newAreFree;

        size++;
    }
    
    public void DeletePivot(int indexDelete){
        String[] newNames = new String[names.length - 1];
        int[][] newPositions = new int[positions.length - 1][2];
        double[] newValues = new double[values.length - 1];
        boolean[] newAreFree = new boolean[areFree.length - 1];

        int counter = 0;
        for (int index = 0; index < newNames.length; index++) {
            if(index != indexDelete){
                newNames[counter] = names[counter];
                newPositions[counter][0] = positions[counter][0];
                newPositions[counter][1] = positions[counter][1];
                newValues[counter] = values[counter];
                newAreFree[counter] = areFree[counter];
                counter++;
            }
        }

        names = newNames;
        positions = newPositions;
        values = newValues;
        areFree = newAreFree;

        size--;
    }

    public void UpdatePivot_Name(){}
    public void UpdatePivot_Position(){}
    public void UpdatePivot_Value(){}
    public void UpdatePivot_isFree(){}

    
    public String GetPivot_Name(String name){
        for (int index = 0; index < size; index++) {
            if(names[index].equals(name)){
                return names[index];
            }
        }
        return " ";
    }
    public int[] GetPivot_Position(String name){
        int[] position = {-1, -1};
        for (int index = 0; index < size; index++) {
            if(names[index].equals(name)){
                position = positions[index];
                return position;
            }
        }
        return position;
    }
    public double GetPivot_Value(String name){
        double value = 0;
        for (int index = 0; index < size; index++) {
            if(names[index].equals(name)){
                value = values[index];
                return value;
            }
        }
        return value;
    }
    public boolean GetPivot_isFree(String name){
        boolean isFree = false;
        for (int index = 0; index < size; index++) {
            if(names[index].equals(name)){
                isFree = areFree[index];
                return isFree;
            }
        }
        return isFree;
    }

    public boolean Validate_ColumnHasPivot(int indexColumn){
        //If there is any element in positions whose value [1] (columns) is equal to the parameter, then that column has already a pivot
        for (int[] position: positions) {
            if(position[1] == indexColumn)
                return true;
        }
        return false;
    }
    
    public boolean Validate_RowHasPivot(int indexRow){
        //If there is any element in positions whose value [0] (rows) is equal to the parameter, then that row has already a pivot
        for (int[] position: positions) {
            if(position[0] == indexRow)
                return true;
        }
        return false;
    }

    public void PrintPivots(){
        String topDivisor = "------------";
        System.out.println("\n\nDetailed report of variables' status....");
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|\n", "Name", "Position", "Coeficients", "Status");
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|\n", topDivisor, topDivisor, topDivisor, topDivisor);
        for (int index = 0; index < names.length; index++) {
            if(areFree[index]){
                String name =names[index];
                String na ="N/A";
                String status = "Free";
                System.out.printf("|%-12s|%-12s|%-12s|%-12s|\n", name, na, na, status);
            }else{
                String name =names[index];
                String position = "(" + (positions[index][0]+1) +","+ (positions[index][1]+1) + ")";
                String status = "Basic";
                System.out.printf("|%-12s|%-12s|%-12.2f|%-12s|\n", name, position, values[index], status);
            }
        }        
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|\n\n\n", topDivisor, topDivisor, topDivisor, topDivisor);
    }

    public void PrintPivots_CompleteStatus(){
        String topDivisor = "------------";
        System.out.println("\n\nUpdated detailed report of variables' status");
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", topDivisor, topDivisor, topDivisor, topDivisor, topDivisor);
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", "Name", "Position", "Coeficient", "Result" , "Status");
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", topDivisor, topDivisor, topDivisor, topDivisor, topDivisor);
        for (int index = 0; index < names.length; index++) {
            if(areFree[index]){
                String name =names[index];
                String na ="N/A";
                String status = "Free";
                System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", name, na, na, na, status);
            }else{
                String name =names[index];
                String position = "(" + (positions[index][0]+1) +","+ (positions[index][1]+1) + ")";
                String status = "Basic";
                System.out.printf("|%-12s|%-12s|%-12.2f|%-12.2f|%-12s|\n", name, position, values[index], results[index], status);
            }
        }        
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n\n\n", topDivisor, topDivisor, topDivisor, topDivisor, topDivisor);
    }

}