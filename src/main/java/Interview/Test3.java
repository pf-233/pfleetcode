package Interview;

/**
 * /**
 *  * [1 2 3]
 *  * [8 9 4]
 *  * [7 6 5]
 *  按1、2、3、4、5、6、7、8、9顺序打印
 */
public class Test3 {

    public static void main(String[] args) {
        Test3 test3 = new Test3();
        int[][] grid = new int[][] {
                {1,2,3},{8,9,4},{7,6,5}
        };
        test3.printGrid(grid, 0);
    }

    private void printGrid(int[][] grid, int layer) {
        int r = grid.length;
        int c = grid[0].length;
        if (layer > (c  + 1) / 2) {
            return;
        }
        // 第layer层开始[layer][layer, c - layer]
        for (int i = layer; i < c - layer; i++) {
            System.out.print(grid[layer][i]+",");
        }
        //列[layer + 1, r - layer] [r - 1 - layer]
        for (int i = layer + 1; i < r - layer; i++) {
            System.out.print(grid[i][r - 1 - layer]+",");
        }
        // 行[]
        for (int i = r - 2 - layer; i >= layer; i--) {
            System.out.print(grid[r - 1 - layer][i]+",");
        }

        for (int i = r - 1 - layer - 1; i > layer; i--) {
            System.out.print(grid[i][layer]+",");
        }

        printGrid(grid, layer+1);
    }
}
