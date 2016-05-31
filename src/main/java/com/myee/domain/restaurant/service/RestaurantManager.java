package com.myee.domain.restaurant.service;

import com.myee.domain.restaurant.RestaurantService;
import com.myee.domain.restaurant.dao.*;
import com.myee.domain.restaurant.model.RQueuing;
import com.myee.domain.restaurant.view.RDiningV;
import com.myee.domain.restaurant.view.RQueuingV;
import com.myee.domain.CustomUserDetails;
import com.myee.dto.*;
import com.myee.util.StringUtil;
import com.google.common.collect.Lists;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Component
public class RestaurantManager implements RestaurantService {
    @Autowired
    private EntityManager em;
    @Autowired
    private TableDao      tableDao;
    @Autowired
    private TableTypeDao  typeDao;
    @Autowired
    private TableZoneDao  zoneDao;
    @Autowired
    private RDiningVDao   diningVDao;
    @Autowired
    private RQueuingVDao  queuingVDao;

    @Override
    public List<Object[]> statTimesByType(CustomUserDetails user, WhereVo whereVo) {
        long orgID = user.getCurrentOrgID();
        Date begin = firstMonment(whereVo.getBeginDate());
        Date end = lastMonment(whereVo.getEndDate());
        return diningVDao.statTimesByType(orgID, begin, end);
    }

    @Override
    public List<Object[]> statTimesByZone(CustomUserDetails user, WhereVo whereVo) {
        long orgID = user.getCurrentOrgID();
        Date begin = firstMonment(whereVo.getBeginDate());
        Date end = lastMonment(whereVo.getEndDate());
        if (whereVo.tableType() != null) {
            return diningVDao.statTimesByZone(orgID, whereVo.tableType(), begin, end);
        }
        return diningVDao.statTimesByZone(orgID, begin, end);
    }

    @Override
    public List<Object[]> statAvgEatMinutesByType(CustomUserDetails user, WhereVo whereVo) {
        long orgID = user.getCurrentOrgID();
        Date begin = firstMonment(whereVo.getBeginDate());
        Date end = lastMonment(whereVo.getEndDate());
        return diningVDao.statAvgEatMinutesByType(orgID, begin, end);
    }

    @Override
    public List<Object[]> statAvgEatMinutesByZone(CustomUserDetails user, WhereVo whereVo) {
        long orgID = user.getCurrentOrgID();
        Date begin = firstMonment(whereVo.getBeginDate());
        Date end = lastMonment(whereVo.getEndDate());
        if (whereVo.tableType() != null) {
            return diningVDao.statAvgEatMinutesByZone(orgID, whereVo.tableType(), begin, end);
        }
        return diningVDao.statAvgEatMinutesByZone(orgID, begin, end);
    }

    @Override
    public List<Object[]> statAvgTrunMinutesByType(CustomUserDetails user, WhereVo whereVo) {
        long orgID = user.getCurrentOrgID();
        Date begin = firstMonment(whereVo.getBeginDate());
        Date end = lastMonment(whereVo.getEndDate());
        return diningVDao.statAvgTrunMinutesByType(orgID, begin, end);
    }

    @Override
    public List<Object[]> statAvgTrunMinutesByZone(CustomUserDetails user, WhereVo whereVo) {
        long orgID = user.getCurrentOrgID();
        Date begin = firstMonment(whereVo.getBeginDate());
        Date end = lastMonment(whereVo.getEndDate());
        if (whereVo.tableType() != null) {
            return diningVDao.statAvgTrunMinutesByZone(orgID, whereVo.tableType(), begin, end);
        }
        return diningVDao.statAvgTrunMinutesByZone(orgID, begin, end);
    }

    @Override
    public List<RTableTypeVo> selectTypesByOrgId(long orgID) {
        return typeDao.selectTypesByOrgId(orgID);
    }

    @Override
    public List<RTableVo> selectTablesByOrgId(long orgID) {
        return tableDao.selectTablesByOrgId(orgID);
    }

    @Override
    public List<RTableZoneVo> selectZonesByOrgId(long orgID) {
        return zoneDao.selectZonesByOrgId(orgID);
    }

    @Override
    public PageResult<RQueuingV> selectQueuing(CustomUserDetails user, WhereVo whereVo, QueryVo queryVo) {
        int iPage = 0 == queryVo.getLength() ? 0 : queryVo.getStart() / queryVo.getLength();
        Pageable pageable = new PageRequest(iPage, queryVo.getLength());
        Specification spec = toQueueSpecification(user, whereVo);
        SimpleJpaRepository dao = new SimpleJpaRepository(RQueuingV.class, em);
        Page<RQueuingV> page = dao.findAll(spec, pageable);
        return new PageResult<RQueuingV>(queryVo.getDraw(), dao.count(spec), page.getContent());
    }

    @Override
    public PageResult<RDiningV> selectDining(CustomUserDetails user, WhereVo whereVo, QueryVo queryVo) {
        Pageable pageable = new PageRequest(queryVo.getStart() / queryVo.getLength(), queryVo.getLength());
        Specification spec = toDinerSpecification(user, whereVo);
        SimpleJpaRepository dao = new SimpleJpaRepository(RDiningV.class, em);
        Page<RDiningV> page = dao.findAll(spec, pageable);
        return new PageResult<RDiningV>(queryVo.getDraw(), dao.count(spec), page.getContent());
    }

