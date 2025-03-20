// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: InstantCommand.

package frc.robot.commands;
import static frc.robot.Constants.AlgaeGrabberConstants.kArmDownPos;
import static frc.robot.Constants.AlgaeGrabberConstants.kArmMidPos;
import static frc.robot.Constants.AlgaeGrabberConstants.kArmUpPos;
import static frc.robot.RobotContainer.currentAlgaeArmHeight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.AlgaeGrabber;
import frc.robot.Constants.AlgaeGrabberConstants;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class ChangeAlgaeArmLevel extends InstantCommand {

    private final AlgaeGrabber m_algaeGrabber;
    private boolean m_goingUp;

    public ChangeAlgaeArmLevel(boolean goingUp, AlgaeGrabber subsystem) {
        m_goingUp = goingUp;

        m_algaeGrabber = subsystem;
        addRequirements(m_algaeGrabber);
    }

    @Override
    public void execute() {
        if(m_goingUp) {
            if(currentAlgaeArmHeight == 0) {
                System.out.println("0 to 1");
                currentAlgaeArmHeight = 1;
                m_algaeGrabber.goToSetpoint(kArmMidPos);
            } else if(currentAlgaeArmHeight == 1) {
                System.out.println("1 to 2");
                currentAlgaeArmHeight = 2;
                m_algaeGrabber.goToSetpoint(kArmUpPos);
            }
        } else {
            if(currentAlgaeArmHeight == 1) {
                System.out.println("1 to 0");
                currentAlgaeArmHeight = 0;
                m_algaeGrabber.goToSetpoint(kArmDownPos);
            } else if(currentAlgaeArmHeight == 2) {
                System.out.println("2 to 1");
                currentAlgaeArmHeight = 1;
                m_algaeGrabber.goToSetpoint(kArmMidPos);
            }
        }
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
