package consoleTasks;

import java.io.*;
import java.util.StringTokenizer;

public class TreeSetDone extends TreeSetPart {
	
	public TreeSetDone() {
		super();
	}
	
	
	public void fromFile (String fileName) {
		try (BufferedReader in = new BufferedReader (new FileReader(fileName))){
			String strng;
			clear();
			while ((strng = in.readLine()) != null) {
				if (strng.trim().isEmpty()) continue;
				StringTokenizer st = new StringTokenizer(strng);
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				addPoint (new Point2D(x, y));
			}
			in.close();
		}catch (IOException e) {
			System.err.println ("Проблема із прочитанням з файлу: " + e.getMessage());
		}
	}
	
	public void toFile (String fileName) {
		try (PrintWriter out = new PrintWriter (new FileWriter(fileName))){
			out.printf ("%9s%25s\n", "x", "y");
			var iterator = getIterator();
			while (iterator.hasNext()) {
				var val = iterator.next();
				out.printf("%f\t%f\n", val.getX(), val.getY());
			}
		}catch (IOException e) {
			System.err.println ("Помилка запису до файлу: " + e.getMessage());
		}
	}
	
	public static void main (String[] args) {
		TreeSetDone treeSetDone = new TreeSetDone();
		
		String inputFile = "input.txt";
		String outputFile = "output.txt";
		
		treeSetDone.fromFile(inputFile);
		System.out.println("Точки, що були отримані з файлу: ");
		var iterator = treeSetDone.getIterator();
		while (iterator.hasNext()) {
			var point = iterator.next();
			System.out.println ("Точки: (" + point.getX() + "," + point.getY() + ")");
		}
		
		treeSetDone.toFile(outputFile);
		System.out.println("Точки записані до файлу: " + outputFile);
	}
}