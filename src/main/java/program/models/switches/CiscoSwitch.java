package program.models.switches;

public class CiscoSwitch extends AbstractSwitch {
    public CiscoSwitch(int id, String ip, String community, String name, int portCount) {
        super(id, ip, community, name, portCount);
    }
}
