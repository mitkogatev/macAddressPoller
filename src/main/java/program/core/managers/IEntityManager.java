package program.core.managers;

import program.entities.ISwitchEntity;

public interface IEntityManager extends ISwitchEntity {
    int createSwitch(String ip, String community, String name, int portCount, String template);
}
