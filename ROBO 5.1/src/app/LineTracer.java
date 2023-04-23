package app;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class LineTracer extends Thread{
	private DataE DE1;
	private EV3ColorSensor colorSensor;
	private EV3LargeRegulatedMotor leftMotor,rightMotor;
	public  LineTracer(DataE DE){
		DE1 = DE;
		colorSensor = new EV3ColorSensor(SensorPort.S2);
		leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	}
	public void run(){
		EV3 ev3 = (EV3) BrickFinder.getLocal();
		TextLCD lcd = ev3.getTextLCD();
		
		// sampleFetcher
		float redSample[];
		SensorMode redMode = colorSensor.getRedMode();
		redSample = new float[redMode.sampleSize()];
		
		// values for line following and obstacle avoidance
		final int degrees = 180;
		leftMotor.setSpeed(250);
		rightMotor.setSpeed(250);
		float lower = 0.08f;
		float upper = 0.35f;
		
		
		while (true){
			redMode.fetchSample(redSample, 0);
			
			//sample data
			lcd.clear();
			lcd.drawString(String.valueOf(redSample[0]), 1, 3);
			
			//line follower
			if(DE1.getCMD() == 1) {
				if (redSample[0] < lower){ 
				leftMotor.forward();
				rightMotor.stop();
			}
			else if (redSample[0] > upper){ 
				leftMotor.stop();
				rightMotor.forward();
			}
			}
			else if(DE1.getStop() == 1){
				// obstacle avoidance
				leftMotor.stop();
				rightMotor.stop();
				Delay.msDelay(1000);
				leftMotor.rotate(degrees);
				Delay.msDelay(1000);
				rightMotor.forward();
				leftMotor.forward();
				Delay.msDelay(1000);
				leftMotor.stop();
				rightMotor.stop();
				Delay.msDelay(1000);
				rightMotor.rotate(160);
				Delay.msDelay(1000);
				rightMotor.forward();
				leftMotor.forward();
				Delay.msDelay(1000);
				leftMotor.stop();
				rightMotor.stop();
				DE1.setStop(2);
			}
			else{
				//stops the 2nd time around
				System.exit(0);
			}	
		}
	}	
}