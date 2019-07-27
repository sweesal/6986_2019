package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
//import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Utilities.Constants;

public class TrackPanel extends Subsystem{

    public boolean panelFlag = false;
    private SerialPort openMV_Port;
    public Solenoid lampLED = RobotMap.lampLEDSolenoid;

    public TrackPanel(SerialPort openMV_Port){
        this.openMV_Port = openMV_Port;
    }

    @Override
    public void initDefaultCommand() {
  
    }

    @Override
    public void periodic() {
     
        if (Robot.oi.logitechF310.getRawButtonPressed(7)){
            panelFlag = true;
          }
        if ((Robot.oi.logitechF310.getRawButtonReleased(7))){
              panelFlag = false;
        }
        if (panelFlag){
            OpenLamp();
            //runTrackPanel();
        }else{
            CloseLamp();
        }
      
    }


    //Run TrackPnel method when button pressed was detected.


    public void runTrackPanel() {
        
        //OpenLamp();
        openMV_Port.writeString("1");
        openMV_Port.flush();
        String rev = openMV_Port.readString();
        try {
            
            int index = rev.indexOf("[");
            int index2 = rev.indexOf(",");
            int index3 = rev.indexOf("]");
            double x = Double.valueOf(rev.substring(index + 1, index2));
            double y = Double.valueOf(rev.substring(index2 + 1, index3));
            double errorX = Constants.panelFrameXpixel - x;
            double errorY = Constants.panelFrameYpixel - y;
            
            double speedLeft = errorY * Constants.vision_kP - errorX * Constants.vision_kP;
            double speedRight = errorY * Constants.vision_kP + errorX * Constants.vision_kP;

            Robot.drivebase.TankDrive_PercentOutput(-speedLeft, -speedRight);
            
        } catch (Exception e) {
            e.toString();
        }
        System.out.println(rev);
    }



    //Turn on & off the vision LED.
    public void OpenLamp(){

        lampLED.set(true);
        
    }
    
    public void CloseLamp(){

        lampLED.set(false);

    }

    

}
