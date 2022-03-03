package Classes.Recursion;
import Classes.Utilities.Vector;

public class Recursion {
    static Vector vector = new Vector();
    int indexPermutation = 0;
    
    public int[][]  Get_ArrayOfPermutations(int number){
        indexPermutation = 0;
        //set a base array TO PERMUTATE
        int[] array = new int[number];
        array = vector.Set_VectorToIndeces(array);

        //array that will contain the list of permutations (size = arrabase.factorial())
        int[][] permutationArray;
        permutationArray = new int[Get_Factorial(array.length)][array.length];

        PermuteArray(array, 0, permutationArray);
        return permutationArray;
    }

    
    private void    PermuteArray(int[] array, int indexBase, int[][] permutationArray) {
        for (int indexChange = indexBase; indexChange < array.length; indexChange++) {
            array = vector.SwapElements(array, indexChange, indexBase);
            PermuteArray(array, (indexBase + 1), permutationArray);
            array = vector.SwapElements(array, indexBase, indexChange);
        }
        if(indexBase == (array.length - 1) && indexPermutation<= permutationArray.length){
            try {
                permutationArray[indexPermutation] = vector.CopyVector(array);
                indexPermutation++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.printf("\n\nException: %s\n", e);
            }
        }
    }
    

    public int      Get_Factorial(int number){
        int counter = 1;
        for (int i = 1; i <= number; i++) {
            counter = counter * i;
        }
        return counter;
        }
      

}
