package com.myee.web;

import com.myee.domain.adempiere.AdempiereService;
import com.myee.domain.cleverm.ClevermService;
import com.myee.domain.restaurant.RestaurantService;
import com.myee.domain.restaurant.view.RDiningV;
import com.myee.domain.restaurant.view.RQueuingV;
import com.myee.domain.CustomUserDetails;
import com.myee.dto.*;
import com.myee.util.DTOUtil;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("api")
public class WebAppController {

    private static final int RESOURCE_TYPE_DIR = 1;
    @Autowired
    private AdempiereService  adempiere;
    @Autowired
    private ClevermService    cleverm;
    @Autowired
    private RestaurantService restaurant;

    @RequestMapping("queryUserInfo")
    @ResponseBody
    public UserVo queryUerInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new UserVo(principal);
    }

    public CustomUserDetails currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return (CustomUserDetails) principal;
        }
        return CustomUserDetails.EMPTY;

    }


    @RequestMapping("queuingResult")
    @ResponseBody
    public PageResult<RQueuingV> queuingResult(WhereVo where, QueryVo query) {
        return restaurant.selectQueuing(currentUser(), where, query);
    }

    @RequestMapping("diningResult")
    @ResponseBody
    public PageResult<RDiningV> diningResult(WhereVo where, QueryVo query) {
        return restaurant.selectDining(currentUser(), where, query);
    }

    @RequestMapping("tableTurnResult")
    @ResponseBody
    public PageResult<TableTurnVo> tableTurnResult(WhereVo where, QueryVo query) {
        return restaurant.statTableTurn(currentUser(), where, query);
    }

    String toString(Object object) {
        if (object != null) {
            return object.toString();
        }
        return null;
    }

    @RequestMapping("exportTableTurn")
    public void exportTableTurn(String q, HttpServletResponse resp) {
        QueryVo query = new QueryVo(0, 0, Integer.MAX_VALUE);
        PageResult<TableTurnVo> pageResult = tableTurnResult(null, query);
        CSVWriter writer = null;
        try {
            Collection<TableTurnVo> rows = pageResult.getRows();
            if (rows != null) {
                resp.setHeader("Content-type", "text/csv;charset=gb2312");
                resp.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("翻台统计.csv", "utf8"));
                writer = new CSVWriter(resp.getWriter());
                writer.writeNext(new String[]{
                        "桌号",
                        "开始日期",
                        "结束日期",
                        "区域",
                        "桌型",
                        "翻台次数",
                        "平均就餐时长（分）",
                        "平均翻台时长（分）",
                        "备注"});
                for (TableTurnVo row : rows) {
                    writer.writeNext(new String[]{
                            row.getTableName(),
                            row.getBeginDate(),
                            row.getEndDate(),
                            row.getZoneName(),
                            row.getTableTypeName(),
                            toString(row.getTurnTimes()),
                            toString(row.getEatMinutes()),
                            toString(row.getTurnMinutes()),
                            ""
                    });
                }
            }
        } catch (Exception ex) {

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @RequestMapping("tableTypeResult")
    @ResponseBody
    public List<LookupVo> tableTypeResult() {
        List<RTableTypeVo> tableTypeList = restaurant.selectTypesByOrgId(currentUser().getCurrentOrgID());
        return DTOUtil.toLookups(tableTypeList);
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public void databaseError(Exception ex, HttpServletResponse response) {
        try {
            response.addHeader("Error", ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
