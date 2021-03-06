package Classes.Matrix.Pivots;
import Classes.Matrix.Matrix_Simple;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Classes.Pivot;
import Classes.Matrix.Pivots.Classes.Pivots;
//import Inputs; You don't need to import files in the same folder
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Vector;
import Classes.Utilities.Inputs;


public class GetPivots{  

   static Vector vector = new Vector();    

   protected static   Pivots      Pivots;
   protected static   Matrix      Matrix;
   protected static   int         SizeColumns;
   protected static   int         SizeRows;

   protected static   int         AmountVariables;
   protected static   int         AmountFreeVariables;
   protected static   int         AmountBasicVariables;

   protected static   boolean     StepByStepStatus;

    
   public static Pivots Main(double[][] matrix, boolean stepByStep){      
      Set_Variables(matrix, stepByStep);      
      Set_Pivots();

      return Pivots;
   }

   public      static void     Reset_Pivots(){
      Pivots = new Pivots();
   }
   public      static void     Set_Variables(double[][] matrix, boolean StepByStep){
      StepByStepStatus = StepByStep;
      Matrix      = new Matrix_Simple("tmp", matrix);
      Pivots      = new Pivots();
      SizeColumns = Matrix.Get_SizeColumns();
      SizeRows    = Matrix.Get_SizeRows();
      
      //Variables
      AmountVariables = SizeColumns;
      AmountBasicVariables = (SizeRows < AmountVariables) ? SizeRows : AmountVariables;
      AmountFreeVariables = AmountVariables - AmountBasicVariables;

   }

   public      static void     Set_Pivots(){
      Reset_Pivots();
      for (int indexColumn = 0; indexColumn < SizeColumns; indexColumn++){
         Set_PivotOfColumn(indexColumn);
      }
   }
   protected   static void     Set_PivotOfColumn(int indexColumn){
      if(!Set_BasicVariable_Column(indexColumn)){         
         Pivots.Add_FreePivot();         
      }
   }
   protected   static boolean  Set_BasicVariable_Column(int indexColumn) {


      if(indexColumn >= 0 && indexColumn <= SizeColumns){
         double coeficient;
         for (int indexRow = 0; indexRow < SizeRows; indexRow++) {
            int[] position = {indexRow, indexColumn};
            coeficient = Matrix.GetElement(position);
            //the pivot cannot be zero, and the can only be one per row
            if(coeficient !=0 && !Pivots.Check_RowHasPivot(indexRow)){
               String name = "X" + (indexColumn + 1);
               boolean isFree = false;
               Pivot newPivot = new Pivot(name, position, coeficient, isFree);

               Pivots.Add_BasicPivot(newPivot);

               return true;
            }
         }
         return false;
      }else{
         System.out.println("Error: the index of the column does not exist in the matrix: " + indexColumn + ". Amoun of columns: "+ SizeColumns);
         System.out.println("in method GetPivot_Column");
      }
      return false;

   }

}