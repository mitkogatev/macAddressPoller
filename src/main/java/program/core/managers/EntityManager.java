package program.core.managers;

import program.constants.BaseConstants;
import program.entities.ISwitchEntity;
import program.factories.ISwitchFactory;
import program.factories.SwitchFactory;
import program.models.switches.Switch;

import java.util.HashMap;
import java.util.Map;

public class EntityManager implements IEntityManager,ISwitchEntity {
    private Map<Integer,Switch> switchDb;
    private int currentId;

    public EntityManager() {
        this.switchDb=new HashMap<>();
        this.currentId=BaseConstants.FIRST_ID;
    }

    @Override
    public int createSwitch(String ip, String community, String name, int portCount,String template) {
        ISwitchFactory switchFactory=new SwitchFactory();

        Switch sw=switchFactory.createSwitch(this.currentId++, ip,community, name,  portCount,template);
        this.addSwitch(sw);
        return sw.getId();
    }
    @Override
    public void addSwitch(Switch sw) {
        this.switchDb.put(sw.getId(),sw);
    }

    @Override
    public void removeSwitch(int swId) {
        this.switchDb.remove(swId);
    }

    @Override
    public void updateSwitch(int swId, Switch sw) {
        this.switchDb.put(swId,sw);
    }

    @Override
    public Switch findSwitchById(int swId) {
        return this.switchDb.get(swId);
    }
}
