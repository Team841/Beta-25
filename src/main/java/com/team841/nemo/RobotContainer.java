// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team841.nemo;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.team841.nemo.constants.TunerConstants;
import com.team841.nemo.drivetrain.Drivetrain;
import com.team841.nemo.drivetrain.Snapping;
import com.team841.nemo.escalator.Escalator;
import com.team841.nemo.escalator.Move;
import com.team841.nemo.shooter.Shooter;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  private double MaxSpeed =
      TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
  private double MaxAngularRate =
      RotationsPerSecond.of(0.75)
          .in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final SwerveRequest.FieldCentric drive =
      new SwerveRequest.FieldCentric()
          .withDeadband(MaxSpeed * 0.1)
          .withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
          .withDriveRequestType(SwerveModule.DriveRequestType.OpenLoopVoltage);

  private final Telemetry logger = new Telemetry(MaxSpeed);

  private final CommandXboxController joystick = new CommandXboxController(0);

  public final Drivetrain drivetrain = TunerConstants.createDrivetrain();
  public final Escalator escalator = new Escalator();
  public final Shooter shooter = new Shooter();
  public final Control control;

  public RobotContainer() {
    control = new Control(drivetrain, escalator, shooter);
    configureBindings();
  }

  private void configureBindings() {

    drivetrain.setDefaultCommand(
        // Drivetrain will execute this command periodically
        drivetrain.applyRequest(
            () ->
                drive
                    .withVelocityX(
                        joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(
                        joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(
                        -joystick.getRightX()
                            * MaxAngularRate) // Drive counterclockwise with negative X (left)
            ));

    //        // Run SysId routines when holding back/start and X/Y.
    //        // Note that each routine should be run exactly once in a single log.
    //
    // joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
    //
    // joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
    //
    // joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
    //
    // joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

    // reset the field-centric heading on left bumper press
    joystick.start().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

    joystick.leftBumper().and(joystick.leftTrigger()).whileTrue(control.snapScoreLeftL3);
    joystick.leftBumper().and(joystick.rightTrigger()).whileTrue(control.snapScoreLeftL4);
    joystick.rightBumper().and(joystick.leftTrigger()).whileTrue(control.snapScoreRightL3);
    joystick.rightBumper().and(joystick.rightTrigger()).whileTrue(control.snapScoreRightL4);
    joystick.leftBumper().and(joystick.y()).whileTrue(control.snapScoreLeftL2);
    joystick.rightBumper().and(joystick.y()).whileTrue(control.snapScoreRightL2);

    joystick.leftBumper().onFalse(new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));
    joystick.rightBumper().onFalse(new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));

    joystick.b().and(joystick.leftTrigger()).whileTrue(control.noSnapAutoScoreL3);
    joystick.b().and(joystick.rightTrigger()).whileTrue(control.noSnapAutoScoreL4);
    joystick.b().and(joystick.y()).whileTrue(control.noSnapAutoScoreL2);

    joystick.b().onFalse(new InstantCommand(() -> this.escalator.setPosition(Escalator.Position.Home)));

    // l3: left trigger
    // l4: right trigger
    // l2: y
    // right bumper right align
    // left bumper left align

    drivetrain.registerTelemetry(logger::telemeterize);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
