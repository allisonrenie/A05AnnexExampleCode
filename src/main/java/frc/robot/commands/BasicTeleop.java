/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BasicTeleop extends Command {

  //this is a basic tank drive that runs with the xbox controller

  public BasicTeleop() {
    super();
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //retrieving the xbox controller from our oi 
    XboxController xbox = Robot.m_oi.getXbox();

    //setting the values of the Y axises of both the joysticks equal to variables
    //may have to change to negative to make it work right
    double right = xbox.getY(Hand.kRight);
    double left = xbox.getY(Hand.kLeft);

    //making the robot move by making the left and right motor powers
    Robot.driveTrain.makeRightGo(right);
    Robot.driveTrain.makeLeftGo(left);
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
