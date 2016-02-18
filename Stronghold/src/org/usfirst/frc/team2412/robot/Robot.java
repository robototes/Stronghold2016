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
	RobotControl rcs[] = new RobotControl[2];
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		Constants.INTAKEMOTORCONTROLLER.setSafetyEnabled(false);
		rcs[0] = new DriveControl(Constants.DRIVERCONTROLS, Constants.DRIVEFRONTLEFTCONTROLLER, Constants.DRIVEREARLEFTCONTROLLER, Constants.DRIVEFRONTRIGHTCONTROLLER, Constants.DRIVEREARRIGHTCONTROLLER);
		rcs[1] = new IntakeControl(Constants.CODRIVERCONTROLS);
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
		//check if any of the gear change buttons (used for climbing) have not been pressed (they are being pressed by default on the old codriver) and remove DriveControl if they have (because we won't need it when climbing)
		//TODO change this to check if the buttons are being pressed (vs. if they are NOT being pressed)
		if(!Constants.CODRIVERCONTROLS.getRawButton(Constants.GEARCHANGELEFTBUTTONID) || !Constants.CODRIVERCONTROLS.getRawButton(Constants.GEARCHANGERIGHTBUTTONID)) { 
			//rcs[0] = null; //the first RobotControl class will always be RobotDrive. Setting it to null will make the loop below ignore it.
			if(rcs[0] instanceof RobotControl) {
				rcs[0] = new ClimbControl(Constants.CODRIVERCONTROLS);
				System.out.println("ClimbControl");
			}
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
