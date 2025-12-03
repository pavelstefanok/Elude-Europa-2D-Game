package PaooGame.harta;
import java.util.*;

public class RandomMatrixFromList {
    public int a;
    public static String[][] genMap(int r, int c, int rooms) {
        String[][] m = new String[r][c];
        for (String[] row : m) Arrays.fill(row, "0");

        Random rand = new Random();
        List<int[]> pos = new ArrayList<>();

        int sr = r / 2, sc = c / 2;
        m[sr][sc] = "S";
        pos.add(new int[]{sr, sc});

        int[][] dirs = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        int at = 0, maxAtt = 1000;

        while (pos.size() < rooms - 1 && at < maxAtt) {
            int[] cur = pos.get(rand.nextInt(pos.size()));
            int r1 = cur[0], c1 = cur[1];

            List<int[]> shufDirs = new ArrayList<>(Arrays.asList(dirs));
            Collections.shuffle(shufDirs);

            for (int[] dir : shufDirs) {
                at++;

                int nr = r1 + dir[0], nc = c1 + dir[1];

                if (nr >= 0 && nr < r && nc >= 0 && nc < c && m[nr][nc].equals("0")) {

                    double expandChance = 0.3 + rand.nextDouble() * 0.4;
                    if (rand.nextDouble() > expandChance) continue;

                    int cnt = 0;
                    for (int[] d : dirs) {
                        int nr2 = nr + d[0], nc2 = nc + d[1];
                        if (nr2 >= 0 && nr2 < r && nc2 >= 0 && nc2 < c && m[nr2][nc2].equals("*")) {
                            cnt++;
                        }
                    }

                    if (cnt <= 1) {
                        m[nr][nc] = "*";
                        pos.add(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }

        if (at >= maxAtt) {
            System.out.println("Max attempts reached.");
        }

        int[] finalRoom = null;
        Collections.shuffle(pos);

        for (int[] cur : pos) {
            for (int[] dir : dirs) {
                int nr = cur[0] + dir[0], nc = cur[1] + dir[1];

                if (nr >= 0 && nr < r && nc >= 0 && nc < c && m[nr][nc].equals("0")) {
                    int cnt = 0;
                    for (int[] d : dirs) {
                        int nr2 = nr + d[0], nc2 = nc + d[1];
                        if (nr2 >= 0 && nr2 < r && nc2 >= 0 && nc2 < c &&
                                (m[nr2][nc2].equals("*") || m[nr2][nc2].equals("S"))) {
                            cnt++;
                        }
                    }

                    if (cnt <= 1) {
                        m[nr][nc] = "*";
                        finalRoom = new int[]{nr, nc};
                        pos.add(finalRoom);
                        break;
                    }
                }
            }
            if (finalRoom != null) break;
        }

        if (finalRoom == null) {
            finalRoom = pos.get(pos.size() - 1);
        }

        int cr = r / 2, cc = c / 2;

        pos.sort((a, b) -> {
            double da = Math.pow(a[0] - cr, 2) + Math.pow(a[1] - cc, 2);
            double db = Math.pow(b[0] - cr, 2) + Math.pow(b[1] - cc, 2);
            return Double.compare(db, da);
        });

        int[] keyRoom = pos.get(0);
        m[keyRoom[0]][keyRoom[1]] = "C";
        m[finalRoom[0]][finalRoom[1]] = "F";

        return m;
    }

    public static void printMap(String[][] m) {
        for (String[] row : m) {
            for (String cell : row) {
                System.out.print((cell.equals("0") ? "." : cell) + "\t");
            }
            System.out.println();
        }
    }
}
