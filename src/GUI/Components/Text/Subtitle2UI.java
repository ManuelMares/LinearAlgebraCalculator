package GUI.Components.Text;
import java.awt.Color;
import java.awt.Font;

public class Subtitle2UI extends Text {
    public Subtitle2UI(String message){
        Set_Properties(16, Font.BOLD, Color.BLACK);
        Set_Message(message);
    }
    public Subtitle2UI(String message, Color fontColor, Color backgroundColor){
        Set_Properties(16, Font.BOLD, fontColor);
        Set_Background(backgroundColor);
        Set_Message(message);
    }
}
