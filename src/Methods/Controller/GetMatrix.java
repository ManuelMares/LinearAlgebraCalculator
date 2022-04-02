package Methods.Controller;
import Classes.Utilities.Printer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;

import Classes.Utilities.Colors;
import Classes.Utilities.Inputs;
import GUI.Components.Containers.*;
import GUI.Components.Text.TextUI;
import GUI.Inputs.Input_BiggerThan;
import GUI.Inputs.Input_Matrix;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class GetMatrix extends SectionVertical{
     JTextField textField;
     JButton button;
     JButton buttonSize;
     Input_Matrix inputs;
     int[] size = new int[2];
     Input_BiggerThan columns;     
     Input_BiggerThan rows;
     Consumer<Integer> consumer;
     boolean validMatrix = true;

    public  SectionScrollUI Get_FreeUI(){        
        SectionScrollUI newSection = new SectionScrollUI(new int[] {1000, 300} );
        newSection.Set_BackgroundColor(Colors.blue3);
        newSection.add(this);
        return newSection;
    }
    
    public void Set_Matrix(Integer[] newSize, Consumer<Integer> action){
        consumer = action;
        size[0] = newSize[0].intValue();
        size[1] = newSize[1].intValue();
        Set_Properties();
    }

    public void Set_Properties(){
        try {
            inputs = new Input_Matrix(size);
            this.Add_Component(inputs.Get_UI());
            
            button = new JButton("Next");
            this.add(button);
    
            event e = new event();
            button.addActionListener(e);
        } catch (Exception e) {
            System.out.println("Error in Set Properties GetMatrix");
            System.out.print(e);
        }
    }


    public class event implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                consumer.accept(0);
            }catch(Exception ex){
                System.out.println("error, morro" + ex);
                ex.printStackTrace();
            }
        }
    }

    public static double[][] Square(){
        boolean isConfirmed = true;
        String confirmationMessage = "Is the matrix correct?";
        double[][] matrix;
        
        int size = Inputs.BiggerThan("Please indicate the size of the SQUARE matrix", 2);
        matrix = new double[size][size];
        //GetValues(matrix);
        
        String message = "The given matrix is:";
        Printer.Matrix(matrix, message);
        isConfirmed = Inputs.YesNo(confirmationMessage);

        while(!isConfirmed){
            size = Inputs.BiggerThan("Please indicate the size of the SQUARE matrix", 2);
            matrix = new double[size][size];
            //GetValues(matrix);
            
            Printer.Matrix(matrix, message);
            isConfirmed = Inputs.YesNo(confirmationMessage);
        }

        return matrix;
    }

    public static double[][] Identity(int size){
        double[][] newMatrix = new double[size][size];
        for (int index = 0; index < size; index++) {
            newMatrix[index][index] = 1;
        }
        return newMatrix;
    }

    public double[][] GetValues(){
        int index = 0;
        double[][] matrix = new double[size[0]][size[1]];
        for (int indexRow = 0; indexRow < matrix.length; indexRow++) {            
            for (int indexColumn = 0; indexColumn < matrix[0].length; indexColumn++) {
                try {
                    validMatrix = true;
                    double input = inputs.Get_InputValue(index);
                    matrix[indexRow][indexColumn] =  input;       
                    index++;
                } catch (Exception e) {
                    validMatrix = false;
                    System.out.println(e);
                }
            }
        }
        if(validMatrix)
            return matrix;
        return null;
    }
    
}
