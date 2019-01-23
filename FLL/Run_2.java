package FLL;

import EV3.BrickButtons;
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
import Motion.WhiteLineAlignment;
import Navigation.SpecialFunctions;
import Navigation.Traveler;
import Tools.Default;
import Tools.Run;
import Tools.RunsMenu;

public class Run_2 implements Runnable{

	Run runnable;
	public GyroPID pid = new GyroPID();
	
	public Run_2(Run runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public void run() {
		
		Aligner.setWhiteValue(0.85);
		
		pid = new GyroPID(0, 0.5, 0.001, 0.001);
		pid.setBaseSpeed(-200);
		
		if(!RunsMenu.active) return;

		SpecialFunctions.smiley();
		Sound.beep(100);
		BrickButtons.waitForAnyPress();
		SpecialFunctions.smileyOff();

		pid.g.recalibrate();		
		MoveTank.onForCent(250, 250, 20, true);
		pid.setTarget(0);
		pid.startPID();
		for(int i = -70; i>-250; i--) {
			pid.setBaseSpeed(i);
			Wait.time(5);
		}
		Wait.time(3000);
		pid.stopPID();
		
		if(!RunsMenu.active) return; // Break point

		WhiteLineAlignment.align(-150);
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.align(-150);
		if(!RunsMenu.active) return; // Break point
		WhiteLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.align(-100);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(-100, -100, 90, true);		
		if(!RunsMenu.active) return; // Break point
		
		WhiteLineAlignment.find(50);
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(150, 150, 100, true);
		if(!RunsMenu.active) return; // Break point
		
		Traveler t = new Traveler(0, 0, 12, 8.2);
		t.turnInSpot(50, -100);
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.find(-200);
		if(!RunsMenu.active) return; // Break point
		MoveTank.onForCent(-200, -200, 250, true);
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(30, -100);
		
		WhiteLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		BlackLineAlignment.align(100);
		if(!RunsMenu.active) return; // Break point
		
		MoveTank.onForCent(-200, -200, 120, true); // -100
		if(!RunsMenu.active) return; // Break point
		
		t.turnInSpot(110, 100);
		
		MoveTank.onForCent(200, 200, 500, true); // 100
		if(!RunsMenu.active) return; // Break point
		Wait.time(200);
		MoveTank.onForCent(-200, -200, 300, true); // -100
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(30, -100);
		MoveTank.onForCent(-200, -200, 200, true); // -100
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(30, -100);
		MoveTank.onForCent(500, 500, 500, true);
		if(!RunsMenu.active) return; // Break point
		t.turnInSpot(45, 625);
		MoveTank.onForCent(900, 900, 1300, true);
		if(!RunsMenu.active) return; // Break point
		
		pid.closePID();
		runnable.runFinished();
	}
	
}
