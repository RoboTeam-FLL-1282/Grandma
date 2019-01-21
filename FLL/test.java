package FLL;
import EV3.*;
import Motion.Aligner;
import Motion.Colors;
import Motion.GyroPID;
import Motion.WhiteLineAlignment;
import Navigation.Traveler;
import Tools.Alert;
import Tools.Default;
public class test{
	
	public static void main(String[] args) {

		MediumMotor b = new MediumMotor(Ports.B);
		b.onForDegrees(100, 600, true);
		
	}
}

	
