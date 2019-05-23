/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class EncoderAuto extends Command {

  private double rightIntegral;
  private double leftIntegral;
  private double correction;
  private boolean isFinished;

  public EncoderAuto() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.isFinished = false;
    Robot.driveTrain.rl.setSelectedSensorPosition(0);
    Robot.driveTrain.ll.setSelectedSensorPosition(0);
    rightIntegral = 0;
    leftIntegral = 0;
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double rightDesiredVelocity = Constants.ENC_AUTO_MOTOR_POWER * 188;
    double leftDesiredVelocity = Constants.ENC_AUTO_MOTOR_POWER * 198;

    double rightCurrentVelocity = Robot.driveTrain.getRightVelocity();
    double leftCurrentVelocity = Robot.driveTrain.getLeftVelocity();

    double rightError = rightDesiredVelocity - rightCurrentVelocity;
    double leftError = leftDesiredVelocity - leftCurrentVelocity;

    double p1 = rightError * Constants.PROPORTIONAL_CONSTANT;
    double p2 = leftError * Constants.PROPORTIONAL_CONSTANT;

    rightIntegral += (rightError) * Constants.INTEGRAL_CONSTANT;
    leftIntegral += (leftError) * Constants.INTEGRAL_CONSTANT;

    correction = (rightError - leftError) * Constants.CORRECTION_CONSTANT;

    Robot.driveTrain.makeRightGo((Constants.ENC_AUTO_MOTOR_POWER)+ p1 + rightIntegral + correction);
    Robot.driveTrain.makeLeftGo((Constants.ENC_AUTO_MOTOR_POWER) + p2 + leftIntegral - correction);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.driveTrain.rl.getSelectedSensorPosition() < Constants.ENC_AUTO_ENC_DISTANCE){
      rightIntegral = 0;
      leftIntegral = 0;
      correction = 0;
      isFinished = false;
    }
    else{
      isFinished = true;
    }
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
