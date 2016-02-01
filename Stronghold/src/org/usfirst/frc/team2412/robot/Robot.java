package org.usfirst.frc.team2412.robot;

import java.util.ArrayList;

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
	int autoLoopCounter;
	
	CANTalon CANs[] = {new CANTalon(2), new CANTalon(3), new CANTalon(4), new CANTalon(5)};
	CANTalon testCAN = new CANTalon(4);
	//allowed and forbidden buttons for dc (DriveControl) and cc (CollectingControl)
	int dcAllowedButtons[] = {-1};
	int dcForbiddenButtons[] = {12};
	
	int ccAllowedButtons[] = {1};
	int ccForbiddenButtons[] = {};
	RobotControl rcs[] = new RobotControl[5];
	ArrayList<String> messages = new ArrayList<String>();
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	myRobot = new RobotDrive(testCAN, new CANTalon(2), new CANTalon(3), new CANTalon(5));
    	stick = new Joystick(0);
    	rcs[0] = new DriveControl(stick, CANs, dcAllowedButtons, dcForbiddenButtons, 1000);
    	rcs[1] = new CollectingControl(stick, CANs, ccAllowedButtons, ccForbiddenButtons, 1000);
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
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	for(RobotControl rc : rcs) {
    		if(rc!=null) rc.process();
    	}
    	/*if(stick.getRawButton(1)) {
    		testCAN.set(stick.getX());
    	} else {
    		SmartDashboard.putString("Status", "Driving - ArcadeDrive");
    		myRobot.arcadeDrive(stick);
    	}*/
        //myRobot.arcadeDrive(stick);
    	//respond to joystick trigger
    	/*if(stick.getRawButton(1)) { //button 1 is the trigger
    		//drive
    		myRobot.drive(-1.0, 0);
    	}*/
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
