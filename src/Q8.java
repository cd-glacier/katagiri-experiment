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
        List<List<Double>> setosas = Util.convertSampleToList(sample, 0);
        List<List<Double>> versicolors = Util.convertSampleToList(sample,1);
        List<List<Double>> versinicas = Util.convertSampleToList(sample, 2);

        List<List<Double>> samples = new ArrayList<List<Double>>();
        samples.addAll(setosas);
        samples.addAll(versicolors);



    }
}
