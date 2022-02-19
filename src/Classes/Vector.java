package Classes;

public class Vector {
    public Vector(){}


    //Increase vector
    public String[] IncreaseVector(String[] originalVector){ 
        String[] newVector = new String[originalVector.length + 1];
        int index = 0; 
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }

        return newVector;
    }    
    public String[] IncreaseVector(String[] originalVector, int finalSize){    
        String[] newVector = new String[finalSize];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }
        return newVector;
    }
    
    public double[] IncreaseVector(double[] originalVector){        
        double[] newVector = new double[originalVector.length + 1];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }

        return newVector;
    }    
    public double[] IncreaseVector(double[] originalVector, int finalSize){    
        double[] newVector = new double[finalSize];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }
        return newVector;
    }

    public int[] IncreaseVector(int[] originalVector){        
        int[] newVector = new int[originalVector.length + 1];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index] = originalVector[index];
            index++;
        }

        return newVector;
    }    
    public int[] IncreaseVector(int[] originalVector, int finalSize){    
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

    public int[][] IncreaseVector(int[][] originalVector){        
        int[][] newVector = new int[originalVector.length + 1][2];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index][0] = originalVector[index][0];
            newVector[index][1] = originalVector[index][1];
            index++;
        }
        return newVector;
    }    
    public int[][] IncreaseVector(int[][] originalVector, int finalSize){    
        int[][] newVector = new int[finalSize][2];
        int index = 0;
        while (index < originalVector.length) {
            newVector[index][0] = originalVector[index][0];
            newVector[index][1] = originalVector[index][1];
            index++;
        }
        return newVector;
    }


    public void CopyVector(){};
}
