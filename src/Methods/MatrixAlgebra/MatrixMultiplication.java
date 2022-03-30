package Methods.MatrixAlgebra;
import Classes.*;
import Classes.Matrix.Matrix_Multiplication;
import Classes.Matrix.Matrix_Simple;
import Classes.Utilities.Colors;
import Classes.Utilities.Inputs;
import Classes.Utilities.Printer;
import Classes.Utilities.Vector;
import GUI.Components.Containers.SectionCardinalUI;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Components.Tables.MatrixUI;
import GUI.Components.Text.Subtitle1UI;
import GUI.Components.Text.Subtitle2UI;
import GUI.Controllers.MultiplicationUI;
import Methods.Controller.GetMatrix;

public class MatrixMultiplication {
    Matrix_Multiplication matrixMultiplication;
    private SectionCardinalUI UI;
    SectionScrollUI top, center, bottom;
    int[] size = {1000, 200};


    
    public void Main(double[][] m1, double[][] m2){
        matrixMultiplication = new Matrix_Multiplication(m1, m2);
        Set_UIProperties();
        if(matrixMultiplication.CheckCompatibility()){
            matrixMultiplication.Multiply_Matrices();
        }
        Print_Conclusion();
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
        Subtitle1UI Title = new Subtitle1UI("Multiply Matrices", Colors.black, Colors.gray3);
        top.Add_Component(Title);        
        MatrixUI matrixUI1 =  new MatrixUI(matrixMultiplication.Get_CopyMatrix(), "matrix A");
        matrixUI1.Set_BackgroundColor(Colors.gray3);
        MatrixUI matrixUI2 =  new MatrixUI(matrixMultiplication.matrix2, "matrix B");
        matrixUI2.Set_BackgroundColor(Colors.gray3);
        top.Add_Component(matrixUI1);
        top.Add_Component(matrixUI2);
    }
    private void Set_BottomProperties(){ 
        bottom = new SectionScrollUI(size);
        bottom.Set_BackgroundColor(Colors.gray3);
        //bottom.Set_BackgroundColor(Colors.gray3); 
        Subtitle1UI conclusion = new Subtitle1UI("Conclusion", Colors.black, Colors.gray3);
        bottom.Add_Component(conclusion);    
    }
    public  void Set_UI(){  
        UI.Add_Component(top, "NORTH");  
        
        center = matrixMultiplication.Get_UI();
        //center.setPreferredSize(new Dimension(1000,300));
        UI.Add_Component(center, "CENTER");  
        
        UI.Add_Component(bottom, "SOUTH");   
    }

    
    private void    Print_Conclusion() { 
        if(matrixMultiplication.CheckCompatibility())     
            Print_Matrix();
        else    
            PrintNotCompatible();
    }

    private void    Print_Matrix() {
        MatrixUI matrixUI = new MatrixUI(matrixMultiplication.ResultMatrix, "Final matrix");
        matrixUI.Set_TableColor(Colors.gray3);
        bottom.Add_Component(matrixUI);
        System.out.println("Amonos, ta' jalando");
    }
    private void    PrintNotCompatible() {
        String message = "The given matrices are not compatible";
        Subtitle2UI conclusion = new Subtitle2UI(message);
        bottom.Add_Component(conclusion);
        System.out.println("Amonos, ta' jalando");
    }



}