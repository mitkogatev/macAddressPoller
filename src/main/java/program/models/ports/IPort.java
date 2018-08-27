package program.models.ports;

import program.models.baseModels.Identifiable;
import program.models.baseModels.Nameable;

public interface IPort extends Identifiable,Nameable {
    String getPortNum();
}
