package Classes;

public class Vector {
    public Vector(){}


    public  int[]    New(int length){
        int[] array = new int[length];
        for (int index = 0; index < array.length; index++) {
            array[index] = 0;
        }
        return array;
    }
    public  int[]    New(int length, int value){
        int[] array = new int[length];
        for (int index = 0; index < array.length; index++) {
            array[index] = value;
        }
        return array;
    }
    public  double[] New(int length, double value){
        double[] array = new double[length];
        for (int index = 0; index < array.length; index++) {
            array[index] = value;
        }
        return array;
    }


    public int       IndexOf(int[] array, int value){
        if(array != null && array.length >= 0){
            for (int counter = 0; counter < array.length; counter++) {
                if(array[counter] == value){
                    return counter;
                }
            }
        }
        return -1;
    }
    public int       IndexOf(double[] array, double value){
        if(array != null && array.length >= 0){
            for (int counter = 0; counter < array.length; counter++) {
                if(array[counter] == value){
                    return counter;
                }
            }
        }
        return -1;
    }


    public  boolean  AreEqual(double[] arr1, double[] arr2){      
        boolean areEqual = true;
        try{
            for (int index = 0; index < arr2.length; index++) {
                if(arr1[index] != arr2[index]){
                    return false;
                }
            }   
        }catch(NullPointerException ex){
            System.out.printf("Exception: %s\n",ex);
        }
        return areEqual;
    }
    public  boolean  AreEqual(int[] arr1, int[] arr2){
        boolean areEqual = true;
        try{
            for (int index = 0; index < arr2.length; index++) {
                if(arr1[index] != arr2[index]){
                    return false;
                }
            } 
        }catch(NullPointerException ex){
            System.out.printf("Exception: %s\n",ex);
        }
        return areEqual;
    }
  


    //Increase vector
    public String[]  IncreaseVector(String[] originalVector){ 
        String[] newVector = new String[originalVector.length + 1];
        int index = 0; 
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }

        return newVector;
    }    
    public String[]  IncreaseVector(String[] originalVector, int finalSize){    
        String[] newVector = new String[finalSize];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }
        return newVector;
    }
    public double[]  IncreaseVector(double[] originalVector){        
        double[] newVector = new double[originalVector.length + 1];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }

        return newVector;
    }    
    public double[]  IncreaseVector(double[] originalVector, int finalSize){    
        double[] newVector = new double[finalSize];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }
        return newVector;
    }
    public int[]     IncreaseVector(int[] originalVector){        
        int[] newVector = new int[originalVector.length + 1];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }

        return newVector;
    }    
    public int[]     IncreaseVector(int[] originalVector, int finalSize){    
        int[] newVector = new int[finalSize];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }
        return newVector;
    }
    public boolean[] IncreaseVector(boolean[] originalVector){        
        boolean[] newVector = new boolean[originalVector.length + 1];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }
        return newVector;
    }    
    public boolean[] IncreaseVector(boolean[] originalVector, int finalSize){    
        boolean[] newVector = new boolean[finalSize];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }
        return newVector;
    }
    public int[][]   IncreaseVector(int[][] originalVector){        
        int[][] newVector = new int[originalVector.length + 1][2];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index][0] = originalVector[index][0];
            newVector[index][1] = originalVector[index][1];
            index++;
        }
        return newVector;
    }    
    public int[][]   IncreaseVector(int[][] originalVector, int finalSize){    
        int[][] newVector = new int[finalSize][2];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index][0] = originalVector[index][0];
            newVector[index][1] = originalVector[index][1];
            index++;
        }
        return newVector;
    }


    public int[]     SwapElements(int[] array, int index1, int index2){
        int[] newArray = CopyVector(array);
        newArray[index1] = array[index2];
        newArray[index2] = array[index1];
        return newArray;
    }
    public double[]  SwapElements(double[] array, int index1, int index2){
        double[] newArray = CopyVector(array);
        newArray[index1] = array[index2];
        newArray[index2] = array[index1];
        return newArray;
    }


    public int[]     ByScalar(int[] array, int scalar){
        int[] newArray = new int[array.length];
        for (int index = 0; index < array.length; index++) {
            newArray[index] = array[index] * scalar;
        }
        return newArray;
    }
    public double[]  ByScalar(double[] array, double scalar){
        double[] newArray = new double[array.length];
        for (int index = 0; index < array.length; index++) {
            newArray[index] = array[index] * scalar;
        }
        return newArray;
    }



    public int[]     CopyVector(int[] array){
        int[] newArray = new int[array.length];
        int counter = 0;
        for (int index : array) {
            newArray[counter] = index;
            counter++;
        }
        return newArray;
    };
    public double[]  CopyVector(double[] array){
        double[] newArray = new double[array.length];
        int counter = 0;
        for (double index : array) {
            newArray[counter] = index;
            counter++;
        }
        return newArray;
    };

    public int[]     Set_VectorToIndeces(int[] array){
        int[] newArray = new int[array.length];
        for (int index = 0; index < array.length; index++) {
            newArray[index] = index;
        }
        return newArray;
    }




    
    
}
