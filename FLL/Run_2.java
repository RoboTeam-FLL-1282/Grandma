package FLL;

import EV3.BrickButtons;
import EV3.MoveTank;
import EV3.Sound;
import EV3.Wait;
import Motion.Accelerator;
import Motion.Aligner;
import Motion.BlackLineAlignment;
import Motion.GyroPID;
import Motion.WhiteLineAlignment;
import Navigation.SpecialFunctions;
import Navigation.Traveler;
import Tools.MediumMotors;
import Tools.Run;
import Tools.RunsMenu;

public class Run_2 implements Runnable, MediumMotors{

	Run runnable;
	public GyroPID pid = new GyroPID();
	
	/**
	 * Used for adding a listener. 
	 * @param runnable
	 */
	public Run_2(Run runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public void run() {
		
		Aligner.setWhiteValue(0.85);
		
		Traveler t = new Traveler(0, 0, 12, 8.2);
		
		pid = new GyroPID(0, 1, 0.001, 0.001);
		pid.setBaseSpeed(-200);
		
		if(!RunsMenu.active) return; //Break point

		SpecialFunctions.smiley();
		Sound.beep(100);
		BrickButtons.waitForAnyPress();
		SpecialFunctions.smileyOff();

		GyroPID.g.recalibrate();		
		MoveTank.onForCent(250, 250, 20, true);
		
		// Start moving towards M06.
		c.on(15000);
		pid.startPID();
		Accelerator.acceleratePID(pid, 0.5, -70, -500, false);
		if(!RunsMenu.active) return; // Break point
		Wait.time(2000);
		for(int i = -500; i<0; i+=30) {
			pid.setBaseSpeed(i);
			Wait.time(30);
		}
		pid.stopPID();
		
		if(!RunsMenu.active) return; // Break point
		
		t.turnInSpot(15, -100);
		
		// Align on line:
		WhiteLineAlignment.align(-100);
		MoveTank.off();
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.align(-100);
		if(!RunsMenu.active) return; // Break point
		WhiteLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.align(-100);
		if(!RunsMenu.active) return; // Break point
		
		// A little fetch.
		MoveTank.onForCent(-100, -100, 90, true);		
		if(!RunsMenu.active) return; // Break point
		
		c.onForSeconds(1000, 5, true);
		c.onForSeconds(-4000, 6, true);
		Wait.time(500);
		c.onForSeconds(4000, 3, true);
		
		// Finds black line and moves backwards.
		WhiteLineAlignment.find(50);
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(150, 150, 150, true);
		if(!RunsMenu.active) return; // Break point
		
		// Rotates and moves forwards.
		t.turnInSpot(45, -100);
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.find(-300);
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(-300, -300, 200, true);
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(45, -100);
		
		// Align in line.
		WhiteLineAlignment.align(200);
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.align(200);
		if(!RunsMenu.active) return; // Break point
		
		// Move to collect the resources.
		MoveTank.onForCent(-300, -300, 150, false); // -100
		if(!RunsMenu.active) return; // Break point
		
		// A little turn.
		t.turnInSpot(110, 100);
		
		// Coming back to base.
		MoveTank.onForCent(300, 300, 500, false); // 100
		if(!RunsMenu.active) return; // Break point
		Wait.time(200);
		MoveTank.onForCent(-300, -300, 300, false); // -100
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(30, -100);
		MoveTank.onForCent(-300, -300, 200, false); // -100
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(30, -100);
		MoveTank.onForCent(500, 500, 510, false);
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(30, 625);
		MoveTank.onForCent(1000, 1000, 1300, true);
		if(!RunsMenu.active) return; // Break point
		
		pid.closePID();
		runnable.runFinished();
	}
	
}
