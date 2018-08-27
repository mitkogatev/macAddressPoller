package program.models.macs;

import program.models.baseModels.Identifiable;

public interface IMacAddress extends Identifiable {
    String getValue();
    int getPortId();
    int getUserId();
    String getLastSeen();
    String getDateCreated();
}