    @Override
    public long countQueued(final CustomUserDetails currentUser, final Date queueDate) {
        SimpleJpaRepository dao = new SimpleJpaRepository(RQueuing.class, em);
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predList = Lists.newArrayList();

                Predicate predItem = cb.equal(root.get("orgID"), currentUser.getCurrentOrgID());
                predList.add(predItem);

                Date begin = firstMonment(queueDate);
                Date end = lastMonment(queueDate);
                predItem = cb.between(root.get("timeFallIn"), begin, end);
                predList.add(predItem);

                Predicate[] predArray = new Predicate[predList.size()];
                predList.toArray(predArray);
                return cb.and(predArray);
            }


        };
        return dao.count(spec);
    }

    private Date firstMonment(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    private Date lastMonment(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate(), 23, 59, 59);
    }

    @Override
    public long countSeated(final CustomUserDetails currentUser, final Date queueDate) {
        SimpleJpaRepository dao = new SimpleJpaRepository(RQueuing.class, em);
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predList = Lists.newArrayList();

                Predicate predItem = cb.equal(root.get("orgID"), currentUser.getCurrentOrgID());
                predList.add(predItem);

                predItem = cb.equal(root.get("waitState"), "3");
                predList.add(predItem);

                Date begin = firstMonment(queueDate);
                Date end = lastMonment(queueDate);
                predItem = cb.between(root.get("timeFallIn"), begin, end);
                predList.add(predItem);

                Predicate[] predArray = new Predicate[predList.size()];
                predList.toArray(predArray);
                return cb.and(predArray);
            }
        };
        return dao.count(spec);
    }

    @Override
    public Long missOfTurn(CustomUserDetails user, WhereVo where, long minWaitMinutes, long maxWaitMinutes) {
        if (where.tableType() != null) {
            return queuingVDao.missOfTurn(user.getOrgID(), where.tableType(), where.getBeginDate(), where.getEndDate(), minWaitMinutes, maxWaitMinutes);
        }
        return queuingVDao.missOfTurn(user.getOrgID(), where.getBeginDate(), where.getEndDate(), minWaitMinutes, maxWaitMinutes);
    }

    transient DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public PageResult<TableTurnVo> statTableTurn(CustomUserDetails user, WhereVo where, QueryVo query) {
        List<Object[]> turnResult = diningVDao.statTableTurn(user.getOrgID(), where.getBeginDate(), where.getEndDate());
        List<TableTurnVo> resultList = Lists.newArrayList();
        int iStart = query.getStart();
        int iEnd = Math.min(turnResult.size(), iStart + query.getLength()) - 1;
        String beginDate = "";
        if (where.getBeginDate() != null) {
            beginDate = dateFormatter.print(where.getBeginDate().getTime());
        }
        String endDate = "";
        if (where.getEndDate() != null) {
            endDate = dateFormatter.print(where.getEndDate().getTime());
        }
        while (iStart < iEnd) {
            Object[] line = turnResult.get(iStart);
            TableTurnVo vo = new TableTurnVo();
            vo.setBeginDate(beginDate);
            vo.setEndDate(endDate);
            vo.setTableName((String) line[0]);
            vo.setZoneName((String) line[1]);
            vo.setTableTypeName((String) line[2]);
            vo.setTurnTimes(line[3]);
            vo.setEatMinutes(line[4]);
            vo.setTurnMinutes(line[5]);
            resultList.add(vo);
            iStart++;
        }
        return new PageResult<TableTurnVo>(query.getDraw(), turnResult.size(), resultList);
    }

    private Specification<?> toDinerSpecification(final CustomUserDetails currentUser, final WhereVo where) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predList = Lists.newArrayList();

                Predicate predItem = cb.equal(root.get("orgID"), currentUser.getCurrentOrgID());
                predList.add(predItem);

//                if (where.getWaitState() > 0) {
//                    predItem = cb.equal(root.get("waitState"), where.getWaitState());
//                    predList.add(predItem);
//                }

                if (!StringUtil.isBlank(where.getTableName())) {
                    predItem = cb.like(root.get("tableName"), where.getTableName());
                    predList.add(predItem);
                }

                Date startDate = where.getBeginDate();
                Date endDate = where.getEndDate();
                if (null != startDate) {
                    Date begin = firstMonment(startDate);
                    predItem = cb.greaterThan(root.get("timeSeated"), begin);
                    predList.add(predItem);
                }

                if (null != endDate) {
                    Date end = lastMonment(endDate);
                    predItem = cb.lessThan(root.get("timeSeated"), end);
                    predList.add(predItem);
                }

                Long tableType = where.tableType();
                if (tableType != null) {
                    predItem = cb.equal(root.get("tableTypeId"), tableType.longValue());
                    predList.add(predItem);
                }

                Predicate[] predArray = new Predicate[predList.size()];
                predList.toArray(predArray);
                return cb.and(predArray);
            }
        };
    }

    private Specification<?> toQueueSpecification(final CustomUserDetails currentUser, final WhereVo where) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predList = Lists.newArrayList();

                Predicate predItem = cb.equal(root.get("orgID"), currentUser.getCurrentOrgID());
                predList.add(predItem);

                if (where.getWaitState() > 0) {
                    predItem = cb.equal(root.get("waitState"), where.getWaitState());
                    predList.add(predItem);
                }

                Date startDate = where.getBeginDate();
                Date endDate = where.getEndDate();
                if (null != startDate) {
                    Date begin = firstMonment(startDate);
                    predItem = cb.greaterThan(root.get("timeFallIn"), begin);
                    predList.add(predItem);
                }

                if (null != endDate) {
                    Date end = lastMonment(endDate);
                    predItem = cb.lessThan(root.get("timeFallIn"), end);
                    predList.add(predItem);
                }

                Long tableType = where.tableType();
                if (tableType != null) {
                    predItem = cb.equal(root.get("tableTypeId"), tableType.longValue());
                    predList.add(predItem);
                }
                Predicate[] predArray = new Predicate[predList.size()];
                predList.toArray(predArray);
                return cb.and(predArray);
            }
        };
    }

}
