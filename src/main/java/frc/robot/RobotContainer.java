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
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

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

  // The robot's subsystems
  public final AlgaeGrabber m_algaeGrabber = new AlgaeGrabber();
  public final CoralChute m_coralChute = new CoralChute();
  public final Elevator m_elevator = new Elevator();
  public final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  public static SlewRateLimiter leftLimit = new SlewRateLimiter(0.9);
  public static SlewRateLimiter rightLimit = new SlewRateLimiter(0.9);

  // Joysticks
  private final XboxController xboxController = new XboxController(0);

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  private RobotContainer() {
    // Smartdashboard Subsystems

    // SmartDashboard Buttons
    SmartDashboard.putData("AutonomousCommand", new AutonomousCommand(m_algaeGrabber, m_coralChute, m_elevator, m_driveSubsystem));
    //SmartDashboard.putData("TankDrive: stopped", new TankDrive(() -> 0.0, () -> 0.0, m_driveSubsystem));
    SmartDashboard.putData("Reset Drive Encoders", m_driveSubsystem.resetEncodersCommand());
    SmartDashboard.putData("Reset Elevator Encoders", m_elevator.resetEncodersCommand());

    // We need to figure out the value to pass to achieve those heights
    // SmartDashboard.putData("ChangeElevatorHeight", new ChangeElevatorHeight(
    // m_elevator ));

    // Configure the button bindings
    configureButtonBindings();



    // Configure default commands
    m_driveSubsystem.setDefaultCommand(new TankDrive(() -> leftLimit.calculate(getLeftY()), () -> rightLimit.calculate(getRightY()), m_driveSubsystem));
    //m_algaeGrabber.setDefaultCommand(new RunAlgaeArmMotor(m_algaeGrabber, () -> getLeftTrigger(), () -> getRightTrigger()));

    // Configure autonomous sendable chooser
    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand(m_algaeGrabber, m_coralChute, m_elevator, m_driveSubsystem));
    m_chooser.addOption("Score coral", new AutonomousCommand(m_algaeGrabber, m_coralChute, m_elevator, m_driveSubsystem,1));
    m_chooser.addOption("Leave starting line", new AutonomousCommand(m_algaeGrabber, m_coralChute, m_elevator, m_driveSubsystem, 2));
    // m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ',
    // '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ',
    // '')} ));
    
    m_driveSubsystem.resetEncoders();

    SmartDashboard.putData("Auto Mode", m_chooser);

    CommandScheduler.getInstance()
        .onCommandInitialize(
            command ->
                Shuffleboard.addEventMarker(
                    "Command initialized", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandExecute(
            command ->
                Shuffleboard.addEventMarker(
                    "Command executed", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandFinish(
            command ->
                Shuffleboard.addEventMarker(
                    "Command finished", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandInterrupt(
            command ->
                Shuffleboard.addEventMarker(
                    "Command interrupted", command.getName(), EventImportance.kNormal));
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
    // Some old button bindings to drive a preset amount of time:
    //leftBumper.onTrue( ( new TankDrive( () -> 0.2, () -> 0.2, m_driveSubsystem ).withInterruptBehavior(InterruptionBehavior.kCancelSelf) ).withTimeout(3) );
    //bButton2.onTrue( m_driveSubsystem.goToSetpointCommand(100).withInterruptBehavior(InterruptionBehavior.kCancelSelf) );     

    /* https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robotbuilder/writing-code/robotbuilder-drive-tank.html 
    A common use case is to have a joystick that should drive some actuators that are part of a subsystem. 
    The problem is that the joystick is created in the RobotContainer class and the motors to be controlled are in the subsystem. 
    The idea is to create a command that, when scheduled, reads input from the joystick and calls a method that is created on the subsystem that drives the motors. 
    */

    // ALGAE GRABBER: Running the ball-grabber motors in one direction with the leftBumper and another with the rightBumper (speeds are preset)
    final JoystickButton leftBumper = new JoystickButton(xboxController, XboxController.Button.kLeftBumper.value);
    //leftBumper.whileTrue(new RunBallGrabber(m_algaeGrabber, true).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
    
    final JoystickButton rightBumper = new JoystickButton(xboxController, XboxController.Button.kRightBumper.value);
    //rightBumper.whileTrue(new RunBallGrabber(m_algaeGrabber, false).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    // ELEVATOR: Pressing an AXYB button moves the elevator to level 0/1/2/3
    final JoystickButton aButton = new JoystickButton(xboxController, XboxController.Button.kA.value);
    aButton.onTrue(new ChangeElevatorLevel(0, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton xButton = new JoystickButton(xboxController, XboxController.Button.kX.value);
    xButton.onTrue(new ChangeElevatorLevel(1, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton yButton = new JoystickButton(xboxController, XboxController.Button.kY.value);
    yButton.onTrue(new ChangeElevatorLevel(2, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    final JoystickButton bButton2 = new JoystickButton(xboxController, XboxController.Button.kB.value);
    bButton2.onTrue(new ChangeElevatorLevel(3, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    // CORAL CHUTE: Holding down the START button will advance the coral, with a backspin depending on the elevator height.
    final JoystickButton startButton = new JoystickButton(xboxController, XboxController.Button.kStart.value);
    //startButton.whileTrue(new AdvanceCoral(m_coralChute).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

    // ELEVATOR TESTING: Slowly run the elevator motor to set up encoders. (switch direction if necessary)
    final JoystickButton backButton = new JoystickButton(xboxController, XboxController.Button.kBack.value);
    backButton.whileTrue(new RunElevator(() -> 0.01, m_elevator));

    // DRIVE TESTING: Use the d-pad to control the robot very slowly, in order to set encoders.
    final POVButton upLeftPOVButton = new POVButton(xboxController, 315);
    upLeftPOVButton.whileTrue(new TankDrive(() -> 0.01, () -> 0, m_driveSubsystem));

    final POVButton upRightPOVButton = new POVButton(xboxController, 45);
    upRightPOVButton.whileTrue(new TankDrive(() -> 0, () -> 0.01, m_driveSubsystem));

    final POVButton downLeftPOVButton = new POVButton(xboxController, 225);
    downLeftPOVButton.whileTrue(new TankDrive(() -> -0.01, () -> 0, m_driveSubsystem));

    final POVButton downRightPOVButton = new POVButton(xboxController, 135);
    downRightPOVButton.whileTrue(new TankDrive(() -> 0, () -> -0.01, m_driveSubsystem));
  }


  public XboxController getXboxController() {
    return xboxController;
  }

  public double getLeftY() {
    return xboxController.getLeftY();
  }

  public double getRightY() {
    return xboxController.getRightY();
  }

  public double getLeftTrigger() {
    return xboxController.getLeftTriggerAxis();
  }

  public double getRightTrigger() {
    return xboxController.getRightTriggerAxis();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    System.out.println("getting autonomous command");
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }

}
