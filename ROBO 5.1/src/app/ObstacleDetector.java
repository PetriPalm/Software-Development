package app;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class ObstacleDetector extends Thread{
	private DataE DE;
	private EV3UltrasonicSensor us;
	public ObstacleDetector(DataE DE1){
		DE = DE1;
		us = new EV3UltrasonicSensor(SensorPort.S3);
	}
		public void run(){
		float[] DistanceSample;
		SampleProvider DistanceMode = us.getDistanceMode();
		DistanceSample = new float [DistanceMode.sampleSize()];
		
			float safeDistance = 0.17f;
			while (true){
			us.fetchSample(DistanceSample, 0);
				if (DistanceSample[0] > safeDistance){
				DE.setCMD(1);
				}
				else 
				{
				DE.setCMD(0);
				}
			}
		}
}