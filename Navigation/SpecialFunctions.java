package Navigation;

import EV3.BrickButtons;
import EV3.BrickLight;
import EV3.Display;
import EV3.MoveTank;
import EV3.Ports;
import EV3.Sound;
import EV3.Wait;
import Motion.Accelerator;
import Motion.Aligner;
import Motion.BlackLineAlignment;
import Motion.GyroPID;
import Motion.LineAlignment;
import Motion.Sides;
import Motion.Unique;
import Motion.WhiteLineAlignment;
import Tools.RunsMenu;

public class SpecialFunctions {

	static boolean smiley = true;
	
	public static void smiley() {
		new Thread() {
			@Override
			public void run() {
				while(smiley) {
					Display.resetScreen();
					BrickLight.on(1);
					Wait.time(500);
					BrickLight.on(0);
					Display.text("   **        **     ", 2, 15);
					Display.text("   **        **     ", 2, 30);
					Display.text("**      *       **  ", 2, 45);
					Display.text(" **            **   ", 2, 60);
					Display.text("  **************    ", 2, 75);
					Display.text("   ************     ", 2, 90);
					Wait.time(500);
				}
			}
		}.start();
	}
	
	public static void smileyOff() {
		smiley = false;
	}
	
	public static GyroPID navigateToOpsiteSection() {

		//Aligner.setWhiteValue(0.85);
		GyroPID pid = new GyroPID(-10, 1, 0.001, 0.001);
		pid.setBaseSpeed(-250);
		smiley();
		Sound.beep(100);
		BrickButtons.waitForAnyPress();
		smileyOff();
		pid.g.recalibrate();

		// Move robot
		Accelerator.accelerate(0.5, -150, -250, false);
		if(!RunsMenu.active) return pid; // Break point
		Sound.beep(100);
		pid.startPID();
		Wait.time(500);
		pid.stopPID();
		Sound.beep(100);
		if(!RunsMenu.active) return pid; // Break point
		
		// Align on white line and then turn x degrees
		//		Display.resetScreen();
		WhiteLineAlignment.align(-250);
		if(!RunsMenu.active) return pid; // Break point
		Sound.beep(100);
		pid.g.reset();
		//MoveTank.onForCent(200, 200, 500, true);
	
		Traveler t = new Traveler(0, 0, 12, 8.2);
		t.turnInSpot(70, -100);
		if(!RunsMenu.active) return pid; // Break point
		pid.setTarget(pid.g.angle());
		pid.startPID();
		if(!RunsMenu.active) return pid; // Break point
		Wait.time(1000);
		pid.stopPID();
		if(!RunsMenu.active) return pid; // Break point
		
		BlackLineAlignment.find(Sides.LEFT, -250);
		if(!RunsMenu.active) return pid; // Break point
		MoveTank.onForCent(-250, -250, 200, true);
		if(!RunsMenu.active) return pid; // Break point
		t.turnInSpot(42, -100);
		if(!RunsMenu.active) return pid; // Break point
		BlackLineAlignment.find(-200);
		if(!RunsMenu.active) return pid; // Break point
		MoveTank.onForCent(-100, -100, 150, true);
		if(!RunsMenu.active) return pid; // Break point
		//WhiteLineAlignment.align(100);
		BlackLineAlignment.align(100);
		if(!RunsMenu.active) return pid; // Break point
		//LineAlignment.align(100);
 
		//Unique.alignOnBigAngle(-100, Sides.LEFT);
		return pid;
	}
	
	public static GyroPID navigateToFarT() {

		//Aligner.setWhiteValue(0.85);
		GyroPID pid = new GyroPID(-10, 1, 0.001, 0.001);
		pid.setBaseSpeed(-250);
		smiley();
		Sound.beep(100);
		BrickButtons.waitForAnyPress();
		smileyOff();
		pid.g.recalibrate();

		// Move robot
		Accelerator.accelerate(0.5, -150, -250, false);
		if(!RunsMenu.active) return pid; // Break point
		Sound.beep(100);
		pid.startPID();
		if(!RunsMenu.active) return pid; // Break point
		Wait.time(500);
		pid.stopPID();
		if(!RunsMenu.active) return pid; // Break point
		Sound.beep(100);

		// Align on white line and then turn x degrees
		//		Display.resetScreen();
		WhiteLineAlignment.align(-250);
		if(!RunsMenu.active) return pid; // Break point
		Sound.beep(100);
		pid.g.reset();
		//MoveTank.onForCent(200, 200, 500, true);
	
		Traveler t = new Traveler(0, 0, 12, 8.2);
		t.turnInSpot(70, -100);
		if(!RunsMenu.active) return pid; // Break point
		pid.setTarget(pid.g.angle());
		pid.startPID();
		if(!RunsMenu.active) return pid; // Break point
		Wait.time(1000);
		pid.stopPID();
		if(!RunsMenu.active) return pid; // Break point
		
		BlackLineAlignment.find(Sides.LEFT, -250);
		if(!RunsMenu.active) return pid; // Break point
		MoveTank.onForCent(-250, -250, 200, true);
		if(!RunsMenu.active) return pid; // Break point
		t.turnInSpot(42, -100);
		if(!RunsMenu.active) return pid; // Break point
		BlackLineAlignment.find(-200);
		if(!RunsMenu.active) return pid; // Break point
		MoveTank.onForCent(-100, -100, 100, true);
		if(!RunsMenu.active) return pid; // Break point
		//WhiteLineAlignment.align(100);
		BlackLineAlignment.align(100);
		if(!RunsMenu.active) return pid; // Break point
		//LineAlignment.align(100);
 
		//Unique.alignOnBigAngle(-100, Sides.LEFT);
		return pid;
	}

}
