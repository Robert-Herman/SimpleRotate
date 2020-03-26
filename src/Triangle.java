public class Triangle {
    public double[][][] pyramid =   {
                                        {   { 0, -0.8165,  0.8165},
                                            {-1,  0.3333,  0.3333},
                                            { 0,  0.4714,  0.4714},
                                            { 1,  1,       1}       },
                                        
                                        {   { 0,  0.8165,  0},
                                            {-1,  0.3333,  0.3333},
                                            { 0,  0.4714, -0.9428},
                                            { 1,  1,       1}       },

                                        {   { 0,  0,      -0.8165},
                                            {-1,  0.3333,  0.3333},
                                            { 0, -0.9428,  0.4714},
                                            { 1,  1,       1}       },
                                        
                                        {
                                            {-0.8165, 0.8165,  0},
                                            { 0.3333, 0.3333,  0.3333},
                                            { 0.4714, 0.4714, -0.9428},
                                            { 1,       1,       1}  }
                                    };

    public double[][] triangleXYZ = { { 0, -0.8165,  0.8165,  0},
                                      {-1,  0.3333,  0.3333,  0.3333},
                                      { 0,  0.4714,  0.4714, -0.9428},
                                      { 1,  1,       1,       1} };
    public Triangle() {
    }
    public double[][][] getVectors() {
        double[][][] vectors = new double[4][3][2];
        for (int i=0; i<pyramid.length; i++) {
            for (int j=0; j<(pyramid[i].length-1); j++) {
                for (int k=0; k<(pyramid[i][j].length-1); k++) {
                    vectors[i][j][k] = pyramid[i][j][k] - pyramid[i][j][k+1];
                }
            }            
        }
        return vectors;
    }
    public double[][] normalVector() {
        double[][][] vectors = getVectors(); //4x3x2 triangle x XYZ x vectors
        double[][] vectorsN = new double[3][4]; //XYZ x vectors
        double[][] testDet;
        int[][] allbut = { {1, 2},
                           {0, 2},
                           {0, 1}};
        for (int i=0; i<vectors.length; i++) {
            for (int j=0; j<vectors[i].length; j++) {
                testDet = new double[][] { {vectors[i][allbut[j][0]][0],vectors[i][allbut[j][0]][1]},
                                           {vectors[i][allbut[j][1]][0],vectors[i][allbut[j][1]][1]} };
                vectorsN[j][i] = det(testDet);
            }
        }
        for (int i=0; i<vectorsN[0].length-1; i++) {
            vectorsN[1][i] = -vectorsN[1][i];
        }
        return vectorsN;
    }
    public double det(double[][] matrix) {
        return ((matrix[0][0]*matrix[1][1]) - (matrix[0][1]*matrix[1][0]));
    }
    public double[][] centroid() {
        double[][] centroidXYZ = new double[4][4];
        for (int i=0; i<pyramid.length; i++) {
            for (int j=0; j<pyramid[i].length; j++) {
                centroidXYZ[j][i] = (pyramid[i][j][0] + pyramid[i][j][1] + pyramid[i][j][2])/3;
            }            
        }
        return centroidXYZ;
    }
    public void rotateX(double x) {
        triangleXYZ = MathFNC.rotateX(triangleXYZ,x);
        for (int i=0; i<pyramid.length; i++) {
            pyramid[i] = MathFNC.rotateX(pyramid[i],x);
        }
    }
    public void rotateY(double y) {
        triangleXYZ = MathFNC.rotateY(triangleXYZ,y);
        for (int i=0; i<pyramid.length; i++) {
            pyramid[i] = MathFNC.rotateY(pyramid[i],y);
        }
    }
    public void rotateZ(double z) {
        triangleXYZ = MathFNC.rotateZ(triangleXYZ,z);
        for (int i=0; i<pyramid.length; i++) {
            pyramid[i] = MathFNC.rotateZ(pyramid[i],z);
        }
    }
    public void scale(double[] scale) {
        triangleXYZ = MathFNC.matrixScale(triangleXYZ,scale);
        for (int i=0; i<pyramid.length; i++) {
            pyramid[i] = MathFNC.matrixScale(pyramid[i],scale);
        }
    }
    public void translate(double[] translate) {
        triangleXYZ = MathFNC.matrixTranslate(triangleXYZ,translate);
        for (int i=0; i<pyramid.length; i++) {
            pyramid[i] = MathFNC.matrixTranslate(pyramid[i],translate);
        }
    }
}