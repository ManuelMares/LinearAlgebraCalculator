package Methods.Inverse;
import Classes.*;
import GUI.Components.Containers.*;
import GUI.Components.Tables.MatrixUI;
import GUI.Components.Text.*;
import GUI.Controllers.InverseUI;
import Methods.Controller.GetMatrix;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Matrix_Inverse;
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Colors;
import Classes.Utilities.Inputs;


public class InverseMatrix { 
    Recursion   recursion = new Recursion();
    Matrix_Inverse matrix;
    private SectionCardinalUI UI;
    SectionScrollUI top, center, bottom;
    int[] size = {1000, 200};

    public void Main(double[][] entries){
        matrix = new Matrix_Inverse("A", entries);
        Set_UIProperties();
        
        Reduce_Matrix();
        Conclusion();

        Set_UI();
    }
    public  SectionCardinalUI Get_UI(){
        return UI;
    }
    private void Set_UIProperties(){
        UI= new SectionCardinalUI(); 
        UI.Set_BackgroundColor(Colors.gray1);  
        Set_TopProperties();
        Set_BottomProperties();
    }
    private void Set_TopProperties(){
        top = new SectionScrollUI(size);
        top.Set_BackgroundColor(Colors.gray3);
        Subtitle1UI Title = new Subtitle1UI("Reduce Echelon Matrix", Colors.black, Colors.gray3);
        top.Add_Component(Title);        
        MatrixUI matrixUI =  new MatrixUI(matrix, "matrix to solve");
        matrixUI.Set_TableColor(Colors.gray3);
        top.Add_Component(matrixUI);
    }
    private void Set_BottomProperties(){ 
        bottom = new SectionScrollUI(size);
        bottom.Set_BackgroundColor(Colors.gray3);
        Subtitle1UI conclusion = new Subtitle1UI("Conclusion", Colors.black, Colors.gray3);
        bottom.Add_Component(conclusion);    
    }
    public  void Set_UI(){  
        UI.Add_Component(top, "NORTH");  
        
        center = matrix.Get_UI();
        //center.setPreferredSize(new Dimension(1000,300));
        UI.Add_Component(center, "CENTER");  
        
        UI.Add_Component(bottom, "SOUTH");   
    }


    

    private void    Reduce_Matrix(){
        matrix.ReduceMatrix();
    }
    private void    Conclusion(){
        Printer.Subtitle("Conclusion"); 
        if(matrix.IsValid()){
            MatrixUI matrixUI = new MatrixUI(matrix, "Final matrix");
            matrixUI.Set_TableColor(Colors.gray3);
            bottom.Add_Component(matrixUI);
        }else{
            String message = "The matrix does not have an inverse";
            Subtitle2UI conclusion = new Subtitle2UI(message);
            bottom.Add_Component(conclusion);
        }
        Printer.Title("End of the prorgam");
    }
    
    


    public Matrix Reduce(double[][] givenMatrix){
        matrix = new Matrix_Inverse("Matrix A", givenMatrix);
        if(MatrixIsSquare()){
            Reduce_Matrix();      
            int inconsistentRow = matrix.CheckConsistentsy();
            if(matrix.Get_IsConsistent())
                return matrix;

        }
        return null;
    }
    public boolean MatrixIsSquare(){
        if(matrix.Get_SizeRows() == matrix.Get_SizeColumns())
            return true;
        return false;
    }

}
