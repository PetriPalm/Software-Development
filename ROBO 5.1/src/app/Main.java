package app;

public class Main{
	private static DataE DE;
	private static LineTracer LF;
	private static ObstacleDetector OD;

	public static void main(String[] args){
		DE = new DataE();
		LF = new LineTracer(DE);
		OD = new ObstacleDetector(DE);
		OD.start();
		LF.start();	
	}
}