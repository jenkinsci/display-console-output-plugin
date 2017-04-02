package com.lyenliang.displayconsoleoutput;

import java.util.Collection;
import java.util.Collections;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;

@Extension
public class DisplayConsoleOutputActionFactory extends TransientProjectActionFactory{

	 @Override
	    public Collection<? extends Action> createFor(AbstractProject target) {
	        return Collections.singletonList(new DisplayConsoleOutputAction(target));
	    }

}
