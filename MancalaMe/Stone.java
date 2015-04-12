

public class Stone {
	private double relativeX;
	private double relativeY;
	
	public Stone(double x, double y){
		this.relativeX = x;
		this.relativeY = y;
	}
	
	public double getX(){
		return relativeX;
	}
	
	public void setX(double x){
		this.relativeX = x;
	}
	
	public double getY(){
		return relativeY;
	}
	
	public void setY(double y){
		this.relativeY = y;
	}
	
	public double distanceFrom(Stone bean, int width, int height){
		double dx = 1.0 * (this.relativeX - bean.relativeX) * width;
		double dy = 1.0 * (this.relativeY - bean.relativeY) * height;
	
		double dist = Math.sqrt(dx * dx + dy * dy);
		return dist;
	}
}
