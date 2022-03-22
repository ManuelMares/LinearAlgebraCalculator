package Methods.Inverse;
import Classes.*;
import GUI.Components.Containers.*;
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
    private SectionCardinalUI UI;
    Recursion   recursion = new Recursion();
    Matrix_Inverse matrix;

    public void Main(){
        UI= new SectionCardinalUI(20);

        Create_Matrix();
        Reduce_Matrix();
        Conclusion();
        Set_UI();
    }

    

    private void    Create_Matrix(){
        Printer.Title2("Get Matrix Inverse");
        Printer.Subtitle2("Let's start by indicate the size of the matrix");
        double[][] entries = GetMatrix.Square();
        matrix = new Matrix_Inverse("A", entries);
    }
    private void    Reduce_Matrix(){
        matrix.ReduceMatrix();
    }
    private void    Conclusion(){
        Printer.Subtitle("Conclusion"); 
        if(matrix.IsValid()){
            String message ="The inverse matrix is";
            Printer.Matrix(matrix, message);
        }else{
            System.out.print("The given matrix DOES NOT HAVE an inverse matrix\n\n");
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

    public void Set_UI(){        
        SectionVerticalUI top = new SectionVerticalUI();
        top.Set_BackgroundColor(Colors.gray3);
        Subtitle1UI Title = new Subtitle1UI("Inverse Matrix", Colors.black, Colors.gray3);
        top.Add_Component(Title);
        UI.Add_Component(top, "NORTH");

        UI.Add_Component(matrix.Get_InverseUI(), "CENTER");   
        
        SectionVerticalUI bottom = new SectionVerticalUI();
        bottom.Set_BackgroundColor(Colors.gray3);
        Subtitle1UI conclusion = new Subtitle1UI("Conclusion", Colors.black, Colors.gray3);
        bottom.Add_Component(conclusion);
        UI.Add_Component(bottom, "SOUTH");
    }

    public SectionCardinalUI Get_UI(){
        return UI;
    }
}
