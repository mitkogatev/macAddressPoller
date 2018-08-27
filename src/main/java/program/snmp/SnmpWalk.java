package program.snmp;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
import program.constants.CustomSnmpConstants;
import program.helpers.Validator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SnmpWalk {
    private String targetAddr;
    private String oidStr;
    private String commStr;
    private int snmpVersion;
    private String snmpPortNum;

    //default contsructor
    public SnmpWalk() {
        this.targetAddr = null;
        this.oidStr = null;
        this.commStr = "public";
        this.snmpVersion = SnmpConstants.version2c;
        this.snmpPortNum = "161";
    }

    //constructor with params
    public SnmpWalk(String... params) {
        this();
        this.setArgs(params);
    }

    //set params and validate
    public String setArgs(String... args) {

        /*
         * needed oid and ip
         */
        if (args.length < 2) {
            return CustomSnmpConstants.FEW_ARGS;
        }
        /*
         * Use enums???
         * find params and set
         */
        for (String arg : args) {
            if (arg == null) {
                return CustomSnmpConstants.MSG_FAILED;
            }
            if (arg.startsWith(CustomSnmpConstants.PARAM_COMMUNITY)) {
                this.commStr = arg.substring(2).trim();
            } else if (arg.startsWith(CustomSnmpConstants.PARAM_PORT)) {
                this.snmpPortNum = arg.substring(2).trim();
            } else if (arg.startsWith(CustomSnmpConstants.PARAM_SNMP_VERSION)) {
                String ver = null;
                ver = arg.substring(2).trim();
                switch (ver) {
                    case "1":
                        this.snmpVersion = SnmpConstants.version1;
                        break;
                    case "2":
                    case "2c":
                        this.snmpVersion = SnmpConstants.version2c;
                        break;
                    default:
                        this.snmpVersion = SnmpConstants.version2c;
                        break;
                }
            }
            /*
             * switch ip or oid
             */
            else if (arg.split("\\.").length == 4) {
                this.targetAddr = arg;
            } else {
                this.oidStr = arg;
            }
        }
        if (!Validator.isValidStr(this.targetAddr) || !Validator.isValidStr(this.oidStr)) {
            return CustomSnmpConstants.MSG_FAILED;
        }
        return CustomSnmpConstants.MSG_SUCCESS;
    }

    //int
    public List<VariableBinding> snmpWalkInit() throws IOException, NullPointerException {
        Address targetAddress = GenericAddress.parse("udp:" + this.targetAddr + "/" + this.snmpPortNum);
        TransportMapping<? extends Address> transport = null;
        transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();
        // setting up target
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(this.commStr));
        target.setAddress(targetAddress);
        target.setRetries(3);
        target.setTimeout(1000 * 3);
        target.setVersion(this.snmpVersion);
        OID oid;

        oid = new OID(translateNameToOID(this.oidStr));

        return (this.walk(snmp, target, oid));

    }

    private List<VariableBinding> walk(Snmp snmp, Target target, OID oid) {
        // Get MIB data.
        TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
        List<TreeEvent> events = treeUtils.getSubtree(target, oid);

        return (this.handleResult(events, snmp, oid));
    }

    private List<VariableBinding> handleResult(List<TreeEvent> events, Snmp snmp, OID oid) {
        List<VariableBinding> resultList = new LinkedList<>();

        for (TreeEvent event : events) {
            if (event == null) {
                continue;
            }
            if (event.isError()) {
                continue;
            }

            VariableBinding[] varBindings = event.getVariableBindings();
            if (varBindings == null || varBindings.length == 0) {
                continue;
            }
            for (VariableBinding varBinding : varBindings) {
                if (varBinding == null) {
                    continue;
                }

                resultList.add(varBinding);


            }
        }


        try {
            snmp.close();
        } catch (IOException e) {

            //return "Couldn't close snmp session";
        }
        return resultList;

    }


    private String translateNameToOID(String oidStr) {
        switch (oidStr) {
            case "mib-2":
                oidStr = ".1.3.6.1.2.1";
                break;
            case "mib2":
                oidStr = ".1.3.6.1.2.1";
                break;
            case "system":
                oidStr = ".1.3.6.1.2.1.1";
                break;
            case "interfaces":
                oidStr = ".1.3.6.1.2.1.2";
                break;
            case "at":
                oidStr = ".1.3.6.1.2.1.3";
                break;
            case "ip":
                oidStr = ".1.3.6.1.2.1.4";
                break;
            case "icmp":
                oidStr = ".1.3.6.1.2.1.5";
                break;
            case "tcp":
                oidStr = ".1.3.6.1.2.1.6";
                break;
            case "udp":
                oidStr = ".1.3.6.1.2.1.7";
                break;
            case "egp":
                oidStr = ".1.3.6.1.2.1.8";
                break;
            case "transmission":
                oidStr = ".1.3.6.1.2.1.10";
                break;
            case "snmp":
                oidStr = ".1.3.6.1.2.1.11";
                break;
        }
        return oidStr;
    }
}
