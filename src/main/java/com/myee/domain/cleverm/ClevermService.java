package com.myee.domain.cleverm;

import com.myee.domain.cleverm.model.Computer;
import com.myee.domain.cleverm.model.Notification;
import com.myee.domain.cleverm.model.Resource;
import com.myee.domain.cleverm.view.ResourceV;

import java.util.List;

public interface ClevermService {

    Computer getComputerById(Long computerID);
    
    Notification saveNotification(Notification entity);
    
    List<ResourceV> getChildren(Long orgID, Long parentResID);
    
    Resource saveResource(Resource entity);

    void deleteResource(Long resourceID);

    List<ResourceV> getRootNode();
    
    ResourceV findResource(Long resourceID);
}
