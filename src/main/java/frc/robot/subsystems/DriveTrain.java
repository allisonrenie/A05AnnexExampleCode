/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {

  //right motors
  WPI_TalonSRX rl = new WPI_TalonSRX(RobotMap.rl);
  WPI_TalonSRX rf1 = new WPI_TalonSRX(RobotMap.rf1);
  WPI_TalonSRX rf2 = new WPI_TalonSRX(RobotMap.rf2);

  //left motors
  WPI_TalonSRX ll = new WPI_TalonSRX(RobotMap.ll);
  WPI_TalonSRX lf1 = new WPI_TalonSRX(RobotMap.lf1);
  WPI_TalonSRX lf2 = new WPI_TalonSRX(RobotMap.lf2);

  public DriveTrain(){

    //configuring to factory default
    rl.configFactoryDefault();
    rf1.configFactoryDefault();
    rf2.configFactoryDefault();

    ll.configFactoryDefault();
    lf1.configFactoryDefault();
    lf2.configFactoryDefault();

    //make followers follow the leader
    rf1.follow(rl);
    rf2.follow(rl);

    lf1.follow(ll);
    lf2.follow(ll);

    //making sure it goes forward when we want it to
    rl.setInverted(true);
    ll.setInverted(false);
    rf1.setInverted(InvertType.FollowMaster);
    rf2.setInverted(InvertType.FollowMaster);
    lf1.setInverted(InvertType.FollowMaster);
    lf2.setInverted(InvertType.FollowMaster);

    //adding encoders
    rl.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    ll.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
