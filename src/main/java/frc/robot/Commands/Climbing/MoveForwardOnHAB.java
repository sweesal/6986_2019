/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands.Climbing;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveForwardOnHAB extends Command {
  public MoveForwardOnHAB() {
    requires(Robot.climber);
    requires(Robot.drivebase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.climber.Climbing();
    Robot.drivebase.TankDrive_Original(-0.4, -0.4);
    Robot.climber.PlatForward();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.oi.logitechF310.getRawButtonPressed(1);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    if(RobotMap.limitSwitchClimbingLeft.get() == true || RobotMap.limitSwitchClimbingRight.get() == true){
      Robot.climber.Climbing();
    }else{
        Robot.elevator.StopElevating();
    }
    Robot.climber.PlatStop();
    Robot.drivebase.StopRobot_TankDrive();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
