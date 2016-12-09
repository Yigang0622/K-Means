package Algo;

import java.util.ArrayList;
import java.util.List;

/**
 * KMeans聚类算法
 * Created by Mike on 12/9/2016.
 */
public class KMeans {

    private List<MyVector> vectors = new ArrayList<>();

    private List<MyVector> centers = new ArrayList<>(); //质心

    private int numberOfCluster = 0;
    private List<Cluster> clusters = new ArrayList<>(); //储存所有的族

    private int numberOfIteration = 100;

    public KMeans(List<MyVector> vectors,int numberOfCluster){
        this.vectors = vectors;
        this.numberOfCluster = numberOfCluster;
        initCenters();
        initClusters();


    }

    public List<Cluster> getClusters(){
        return clusters;
    }

    private void initClusters(){
        clusters.clear();
        //预先初始化所有的簇
        for (int i=0;i<numberOfCluster;i++){
            clusters.add(new Cluster());
        }
    }


    public void setNumberOfIteration(int numberOfIteration){
        if (numberOfIteration > 0){
            this.numberOfIteration = numberOfIteration;
        }else {
            System.out.println("numberOfIteration should be greater than 0");
        }
    }


    public void startClustering(){

        System.out.println("开始聚类");

        int counter = 0;
        List<MyVector> lastCenters = new ArrayList<>();

        boolean converged = false;

        while (!converged && counter < numberOfIteration){

            System.out.println("第"+counter+"次迭代");

           // printCenters();
            double[][] distanceMatrix = new double[vectors.size()][numberOfCluster];

            //生成距离矩阵
            for (int i=0;i<vectors.size();i++){
                for (int j=0;j<centers.size();j++){
                    MyVector currentVector = vectors.get(i);
                    MyVector centerVector = centers.get(j);
                    double distance = Distance.getSimDistance(centerVector,currentVector);
                    distanceMatrix[i][j] = distance;
                }
            }

            //add vectors to different clusters
            for (int i=0;i<distanceMatrix.length;i++){
                double[] centerDistance = distanceMatrix[i];
                MyVector vector = vectors.get(i);
                int index = getMinDistanceIndex(centerDistance);
                clusters.get(index).addToCluster(vector);
            }

            counter++;


            lastCenters.clear();
            lastCenters.addAll(centers);

           // printClusters();

            //Refresh centers
            for (int i=0;i<numberOfCluster;i++){
                MyVector vector = clusters.get(i).getCenterVector();
                centers.set(i,vector);
            }

            converged = isConverged(lastCenters);
            if (!converged && counter != numberOfIteration){
                initClusters();
            }


        }

        System.out.println("聚类完成\n迭代次数"+counter);

      //  printClusters();

    }

    private boolean isConverged(List<MyVector> lastCenters){

        if (lastCenters.size() != numberOfCluster){
            return false;
        }

        boolean converge = true;

        for (int i=0;i<this.centers.size();i++){

            MyVector thisVector = this.centers.get(i);
            MyVector thatVector = lastCenters.get(i);

            if (!thisVector.isSameVector(thatVector)){
                converge = false;
            }

        }

        if (converge){
            System.out.println("检测到收敛");
        }

        return converge;

    }


    private int getMinDistanceIndex(double[] arr){

        double min = 99999999999999999999999.0;
        int index = 0;
        for (int i=0;i<arr.length;i++){
            if (arr[i] < min){
                index = i;
                min = arr[i];
            }
        }

        return index;
    }

    private void printMatrix(double[][] mat){

        int height = mat.length;
        int width = mat[0].length;

        for (int i= 0;i< height;i++){
            for (int j=0;j<width;j++){
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void printCenters(){
        System.out.println("Printing Centers");
        for (MyVector vector:centers){
            vector.printVector();
        }
    }

    private void printClusters(){
        for (Cluster cluster:clusters){
            cluster.printCluster(false);
            System.out.println();
        }

    }

    /**
     * 初始质心
     * 从所有的向量中挑选 numberOfCluster 个
     */
    private void initCenters(){

        int sizeOfVectors = vectors.size();

        for (int i=0;i<numberOfCluster;i++){
            int index= (int)(Math.random()*sizeOfVectors);
            centers.add(vectors.get(index));
        }

    }

    public static void main(String[] args){
        List<MyVector> vectors = new ArrayList<>();
        vectors.add(new MyVector(new double[]{1.0, 1.0}));
        vectors.add(new MyVector(new double[]{9.0, 8.0}));
        vectors.add(new MyVector(new double[]{2.0, 1.0}));
        vectors.add(new MyVector(new double[]{3.0, 2.0}));
        vectors.add(new MyVector(new double[]{10.0, 10.0}));
        vectors.add(new MyVector(new double[]{7.0, 8.0}));
        vectors.add(new MyVector(new double[]{3.0, 1.0}));
        vectors.add(new MyVector(new double[]{8.0, 9.0}));
        vectors.add(new MyVector(new double[]{367, 10.0}));
        vectors.add(new MyVector(new double[]{10.0, 7.0}));
        vectors.add(new MyVector(new double[]{33.0, 8.0}));
        vectors.add(new MyVector(new double[]{1.0, 2.0}));
        vectors.add(new MyVector(new double[]{33.0, 9.0}));
        vectors.add(new MyVector(new double[]{35.0, 7.0}));
        vectors.add(new MyVector(new double[]{100.0, 9.0}));
        vectors.add(new MyVector(new double[]{33.0, 10.0}));
        vectors.add(new MyVector(new double[]{8.0, 10.0}));
        vectors.add(new MyVector(new double[]{105.0, 7.0}));
        vectors.add(new MyVector(new double[]{99.0, 8.0}));
        vectors.add(new MyVector(new double[]{1.0, 4.0}));
        vectors.add(new MyVector(new double[]{101.0, 10.0}));
        vectors.add(new MyVector(new double[]{333, 8.0}));
        vectors.add(new MyVector(new double[]{444, 9.0}));
        vectors.add(new MyVector(new double[]{423, 7.0}));
        vectors.add(new MyVector(new double[]{0.1, 2.0}));
        KMeans kMeans = new KMeans(vectors,5);
        kMeans.startClustering();



    }


}
