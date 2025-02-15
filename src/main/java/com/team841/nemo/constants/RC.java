package com.team841.nemo.constants;

import edu.wpi.first.wpilibj.DriverStation;

import java.util.function.Supplier;

public class RC {
    public static Supplier<Boolean> isRedAlliance =
            () -> {
                var alliance = DriverStation.getAlliance();
                return alliance.filter(value -> value == DriverStation.Alliance.Red).isPresent();
    };

    public static final RunType robotType = RunType.COMP;

    public enum RunType{
        SIM, // Simulation
        DEV, // Developer-tuning mode
        COMP, // Comp code, real robot code
        REPLAY
    }


}
