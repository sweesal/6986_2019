package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Subsystem.Claw;
import frc.robot.Subsystem.Climber;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.DriveBaseAdvanced;
import frc.robot.Subsystem.Elevator;
import frc.robot.Subsystem.TrackPanel;


public class Robot extends TimedRobot {


  public static OI oi;

  public static Claw claw;
  public static Climber climber;
  public static DriveBase drivebase;
  public static Elevator elevator;
  public static DriveBaseAdvanced driveBaseAdvanced;
  //public static SerialPort openMV_Port = new SerialPort(115200, edu.wpi.first.wpilibj.SerialPort.Port.kUSB2);
  public static TrackPanel trackpanel;
  
  @Override
    public void robotInit() {
    //this.m_period = 0.04;

    RobotMap.initRobotMap();

    claw = new Claw();
    climber = new Climber();
    elevator = new Elevator();
    drivebase = new DriveBase();
    driveBaseAdvanced = new DriveBaseAdvanced();
    //trackpanel = new TrackPanel(openMV_Port);

    driveBaseAdvanced.MovementRecorderInit();
    oi = new OI();


  }


  @Override
  public void robotPeriodic() {

    Scheduler.getInstance().run();

    RobotMap.ConfigreSmartDashboardDisplay();

    

  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }
  
  @Override
  public void teleopInit() {

  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();

  }

}
