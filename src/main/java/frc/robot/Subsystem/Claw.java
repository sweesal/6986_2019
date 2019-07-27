
package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Claw extends Subsystem {

  private VictorSP clawMotor = RobotMap.clawMotor;

  private Solenoid solenoidClaw = RobotMap.solenoidClaw;
  private Solenoid solenoidHook = RobotMap.solenoidHook;


  //Set default state of the flag.
  public boolean clawFlag = false;
  public boolean hookFlag = false;

  public Claw(){

  }


  @Override
  public void periodic() {


    //Monitior the state of these flags periodiclly.
    GetClawFlag();
    GetHookFlag();

    // SmartDashboard.putBoolean("claw",GetClawFlag());
    // SmartDashboard.putBoolean("hook",GetHookFlag());

   
  }

  @Override
  public void initDefaultCommand() {

    //Get back the hook and the claw when match starts.
    HookBack();
    ClawBack();



  }


  //Press the button then flag turns on, double press it then it turns off. 
  //Then we can control the claw and the hook through only one button.

  public boolean GetClawFlag(){
      if(Robot.oi.logitechF310.getRawButtonPressed(2)){
          clawFlag = clawFlag ? false : true;
      }
      return clawFlag;
  }

  public boolean GetHookFlag(){
      if(Robot.oi.logitechF310.getRawButtonPressed(3)){
          hookFlag = hookFlag ? false : true;
      }
      return hookFlag;
  }


  //The code below are some simpliest methods that operate the claw and the clawwheels.
  //Don't think I should write comments for these things.

  public void IntakeBall(){
      clawMotor.set(-0.65);
  }

  public void OuttakeBall(){
      clawMotor.set(0.9);
  }

  public void StopBallMotor(){
      clawMotor.set(0);
  }

  public void HookOut(){
      solenoidHook.set(true);
  }

  public void HookBack(){
      solenoidHook.set(false);
  }

  public void ClawOut(){
      solenoidClaw.set(true);
  }

  public void ClawBack(){
      solenoidClaw.set(false);
  }

}
