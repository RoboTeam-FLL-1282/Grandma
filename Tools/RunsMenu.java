package Tools;

import java.util.LinkedList;

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

	public RunsMenu() { // No parameters.
		s.addSelectListener(this);
	}

	/**
	 * Adds a new runnable instance with its label.
	 * @param runnable
	 * @param name
	 */
	public void addRun(Runnable runnable, String name) {
		s.addLabel(name);
		runs.add(runnable);
	}

	/**
	 * Adds a new runnable instance with its label and its PID object.
	 * @param runnable
	 * @param name
	 * @param pid
	 */
	public void addRun(Runnable runnable, String name, GyroPID pid) {
		s.addLabel(name);
		runs.add(runnable);
		this.pids.add(pid);
	}

	/**
	 * Show the UI.
	 */
	public void show() {
		s.show();
	}

	/*
	 * Hide the UI.
	 */
	public void hide() {
		s.hide();
	}

	@Override
	public void onSelect(String selectedLabel) {
		if(!isRuning) { // React only if no run is active.
			isRuning = true;
			active = true;
			currentlyRuning = s.getSelectedIndex(); // Update index.
			hide();
			t = new Thread(runs.get(s.getSelectedIndex()));
			t.start(); // Start the run.
		}
	}

	@Override
	public void onPress(Buttons button) {
		// When ESCAPE is pressed:
		// Shut the run down. 
		active = false;
		isRuning = false;
		while(t.isAlive());
		MoveTank.off();
		// Stop and close the run's PID.
		pids.get(currentlyRuning).stopPID();
		pids.get(currentlyRuning).closePID();
		show();// Show the menu. 
	}

	@Override
	public void runFinished() { // Called when the run is finished.
		isRuning = false;
		show();
	}
}
