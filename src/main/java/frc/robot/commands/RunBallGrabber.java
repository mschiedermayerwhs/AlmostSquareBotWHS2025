
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeGrabber;

public class RunBallGrabber extends Command {

    private final AlgaeGrabber m_algaeGrabber;
    private boolean m_forward;

    public RunBallGrabber(AlgaeGrabber algaeGrabber, boolean forward) {
        m_algaeGrabber = algaeGrabber;
        addRequirements(m_algaeGrabber);

        m_forward = forward;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(m_forward) {
            m_algaeGrabber.setBallGrabberMotor(1.0); // TODO: Direction and speed of ball grabber motor.
        } else {
            m_algaeGrabber.setBallGrabberMotor(-1.0);
        }
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
        return false;
    }
}