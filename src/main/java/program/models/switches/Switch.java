package program.models.switches;

import program.models.baseModels.Identifiable;
import program.models.baseModels.Nameable;
import program.models.baseModels.SnmpWalkable;

public interface Switch extends Identifiable, Nameable, SnmpWalkable {
    String getIp();
    String getCommunity();
    String getMacOid();
}
