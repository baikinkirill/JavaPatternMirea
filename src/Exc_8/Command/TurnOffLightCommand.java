package Exc_8.Command;

public class TurnOffLightCommand implements Command {
    private final Light theLight;

    public TurnOffLightCommand(Light light) {
        this.theLight = light;
    }

    public void execute() {
        theLight.turnOff();
    }
}
