package com.team841.nemo.escalator;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANrange;
import com.ctre.phoenix6.hardware.TalonFX;
import com.team841.nemo.constants.RC;
import com.team841.nemo.constants.SC;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Escalator extends SubsystemBase {
  public final TalonFX leftMotor = new TalonFX(SC.Escalator.left, RC.kCANBus);
  public final TalonFX rightMotor = new TalonFX(SC.Escalator.right, RC.kCANBus);
  private final CANrange CANRange = new CANrange(SC.Escalator.CANRange);

  private final MotionMagicTorqueCurrentFOC control;
  private final Follower leftFollower;

  private final Position currentPosition = Position.Home;

  public Escalator() {
    this.leftMotor.getConfigurator().apply(SC.Escalator.configs);
    this.rightMotor.getConfigurator().apply(SC.Escalator.configs);

    this.control = new MotionMagicTorqueCurrentFOC(0);
    this.control.FeedForward = SC.Escalator.FF;
    this.leftFollower = new Follower(SC.Escalator.right, true);
    this.leftMotor.setControl(this.leftFollower);
  }

  public void Log() {}

  @Override
  public void periodic() {
    Log();
    SmartDashboard.putNumber("Motor Pos", rightMotor.getPosition().getValueAsDouble());
    SmartDashboard.putNumber("setpoint", control.Position);
    SmartDashboard.putNumber("canrange", this.CANRange.getDistance().getValueAsDouble());

//    if (this.CANRange.getDistance().getValueAsDouble() < 0.11) {
//      this.rightMotor.setPosition(0);
//    }
  }

  public void setPosition(double Position) {
    rightMotor.setControl(this.control.withPosition(Position));
  }

  public void setPosition(Position position) {
    setPosition(position.getPosition());
  }

  public void stop() {
    rightMotor.stopMotor();
  }

  public void goUp() {
    rightMotor.set(0.2);
  }

  public void goDown() {
    rightMotor.set(-0.2);
  }

  public boolean isAtPosition(Position position) {
    return Math.abs(this.rightMotor.getPosition().getValueAsDouble() - position.getPosition())
        < 0.75;
  }

  public enum Position {
    Intake(0.0),
    L1(15.25),
    L2(23.57),
    L3(36.02),
    L4(57.95),
    Home(2.0);

    private final double position;

    // Constructor
    Position(double position) {
      this.position = position;
    }

    // Getter method
    public double getPosition() {
      return position;
    }
  }
}
