package com.moj.hierarchy;

/**
 * @author nilesh
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class HierarchyNodeTest {

	@Test
	public void testCardinality() {
		HierarchyNode hierarchy = new HierarchyNode("depth1");
		hierarchy.addChild("child1").addChild("child1_child");
		hierarchy.addChild("child2");
		hierarchy.addChild("child3");
		assertEquals(5, hierarchy.cardinality());
	}

	@Test
	public void testNodeToString() {
		HierarchyNode hierarchy = new HierarchyNode("root");
		hierarchy.addChild("child1");
		assertEquals("root", hierarchy.toString());
	}

	@Test
	public void testIsRoot() {
		HierarchyNode hierarchy = new HierarchyNode("depth1");
		HierarchyNode child = hierarchy.addChild("child1");
		assertEquals(true, hierarchy.isRoot());
		assertEquals(false, child.isRoot());
	}

	@Test
	public void testIsLeaf() {
		HierarchyNode hierarchy = new HierarchyNode("depth1");
		HierarchyNode child = hierarchy.addChild("child1");
		HierarchyNode childs_child = child.addChild("child1_child");
		assertEquals(false, child.isLeaf());
		assertEquals(true, childs_child.isLeaf());
	}

	@Test
	public void testParent() {
		HierarchyNode hierarchy = new HierarchyNode("depth1");
		HierarchyNode child = hierarchy.addChild("child1");
		HierarchyNode childs_child = child.addChild("child1_child");
		assertEquals(hierarchy, child.parent);
		assertEquals(child, childs_child.parent);
	}

	@Test
	public void testFindHierarchyNode() {
		HierarchyNode hierarchy = new HierarchyNode("root");
		HierarchyNode child = hierarchy.addChild("child1");
		HierarchyNode notChild = new HierarchyNode("new");
		
		assertEquals(child, hierarchy.findHierarchyNode("child1"));
		assertNotEquals(notChild, hierarchy.findHierarchyNode("new"));
	}
}
