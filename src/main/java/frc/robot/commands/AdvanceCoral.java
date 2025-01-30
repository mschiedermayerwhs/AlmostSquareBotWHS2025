
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.RobotContainer;
import frc.robot.subsystems.CoralChute;

/**
 * While button is being pressed, run motors.
 * Both this command and the instant one (advance coral until next stage) are in the code, but only one will be used in the final implementation.
 */
public class AdvanceCoral extends Command {
    
    private final CoralChute m_coralChute;

    public AdvanceCoral(CoralChute subsystem) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_coralChute = subsystem;
        addRequirements(m_coralChute);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // TODO - Speeds of output motors / amount of spin to apply
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
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
