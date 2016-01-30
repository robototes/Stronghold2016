package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**This class represents a control to action that the robot must perform, such as extending the climbing arm*/
public abstract class RobotControl {
	/**TODO add sensors*/
	//stick to be used for controls
	Joystick stick;
	//motor controllers to be driven
	SpeedController scs[];
	
	//array of button indexes that this class will respond to.
	int buttons[];
	
	//whether this RobotControl is running
	boolean running = false;
	//the maximum amount of time that this RobotControl can run
	long maxtime;
	//timer to keep track of elapsed time
	Timer timer;
	//boolean to track if this object has been disabled permanently
	boolean pDisabled = false;
	public RobotControl(Joystick stick, SpeedController sc[], int buttons[], long maxtime) {
		this.stick = stick;
		this.scs = sc;
		this.maxtime = maxtime;
		timer = new Timer();
	}
	protected abstract void internalProcess(); //internal process for controlling motors, etc.
	/**Returns whether AT LEAST ONE of the buttons specified in the array buttons have been pressed.*/
	protected boolean checkInputs() {
		for(int button : buttons) {
			if(stick.getRawButton(button)) return true;
		}
		return false;
	}
	/**Start the RobotControl*/
	protected void start() {
		running = true;
		//start the timer
		timer.startTimer();
		//...
	}
	public final String process() {
		if(pDisabled) return "";
		if(!checkInputs()) return ""; //no buttons have been pressed
		if(!running) start(); //start the robot if we are not already running
		//check if time has exceeded max time.
		//the timer must be started before we check how much time has passed.
		if(timer.getElaspedTime() > maxtime) {
			if(this instanceof DriveControl) timer.resetTimer(); //reset if this is instance of drivecontrol
			return "";
		}
		internalProcess();
		return "";
	}
	/**A class to keep track of time*/
	private class Timer {
		long origTime = -1;
		void startTimer() {
			origTime = System.nanoTime();
		}
		/**Gets the time elapsed since the timer has been started.*/
		protected long getElaspedTime() {
			if(origTime == -1) throw new IllegalStateException();
			return System.nanoTime() - origTime;
		}
		void resetTimer() {
			origTime = System.nanoTime();
		}
	}
}
