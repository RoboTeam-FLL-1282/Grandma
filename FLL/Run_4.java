package FLL;

import EV3.MoveTank;
import Motion.GyroPID;
import Navigation.SpecialFunctions;
import Navigation.Traveler;
import Tools.MediumMotors;
import Tools.Run;
import Tools.RunsMenu;

public class Run_4 implements Runnable, MediumMotors{

	public GyroPID pid = new GyroPID();
	Run runnable;

	public Run_4(Run runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {

		pid = SpecialFunctions.navigateToFarT(); // Navigates to far T.

		
		//Moving backwards:
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(100, 100, 160, true);
		if(!RunsMenu.active) return; // Break point

		Traveler t = new Traveler(0, 0, 12, 8.2);
		
		// Turning...
		t.turnInSpot(49, 100);
		if(!RunsMenu.active) return; // Break point
		
		// Fetching and positioning.
		MoveTank.onForCent(-250, -250, 900, true);
		if(!RunsMenu.active) return; // Break point
		
		c.onForDegrees(1000, 1100, true);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(200, 200, 200, true);
		if(!RunsMenu.active) return; // Break point
		
		pid.closePID();
		
	}

}
