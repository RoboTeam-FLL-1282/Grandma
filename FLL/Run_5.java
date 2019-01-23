package FLL;

import EV3.MediumMotor;
import EV3.MoveTank;
import EV3.Ports;
import EV3.Wait;
import Motion.GyroPID;
import Navigation.SpecialFunctions;
import Navigation.Traveler;
import Tools.MediumMotors;
import Tools.Run;
import Tools.RunsMenu;

public class Run_5 implements Runnable, MediumMotors{

	public GyroPID pid = new GyroPID();
	Run runnable;

	public Run_5(Run runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {

		pid = SpecialFunctions.navigateToFarT();

		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(100, 100, 160, true);
		if(!RunsMenu.active) return; // Break point

		Traveler t = new Traveler(0, 0, 12, 8.2);
		
		t.turnInSpot(49, 100);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(-250, -250, 900, true);
		if(!RunsMenu.active) return; // Break point
		
		c.onForDegrees(1000, 1100, true);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(200, 200, 200, true);
		if(!RunsMenu.active) return; // Break point
		
		pid.closePID();
		
	}

}
