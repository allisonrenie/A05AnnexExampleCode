/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderAuto extends Command {
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
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.driveTrain.makeRightGo(0.5);
    Robot.driveTrain.makeLeftGo(0.5);
  
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.driveTrain.rl.getSelectedSensorPosition() < 30){
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
    Robot.driveTrain.stopRight();
    Robot.driveTrain.stopLeft();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
