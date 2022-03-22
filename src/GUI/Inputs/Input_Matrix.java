package GUI.Inputs;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Classes.Utilities.Colors;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Components.Containers.SectionVertical;
import GUI.Components.Containers.SectionVerticalUI;

public class Input_Matrix extends JPanel {
    JTextField[] Inputs;
    int totalInputs;
    double[][] matrix;
    boolean validMatrix = true;
    int[] size;

    public Input_Matrix(int[] size){
        this.size = size;
        Set_Layout(size);
        Create_Inputs(size);
        Create_UI(size);
    }
    public SectionVerticalUI Get_UI(){
        SectionVerticalUI container = new SectionVerticalUI();
        container.Set_SizeForced(new int[]{1000, size[0]*21});
        container.Set_BackgroundColor(Colors.gray1);
        container.Add_Component(this);
        return container;
    }

    public void Set_Layout(int[] size){        
        this.setLayout(new GridLayout(size[0], size[1]));
    }
    public void Create_Inputs(int[] size){
        totalInputs = size[0] * size[1];
        matrix = new double[size[0]][size[1]];
        Inputs = new JTextField[totalInputs];

        for (int index = 0; index < totalInputs; index++) {
            JTextField newInput = new JTextField(10);
            newInput.setMaximumSize(new Dimension(12, 8) );
            Inputs[index] = newInput;
        }
    }
    public void Create_UI(int[] size){    
        Dimension dimension = new Dimension(size[0]*60, size[0]*20);
        this.setMaximumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        Set_Inputs();
    }

    private void Set_Inputs() {
        for (int index = 0; index < Inputs.length; index++) {
            this.add(Inputs[index]);
        }
    }


    public double[][] Get_Matrix(){
        double[][] empty = null;
        Set_MatrixValues();
        if(validMatrix)
            return matrix;
        else
            return empty;
    }
    public void Set_MatrixValues(){
        int index = 0;
        for (int indexRow = 0; indexRow < matrix.length; indexRow++) {
            for (int indexColumn= 0; indexColumn< matrix[0].length; indexColumn++) {
                matrix[indexRow][indexColumn] = Get_InputValue(index);
                index++;
            }
        }
    }
    public double Get_InputValue(int index){
        try {
            double value = Double.parseDouble( Inputs[index].getText() );         
            return value;   
        } catch (Exception e) {
            validMatrix = false;
            return 0.0;
        }

    }

}
