import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Q8 {
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

        List<List<Double>> samples = new ArrayList<List<Double>>();
        samples.addAll(setosas);
        samples.addAll(versicolors);
        List<Double> weight = Arrays.asList(0.33, 0.23, -0.38, -0.05, 0.47);


    }
}
