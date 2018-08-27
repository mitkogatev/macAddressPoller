package program.core.managers;

import program.constants.CustomSnmpConstants;
import program.models.switches.Switch;
import program.snmp.SnmpOidParser;
import program.snmp.SnmpWalk;

import java.io.IOException;

public class SnmpManager implements ISnmpManager {
    private SnmpWalk snmpWalk;
    private SnmpOidParser parser;

    public SnmpManager() {
        this.snmpWalk=new SnmpWalk();
        this.parser=new SnmpOidParser();
    }

    @Override
    public String walkMac(Switch sw) {

        if(CustomSnmpConstants.MSG_FAILED.equals(this.snmpWalk.setArgs(sw.getCommunity(), sw.getIp(), sw.getMacOid()))){
            return CustomSnmpConstants.MSG_INVALID_ARGS;
        }

        try {
            return this.parser.parseMac(this.snmpWalk.snmpWalkInit());
            //return this.parser.parsePlain(this.snmpWalk.snmpWalkInit());
            //
        } catch (IOException e) {
           return CustomSnmpConstants.MSG_TRANSPORT_FAILED;
        }catch (NullPointerException ex){
            return CustomSnmpConstants.MSG_INVALID_OID;
        }
    }





    @Override
    public String walkPlain(String... params) {
        // TODO: 22.8.2018 г.  
        return null;
    }
}
