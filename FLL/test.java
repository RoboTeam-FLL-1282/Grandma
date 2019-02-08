package FLL;

import EV3.BrickButtons;
import EV3.Wait;
import Motion.EncodersPID;
import Tools.Default;

public class test {

	public static void main(String[] args) {
		
		Default.settings();
		
		EncodersPID pid = new EncodersPID(0, 2, 0, 0);
		BrickButtons.waitForAnyPress();
		
		pid.startPID();
		Wait.time(300000);
		pid.stopPID();
		
	}
	
}
