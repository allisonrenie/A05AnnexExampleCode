/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {

    //here we will put constants that we want to be able to use elsewhere in our program
    //this provides a place to easily change numbers so they are labeled and clear

    // ENCODER TELEOP VALUES
    public static final double DEADBAND = 0.05;
    public static final double DEADBAND_DIVISION = .96;
    public static final double PROPORTIONAL_CONSTANT = .001;
    public static final double INTEGRAL_CONSTANT = .00075;
    public static final double CORRECTION_CONSTANT = .00075;

    //CALIBRATOR VALUES
    //change this to test different value's base inaccuracy
    public static final double CALI_MOTOR_POWER = 0.1;
    public static final double CALI_ENC_COUNT = 200;

    //ENCODER AUTO VALUES/ENCODER AUTO NO PID VALUES
    public static final double ENC_AUTO_MOTOR_POWER = 0.2;
    public static final double ENC_AUTO_ENC_DISTANCE = 200;

}
