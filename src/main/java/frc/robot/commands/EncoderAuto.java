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

  //making values we use later
  private double rightIntegral;
  private double leftIntegral;
  private double correction;
  private boolean isFinished;

  public EncoderAuto() {
    //making it require driveTrain
    //means two commands can't use driveTrain at the same time
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //making isFinished false when our command first runs
    this.isFinished = false;

    //setting encoder values to 0
    Robot.driveTrain.rl.setSelectedSensorPosition(0);
    Robot.driveTrain.ll.setSelectedSensorPosition(0);

    //resetting integral
    rightIntegral = 0;
    leftIntegral = 0;
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    /*
    getting our desired velocity from the requested motor power 
    and the constant that makes that value into the same scale that the currentVelocity values will
    come in at, which is encoder counts per 100ms
    */
    double rightDesiredVelocity = Constants.ENC_AUTO_MOTOR_POWER * 188;
    double leftDesiredVelocity = Constants.ENC_AUTO_MOTOR_POWER * 198;

    //getting our current velocities from the sensors
    double rightCurrentVelocity = Robot.driveTrain.getRightVelocity();
    double leftCurrentVelocity = Robot.driveTrain.getLeftVelocity();

    //taking the difference to find the error
    double rightError = rightDesiredVelocity - rightCurrentVelocity;
    double leftError = leftDesiredVelocity - leftCurrentVelocity;

    //getting our P value from the error
    double p1 = rightError * Constants.PROPORTIONAL_CONSTANT;
    double p2 = leftError * Constants.PROPORTIONAL_CONSTANT;

    //accumulating the integral
    rightIntegral += (rightError) * Constants.INTEGRAL_CONSTANT;
    leftIntegral += (leftError) * Constants.INTEGRAL_CONSTANT;

    //getting our correction value from the errors
    correction = (rightError - leftError) * Constants.CORRECTION_CONSTANT;

    //giving the robot motor powers using the P, I and correction values as adjusters
    Robot.driveTrain.makeRightGo((Constants.ENC_AUTO_MOTOR_POWER)+ p1 + rightIntegral + correction);
    Robot.driveTrain.makeLeftGo((Constants.ENC_AUTO_MOTOR_POWER) + p2 + leftIntegral - correction);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //making the command end
    if(Robot.driveTrain.rl.getSelectedSensorPosition() < Constants.ENC_AUTO_ENC_DISTANCE){
      //if the robot has gone the requested amount of encoder counts, 
      //the integrals and correction are set to 0 
      //and isFinished is set to true so that end() will run
      rightIntegral = 0;
      leftIntegral = 0;
      correction = 0;
      isFinished = true;
    }
    else{
      isFinished = false;
    }
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //stops the motors
    Robot.driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
