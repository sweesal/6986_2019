package frc.robot.Commands.RobotDrive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TankDrive extends Command {
  public TankDrive() {
    requires(Robot.drivebase);
  }

  @Override
  protected void initialize() {
  }


  @Override
  protected void execute() {
    Robot.drivebase.TankDrive_PercentOutput(Robot.oi.logitechF310.getRawAxis(1), Robot.oi.logitechF310.getRawAxis(5));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }


  @Override
  protected void end() {
    Robot.drivebase.StopRobot_TankDrive();
  }


  @Override
  protected void interrupted() {
    end();
  }
}
