package program.models.switches;

import program.models.baseModels.Identifiable;
import program.models.baseModels.Nameable;

public interface Switch extends Identifiable, Nameable {
    String getIp();
    String getCommunity();
    String getMacOid();
}
