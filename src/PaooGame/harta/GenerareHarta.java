package PaooGame.harta;

import PaooGame.harta.*;

public class GenerareHarta {
    public static void g() {
        int rows = 7;
        int cols = 7;
        int totalRooms = 22;
        int roomH=34;
        int roomW=60;


        //String[][] smallMap = RandomMatrixFromList.genMap(rows, cols, totalRooms);
        //System.out.println("===== Harta mică =====");
        //RandomMatrixFromList.printMap(smallMap);


        //char[][] bigMap = MapExpander.expandMap(smallMap, roomH, roomW);
        //System.out.println("===== Harta MARE =====");
        //MapExpander.printBigMap(bigMap);
    }

    public static char[][] Harta(int nivel) {
        int rows = 7;
        int cols = 7;
        int totalRooms = 32;
        int roomH=34;
        int roomW=60;
        int x = 1;
        //int roomSize = 4;
        String[][] smallMap = RandomMatrixFromList.genMap(rows*x, cols*x, totalRooms);

        char[][] bigMap = MapExpander.expandMap(smallMap, roomH, roomW,nivel);
        return bigMap;
    }


}
