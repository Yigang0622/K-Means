package ColorPicker;

import Algo.Cluster;
import Algo.KMeans;
import Algo.MyVector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 12/9/2016.
 */
public class ThemeColorPicker {

    private BufferedImage image;

    List<MyVector> vectors;

    private String path;

    public void setColorNumber(int colorNumber) {
        this.colorNumber = colorNumber;
    }

    private int colorNumber = 5;

    public ThemeColorPicker(String path){
        image = FileUtil.loadImg(path);
        vectors = generateColorVectors();
        this.path = path;
    }

    public void getThemeColor(){

        KMeans kMeans = new KMeans(vectors,colorNumber);
        kMeans.startClustering();
        List<Cluster> clusters = kMeans.getClusters();

        List<MyVector> vectors = new ArrayList<>();


        for (Cluster cluster:clusters){
            MyVector vector  =  cluster.getCenterVector();
            vectors.add(vector);
            System.out.println(rgbToHex(vector));
        }

        new PaletteGUI(vectors,path);

    }

    private String rgbToHex(MyVector vector){
        int r = vector.get(0).intValue();
        int g = vector.get(1).intValue();
        int b = vector.get(2).intValue();
        String hex = String.format("#%02x%02x%02x", r, g, b);
        return hex;
    }

    private List<MyVector> generateColorVectors(){
        List<MyVector> vectors = new ArrayList<>();

        for (int i = 0;i<image.getWidth();i++){
            for (int j=0;j<image.getHeight();j++){
                double r = getR(i,j);
                double g = getG(i,j);
                double b = getB(i,j);

                vectors.add(new MyVector(new double[]{r,g,b}));

            }
        }

        return vectors;

    }


    private double getR(int x,int y){
        int rgb = image.getRGB(x, y);
        int r = (rgb & 0xff0000) >> 16;
        return r;
    }

    private double getG(int x,int y){
        int rgb = image.getRGB(x, y);
        int g = (rgb & 0xff00) >> 8;
        return g;
    }

    private double getB(int x,int y){
        int rgb = image.getRGB(x, y);
        int b = (rgb & 0xff);
        return b;
    }

    public static void main(String[] args){
        ThemeColorPicker picker = new ThemeColorPicker("/Users/Mike/Desktop/1.jpg");
        picker.setColorNumber(5);
        picker.getThemeColor();
    }

}

class FileUtil{
    public static BufferedImage loadImg(String path){
        File imgFile = new File(path);
        try {
            return ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
