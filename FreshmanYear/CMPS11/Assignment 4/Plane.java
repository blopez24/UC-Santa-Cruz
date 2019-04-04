
class Point
{
	private double x;
	private double y;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getDistance(Point point)
	{
		double x2 = point.getX();
		double y2 = point.getY();
		
		double x2Minusx1Squared = Math.pow((x2 - this.x), 2);
		double y2Minusy1Squared = Math.pow((y2 - this.y), 2);
		
		return Math.sqrt(x2Minusx1Squared + y2Minusy1Squared);
	}
}

abstract class Shape
{
	public abstract double getArea();
	
	public abstract double getPerimeter();
}

interface Symmetric
{
	Point getPointOfSymmetry();
}

class Triangle extends Shape
{
	private Point p1;
	private Point p2;
	private Point p3;
	
	public Triangle(Point firstPoint, Point secondPoint, Point thirdPoint)
	{
		this.p1 = firstPoint;
		this.p2 = secondPoint;
		this.p3 = thirdPoint;
	}
	
	public Point getFirstPoint()
	{
		return this.p1;
	}
	
	public Point getSecondPoint()
	{
		return this.p2;
	}
	
	public Point getThirdPoint()
	{
		return this.p3;
	}
	
	public double getArea()
	{	
		double ax = p1.getX();
		double ay = p1.getY();
		
		double bx = p2.getX();
		double by = p2.getY();
		
		double cx = p3.getX();
		double cy = p3.getY();
		
		return Math.abs( (ax*(by - cy) + bx*(cy - ay) + cx*(ay - by))/2 );
	}
	
	public double getPerimeter()
	{
		double ax = p1.getX();
		double ay = p1.getY();
		
		double bx = p2.getX();
		double by = p2.getY();
		
		double cx = p3.getX();
		double cy = p3.getY();
		double lengthA = Math.sqrt((Math.pow((by - ay), 2) + Math.pow((bx - ax), 2)));
		double lengthB = Math.sqrt((Math.pow((cy - by), 2) + Math.pow((cx - bx), 2)));
		double lengthC = Math.sqrt((Math.pow((ay - cy), 2) + Math.pow((ax - cx), 2)));
		
		return lengthA + lengthB + lengthC;
	}
}

class Rectangle extends Shape
{
	private Point p1;
	
	private double length;
	private double width;
	
	public Rectangle(Point topLeftPoint, double length, double width)
	{
		p1 = topLeftPoint;
		this.length = length;
		this.width = width;
	}
	
	public Point getTopLeftPoint()
	{
		return p1;
	}
	
	public double getLength()
	{
		return this.length;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getArea()
	{	
		return this.width * this.length;
	}
	
	public double getPerimeter()
	{
		return (2 * this.length) + (2 * this.width);
	}
}

class Trapezoid extends Shape
{
	private Point p1;
	private Point p2;
	
	private double topSide;
	private double bottomSide;
	
	public Trapezoid(Point topLeftPoint, Point bottomLeftPoint, double topSide, double bottomSide)
	{
		this.p1 = topLeftPoint;
		this.p2 = bottomLeftPoint;
		this.topSide = topSide;
		this.bottomSide = bottomSide;
	}
	
	public Point getTopLeftPoint()
	{
		return this.p1;
	}
	
	public Point getBottomLeftPoint()
	{
		return this.p2;
	}
	
	public double getTopSide()
	{
		return this.topSide;
	}
	
	public double getBottomSide()
	{
		return this.bottomSide;
	}
	
	public double getArea()	
	{	
		double height = Math.abs(this.getTopLeftPoint().getY() - this.getBottomLeftPoint().getY());
		
		return ( (this.getTopSide() + this.getBottomSide()) / 2 ) * height;
	}
	
	public double getPerimeter()
	{
		double x1, y1, x2, y2;
		double x3, y3, x4, y4;
		
		x1 = this.getTopLeftPoint().getX();
		y1 = this.getTopLeftPoint().getY();
		x2 = this.getBottomLeftPoint().getX();
		y2 = this.getBottomLeftPoint().getY();
		
		x3 = this.getTopLeftPoint().getX() + this.getTopSide();
		y3 = this.getTopLeftPoint().getY();
		x4 = this.getBottomLeftPoint().getX() + this.getBottomSide();
		y4 = this.getBottomLeftPoint().getY();
		
		double leftSide = Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
		
		double rightSide = Math.sqrt((Math.pow((x4 - x3), 2) + Math.pow((y4 - y3), 2)));
		
		return this.getTopSide() + this.getBottomSide() + leftSide + rightSide;
	}
}

class Circle extends Shape implements Symmetric
{
	private Point p1;
	private double radius;
	
