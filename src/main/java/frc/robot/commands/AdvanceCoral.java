
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.RobotBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.CoralChute;
import frc.robot.Constants.CoralChuteConstants;
import frc.robot.Constants.CoralChuteConstants.*;

public class AdvanceCoral extends Command {
    
    private final CoralChute m_coralChute;
    private boolean m_fast;
    private double m_leftSpeed = 0;
    private double m_rightSpeed = 0;

    public AdvanceCoral(CoralChute subsystem) {
        m_coralChute = subsystem;
        m_fast = false;
        addRequirements(m_coralChute);
    }

    public AdvanceCoral(CoralChute subsystem, boolean fast) {
        m_coralChute = subsystem;
        m_fast = fast;
        addRequirements(m_coralChute);
    }

    public AdvanceCoral(CoralChute subsystem, double leftSpeed, double rightSpeed) {
        m_coralChute = subsystem;
        m_fast = false;
        m_leftSpeed = leftSpeed;
        m_rightSpeed = rightSpeed;
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
            m_coralChute.setLeftOutputMotor(CoralChuteConstants.kNormalLeftOutputSpeed);
            m_coralChute.setRightOutputMotor(CoralChuteConstants.kNormalRightOutputSpeed);
        } else if(m_fast) {
            m_coralChute.setLeftOutputMotor(CoralChuteConstants.kFastLeftOutputSpeed);
            m_coralChute.setRightOutputMotor(CoralChuteConstants.kFastRightOutputSpeed);
        } else if(RobotContainer.currentElevatorHeight == 3) {
            m_coralChute.setLeftOutputMotor(CoralChuteConstants.kMaxLeftOutputSpeed);
            m_coralChute.setRightOutputMotor(CoralChuteConstants.kMaxRightOutputSpeed);
        } else if(m_leftSpeed != 0 || m_rightSpeed != 0) {
            m_coralChute.setLeftOutputMotor(m_leftSpeed);
            m_coralChute.setRightOutputMotor(m_rightSpeed);
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
