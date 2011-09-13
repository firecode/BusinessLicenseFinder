/*
 * @author Raghu Ram Bongula
 */
package com.dto;

import android.app.ProgressDialog;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgressBarHolder.
 */
public class ProgressBarHolder {

	/** The progress bar. */
	public static ProgressDialog progressBar;

	/**
	 * Gets the progress bar.
	 * 
	 * @return the progress bar
	 */
	public static ProgressDialog getProgressBar() {
		return progressBar;
	}

	/**
	 * Sets the progress bar.
	 * 
	 * @param progressBar
	 *            the new progress bar
	 */
	public static void setProgressBar(ProgressDialog progressBar) {
		ProgressBarHolder.progressBar = progressBar;
	}

}
