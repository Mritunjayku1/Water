package com.water.model;





import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





@Entity
@Table(name="SMS_TEMP")
public class SmsTemp  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SMS_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer smsId;

	
	@Column(name = "MSG_NAME", length = 100)
	private String smsName;

	
	
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
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "smsTypes", cascade = CascadeType.ALL)
	private Set<Message>  msgDtls;
}
