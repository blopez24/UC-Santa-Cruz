import java.util.Scanner;


public class PlaneTest {
	
	/* 
		Test 1: the point class for using positive and negative doubles.
		Should have the following at the bottom of the console
		Please enter 4 doubles for two points
		The x-coor of the first point:
		4.1
		The y-coor of the first point:
		5.5
		The distance between the points:
		2.92
		Please enter 4 doubles for two points
		The x-coor of the first point:
		4.1
		The y-coor of the first point:
		-5.5
		The distance between the points:
		8.88
		Please enter 4 doubles for two points
		The x-coor of the first point:
		-4.1
		The y-coor of the first point:
		5.5
		The distance between the points:
		6.80
		Please enter 4 doubles for two points
		The x-coor of the first point:
		-4.1
		The y-coor of the first point:
		-5.5
		The distance between the points:
		10.80
	*/
	 private static String test1 = "point 4.1 5.5 2.3 3.2 point 4.1 -5.5 2.3 3.2 point -4.1 5.5 2.3 3.2 point -4.1 -5.5 2.3 3.2 q";
		 
	 /* 
		Test 2: the triangle class with a right triangle, an acute triangle and an obtuse triangle
		Should have the following at the bottom of the console
		Please enter 6 doubles for three points of a triangle
		The x-coor of the first point:
		4.3
		The y-coor of the second point:
		4.5
		The x-coor of the third point:
		1.2
		The area:
		3.41
		The perimeter:
		9.10
		Please enter 6 doubles for three points of a triangle
		The x-coor of the first point:
		4.3
		The y-coor of the second point:
		4.5
		The x-coor of the third point:
		1.2
		The area:
		3.41
		The perimeter:
		8.78
		Please enter 6 doubles for three points of a triangle
		The x-coor of the first point:
		3.3
		The y-coor of the second point:
		4.5
		The x-coor of the third point:
		1.2
		The area:
		2.31
		The perimeter:
		8.32
	*/
	 private static String test2 = "triangle 4.3 6.7 1.2 4.5 1.2 6.7 triangle 4.3 5.7 1.2 4.5 1.2 6.7 triangle 3.3 6.7 0.2 4.5 1.2 6.7 q";
			
	 /* 
		Test 3: rectangle class
		Should have the following at the bottom of the console
		Please enter 2 doubles for the topLeftPoint of the rectangle
		Please enter 2 doubles for the length and width of the rectangle
		The y-coor of the top left point:
		1.2
		The length:
		6.1
		The width:
		2.5
		The area:
		15.25
		The perimeter:
		17.20
		Please enter 2 doubles for the topLeftPoint of the rectangle
		Please enter 2 doubles for the length and width of the rectangle
		The y-coor of the top left point:
		1.2
		The length:
		1.4
		The width:
		9.2
		The area:
		12.88
		The perimeter:
		21.20
	*/
	 private static String test3 = "rectangle 5.4 1.2 6.1 2.5 rectangle -5.4 1.2 1.4 9.2 q";
			
	/* 
		Test 4: trapezoid class where topLeftPoint's x value is less than bottomLeftPoint's x value
		Should have the following at the bottom of the console
		Please enter 4 doubles for the two points of the trapezoid
		Please enter 2 doubles for the sides of the trapezoid
		The x-coor of the top left point:
		1.2
		The y-coor of the bottom left point:
		-3.4
		The top side length:
		5.1
		The bottom side length:
		5.1
		The area:
		34.68
		The perimeter:
		24.01
		Please enter 4 doubles for the two points of the trapezoid
		Please enter 2 doubles for the sides of the trapezoid
		The x-coor of the top left point:
		1.3
		The y-coor of the bottom left point:
		1.3
		The top side length:
		10.3
		The bottom side length:
		6.8
		The area:
		29.93
		The perimeter:
		24.99
		Please enter 4 doubles for the two points of the trapezoid
		Please enter 2 doubles for the sides of the trapezoid
		The x-coor of the top left point:
		1.3
		The y-coor of the bottom left point:
		-1.3
		The top side length:
		6.8
		The bottom side length:
		10.9
		The area:
		53.98
		The perimeter:
		32.00
	*/
	private static String test4 = "trapezoid 1.2 3.4 2.4 -3.4 5.1 5.1 trapezoid 1.3 4.8 2.5 1.3 10.3 6.8 trapezoid 1.3 4.8 2.5 -1.3 6.8 10.9 q";
		
