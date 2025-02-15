package com.team841.nemo.constants;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;

public class SC {
    public static class Shooter{
        public static int intakeMotorID = 9;
        public static final TalonFXConfiguration configs = new TalonFXConfiguration();
        public static final CurrentLimitsConfigs currentLimits = new CurrentLimitsConfigs()
                .withStatorCurrentLimit(60)
                .withStatorCurrentLimitEnable(true)
                .withSupplyCurrentLimitEnable(true);
    }

    public static class Escalator{
        public static int left = 12;
        public static int right = 11;
        public static Slot0Configs slot0Configs = new Slot0Configs()
                .withKP(1);
        public static double FF = 0.035;
        public static final TalonFXConfiguration configs = new TalonFXConfiguration()
                .withSlot0(slot0Configs)
                .withCurrentLimits(
                        new CurrentLimitsConfigs()
                                .withStatorCurrentLimit(60)
                                .withStatorCurrentLimitEnable(true)
                                .withSupplyCurrentLimitEnable(true)
                )
                .withFeedback(
                        new FeedbackConfigs()
                                .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor)
                );
    }
}
