import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Q7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Util.makeBR("./data/iris.txt");

        // make iris array
        double[][][] sample;
        sample = Util.makeSample(br);
        br.close();

        // array to list
        List<List<Double>> setosas = Util.convertSampleToList(sample, 0);
        List<List<Double>> versicolors = Util.convertSampleToList(sample,1);
        List<List<Double>> versinicas = Util.convertSampleToList(sample, 2);

        List<Double> weight = Arrays.asList(0.33, 0.23, -0.38, -0.05, 0.47);
        List<Double> outSetosas = setosas.stream().map(x -> Util.dot(weight, x)).collect(Collectors.toList());
        List<Double> outVersicolors = versicolors.stream().map(x -> Util.dot(weight, x)).collect(Collectors.toList());
        List<Double> result = new ArrayList<Double>();
        for(int i = 0; i < outSetosas.size(); i++){
            if (outSetosas.get(i).doubleValue() - outVersicolors.get(i).doubleValue() > 0){
                result.add(1.0);
            } else if (outSetosas.get(i).doubleValue() - outVersicolors.get(i).doubleValue() <= 0){
                result.add(2.0);
            }
        }

        int count = 0;
        for (int i = 0; i < setosas.size(); i++){
            if(result.get(i).intValue() == 1 && result.get(i).intValue() == setosas.get(i).get(4).intValue()){
                count++;
            }
        }
        System.out.println(count/result.size()*100 + "%");
    }
}

