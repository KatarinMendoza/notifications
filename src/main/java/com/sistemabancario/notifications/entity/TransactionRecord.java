package com.sistemabancario.notifications.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
public class TransactionRecord {
	private String id;
	private String accountId;
	private String operationTypeId;
	private Double amount;
	private Integer share;
	private Date payDate;
	private String email;
}
