package jvm;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Ctest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(new Ctest().calculate(""));
        System.out.println(Runtime.getRuntime().availableProcessors());

        String string = "aa";
        FutureTask future = new FutureTask<Integer>(new Callable() {
            @Override
            public Object call() throws Exception {
                return new Random().nextInt(1000);
            }
        });
        new Thread(future).start();
        System.out.println(future.get());;

        Thread threa = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(string);
            }
        });
        threa.start();
        threa.join();

    }


    public void solveSudoku(char[][] board) {
        LinkedList<Point> list = new LinkedList();
        int[][][] mindi = new int[3][3][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    list.add(new Point(i, j));
                } else {
                    mindi[i / 3][j / 3][board[i][j] - '0' - 1] = 1;
                }
            }
        }
        dfs(list, board, mindi);
    }

    private boolean dfs(LinkedList<Point> list, char[][] board, int[][][] mindi) {
        if(list.size() == 0) {
            return true;
        }
        Point tmp = list.removeFirst();
        int[] now = new int[9];
        for(int i = 0; i < 9; i++) {
            if(board[tmp.row][i] != '.')
                now[board[tmp.row][i] -'0' - 1] = 1;
            if(board[i][tmp.col] != '.')
                now[board[i][tmp.col] -'0' - 1] = 1;
        }
        for(int i = 0; i < 9; i++) {
            if(mindi[tmp.row / 3][tmp.col / 3][i] == 0 && now[i] == 0) {
                board[tmp.row][tmp.col] = (char)('0' + i + 1);
                mindi[tmp.row / 3][tmp.col / 3][i] = 1;
                if(dfs(list, board, mindi)) {
                    return true;
                }
                board[tmp.row][tmp.col] = '.';
                mindi[tmp.row / 3][tmp.col / 3][i] = 0;
            }
        }
        list.addFirst(tmp);
        return false;
    }

    static class Point {
        private int row;
        private int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int calculate(String s) {
        int x = 1;
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case 'A' : x = 2 * x + y;break;
                case 'B' : y = 2 * y + x; break;
            }
        }
        return x + y;
    }
}
