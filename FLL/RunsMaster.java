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
		Run_5 r5 = new Run_5(r);
		Run_6 r6 = new Run_6(r);
		r.addRun(r2, "Run 2", r2.pid);
		r.addRun(r4, "Run 4", r4.pid);
		r.addRun(r5, "Run 5", r5.pid);
		r.addRun(r6, "Run 6", r6.pid);
		r.show();
	}
	
}
