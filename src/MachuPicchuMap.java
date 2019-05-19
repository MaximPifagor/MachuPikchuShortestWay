import javafx.util.Pair;

import java.util.*;

public class MachuPicchuMap {
    private char[][] map;
    private Vertex finish;
    private Vertex start;
    private final char wallValue;
    private final char startValue;
    private final char finishValue;
    private int widthX;
    private int heightY;
    private HashMap<Vertex, Vertex> previous = new HashMap<>();

    public MachuPicchuMap(char[][] map, char startValue, char finishValue, char wallValue) {
        this.map = map;
        this.startValue = startValue;
        this.finishValue = finishValue;
        this.wallValue = wallValue;
        heightY = map[0].length;
        widthX =  map.length;
    }

    public char[][] getShortestWay() {
        start = getStartPosition(startValue);
        ArrayDeque<Vertex> deque = new ArrayDeque<>();
        deque.add(start);
        previous.put(start, null);
        while (!deque.isEmpty()) {
            Vertex e = deque.poll();
            if (e.value == finishValue) {
                finish = e;
                break;
            }
            deque.addAll(getNeighbors(e));
            for (Vertex a:deque) {
                System.out.println(a.x_Idx + " " + a.y_Idx + " " + a.value);
            }
            System.out.println();
        }
        return getDirection(map.clone());
    }


    private Vertex getStartPosition(char startValue) {
        Vertex start = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == startValue)
                    start = new Vertex(i, j, map[i][j]);
            }
        }
        return start;
    }

    HashMap<Integer,Pair<Integer,Integer>> steps = new HashMap<>();
    {
        steps.put(0,new Pair<>(1,0));
        steps.put(1, new Pair<>(0,1));
        steps.put(2, new Pair<>(-1,0));
        steps.put(3, new Pair<>(0,-1));
    }

    private List<Vertex> getNeighbors(Vertex e) {
        ArrayList<Vertex> neighbors = new ArrayList<>();
        for (Map.Entry<Integer, Pair<Integer,Integer>> step : steps.entrySet()) {
            int x_IdxNext = e.x_Idx + step.getValue().getKey();
            int y_IdxNext = e.y_Idx + step.getValue().getValue();
            if ((x_IdxNext >= 0 && y_IdxNext >= 0) &&
                    (x_IdxNext < widthX && y_IdxNext < heightY) &&
                    (map[x_IdxNext][y_IdxNext] != wallValue)) {
                Vertex v = new Vertex(x_IdxNext, y_IdxNext, map[x_IdxNext][y_IdxNext]);
                if (!previous.containsKey(v)) {
                    neighbors.add(v);
                    previous.put(v, e);
                }
            }
        }
        return neighbors;
    }

    private char[][] getDirection(char[][] map) {
        if (finish == null)
            return null;
        char[][] newPath = map;
        Vertex toStart = previous.get(finish);
        while (!toStart.equals(start)) {
            newPath[toStart.x_Idx][toStart.y_Idx] = '+';
            toStart = previous.get(toStart);
        }
        return newPath;
    }
}
