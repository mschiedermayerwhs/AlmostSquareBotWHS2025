
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.RobotContainer;
import frc.robot.subsystems.CoralChute;

public class AdvanceCoral extends Command {
    
    private final CoralChute m_coralChute;

    public AdvanceCoral(CoralChute subsystem) {
        m_coralChute = subsystem;
        addRequirements(m_coralChute);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    // TODO: Advancing coral: correct amount of backspin/speed?
    @Override
    public void execute() {
        if(RobotContainer.currentElevatorHeight == 0) {
            m_coralChute.setLeftOutputMotor(0.2);
            m_coralChute.setRightOutputMotor(0.25);
        } else {
            m_coralChute.setLeftOutputMotor(0.25);
            m_coralChute.setRightOutputMotor(0.25);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_coralChute.setLeftOutputMotor(0);
        m_coralChute.setRightOutputMotor(0);
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
