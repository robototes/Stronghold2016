package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;

/**This class represents a control to action that the robot must perform, such as extending the climbing arm*/
public abstract class RobotControl {
	/**TODO add sensors*/
	//stick to be used for controls
	Joystick stick;
	//motor controllers to be driven
	public abstract void process();
}
