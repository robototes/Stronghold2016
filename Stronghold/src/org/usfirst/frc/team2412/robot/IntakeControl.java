package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;

public class IntakeControl extends RobotControl {

	public IntakeControl(Joystick stick) {
		this.stick = stick;
	}
	/**Function to take in the ball. The parameter buttonPressed specifies whether the associated button (Constants.TAKEINBALLBUTTONID)s has been pressed.*/
	private void takeInBall(boolean buttonPressed) {
		if(buttonPressed) {
			//start taking in the ball if we haven't already
			Constants.INTAKEMOTORCONTROLLER.set(1);
		} else {
			Constants.INTAKEMOTORCONTROLLER.set(0); //stop the motor
		}
	}
	/**Function to shoot out the ball. The parameter buttonPressed specifies whether the associated button (Constants.SHOOTOUTBALLBUTTONID)s has been pressed.*/
	private void shootOutBall(boolean buttonPressed) {
		if(buttonPressed) {
			Constants.INTAKEMOTORCONTROLLER.set(-1.0);
		} else {
			Constants.INTAKEMOTORCONTROLLER.set(0.0);
		}
	}
	@Override
	public void process() {
		takeInBall(stick.getRawButton(Constants.TAKEINBALLBUTTONID));
		shootOutBall(stick.getRawButton(Constants.SHOOTOUTBALLBUTTONID));
	}
	
}
