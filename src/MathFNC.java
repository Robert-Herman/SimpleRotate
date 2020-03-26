class MathFNC {
    public static double[][] rotateX(double[][] matrix, double angle) {
        double radians = Math.toRadians(angle);
        double[][] rX = {{1,0,0,0},
                         {0,Math.cos(radians),-Math.sin(radians),0},
                         {0,Math.sin(radians), Math.cos(radians),0},
                         {0,0,0,1}};
        return matrixMultiply(rX, matrix);
    }
    public static double[][] rotateY(double[][] matrix, double angle) {
        double radians = Math.toRadians(angle);
        double[][] rY = {{ Math.cos(radians),0,Math.sin(radians),0},
                         { 0,1,0,0},
                         {-Math.sin(radians),0,Math.cos(radians),0},
                         {0,0,0,1}};
        return matrixMultiply(rY, matrix);
    }
    public static double[][] rotateZ(double[][] matrix, double angle) {
        double radians = Math.toRadians(angle);
        double[][] rZ = {{Math.cos(radians),-Math.sin(radians),0,0},
                         {Math.sin(radians),Math.cos(radians),0,0},
                         {0,0,1,0},
                         {0,0,0,1}};
        return matrixMultiply(rZ, matrix);
    }
    public static double[][] matrixTranslate(double[][] matrix, double[] translate) {
        double[][] matrixT = {{1,0,0,translate[0]},
                              {0,1,0,translate[1]},
                              {0,0,1,translate[2]},
                              {0,0,0,1}};
        return matrixMultiply(matrixT, matrix);
    }
    public static double[][] matrixScale(double[][] matrix, double[] scale) {
        double[][] matrixS = {{scale[0],0,0,0},
                              {0,scale[1],0,0},
                              {0,0,scale[2],0},
                              {0,0,0,1}};
        return matrixMultiply(matrixS, matrix);
    }
    public static double[][] matrixMultiply(double[][] matrixA, double[][] matrixB) {
        double[][] matrixAB = new double[matrixA.length][matrixB[0].length];
        for (int row=0; row<matrixA.length; row++) {
            for (int col=0; col<matrixB[0].length; col++) {
                matrixAB[row][col] = matrixMultiplyRowCol(matrixA[row], matrixGetColumn(matrixB,col));
            }
        }
        matrixAB[matrixA.length-1] = matrixB[matrixB.length-1];
        return matrixAB;
    }
    public static double[] matrixGetColumn(double[][] matrix, int col) {
        double[] column = new double[matrix.length];
        for (int i=0; i<matrix.length; i++) {
            column[i] = matrix[i][col];
        }
        return column;
    }
    public static double matrixMultiplyRowCol(double[] row, double[] col) {
        double sum = 0;
        for (int i=0; i<row.length; i++) {
            sum = sum + row[i]*col[i];
        }
        return sum;
    }
    public static double matrixSum(double[][] matrix) {
        double sum = 0;
        for (int row=0; row<matrix.length; row++) {
            for (int col=0; col<matrix[0].length; col++) {
                sum = sum + matrix[row][col];
            }
        }
        return sum;
    }
}