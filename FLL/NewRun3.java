package FLL;


import EV3.MoveTank;
import EV3.Sound;
import EV3.Wait;
import Motion.BlackLineAlignment;
import Motion.GyroPID;
import Navigation.SpecialFunctions;
import Navigation.Traveler;
import Tools.MediumMotors;
import Tools.Run;
import Tools.RunsMenu;

public class NewRun3 implements Runnable, MediumMotors{
	
	Run runnable;
	public GyroPID pid = new GyroPID();
	
	/**
	 * Used for adding a listener. 
	 * @param runnable
	 */
	public NewRun3(Run runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public void run() {		
		
		pid = SpecialFunctions.navigateToOpsiteSection(); // Navigates to the far T.  

		Traveler t = new Traveler(0, 0, 12, 8.2);
		
		// Moving towards the food production.
		MoveTank.onForCent(-100, -100, 300, true);
		if(!RunsMenu.active) return; // Break point
		
		Sound.beep(100);
		c.onForDegrees(500, 540, true);
		if(!RunsMenu.active) return; // Break point
		
		// Aligns on line and approaches M013.
		BlackLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(100, 100, 160, true);
		if(!RunsMenu.active) return; // Break point

		// Turning..
		t.turnInSpot(50, -100);
		if(!RunsMenu.active) return; // Break point

		MoveTank.onForCent(200, 200, 700, true);
		if(!RunsMenu.active) return; // Break point

		Wait.time(200);
		MoveTank.onForCent(-200, -200, 700, true);
		if(!RunsMenu.active) return; // Break point

		
		t.turnInSpot(50, 100);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(-200, -200, 300, true);
		if(!RunsMenu.active) return; // Break point

		// Aligns on line and moves towards M15.
		BlackLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		//WhiteLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		//BlackLineAlignment.align(-100);
		if(!RunsMenu.active) return; // Break point
		
		
		MoveTank.onForCent(100, 100, 160, true);
		if(!RunsMenu.active) return; // Break point
		
		t.turnInSpot(145, -100);
		if(!RunsMenu.active) return; // Break point
		
		pid.setTarget(GyroPID.g.angle());
		pid.setBaseSpeed(250);
		pid.startPID();
		Wait.time(4700);
		pid.stopPID();
		if(!RunsMenu.active) return; // Break point
		
		Wait.time(500);
		
		// Coming back to space.
		MoveTank.on(-600, -600);
		if(!RunsMenu.active) return; // Break point
		Wait.time(2500);
		MoveTank.off();
		if(!RunsMenu.active) return; // Break point
		
		t.turnInSpot(20, 100);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(-900, -900, 1000, true);
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(30, 100);
		MoveTank.onForCent(-900, -900, 700, true);
	
		pid.closePID();
		runnable.runFinished();
	}
	
}