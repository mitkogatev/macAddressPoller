package program.models.switches;

import org.snmp4j.smi.VariableBinding;
import program.constants.BaseConstants;

import java.io.IOException;
import java.util.List;

public class TplinkSwitch extends AbstractSwitch {
    private String sysId;

    public TplinkSwitch(int id, String ip, String community, String name, int portCount) {
        super(id, ip, community, name, portCount);
        this.setSysId();
    }

    private void setSysId() {
        String tmpResult = BaseConstants.LINE_IS_EMPTY;
        try {
            tmpResult = super.snmp.walk(BaseConstants.SYS_ID_OID).get(0).getVariable().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sysId = tmpResult;
    }

    @Override
    public String getMacOid() {
        return this.sysId + BaseConstants.TPLINK_MAC_PART_OID;
    }
}

