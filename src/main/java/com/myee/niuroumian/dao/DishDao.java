package com.myee.niuroumian.dao;

import com.myee.niuroumian.domain.DishInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ray.Fu on 2016/6/1.
 */
public interface DishDao extends JpaRepository<DishInfo, Long> {
}
