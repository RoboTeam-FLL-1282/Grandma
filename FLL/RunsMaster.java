package FLL;

import EV3.Ports;
import Motion.Aligner;
import Motion.GyroPID;
import Tools.Default;
import Tools.RunsMenu;

public class RunsMaster {

	public static void main(String[] args) {
		Default.settings();
		Aligner.setSensorsPorts(Ports.S2, Ports.S4);
		GyroPID.setGyroPort(Ports.S3);
		RunsMenu r = new RunsMenu();
		Run_4 r4 = new Run_4(r);
		Run_2 r2 = new Run_2(r);
		r.addRun(r2, "Run_2", r2.pid);
		r.addRun(r4, "Run_4", r4.pid);
		r.show();
	}
	
}
