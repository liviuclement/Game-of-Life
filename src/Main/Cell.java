package Main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {
    private Integer posX;
    private Integer posY;
    private char value;


    Cell(int posX, int posY, char value){
        this.posY = posY;
        this.posX = posX;
        this.value = value;
    };
        public void setValue(char value){
            this.value = value;
        };


        boolean isAlive()
        {
            if(value == '+')
                return true;
            else
                return false;
        }
    int getPosX()
    {
        return posX;
    }

    int getPosY()
    {
        return posY;
    }

    char getValue()
    {
        return value;
    }


}
