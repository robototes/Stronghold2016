package org.usfirst.frc.team2412.testcode;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick stick;
	Joystick coStick;
	int autoLoopCounter;
	
	CANTalon r1 = new CANTalon(8);
	CANTalon r2 = new CANTalon(3);
	CANTalon l1 = new CANTalon(4);
	CANTalon l2 = new CANTalon(1);
	//Talon t = new Talon(1);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(l1, l2, r1, r2);
		//myRobot = new RobotDrive(new CANTalon(4), new CANTalon(2), new CANTalon(3), new CANTalon(5));
		stick = new Joystick(0);
		coStick = new Joystick(1);
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		autoLoopCounter = 0;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}
	}
	
	/**
	 * This function is called once each time the robot enters tele-operated mode
	 */
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		if(coStick.getRawButton(9))
			//Drive like airplane
			myRobot.arcadeDrive(-stick.getY(), -stick.getX(), false);
		else
			//Drive with twist
			myRobot.arcadeDrive(-stick.getY(), -stick.getTwist(), false);
		//alternatively we could just do:
		//myRobot.arcadeDrive(-stick.getY(), -(coStick.getRawButton(9) ? stick.getX() : stick.getTwist()), false);
		//but the way above is much simpler.
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
	
}
