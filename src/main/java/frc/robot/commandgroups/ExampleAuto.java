/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.EncoderAuto;
import frc.robot.commands.TurnDegrees;

public class ExampleAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ExampleAuto() {

    //this simple autonomous moves the robot in a square
    //you can run command groups from button in the OI just like commands

    //you can also put command groups inside of other command groups
    //you call them the same way:
    //addSequential(new CommandGroup());
    addSequential(new EncoderAuto(300, 0.2));
    addSequential(new TurnDegrees(true, 90, 0.2));

    addSequential(new EncoderAuto(300, 0.2));
    addSequential(new TurnDegrees(true, 90, 0.2));

    addSequential(new EncoderAuto(300, 0.2));
    addSequential(new TurnDegrees(true, 90, 0.2));

    addSequential(new EncoderAuto(300, 0.2));
    addSequential(new TurnDegrees(true, 90, 0.2));
    


    //this is the directions provided in the template. i'll leave them here. 
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
