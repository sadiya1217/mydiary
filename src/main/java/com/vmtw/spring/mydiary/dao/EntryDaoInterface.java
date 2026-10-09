package com.vmtw.spring.mydiary.dao;
import java.util.List;


import com.vmtw.spring.mydiary.entities.Entry;

public interface EntryDaoInterface {

	
	public void save(Entry entry);
	public void update(Entry entry);
	public void delete(Entry entry);
	public Entry findById(int id);
	public List<Entry> findAll();
	
	public List<Entry> findByUserid(int id);
}
