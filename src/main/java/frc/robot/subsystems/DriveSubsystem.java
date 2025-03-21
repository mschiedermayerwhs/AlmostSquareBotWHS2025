package frc.robot.subsystems;

//static import reduces verbosity of adding constants without this you would need to Write Constants.DriveConstants.kFrontLeftDrivePort
import static frc.robot.Constants.DriveConstants.*;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

public class DriveSubsystem extends SubsystemBase {
    private SparkMax frontLeftMotor;
    private SparkMax frontRightMotor;   
    private SparkMax backLeftMotor;
    private SparkMax backRightMotor;
    private DifferentialDrive m_differentialDrive;
    private double setpointLeft;
    private double setpointRight;

    // private SparkClosedLoopController PIDcontrollerLeft;
    // private SparkClosedLoopController PIDcontrollerRight;

    // May only need encoder for leaders
    RelativeEncoder frontLeftEnc;
    RelativeEncoder frontRightEnc;
    RelativeEncoder backLeftEnc;
    RelativeEncoder backRightEnc;

    /**
    * https://github.com/REVrobotics/REVLib-Examples/blob/main/Java/SPARK/Closed%20Loop%20Control/src/main/java/frc/robot/Robot.java
    * https://github.com/frcteam-108-Sigmacats/SigmaCode2025TestBot/blob/main/src/main/java/frc/robot/subsystems/SwerveModule.java
    */
    public DriveSubsystem() {       
        // FRONT LEFT
        SparkMaxConfig frontLeftConfig = new SparkMaxConfig();
        frontLeftConfig.inverted(true);
        frontLeftConfig.encoder.positionConversionFactor(kDriveEncoderConversionFactor);
        // frontLeftConfig.closedLoop
        //     .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        //     .p(kP)
        //     .i(kI)
        //     .d(kD)
        //     .outputRange(kMinOutput, kMaxOutput);
        frontLeftMotor = new SparkMax(kFrontLeftDrivePort, MotorType.kBrushless);
        frontLeftMotor.configure(frontLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        frontLeftEnc = frontLeftMotor.getEncoder();
        
        // BACK LEFT
        SparkMaxConfig backLeftConfig = new SparkMaxConfig();
        backLeftConfig.inverted(false);
        backLeftConfig.follow(frontLeftMotor);
        backLeftConfig.encoder.positionConversionFactor(kDriveEncoderConversionFactor);
        // backLeftConfig.closedLoop
        //     .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        //     .p(kP)
        //     .i(kI)
        //     .d(kD)
        //     .outputRange(kMinOutput, kMaxOutput);
        backLeftMotor = new SparkMax(kBackLeftDrivePort, MotorType.kBrushless);
        backLeftMotor.configure(backLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        backLeftEnc = backLeftMotor.getEncoder();

        // FRONT RIGHT
        SparkMaxConfig frontRightConfig = new SparkMaxConfig();
        frontRightConfig.inverted(false);
        frontRightConfig.encoder.positionConversionFactor(kDriveEncoderConversionFactor);
        // frontRightConfig.closedLoop
        //     .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        //     .p(kP)
        //     .i(kI)
        //     .d(kD);
        frontRightMotor = new SparkMax(kFrontRightDrivePort, MotorType.kBrushless);
        frontRightMotor.configure(frontRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        frontRightEnc = frontRightMotor.getEncoder();

        // BACK RIGHT
        SparkMaxConfig backRightConfig = new SparkMaxConfig();
        backRightConfig.inverted(true);
        backRightConfig.follow(frontRightMotor);
        backRightConfig.encoder.positionConversionFactor(kDriveEncoderConversionFactor);
        // invertedFollowFrontRightConfig.closedLoop
        //     .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        //     .p(kP)
        //     .i(kI)
        //     .d(kD)
        //     .outputRange(kMinOutput, kMaxOutput);
        backRightMotor = new SparkMax(kBackRigthDrivePort, MotorType.kBrushless);
        backRightMotor.configure(backRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        backRightEnc = backRightMotor.getEncoder();
 
        m_differentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

        // PIDcontrollerLeft = frontLeftMotor.getClosedLoopController();
        // PIDcontrollerRight = frontRightMotor.getClosedLoopController();
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_differentialDrive.tankDrive(leftSpeed*kMaxOutput, rightSpeed*kMaxOutput, true);
        //System.out.println("tank driving: " + leftSpeed + ", " + rightSpeed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putData(m_differentialDrive);
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Back Left Pos: ", getBackLeftPos());
        SmartDashboard.putNumber("Back Left Vel: ", getBackLeftVel());

        SmartDashboard.putNumber("Back Right Pos: ", getBackRightPos());
        SmartDashboard.putNumber("Back Right Vel: ", getBackRightVel());

        SmartDashboard.putNumber("Front Left Pos: ", getFrontLeftPos());
        SmartDashboard.putNumber("Front Left Vel: ", getFrontLeftVel());

        SmartDashboard.putNumber("Front Right Pos: ", getFrontRightPos());
        SmartDashboard.putNumber("Front Right Vel: ", getFrontRightVel());

        // This was kind of working.  However, it seemed to be going in the wrong direction
        // therefore, it never would reach the setpoint.  ALSO: the b button code was still firing off
        // the command to set the setpoint MANY times, so the setpoint kept getting re-updated...
       // PIDcontrollerLeft.setReference(setpointLeft, ControlType.kPosition);
       // PIDcontrollerRight.setReference(setpointRight, ControlType.kPosition);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    public void resetEncoders() {
        frontLeftEnc.setPosition(0);
        frontRightEnc.setPosition(0);
        backLeftEnc.setPosition(0);
        backRightEnc.setPosition(0);
    }

    public Command resetEncodersCommand() {
        return run(() -> resetEncoders()).withTimeout(0.25);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void goToSetpoint(double point) {
        double curPosLeft = frontLeftEnc.getPosition();
        double curPosRight = frontRightEnc.getPosition();
       
        // This causes issues with analog stick inputs.
        setpointLeft = curPosLeft + point;
        setpointRight = curPosRight + point;
        System.out.println("[" + Timer.getFPGATimestamp() + "] Left Motor Current Pos: " + curPosLeft + ". SetPoint: " + setpointLeft);
        System.out.println("Right Motor Current Pos: " + curPosRight + ". SetPoint: " + setpointRight);

        //setpoint = point;
        // System.out.println("setting setpoint...");
        //PIDcontrollerLeft.setReference(setpointLeft, ControlType.kPosition);
        //PIDcontrollerRight.setReference(setpointRight, ControlType.kPosition);
        // System.out.println("setpoint done");
    }

    public Command goToSetpointCommand(double point) {
        return run(() -> goToSetpoint(point));
    }

    //TODO: To control the robot during autonomous, we will need to be able to rotate by a certain amount. This will require experimentation.
    // we may be able to use differentialDrive.arcadeDrive  to set a specific rotation.
    public void goToRotate(double degrees) {
        double curPosLeft = frontLeftEnc.getPosition();
        double curPosRight = frontRightEnc.getPosition();

        setpointLeft = curPosLeft + 0;
        setpointRight = curPosRight + 0;

        System.out.println("[" + Timer.getFPGATimestamp() + "] Left Motor Current Pos: " + curPosLeft + ". SetPoint: " + setpointLeft);
        System.out.println("Right Motor Current Pos: " + curPosRight + ". SetPoint: " + setpointRight);
    }

    public Command goToRotateCommand(double degrees) {
        return run(() -> goToRotate(degrees));
    }

    public void setLeftMotor(double speed) {
        frontLeftMotor.set(speed);
    }

    public void setRightMotor(double speed) {
        frontRightMotor.set(speed);
    }

    public void stop() {
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
    }

    public RelativeEncoder getFrontLeftEncoder() {
        return frontLeftEnc;
    }

    public RelativeEncoder getFrontRightEncoder() {
        return frontRightEnc;
    }

    public RelativeEncoder getBackLeftEncoder() {
        return backLeftEnc;
    }

    public RelativeEncoder getBackRightEncoder() {
        return backRightEnc;
    }

    public double getFrontLeftPos() {
        return frontLeftEnc.getPosition();
    }

    public double getFrontRightPos() {
        return frontRightEnc.getPosition();
    }

    public double getBackLeftPos() {
        return backLeftEnc.getPosition();
    }

    public double getBackRightPos() {
        return backRightEnc.getPosition();
    }

    public double getFrontLeftVel() {
        return frontLeftEnc.getVelocity();
    }

    public double getFrontRightVel() {
        return frontRightEnc.getVelocity();
    }

    public double getBackLeftVel() {
        return backLeftEnc.getVelocity();
    }

    public double getBackRightVel() {
        return backRightEnc.getVelocity();
    }
}
