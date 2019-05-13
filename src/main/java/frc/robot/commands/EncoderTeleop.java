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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class EncoderTeleop extends Command {
  
  double rightIntegral;
  double leftIntegral;
  double useRight;
  double useLeft;
  
  private boolean isFinished;

  public EncoderTeleop() {
    super();
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;

    //resetting my integral when the program starts
    rightIntegral = 0;
    leftIntegral = 0;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //get joystick
    XboxController xbox = Robot.m_oi.getXbox();

    //getting the position of the joysticks on the y axis
    //this is a value from -1 to 1
    //cubed 
    double right = -((-xbox.getY(Hand.kRight)) * (-xbox.getY(Hand.kRight)) * (-xbox.getY(Hand.kRight)));
    double left = -((-xbox.getY(Hand.kLeft)) * (-xbox.getY(Hand.kLeft)) * (-xbox.getY(Hand.kLeft)));

    //adding right deadband
    if(right < Constants.DEADBAND){
      useRight = 0;
    }
    else{
      useRight = (right - Constants.DEADBAND) / Constants.DEADBAND_DIVISION;
    }

    if(right < .4 && right > - Constants.DEADBAND){
      useRight = 0;
    }
    else{
      useRight = (right + Constants.DEADBAND) / -Constants.DEADBAND_DIVISION;
    }

    //adding left deadband
    if(left < Constants.DEADBAND){
      useLeft = 0;
    }
    else{
      useLeft = (left - Constants.DEADBAND) / Constants.DEADBAND_DIVISION;
    }

    if(left < Constants.DEADBAND && left > -Constants.DEADBAND){
      useLeft = 0;
    }
    else{
      useLeft = (left + Constants.DEADBAND) / -Constants.DEADBAND_DIVISION;
    }

    //this is the velocity determined by the joystick input. 
    //188 and 198 are close to the robot's max speed.
    double rightDesiredVelocity = useRight * 188;
    double leftDesiredVelocity = useLeft * 198;

    //printing stuff to dashboard
    SmartDashboard.putString("DB/String 5", Double.toString(rightDesiredVelocity));
    SmartDashboard.putString("DB/String 6", Double.toString(leftDesiredVelocity));
    System.out.println("Right des" + rightDesiredVelocity);
    System.out.println("Left des" + leftDesiredVelocity);
     
    //getting current velocity from our motorcontrollers
    //in encoder counts per 100ms
    double rightCurrentVelocity = Robot.driveTrain.getRightVelocity();
    double leftCurrentVelocity = Robot.driveTrain.getLeftVelocity();
    
    //getting errors
    double rightError = rightDesiredVelocity - rightCurrentVelocity;
    double leftError = leftDesiredVelocity - leftCurrentVelocity;

    //proportional
    double p1 = rightError * Constants.PROPORTIONAL_CONSTANT;
    double p2 = leftError * Constants.PROPORTIONAL_CONSTANT;

    //integral
    //adds right and left errors to their respective integrals every time around
    rightIntegral += (rightError) * Constants.INTEGRAL_CONSTANT;
    leftIntegral += (leftError) * Constants.INTEGRAL_CONSTANT;

    //printing integral to dashboard
    SmartDashboard.putString("DB/String 7", Double.toString(rightIntegral));
    SmartDashboard.putString("DB/String 8", Double.toString(leftIntegral));

    //if one side has more error than the other
    //more power added to more error side, power taken from less error side
    double correction = (rightError - leftError) * Constants.CORRECTION_CONSTANT;

    //giving the power to the motors
    //have basic joystick position power, plus p, i, and side-based correction
    //Robot.driveTrain.makeRightGo(((right) * (right) * (right)) + p1 + rightIntegral + correction);
    //Robot.driveTrain.makeLeftGo(((left) * (left) * (left)) + p2 + leftIntegral - correction);
    Robot.driveTrain.makeRightGo((useRight)+ p1 + rightIntegral + correction);
    Robot.driveTrain.makeLeftGo((useLeft) + p2 + leftIntegral - correction);
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
