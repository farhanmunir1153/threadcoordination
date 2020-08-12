package com.thread.coordination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadCoordination {

	public static void main(String[] args) throws InterruptedException {
		List<Long> inputNumbers = Arrays.asList(100000000l, 3435l, 35566l, 2334l, 53576l, 24842l, 3565l);
		List<FactorialThread> factorialThreads = new ArrayList<>();
		
		for(Long number: inputNumbers) {
			factorialThreads.add(new FactorialThread(number));
		}		
		int i = 0;
		for (Thread factThread : factorialThreads) {		
			factThread.setDaemon(true);
			factThread.start();
		}
		System.out.println("Total threads active after wait done = "+Thread.activeCount());
		for (Thread factThread : factorialThreads) {
			factThread.join(2000);
		}
		
		for (i = 0; i < inputNumbers.size(); i++) {
			FactorialThread factorialThread = factorialThreads.get(i);
			if(factorialThread.isFinished()) {
				System.out.println("Factorial of "+inputNumbers.get(i)+" is "+factorialThread.getResult()+"\n");
			}else {
				System.out.println("Calculation for "+inputNumbers.get(i)+" is still in progress");
				factorialThread.interrupt();
			}
		}		
		System.out.println("Total threads active after wait done = "+Thread.activeCount()); 
		if(Thread.activeCount() == 1) {
			System.out.println("All factorial threads either completed or failed only main thread remains now");
		}
		
	}
	
}
