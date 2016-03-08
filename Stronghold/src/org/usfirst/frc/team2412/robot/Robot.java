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
	RobotControl rcs[] = new RobotControl[2];
	
	
	/********AUTONOMOUS VARIABLES****************/
	//startup time for autonomous (in nanoseconds)
	long startupTimeAutonomous;
	
	//how long we want to drive (in nanoseconds)
	long driveTimeAutonomous = 1000000000;
	
	//Robotdrive for autonomous (set to null at teleopInit so we don't interfere)
	RobotDrive robotDriveAutonomous = null;
	
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
		rcs[1] = new IntakeControl(Constants.DRIVERCONTROLS);
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		robotDriveAutonomous = new RobotDrive(Constants.DRIVEL1CONTROLLER, Constants.DRIVEL2CONTROLLER, Constants.DRIVER1CONTROLLER, Constants.DRIVER2CONTROLLER);
		startupTimeAutonomous = System.nanoTime();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		updateAutonomousMode();
		switch(autonomousMode) {
		case Constants.MOVETOWARDSOBSTACLE:
			//if(robotPosition == obstaclePosition) {
				//drive forward for now
				//robotDriveAutonomous.drive(1.0, 0.0);
			//}
			//break;
			System.out.println("Move toward");
			robotDriveAutonomous.drive(0.25, 0.0);
			break;
		case Constants.DRIVETHROUGHOBSTACLE:
			robotDriveAutonomous.drive(0.25, 0.0);
			System.out.println("Drive through obstacle");
			break;
		case Constants.MOVETOWARDGOAL:
			//turn
			System.out.println("Turning");
			robotDriveAutonomous.drive(0.25, 1.0);
			break;
		case Constants.SHOOT:
			System.out.println("Shoot");
			robotDriveAutonomous.drive(0.0, 0.0); //stop
			Constants.INTAKEMOTORCONTROLLER.set(1.0);
			break;
		case Constants.DRIVEBACK:
			System.out.println("Driving back");
			robotDriveAutonomous.drive(0.25, 0);
			break;
		}
	}
	
	//method to update autonomous mode if necessary
	private void updateAutonomousMode() {
			long deltaTime = System.nanoTime() - startupTimeAutonomous;
			//System.out.println(deltaTime);
			switch(autonomousMode) {
			case Constants.MOVETOWARDSOBSTACLE:
				if(deltaTime > 1000000000L)
					autonomousMode = Constants.DRIVETHROUGHOBSTACLE;
				break;
			case Constants.DRIVETHROUGHOBSTACLE:
				if(deltaTime > 6000000000L)
					autonomousMode = Constants.MOVETOWARDGOAL;
				break;
			case Constants.MOVETOWARDGOAL:
				if(deltaTime > 10000000000L)
					autonomousMode = Constants.SHOOT;
				break;
			case Constants.SHOOT:
				if(deltaTime > 11000000000L)
					autonomousMode = Constants.DRIVEBACK;
				break;
			default:
				break;
			}
	}
	/**
	 * This function is called once each time the robot enters tele-operated mode
	 */
	public void teleopInit(){
		//set robotDriveAutonomous to null so it doesn't interfere with the teleop motor code
		robotDriveAutonomous = null;
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
