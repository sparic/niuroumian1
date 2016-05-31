package com.myee.web;

import com.myee.domain.adempiere.AdempiereService;
import com.myee.domain.cleverm.ClevermService;
import com.myee.domain.restaurant.RestaurantService;
import com.myee.domain.restaurant.view.RDiningV;
import com.myee.domain.restaurant.view.RQueuingV;
import com.myee.domain.CustomUserDetails;
import com.myee.dto.PageResult;
import com.myee.dto.QueryVo;
import com.myee.dto.WhereVo;
import com.myee.util.StringUtil;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.data.ScatterData;
import com.github.abel533.echarts.series.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.Map;

/**
 * Created by Martin on 2015/7/29.
 */
@Controller
@RequestMapping("chart")
public class WebChartController {
    @Autowired
    private AdempiereService  adempiere;
    @Autowired
    private ClevermService    cleverm;
    @Autowired
    private RestaurantService restaurant;

    public CustomUserDetails currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return (CustomUserDetails) principal;
        }
        return CustomUserDetails.EMPTY;
    }


    private static class StatByTable {
        public int iQueued;
        public int iSeated;
        public int iPassed;
        public int totalWaited;
    }

    private static class StatTerm {
        public Object tableCount     = 0;
        public Object avgEatMinutes  = 0;
        public Object avgTermMinutes = 0;
    }

    @RequestMapping("statTermByType")
    @ResponseBody
    public Option statTermByType(@RequestBody WhereVo where) {
        Option option = new Option();

        Map<String, StatTerm> statMap = Maps.newHashMap();
        List<Object[]> resultList = restaurant.statTimesByType(currentUser(), where);
        for (Object[] result : resultList) {
            String typeName = (String) result[0];
            StatTerm statResult = statMap.get(typeName);
            if (null == statResult) {
                statResult = new StatTerm();
                statMap.put(typeName, statResult);
            }
            statResult.tableCount = result[1];
        }

        resultList = restaurant.statAvgEatMinutesByType(currentUser(), where);
        for (Object[] result : resultList) {
            String typeName = (String) result[0];
            StatTerm statResult = statMap.get(typeName);
            if (null == statResult) {
                statResult = new StatTerm();
                statMap.put(typeName, statResult);
            }
            if (null != result[1]) {
                statResult.avgEatMinutes = result[1];
            }
        }

        resultList = restaurant.statAvgTrunMinutesByType(currentUser(), where);
        for (Object[] result : resultList) {
            String typeName = (String) result[0];
            StatTerm statResult = statMap.get(typeName);
            if (null == statResult) {
                statResult = new StatTerm();
                statMap.put(typeName, statResult);
            }
            if (null != result[1]) {
                statResult.avgTermMinutes = result[1];
            }
        }

        List<Object> list0 = Lists.newArrayList();
        List<Object> list1 = Lists.newArrayList();
        List<Object> list2 = Lists.newArrayList();
        List<Object> list3 = Lists.newArrayList();
        for (Map.Entry<String, StatTerm> entry : statMap.entrySet()) {
            String typeName = entry.getKey();
            StatTerm stat = entry.getValue();
            list0.add(typeName);
            list1.add(stat.tableCount);
            list2.add(stat.avgEatMinutes);
            list3.add(stat.avgTermMinutes);
        }

        CategoryAxis axis = new CategoryAxis();
        axis.setData(list0);
        option.xAxis(axis);

        Bar bar1 = new Bar();
        bar1.setData(list1);
        option.series(bar1);

        Bar bar2 = new Bar();
        bar2.setData(list2);
        option.series(bar2);

        Bar bar3 = new Bar();
        bar3.setData(list3);
        option.series(bar3);

        return option;
    }

    @RequestMapping("statTermByZone")
    @ResponseBody
    public Option statTermByZone(@RequestBody WhereVo where) {
        Option option = new Option();
        Map<String, StatTerm> statMap = Maps.newHashMap();
        List<Object[]> resultList = restaurant.statTimesByZone(currentUser(), where);
        for (Object[] result : resultList) {
            String typeName = (String) result[0];
            StatTerm statResult = statMap.get(typeName);
            if (null == statResult) {
                statResult = new StatTerm();
                statMap.put(typeName, statResult);
            }
            statResult.tableCount = result[1];
        }

        resultList = restaurant.statAvgEatMinutesByZone(currentUser(), where);
        for (Object[] result : resultList) {
            String typeName = (String) result[0];
            StatTerm statResult = statMap.get(typeName);
            if (null == statResult) {
                statResult = new StatTerm();
                statMap.put(typeName, statResult);
            }
            if (null != result[1]) {
                statResult.avgEatMinutes = result[1];
            }
        }

        resultList = restaurant.statAvgTrunMinutesByZone(currentUser(), where);
        for (Object[] result : resultList) {
            String typeName = (String) result[0];
            StatTerm statResult = statMap.get(typeName);
            if (null == statResult) {
                statResult = new StatTerm();
                statMap.put(typeName, statResult);
            }
            if (null != result[1]) {
                statResult.avgTermMinutes = result[1];
            }
        }

        List<Object> list0 = Lists.newArrayList();
        List<Object> list1 = Lists.newArrayList();
        List<Object> list2 = Lists.newArrayList();
        List<Object> list3 = Lists.newArrayList();
        for (Map.Entry<String, StatTerm> entry : statMap.entrySet()) {
            String typeName = entry.getKey();
            StatTerm stat = entry.getValue();
            list0.add(typeName);
            list1.add(stat.tableCount);
            list2.add(stat.avgEatMinutes);
            list3.add(stat.avgTermMinutes);
        }

        CategoryAxis axis = new CategoryAxis();
        axis.setData(list0);
        option.xAxis(axis);

        Bar bar1 = new Bar();
        bar1.setData(list1);
        option.series(bar1);

        Bar bar2 = new Bar();
        bar2.setData(list2);
        option.series(bar2);

        Bar bar3 = new Bar();
        bar3.setData(list3);
        option.series(bar3);
        if (where.tableType() != null) {
            option.title("区域翻台统计分析(" + where.getTableType().getName() + ")");
        } else {
            option.title("区域翻台统计分析(全桌型)");
        }
        return option;
    }

    @RequestMapping("eatDuration")
    @ResponseBody
    public Option eatDuration(@RequestBody WhereVo where) {
        Option option = new Option();
        PageResult<RDiningV> resultList = restaurant.selectDining(currentUser(), where, new QueryVo(0, 0, Integer.MAX_VALUE));
        Map<String, List<Object>> statMap = Maps.newHashMap();
        for (RDiningV rv : resultList.getRows()) {
            String tableTypeName = rv.getTableTypeName();
            if (null != rv.getEatMinutes()) {
                List<Object> ptList = statMap.get(tableTypeName);
                if (null == ptList) {
                    ptList = Lists.newArrayList();
                    statMap.put(tableTypeName, ptList);
                }
                ptList.add(new ScatterData(trimDate(rv.timeSeated()), rv.getEatMinutes()));
            }
        }
        for (Map.Entry<String, List<Object>> entry : statMap.entrySet()) {
            String tableTypeName = entry.getKey();
            List<Object> pts = entry.getValue();
            Scatter scatter = new Scatter(tableTypeName);
            scatter.setData(pts);
            Data avg = new Data("平均值");
            avg.type(MarkType.average);
            scatter.markLine(new MarkLine().data(avg));
            Data max = new Data("最大值");
            max.type(MarkType.max);

            Data min = new Data("最小值");
            min.type(MarkType.min);
            scatter.markPoint(new MarkPoint().data(max, min));
            option.series().add(scatter);
        }

        Legend legend = new Legend();
        legend.data().addAll(statMap.keySet());
        option.legend(legend);

        return option;
    }

    Date trimDate(Date time) {
        Date result = new Date(time.getTime());
        result.setYear(2015);
        result.setMonth(8);
        result.setDate(26);
        return result;
    }

    @RequestMapping("missesOfTurn")
    @ResponseBody
    public Option missesOfTurn(@RequestBody WhereVo where) {
        Option option = new Option();
        List<Object> list0 = Lists.newArrayList();
        list0.add(new PieData("10分钟之内", restaurant.missOfTurn(currentUser(), where, 0, 9)));
        list0.add(new PieData("10~20分钟", restaurant.missOfTurn(currentUser(), where, 10, 19)));
        list0.add(new PieData("20~30分钟", restaurant.missOfTurn(currentUser(), where, 20, 29)));
        list0.add(new PieData("30分钟以上", restaurant.missOfTurn(currentUser(), where, 30, 1000)));
        Pie pie = new Pie();
        pie.setData(list0);
        option.series(pie);
        if (where.tableType() != null) {
            option.title("顾客流失时段分布图(" + where.getTableType().getName() + ")");
        } else {
            option.title("顾客流失时段分布图(全桌型)");
        }
        return option;
    }


    @RequestMapping("dinerTable")
    @ResponseBody
    public Option dinerTable(@RequestBody WhereVo where) {
        Option option = new Option();
        PageResult<RQueuingV> resultList = restaurant.selectQueuing(currentUser(), where, new QueryVo(0, 0, Integer.MAX_VALUE));
        Map<String, StatByTable> statMap = Maps.newHashMap();
        for (RQueuingV rv : resultList.getRows()) {
            String tableTypeName = rv.getTableTypeName();
            if (StringUtil.isBlank(tableTypeName)) {
                continue;
            }
            StatByTable stat = statMap.get(tableTypeName);
            if (null == stat) {
                stat = new StatByTable();
                statMap.put(tableTypeName, stat);
            }
            stat.iQueued++;
            long waitMinutes = 0;
            if (rv.getWaitMinutes() != null) {
                waitMinutes = rv.getWaitMinutes();
            }
            stat.totalWaited += waitMinutes;
            if ("3".equals(rv.getWaitState())) {
                stat.iSeated++;
            } else if ("4".equals(rv.getWaitState())) {
                stat.iPassed++;
            }
        }

        List<Object> list0 = Lists.newArrayList();
        List<Object> list1 = Lists.newArrayList();
        List<Object> list2 = Lists.newArrayList();
        List<Object> list3 = Lists.newArrayList();
        List<Object> list4 = Lists.newArrayList();
        for (Map.Entry<String, StatByTable> entry : statMap.entrySet()) {
            String typeName = entry.getKey();
            StatByTable stat = entry.getValue();
            list0.add(typeName);
            list1.add(stat.iQueued);
            list2.add(stat.iSeated);
            list3.add(stat.iPassed);
            list4.add((int) (stat.totalWaited / stat.iQueued));
        }

        CategoryAxis axis = new CategoryAxis();
        axis.setData(list0);
        option.xAxis(axis);

        Bar bar1 = new Bar();
        bar1.setData(list1);
        option.series(bar1);

        Bar bar2 = new Bar();
        bar2.setData(list2);
        option.series(bar2);

        Bar bar3 = new Bar();
        bar3.setData(list3);
        option.series(bar3);

        Line line1 = new Line();
        line1.setData(list4);
        option.series(line1);

        return option;
    }

    @RequestMapping("diningRate")
    @ResponseBody
    public Option diningRate(@RequestBody WhereVo where) {
        Option option = new Option();

        DateTime time = new DateTime(where.getQueueDate());

        List<Object> list0 = Lists.newArrayList();
        list0.add(time.minusMonths(1).toString("前一月(yyyy-MM-dd E)"));
        list0.add(time.minusDays(7).toString("前一周(yyyy-MM-dd E)"));
        list0.add(time.minusDays(2).toString("前两天(yyyy-MM-dd E)"));
        list0.add(time.minusDays(1).toString("前一天(yyyy-MM-dd E)"));
        list0.add(time.toString("当天(yyyy-MM-dd E)"));
        CategoryAxis axis = new CategoryAxis();
        axis.setData(list0);
        option.xAxis(axis);

        List<Object> list1 = Lists.newArrayList();
        List<Object> list2 = Lists.newArrayList();
        List<Object> list3 = Lists.newArrayList();

        amountBeforeDays(where.isBefore30(), time, 30, list1, list2, list3);
        amountBeforeDays(where.isBefore7(), time, 7, list1, list2, list3);
        amountBeforeDays(where.isBefore2(), time, 2, list1, list2, list3);
        amountBeforeDays(where.isBefore1(), time, 1, list1, list2, list3);
        amountBeforeDays(true, time, 0, list1, list2, list3);

        Bar bar1 = new Bar();
        bar1.setData(list1);
        option.series(bar1);

        Bar bar2 = new Bar();
        bar2.setData(list2);
        option.series(bar2);

        Line line1 = new Line();
        line1.setData(list3);
        option.series(line1);

        return option;
    }

    private void amountBeforeDays(boolean fromDB, DateTime time, int beforeDays, List<Object> list1, List<Object> list2, List<Object> list3) {
        long lQueued = 0L;
        long lSeated = 0L;
        if (fromDB) {
            Date before = time.minusDays(beforeDays).toDate();
            if (30 == beforeDays) {
                before = time.minusMonths(1).toDate();
            }
            lQueued = restaurant.countQueued(currentUser(), before);
            lSeated = restaurant.countSeated(currentUser(), before);
        }
        list1.add(lQueued);
        list2.add(lSeated);
        if (0 == lSeated) {
            list3.add(0);
        } else {
            list3.add((int) (lSeated * 100 / lQueued));
        }
    }

    List<Object> generateSeries(int size, int min, int max) {
        Random random = new Random();
        int step = max - min;
        List<Object> result = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            result.add(min + (int) (step * random.nextDouble()));
        }
        return result;
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public void databaseError(Exception ex, HttpServletResponse response) {
        try {
            ex.printStackTrace();
            response.addHeader("Error", ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
