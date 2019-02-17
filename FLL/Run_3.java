package FLL;


import EV3.MoveTank;
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

public class Run_3 implements Runnable, MediumMotors{
	
	Run runnable;
	public GyroPID pid = new GyroPID();
	
	/**
	 * Used for adding a listener. 
	 * @param runnable
	 */
	public Run_3(Run runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public void run() {		
		
		pid = SpecialFunctions.getToOppositeSection(); 	// Navigates to the far T. Replaced from navigateToOpsiteSection

		pid.setConstants(5, 0.05, 0.2);
		
		Traveler t = new Traveler(0, 0, 12, 8.2);
		
		// Moving towards the food production.
		MoveTank.onForCent(-100, -100, 300, true);
		if(!RunsMenu.active) return; // Break point
		
		Sound.beep(100);
		b.onForDegrees(-300, 300, true);
		if(!RunsMenu.active) return; // Break point
		
		// Aligns on line and approaches M013.
		BlackLineAlignment.align(100);
		WhiteLineAlignment.align(-100);
		BlackLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(100, 100, 40, true);
		if(!RunsMenu.active) return; // Break point
		
		t.turnInSpot(143, -100);
		if(!RunsMenu.active) return; // Break point
		
		pid.setTarget(-213);
		pid.setBaseSpeed(250);
		pid.startPID();
		Wait.time(5000);
		pid.stopPID();
		if(!RunsMenu.active) return; // Break point
				
		Wait.time(500);
				
		MoveTank.onForCent(-200, -200, 350, true);
				
		t.turnInSpot(143, 100);
		MoveTank.onForCent(-250, -250, 240, true);
				
		MoveTank.onForCent(250, 250, 240, true);
		t.turnInSpot(143, -100);
		
		MoveTank.onForCent(-200, -200, 80, true);
		
		c.onForSeconds(-3000, 5, true);
				
		c.on(50000);
		MoveTank.onForCent(-200, -200, 220, false);
				
		// Coming back to space.
		MoveTank.onForCent(-600, -600, 350, false);
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(90, 200);
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(-600, -600, 700, false);
			
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(90, -200);
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(-600, -600, 1000, false);
		
		c.off();
		
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(50, 200);
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(-600, -600, 700, true);
		if(!RunsMenu.active) return; // Break point
//				
//		t.turnInSpot(20, 100);
//		if(!RunsMenu.active) return; // Break point
//		
//		MoveTank.onForCent(-900, -900, 1000, true);
//		if(!RunsMenu.active) return; // Break point
//		t.turnInSpot(30, 100);
//		MoveTank.onForCent(-900, -900, 700, true);
	
		pid.closePID();
		runnable.runFinished();
	}
	
}