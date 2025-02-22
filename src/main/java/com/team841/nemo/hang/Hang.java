package com.team841.nemo.hang;

import com.ctre.phoenix6.hardware.TalonFX;
import com.team841.nemo.constants.SC;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hang extends SubsystemBase {

  public final TalonFX leftMotor = new TalonFX(SC.Hang.left, "rio");
  public final TalonFX rightMotor = new TalonFX(SC.Hang.right, "rio");
  public final TalonFX intakeMotor = new TalonFX(SC.Intake.id, "rio");

  /** Creates a new Hang. */
  public Hang() {
    this.leftMotor.getConfigurator().apply(SC.Hang.configs);
    this.rightMotor.getConfigurator().apply(SC.Hang.configs);
    this.intakeMotor.getConfigurator().apply(SC.Intake.configs);
  }

  public void setDutyCyle(double dutyCyle) {
    this.leftMotor.set(dutyCyle);
    this.rightMotor.set(dutyCyle);
  }

  public void stop() {
    this.leftMotor.stopMotor();
    this.rightMotor.stopMotor();
  }

  public Command retract(){
    return new RunCommand(() -> this.setDutyCyle(-0.24), this);
  }
  
  public Command deploy(){
    return new RunCommand(() -> this.setDutyCyle(0.24), this);
  }

  public Command intakeUp(){
    return new RunCommand(() -> this.intakeMotor.set(0.1), this);
  }

  public Command intakeDown(){
    return new RunCommand(() -> this.intakeMotor.set(-0.1), this);
  }

  public void stopIntake(){
    this.intakeMotor.stopMotor();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