	public Circle(Point center, double radius)
	{
		p1 = center;
		this.radius = radius;
	}
	
	public Point getCenter()
	{
		return this.p1;
	}
	
	public double getRadius()
	{
		return this.radius;
	}
	
	public double getArea() 
	{
		return Math.PI * radius * radius;
	}

	public double getPerimeter() 
	{
		return 2 * Math.PI * radius;
	}
	
	public Point getPointOfSymmetry() 
	{
		return this.p1;
	}
}

class EquilateralTriangle extends Triangle implements Symmetric
{
	private double side;
	
	public EquilateralTriangle(Point topPoint, double side)
	{
		super(topPoint, 
		new Point(topPoint.getX() + (-side/2), topPoint.getY() - Math.sqrt(Math.pow(side, 2) - Math.pow(side/2, 2))),
		new Point(topPoint.getX() + (side/2), topPoint.getY() - Math.sqrt(Math.pow(side, 2) - Math.pow(side/2, 2))));
		this.side = side;
	}
	
	public Point getTopPoint()
	{
		return super.getFirstPoint();
	}
	
	public double getSide()
	{
		return this.side;
	}
	
	public double getArea()
	{	
		return super.getArea();
	}
	
	public double getPerimeter()
	{
		return super.getPerimeter();
	}

	public Point getPointOfSymmetry()	
	{
		double halfOfSide = this.getSide()/2;
		double newX = super.getFirstPoint().getX();
		double newY = super.getSecondPoint().getY() + (halfOfSide * Math.tan(30 * (Math.PI/180) ) );
		
		Point pOfS = new Point(newX, newY);
		return pOfS;
	}
}

class Square extends Rectangle implements Symmetric
{
	public Square(Point topLeft, double side)
	{
		super(topLeft, side, side);
	}
	
	public double getSide()
	{
		return super.getLength();
	}
	
	public double getArea()
	{	
		return super.getArea();
	}
	
	public double getPerimeter()
	{
		return super.getPerimeter();
	}

	public Point getPointOfSymmetry()
	{
		double newX = super.getTopLeftPoint().getX() + this.getSide()/2;
		double newY = super.getTopLeftPoint().getY() - this.getSide()/2;
		
		Point pOfS = new Point(newX, newY);
		return pOfS;
	}
}

public class Plane 
{
	private Shape[] shapes;
	private int count = 0;
	
	public Plane()
	{
		this.shapes = new Shape[0];
	}
	
	public Shape[] getShape()
	{
		return this.shapes;
	}
	
	public void addShape(Shape shape)
	{
		count++;
		Shape[] newShapes = new Shape[count];
		
		for(int i = 0; i < shapes.length; i++)
		{
			newShapes[i] = shapes[i];
		}
		
		newShapes[count - 1] = shape;
		
		shapes = newShapes;
	}
	
	public double getSumOfAreas()
	{
		double sum = 0;
		
		for(int i = 0; i < shapes.length; i++)
		{
			sum += shapes[i].getArea();
		}
		
		return sum;
	}
	
	public double getSumOfPerimeters()
	{
		double sum = 0;
		
		for(int i = 0; i < shapes.length; i++)
		{
			sum += shapes[i].getPerimeter();
		}
		
		return sum;
	}
	
	public Point getCenterOfPointOfSymmetries()
	{
		double tempX = 0;
		double tempY = 0;
		double count = 0;
		
		for(int i = 0; i < shapes.length; i++)
		{
			if(shapes[i] instanceof Symmetric)
			{
				tempX += ((Symmetric) shapes[i]).getPointOfSymmetry().getX();
				tempY += ((Symmetric) shapes[i]).getPointOfSymmetry().getY();
				count++;
			}
		}
		tempX = tempX/count;
		tempY = tempY/count;
		
		if(count == 0)
			return null;
		
		Point pOfS = new Point(tempX, tempY);
		return pOfS;
	}
}
