package com.team841.nemo.escalator;

import edu.wpi.first.wpilibj2.command.Command;

public class Move extends Command {
  public Move(Escalator escalator, Escalator.Position position) {
    this.escalator = escalator;
    this.position = position;

    addRequirements(this.escalator);
    setName("Move");
  }

  Escalator escalator;
  Escalator.Position position;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    escalator.setPosition(position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.escalator.setPosition(position);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.escalator.isAtPosition(position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.escalator.stop();
  }
}
