package com.myee.domain.cleverm.dao;

import com.myee.domain.cleverm.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerDao extends JpaRepository<Computer, Long> {

}
