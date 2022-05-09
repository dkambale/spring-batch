package com.codingworld.springbatch.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sh_tran")
@Entity
public class ShareTransaction {

  @Id
  private Integer shareCode;
  private String companyName;
  private String qty;
  private String amount;
  private Integer type;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getShareCode() {
    return shareCode;
  }

  public void setShareCode(Integer shareCode) {
    this.shareCode = shareCode;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getQty() {
    return qty;
  }

  public void setQty(String qty) {
    this.qty = qty;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
}
