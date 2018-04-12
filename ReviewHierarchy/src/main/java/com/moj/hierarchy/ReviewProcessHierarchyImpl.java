package com.moj.hierarchy;

/**
 * @author nilesh
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author nilesh
 * 
 * main implementation class to perform hierarchy ops.
 * Comments are not added in any other classes, most of the part is self explanatory 
 *
 */
public class ReviewProcessHierarchyImpl {
	// Can keep in prop file
	static String FILE_NAME = "src/main/resources/reviewers-and-reviewees.txt";
	static String DELIM1 = "reviews";
	static char DELIM2 = '(';
	static String NEW_CHILD_CHARS = "| ";
	static String CHILD_LEAF_CHARS = "\\-";
	static List<ReviewData> reviewDataList = new ArrayList<ReviewData>();

	/**
	 * processFileData(fileName) will read file by name and process i.e. takes line by line from file 
	 * and pass it to createReviewDataListByFileData(line) 
	 * @param fileName
	 * @throws IOException 
	 * 
	 */
	private void processFileData(String fileName) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.filter(line -> !(line.trim().isEmpty()))
					.forEach(ReviewProcessHierarchyImpl::createReviewDataListByFileData);
		} 
		if(reviewDataList!=null) Collections.sort(reviewDataList);
	}

	/**
	 * createReviewDataListByFileData(line) will extract review data by custom delimiters and put in list 
	 * @param line
	 */
	private static void createReviewDataListByFileData(String line) {
		String WITH_DELIMITER = "(?=\\%1$s)";
		List<String> lstVals = Pattern.compile(DELIM1 + "|" + String.format(WITH_DELIMITER, DELIM2)).splitAsStream(line)
				.collect(Collectors.toList());
		if (lstVals.size() == 4) {
			reviewDataList.add(new ReviewData(lstVals));
		}
	}

	/**
	 * createHierarchy() will process on createReviewDataListByFileData(line) resultant list
	 * It will create hierarchy i.e. custom tree (can have with multiple childs at same level) 
	 * @return hierarchy
	 */
	private HierarchyNode createHierarchy() {
		HierarchyNode hierarchy = null;
		List<ReviewData> pendingReviewData = new ArrayList<>();
		for (ReviewData data : reviewDataList) {
			String reviewer = data.getReviewer() + " " + data.getReviewerGrade();
			String reviewees = data.getReviewees() + " " + data.getRevieweesGrade();

			if (hierarchy != null) {
				/**
				 * This block will keep adding childs
				 */
				HierarchyNode child = hierarchy.findHierarchyNode(reviewer);
				if (child != null) {
					child.addChild(reviewees);
				} else {
					/**
					 * If any reviewer who has not been added as child earlier, it will be placed in pendingList to process later
					 */
					pendingReviewData.add(data);
				}
			} else {
				/**
				 * This will be entry point for hierarchy 
				 */
				hierarchy = new HierarchyNode(reviewer);
				hierarchy.addChild(reviewees);
			}
		}
		
		if (pendingReviewData != null) {
			/**
			 * processing pending childs/node, mostly it will be reviewees
			 * 1. Reverse list to maintain original order of node
			 * 2. Iterate for all pending nodes
			 * 3. Find for pending nodes parent then add pending node(root/reviewees) at right loc
			 */
			Collections.reverse(pendingReviewData);
			for (ReviewData data : pendingReviewData) {
				HierarchyNode pendningChild = hierarchy
						.findHierarchyNode(data.getReviewer() + " " + data.getReviewerGrade());
				if (pendningChild != null) {
					pendningChild.addPendingChild(data.getReviewees() + " " + data.getRevieweesGrade());
				}
			}
		}
		return hierarchy;
	}

	/**
	 * Format hierarchy with custom chars/formats
	 * @param depth
	 * @param isLeaf
	 * @return
	 */
	private String format(int depth, boolean isLeaf) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(NEW_CHILD_CHARS);
		}
		if (isLeaf) {
			sb.append(CHILD_LEAF_CHARS);
		}
		return sb.toString();
	}

	/**
	 * execute(fileName) will process entry point for entire task.
	 * If fileName present program will perform action on it else it will use default file.
	 * This func will execute all above functions in mentioned order.
	 * 
	 * @return reviewSystemHierarchy (This is hierarchical order of input file data i.e. reviewers-and-reviewees data)
	 * @param args
	 * @throws IOException 
	 */
	public String execute(String fileName) throws IOException {
		String reviewSystemHierarchy = new String();
		reviewDataList = new ArrayList<ReviewData>();
		processFileData(fileName == null ? FILE_NAME : fileName);

	    if (!reviewDataList.isEmpty()) {
			HierarchyNode hierarchy = createHierarchy();
			for (HierarchyNode node : hierarchy) {
				String indent = format(node.getLevel(), node.isLeaf());
				reviewSystemHierarchy += indent + node.name +"\n";
			}
			hierarchy=null;
		}
		return reviewSystemHierarchy;
	}
	/**
	 * Intentionally kept for-  MOJ member
	 * psv main(args) function kept to check program quickly. 
	 */
	public static void main(String[] args) throws IOException {
		ReviewProcessHierarchyImpl impl = new ReviewProcessHierarchyImpl();
		System.out.println(impl.execute(null));
	}
}
