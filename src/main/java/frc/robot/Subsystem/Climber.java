

package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap;

//The Climber Subsystem is inherited from the Elevator subsystem.

public class Climber extends Elevator {

    //When the gear shifts the motor elevator will not lift the claw, instead the aluminium frame will go dwon.
    //And lift the robot up to the platform.
    public Solenoid elevatorGear = RobotMap.elevatorGear;

    //The climbing cylinder will eject the climbing claws.
    public Solenoid climbingClawSolenoid = RobotMap.climbingClawSolenoid;

    //Tow motors for the small wheels under the chassis.
    public VictorSP climbingMotorLeft = RobotMap.climbingClawMotorLeft;
    public VictorSP climbingMotorRight = RobotMap.climbingClawMotorRight;

    public SpeedControllerGroup speedControllerGroupClimberMotors = new SpeedControllerGroup(climbingMotorLeft, climbingMotorRight);
    
    
    //The climber limitswithc will do the the same, but at the climbing period.
    public DigitalInput limitSwitchClimberLeft = RobotMap.limitSwitchClimbingLeft;
    public DigitalInput LimitSwitchClimberRight = RobotMap.limitSwitchClimbingRight;




  @Override
  public void periodic() {
   
  }


  @Override
  public void initDefaultCommand() {

    ClimbingClawBack();
    ClimbingGearDown();
    PlatStop();

  }

  public void ClimbingGearUp(){
      elevatorGear.set(true);

  }

  public void ClimbingGearDown(){
      elevatorGear.set(false);

  }

  public void ClimbingClawOut(){
     climbingClawSolenoid.set(true);

  }

  public void ClimbingClawBack(){
     climbingClawSolenoid.set(false);
  }

  public void Climbing(){

      //0-800:   0.75%;
      //800-900: 0.455；
      //>900：   0.25%;
      
      if(limitSwitchClimberLeft.get() == true || LimitSwitchClimberRight.get() == true){
          if(encoderElevator.get() > 800){
              if(encoderElevator.get() > 900){
                  speedControllerGroupElevate.set(0.25);
              }else{
                  speedControllerGroupElevate.set(0.45);
              }
          }else{
              speedControllerGroupElevate.set(0.75); 
          }
      }else{
          speedControllerGroupElevate.set(0);
      }

  }

  //These two motors aren't running at the same direction, so we give then different values sprerately.
  public void PlatForward(){
      //speedControllerGroupPlatMove.set(0.3);
      climbingMotorLeft.set(0.25);
      climbingMotorRight.set(-0.25);
  }

  public void PlatBackward(){
      //speedControllerGroupPlatMove.set(-0.3);
      climbingMotorLeft.set(-0.25);
      climbingMotorRight.set(0.25);

  }

  public void PlatStop(){
      //speedControllerGroupPlatMove.set(0);
      climbingMotorLeft.set(0);
      climbingMotorRight.set(0);
  }

}
