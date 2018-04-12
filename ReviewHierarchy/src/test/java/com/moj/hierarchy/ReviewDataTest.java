package com.moj.hierarchy;

/**
 * @author nilesh
 */

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ReviewDataTest {
	@Test
	public void testSortingbyReviewersGrade() {
		List<ReviewData> lstRd = new ArrayList<>();
		lstRd.add(new ReviewData(Arrays.asList("Alfred Hitchcock", "(A9)", "Cary Grant", "(A8)")));
		lstRd.add(new ReviewData(Arrays.asList("Grace Kelly", "(A8)", "Omar Sharif", "(A7)")));
		lstRd.add(new ReviewData(Arrays.asList("Vivien Leigh", "(A8)", "Steve McQueen", "(A7)")));
		lstRd.add(new ReviewData(Arrays.asList("Hedy Lamarr", "(A6)", "Marie Dressler", "(A6)")));
		lstRd.add(new ReviewData(Arrays.asList("Yul Brynner", "(A6)", "Waheeda Rehman", "(A5)")));
		lstRd.add(new ReviewData(Arrays.asList("Vivien Leigh", "(A8)", "Doris Day", "(A7)")));
		lstRd.add(new ReviewData(Arrays.asList("Alfred Hitchcock", "(A9)", "Cary Grant", "(A8)")));
		lstRd.add(new ReviewData(Arrays.asList("Vivien Leigh", "(A8)", "Carole Lombard", "(A6)")));
		lstRd.add(new ReviewData(Arrays.asList("Steve McQueen", "(A7)", "Montgomery Clift", "(A6)")));

		Collections.sort(lstRd);
		
		List<ReviewData> sortedListByGrade = new ArrayList<>();
		sortedListByGrade.add(new ReviewData(Arrays.asList("Alfred Hitchcock", "(A9)", "Cary Grant", "(A8)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Alfred Hitchcock", "(A9)", "Cary Grant", "(A8)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Grace Kelly", "(A8)", "Omar Sharif", "(A7)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Vivien Leigh", "(A8)", "Steve McQueen", "(A7)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Vivien Leigh", "(A8)", "Doris Day", "(A7)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Vivien Leigh", "(A8)", "Carole Lombard", "(A6)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Steve McQueen", "(A7)", "Montgomery Clift", "(A6)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Hedy Lamarr", "(A6)", "Marie Dressler", "(A6)")));
		sortedListByGrade.add(new ReviewData(Arrays.asList("Yul Brynner", "(A6)", "Waheeda Rehman", "(A5)")));

		for(int i=0;i<lstRd.size();i++) {
			System.out.println(i+"="+lstRd.get(i).getReviewerGrade()+"="+sortedListByGrade.get(i).getReviewerGrade());
			assertTrue(lstRd.get(i).getReviewerGrade().equals(sortedListByGrade.get(i).getReviewerGrade()));
		}
	}

}
