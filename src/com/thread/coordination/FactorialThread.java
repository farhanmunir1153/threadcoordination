package com.thread.coordination;

import java.math.BigInteger;

public class FactorialThread extends Thread{

	private long number;
	private BigInteger result = BigInteger.ZERO;
	private boolean isFinished = false;

	public FactorialThread(long number) {
		super();
		this.number = number;
	}
	
	public BigInteger factorial(long n) {
		BigInteger temp = BigInteger.ONE;
		for (long i=n; i>0; i--) {
			if(this.isInterrupted()) {
				return temp;
			}
			temp = temp.multiply(new BigInteger(Long.toString(i)));
		}
		return temp;
	}
	
	@Override
	public void run() {
		this.result = factorial(this.number);
		this.isFinished = true;		
	}

	public BigInteger getResult() {
		return this.result;
	}

	public boolean isFinished() {
		return isFinished;
	}

}
