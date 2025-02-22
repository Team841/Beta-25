package com.team841.nemo.hang;

import com.team841.nemo.constants.SC;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hang extends SubsystemBase {

  public final TalonFX leftMotor = new TalonFX(SC.Hang.left, "rio");
  public final TalonFX rightMotor = new TalonFX(SC.Hang.right, "rio");

  /** Creates a new Hang. */
  public Hang() {
    this.leftMotor.getConfigurator().apply(SC.Hang.configs);
    this.rightMotor.getConfigurator().apply(SC.Hang.configs);
  }

  public void setDutyCyle(double dutyCyle){
    this.leftMotor.set(dutyCyle);
    this.rightMotor.set(dutyCyle);
  }

  public void stop(){
    this.leftMotor.stopMotor();
    this.rightMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
