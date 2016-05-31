package com.myee.domain.adempiere.service;

import com.myee.domain.adempiere.dao.ADOrgVDao;
import com.myee.domain.adempiere.view.ADOrgV;
import com.myee.domain.adempiere.AdempiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AdempiereManager implements AdempiereService {
    @Autowired
    private EntityManager em;
    @Autowired
    private ADOrgVDao     orgVDao;

    @Override
    public ADOrgV findOrgV(long orgID) {
        return orgVDao.findOne(orgID);
    }
}
