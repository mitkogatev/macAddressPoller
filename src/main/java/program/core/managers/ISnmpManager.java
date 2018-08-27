package program.core.managers;

import program.models.switches.Switch;

public interface ISnmpManager {
    String walkMac(Switch sw);
    String walkPlain(String... params);
}
