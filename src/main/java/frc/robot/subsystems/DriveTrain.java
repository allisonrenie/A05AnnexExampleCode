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
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.BasicTeleop;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {

  //right motors
  WPI_TalonSRX rl = new WPI_TalonSRX(RobotMap.rl);
  WPI_VictorSPX rf1 = new WPI_VictorSPX(RobotMap.rf1);
  WPI_VictorSPX rf2 = new WPI_VictorSPX(RobotMap.rf2);

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
    //getselectedsensor
  }

  //method to stop motors
  public void stop(){
    rl.set(0);
    ll.set(0);
  }

  //method to stop just the right motors
  public void stopRight(){
    rl.set(0);
  }

  //method to stop just the left motors
  public void stopLeft(){
    ll.set(0);
  }

  //method to make right side go
  public void makeRightGo(double rightPower){
     
    rl.set(rightPower);
  }

  //method to make left side go
  public void makeLeftGo(double leftPower){

    ll.set(leftPower);
  }

  public int getRightEnc(){
    int rightEnc = rl.getSelectedSensorPosition();
    SmartDashboard.putString("DB/String 0", Integer.toString(rightEnc));
    return rightEnc;
  }
  
  public int getLeftEnc(){
    int leftEnc = ll.getSelectedSensorPosition();
    SmartDashboard.putString("DB/String 1", Integer.toString(leftEnc));
    return leftEnc;
  }

  @Override
  public void initDefaultCommand() {

    setDefaultCommand(new BasicTeleop());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
