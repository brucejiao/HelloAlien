package com.brucejiao.alien.dointerface;

import java.util.List;

/**
 * Created by JiaoJianJun on 2017/9/1.
 */

public interface IAddressModel {
    /**
     * 获取加载进度
     * @param progress
     */
    void setProgress(boolean progress);

    /**
     * 传Object
     */
    void setObject(Object object);

    /**
     * 传String
     */
    void setValue(String value);
    /**
     * 传List
     */
    void setList(List list);
}
