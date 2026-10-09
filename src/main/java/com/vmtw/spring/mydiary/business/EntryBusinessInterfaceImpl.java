package com.vmtw.spring.mydiary.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmtw.spring.mydiary.dao.EntryDaoInterface;
import com.vmtw.spring.mydiary.entities.Entry;
@Component
public class EntryBusinessInterfaceImpl implements EntryBusinessInterface {
	@Autowired
	private EntryDaoInterface entryDaoInterface;
	
	

	public EntryDaoInterface getEntryDaoInterface() {
		return entryDaoInterface;
	}

	public void setEntryDaoInterface(EntryDaoInterface entryDaoInterface) {
		this.entryDaoInterface = entryDaoInterface;
	}

	@Override
	public void save(Entry entry) {
		entryDaoInterface.save(entry);
	}

	@Override
	public void update(Entry entry) {
		entryDaoInterface.update(entry);

	}

	public void delete(Entry entry) {
		entryDaoInterface.delete(entry);

	}

	public Entry findById(int id) {
		return entryDaoInterface.findById(id);
	}

	public List<Entry> findAll() {
		 return entryDaoInterface.findAll();
	}

	@Override
	public List<Entry> findByUserid(int id) {
		// TODO Auto-generated method stub
		return entryDaoInterface.findByUserid(id);
	}

}
