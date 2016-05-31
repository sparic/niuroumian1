package com.myee.domain.cleverm.service;

import com.myee.domain.cleverm.ClevermService;
import com.myee.domain.cleverm.dao.ComputerDao;
import com.myee.domain.cleverm.dao.NotificationDao;
import com.myee.domain.cleverm.dao.ResourceDao;
import com.myee.domain.cleverm.dao.ResourceVDao;
import com.myee.domain.cleverm.model.Computer;
import com.myee.domain.cleverm.model.Notification;
import com.myee.domain.cleverm.model.Resource;
import com.myee.domain.cleverm.view.ResourceV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClevermManager implements ClevermService {
    @Autowired
    private ComputerDao     computerDao;
    @Autowired
    private NotificationDao noticeDao;
    @Autowired
    private ResourceDao     resourceDao;
    @Autowired
    private ResourceVDao    resourcevDao;

    @Override
    public Computer getComputerById(Long computerID) {
        return computerDao.findOne(computerID);
    }

    @Override
    public Notification saveNotification(Notification entity) {
        return noticeDao.saveAndFlush(entity);
    }

    @Override
    public List<ResourceV> getChildren(Long orgID, Long parentResID) {
        return resourcevDao.getChildren(parentResID, orgID);
    }

    @Override
    public Resource saveResource(Resource entity) {
        return resourceDao.saveAndFlush(entity);
    }

    @Override
    public void deleteResource(Long resourceID) {
        resourceDao.delete(resourceID);
    }

    @Override
    public List<ResourceV> getRootNode() {
        return resourcevDao.getRootNode();
    }

    @Override
    public ResourceV findResource(Long resourceID) {
        return resourcevDao.findOne(resourceID);
    }

}
