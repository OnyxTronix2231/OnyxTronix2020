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

  public WriteValuesToFile(Ventilator ventilator) {
    this.ventilator = ventilator;
  }

  @Override
  public void initialize() {
    try {
      Files.delete(Paths.get("/home/lvuser/VentilatorValues.csv"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    Path path = Paths.get("/home/lvuser/VentilatorValues.csv");

    try {
      this.writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
      this.printWriter = new PrintWriter(writer);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    printWriter.println("Stator Current" + "," + "Supply Current" + "," + "Encoder units per second" + "," + "RPM");
  }

  @Override
  public void execute() {
    printWriter.println(ventilator.getStatorCurrent() + "," + ventilator.getSupplyCurrent() + ","
        + ventilator.getEncoderUnitsPerSec() + "," + ventilator.getRpm());
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
