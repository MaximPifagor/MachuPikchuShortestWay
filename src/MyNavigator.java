import java.util.*;

public class MyNavigator implements Navigator {
    @Override
    public char[][] searchRoute(char[][] table) {
        MachuPicchuMap map = new MachuPicchuMap(table,'@','X','#');
        return map.getShortestWay();
    }
}
