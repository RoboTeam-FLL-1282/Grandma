package FLL;

import EV3.Ports;
import Motion.Aligner;
import Motion.GyroPID;
import Tools.Default;
import Tools.RunsMenu;

public class RunsMaster {

	public static void main(String[] args) {
		Default.settings(); //Sets screen and main motors.
		Aligner.setSensorsPorts(Ports.S2, Ports.S4); // Sets Aligner sensors.
		GyroPID.setGyroPort(Ports.S3);// Set Gyro port.
		
		// Create instances:
		RunsMenu r = new RunsMenu();
		Run_1 r1 = new Run_1(r);
		Run_2 r2 = new Run_2(r);
		Run_3 r3 = new Run_3(r);
		Run_4 r4 = new Run_4(r);
		
		// Add runs to RunsMenu:
		r.addRun(r1, "Run 1", r1.pid);
		r.addRun(r2, "Run 2", r2.pid);
		r.addRun(r3, "Run 3", r3.pid);
		r.addRun(r4, "Run 4", r4.pid);
		r.show();
	}
	
}
