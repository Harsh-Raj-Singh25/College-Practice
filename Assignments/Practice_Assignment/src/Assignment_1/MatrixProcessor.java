public class MatrixProcessor {
    public static void main(String[] args) {
        int[][] m1 = {{1, 1, 0}, {2, 1, 2}, {5, 1, 4}};
        int[][] m2 = {{1, 2, 1}, {2, 3, 7}, {3, 4, 9}};

        System.out.println("Is Square ? " + isSquare(m1));
        System.out.println("Is Identity ? " + isIdentity(m1));
        System.out.println("Is Diagonal ? " + isDiagonal(m1));
    }

    public static boolean isSquare(int[][] mt) { 
	return mt.length == mt[0].length; }

    public static boolean isDiagonal(int[][] mt) {
        for (int i = 0; i < mt.length; i++) {
            for (int j = 0; j < mt[0].length; j++) {
                if (i != j && mt[i][j] != 0) return false;
            }
        }
        return true;
    }

    public static boolean isIdentity(int[][] mt) {
        if (!isDiagonal(mt)) return false;
        for (int i = 0; i < mt.length; i++) if (mt[i][i] != 1) return false;
        return true;
    }
}