package program.models.switches;

import program.constants.BaseConstants;
import program.models.ports.IPort;
import program.models.ports.Port;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractSwitch implements Switch {
    private int id;
    private String ip;
    private String community;
    private String name;
    private List<IPort> ports;
    private Map<String,String> oidStrings;

    protected AbstractSwitch(int id, String ip, String community, String name, int portCount) {
        this.id=id;
        this.ip=ip;
        this.community=community;
        this.name=name;

        this.createPorts(portCount);

        this.oidStrings=new HashMap<>();
        this.oidStrings.put(BaseConstants.IFINDEX_OID_KEY,BaseConstants.IFINDEX_OID);
        this.oidStrings.put(BaseConstants.IFADMIN_OID_KEY,BaseConstants.IFADMIN_OID);
        this.oidStrings.put(BaseConstants.IFOPER_OID_KEY,BaseConstants.IFOPER_OID);
        //override if not generic
        this.oidStrings.put(BaseConstants.MAC_OID_KEY,BaseConstants.MAC_OID_GENERIC);
    }

    private void createPorts(int portCount) {
        this.ports=new LinkedList<>();
        for (int i = 1; i <= portCount; i++) {
            this.ports.add(new Port(i));
        }
    }

    @Override
    public String getMacOid(){
        return this.oidStrings.get(BaseConstants.MAC_OID_KEY);
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCommunity() {
        return this.community;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
