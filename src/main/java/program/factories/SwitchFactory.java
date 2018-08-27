package program.factories;

import program.constants.BaseConstants;
import program.models.switches.Switch;

import java.lang.reflect.InvocationTargetException;

public class SwitchFactory implements ISwitchFactory {
    @Override
    public Switch createSwitch(int id, String ip, String community, String name, int portCount, String template) {
        Switch sw=null;
        Class swClass=null;
        if(template==null||BaseConstants.LINE_IS_EMPTY.equals(template)){
            template=BaseConstants.GENERIC_SWITCH_STR;
        }

        try {
            swClass=Class.forName(BaseConstants.SWITCH_MODEL_PATH+template+BaseConstants.SWITCH_STR);
        } catch (ClassNotFoundException e) {
            // TODO: 26.8.2018 г. print msg template not found trying generic
            try {
                swClass=Class.forName(BaseConstants.SWITCH_MODEL_PATH+BaseConstants.GENERIC_SWITCH_STR);
            } catch (ClassNotFoundException e1) {
                // TODO: 26.8.2018 г. return smth wrong msg

                return sw;
            }
        }
        try {

            sw=(Switch) swClass.getDeclaredConstructors()[0].newInstance(id, ip,community, name,  portCount);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return sw;
    }
}
