package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Staffing {
	private static final String FCL_FILE = "./fcl/Staffing.fcl";
	
	public float getRisk(double funding, int staffing) {
		FIS fis = FIS.load(FCL_FILE, true);
		
		FunctionBlock fb = fis.getFunctionBlock("getRisk");
		JFuzzyChart.get().chart(fb);
		fis.setVariable("funding", funding);
		fis.setVariable("staffing", staffing);
		fis.evaluate();
		
		Variable risk = fb.getVariable("risk");
		JFuzzyChart.get().chart(risk, risk.getDefuzzifier(), true);
		
		return (float) risk.getValue();
	}
	
	public static void main(String[] args) {
		Staffing s = new Staffing();
		double risk = s.getRisk(60, 14);
		System.out.print(risk);
	}
}
