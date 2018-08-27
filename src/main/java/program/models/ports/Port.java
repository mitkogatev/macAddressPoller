package program.models.ports;

import program.constants.BaseConstants;

public class Port implements IPort {
    private int id;
    private String name;
    private int portNum;

    public Port() {
    }

    public Port(int portNum) {
        this.portNum = portNum;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPortNum() {
        return this.portNum + BaseConstants.LINE_IS_EMPTY;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
