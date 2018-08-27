package program.core.managers;

import program.models.switches.Switch;

public class SuperManager implements ISuperManager {
    private IEntityManager entityManager;
    private ISnmpManager snmpManager;

    public SuperManager() {
        this.entityManager = new EntityManager();
        this.snmpManager = new SnmpManager();
    }

    @Override
    public int createSwitch( String ip, String community, String name, int portCount, String template) {
        return this.entityManager.createSwitch(ip, community, name, portCount, template);
    }

    @Override
    public String walkMac(Switch sw) {
        return this.snmpManager.walkMac(sw);
    }

    @Override
    public String walkPlain(String... params) {
        return this.snmpManager.walkPlain(params);
    }

    @Override
    public void addSwitch(Switch sw) {
        this.entityManager.addSwitch(sw);
    }

    @Override
    public void removeSwitch(int swId) {
        this.entityManager.removeSwitch(swId);
    }

    @Override
    public void updateSwitch(int swId, Switch sw) {
        this.entityManager.updateSwitch(swId, sw);
    }

    @Override
    public Switch findSwitchById(int swId) {
        return this.entityManager.findSwitchById(swId);
    }
}
