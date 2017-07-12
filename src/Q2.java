import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2{
    public static String getIrisName(int d) {
        String name = "";
        switch(d){
            case 1:
                name = "Setosa";
                break;
            case 2:
                name = "Versicolor";
                break;
            case 3:
                name = "Virginica";
                break;
        }
        return name;
    }

    public static String getElementName(int i) {
        String name = "";
        switch(i){
            case 1:
                name = "がく片の長さ";
                break;
            case 2:
                name = "がく片の幅";
                break;
            case 3:
                name = "花弁の長さ";
                break;
            case 4:
                name = "花弁の幅";
            case 5:
                name = "品種";
        }
        return name;
    }

    public static void main(String[] args) {
        File file = new File("./data/iris.txt");
        FileReader filereader = null;
        try {
            filereader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(filereader);

        // make iris array
        String str;
        double[][][] sample;
        sample = new double[3][50][5];
        try {
            int line = 0;
            while((str = br.readLine()) != null) {
                int type = 0;
                if (line <= 49){
                    type = 0;
                } else if (line <= 99){
                    type = 1;
                } else {
                    type = 2;
                }
                String[] irisdata = str.split(" ", 0);

                for(int k = 0; k < 5; k++){
                    sample[line/50][line%50][k] = Double.parseDouble(irisdata[k]);
                }
                line += 1;
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        double[][] sum;
        sum = new double[3][5];
        for (int type = 0; type < sample.length; type++){
            for(int i = 0; i < sample[type].length; i++){
                for(int j = 0; j < sample[type][i].length; j++) {
                    sum[type][j] += sample[type][i][j];
                }
            }
        }


        double[][] average;
        average = new double[3][5];
        for (int type = 0; type < average.length; type++){
            for(int i = 0; i < sum[type].length; i++){
                average[type][i] = sum[type][i] / 50.0;
                System.out.println(getIrisName(type+1) + " : " + average[type][i]);
            }
        }

        double[][] sdev;
        double[][] siguma;
        sdev = new double[3][5];
        siguma = new double[3][5];
        for (int type = 0; type < sample.length; type++){
            for(int i = 0; i < sample[type].length; i++){
                for(int j = 0; j < sample[type][i].length; j++){
                    siguma[type][j] += (sample[type][i][j] - average[type][j])*(sample[type][i][j] - average[type][j]);
                }
            }
            for(int j = 0; j < sample[type][0].length; j++) {
                sdev[type][j] = Math.sqrt(siguma[type][j] / 50.0);
            }
        }

        for(int i = 0; i < sdev.length; i++){
            for(int j = 0; j < sdev[i].length; j++){
                System.out.println(getIrisName(i+1) + " " + getElementName(j+1) + " : " + sdev[i][j]);
            }
        }
    }
}

