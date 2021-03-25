import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
    int countPoints=0;        
        for(Point p:s.getPoints())
        {
        	countPoints+=1;
        }	
        
        return countPoints;
    }

    public double getAverageLength(Shape s) {
        double average=getPerimeter(s)/getNumPoints(s);
        return average;
    }

    public double getLargestSide(Shape s) {
        double longestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update longestSide
            if(currDist>longestSide)
            {
            	longestSide=currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return longestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        return 0.0;
    }

    public double getLargestPerimeterMultipleFiles() {
    	double largestPerimeter=0.0;
        for (int i=0;i<6;i++) {
        	FileResource fr = new FileResource();
            Shape s = new Shape(fr);
            double perimeter=getPerimeter(s);
            if(perimeter>largestPerimeter)
            {
            	largestPerimeter=perimeter;
            }
        }
        return largestPerimeter;
        
    }

    public String getFileWithLargestPerimeter() {
    		double largestPerimeter=0.0;
    		int result=1;
    		for (int i=0;i<4;i++) {
        	FileResource fr = new FileResource();
            Shape s = new Shape(fr);
            double perimeter=getPerimeter(s);
            if(perimeter>largestPerimeter)
            {
            	largestPerimeter=perimeter;
            	result=i+1;
            	
            }
        }
        return "example "+result+".txt";
        
        }

    public void testPerimeter (Shape s) {
        
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
    	System.out.println(pr.getFileWithLargestPerimeter());
    }
}
