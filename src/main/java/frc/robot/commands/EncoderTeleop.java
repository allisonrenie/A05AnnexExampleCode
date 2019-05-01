/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class EncoderTeleop extends Command {
  
  private boolean isFinished;
  double currentRate;

  //figure out ports!!!
  Encoder rightEncoder = new Encoder(RobotMap.rightEncoder, 2, false, Encoder.EncodingType.k4X);
  Encoder leftEncoder = new Encoder(RobotMap.leftEncoder, 2, false, Encoder.EncodingType.k4X);

  public EncoderTeleop() {
    super();
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;

    //configuring encoders
    rightEncoder.setMaxPeriod(.1);
    rightEncoder.setMinRate(10);
    rightEncoder.setDistancePerPulse(5);
    rightEncoder.setReverseDirection(true);
    rightEncoder.setSamplesToAverage(7);
    
    leftEncoder.setMaxPeriod(.1);
    leftEncoder.setMinRate(10);
    leftEncoder.setDistancePerPulse(5);
    leftEncoder.setReverseDirection(true);
    leftEncoder.setSamplesToAverage(7);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
