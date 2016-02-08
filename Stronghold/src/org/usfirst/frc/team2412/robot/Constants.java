package org.usfirst.frc.team2412.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;

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
	public static final int TAKEINBALLBUTTONID = 5;
	public static final int SHOOTOUTBALLBUTTONID = 6;
	//Motor Controller for IntakeControl
	public static final CANTalon INTAKEMOTORCONTROLLER = new CANTalon(5);
	
	//Button IDs for ClimbControl
	public static final int GEARCHANGELEFTBUTTONID = 10;
	public static final int GEARCHANGERIGHTBUTTONID = 11;
	public static final int EXTENDARMBUTTONID = 12;
	public static final int PULLUPROBOTBUTTONID = 9;
	//Motor Controllers for ClimbControl
	public static final CANTalon GEARCHANGELEFTCONTROLLER = new CANTalon(2);
	public static final CANTalon GEARCHANGERIGHTCONTROLLER = new CANTalon(3);
	public static final CANTalon EXTENDARMCONTROLLER = new CANTalon(4);
	//The CANTalons for pulling the robot up are the same as the ones for DriveControl (see above)
};