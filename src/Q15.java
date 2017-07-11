import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Q15 {
    public static double getRecognition(List<List<Double>> train, List<List<Double>> test){
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
            if(i < test.size()/3 && result.get(i) < train.size()/3) {
                correctCount++;
            } else if (i >= test.size()*2/3 && result.get(i) >= train.size()*2/3) {
                correctCount++;
            } else if (test.size()/3 <= i && i < test.size()*2/3 && train.size()/3 <= result.get(i) && result.get(i) < train.size()*2/3) {
                correctCount++;
            }
        }
        return correctCount;
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

        // Q13
        int begin = 0;
        int mid = 20;
        int end = 50;
        List<List<Double>> train = Util.split(setosas, begin, mid);
        train.addAll(Util.split(versicolors, begin, mid));
        train.addAll(Util.split(versinicas, begin, mid));
        List<List<Double>> test = Util.split(setosas, mid, end);
        test.addAll(Util.split(versicolors, mid, end));
        test.addAll(Util.split(versinicas, mid, end));

        double correctCount = getRecognition(test, test);
        System.out.println(correctCount/((end-mid)*3)*100 + "%");

        // Q14
        mid = 10;
        train = Util.split(setosas, begin, mid);
        train.addAll(Util.split(versicolors, begin, mid));
        train.addAll(Util.split(versinicas, begin, mid));
        test = Util.split(setosas, mid, end);
        test.addAll(Util.split(versicolors, mid, end));
        test.addAll(Util.split(versinicas, mid, end));

        correctCount = getRecognition(test, test);
        System.out.println(correctCount/((end-mid)*3)*100 + "%");

    }
}

