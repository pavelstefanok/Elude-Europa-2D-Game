package PaooGame.Camera;

public class CameraView {

    public static void printCamera(char[][] map, int camH, int camW) {
        int rows = map.length;
        int cols = map[0].length;

        // 1. Căutăm poziția literei 'S'
        int px = -1, py = -1;
        boolean found = false;
        for (int i = 0; i < rows && !found; i++) {
            for (int j = 0; j < cols && !found; j++) {
                if (map[i][j] == 'S') {
                    px = i;
                    py = j;
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Camera 'S' nu a fost găsită.");
            return;
        }

        // 2. Calculăm colțul din stânga sus al ferestrei camerei
        int startX = Math.max(0, px - camH / 2);
        int startY = Math.max(0, py - camW / 2);

        // Asigurăm că nu depășim marginile hărții
        if (startX + camH > rows) startX = Math.max(0, rows - camH);
        if (startY + camW > cols) startY = Math.max(0, cols - camW);

        // 3. Afișăm fereastra
        for (int i = 0; i < camH; i++) {
            for (int j = 0; j < camW; j++) {
                System.out.print(map[startX + i][startY + j]);
            }
            System.out.println();
        }
    }
}
