package com.goodlife.model;

import java.util.ArrayList;
import java.util.List;

public class CurriculumTree {

	private Chapter chapter = new Chapter();
	private List<SubChapter> subChapList = new ArrayList<SubChapter>();

	public CurriculumTree() {
		super();
	}

	public CurriculumTree(Chapter chapterList,
			List<SubChapter> subChapList) {
		super();
		this.chapter = chapterList;
		this.subChapList = subChapList;
	}

	public Chapter getChapterList() {
		return chapter;
	}

	public void setChapterList(Chapter chapterList) {
		this.chapter = chapterList;
	}

	public List<SubChapter> getSubChapList() {
		return subChapList;
	}

	public void setSubChapList(List<SubChapter> subChapList) {
		this.subChapList = subChapList;
	}	
}
