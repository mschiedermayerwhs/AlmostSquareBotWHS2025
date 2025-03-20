
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeGrabber;
import java.util.function.DoubleSupplier;

public class RunBallGrabber extends Command {

    private final AlgaeGrabber m_algaeGrabber;
    private DoubleSupplier m_speed;

    public RunBallGrabber(AlgaeGrabber algaeGrabber, DoubleSupplier speed) {
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
        m_algaeGrabber.setBallGrabberMotor(m_speed.getAsDouble());
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