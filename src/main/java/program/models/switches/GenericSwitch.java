package program.models.switches;

import program.constants.BaseConstants;

import java.io.IOException;

public class GenericSwitch extends AbstractSwitch {
    public GenericSwitch(int id, String ip, String community, String name, int portCount) {
        super(id, ip, community, name, portCount);
    }

    @Override
    public String pollMacs() {
        try {
            return super.parser.parseMac(super.snmp.walk(super.oidStrings.get(BaseConstants.MAC_OID_KEY)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BaseConstants.LINE_IS_EMPTY;
    }
}
