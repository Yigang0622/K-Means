package Algo;

import java.util.Objects;
import java.util.Vector;

/**
 * Created by Mike on 12/9/2016.
 */
public class MyVector extends Vector<Double> {

    public MyVector(){

    }

    public MyVector(double[] arr){
        addByArray(arr);
    }

    public MyVector(int size){
        for(int i=0;i<size;i++){
            add(0.0);
        }

    }

    public void addByArray(double[] arr){
        for (Double val:arr){
            super.add(val);
        }
    }

    public void printVector(){
        System.out.print("[");
        for (int i=0;i<this.size();i++){
            System.out.print(get(i));
            if (i !=this.size()-1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public boolean isSameVector(MyVector vector){

        if (vector.size() != size()){
            return false;
        }


        boolean same = true;

        for (int i=0;i<vector.size();i++){
            if (!Objects.equals(vector.get(i), get(i))){
                same = false;
            }
        }

        return same;

    }

}
