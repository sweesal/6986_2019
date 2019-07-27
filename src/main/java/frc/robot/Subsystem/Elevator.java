package frc.robot.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class Elevator extends Subsystem {

  //Tow motors fot the elevator.
  public WPI_TalonSRX elevatorMotorLeft = RobotMap.elevatorLeftMotor;
  public WPI_TalonSRX elevatorMotorRight = RobotMap.elevatorRightMotor;

  //The elecator limitswitch will stop the elevator motor when it returns true at the game period.
  public DigitalInput limitSwitchElevator = RobotMap.limitSwitchElevator;

  public Encoder encoderElevator = RobotMap.encoderElevator;

  public SpeedControllerGroup speedControllerGroupElevate = new SpeedControllerGroup(elevatorMotorLeft, elevatorMotorRight);

  //Set default climbing flag.
  public boolean climbingFlag = false;


  @Override
  public void periodic() {
    
   // SmartDashboard.putNumber("6", elevatorMotorLeft.getSelectedSensorPosition());
   // SmartDashboard.putNumber("7", elevatorMotorRight.getSelectedSensorPosition());

    ResetElevatorEncoder();
   
  }

  @Override
  public void initDefaultCommand() {

  }

  public void ResetElevatorEncoder(){
    if(limitSwitchElevator.get()  == false){
      encoderElevator.reset();
    }
  }

  // The func Ascend(), The encoder is ranged between 0-650 from ground to the top.
  // So we make the motors set different speed at the different level.
  //   0-500:   60%;
  //   500-585: 45%;
  //   585-620: 30%;
  //   >620;     0%;
  // Note that the encoder read of 620 is also the mecanical highest level the robot can reach.

  public void Ascend(){
    if(encoderElevator.get() > 500){
        if(encoderElevator.get() > 585){
            if(encoderElevator.get() > 620){
                speedControllerGroupElevate.set(0);
            }
            else{
                speedControllerGroupElevate.set(0.3);
            }
        }
        else{
            speedControllerGroupElevate.set(0.45);
        }
    }
    else{
        speedControllerGroupElevate.set(0.6);
    }
  }

  //The same as Ascend(), no more comment this time.
  // 0-45:   0.4%;
  // 45-120  0.5%;
  // >120    0.65%
  // else:   0;
  //Note that the percent output in Descend is generally smaller than the Ascend() because the weight of the claw.

  public void Decsend(){
    if(limitSwitchElevator.get() == true){
        if(encoderElevator.get() < 120){
            if(encoderElevator.get() > 45){
                speedControllerGroupElevate.set(-0.5);
            }
            else{
                speedControllerGroupElevate.set(-0.4);
            }
        }
        else{
            speedControllerGroupElevate.set(-0.65);
        }
    }
    else{
        speedControllerGroupElevate.set(0);
    }
  }

  public void StopElevating(){
    speedControllerGroupElevate.stopMotor();
  }

  public void Ascend_PercentOuptput(double inputPower){
      if(encoderElevator.get() < 600){
        speedControllerGroupElevate.set(inputPower);
      }else{
        speedControllerGroupElevate.set(0);
      }
  }



  

}
