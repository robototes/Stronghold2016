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
	void extendArm(boolean buttonPressed, boolean stop) {
		if(buttonPressed && !stop) {
			Constants.EXTENDARMCONTROLLER.set(1);
		} else {
			Constants.EXTENDARMCONTROLLER.set(0);
		}
	}
	void pullUpRobot(boolean buttonPressed) {
		if(buttonPressed) {
			//set all drive motors to the stick's y value
			double stickYAxis = stick.getY();
			stickYAxis = stickYAxis <= 0 ? stickYAxis : 0;
			Constants.DRIVEL1CONTROLLER.set(stickYAxis);
			Constants.DRIVEL3CONTROLLER.set(stickYAxis);
			Constants.DRIVER1CONTROLLER.set(stickYAxis);
			Constants.DRIVER3CONTROLLER.set(stickYAxis);
			System.out.println("Pulling up robot: " + stickYAxis);
		} else {
			//set all drive motors to zero
			Constants.DRIVEL1CONTROLLER.set(0);
			Constants.DRIVEL3CONTROLLER.set(0);
			Constants.DRIVER1CONTROLLER.set(0);
			Constants.DRIVER3CONTROLLER.set(0);
		}
	}
	@Override
	public void process() {
		changeGearsLeft(stick.getRawButton(Constants.GEARCHANGELEFTBUTTONID));
		changeGearsRight(stick.getRawButton(Constants.GEARCHANGERIGHTBUTTONID));
		extendArm(stick.getRawButton(Constants.EXTENDARMBUTTONID), false);
		pullUpRobot(stick.getRawButton(Constants.PULLUPROBOTBUTTONID));
	}

}
