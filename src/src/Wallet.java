package src;

import java.time.LocalDateTime;

public class Wallet {
	
	private LocalDateTime createDate;
	private double balance;
	private Integer fd;
	
	public Wallet(LocalDateTime createDate, Double balance, Integer fd) {
		super();
		this.createDate = createDate;
		this.balance = balance;
		this.fd = fd;
	}
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getFd() {
		return fd;
	}
	public void setFd(Integer fd) {
		this.fd = fd;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
}
