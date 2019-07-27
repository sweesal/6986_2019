package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class ElevatorPID extends PIDSubsystem{
  /**
   * Add your docs here.
   */
  public ElevatorPID() {
    // Intert a subsystem name and PID values here
    super("SubsystemName", 0, 0, 0);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    disable();
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {

    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
}
