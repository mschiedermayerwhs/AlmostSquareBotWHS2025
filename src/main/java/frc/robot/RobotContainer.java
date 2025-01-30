// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.POVButton;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

  public static int currentElevatorHeight = 0;
  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  // The robot's subsystems
  public final AlgaeGrabber m_algaeGrabber = new AlgaeGrabber();
  public final CoralChute m_coralChute = new CoralChute();
  public final Elevator m_elevator = new Elevator();
  public final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // Joysticks
  private final XboxController xboxController = new XboxController(0);

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  private RobotContainer() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("AutonomousCommand", new AutonomousCommand());
    SmartDashboard.putData("TankDrive: stopped", new TankDrive(0, 0, m_driveSubsystem));
    SmartDashboard.putData("EmptyCommand", new EmptyCommand());

    // TODO Mr. Schiedermayer - We may want to put separate buttons for each height.
    // We need to figure out the int to pass to achieve those heights
    // SmartDashboard.putData("ChangeElevatorHeight", new ChangeElevatorHeight(
    // m_elevator ));
    SmartDashboard.putData("AdvanceCoral", new AdvanceCoralInstant(m_coralChute));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    m_driveSubsystem.setDefaultCommand(new TankDrive(xboxController.getLeftY(), xboxController.getRightY(), m_driveSubsystem));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    // m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ',
    // '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ',
    // '')} ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
    // Create some buttons
    
    /**
    final POVButton pOVButton = new POVButton(xboxController, 0, 0);
    pOVButton.onTrue(new EmptyCommand().withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton rightJoystickButton = new JoystickButton(xboxController,
        XboxController.Button.kRightStick.value);
    rightJoystickButton.onTrue(new EmptyCommand().withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton leftJoystickButton = new JoystickButton(xboxController,
        XboxController.Button.kLeftStick.value);
    leftJoystickButton.onTrue(new EmptyCommand().withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton rightBumper = new JoystickButton(xboxController, XboxController.Button.kRightBumper.value);
    rightBumper.onTrue(new EmptyCommand().withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton leftBumper = new JoystickButton(xboxController, XboxController.Button.kLeftBumper.value);
    leftBumper.onTrue(new EmptyCommand().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
    */

    final JoystickButton startButton = new JoystickButton(xboxController, XboxController.Button.kStart.value);
    startButton.whileTrue(new AdvanceCoral(m_coralChute).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    // NOTE: This is where the control would swap if the coral is being advanced via
    // a single press or a hold.
    // startButton.onTrue(new AdvanceCoralInstant( m_coralChute
    // ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));


    /* https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robotbuilder/writing-code/robotbuilder-drive-tank.html 
    A common use case is to have a joystick that should drive some actuators that are part of a subsystem. 
    The problem is that the joystick is created in the RobotContainer class and the motors to be controlled are in the subsystem. 
    The idea is to create a command that, when scheduled, reads input from the joystick and calls a method that is created on the subsystem that drives the motors. 
    */

    final JoystickButton backButton = new JoystickButton(xboxController, XboxController.Button.kBack.value);
    backButton.onTrue(new EmptyCommand().withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton yButton = new JoystickButton(xboxController, XboxController.Button.kY.value);
    yButton.onTrue(new ChangeElevatorHeight(2, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton xButton = new JoystickButton(xboxController, XboxController.Button.kX.value);
    xButton.onTrue(new ChangeElevatorHeight(1, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton aButton = new JoystickButton(xboxController, XboxController.Button.kA.value);
    aButton.onTrue(new ChangeElevatorHeight(0, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    //final JoystickButton bButton2 = new JoystickButton(xboxController, XboxController.Button.kB.value);
    //bButton2.onTrue(new EmptyCommand().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
  public XboxController getXboxController() {
    return xboxController;
  }

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }

}
