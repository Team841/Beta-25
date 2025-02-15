// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team841.nemo;

import com.ctre.phoenix6.SignalLogger;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import sun.misc.Signal;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public Robot() {
    m_robotContainer = new RobotContainer();
    SignalLogger.setPath("/media/sda1/");
    SignalLogger.start();

    SignalLogger.writeString("MetaData/ProjectName", "Nemo");
    SignalLogger.writeString("MetaData/MavenName", BuildConstants.MAVEN_NAME);
    SignalLogger.writeString("MetaData/BuildDate", BuildConstants.BUILD_DATE);
    SignalLogger.writeString("MetaData/GitSHA", BuildConstants.GIT_SHA);
    SignalLogger.writeString("MetaData/GitDate", BuildConstants.GIT_DATE);
    SignalLogger.writeString("MetaData/GitBranch", BuildConstants.GIT_BRANCH);


    switch (BuildConstants.DIRTY) {
      case 0:
        SignalLogger.writeString("MetaData/GitDirty", "All changes committed");
        break;
      case 1:
        SignalLogger.writeString("MetaData/GitDirty", "Uncomitted changes");
        break;
      default:
        SignalLogger.writeString("MetaData/GitDirty", "Unknown");
        break;
    }
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}
