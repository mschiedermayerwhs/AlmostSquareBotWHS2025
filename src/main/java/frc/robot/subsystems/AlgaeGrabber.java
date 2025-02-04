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

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class AlgaeGrabber extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private SparkMax ballGrabberMotor;
private SparkMax armRotateMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public AlgaeGrabber() {
        // TODO - Inverting algae grabber motors
        SparkMaxConfig notInvertedConfig = new SparkMaxConfig();
        notInvertedConfig.inverted(false);
        SparkMaxConfig invertedConfig = new SparkMaxConfig();
        invertedConfig.inverted(true);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

// ballGrabberMotor = new SparkMax(10, MotorType.kBrushless);
// ballGrabberMotor.configure(notInvertedConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

// armRotateMotor = new SparkMax(12, MotorType.kBrushless);
// armRotateMotor.configure(notInvertedConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


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
    public void setBallGrabberMotor(double speed) {
        //ballGrabberMotor.set(speed);
    }

    public void setArmRotateMotor(double speed) {
        //armRotateMotor.set(speed);
    }
}

