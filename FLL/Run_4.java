package FLL;

import javax.activation.MailcapCommandMap;

import EV3.BrickButtons;
import EV3.MediumMotor;
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

public class Run_4 implements Runnable{
	
	Run runnable;
	public GyroPID pid = new GyroPID();
	
	public Run_4(Run runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public void run() {		
		
		pid = SpecialFunctions.navigateToOpsiteSection();	    
		
		MediumMotor c =  new MediumMotor(Ports.C);
		
//		MoveTank.onForCent(-100, -100, 150, true);
//
		Traveler t = new Traveler(0, 0, 12, 8.2);
//		
//		t.turnInSpot(95, -100);
//		
//		Sound.beep(100);
//		BlackLineAlignment.align(-100);
		
		MoveTank.onForCent(-100, -100, 300, true);
		
		Sound.beep(100);
		c.onForDegrees(500, 540, true);
		
		//b.onForDegrees(-500, 200, true);
		
		BlackLineAlignment.align(100);
		
		MoveTank.onForCent(100, 100, 160, true);
		
		t.turnInSpot(50, -100);
		
		MoveTank.onForCent(200, 200, 700, true);
		Wait.time(200);
		MoveTank.onForCent(-200, -200, 700, true);
		
		t.turnInSpot(50, 100);
		
		MoveTank.onForCent(-200, -200, 300, true);
		
		//WhiteLineAlignment.align(100);//
		BlackLineAlignment.align(100);
		
		MoveTank.onForCent(100, 100, 160, true);
		
		t.turnInSpot(145, -100);
		
		pid.setTarget(pid.g.angle());
		pid.setBaseSpeed(250);
		pid.startPID();
		Wait.time(4700);
		pid.stopPID();
		
		Wait.time(500);
		
		
		
//		pid.setBaseSpeed(-500);
//		pid.setTarget(pid.g.angle() + 0.5); 
//		pid.startPID();
//		Wait.time(3300);
//		pid.stopPID();
		
		MoveTank.on(-600, -600);
		Wait.time(3000);
		MoveTank.off();
		
		t.turnInSpot(20, 100);
		
		MoveTank.onForCent(-900, -900, 1600, true);
	
		pid.closePID();
		runnable.runFinished();
	}
	
}
