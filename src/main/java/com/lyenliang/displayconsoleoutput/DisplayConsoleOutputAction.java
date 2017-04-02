package com.lyenliang.displayconsoleoutput;

import java.io.IOException;

import org.apache.commons.jelly.XMLOutput;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.Run;

public class DisplayConsoleOutputAction<P extends AbstractProject<P, R>, R extends AbstractBuild<P, R>> implements Action {

	private final P project;
	
	public DisplayConsoleOutputAction(P project) {
		this.project = project;
	}
	
	@Override
	public String getIconFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrlName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getLastBuildNumber() {
    	if (project == null) {
            return 0;
        }
    	return project.getLastBuild().getNumber();
    }
    
    public R getBuild() {
        if (project == null) {
            return null;
        }
        R build = project.getLastBuild();
        return build;
    }

    public boolean isComplete() throws IOException {
        R build = getBuild();
        return build == null || build.getLogText().length() < 4096L;
        
    }
    
    public Run getRun() {
    	R build = getBuild();
    	return build;
    }

    public void writeLogTo(XMLOutput out) throws IOException {
        R build = getBuild();
        if (build != null) {
            build.getLogText().writeHtmlTo(Math.max(0L, build.getLogText().length() - 4096L), out.asWriter());
        }
    }

}