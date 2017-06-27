import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Q7 {
    public static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iterator, boolean parallel) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
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
                List<String> irisdata = Arrays.asList(str.split(" ", 0));

                for(int k = 0; k < 5; k++){
                    sample[line/50][line%50][k] = Double.parseDouble(irisdata.get(k));
                }
                line += 1;
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        // array to list
        HashMap<String, List<List<Double>>> samples;
        List<List<Double>> setosas = new ArrayList<List<Double>>(){};
        List<List<Double>> versicolors = new ArrayList<List<Double>>(){};
        List<List<Double>> versinicas = new ArrayList<List<Double>>(){};
        for(int type = 0; type < 3; type++) {
            for (int i = 0; i < sample[type].length; i++) {
                List<Double> tmp = new ArrayList<Double>();
                for (int j = 0; j < sample[type][i].length; j++) {
                    tmp.add(sample[type][i][j]);
                }
                switch (type) {
                    case 0:
                        setosas.add(tmp);
                        break;
                    case 1:
                        versicolors.add(tmp);
                        break;
                    case 2:
                        versinicas.add(tmp);
                        break;
                }
            }
        }

        List<Double> weight = Arrays.asList(0.33, 0.23, -0.38, -0.05, 0.47);
        List<Double> outSetosas = setosas.stream().map(x -> dot(weight, x)).collect(Collectors.toList());
        List<Double> outVersicolors = versicolors.stream().map(x -> dot(weight, x)).collect(Collectors.toList());
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

