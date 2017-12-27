package com.pactera.emtd.uhf;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import cn.pda.serialport.SerialPort;
import cn.pda.serialport.Tools;
import com.handheld.UHF.UhfManager;

/**
 * This class echoes a string called from JavaScript.
 */
public class UHF extends CordovaPlugin {
    public static final String NOSUPPORT = "暂不支持UHF!";
    UhfManager manager;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try{
            UhfManager manager = UhfManager.getInstance();
            if (action.equals("getEpcList")) {
                this.getEPCList(manager,callbackContext);
                manager.close();
                return true;
            }else if (action.equals("setOutputPower")) {
                String power= args.getString(0);
                this.setOutputPower(power,manager, callbackContext);
                return true;
                manager.close();
            }
            return false;
        }catch(Exception e){
            callbackContext.error(e.getMessage());
        }
    }

    private void getEPCList(UhfManager manager,CallbackContext callbackContext) {
        try {
            List<byte[]> epcBList = manager.inventoryRealTime();//ArrayList
            List<String> epcList = new ArrayList();
            if (epcBList != null && !epcBList.isEmpty()) {
                for (byte[] epc : epcBList) {
                    epcList.add(Tools.Bytes2HexString(epc, epc.length));
                }
            }
            JSONArray array = new JSONArray(epcList);
            callbackContext.success(array);

        } catch (Exception e) {
            callbackContext.error(e.getMessage());
        }
    }

    private void setOutputPower(String power,UhfManager manager,CallbackContext callbackContext) {
        try{
            int p=Integer.parseInt(power);
            if(manager.setOutputPower(p)){
                callbackContext.success();
            }else{
                callbackContext.error(0);
            }
        }catch (NumberFormatException e) {
            callbackContext.error("功率值必须为数字！");
        }catch(Exception e){
            callbackContext.error(e.getMessage());
        }
    }
}

