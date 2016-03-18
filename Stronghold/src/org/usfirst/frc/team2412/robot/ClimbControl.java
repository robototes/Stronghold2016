package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ClimbControl extends RobotControl {

	public ClimbControl(Joystick stick) {
		this.stick = stick;
	}
	void changeGearsLeft(boolean buttonPressed) {
		if(buttonPressed) {
			Constants.GEARCHANGELEFTCONTROLLER.set(0.25);
		} else {
			Constants.GEARCHANGELEFTCONTROLLER.set(0);
		}
	}
	void changeGearsRight(boolean buttonPressed) {
		if(buttonPressed) {
			Constants.GEARCHANGERIGHTCONTROLLER.set(0.25);
		} else {
			Constants.GEARCHANGERIGHTCONTROLLER.set(0);
		}
	}
	void moveRobotVertically(boolean climbUp, boolean descend) {
		if(climbUp) {
			System.out.println("Climb");
			Constants.CLIMBCONTROLLER.set(0.5);
		} else if(descend) {
			Constants.CLIMBCONTROLLER.set(-0.5);
		} else {
			Constants.CLIMBCONTROLLER.set(0.0);
		}
	}
	@Override
	public void process() {
		changeGearsLeft(stick.getRawButton(Constants.GEARCHANGELEFTBUTTONID));
		changeGearsRight(stick.getRawButton(Constants.GEARCHANGERIGHTBUTTONID));
		moveRobotVertically(stick.getRawButton(Constants.CLIMBBUTTONID), stick.getRawButton(Constants.DESCENDBUTTONID));
	}

}
