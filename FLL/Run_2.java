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

		WhiteLineAlignment.align(-150);
		BlackLineAlignment.align(-150);
		WhiteLineAlignment.align(100);
		BlackLineAlignment.align(-100);
		
		MoveTank.onForCent(-100, -100, 100, true);		
		
		WhiteLineAlignment.find(50);
		MoveTank.onForCent(150, 150, 100, true);
		
		Traveler t = new Traveler(0, 0, 12, 8.2);
		t.turnInSpot(50, -100);
		BlackLineAlignment.find(-200);
		MoveTank.onForCent(-200, -200, 250, true);
		t.turnInSpot(30, -100);
		
		WhiteLineAlignment.align(100);
		BlackLineAlignment.align(100);
		
		MoveTank.onForCent(-100, -100, 120, true);
		
		t.turnInSpot(110, 100);
		
		MoveTank.onForCent(100, 100, 500, true);
		Wait.time(200);
		MoveTank.onForCent(-100, -100, 300, true);
		t.turnInSpot(30, -100);
		MoveTank.onForCent(-100, -100, 200, true);
		t.turnInSpot(30, -100);
		MoveTank.onForCent(500, 500, 500, true);
		t.turnInSpot(45, 625);
		MoveTank.onForCent(900, 900, 1300, true);
		
		pid.closePID();
		runnable.runFinished();
	}
	
}
