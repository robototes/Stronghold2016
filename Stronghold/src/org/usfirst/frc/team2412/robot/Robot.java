package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotControl rcs[] = new RobotControl[3];
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		Constants.INTAKEMOTORCONTROLLER.setSafetyEnabled(false);
		rcs[0] = new DriveControl(Constants.DRIVERCONTROLS, Constants.DRIVEFRONTLEFTCONTROLLER, Constants.DRIVEREARLEFTCONTROLLER, Constants.DRIVEFRONTRIGHTCONTROLLER, Constants.DRIVEREARRIGHTCONTROLLER);
		rcs[1] = new IntakeControl(Constants.DRIVERCONTROLS);
		rcs[2] = new ClimbControl(Constants.DRIVERCONTROLS);
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		/*if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}*/
	}
	
	/**
	 * This function is called once each time the robot enters tele-operated mode
	 */
	public void teleopInit(){
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		//check if any of the gear change buttons (used for climbing) have been pressed and remove DriveControl if they have (because we won't need it when climbing)
		if(Constants.DRIVERCONTROLS.getRawButton(Constants.GEARCHANGELEFTBUTTONID) || Constants.DRIVERCONTROLS.getRawButton(Constants.GEARCHANGERIGHTBUTTONID)) { 
			rcs[0] = null; //the first RobotControl class will always be RobotDrive. Setting it to null will make the loop below ignore it.
		}
		for(RobotControl rc : rcs) {
			if(rc!=null) rc.process();
		}
		
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
	
}
