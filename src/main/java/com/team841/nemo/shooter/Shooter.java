package com.team841.nemo.shooter;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.hardware.TalonFX;
import com.team841.nemo.constants.SC;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final TalonFX intakeMotor = new TalonFX(SC.Shooter.intakeMotorID, "rio");

  public Shooter() {
    intakeMotor.getConfigurator().refresh(SC.Shooter.currentLimits);
    intakeMotor.getConfigurator().apply(SC.Shooter.currentLimits);
  }

  /**
   * Method to run intake a particuar setpoint
   *
   * @param setpoint the percent outut at which the intake should run.
   */
  public void runIntake(double setpoint) {
    intakeMotor.set(setpoint);
  }

  public void slowShooter() {
    intakeMotor.set(0.075);
  }

  public void pullIntake() {
    intakeMotor.set(0.3);
  }

  public void stop() {
    intakeMotor.stopMotor();
  }

  public void Log() {
    SignalLogger.writeDouble("Shooter/PercentOutput", intakeMotor.get());
  }

  public Command shoot() {
    return new RunCommand(() -> intakeMotor.set(-0.5), this)
        .withName("Shoot")
        .withTimeout(1)
        .finallyDo(() -> intakeMotor.stopMotor());
  }

  @Override
  public void periodic() {
    Log();
    SmartDashboard.putNumber("Shooter Percent Outpet", intakeMotor.get());
  }
}
