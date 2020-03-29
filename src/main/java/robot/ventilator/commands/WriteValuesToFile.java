package robot.ventilator.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ventilator.Ventilator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteValuesToFile extends CommandBase {

  private final Ventilator ventilator;
  private PrintWriter printWriter;
  private BufferedWriter writer;

  public WriteValuesToFile(Ventilator ventilator){
    this.ventilator = ventilator;
  }

  @Override
  public void initialize() {
    Path path = Paths.get("/home/lvuser/VentilatorValues.csv");
    try {
      this.writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
      this.printWriter = new PrintWriter(writer);
    } catch(IOException ex) {
      ex.printStackTrace();
    }
    printWriter.println("Ampere" + "," + "ClosedLoopError" + "," + "RPM");
  }

  @Override
  public void execute() {
    printWriter.println(ventilator.getAmp() + "," + ventilator.getClosedLoopError() + "," + ventilator.getRpm());
  }

  @Override
  public void end(boolean interrupted) {
    printWriter.close();
    try {
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
