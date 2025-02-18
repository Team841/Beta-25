package com.team841.nemo;

import com.team841.nemo.drivetrain.Drivetrain;
import com.team841.nemo.drivetrain.Snapping;
import com.team841.nemo.escalator.Escalator;
import com.team841.nemo.escalator.Move;
import com.team841.nemo.shooter.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Control {

    private final Drivetrain drivetrain;
    private final Escalator escalator;
    private final Shooter shooter;

    public final SequentialCommandGroup snapScoreLeftL4;
    public final SequentialCommandGroup snapScoreLeftL3;
    public final SequentialCommandGroup snapScoreLeftL2;

    public final SequentialCommandGroup snapScoreRightL4;
    public final SequentialCommandGroup snapScoreRightL3;
    public final SequentialCommandGroup snapScoreRightL2;

    public final SequentialCommandGroup noSnapAutoScoreL4;
    public final SequentialCommandGroup noSnapAutoScoreL3;
    public final SequentialCommandGroup noSnapAutoScoreL2;

    public Control(Drivetrain drivetrain, Escalator escalator, Shooter shooter){
        this.drivetrain = drivetrain;
        this.escalator = escalator;
        this.shooter = shooter;

        this.snapScoreLeftL4 = new SequentialCommandGroup(new Snapping(drivetrain, true), new Move(escalator, Escalator.Position.L4), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
        this.snapScoreLeftL3 = new SequentialCommandGroup(new Snapping(drivetrain, true), new Move(escalator, Escalator.Position.L3), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
        this.snapScoreLeftL2 = new SequentialCommandGroup(new Snapping(drivetrain, true), new Move(escalator, Escalator.Position.L2), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));

        this.snapScoreRightL4 = new SequentialCommandGroup(new Snapping(drivetrain, false), new Move(escalator, Escalator.Position.L4), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
        this.snapScoreRightL3 = new SequentialCommandGroup(new Snapping(drivetrain, false), new Move(escalator, Escalator.Position.L3), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
        this.snapScoreRightL2 = new SequentialCommandGroup(new Snapping(drivetrain, false), new Move(escalator, Escalator.Position.L2), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));

        this.noSnapAutoScoreL4 = new SequentialCommandGroup(new Move(escalator, Escalator.Position.L4), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
        this.noSnapAutoScoreL3 = new SequentialCommandGroup(new Move(escalator, Escalator.Position.L3), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
        this.noSnapAutoScoreL2 = new SequentialCommandGroup(new Move(escalator, Escalator.Position.L2), new Shooter().shoot(), new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
    }
}
