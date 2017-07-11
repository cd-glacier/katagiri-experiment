import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Q17 {
    public static double getDisformation(List<List<Double>> train, List<List<Double>> averageVectors){
        List<Double> eDistances = train.stream().map( x -> {
            List<Double> nearest = getNearest(x, averageVectors);
            return Util.euclideanDist(x, nearest);
        }).collect(Collectors.toList());

        return Util.sum(eDistances);
    }

    public static List<Double> getNearest(List<Double> x, List<List<Double>> vecs){
        List<Double> distances = vecs.stream().map( v -> {
            return Util.euclideanDist(x, v);
        }).collect(Collectors.toList());

        double min = 9999999999999999999999.0;
        int index = -1;
        for(int i = 0; i < distances.size(); i++){
            if(min > distances.get(i)){
                min = distances.get(i);
                index = i;
            }
        }
        return vecs.get(index);
    }

    public static List<List<Double>> getClustors(List<List<List<Double>>> clustors){
        return clustors.stream().map(clustor -> {
            return getAverageVector(clustor);
        }).collect(Collectors.toList());
    }

    public static List<Double> getAverageVector(List<List<Double>> clustor){
        Optional<List<Double>> sum = clustor.stream().reduce((v, a) -> {
            return Util.add(v, a);
        });
        List<Double> average = sum.get().stream().map( x -> x / (double)clustor.size()).collect(Collectors.toList());
        return average;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = Util.makeBR("./data/iris.txt");

        // make iris array
        double[][][] sample;
        sample = Util.makeSample(br);
        br.close();

        // array to list
        List<List<Double>> setosas = Util.convertSampleToExtendedList(sample, 0);
        List<List<Double>> versicolors = Util.convertSampleToExtendedList(sample,1);
        List<List<Double>> versinicas = Util.convertSampleToExtendedList(sample, 2);

        // split data
        int begin = 0;
        int mid = 20;
        int end = 50;
        List<List<Double>> trainSetosas = Util.split(setosas, begin, mid);
        List<List<Double>> trainVersicolors = Util.split(versicolors, begin, mid);
        List<List<Double>> trainVersinicas = Util.split(versinicas, begin, mid);

        // learn
        int k = 1;
        List<List<List<Double>>> firstClustors = Util.split(trainSetosas, k);
        double disformation = getDisformation(trainSetosas, getClustors(firstClustors));

        List<List<Double>> clustors = getClustors(firstClustors);
        List<Integer> clustorIndexes = trainSetosas.stream().map( x -> {
            List<Double> nearest = getNearest(x, clustors);
            int index = -1;
            for(int i = 0; i < clustors.size(); i++){
                if(Util.equals(clustors.get(i), nearest)) {
                    index = i;
                }
            }
            return index;
        }).collect(Collectors.toList());

        System.out.println(clustorIndexes);
    }
}
