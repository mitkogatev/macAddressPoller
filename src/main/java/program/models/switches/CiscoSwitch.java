package program.models.switches;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import program.constants.BaseConstants;
import program.constants.CustomSnmpConstants;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CiscoSwitch extends AbstractSwitch {
    /*
    Cisco switches are using "@"+vlan appended to community to get mac address,
    so we need to loop over vlans.
    Possible methods:
        1. Create vlans field filled from outside and iterate over it.
        2. Every time getMac is called snmpwalk vlans and iterate over.
        3. Walk vlans upon creation and save them, this will need periodic poll to update changes in vlans.
     */
    private Set<String> vlans;
    public CiscoSwitch(int id, String ip, String community, String name, int portCount) {
        super(id, ip, community, name, portCount);
        this.vlans=new HashSet<>();
    }
    private void updateVlans(){
        try {
            List<VariableBinding> vars=super.snmp.walk(BaseConstants.CISCO_VLAN_OID);
            for (VariableBinding var : vars) {
                OID resultOid = var.getOid();
                String vlan=resultOid.toString().substring(BaseConstants.CISCO_VLAN_OID.length()+BaseConstants.FIRST_ID);
                this.vlans.add(vlan);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String pollMacs() {
        StringBuilder result=new StringBuilder();
        this.updateVlans();
        for (String vlan : vlans) {
            String community=CustomSnmpConstants.PARAM_COMMUNITY+this.getCommunity()+BaseConstants.AT+vlan;
            super.snmp.setArgs(community,BaseConstants.MAC_OID_GENERIC);
            try {
                result.append(super.parser.parseMac(super.snmp.snmpWalkInit()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
