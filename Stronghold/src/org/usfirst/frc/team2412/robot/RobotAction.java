package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**This class represents an action that the robot must perform, such as extending the climbing arm*/
public abstract class RobotAction {
	/**TODO add sensors*/
	//stick to be used for controls
	Joystick stick;
	//motor controllers to be driven
	SpeedController scs[];
	public RobotAction(Joystick stick, SpeedController[] sc) {
		this.stick = stick;
		this.scs = sc;
	}
	
}
