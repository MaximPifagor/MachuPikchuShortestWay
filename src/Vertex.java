import sun.rmi.log.LogInputStream;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    Integer x_Idx;
    Integer y_Idx;
    Character value;

    public Vertex(Integer x_Idx, Integer y_Idx, Character value) {
        this.x_Idx = x_Idx;
        this.y_Idx = y_Idx;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return x_Idx*1000 + y_Idx*100 + value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null)
            if(obj instanceof Vertex)
                return  x_Idx == ((Vertex) obj).x_Idx && y_Idx == ((Vertex) obj).y_Idx && value == ((Vertex) obj).value;
        return false;
    }
}
