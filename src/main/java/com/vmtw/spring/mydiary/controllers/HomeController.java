package com.vmtw.spring.mydiary.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vmtw.spring.mydiary.business.EntryBusinessInterface;
import com.vmtw.spring.mydiary.business.UserBusinessInterface;
import com.vmtw.spring.mydiary.entities.Entry;
import com.vmtw.spring.mydiary.entities.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserBusinessInterface userBusinessInterface;
	@Autowired
	HttpSession session;
	
	public UserBusinessInterface getUserBusinessInterface() {
		return userBusinessInterface;
	}

	public void setUserBusinessInterface(UserBusinessInterface userBusinessInterface) {
		this.userBusinessInterface = userBusinessInterface;
	}
	@Autowired
	private EntryBusinessInterface entryBusinessInterface;
	private Entry entry;
	
	public EntryBusinessInterface getEntryBusinessInterface() {
		return entryBusinessInterface;
	}

	public void setEntryBusinessInterface(EntryBusinessInterface entryBusinessInterface) {
		this.entryBusinessInterface = entryBusinessInterface;
	}

	@RequestMapping("login")
	public ModelAndView homepage() {
		
		ModelAndView model=new ModelAndView("loginpage");
		
		return model;
	}
	
	@RequestMapping("register")
	public ModelAndView registrationpage() {
		
		ModelAndView model=new ModelAndView("registrationpage");
		
		return model;
	}
	
	@RequestMapping(value="saveuser",method=RequestMethod.POST)
	public ModelAndView saveuser(@ModelAttribute("user") User user) {
		
		//code to save the user details in the data base
		ModelAndView model=new ModelAndView("registersuccess");
		
		userBusinessInterface.save(user);
		
				
		return model;
			
	}
	
	@RequestMapping(value="/processLogin", method=RequestMethod.POST)
	public ModelAndView processLoginuser(@ModelAttribute("user") User user) {
		
		ModelAndView model=new ModelAndView("loginpage");
		
		User user1=userBusinessInterface.findByUsername(user.getUsername());
		
		//System.out.println(user1.getUsername());
		
		if(user1!=null && user.getPassword().equals(user1.getPassword())) {
			
			System.out.println(user1.getUsername());
			
			 model.setViewName("userhomepage");
			 model.addObject("user", user1);
			 
			 session.setAttribute("user", user1);
			 
			 List <Entry> entries=null;
			 
			 try {
				entries=entryBusinessInterface.findByUserid(user1.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 model.addObject("entrieslist", entries);
			 
		}
		
		
		return model;
		
	}	
	@RequestMapping("addentry")
	public ModelAndView addentry(@ModelAttribute("entry") Entry entry) {
		
		ModelAndView model =new ModelAndView("addentryform");
		
		
		return model;
	}
	@RequestMapping("saveentry")
	public ModelAndView saveentry(@ModelAttribute("entry") Entry entry) {
		
		ModelAndView model = new ModelAndView("userhomepage");
		 entryBusinessInterface.save(entry);
		 
		 User user1=(User)session.getAttribute("user");
		 
		 List<Entry> entries=new ArrayList<Entry>();
		 
		 try {
			entries=entryBusinessInterface.findByUserid(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 model.addObject("entrieslist", entries);
		
		return model;
	}
	@RequestMapping("viewentry")
		public ModelAndView viewentry(@RequestParam("id") int id) {
			
			ModelAndView model= new ModelAndView("displayentry");
			
			Entry entry = entryBusinessInterface.findById(id);
			
			model.addObject("entry", entry);
		
		return model;
	}
	
	@RequestMapping("userhome")
	public ModelAndView userhomepage() {
			ModelAndView model = new ModelAndView("userhomepage");
			
			User user1=(User)session.getAttribute("user");
			
			List<Entry> entries=null;
			
			try {
				entries=entryBusinessInterface.findByUserid(user1.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			model.addObject("entrieslist", entries);
			
			
			
			
			return model;
	}
	@RequestMapping("updateentry")
	public ModelAndView updateentry(@RequestParam("id") int id) {
		
		ModelAndView model=new ModelAndView("displayupdateentry");
		
		Entry entry=entryBusinessInterface.findById(id);
		
		model.addObject("entry", entry);
		return model;
	}
	
	@RequestMapping("processentryupdate")
	public ModelAndView processentryupdate(@ModelAttribute("entry") Entry entry) {
		
		ModelAndView model=new ModelAndView("userhomepage");
		
		entryBusinessInterface.update(entry);
		
		User user1=(User)session.getAttribute("user");
		List<Entry> entries=null;
		
		 
		 try {
			entries=entryBusinessInterface.findByUserid(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 model.addObject("entrieslist", entries);
		
		return model;
	}
	
	@RequestMapping("deleteentry")
	public ModelAndView deleteentry(@RequestParam("id") int id) {

	    Entry entry = entryBusinessInterface.findById(id);

	    if (entry != null) {
	        entryBusinessInterface.delete(entry);
	    }

	    User user1 = (User) session.getAttribute("user");

	    List<Entry> entries = new ArrayList<Entry>();

	    try {
	        entries = entryBusinessInterface.findByUserid(user1.getId());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ModelAndView model = new ModelAndView("userhomepage");
	    model.addObject("entrieslist", entries);

	    return model;
	}
	@RequestMapping("signout")
	public ModelAndView signout() {

	    session.invalidate();

	    return new ModelAndView("loginpage");
	}
	
}
