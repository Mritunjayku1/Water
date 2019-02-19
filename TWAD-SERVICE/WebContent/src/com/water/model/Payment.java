package com.water.model;

import java.util.Date;
import java.util.Set;

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
@Table(name="PAYMENT")
public class Payment {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PAY_INFO_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer payInfoId;
	

	
	
	@Column(name = "TRNS_INFO_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer trsInfoId;
	

	
	
	@Column(name = "TRNS_NUMBER", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer trsNumber;
	

	
	
	@Column(name = "PAY_TYPE", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer payType;
	

	
	
	@Column(name = "PAYMT_GATWAY_NUM", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer paymtGatwayNum;
	

	
    @Column(name = "PAY_STATUS", length = 1)
	private Character payStatus;
	
	
    @Column(name = "STATUS_FLAG", length = 1)
	private Character statusFlag;

	
	
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
	

	@ManyToOne(optional = false)
	@JoinColumn(name="APP_ID")
	private Application appId;

}
