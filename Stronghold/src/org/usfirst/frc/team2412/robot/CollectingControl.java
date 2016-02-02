package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class CollectingControl extends RobotControl {

	public CollectingControl(Joystick stick, SpeedController scs[], int buttons[], int forbiddenButtons[], long maxtime) {
		super(stick, scs, buttons, forbiddenButtons, maxtime);
	}

	@Override
	protected void internalProcess() {
		for(SpeedController s : scs) {
			s.set(0); //to prevent not updated enough errors
		}
		System.out.println("CollectorControl");
	}
	
}
