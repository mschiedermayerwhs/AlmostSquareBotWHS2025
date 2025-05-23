// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

/**
 *
 */
public class TankDrive extends Command {

    private final DriveSubsystem m_driveSubsystem;
    private DoubleSupplier m_leftSpeed;
    private DoubleSupplier m_rightSpeed;
 
    public TankDrive(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed, DriveSubsystem subsystem) {
  
        m_leftSpeed = leftSpeed;
        m_rightSpeed = rightSpeed;
 
        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Speed limited depending on height; if elevator is at L2/3/4, the joysticks are half as effective
        if(RobotContainer.currentElevatorHeight >= 1) {
            m_driveSubsystem.tankDrive(m_leftSpeed.getAsDouble() * 0.5, m_rightSpeed.getAsDouble() * 0.5); // TODO: Is speed limited sufficiently?
        } else {            
            m_driveSubsystem.tankDrive(m_leftSpeed.getAsDouble(), m_rightSpeed.getAsDouble());
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.tankDrive(0.0, 0.0);
        System.out.println("stopped");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {       
        return false;    
    }
}
