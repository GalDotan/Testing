
package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ma5951.utils.DashBoard.MAShuffleboard;
import com.ma5951.utils.Logger.LoggedBool;
import com.ma5951.utils.Logger.LoggedDouble;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Shooter extends SubsystemBase {
  private static Shooter shooter;

  private TalonFX motor;
  private DigitalInput frontIr;
  private MAShuffleboard board;

  private LoggedBool frontIRLog;


  private Shooter() {
    motor = new TalonFX(PortMap.Shooter.SHOOTER_LEFT_ID);
    frontIr = new DigitalInput(PortMap.Shooter.SHOOTER_IR_DIO);
    board = new MAShuffleboard("Shooter");
    frontIRLog = new LoggedBool("/Subsystems/Shooter/Fron IR");
  }

  public boolean getIR() {
    return frontIr.get();
  }

  public void setVoltage(double volt) {
    motor.setVoltage(volt);
  }

  public static Shooter getInstance() {
    if (shooter == null) {
      shooter = new Shooter();
    }

    return shooter;
  }

  @Override
  public void periodic() {
    board.addBoolean("Front IR", getIR());
    board.addNum("Motor get", motor.get());
    frontIRLog.update(getIR());
  }
}
