package Variables;

public class Dictionary {
    //constructors
    private double[][] values;
    public Dictionary(double[][] arreglo){
        values = arreglo;
    }
    public Dictionary(int size){
        values = new double[size][2];
    }
    public Dictionary(){
        values = new double[0][2];
    }





    public void AddMultipleElements(double[][] array){
        for (double[] pair : array) {
            AddElement(pair);
        }
    }

    public void AddElement(double[] newPair){
        double[][] newArray = new double[values.length + 1][2];
        int counter=0;
        if(values == null ){
            values = new double[1][2];
            values[1] = newPair;
            counter++;
        }
        for (double[] pair : values) {
            newArray[counter] = values[counter];
            counter++;
        }
        newArray[counter] = newPair;
        values = newArray;
    }
    
    public void EditKey(double key, int position){
        values[position][0]= key;}

    public void EditValue(double value, int position){
        values[position][1]= value;
    }

    public void DeleteElement(int position){
        double[][] newArray = new double[values.length - 1][2];
        for (int counter = 0; counter < newArray.length; counter++) {
            if( counter == position);
                newArray[counter] = values[counter];
        }
        values = newArray;
    }

    public void PrintDictionary(){
        System.out.print("{");
        int counter = 1;
        for (double[] pair : values) {
            System.out.print("  X" + counter + " : ");
            System.out.print(pair[1]+"  ");
            counter++;
        }
        System.out.println("}");
    }

    public double GetValue(double key){
        double value = 0;
        for (double[] element : values) {
            if(element[0] == key ){
                value = element[0];
            }
        }
        return value;
    }

    public double GetKey_Double(double value){
        double key = 0;
        for (double[] element : values) {
            if(element[1] == value ){
                key = element[1];
            }
        }
        return key;
    }

    public double[] GetPair(int position){
        double[] pair = values[position];
        return pair;
    }

    public int Size(){
        return values.length;
    }
    
    

}
