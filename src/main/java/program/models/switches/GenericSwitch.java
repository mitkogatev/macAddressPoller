package program.models.switches;

import program.constants.BaseConstants;

import java.io.IOException;

public class GenericSwitch extends AbstractSwitch {
    public GenericSwitch(int id, String ip, String community, String name, int portCount) {
        super(id, ip, community, name, portCount);
    }
}
