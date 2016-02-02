package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveControl extends RobotControl {
	RobotDrive rd = null;

	public DriveControl(Joystick stick, SpeedController sc[], int buttons[], int forbiddenButtons[], long maxtime) {
		super(stick, sc, buttons, forbiddenButtons, maxtime);
		if(sc.length > 3)
			rd = new RobotDrive(sc[0], sc[1], sc[2], sc[3]);
	}
	

	@Override
	protected void internalProcess() {
		if(rd != null)
			rd.arcadeDrive(stick);
		System.out.println("DriveControl");
	}
}
