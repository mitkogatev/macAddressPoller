package program.entities;

import program.models.switches.Switch;

public interface ISwitchEntity {
    void addSwitch(Switch sw);
    void removeSwitch(int swId);
    void updateSwitch(int swId, Switch sw);
    Switch findSwitchById(int swId);

    // TODO: 27.8.2018 г.
    // use mapping table swId ->swName(otherwise query will be slow)
    //Switch findSwitchByName(String swName);
}
