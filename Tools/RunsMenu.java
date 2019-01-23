package Tools;

import java.util.LinkedList;

import EV3.BrickButtons;
import EV3.Buttons;
import EV3.MoveTank;
import Motion.GyroPID;

public class RunsMenu implements SelectListener, BrickButtonsListener, Run{

	Runnable runnable;
	SelectMenu s = new SelectMenu();
	LinkedList<Runnable> runs = new LinkedList<Runnable>();
	LinkedList<GyroPID> pids = new LinkedList<GyroPID>();

	Brick brick = new Brick(this, Buttons.ESCAPE);
	Thread t;
	int currentlyRuning;
	public static boolean active = false;

	boolean isRuning = false;

	public RunsMenu() {
		s.addSelectListener(this);
	}

	public void addRun(Runnable runnable, String name) {
		s.addLabel(name);
		runs.add(runnable);
	}

	public void addRun(Runnable runnable, String name, GyroPID pid) {
		s.addLabel(name);
		runs.add(runnable);
		this.pids.add(pid);
	}

	public void show() {
		s.show();
	}

	public void hide() {
		s.hide();
	}

	@Override
	public void onSelect(String selectedLabel) {
		if(!isRuning) {
			isRuning = true;
			active = true;
			currentlyRuning = s.getSelectedIndex();
			hide();
			t = new Thread(runs.get(s.getSelectedIndex()));
			t.start();
		}
	}

	@Override
	public void onPress(Buttons button) {
		active = false;
		isRuning = false;
		while(t.isAlive());
		MoveTank.off();
		pids.get(currentlyRuning).stopPID();
		pids.get(currentlyRuning).closePID();
		show();
	}

	@Override
	public void runFinished() {
		isRuning = false;
		show();
	}
}
