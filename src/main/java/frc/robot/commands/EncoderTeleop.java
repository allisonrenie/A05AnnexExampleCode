/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class EncoderTeleop extends Command {
  
  private boolean isFinished;

  public EncoderTeleop() {
    super();
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    XboxController xbox = Robot.m_oi.getXbox();

    double right = (-xbox.getY(Hand.kRight));
    double left = (-xbox.getY(Hand.kLeft));

    //we want these to be the velocity, not just joystick position
    //mult by whatever number the velocity is compared to the joystick value
    //5 is not real number
    //left is 236 -- 188
    //right is 248 -- 198
    double rightDesiredVelocity = right * 188;
    double leftDesiredVelocity = left * 198;
     SmartDashboard.putString("DB/String 5", Double.toString(rightDesiredVelocity));
    SmartDashboard.putString("DB/String 6", Double.toString(leftDesiredVelocity));
    System.out.println("Right des" + rightDesiredVelocity);
    System.out.println("Left des" + leftDesiredVelocity);
     

    double rightCurrentVelocity = Robot.driveTrain.getRightVelocity();
    double leftCurrentVelocity = Robot.driveTrain.getLeftVelocity();
    
    double p1 = (rightDesiredVelocity - rightCurrentVelocity) * .005;
    double p2 = (leftDesiredVelocity - leftCurrentVelocity) * .005;

    //now we have the p in the pid loop. will prolly add in integral next.
    Robot.driveTrain.makeRightGo(right + p1);
    Robot.driveTrain.makeLeftGo(left + p2);
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
