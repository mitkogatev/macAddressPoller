package program.snmp;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import program.constants.BaseConstants;

import java.util.List;

public class SnmpOidParser {

    public String parsePlain(List<VariableBinding> varBindings){
        StringBuilder result=new StringBuilder();
        for (VariableBinding varBinding : varBindings) {
            OID resultOid = varBinding.getOid();
            String resultSyntaxString = varBinding.getVariable().getSyntaxString();
            Variable resultVar = varBinding.getVariable();//port
            result.append(resultOid).append("=").append(varBinding.getVariable()).append(System.lineSeparator());
        }
        return result.toString();
    }
    public String parseMac(List<VariableBinding> varBindings){
        StringBuilder result=new StringBuilder();
        for (VariableBinding varBinding : varBindings) {
            OID resultOid = varBinding.getOid();
            String resultSyntaxString = varBinding.getVariable().getSyntaxString();
            String port = varBinding.getVariable().toString();//port
            if(port.toLowerCase().contains("port")) {
                result.append(translateOidToMacTplink(resultOid)).append(" port: ").append(port.toLowerCase().replace("port","")).append(System.lineSeparator());
            }else{
                result.append(translateOidToMac(resultOid)).append(" port: ").append(varBinding.getVariable()).append(System.lineSeparator());

            }
        }
        return result.toString();
    }
    private String translateOidToMacTplink(OID oid){
        String oidStr=oid.toString();
        String[] octets=oidStr.split("\\.");
        int startIndex=octets.length-7;
        StringBuilder macAddress=new StringBuilder();
        for (int i = startIndex; i < octets.length-1; i++) {
            String hex=Integer.toHexString(Integer.valueOf(octets[i]));
            if(hex.length()==1){
                hex="0"+hex;
            }
            if(i!=octets.length-2){
                hex=hex+":";
            }
            macAddress.append(hex.toUpperCase());
        }
        return "mac: "+ macAddress.toString();
    }
    private String translateOidToMac(OID oid) {
        String oidStr=oid.toString();
        //String oidStr=oid.toString().substring(macOid.length());
        String[] octets=oidStr.split("\\.");
        //String vlan=octets[0];//wrong
        int startIndex=octets.length-BaseConstants.MAC_LENGTH;
        StringBuilder macAddress=new StringBuilder();
        for (int i = startIndex; i < octets.length; i++) {
            String hex=Integer.toHexString(Integer.valueOf(octets[i]));
            if(hex.length()==1){
                hex="0"+hex;
            }
            if(i!=octets.length-1){
                hex=hex+":";
            }
            macAddress.append(hex.toUpperCase());
        }
        //return ("vlan: "+vlan+"mac: "+macAddress.toString());
        return "mac: "+ macAddress.toString();
    }
}
