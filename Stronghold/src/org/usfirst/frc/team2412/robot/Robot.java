package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	RobotControl rcs[] = new RobotControl[3];//Jacob changed it from 2 to 3
	
	
	/********AUTONOMOUS VARIABLES****************/
	//startup time for autonomous (in nanoseconds)
	long startupTimeAutonomous = -1;
	//how long we want to drive (in nanoseconds)
	long driveTimeAutonomous = 1000000000;
	
	//Robotdrive for autonomous (set to null at teleopInit so we don't interfere)
	RobotDrive robotDriveAutonomous = null;
	RobotDrive robotDriveAutonomous2 = null;
	
	//which autonomous mode we are in (see Constants.java)
	private int autonomousMode = Constants.MOVETOWARDSOBSTACLE; //negative because that is not a valid Autonomous Mode Value
	
	//Position of robot, represented as an int
	//Position codes:
	// ----- ----- ----- ----- ----- -----
	//|     |     |     |     |     |     |
	//|  0  |  1  |  2  |  3  |  4  | S.P |
	//|     |     |     |     |     |     |
	// ----- ----- ----- ----- ----- -----
	//S.P = Secret Passage
	//private int robotPosition = -1; //negative so we know that it has not been set.
	//private int obstaclePosition = -1; //negative; see above for position codes.
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		Constants.INTAKEMOTORCONTROLLER.setSafetyEnabled(false);
		rcs[0] = new DriveControl(Constants.DRIVERCONTROLS, Constants.DRIVEL1CONTROLLER, Constants.DRIVEL2CONTROLLER, Constants.DRIVEL3CONTROLLER, Constants.DRIVER1CONTROLLER, Constants.DRIVER2CONTROLLER, Constants.DRIVER3CONTROLLER);
		rcs[1] = new IntakeControl(Constants.CODRIVERCONTROLS);
		rcs[2] = new ClimbControl(Constants.CODRIVERCONTROLS);
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		autonomousMode = Constants.MOVETOWARDSOBSTACLE; //added so that autonomousMode is reset every single time in order to prevent the robot from starting in the last mode.
		robotDriveAutonomous = new RobotDrive(Constants.DRIVEL1CONTROLLER, Constants.DRIVEL2CONTROLLER, Constants.DRIVER1CONTROLLER, Constants.DRIVER2CONTROLLER);
		robotDriveAutonomous2 = new RobotDrive(Constants.DRIVEL3CONTROLLER, Constants.DRIVER3CONTROLLER);
		startupTimeAutonomous = System.nanoTime();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		if(robotDriveAutonomous == null) {
			robotDriveAutonomous = new RobotDrive(Constants.DRIVEL1CONTROLLER, Constants.DRIVEL2CONTROLLER, Constants.DRIVER1CONTROLLER, Constants.DRIVER2CONTROLLER);
		}
		if(robotDriveAutonomous2 == null) {
			robotDriveAutonomous2 = new RobotDrive(Constants.DRIVEL3CONTROLLER, Constants.DRIVER3CONTROLLER);
		}
		if(startupTimeAutonomous < 0) {
			startupTimeAutonomous = System.nanoTime();
		}
		updateAutonomousMode();
		
		switch(autonomousMode) {
		case Constants.MOVETOWARDSOBSTACLE:
			//if(robotPosition == obstaclePosition) {
				//drive forward for now
				//robotDriveAutonomous.drive(1.0, 0.0);
			//}
			//break;
			System.out.println("Move toward");
			//Constants.INTAKEMOTORCONTROLLER.set(-1.0); //lower the arm
			//drive forward at 85%
			robotDriveAutonomous.drive(0.85, 0.0); //original was 0.7. drives the first four cims
			robotDriveAutonomous2.drive(0.85, 0.0); //drives the last 2 cims
			break;
		case Constants.DRIVETHROUGHOBSTACLE:
			Constants.INTAKEMOTORCONTROLLER.set(0.0); //turn off
			robotDriveAutonomous.drive(0.0, 0.0); //stop first four cims
			robotDriveAutonomous2.drive(0.0, 0.0); //stop last 2 cims
			System.out.println("Stopping"); //  Mr Johnston changed from: "Drive through obstacle");
			break;
		}
	}
	
	//method to update autonomous mode if necessary
	private void updateAutonomousMode() {
			long deltaTime = System.nanoTime() - startupTimeAutonomous; //get how long has passed since we first started (in nanoseconds)
			//System.out.println(deltaTime);
			//This is a simple implementation of a state machine - autonomousMode is set based on how much time has passed.
			switch(autonomousMode) {
			case Constants.MOVETOWARDSOBSTACLE:
				//stop driving forward after 3100000000 nanoseconds (3.1 seconds)
				if(deltaTime > 3100000000L) //original value was 800000000, next value was 1600000000L, jacob changed 21-31
					autonomousMode = Constants.DRIVETHROUGHOBSTACLE;
				break;
			//ignore all the stuff below:
			case Constants.DRIVETHROUGHOBSTACLE:
				if(deltaTime > 5000000000L) //moved up from 2000000000L because MOVETOWARDSOBSTACLE takes too long. 
					autonomousMode = Constants.MOVETOWARDGOAL;
				break;
			case Constants.MOVETOWARDGOAL:
				if(deltaTime > 7000000000L) //moved up from 5000000000L. See above.
					autonomousMode = Constants.SHOOT;
				break;
			case Constants.SHOOT:
				if(deltaTime > 8000000000L)
					autonomousMode = Constants.DRIVEBACK;
				break;
			default:
				break;
			}
	}
	/**
	 * This function is called once each time the robot enters tele-operated mode
	 */
	public void teleopInit() {
		/****Clean up from autonomous*/
		startupTimeAutonomous = -1; //reset autonomous time
		Constants.INTAKEMOTORCONTROLLER.set(0.0); //stop intake motor
		//Stop all motors
		if(robotDriveAutonomous != null)
			robotDriveAutonomous.drive(0.0, 0.0);
		if(robotDriveAutonomous2 != null)
			robotDriveAutonomous2.drive(0.0, 0.0);
		//set robotDriveAutonomous to null so it doesn't interfere with the teleop motor code
		robotDriveAutonomous = null;
		robotDriveAutonomous2 = null;
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		//Run all non-null RobotControls
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
