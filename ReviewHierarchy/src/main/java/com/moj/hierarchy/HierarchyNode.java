package com.moj.hierarchy;

/**
 * @author nilesh
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HierarchyNode implements Iterable<HierarchyNode> {

	String name;
	public HierarchyNode parent;
	public List<HierarchyNode> children;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	public int cardinality() {
		return elementsIndex.size();
	}

	private List<HierarchyNode> elementsIndex;

	public HierarchyNode(String name) {
		this.name = name;
		this.children = new LinkedList<HierarchyNode>();
		this.elementsIndex = new LinkedList<HierarchyNode>();
		this.elementsIndex.add(this);
	}

	public HierarchyNode addChild(String child) {
		HierarchyNode childNode = new HierarchyNode(child);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public HierarchyNode addPendingChild(String child) {
		HierarchyNode childNode = new HierarchyNode(child);
		childNode.parent = this;
		this.children.add(0, childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public HierarchyNode updateRoot(String newRoot) {
		HierarchyNode newRootNode = new HierarchyNode(newRoot);
		newRootNode.parent = null;
		this.parent = newRootNode;
		newRootNode.children.add(this);
		this.registerChildForSearch(newRootNode);
		return newRootNode;
	}

	public int getLevel() {
		if (this.isRoot()) {
			return 0;
		}else {
			return parent.getLevel() + 1;
		}
	}

	private void registerChildForSearch(HierarchyNode node) {
		elementsIndex.add(node);
		if (parent != null) {
			parent.registerChildForSearch(node);
		}
	}

	public HierarchyNode findHierarchyNode(Comparable<String> cmp) {
		for (HierarchyNode element : this.elementsIndex) {
			String elname = element.name;
			if (cmp.compareTo(elname) == 0) {
				return element;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return name != null ? name.toString() : "[name null]";
	}

	@Override
	public final int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public Iterator<HierarchyNode> iterator() {
		HierarchyNodeItr iter = new HierarchyNodeItr(this);
		return iter;
	}
}
