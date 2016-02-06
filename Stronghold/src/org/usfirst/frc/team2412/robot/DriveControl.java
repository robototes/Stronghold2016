package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveControl extends RobotControl {
	RobotDrive rd = null;

	public DriveControl(Joystick stick, CANTalon frontLeft, CANTalon rearLeft, CANTalon frontRight, CANTalon rearRight) {
		this.stick = stick;
		rd = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	

	void drive() {
		rd.arcadeDrive(stick);
	}
	@Override
	public void process() {
		drive();
	}
}
