package frc.robot.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Utilities.Algorithm;

/**
 * These are some advances methods for the drivebase inherited from the
 * DriveBase class.
 */

public class DriveBaseAdvanced extends Subsystem {

    public WPI_TalonSRX left_1 = RobotMap.left_1;
    public WPI_TalonSRX left_2 = RobotMap.left_2;
    public WPI_TalonSRX left_3 = RobotMap.left_3;
    public WPI_TalonSRX right_1 = RobotMap.right_1;
    public WPI_TalonSRX right_2 = RobotMap.right_2;
    public WPI_TalonSRX right_3 = RobotMap.right_3;

    //public SpeedControllerGroup leftGroup = new SpeedControllerGroup(left_1, left_2, left_3);
    //public SpeedControllerGroup rightGroup = new SpeedControllerGroup(right_1, right_2, right_3);
    //public DifferentialDrive differentialDrive = new DifferentialDrive(leftGroup, rightGroup);



    public DriveBaseAdvanced(){

      MovementRecorderInit();
    //  CalculateDistance();

    }

    @Override
    public void initDefaultCommand() {

    }

    @Override
    public void periodic() {

      if(Robot.oi.middleRightButton.get()){
        ResetDistance();
      }

      CalculateDistance();
    //CalculateVelAccel();
      SmartDashboard.putNumber("leftPos", left_2.getSelectedSensorPosition());
      SmartDashboard.putNumber("rightPos", right_2.getSelectedSensorPosition());
    
    }

    public void test(){
      
    }

    public void ResetDistance(){

      leftDistance = 0;
      rightDistance = 0;
      left_2.setSelectedSensorPosition(0);
      right_2.setSelectedSensorPosition(0);
    
    }


  //ref: team 6947 code 2019. Hope we'd be able to measure out these parameters and finish the pathweaver soon.
    private static double prevLeftVelocity, prevRightVelocity;
    private static double maxLeftVelocity, maxRightVelocity;

    private static double prevLeftAccel, prevRightAccel;
    private static double maxLeftAccel, maxRightAccel;

    private static double maxRightJerk, maxLeftJerk;

    private static double leftDistance = 0;
    private static double rightDistance = 0;

    public static Timer maxVelAccelTimer;
      
    public void MovementRecorderInit(){


      maxVelAccelTimer = new Timer();

      maxVelAccelTimer.reset();
      maxVelAccelTimer.start();
      ResetDistance();

      prevLeftVelocity = 0;
      prevRightVelocity = 0;
          
      maxLeftVelocity = 0;
      maxRightVelocity = 0;

      prevLeftAccel = 0;
      prevRightAccel = 0;

      maxLeftAccel = 0;
      maxRightAccel = 0;

      maxRightJerk = 0;
      maxLeftJerk = 0;

          
  }

  public void CalculateDistance(){

    //double distance = 0;
      
    double leftMpS = Algorithm.EncoderToMs(left_2.getSelectedSensorVelocity(), 6);
    double rightMpS = Algorithm.EncoderToMs(right_2.getSelectedSensorVelocity(), 6);

    if(leftMpS > maxLeftVelocity){
        maxLeftVelocity = leftMpS;
    }
    if(rightMpS > maxRightVelocity){
        maxRightVelocity = rightMpS;
    }

    double leftAccel = 0;
    double rightAccel = 0;

    double rightJerk = 0;
    double leftJerk = 0;

    if(maxVelAccelTimer.get()>.02){

      leftDistance = leftDistance + leftMpS;
      rightDistance =  rightDistance + rightMpS;
      
      //double distance = (leftDistance + rightDistance)/2;

      leftAccel = (leftMpS - prevLeftVelocity) /.02;
      rightAccel = (rightMpS - prevRightVelocity) /.02;

      leftJerk = (leftAccel - prevLeftAccel) / .02;
      rightJerk = (rightAccel - prevRightAccel) / .02;

      leftDistance = leftDistance + leftMpS;
      rightDistance =  rightDistance + rightMpS;


      if(leftAccel > maxLeftAccel){
        maxLeftAccel = leftAccel;
      }

      if(rightAccel > maxRightAccel){
        maxRightAccel = rightAccel;
      }

      if(leftJerk > maxLeftJerk){
        maxLeftJerk = leftJerk;
      }
      if(rightJerk > maxRightJerk){
        maxRightJerk = rightJerk;
      }

    }

    SmartDashboard.putNumber("leftVelo", leftMpS);
    SmartDashboard.putNumber("rightVelo", rightMpS);
    SmartDashboard.putNumber("Velo", Algorithm.SquareAv(leftMpS, rightMpS));

    SmartDashboard.putNumber("preleftVelo", prevLeftVelocity);


    SmartDashboard.putNumber("distanceLeft", leftDistance);
    SmartDashboard.putNumber("distanceRight", rightDistance);

    SmartDashboard.putNumber("distance", leftDistance/2 + rightDistance/2);
    //SmartDashboard.putNumber("distance", distance);

    SmartDashboard.putNumber("accelLeft", leftAccel);
    SmartDashboard.putNumber("accelRight", rightAccel);
    SmartDashboard.putNumber("accel", Algorithm.SquareAv(leftAccel, rightAccel));
    SmartDashboard.putNumber("jerkLeft", leftJerk);
    SmartDashboard.putNumber("jerkRight", rightJerk);
    SmartDashboard.putNumber("jerk", Algorithm.SquareAv(leftJerk, rightJerk));

    SmartDashboard.putNumber("accelLeftMax", maxLeftAccel);
    SmartDashboard.putNumber("accelRightMax", maxRightAccel);
    SmartDashboard.putNumber("jerkLeftMax", maxLeftJerk);
    SmartDashboard.putNumber("jerkRightMax", maxRightJerk);

    prevLeftVelocity = leftMpS;
    prevRightVelocity = rightMpS;

    prevLeftAccel = leftAccel;
    prevRightAccel = rightAccel;

  }




}
