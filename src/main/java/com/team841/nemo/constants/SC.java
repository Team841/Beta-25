package com.team841.nemo.constants;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

public class SC {
  public static class Shooter {
    public static int intakeMotorID = 9;
    public static final TalonFXConfiguration configs = new TalonFXConfiguration();
    public static final CurrentLimitsConfigs currentLimits =
        new CurrentLimitsConfigs()
            .withStatorCurrentLimit(60)
            .withStatorCurrentLimitEnable(true)
            .withSupplyCurrentLimitEnable(true);

    public static final int CANRangeFrontID = 0;
    public static final int CANRangeBackID = 1;
  }

  public static class Escalator {
    public static int left = 12;
    public static int right = 11;
    public static int CANRange = 3;
    public static Slot0Configs slot0Configs =
        new Slot0Configs()
            .withKP(75)
            .withKD(1.8)
            .withKS(5.21)
            .withKV(5)
            .withKA(0.387)
            .withKG(8.79)
            .withGravityType(GravityTypeValue.Elevator_Static)
            .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseVelocitySign);
    public static double FF = 12.2;
    public static final TalonFXConfiguration configs =
        new TalonFXConfiguration()
            .withSlot0(slot0Configs)
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withStatorCurrentLimit(60)
                    .withStatorCurrentLimitEnable(true)
                    .withSupplyCurrentLimitEnable(true))
            .withFeedback(
                new FeedbackConfigs()
                    .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor))
            .withMotionMagic(
                new MotionMagicConfigs()
                    .withMotionMagicAcceleration(145)
                    .withMotionMagicCruiseVelocity(160*2)
                    .withMotionMagicJerk(130))
                .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake))
                .withAudio(new AudioConfigs().withAllowMusicDurDisable(true));
  }

  public static class Hang{
            public static final int left = 13;
            public static final int right = 3;

            public static final TalonFXConfiguration configs =
                new TalonFXConfiguration()
                    .withCurrentLimits(
                        new CurrentLimitsConfigs()
                            .withStatorCurrentLimit(60)
                            .withStatorCurrentLimitEnable(true)
                            .withSupplyCurrentLimitEnable(true))
                    .withFeedback(
                        new FeedbackConfigs()
                            .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor))
                        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake))
                        .withAudio(new AudioConfigs().withAllowMusicDurDisable(true));
            
  }
}
