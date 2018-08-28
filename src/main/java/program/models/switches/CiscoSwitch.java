package program.models.switches;

public class CiscoSwitch extends AbstractSwitch {
    /*
    Cisco switches are using "@"+vlan appended to community to get mac address,
    so we need to loop over vlans.
    Possible methods:
        1. Create vlans field filled from outside and iterate over it.
        2. Every time getMac is called snmpwalk vlans and iterate over.
        3. Walk vlans upon creation and save them, this will need periodic poll to update changes in vlans.
     */
    public CiscoSwitch(int id, String ip, String community, String name, int portCount) {
        super(id, ip, community, name, portCount);
    }

}
