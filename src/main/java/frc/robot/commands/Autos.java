// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */

  /*
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }
  */

  // public static Command driveWaitStop(DriveSubsystem driveSubsystem) {
  //   System.out.println("inside of driveWaitStop command");
  //   return Commands.sequence(
  //     new TankDrive(() -> 0.1, () -> 0.1, driveSubsystem), 
  //     Commands.waitSeconds(0.5), 
  //     new TankDrive(() -> 0.0, () -> 0.0, driveSubsystem)
  //   );
  // }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
