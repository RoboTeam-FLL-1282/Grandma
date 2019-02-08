package FLL;

import EV3.BrickButtons;
import EV3.MoveTank;
import EV3.Sound;
import EV3.Wait;
import Motion.BlackLineAlignment;
import Motion.GyroPID;
import Motion.WhiteLineAlignment;
import Navigation.SpecialFunctions;
import Tools.MediumMotors;
import Tools.Run;
import Tools.RunsMenu;

public class Run_1 implements Runnable, MediumMotors{

	public GyroPID pid = new GyroPID();
	Run runnable;

	/**
	 * Used for adding a listener. 
	 * @param runnable
	 */
	public Run_1(Run runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {

		pid = new GyroPID(20, 0.8, 0.001, 0.001);
		
		SpecialFunctions.smiley();
		Sound.beep(100);
		BrickButtons.waitForAnyPress();
		SpecialFunctions.smileyOff();
		
		GyroPID.g.recalibrate();
		
		MoveTank.onForCent(100, 100, 100, true); // Move forwards towards M01.
		
		if(!RunsMenu.active) return; //Break point
		
		pid.setBaseSpeed(-400);
		pid.startPID();// Start moving  with PID.
		
		if(!RunsMenu.active) return; //Break point

		Wait.time(2500);
		pid.stopPID();
		
		if(!RunsMenu.active) return; //Break point
		
		BlackLineAlignment.find(-400); // Align on line.
		WhiteLineAlignment.find(-400);
		
		if(!RunsMenu.active) return; //Break point
		
		MoveTank.onForCent(-400, -400, 400, true); // Move forwards strongly.
		
		if(!RunsMenu.active) return; //Break point
				
		
		MoveTank.off();
		
		c.onForDegrees(3000, 9000, true); // Start rotating medium motors.
				
		if(!RunsMenu.active) return; //Break point
								
		// Coming back to base:
		MoveTank.onForCent(100, 100, 100, true);
		
		MoveTank.onForCent(900, 900, 900, true);
		
		
		pid.closePID();
		
	}

}
