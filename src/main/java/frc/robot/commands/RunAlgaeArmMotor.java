
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeGrabber;
import java.util.function.DoubleSupplier;

public class RunAlgaeArmMotor extends Command {

    private final AlgaeGrabber m_algaeGrabber;
    private double m_leftTrigger;
    private double m_rightTrigger;

    public RunAlgaeArmMotor(AlgaeGrabber algaeGrabber, double leftTrigger, double rightTrigger) {
        m_algaeGrabber = algaeGrabber;
        addRequirements(m_algaeGrabber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // NOTE: The AlgaeGrabber's Arm Motor is limited in its position. The AlgaeGrabber.setArmRotateMotor(speed) method *should* work with the encoders to impose limits on its position.
        // Here, the velocity will be +1.0 if the left trigger is down all the way, and -1.0 if the right trigger is down all the way.

        m_algaeGrabber.setArmRotateMotor(m_leftTrigger - m_rightTrigger);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_algaeGrabber.setArmRotateMotor(0);
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
