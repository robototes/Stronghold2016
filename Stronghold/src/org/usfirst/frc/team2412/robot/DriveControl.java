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
		if(stick.getRawButton(5)) {
			//Drive like airplane
			double stickY = stick.getY();
			double stickX = stick.getX();
			rd.arcadeDrive(stickY, stickX, false); // Question from Mr. Johnston: shouldn we call getY() and getX() only once?
			rd2.arcadeDrive(stickY, stickX, false);
		} else {
			//Drive with twist
			double stickY = stick.getY();
			double twist = -stick.getRawAxis(3);
			rd.arcadeDrive(stickY, twist, false);
			rd2.arcadeDrive(stickY, twist, false);
		}
	}
	@Override
	public void process() {
		drive();
	}
}
