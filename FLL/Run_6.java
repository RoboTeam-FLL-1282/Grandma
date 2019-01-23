package FLL;

import EV3.BrickButtons;
import EV3.MediumMotor;
import EV3.MoveTank;
import EV3.Ports;
import EV3.Sound;
import EV3.Wait;
import Motion.BlackLineAlignment;
import Motion.GyroPID;
import Motion.WhiteLineAlignment;
import Navigation.SpecialFunctions;
import Navigation.Traveler;
import Tools.MediumMotors;
import Tools.Run;
import Tools.RunsMenu;

public class Run_6 implements Runnable, MediumMotors{

	public GyroPID pid = new GyroPID();
	Run runnable;

	public Run_6(Run runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {

		pid = new GyroPID(5, 0.5, 0.001, 0.001);
		
		SpecialFunctions.smiley();
		Sound.beep(100);
		BrickButtons.waitForAnyPress();
		SpecialFunctions.smileyOff();
		
		pid.g.recalibrate();
		
		pid.setBaseSpeed(-250);
		pid.startPID();
		Wait.time(2000);
		pid.stopPID();
		
		BlackLineAlignment.find(-250);
		WhiteLineAlignment.find(-250);
		MoveTank.onForCent(-250, -250, 300, true);
		
		MoveTank.onForCent(250, 250, 200, true);
		
		c.onForDegrees(3000, 7000, true);
				
		MoveTank.onForCent(100, 100, 200, true);
		
		MoveTank.onForCent(900, 900, 500, true);
		
		pid.closePID();
		
	}

}
