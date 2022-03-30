package Classes.Matrix;
import Classes.Matrix.AbstractClasses.Matrix;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Controllers.MultiplicationUI;


public class Matrix_Simple extends Matrix{  
   private   MultiplicationUI           multiplyUI;    


   //CONSTRUCTOR---------------------------------------------
   public Matrix_Simple(String name, double[][] values){
      super(name, values);
      multiplyUI   = new MultiplicationUI();
   }
   public Matrix_Simple(String name, int[] size){
      super(name, size);
      multiplyUI   = new MultiplicationUI();
   } 
   public SectionScrollUI Get_UI(){
      return multiplyUI.Get_UI();
   }

}