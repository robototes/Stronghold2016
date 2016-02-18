package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;


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
	//Joysticks
	public static final Joystick DRIVERCONTROLS = new Joystick(0); //Joystick for controlling driving
	public static final Joystick CODRIVERCONTROLS = new Joystick(1); //"Joystick" (actually the co-driver) for other operations such as shooting
	
	//CANTalons for DriveControl
	public static final CANTalon DRIVEFRONTLEFTCONTROLLER = new CANTalon(2);
	public static final CANTalon DRIVEREARLEFTCONTROLLER = new CANTalon(4);
	public static final CANTalon DRIVEFRONTRIGHTCONTROLLER = new CANTalon(3);
	public static final CANTalon DRIVEREARRIGHTCONTROLLER = new CANTalon(5);
	
	//Buttons IDs for IntakeControl
	public static final int TAKEINBALLBUTTONID = 3;
	public static final int SHOOTOUTBALLBUTTONID = 1; //same as GEARCHANGELEFTBUTTONID, but on joystick, not codriver
	//Motor Controller for IntakeControl (currently talon sr, should change to talon srx)
	public static final Talon INTAKEMOTORCONTROLLER = new Talon(0);
	
	//Button IDs for ClimbControl (They will be on the codriver)
	public static final int GEARCHANGELEFTBUTTONID = 1;
	public static final int GEARCHANGERIGHTBUTTONID = 2;
	public static final int EXTENDARMBUTTONID = 11;
	public static final int PULLUPROBOTBUTTONID = 5;
	//Motor Controllers for ClimbControl
	public static final Talon GEARCHANGELEFTCONTROLLER = new Talon(1);
	public static final CANTalon GEARCHANGERIGHTCONTROLLER = new CANTalon(3);
	public static final CANTalon EXTENDARMCONTROLLER = new CANTalon(4);
	//The CANTalons for pulling the robot up are the same as the ones for DriveControl (see above)
};