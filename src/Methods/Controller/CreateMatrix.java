package Methods.Controller;

import java.util.function.Consumer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.Utilities.Colors;
import Classes.Utilities.Printer;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Components.Containers.SectionVerticalUI;
import Methods.SolveMatrix.SolveMatrix_ReduceEchelon;

public class CreateMatrix extends SectionVerticalUI {
    GetMatrix getMatrix;
    double[][] Matrix;
    int[] size;
    Consumer<Integer> consumer;

    public CreateMatrix(Consumer<Integer> action){
        consumer = action;
        Set_Properties();
        Set_MatrixSize();
    }

    public SectionScrollUI Get_Component(){
        SectionScrollUI container = new SectionScrollUI(size);
        container.add(this);
        return container;
    }

    public double[][] Get_Matrix(){
        return Matrix;
    }
    
    public void Set_Properties(){
        getMatrix = new GetMatrix();
        size = new int[] {1000, 350};
    }

    public void Set_Matrix(Integer[] size){
        try {
            Consumer<Integer> tmp = num -> Fill_Matrix(num);
            getMatrix.Set_Matrix(size, tmp);
            this.Add_Component(getMatrix);
            
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            System.out.println("size: " + size[0] + " , " + size[1]);
           System.out.println("Error in Set Matrix");
           System.out.println(e);
           e.printStackTrace();
        }
    }
    
    public void Fill_Matrix(Integer num){
        Matrix = getMatrix.GetValues();
        consumer.accept(0);
    }
    
    private void Set_MatrixSize(){
        Consumer<Integer[]> tmp = size -> Set_Matrix(size);
        GetSize getSize = new GetSize(tmp);
        //getSize.Set_Size(new int[] {1100, 50});
        this.Add_Component(getSize);
    }
}
