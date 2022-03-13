package Classes.Matrix;

import Classes.Matrix.AbstractClasses.Matrix;
//import Inputs; You don't need to import files in the same folder
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Inputs;


public class Matrix_Simple extends Matrix{      


   //CONSTRUCTOR---------------------------------------------
   public Matrix_Simple(String name, double[][] values){
      super(name, values);
   }
   public Matrix_Simple(String name, int[] size){
      super(name, size);
   }


}