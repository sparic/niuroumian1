package com.myee.util;

import com.myee.domain.cleverm.view.ResourceV;
import com.myee.dto.LookupVo;
import com.myee.dto.RTableTypeVo;
import com.myee.dto.ResourceVo;
import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.List;

public final class DTOUtil {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List<LookupVo> toLookups(List<RTableTypeVo> tableTypeList) {
        List<LookupVo> resultList = Lists.newArrayList();
        if (resultList != null) {
            for (RTableTypeVo typeVo : tableTypeList) {
                resultList.add(new LookupVo(typeVo.getTypeName(), typeVo.getTypeId()));
            }
        }
        return resultList;
    }
}
