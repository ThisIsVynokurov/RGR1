package consoleTasks;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetPart extends Interpolator {
	
	public TreeSetPart() {
		this.set = new TreeSet<>();
	}
	
	private final TreeSet<Point2D> set;

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public int numPoints() {
		return set.size();
	}

	@Override
	public void addPoint(Point2D pt) {
		set.add(pt);
	}

	@Override
	public Point2D getPoint(int i) {
		var iterator = getIterator();
		
		for (int count = 0; iterator.hasNext() && count < i; count++) {
			iterator.next();
		}
		if (iterator.hasNext()) return iterator.next();
		else return null;
	}
	
	public Iterator<Point2D> getIterator(){
		return set.iterator();
	}

	@Override
	public void setPoint(int i, Point2D pt) {
		var iterator = getIterator();
		
		for (int count = 0; iterator.hasNext() && count < i; count++) {
			iterator.next();
		}
		var prevPoint = iterator.next();
		prevPoint.setX(pt.getX());
		prevPoint.setY(pt.getY());
		
	}

	@Override
	public void removeLastPoint() {
		set.pollLast();
	}

	@Override
	public void sort() {
	}
}
