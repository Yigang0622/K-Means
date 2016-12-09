package Algo;

/**
 * Created by Mike on 12/9/2016.
 */
public class Distance {


    /**
     * 计算欧式距离
     * @param vector1
     * @param vector2
     * @return
     */
    public static double getSimDistance(MyVector vector1, MyVector vector2){

        double sumOfSquare = 0.0;

        for (int i=0;i<vector1.size();i++){

            double vector1Score = vector1.get(i);
            double vector2Score = vector2.get(i);
            sumOfSquare +=  Math.pow(vector1Score - vector2Score,2);

        }

        return Math.sqrt(sumOfSquare);
        //return (1 / (1 + Math.sqrt(sumOfSquare)));
    }


    /**计算皮尔逊相关系数
     * 返回 -1 ~ 1
     * -1-0为正相关
     * 0-1为正相关
     */

    public static double getPearsonDistance(MyVector vector1, MyVector vector2){

        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum1Sq = 0.0;
        double sum2Sq = 0.0;
        double pSum = 0.0;

        for (int i =0;i<vector1.size();i++){
            sum1 += vector1.get(i);
            sum2 += vector2.get(i);

            sum1Sq += Math.pow(vector1.get(i),2);
            sum2Sq += Math.pow(vector2.get(i),2);

            pSum += vector1.get(i) * vector2.get(i);

        }
        int n = vector1.size();
        double num = pSum - ((sum1*sum2)/n);
        double den = Math.sqrt( (sum1Sq - Math.pow(sum1,2)/n) * (sum2Sq - Math.pow(sum2,2)/n) );

        if (den ==0){
            return 0;
        }else {
            return num/den;
        }

    }

    public static void main(String[] args){
        MyVector vector1 = new MyVector();
        vector1.addByArray(new double[]{0.0, 0.0});

        MyVector vector2 = new MyVector();
        vector2.addByArray(new double[]{3.0, 4.0});

        System.out.println(getSimDistance(vector1,vector2));

    }

}
