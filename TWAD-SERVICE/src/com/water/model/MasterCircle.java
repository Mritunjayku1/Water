package com.water.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="MASTER_CIRCLE")
public class MasterCircle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CIRCLE_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer circleId;
	
	@Column(name = "CIRCLE_NAME", length = 100)
	private String circleName;
	
	

	@ManyToOne(targetEntity = MasterRegion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "REGION_ID", nullable = true)
	private MasterRegion regionId;
	
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TS", nullable = false, length = 26)
	private Date createTs;

	
	@Column(name = "CREATE_USERID", nullable = false, length = 30)
	private String createUserId;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TS", nullable = false, length = 26)
	private Date updateTs;

	
	@Column(name = "UPDATE_USERID", nullable = false, length = 30)
	private String updateUserId;


	public Integer getCircleId() {
		return circleId;
	}


	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}


	public String getCircleName() {
		return circleName;
	}


	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}


	public Date getCreateTs() {
		return createTs;
	}


	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}


	public String getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


	public Date getUpdateTs() {
		return updateTs;
	}


	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}


	public String getUpdateUserId() {
		return updateUserId;
	}


	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public MasterRegion getRegionId() {
		return regionId;
	}


	public void setRegionId(MasterRegion regionId) {
		this.regionId = regionId;
	}





}