	/* 
		Test 5: trapezoid class where topLeftPoint's x value is greater than bottomLeftPoint's x value
		Should have the following at the bottom of the console
		Please enter 4 doubles for the two points of the trapezoid
		Please enter 2 doubles for the sides of the trapezoid
		The x-coor of the top left point:
		4.4
		The y-coor of the bottom left point:
		1.3
		The top side length:
		9.7
		The bottom side length:
		5.4
		The area:
		26.43
		The perimeter:
		26.62
		Please enter 4 doubles for the two points of the trapezoid
		Please enter 2 doubles for the sides of the trapezoid
		The x-coor of the top left point:
		4.4
		The y-coor of the bottom left point:
		1.3
		The top side length:
		3.4
		The bottom side length:
		5.4
		The area:
		15.40
		The perimeter:
		16.44
	*/
	private static String test5 = "trapezoid 4.4 4.8 2.2 1.3 9.7 5.4 trapezoid 4.4 4.8 2.2 1.3 3.4 5.4 q";
		
	/* 
		Test 6: 
		Should have the following at the bottom of the console
		Please enter 2 doubles for the center of the circle
		Please enter 1 double for the radius of the circle
		The x-coor of the center:
		-8.5
		The radius:
		7.3
		The area:
		167.42
		The perimeter:
		45.87
		The x-coor and y-coor of the point of symmetry:
		-8.50 -5.60
	*/
	private static String test6 = "circle -8.5 -5.6 7.3 q";
		
	/* 
		Test 7: equilateraltriangle class
		Should have the following at the bottom of the console
		Please enter 2 doubles for the topPoint of the equilateral triangle
		Please enter 1 double for the side of the equilateral triangle
		The x-coor of the top point:
		8.5
		The x&y-coor of the first point:
		8.50 -5.60
		The x&y-coor of the second point:
		4.85 -11.92
		The x&y-coor of the third point:
		12.15 -11.92
		The side length:
		7.3
		The area:
		23.08
		The perimeter:
		21.90
		The x-coor and y-coor of the point of symmetry:
		8.50 -9.81
	*/
	private static String test7 = "equiltri 8.5 -5.6 7.3 q";
		
	/* 
		Test 8: square class 
		Should have the following at the bottom of the console
		Please enter 2 doubles for the topLeft of the square
		Please enter 1 double for the side of the square
		The y-coor of the top left point:
		5.6
		The side length:
		7.3
		The width:
		7.3
		The area:
		53.29
		The perimeter:
		29.20
		The x-coor and y-coor of the point of symmetry:
		-4.85 1.95
	*/
	private static String test8 = "square -8.5 5.6 7.3 q";
		
	/* 
		Test 9: plane class (empty plane and plane where shapes overlap)
		Should have the following at the bottom of the console
		Please enter shapes
		The sum of the areas:
		0.00
		The sum of the perimeters:
		0.00
		Center of point of symmetries is null
		Please enter shapes
		The sum of the areas:
		15.19
		The sum of the perimeters:
		29.52
		Center of point of symmetries is null
	*/
	private static String test9 = "plane inputDone plane t 3.3 6.7 0.2 4.5 1.2 6.7 r 3.3 6.7 1.4 9.2 inputDone q";
		
