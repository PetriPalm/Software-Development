package app;
public class DataE{
	
	private int Stop = 1;
	private int CMD = 1;
	public DataE() {}
		
		//for Obstacle avoidance
		public void setCMD(int command){
			CMD = command;
		}
		public int getCMD(){
			return CMD;
		}
		//to stop the 2nd time around
		public void setStop(int StopCommand){
			Stop = StopCommand; 
		}
		public int getStop(){
			return Stop;
		}
}
