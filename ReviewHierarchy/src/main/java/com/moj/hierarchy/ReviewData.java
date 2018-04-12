package com.moj.hierarchy;

/**
 * @author nilesh
 */
import java.util.List;

public class ReviewData implements Comparable<ReviewData>{
	
	private String reviewer;
	private String reviewerGrade;
	private String reviewees;
	private String revieweesGrade;
	
	public ReviewData(String reviewer, String reviewerGrade, String reviewees, String revieweesGrade) {
		this.reviewer = reviewer;
		this.reviewerGrade = reviewerGrade;
		this.reviewees = reviewees;
		this.revieweesGrade = revieweesGrade;
	}
	public ReviewData(List<String> lstVals) {
		this.reviewer = lstVals.get(0).trim();
		this.reviewerGrade = lstVals.get(1).trim();
		this.reviewees = lstVals.get(2).trim();
		this.revieweesGrade = lstVals.get(3).trim();
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getReviewerGrade() {
		return reviewerGrade;
	}
	public void setReviewerGrade(String reviewerGrade) {
		this.reviewerGrade = reviewerGrade;
	}
	public String getReviewees() {
		return reviewees;
	}
	public void setReviewees(String reviewees) {
		this.reviewees = reviewees;
	}
	public String getRevieweesGrade() {
		return revieweesGrade;
	}
	public void setRevieweesGrade(String revieweesGrade) {
		this.revieweesGrade = revieweesGrade;
	}
	/**
	 * Comparing on Grade basis
	 */
	@Override
	public int compareTo(ReviewData reviewData) {
		return Integer.parseInt(reviewData.reviewerGrade.substring(2,reviewData.reviewerGrade.indexOf(')'))) - Integer.parseInt(this.reviewerGrade.substring(2,reviewerGrade.indexOf(')')));
	}
	@Override
	public String toString() {
		return "ReviewData [reviewer=" + reviewer + ", reviewerGrade=" + reviewerGrade + ", reviewees=" + reviewees
				+ ", revieweesGrade=" + revieweesGrade + "]\n";
	}
}
