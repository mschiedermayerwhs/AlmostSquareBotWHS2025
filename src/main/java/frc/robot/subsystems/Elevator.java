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

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.*;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Elevator extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private SparkMax leadElevatorMotor;
private SparkMax followerElevatorMotor;
private SparkClosedLoopController PIDcontroller;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    RelativeEncoder leadMotorEnc;
    RelativeEncoder followerMotorEnc;
    //private SparkClosedLoopController pid;
    //Encoder encoder;
    
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
            .outputRange(kMinOutput, kMaxOutput);
        leadElevatorMotor = new SparkMax(kElevatorDriveLeftPort, MotorType.kBrushless);
        leadElevatorMotor.configure(leftLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // RIGHT IS FOLLOWER
        SparkMaxConfig rightFollowInvertConfig = new SparkMaxConfig();
        rightFollowInvertConfig.inverted(true);
        rightFollowInvertConfig.follow(kElevatorDriveLeftPort);
        followerElevatorMotor = new SparkMax(kElevatorDriveRightPort, MotorType.kBrushless);
        // followerElevatorMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        leadMotorEnc = leadElevatorMotor.getEncoder();
        PIDcontroller = leadElevatorMotor.getClosedLoopController();
        followerMotorEnc = followerElevatorMotor.getEncoder();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    // One speed for two motors moving in tandem
    public void setElevatorMotor(double speed) {
        //leadElevatorMotor.set(speed); // the other motor should follow
    }

    public void goToLevel(int level) {
        //if levels are actually evenly spaced, we should eb able to directly translate an elevator index level to a distance
        // then setReference to set the new setpoint
        // requires tuned conversion factors
        switch(level) {
            case 0: PIDcontroller.setReference(1, ControlType.kPosition); // units?
            case 1: PIDcontroller.setReference(2, ControlType.kPosition); // units?
        }
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

