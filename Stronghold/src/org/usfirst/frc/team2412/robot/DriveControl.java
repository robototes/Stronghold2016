package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveControl extends RobotControl {

	public DriveControl(Joystick stick, SpeedController sc[], int buttons[], long maxtime) {
		super(stick, sc, buttons, maxtime);
	}
	

	@Override
	protected void internalProcess() {
		System.out.println("DriveControl");
	}

}
