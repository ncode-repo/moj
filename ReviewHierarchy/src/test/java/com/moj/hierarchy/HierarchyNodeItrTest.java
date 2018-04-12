package com.moj.hierarchy;

/**
 * @author nilesh
 */

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class HierarchyNodeItrTest {
	@Test
	public void testHasNext() {
		HierarchyNode hierarchy = new HierarchyNode("root1");
		hierarchy.addChild("child1");
		Iterator<HierarchyNode> hiIterator = hierarchy.iterator();
		while(hiIterator.hasNext()) {
			hiIterator.next();
		}
		assertEquals(false, hiIterator.hasNext());
	}
	@Test
	public void testNext() {
		HierarchyNode hierarchy = new HierarchyNode("root1");
		for (HierarchyNode node : hierarchy) {
			assertEquals(node, hierarchy);
		}
	}
}
