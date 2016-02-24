package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;

public class IntakeControl extends RobotControl {

	public IntakeControl(Joystick stick) {
		this.stick = stick;
	}
	/**Function to take in the ball. The parameters xxxxxxButtonPressed specifies whether the associated button has been pressed.*/
	private void takeInBall(boolean takeInButtonPressed, boolean shootOutButtonPressed) {
		if(takeInButtonPressed) {
			//start taking in the ball if we haven't already
			System.out.println("Taking in ball");
			Constants.INTAKEMOTORCONTROLLER.set(-1.0);
		} else if (!shootOutButtonPressed) { //only set intakemotorcontroller to 0 if the other button isn't being pressed so we don't interfere.
			System.out.println("Not taking in ball");
			Constants.INTAKEMOTORCONTROLLER.set(0); //stop the motor
		}
	}
	/**Function to shoot out the ball. The parameters xxxxxxButtonPressed specifies whether the associated button has been pressed.*/
	private void shootOutBall(boolean takeInButtonPressed, boolean shootOutButtonPressed) {
		if(shootOutButtonPressed) {
			System.out.println("Shooting out ball");
			Constants.INTAKEMOTORCONTROLLER.set(1.0);
		} else if(!takeInButtonPressed) { //only set intakemotorcontroller to 0 if the other button isn't being pressed so we don't interfere.
			System.out.println("Not shooting out ball");
			Constants.INTAKEMOTORCONTROLLER.set(0.0);
		}
	}
	@Override
	public void process() {
		boolean takeInButtonPressed = stick.getRawButton(Constants.TAKEINBALLBUTTONID);
		boolean shootOutButtonPressed = stick.getRawButton(Constants.SHOOTOUTBALLBUTTONID);
		takeInBall(takeInButtonPressed, shootOutButtonPressed);
		shootOutBall(takeInButtonPressed, shootOutButtonPressed);
	}
	
}
