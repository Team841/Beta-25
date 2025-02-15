package com.team841.nemo.escalator;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.team841.nemo.constants.SC;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Escalator extends SubsystemBase {
    private final TalonFX leftMotor = new TalonFX(SC.Escalator.left);
    private final TalonFX rightMotor = new TalonFX(SC.Escalator.right);

    private final PositionDutyCycle control;
    private final Follower leftFollower;

    public Escalator() {
        this.leftMotor.getConfigurator().apply(SC.Escalator.configs);
        this.rightMotor.getConfigurator().apply(SC.Escalator.configs);

        this.control = new PositionDutyCycle(0);
        this.control.FeedForward = SC.Escalator.FF;
        this.control.Velocity = 5;
        this.leftFollower = new Follower(SC.Escalator.right, true);
    }

    public void Log(){
    }

    @Override
    public void periodic() {
        Log();
        SmartDashboard.putNumber("Motor Pos", rightMotor.getPosition().getValueAsDouble());
        SmartDashboard.putNumber("setpoint", control.Position);
    }

    public void setPosition(double Position){
        rightMotor.setControl(this.control.withPosition(Position));
    }

    public void stop(){
        rightMotor.stopMotor();
    }

    public void goUp(){
        rightMotor.set(0.2);
    }

    public void goDown(){
        rightMotor.set(-0.2);
    }

}
