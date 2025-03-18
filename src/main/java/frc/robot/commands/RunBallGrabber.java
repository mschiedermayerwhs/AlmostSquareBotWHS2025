
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeGrabber;

public class RunBallGrabber extends Command {

    private final AlgaeGrabber m_algaeGrabber;
    private double m_speed;

    public RunBallGrabber(AlgaeGrabber algaeGrabber, double speed) {
        m_algaeGrabber = algaeGrabber;
        addRequirements(m_algaeGrabber);

        m_speed = speed;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_algaeGrabber.setBallGrabberMotor(m_speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_algaeGrabber.setBallGrabberMotor(0);
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