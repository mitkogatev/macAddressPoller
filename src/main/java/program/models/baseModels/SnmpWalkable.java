package program.models.baseModels;

public interface SnmpWalkable {
    String pollMacs();
    String pollIfOperStatus();
    String pollIfAdminStatus();
}
