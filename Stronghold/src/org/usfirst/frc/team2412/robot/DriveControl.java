package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveControl extends RobotControl {
	RobotDrive rd = null;
	RobotDrive rd2 = null; //second rd for the extra two motors

	public DriveControl(Joystick stick, CANTalon l1, CANTalon l2, CANTalon l3, CANTalon r1, CANTalon r2, CANTalon r3) {
		this.stick = stick;
		rd = new RobotDrive(l1, l2, r1, r2);
		rd2 = new RobotDrive(l3, r3);
	}

	void drive() {
		double stickY = -stick.getY();
		double stickX = -stick.getX();
		
		double stickTwist = -Math.pow(stick.getRawAxis(3), 1); //getRawAxis(3) is for the new joystick, getTwist is for the logitech joystick.
		if(stick.getRawButton(5)) {
			//Drive like airplane
			rd.arcadeDrive(stickY, stickX, true);
			rd2.arcadeDrive(stickY, stickX, true);
		} else {
			//Drive with twist
			rd.arcadeDrive(stickY, stickTwist, true); //Math.pow for cubing
			rd2.arcadeDrive(stickY, stickTwist, true);
		}
	}
	@Override
	public void process() {
		drive();
	}
}
