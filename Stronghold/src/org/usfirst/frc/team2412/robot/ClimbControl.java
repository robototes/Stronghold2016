package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class ClimbControl extends RobotControl {

	//variables indicating whether the shifters are in drive or climbing mode
	private boolean doClimbLeft = false;
	private boolean doClimbRight = false;
	
	public ClimbControl(Joystick stick) {
		this.stick = stick;
	}
	private void updateShifters(boolean climbButtonPressed, boolean driveButtonPressed, Victor t, boolean climbLeft) {
		if(climbButtonPressed) {
			//climb button pressed, set victor to 100%
			t.set(1.0);
			if(climbLeft) doClimbLeft = true; else doClimbRight = true;
		} else if(driveButtonPressed) {
			//drive button pressed, set victor to 100%
			t.set(-1.0);
			if(climbLeft) doClimbLeft = false; else doClimbRight = false;
		} else if(climbLeft ? doClimbLeft : doClimbRight) {
			//set to 10%
			t.set(0.1);
		} else {
			//set to 10%
			t.set(-0.1);
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
			Constants.DRIVEL2CONTROLLER.set(stickYAxis);
			Constants.DRIVEL3CONTROLLER.set(stickYAxis);
			Constants.DRIVER1CONTROLLER.set(stickYAxis);
			Constants.DRIVER2CONTROLLER.set(stickYAxis);
			Constants.DRIVER3CONTROLLER.set(stickYAxis);
			System.out.println("Pulling up robot: " + stickYAxis);
		} else {
			//set all drive motors to zero
			Constants.DRIVEL1CONTROLLER.set(0);
			Constants.DRIVEL2CONTROLLER.set(0);
			Constants.DRIVEL3CONTROLLER.set(0);
			Constants.DRIVER1CONTROLLER.set(0);
			Constants.DRIVER2CONTROLLER.set(0);
			Constants.DRIVER3CONTROLLER.set(0);
		}
	}
	@Override
	public void process() {
		updateShifters(stick.getRawButton(Constants.SHIFTERCHANGECLIMBLEFTBUTTONID), stick.getRawButton(Constants.SHIFTERCHANGEDRIVELEFTBUTTONID), Constants.SHIFTERCHANGELEFTCONTROLLER, true);
		updateShifters(stick.getRawButton(Constants.SHIFTERCHANGECLIMBRIGHTBUTTONID), stick.getRawButton(Constants.SHIFTERCHANGEDRIVERIGHTBUTTONID), Constants.SHIFTERCHANGERIGHTCONTROLLER, false);
		extendArm(stick.getRawButton(Constants.EXTENDARMBUTTONID), false);
		pullUpRobot(stick.getRawButton(Constants.PULLUPROBOTBUTTONID));
	}

}
