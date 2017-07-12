import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Q1{
	public static String getIrisName(int i) {
		String name = "";
		switch(i){
		case 1:
			name = "Setosa";
		case 2:
			name = "Versicolor";
		case 3:
			name = "Virginica";
		}
		return name;
	}


	public static void main(String[] args) throws IOException{
		File file = new File("./data/iris.txt");
		FileReader filereader = null;
		try {
			filereader = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(filereader);

		String str = br.readLine();
		int line = 0;
		int length = 0;
		String irisClass = "";
		while(str != null) {
			if (line == 82){
				irisClass = getIrisName(Integer.parseInt(str.split(" ", 0)[4]));
				length = Integer.parseInt(str);
			}
		}
		System.out.println("品種 : " + irisClass);
		System.out.println("がく弁長 : " + length);
		br.close();
	}
}
