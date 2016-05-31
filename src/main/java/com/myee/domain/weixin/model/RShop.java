package com.myee.domain.weixin.model;

import com.myee.domain.AbstractEntity;
import com.myee.domain.HasId;
import com.myee.domain.ToDto;
import com.myee.djinn.dto.ShopDetail;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 2016/1/18.
 */
@Entity
@Table(name = "r_shop")
public class RShop extends AbstractEntity implements HasId, ToDto<ShopDetail> {
    @Id
    @Column(name = "r_shop_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_137", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "R_Shop", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_137")
    private Long   storeId;
    @Column(name = "brand_name", length = 40)
    private String brandName;
    @Column(name = "shop_name", length = 40)
    private String shopName;
    @Column(name = "shop_type", length = 20)
    private String shopType;
    @Column(name = "score")
    private float  score;
    @Column(name = "time_open")
    private Date   timeOpen;
    @Column(name = "time_close")
    private Date   timeClose;
    @Column(name = "address")
    private String address;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "latitude")
    private double latitude;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public Date getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
    }

    @Override
    public ShopDetail toDto() {
        ShopDetail dto = new ShopDetail();
        dto.setClientId(getClientID());
        dto.setClientName(getBrandName());
        dto.setShopId(getStoreId());
        dto.setShopName(getShopName());
        dto.setScore(score);
        dto.setShopType(shopType);
        dto.setAddress(address);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setOpeningTime(toHourString(timeOpen) + "~" + toHourString(timeClose));
        return dto;
    }

    String toHourString(Date time) {
        return new DateTime(time.getTime()).toString("HH:mm");
    }

    @Override
    public String getId() {
        return String.valueOf(storeId);
    }

    @Override
    public void setId(String id) {

    }
}
