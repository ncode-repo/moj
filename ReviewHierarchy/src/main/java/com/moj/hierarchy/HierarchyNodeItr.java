package com.moj.hierarchy;

/**
 * @author nilesh
 */

import java.util.Iterator;

public class HierarchyNodeItr implements Iterator<HierarchyNode> {

	enum ProcessStages {
		ProcessParent, ProcessChildCurNode, ProcessChildSubNode
	}

	private HierarchyNode hierarchyNode;
	private ProcessStages doNext;
	private HierarchyNode next;
	private Iterator<HierarchyNode> childrenCurNodeItr;
	private Iterator<HierarchyNode> childrenSubNodeItr;

	public HierarchyNodeItr(HierarchyNode hierarchyNode) {
		this.hierarchyNode = hierarchyNode;
		this.doNext = ProcessStages.ProcessParent;
		this.childrenCurNodeItr = hierarchyNode.children.iterator();
	}

	@Override
	public boolean hasNext() {

		if (this.doNext == ProcessStages.ProcessParent) {
			this.next = this.hierarchyNode;
			this.doNext = ProcessStages.ProcessChildCurNode;
			return true;
		}

		if (this.doNext == ProcessStages.ProcessChildCurNode) {
			if (childrenCurNodeItr.hasNext()) {
				HierarchyNode childDirect = childrenCurNodeItr.next();
				childrenSubNodeItr = childDirect.iterator();
				this.doNext = ProcessStages.ProcessChildSubNode;
				return hasNext();
			} else {
				this.doNext = null;
				return false;
			}
		}

		if (this.doNext == ProcessStages.ProcessChildSubNode) {
			if (childrenSubNodeItr.hasNext()) {
				this.next = childrenSubNodeItr.next();
				return true;
			} else {
				this.next = null;
				this.doNext = ProcessStages.ProcessChildCurNode;
				return hasNext();
			}
		}

		return false;
	}

	@Override
	public HierarchyNode next() {
		return this.next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
