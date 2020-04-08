package src;

import java.util.List;

public class User {
	private String name;
	private Wallet wallet;
	private List<String> txn_statemaent;
	private List<String> offer_statement;
	
	public User(String name, Wallet wallet, List<String> txn_statemaent, List<String> offer_statement) {
		super();
		this.name = name;
		this.wallet = wallet;
		this.txn_statemaent = txn_statemaent;
		this.offer_statement = offer_statement;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public List<String> getTxn_statemaent() {
		return txn_statemaent;
	}
	public void setTxn_statemaent(List<String> txn_statemaent) {
		this.txn_statemaent = txn_statemaent;
	}
	public List<String> getOffer_statement() {
		return offer_statement;
	}
	public void setOffer_statement(List<String> offer_statement) {
		this.offer_statement = offer_statement;
	}
	
}
