package Navigation;

import EV3.MoveTank;

public class DeadReckoning {

	// Members:
	int width = 75;
	int height = 92;
	double wheelDiameter = 81.6;
	int x = 100;
	int y = 100;
	double theta = Math.toRadians(0);

	double vr = 0;
	double vl = 10;

	double time = 1; // In seconds.

	public void calculatePosition() {
		double s = (vr+vl)/2;
		theta = (vr-vl)/width + theta;
		x = (int)(s*Math.cos(theta) + x);
		y = (int)(s*Math.sin(theta) + y);
	}

	
//	public void measure() {
//		
//		double P = (Math.PI*wheelDiameter)/360;
//		vl = (MoveTank.leftMotor.getTachoCount()/time)*P*0.5; // pixels per time.
//		vr = (MoveTank.rightMotor.getTachoCount()/time)*P*0.5; // pixels per time.
//	
//		calculatePosition();
//		
//		MoveTank.leftMotor.resetTachoCount();
//		MoveTank.rightMotor.resetTachoCount();
//	
//	}
	
}
