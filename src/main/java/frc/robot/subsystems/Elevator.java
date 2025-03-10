// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import static frc.robot.Constants.DriveConstants.kMaxOutput;
import static frc.robot.Constants.ElevatorConstants.*;
import static frc.robot.RobotContainer.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;

public class Elevator extends SubsystemBase {
    private SparkMax leadElevatorMotor;
    private SparkMax followerElevatorMotor;
    private SparkClosedLoopController PIDcontroller;

    RelativeEncoder leadMotorEnc;
    RelativeEncoder followerMotorEnc;
    
    /**
    *https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/differential-drive-odometry.html
    * https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/differential-drive-kinematics.html
    * https://docs.wpilib.org/en/stable/docs/software/commandbased/pid-subsystems-commands.html
    * https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Velocity%20Closed%20Loop%20Control/src/main/java/frc/robot/Robot.java#L54
    * https://docs.revrobotics.com/revlib/spark/closed-loop

    I think this is a better option as it is meant to work well with command based.  I think we need to use 
    wpilib Encoder instead fo the rev ones though..

    https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/pidcontroller.html
    https://docs.wpilib.org/en/stable/docs/software/commandbased/pid-subsystems-commands.html#pid-control-in-command-based
    */
    public Elevator() {
        // LEFT IS LEAD
        SparkMaxConfig leftLeadConfig = new SparkMaxConfig();
        leftLeadConfig.inverted(false);
        leftLeadConfig.closedLoop  // only need PID on leader
            .p(kP)
            .i(kI)
            .d(kD)
            .outputRange(kMinOutput, kMaxOutput)
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder);
        leftLeadConfig.encoder.positionConversionFactor(kElevatorEncoderConversionFactor);
        leftLeadConfig.encoder.velocityConversionFactor(kElevatorEncoderConversionFactor);
        leadElevatorMotor = new SparkMax(kElevatorDriveLeftPort, MotorType.kBrushless);
        leadElevatorMotor.configure(leftLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // RIGHT IS FOLLOWER
        SparkMaxConfig rightFollowInvertConfig = new SparkMaxConfig();
        rightFollowInvertConfig.follow(kElevatorDriveLeftPort, true);
        rightFollowInvertConfig.inverted(true);
        rightFollowInvertConfig.encoder.positionConversionFactor(kElevatorEncoderConversionFactor);
        rightFollowInvertConfig.encoder.velocityConversionFactor(kElevatorEncoderConversionFactor);
        followerElevatorMotor = new SparkMax(kElevatorDriveRightPort, MotorType.kBrushless);
        followerElevatorMotor.configure(rightFollowInvertConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        leadMotorEnc = leadElevatorMotor.getEncoder();
        PIDcontroller = leadElevatorMotor.getClosedLoopController();
        followerMotorEnc = followerElevatorMotor.getEncoder();

        leadMotorEnc.setPosition(0);
        followerMotorEnc.setPosition(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Elev Leader Pos", getLeadPosition());
        SmartDashboard.putNumber("Elev Follower Pos", getFollowerPosition());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    // One speed for two motors moving in tandem
    public void setElevatorMotor(double speed) {
        leadElevatorMotor.set(speed); // the other motor should follow
    }

    // My biggest concern is making sure this method is only executed one time for a button press
    public void goToLevel(int level) {
        // if levels are actually evenly spaced, we should be able to directly translate an elevator index level to a distance
        // then setReference to set the new setpoint
        // requires tuned conversion factors for the encoder config, and also use the factors here to convert
        switch(level) {
            case 0: PIDcontroller.setReference(lvl0Height, ControlType.kPosition); break; // TODO: Determine necessary elevator heights, reset encoders as necessary.
            case 1: PIDcontroller.setReference(lvl1Height, ControlType.kPosition); break;
            case 2: PIDcontroller.setReference(lvl2Height, ControlType.kPosition); break;
            case 3: PIDcontroller.setReference(lvl3Height, ControlType.kPosition); break;
        }
        currentElevatorHeight = level;
        System.out.println("Going to level " + level);
    }

    public void resetEncoders() {
        leadMotorEnc.setPosition(0);
        followerMotorEnc.setPosition(0);
        PIDcontroller.setReference(0, ControlType.kPosition);
    }

    public Command resetEncodersCommand() {
        return runOnce(() -> resetEncoders()).withTimeout(0.25);
    }

    public RelativeEncoder getLeadEncoder() {
        return leadMotorEnc;
    }

    public RelativeEncoder getFollowerEncoder() {
        return followerMotorEnc;
    }

    public double getLeadPosition() {
        return getLeadEncoder().getPosition();
    }

    public double getFollowerPosition() {
        return getFollowerEncoder().getPosition();
    }

    public double getLeadVelocity() {
        return getLeadEncoder().getVelocity();
    }

    public double getFollowerVelocity() {
        return getFollowerEncoder().getVelocity();
    }
}

