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

    public static List<List<Double>> convertSampleToExtendedList(double[][][] sample, int type){
        List<List<Double>> list = new ArrayList<List<Double>>(){};
        for (int i = 0; i < sample[type].length; i++) {
            List<Double> tmp = new ArrayList<Double>();
            tmp.add(1.0);
            for (int j = 0; j < sample[type][i].length; j++) {
                tmp.add(sample[type][i][j]);
            }
            tmp.remove(5);
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
                name = "縺後￥迚�縺ｮ髟ｷ縺�";
                break;
            case 2:
                name = "縺後￥迚�縺ｮ蟷�";
                break;
            case 3:
                name = "闃ｱ蠑√�ｮ髟ｷ縺�";
                break;
            case 4:
                name = "闃ｱ蠑√�ｮ蟷�";
            case 5:
                name = "蜩∫ｨｮ";
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

    public static List<Double> add(List<Double> a, List<Double> b) {
        List<Double> result = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0);
        for(int i = 0; i < a.size(); i++){
            result.set(i, a.get(i)+b.get(i));
        }
        return result;
    }
    
    public static List<Double> sub(List<Double> a, List<Double> b) {
        List<Double> result = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0);
        for(int i = 0; i < a.size(); i++){
            result.set(i, a.get(i)-b.get(i));
        }
        return result;
    }

    public static <T> List<T> split(List<T> list, int begin, int end){
    	List<T> result = new ArrayList<T>(){};
    	for(int i = begin; i < end; i++){
    		result.add(list.get(i));
    	}
    	return result;
    }
    
	public static double euclideanDist(List<Double> test, List<Double> train) {
		double result = 0.0;
		for(int i = 0; i < test.size(); i++){
			result += Math.pow(test.get(i) - train.get(i), 2);
		}
		return result;
	}
}
