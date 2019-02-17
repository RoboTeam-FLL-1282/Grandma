package FLL;

import EV3.BrickButtons;
import EV3.Ports;
import EV3.Wait;
import Motion.Aligner;
import Motion.EncodersPID;
import Motion.LineFollower;
import Motion.Sides;
import Navigation.Traveler;
import Tools.Default;
import Tools.RunsMenu;

public class test {

	public static void main(String[] args) {
		
		Default.settings();
		
		Aligner.setSensorsPorts(Ports.S2, Ports.S4);
		
		LineFollower l = new LineFollower(10);
		
		RunsMenu.active = true;
		
		l.setTarget(0.15);
		
		//l.setTarget(0.15);
		
		l.startLineFollowing();
		Wait.time(100000);
		l.stopLineFollowing();
		l.closeLineFollower();
		
	}
	
}