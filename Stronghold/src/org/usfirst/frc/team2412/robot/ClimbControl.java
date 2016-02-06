package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ClimbControl extends RobotControl {

	public ClimbControl(Joystick stick) {
		this.stick = stick;
	}
	void changeGearsLeft(boolean buttonPressed) {
		if(buttonPressed) {
			Constants.GEARCHANGELEFTCONTROLLER.set(1);
		} else {
			Constants.GEARCHANGELEFTCONTROLLER.set(0);
		}
	}
	void changeGearsRight(boolean buttonPressed) {
		if(buttonPressed) {
			Constants.GEARCHANGERIGHTCONTROLLER.set(1);
		} else {
			Constants.GEARCHANGERIGHTCONTROLLER.set(0);
		}
	}
	void extendArm(boolean buttonPressed) {
		if(buttonPressed) {
			Constants.EXTENDARMCONTROLLER.set(1);
		} else {
			Constants.EXTENDARMCONTROLLER.set(0);
		}
	}
	void pullUpRobot(boolean buttonPressed) {
		if(buttonPressed) {
			//set all drive motors to the stick's y value
			Constants.DRIVEFRONTLEFTCONTROLLER.set(stick.getY());
			Constants.DRIVEREARLEFTCONTROLLER.set(stick.getY());
			Constants.DRIVEFRONTRIGHTCONTROLLER.set(stick.getY());
			Constants.DRIVEREARRIGHTCONTROLLER.set(stick.getY());
		} else {
			//set all drive motors to zero
			Constants.DRIVEFRONTLEFTCONTROLLER.set(0);
			Constants.DRIVEREARLEFTCONTROLLER.set(0);
			Constants.DRIVEFRONTRIGHTCONTROLLER.set(0);
			Constants.DRIVEREARRIGHTCONTROLLER.set(0);
		}
	}
	@Override
	public void process() {
		changeGearsLeft(stick.getRawButton(Constants.GEARCHANGELEFTBUTTONID));
		changeGearsRight(stick.getRawButton(Constants.GEARCHANGERIGHTBUTTONID));
		extendArm(stick.getRawButton(Constants.EXTENDARMBUTTONID));
		pullUpRobot(stick.getRawButton(Constants.PULLUPROBOTBUTTONID));
	}

}
