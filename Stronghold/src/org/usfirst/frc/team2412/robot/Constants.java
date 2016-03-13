package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;


// Robot CANTalon <ID> assignment guide
//
//       (Front)
//   ____       ____
//  |    |     |    |
//[]|    |     |    |[]
//[]|    |     |    |[]
//  |    |-----|    |
//[]|               |[]
//[]|    <7><8>     |[]
//  |      <6>  <9> |
//[]|<0><2>   <5><3>|[]
//[]|<1>         <4>|[]
//   ---------------
//
//        (Back)
//
// 0,1,3,4 = Drive/Climb motors
// 6 = Extend motor
// 7,8 = Climb motor mode switch
// 9 = Intake motor
public final class Constants {
	/******************************TELEOPERATED CONSTANTS******************************************/
	//Joysticks
	public static final Joystick DRIVERCONTROLS = new Joystick(0); //Joystick for controlling driving
	public static final Joystick CODRIVERCONTROLS = new Joystick(1); //"Joystick" (actually the co-driver) for other operations such as shooting
	
	//CANTalons for DriveControl
	public static final CANTalon DRIVEL1CONTROLLER = new CANTalon(12);
	public static final CANTalon DRIVEL2CONTROLLER = new CANTalon(6);
	public static final CANTalon DRIVEL3CONTROLLER = new CANTalon(7);  // Mr Johnston changed from 4 to 7
	public static final CANTalon DRIVER1CONTROLLER = new CANTalon(8);
	public static final CANTalon DRIVER2CONTROLLER = new CANTalon(3);
	public static final CANTalon DRIVER3CONTROLLER = new CANTalon(2);
	
	//Buttons IDs for IntakeControl (Mr Johnston: now on codriver board)
	public static final int TAKEINBALLBUTTONID = 1;  // Mr Johnston changed from 3 to 2
	public static final int SHOOTOUTBALLBUTTONID = 2; //same as GEARCHANGELEFTBUTTONID, but on joystick, not codriver
	//Motor Controller for IntakeControl
	public static final CANTalon INTAKEMOTORCONTROLLER = new CANTalon(5);
	
	//Button IDs for ClimbControl (They will be on the codriver)
	public static final int GEARCHANGELEFTBUTTONID = 3;
	public static final int GEARCHANGERIGHTBUTTONID = 4;
	public static final int EXTENDARMBUTTONID = 11;
	public static final int PULLUPROBOTBUTTONID = 5;
	//Motor Controllers for ClimbControl
	public static final CANTalon GEARCHANGELEFTCONTROLLER = new CANTalon(9);
	public static final CANTalon GEARCHANGERIGHTCONTROLLER = new CANTalon(10);
	public static final CANTalon EXTENDARMCONTROLLER = new CANTalon(11);
	//The CANTalons for pulling the robot up are the same as the ones for DriveControl (see above)
	
	/****************************AUTONOMOUS CONSTANTS********************************************/
	
	//Which DIO pin the Optical Sensor is plugged into.
	public static final int OPTICALSENSORPIN = 0;
	//The actual Optical Sensor (as DigitalInput because the Optical Sensor is connected to DIO)
	public static final DigitalInput OPTICALSENSOR = new DigitalInput(OPTICALSENSORPIN);
	
	//Constants determining which mode we're in (going from what the robot needs to do first to last)
	public static final int MOVETOWARDSOBSTACLE = 0;
	public static final int DRIVETHROUGHOBSTACLE = 1; //includes driving all the way until the wall
	public static final int MOVETOWARDGOAL = 2;
	public static final int SHOOT = 3;
	public static final int DRIVEBACK = 4;
};