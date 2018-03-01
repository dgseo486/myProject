package testKmeans;

public class printDistance {
	private double centroidDistance1;
	private double centroidDistance2;
	private double centroidDistance3;
	
	public printDistance(double centroidDistance1, double centroidDistance2, double centroidDistance3){
		super();
		this.centroidDistance1=centroidDistance1;
		this.centroidDistance2=centroidDistance2;
		this.centroidDistance3=centroidDistance3;
	}
	public double getCentroidDistance1(){
		return centroidDistance1;
	}
	public double getCentroidDistance2(){
		return centroidDistance2;
	}
	public double getCentroidDistance3(){
		return centroidDistance3;
	}
	@Override
	public String toString(){
		return "[" + centroidDistance1 +", "+ centroidDistance2 +", "+ centroidDistance3 + "]";
	}
}
