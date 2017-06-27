import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
    public static BufferedReader makeBR(String filename) {
        File file = new File(filename);
        FileReader filereader = null;
        try {
            filereader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return new BufferedReader(filereader);
    }

    public static double[][][] makeSample(BufferedReader br){
        String str;
        double[][][] sample;
        sample = new double[3][50][5];
        try {
            int line = 0;
            while((str = br.readLine()) != null) {
                List<String> irisdata = Arrays.asList(str.split(" ", 0));
                for(int k = 0; k < 5; k++){
                    sample[line/50][line%50][k] = Double.parseDouble(irisdata.get(k));
                }
                line += 1;
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return sample;
    }

    public static List<List<Double>> convertSampleToList(double[][][] sample, int type){
        List<List<Double>> list = new ArrayList<List<Double>>(){};
        for (int i = 0; i < sample[type].length; i++) {
            List<Double> tmp = new ArrayList<Double>();
            for (int j = 0; j < sample[type][i].length; j++) {
                tmp.add(sample[type][i][j]);
            }
            list.add(tmp);
        }
        return list;
    }

    public static String getIrisName(int i) {
        String name = "";
        switch(i){
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

    public static double dot(List<Double> weight, List<Double> x) {
        double result = 0.0;
        for(int i = 0; i < weight.size(); i++){
            result += weight.get(i)*x.get(i);
        }
        return result;
    }


}
