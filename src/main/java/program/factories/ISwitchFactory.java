package program.factories;

import program.models.switches.Switch;

public interface ISwitchFactory {
    Switch createSwitch(int id, String ip, String community, String name, int portCount, String template);
}
