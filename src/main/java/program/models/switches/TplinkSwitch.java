package program.models.switches;

public class TplinkSwitch extends AbstractSwitch {
    public TplinkSwitch(int id, String ip, String community, String name, int portCount) {
        super(id, ip, community, name, portCount);
    }

    @Override
    public String pollMacs() {
return "";
    }
}
