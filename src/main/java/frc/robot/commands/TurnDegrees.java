/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnDegrees extends Command {

  //command that turns the robot a certain number of degrees at a certain motor power in the... 
  //...direction of your choice

  //creating the values we'll pass in t=so we can use them here
  private boolean direction;
  private double degrees;
  private double motorPower;
  private boolean isFinished;

  public TurnDegrees(boolean direction, double degrees, double motorPower) {
    requires(Robot.driveTrain);
    this.direction = direction;
    //true = right
    //false = left

    this.degrees = degrees;
    this.motorPower = motorPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //if we want to turn right, we will
    if(direction = true){
      Robot.driveTrain.makeLeftGo(motorPower);
      Robot.driveTrain.makeRightGo(-motorPower);

    } 
    //if we want to turn left, we will
    else if(direction = false){
      Robot.driveTrain.makeLeftGo(-motorPower);
      Robot.driveTrain.makeRightGo(motorPower);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //note: the Yaw on this gyro goes from -180 to 180.

    //when we are turning right and exceed the desired degrees, the command ends
    if(direction = true && Robot.getAHRS().getYaw() >= degrees){
      isFinished = true;
    }
    //when we are turning left and get below the desired degrees, the command ends
    else if(direction = false && Robot.getAHRS().getYaw() <= degrees){
      isFinished = true;
    }
    //if these conditions are not met, the command continues
    else{
      isFinished = false;
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
