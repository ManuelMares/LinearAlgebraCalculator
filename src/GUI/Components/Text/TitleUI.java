package GUI.Components.Text;
import java.awt.Color;
import java.awt.Font;

public class TitleUI extends Text {     
    public TitleUI(String message){
        Set_Properties(25, Font.BOLD, Color.BLACK);
        Set_Message(message);
    }          
    public TitleUI(String message, Color fontColor, Color backgroundColor){
        Set_Properties(25, Font.BOLD, fontColor);
        Set_Background(backgroundColor);
        Set_Message(message);
    }        
}
