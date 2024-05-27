package consoleTasks;

import java.util.*;

public class ListInterpolation extends Interpolator {
	
	private List<Point2D> data = null;
	
	public ListInterpolation (List<Point2D> data) {
		this.data = data;
	}
	
	public ListInterpolation() {
		data = new ArrayList<Point2D>();
	}
	
	public ListInterpolation (Point2D[] data) {
		this();
		for (Point2D pt: data)
			this.data.add(pt);
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public int numPoints() {
		return data.size();
	}

	@Override
	public void addPoint(Point2D pt) {
		data.add(pt);
	}

	@Override
	public Point2D getPoint(int i) {
		return data.get(i);
	}

	@Override
	public void setPoint(int i, Point2D pt) {
		data.set(i, pt);
	}

	@Override
	public void removeLastPoint() {
		data.remove(data.size()-1);
	}

	@Override
	public void sort() {
		java.util.Collections.sort(data);
	}
	
	public static void main (String[] args) {
		
		ListInterpolation fun = new ListInterpolation();
		int num;
		double x;
		java.util.Scanner in = new java.util.Scanner (System.in);
		
		System.out.println ("ListInterpolation\n");
		
		//Adding clean method test (Додавання первірки методу clear())
		fun.addPoint(new Point2D(1.0, Math.sin(1.0)));
		fun.clear();
		System.out.println ("Після очистки (метод clear()), кількість точок: " + fun.numPoints());
		
		//Adding setPoint method test (Додавання перевірки методу setPoint())
		fun.addPoint(new Point2D(2.0, Math.sin(2.0)));
		fun.setPoint(0, new Point2D(3.0, Math.sin(3.0)));
		System.out.println ("Після встановлення точки (метод setPoint()), точка 1: " + fun.getPoint(0));
		
		//Adding removeLastPoint method test (Додавання перевірки методу removeLastPoint())
		fun.removeLastPoint();
		System.out.println ("Після використання методу removeLastPoint(), кількість точок: " + fun.numPoints());
		System.out.print("\n");
		
		do {
			System.out.print("Кількість точок: ");
			num = in.nextInt();
		} while (num <= 0);
		
		for (int i = 0; i < num; i++) {
			x = 1.0 + (5.0 - 1.0)*Math.random();
			fun.addPoint(new Point2D(x, Math.sin(x)));
		}
		
		System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
		System.out.println("Несортований набір: ");
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
		
		fun.sort();
		System.out.println("Відсортований набір: ");
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
		
		System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
		System.out.println("Максимальне значення x: " + fun.getPoint(fun.numPoints()-1).getX());
		
		x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints()-1).getX());
		System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
		System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
		System.out.println("Абсолютна помилка = " + Math.abs(fun.evalf(x)-Math.sin(x)));
		
		//Checking interpolation error dependence (Перевірка залежності помилки інтерполяції)
		System.out.println ("\nПеревірка залежності помилки інтерполяції:");
		int[] pointsCounts = {10, 50, 100, 500};
		Random rand = new Random ();
		for (int count : pointsCounts) {
			fun.clear();
			for (int i = 0; i < count; i++) {
				x = 1.0 + i*(5.0-1.0)/(count-1);
				fun.addPoint(new Point2D(x, Math.sin(x)));
			}

			double totalError = 0.0;
			int testPoints = 10;
			for (int i = 0; i < testPoints; i++) {
				x = 1.0 + (5.0 - 1.0) * rand.nextDouble();
				double interpolatedValue = fun.evalf(x);
				double exactValue = Math.sin(x);
				double error = Math.abs(interpolatedValue - exactValue);
				totalError += error;
			}
			double averageError = totalError/testPoints;
			System.out.println ("Кількість точок: " + count + "\nПомилка інтерполяції: " + averageError);
		}
	}
}
