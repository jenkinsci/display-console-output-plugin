package jenkins.plugins.displayconsoleoutput;

import java.io.IOException;

import org.apache.commons.jelly.XMLOutput;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;

public class DisplayConsoleOutputAction<P extends AbstractProject<P, R>, R extends AbstractBuild<P, R>>
		implements Action {

	private final P project;
	private long threshold = 4096L;

	public DisplayConsoleOutputAction(P project) {
		this.project = project;
	}

	@Override
	public String getIconFileName() {
		return null;
	}

	@Override
	public String getDisplayName() {
		return "Display Console Output Plugin";
	}

	@Override
	public String getUrlName() {
		return null;
	}

	public int getLastBuildNumber() {
		if (project != null && project.getLastBuild() != null ) {
			return project.getLastBuild().getNumber();
		}
		return 0;
	}

	
	@CheckForNull
	public R getBuild() {
		if (project == null) {
			return null;
		}
		return project.getLastBuild();
	}

	public boolean isTooLong() {
		R build = getBuild();
		if (build == null) {
			return false;
		}
		return build.getLogText().length() >= threshold;
	}

	public void writeLogTo(XMLOutput out) throws IOException {
		R build = getBuild();
		if (build != null) {
			build.getLogText().writeHtmlTo(Math.max(0L, build.getLogText().length() - threshold), out.asWriter());
		}
	}

}