package program;

import program.core.Engine;
import program.core.managers.ISuperManager;
import program.core.managers.SuperManager;
import program.io.ConsoleWriter;
import program.io.Writer;

public class Main {
    public static void main(String[] args) {
        Writer writer=new ConsoleWriter();
        ISuperManager superManager=new SuperManager();
        Engine engine=new Engine(writer,superManager);
        engine.run();
    }
}
