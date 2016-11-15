package com.glooory.calligraphy.entity;

import com.google.gson.Gson;

/**
 * Created by Glooory on 2016/11/5 0005 15:01.
 */

public class PinsBean {

    /**
     * pin_id : 786156114
     * user_id : 19070438
     * board_id : 30765816
     * file_id : 108824612
     * file : {"id":108824612,"farm":"farm1","bucket":"hbimg","key":"5a4ee75240ad4a0af3dae460cf76d3d5fb08a9ef13d858-nYwKvV","type":"image/png","height":"898","width":"700","frames":"1","colors":[{"color":15198166,"ratio":0.39},{"color":15856113,"ratio":0.2}],"theme":"e7e7d6","themeColor":"e7e7d6"}
     * media_type : 0
     * source : null
     * link : null
     * raw_text :
     * text_meta : {}
     * via : 1
     * via_user_id : 0
     * original : null
     * created_at : 1468677320
     * like_count : 0
     * comment_count : 0
     * repin_count : 0
     * is_private : 0
     * orig_source : null
     * hide_origin : true
     */

    private String pin_id;
    private String file_id;
    /**
     * id : 108824612
     * farm : farm1
     * bucket : hbimg
     * key : 5a4ee75240ad4a0af3dae460cf76d3d5fb08a9ef13d858-nYwKvV
     * type : image/png
     * height : 898
     * width : 700
     * frames : 1
     * colors : [{"color":15198166,"ratio":0.39},{"color":15856113,"ratio":0.2}]
     * theme : e7e7d6
     * themeColor : e7e7d6
     */

    private FileBean file;

    public static PinsBean objectFromData(String str) {

        return new Gson().fromJson(str, PinsBean.class);
    }

    public String getPin_id() {
        return pin_id;
    }

    public void setPin_id(String pin_id) {
        this.pin_id = pin_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }
}
