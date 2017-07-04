
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q13 {
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
        List<List<Double>> train = Util.split(setosas, begin, mid);
        train.addAll(Util.split(versicolors, begin, mid));
        train.addAll(Util.split(versinicas, begin, mid));
        List<List<Double>> test = Util.split(setosas, mid, end);
        test.addAll(Util.split(versicolors, mid, end));
        test.addAll(Util.split(versinicas, mid, end));

        // study and acognition
        List<Integer> result = new ArrayList<Integer>(){};
        for (int i = 0; i < test.size(); i++){
        	double min = 999999999999999.0;
        	int index = -1;
        	for (int j = 0; j < train.size(); j++){
        		double tmp = Util.euclideanDist(test.get(i), train.get(j));
        		if (tmp < min) {
        			min = tmp;
            		index = j;
        		}
        	}
        	result.add(index);
        }
        
        double correctCount = 0.0;
        for (int i = 0; i < result.size(); i++){
        	if(i < 20 && result.get(i) < 20) {
        		correctCount++;
        	} else if (i >= 40 && result.get(i) >= 40) {
        		correctCount++;
        	} else if (20 <= i && i < 40 && 20 <= result.get(i) && result.get(i) < 40) {
        		correctCount++;
        	}
        }
        
       System.out.println(correctCount/result.size()*100 + "%");
    }
}