	/* 
		Test 10: plane class (plane with multiple points of symmetry and plane with multiple of the same shape)
		Should have the following at the bottom of the console
		Please enter shapes
		The sum of the areas:
		171.03
		The sum of the perimeters:
		53.47
		The x-coor and y-coor of the point of symmetry:
		0.47 -2.57
		Please enter shapes
		The sum of the areas:
		281.47
		The sum of the perimeters:
		132.09
		The x-coor and y-coor of the point of symmetry:
		0.00 -7.71
	*/
	private static String test10 = "plane c -8.5 -5.6 7.3 s 8.5 1.4 1.9 inputDone plane c -8.5 -5.6 7.3 t 3.3 6.7 0.2 4.5 1.2 6.7 tr 1.3 4.8 2.5 -1.3 6.8 10.9 tr 1.2 3.4 2.4 -3.4 5.1 5.1 e 8.5 -5.6 7.3 inputDone q";
			
	
	public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    Point p1;
	    Point p2;
	    Point p3;
	    double s1;
	    double s2;
	    String command = "";
	    while (command != "q") {
	      command = scan.next();
	      switch(command) {
	        case "point":
	          System.out.println("Please enter 4 doubles for two points");
	          p1 = new Point(scan.nextDouble(), scan.nextDouble());
	          p2 = new Point(scan.nextDouble(), scan.nextDouble());
	          System.out.println("The x-coor of the first point:");
	          System.out.println(p1.getX());
	          System.out.println("The y-coor of the first point:");
	          System.out.println(p1.getY());
	          System.out.println("The distance between the points:");
	          System.out.printf("%.2f%n",p1.getDistance(p2));
	          break;
	        case "triangle":
	          System.out.println("Please enter 6 doubles for three points of a triangle");
	          p1 = new Point(scan.nextDouble(), scan.nextDouble());
	          p2 = new Point(scan.nextDouble(), scan.nextDouble());
	          p3 = new Point(scan.nextDouble(), scan.nextDouble());
	          Triangle t = new Triangle(p1,p2,p3);
	          System.out.println("The x-coor of the first point:");
	          System.out.println(t.getFirstPoint().getX());
	          System.out.println("The y-coor of the second point:");
	          System.out.println(t.getSecondPoint().getY());
	          System.out.println("The x-coor of the third point:");
	          System.out.println(t.getThirdPoint().getX());
	          System.out.println("The area:");
	          System.out.printf("%.2f%n",t.getArea());
	          System.out.println("The perimeter:");
	          System.out.printf("%.2f%n",t.getPerimeter());
	          break;
	        case "rectangle":
	          System.out.println("Please enter 2 doubles for the topLeftPoint of the rectangle");
	          p1 = new Point(scan.nextDouble(),scan.nextDouble());
	          System.out.println("Please enter 2 doubles for the length and width of the rectangle");
	          s1 = scan.nextDouble();
	          s2 = scan.nextDouble();
	          Rectangle r = new Rectangle(p1,s1,s2);
	          System.out.println("The y-coor of the top left point:");
	          System.out.println(r.getTopLeftPoint().getY());
	          System.out.println("The length:");
	          System.out.println(r.getLength());
	          System.out.println("The width:");
	          System.out.println(r.getWidth());
	          System.out.println("The area:");
	          System.out.printf("%.2f%n",r.getArea());
	          System.out.println("The perimeter:");
	          System.out.printf("%.2f%n",r.getPerimeter());
	          break;
	        case "trapezoid":
	        		System.out.println("Please enter 4 doubles for the two points of the trapezoid");
		          p1 = new Point(scan.nextDouble(),scan.nextDouble());
		          p2 = new Point(scan.nextDouble(),scan.nextDouble());
		          System.out.println("Please enter 2 doubles for the sides of the trapezoid");
		          s1 = scan.nextDouble();
		          s2 = scan.nextDouble();
		          Trapezoid tr = new Trapezoid(p1,p2,s1,s2);
		          System.out.println("The x-coor of the top left point:");
		          System.out.println(tr.getTopLeftPoint().getX());
		          System.out.println("The y-coor of the bottom left point:");
		          System.out.println(tr.getBottomLeftPoint().getY());
		          System.out.println("The top side length:");
		          System.out.println(tr.getTopSide());
		          System.out.println("The bottom side length:");
		          System.out.println(tr.getBottomSide());
		          System.out.println("The area:");
		          System.out.printf("%.2f%n",tr.getArea());
		          System.out.println("The perimeter:");
		          System.out.printf("%.2f%n",tr.getPerimeter());
	          break;
	        case "circle":
	          System.out.println("Please enter 2 doubles for the center of the circle");
	          p1 = new Point(scan.nextDouble(),scan.nextDouble());
	          System.out.println("Please enter 1 double for the radius of the circle");
	          s1 = scan.nextDouble();
	          Circle c = new Circle(p1,s1);
	          System.out.println("The x-coor of the center:");
	          System.out.println(c.getCenter().getX());
	          System.out.println("The radius:");
	          System.out.println(c.getRadius());
	          System.out.println("The area:");
	          System.out.printf("%.2f%n",c.getArea());
	          System.out.println("The perimeter:");
	          System.out.printf("%.2f%n",c.getPerimeter());
	          System.out.println("The x-coor and y-coor of the point of symmetry:");
	          System.out.printf("%.2f",c.getPointOfSymmetry().getX());
	          System.out.print(" ");
	          System.out.printf("%.2f%n",c.getPointOfSymmetry().getY());
	          break;
	        case "equiltri":
	        		System.out.println("Please enter 2 doubles for the topPoint of the equilateral triangle");
		          p1 = new Point(scan.nextDouble(),scan.nextDouble());
		          System.out.println("Please enter 1 double for the side of the equilateral triangle");
		          s1 = scan.nextDouble();
		          EquilateralTriangle e  = new EquilateralTriangle(p1,s1);
		          System.out.println("The x-coor of the top point:");
		          System.out.println(e.getTopPoint().getX());
		          System.out.println("The x&y-coor of the first point:");
		          System.out.printf("%.2f",e.getFirstPoint().getX());
		          System.out.print(" ");
		          System.out.printf("%.2f%n",e.getFirstPoint().getY());
		          System.out.println("The x&y-coor of the second point:");
		          System.out.printf("%.2f",e.getSecondPoint().getX());
		          System.out.print(" ");
		          System.out.printf("%.2f%n",e.getSecondPoint().getY());
		          System.out.println("The x&y-coor of the third point:");
		          System.out.printf("%.2f",e.getThirdPoint().getX());
		          System.out.print(" ");
		          System.out.printf("%.2f%n",e.getThirdPoint().getY());
		          System.out.println("The side length:");
		          System.out.println(e.getSide());
		          System.out.println("The area:");
		          System.out.printf("%.2f%n",e.getArea());
		          System.out.println("The perimeter:");
		          System.out.printf("%.2f%n",e.getPerimeter());
		          System.out.println("The x-coor and y-coor of the point of symmetry:");
		          System.out.printf("%.2f",e.getPointOfSymmetry().getX());
		          System.out.print(" ");
		          System.out.printf("%.2f%n",e.getPointOfSymmetry().getY());
	          break;
	        case "square":
	        		System.out.println("Please enter 2 doubles for the topLeft of the square");
		          p1 = new Point(scan.nextDouble(),scan.nextDouble());
		          System.out.println("Please enter 1 double for the side of the square");
		          s1 = scan.nextDouble();
		          Square s  = new Square(p1,s1);
		          System.out.println("The y-coor of the top left point:");
		          System.out.println(s.getTopLeftPoint().getY());
		          System.out.println("The side length:");
		          System.out.println(s.getSide());
		          System.out.println("The width:");
		          System.out.println(s.getWidth());
		          System.out.println("The area:");
		          System.out.printf("%.2f%n",s.getArea());
		          System.out.println("The perimeter:");
		          System.out.printf("%.2f%n",s.getPerimeter());
		          System.out.println("The x-coor and y-coor of the point of symmetry:");
		          System.out.printf("%.2f",s.getPointOfSymmetry().getX());
		          System.out.print(" ");
		          System.out.printf("%.2f%n",s.getPointOfSymmetry().getY());
	          break;
	        case "plane":
	          Plane p = new Plane();
	          System.out.println("Please enter shapes");
	          String input = scan.next();
	          while (!input.equals("inputDone")) {
	        	  	if (input.equals("t")) {
	        	  		p.addShape(new Triangle(new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				new Point(scan.nextDouble(),scan.nextDouble())));
	        	  	}
	        	  	else if (input.equals("r")) {
	        	  		p.addShape(new Rectangle(new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				scan.nextDouble(),
	        	  				scan.nextDouble()));
	        	  	}
	        	  	else if (input.equals("tr")) {
	        	  		p.addShape(new Trapezoid(new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				scan.nextDouble(),
	        	  				scan.nextDouble()));
	        	  	}
	        	  	else if (input.equals("c")) {
	        	  		p.addShape(new Circle(new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				scan.nextDouble()));
	        	  	}
	        	  	else if (input.equals("e")) {
	        	  		p.addShape(new EquilateralTriangle(new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				scan.nextDouble()));
	        	  	}
	        	  	else if (input.equals("s")) {
	        	  		p.addShape(new Square(new Point(scan.nextDouble(),scan.nextDouble()),
	        	  				scan.nextDouble()));
	        	  	}
	        	  	input = scan.next();
	          }
	          System.out.println("The sum of the areas:");
	          System.out.printf("%.2f%n",p.getSumOfAreas());
	          System.out.println("The sum of the perimeters:");
	          System.out.printf("%.2f%n",p.getSumOfPerimeters());
	          if (p.getCenterOfPointOfSymmetries() == null) {
	        	  	System.out.println("Center of point of symmetries is null");
	          } else {
	        	  System.out.println("The x-coor and y-coor of the point of symmetry:");
	        	  System.out.printf("%.2f",p.getCenterOfPointOfSymmetries().getX());
	        	  	System.out.print(" ");
	        	  	System.out.printf("%.2f%n",p.getCenterOfPointOfSymmetries().getY());
	          }
	          break;
	        case "q":
	          System.exit(0);
	          break;
	      }
	    }
	    scan.close();
	  }
}
