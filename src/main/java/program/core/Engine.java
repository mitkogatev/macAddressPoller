package program.core;

import program.core.managers.ISuperManager;
import program.io.Writer;
import program.models.switches.Switch;

public class Engine implements Runnable {
    private Writer writer;
    private ISuperManager superManager;

    public Engine(Writer writer, ISuperManager superManager) {
        this.writer = writer;
        this.superManager = superManager;

    }

    @Override
    public void run() {
        int swId=this.superManager.createSwitch("127.0.0.1","public","zyx1",12,"");
        Switch sw =this.superManager.findSwitchById(swId);
        this.writer.writeLine(this.superManager.walkMac(sw));
    }
}
