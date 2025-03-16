
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
            m_coralChute.setLeftOutputMotor(0.8);
            m_coralChute.setRightOutputMotor(1);
        } else {
            m_coralChute.setLeftOutputMotor(1);
            m_coralChute.setRightOutputMotor(1);
        }
        System.out.println("Advancing coral...");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_coralChute.setLeftOutputMotor(0);
        m_coralChute.setRightOutputMotor(0);
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
