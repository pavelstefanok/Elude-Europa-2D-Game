package PaooGame.harta;

import java.util.*;

public class MapExpander {
    static class Obj {
        char sym;
        int w, h;

        Obj(char sym, int w, int h) {
            this.sym = sym;
            this.w = w;
            this.h = h;
        }
    }

    public static boolean langaUsa(char[][] map, int x, int y, int h, int w) {
        for (int i = -2; i < h + 2; i++) {
            for (int j = -2; j < w + 2; j++) {
                int nx = x + i;
                int ny = y + j;
                if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) continue;
                char ch = map[nx][ny];
                if (ch == '%' || ch == '&') return true;
            }
        }
        return false;
    }

    public static boolean poatePlasa(char[][] map, int x, int y, int h, int w,int nivel) {
        if (x + h >= map.length || y + w >= map[0].length) return false;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[x + i][y + j] != '`'&& nivel == 1) return false;
                if (map[x + i][y + j] != '~'&& nivel == 2) return false;
                if (map[x + i][y + j] != '"'&& nivel == 3) return false;
            }
        }
        return true;
    }

    public static void plaseazaObj(char[][] map, int x, int y, Obj o) {
        for (int i = 0; i < o.h; i++) {
            for (int j = 0; j < o.w; j++) {
                map[x + i][y + j] = o.sym;
            }
        }
    }

    public static char[][] expandMap(String[][] smap, int roomH, int roomW,int nivel) {
        int srows = smap.length, scols = smap[0].length;
        int brows = srows * roomH;
        int bcols = scols * roomW;
        char[][] map = new char[brows][bcols];

        for (char[] row : map) Arrays.fill(row, ' ');

        for (int i = 0; i < srows; i++) {
            for (int j = 0; j < scols; j++) {
                String cell = smap[i][j];
                if (cell.equals("0")) continue;

                int sr = i * roomH;
                int sc = j * roomW;

                // Pereti
                for (int r = 0; r < roomH; r++) {
                    for (int c = 0; c < roomW; c++) {
                        if (nivel == 1) {

                            if (r == 0 && c == 0) map[sr + r][sc + c] = '↘';
                            else if (r == 0 && c == roomW - 1) map[sr + r][sc + c] = '↙';
                            else if (r == roomH - 1 && c == 0) map[sr + r][sc + c] = '↗';
                            else if (r == roomH - 1 && c == roomW - 1) map[sr + r][sc + c] = '↖';
                            else if (r == 0) map[sr + r][sc + c] = '↓';
                            else if (r == roomH - 1) map[sr + r][sc + c] = '↑';
                            else if (c == 0) map[sr + r][sc + c] = '→';
                            else if (c == roomW - 1) map[sr + r][sc + c] = '←';
                            else map[sr + r][sc + c] = '`';
                        } else if (nivel ==2) {
                            if (r == 0 && c == 0) map[sr + r][sc + c] = '⇘';
                            else if (r == 0 && c == roomW - 1) map[sr + r][sc + c] = '⇙';
                            else if (r == roomH - 1 && c == 0) map[sr + r][sc + c] = '⇗';
                            else if (r == roomH - 1 && c == roomW - 1) map[sr + r][sc + c] = '⇖';
                            else if (r == 0) map[sr + r][sc + c] = '⇂';
                            else if (r == roomH - 1) map[sr + r][sc + c] = '↾';
                            else if (c == 0) map[sr + r][sc + c] = '⇀';
                            else if (c == roomW - 1) map[sr + r][sc + c] = '↼';
                            else map[sr + r][sc + c] = '~';

                        }else if (nivel ==3) {
                            if (r == 0 && c == 0) map[sr + r][sc + c] = '➴';
                            else if (r == 0 && c == roomW - 1) map[sr + r][sc + c] = '↚';
                            else if (r == roomH - 1 && c == 0) map[sr + r][sc + c] = '➶';
                            else if (r == roomH - 1 && c == roomW - 1) map[sr + r][sc + c] = '↸';
                            else if (r == 0) map[sr + r][sc + c] = '⇣';
                            else if (r == roomH - 1) map[sr + r][sc + c] = '⇡';
                            else if (c == 0) map[sr + r][sc + c] = '⇢';
                            else if (c == roomW - 1) map[sr + r][sc + c] = '⇠';
                            else map[sr + r][sc + c] = '"';
                        }
                    }
                }

                map[sr + roomH / 2][sc + roomW / 2] = cell.charAt(0);
                boolean fin = cell.equals("F");

                // Usi
                if (i > 0 && !smap[i - 1][j].equals("0")) {
                    char ch = fin || smap[i - 1][j].equals("F") ? '%' : '&';
                    int dc = sc + roomW / 2;
                    map[sr][dc] = ch; map[sr][dc + 1] = ch;
                    map[sr - 1][dc] = ch; map[sr - 1][dc + 1] = ch;
                }
                if (i < srows - 1 && !smap[i + 1][j].equals("0")) {
                    char ch = fin || smap[i + 1][j].equals("F") ? '%' : '&';
                    int dc = sc + roomW / 2;
                    map[sr + roomH - 1][dc] = ch; map[sr + roomH - 1][dc + 1] = ch;
                    map[sr + roomH][dc] = ch; map[sr + roomH][dc + 1] = ch;
                }
                if (j > 0 && !smap[i][j - 1].equals("0")) {
                    char ch = fin || smap[i][j - 1].equals("F") ? '%' : '&';
                    int dr = sr + roomH / 2;
                    map[dr][sc] = ch; map[dr + 1][sc] = ch;
                    map[dr][sc - 1] = ch; map[dr + 1][sc - 1] = ch;
                }
                if (j < scols - 1 && !smap[i][j + 1].equals("0")) {
                    char ch = fin || smap[i][j + 1].equals("F") ? '%' : '&';
                    int dr = sr + roomH / 2;
                    map[dr][sc + roomW - 1] = ch; map[dr + 1][sc + roomW - 1] = ch;
                    map[dr][sc + roomW] = ch; map[dr + 1][sc + roomW] = ch;
                }
                boolean fina = cell.equals("F");

                // Dacă e cameră finală, adaugă o singură ușă % într-o direcție fără vecin
                if (fina) {
                    int dc = sc + roomW / 2;
                    int dr = sr + roomH / 2;

                    if (i == 0 || smap[i - 1][j].equals("0")) { // sus
                        map[sr][dc] = '%';
                        map[sr][dc+1] = '%';
                    } else if (i == srows - 1 || smap[i + 1][j].equals("0")) { // jos
                        map[sr + roomH - 1][dc] = '%';
                        map[sr+roomH -1 ][dc+1]='%';
                    } else if (j == 0 || smap[i][j - 1].equals("0")) { // stânga
                        map[dr][sc] = '%';
                        map[dr+1][sc] = '%';
                    } else if (j == scols - 1 || smap[i][j + 1].equals("0")) { // dreapta
                        map[dr][sc + roomW - 1] = '%';
                        map[dr+1][sc + roomW-1] = '%';
                    }
                }

                // Obiecte
                Obj[] objs = {
                        new Obj('2', 2, 2),
                        new Obj('3', 3, 2),
                        new Obj('4', 4, 4),
                        new Obj('x', 1, 1),
                        new Obj('y', 2, 1)
                };
                Random rand = new Random();
                int nrObjs = 1 + rand.nextInt(10) + 3;

                for (int k = 0; k < nrObjs; k++) {
                    for (int tr = 0; tr < 20; tr++) {
                        Obj o = objs[rand.nextInt(objs.length)];
                        int x = sr + 1 + rand.nextInt(Math.max(1, roomH - o.h - 2));
                        int y = sc + 1 + rand.nextInt(Math.max(1, roomW - o.w - 2));
                        if (!langaUsa(map, x, y, o.h, o.w) && poatePlasa(map, x, y, o.h, o.w,nivel)) {
                            plaseazaObj(map, x, y, o);
                            break;
                        }
                    }
                }
            }
        }


        return map;
    }

    public static void printBigMap(char[][] map) {
        for (char[] row : map) {
            for (char c : row) System.out.print(c);
            System.out.println();
        }
    }
}
