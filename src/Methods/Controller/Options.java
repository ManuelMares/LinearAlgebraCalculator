package Methods.Controller;

import java.util.function.Consumer;

import Classes.Utilities.Colors;
import GUI.Components.ButtonUI;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Components.Containers.SectionVertical;
import GUI.Components.Containers.SectionVerticalUI;

public class Options extends SectionVerticalUI {
    String[] menuOptions = {"Reduce Echelon", "Reduce", "Multiply Matrices", "Invert Matrix", "Factorization", "Transpose"};
    int[] size = {1000, 800};
    Consumer<Integer> consumer;

    public Options(Consumer action){
        consumer = action;
        Set_Properties();
        Set_buttons();
    }
    public SectionScrollUI Get_UI(){
        SectionScrollUI container = new SectionScrollUI(size);
        container.Set_BackgroundColor(Colors.gray4);
        container.Add_Component(this);
        
        return container;
    }

    private void Set_Properties() {
        this.Set_BackgroundColor(Colors.gray4);
    }

    public void Set_buttons(){
        Consumer<Integer> tmp = option -> Set_Option(option);
        int index = 0;
        for (String option : menuOptions) {
           ButtonUI button =  new ButtonUI(option, tmp, index);
           button.Set_BackgroundColor(Colors.gray4);
           this.Add_Component(button.Get_UI());
           index++;
        }
    }

    public void Set_Option(int option){
        consumer.accept(option);
    }

    
}
