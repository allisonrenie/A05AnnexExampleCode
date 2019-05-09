/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class Calibrator extends Command {

  private boolean isFinished;

  public Calibrator() {
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


    //make MOTOR_POWER the power you wish to test
    Robot.driveTrain.go(Constants.MOTOR_POWER);

    //printing tested power
    SmartDashboard.putString("DB/String 1", Double.toString(Constants.MOTOR_POWER));
    //printing current velocities
    SmartDashboard.putString("DB/String 2", Double.toString(Robot.driveTrain.getRightVelocity()));
    SmartDashboard.putString("DB/String 3", Double.toString(Robot.driveTrain.getLeftVelocity()));
    //printing differences
    SmartDashboard.putString("DB/String 4", Double.toString(Constants.MOTOR_POWER - Robot.driveTrain.getRightVelocity()));
    SmartDashboard.putString("DB/String 5", Double.toString(Constants.MOTOR_POWER - Robot.driveTrain.getLeftVelocity()));
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
    //stops both motors at end
    Robot.driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
