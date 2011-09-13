/*
 * @author Raghu Ram Bongula
 */
package com.thread;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputationGroup. This class is used to run multiple Runnable in
 * parallel. Each Runnable is run in a seperate thread. The onFinish() method is
 * called after all the parallel computation have completed.
 * 
 * ComputationGroup g = new ComputationGroup() { public void onFinish() {
 * System.out.println("Computations are done."); } }; MyRunner r1 = new
 * MyRunner(); MyRunner r2 = new MyRunner(); MyRunner r3 = new MyRunner();
 * 
 * g.add( r1 ); g.add( r2 ); g.add( r3 ); g.startComputations();
 * System.out.println( "This is printed printed immendiately.");
 */
public class ComputationGroup {

	/** The pc list. */
	private List<Runnable> pcList = null;

	/** The threshold. */
	protected int threshold = 0;

	/** The count. */
	protected int count = 0;

	/**
	 * The Constructor.
	 */
	public ComputationGroup() {
		pcList = new ArrayList<Runnable>();
	}

	/**
	 * Reset.
	 */
	private void reset() {
		threshold = 0;
		count = 0;

	}

	/**
	 * Add.
	 * 
	 * @param pc
	 *            the pc
	 */
	public void add(Runnable pc) {
		pcList.add(pc);

	}

	/**
	 * Start computations.
	 */
	public void startComputations() {
		reset();
		threshold = pcList.size();
		for (Runnable r : pcList) {
			RunnableWrapper pc = new RunnableWrapper(r);
			pc.setComputationGroup(this);
			new Thread(pc).start();
		}
	}

	/**
	 * Wait for release.
	 * 
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public synchronized void waitForRelease() throws InterruptedException {
		count++;
		// The final thread to reach barrier resets barrier and
		// releases all threads
		if (count == threshold) {
			// notify blocked threads that threshold has been reached
			System.out.println("######### Begin FINISH");
			// this causes the onFinished method to be called on the AWT thread.
			Finished finished = new Finished(this);
			new Thread(finished).start();
			notifyAll();
		} else {
			while (count < threshold) {
				wait();
			}
		}
	}

	/**
	 * Hook supplied if you have to inject something when all computations are
	 * done. Normally this is where you'd update you GUI stuff.
	 */
	public void onFinish() {
	}

	/**
	 * The Class Finished. A simple wrapper that makes onFinish() work as
	 * Runnable
	 */
	private class Finished implements Runnable {

		/** The g. */
		ComputationGroup g;

		/**
		 * The Constructor.
		 * 
		 * @param g
		 *            the g
		 */
		public Finished(ComputationGroup g) {
			this.g = g;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			g.onFinish();
		}
	}

	/**
	 * The Class RunnableWrapper.
	 */
	private class RunnableWrapper implements Runnable {

		/** The computation group. */
		private ComputationGroup computationGroup = null;

		/** The runner. */
		private Runnable runner = null;

		/**
		 * The Constructor.
		 * 
		 * @param r
		 *            the r
		 */
		public RunnableWrapper(Runnable r) {
			this.runner = r;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			// Delegate to the real runnable item
			this.runner.run();
			// now wait for others to finish
			try {
				this.computationGroup.waitForRelease();
			} catch (InterruptedException e) {

			}
		}

		/**
		 * Gets the computation group.
		 * 
		 * @return the computation group
		 */
		public ComputationGroup getComputationGroup() {
			return computationGroup;
		}

		/**
		 * Sets the computation group.
		 * 
		 * @param computationGroup
		 *            the computation group
		 */
		public void setComputationGroup(ComputationGroup computationGroup) {
			this.computationGroup = computationGroup;
		}
	}
}