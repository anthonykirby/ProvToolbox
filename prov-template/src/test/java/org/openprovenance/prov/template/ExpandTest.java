package org.openprovenance.prov.template;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.NamedBundle;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.ProvFactory;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.TypedValue;
import org.openprovenance.prov.notation.Utility;

import static org.openprovenance.prov.template.Expand.VAR_NS;
import static org.openprovenance.prov.template.Expand.TMPL_NS;
import junit.framework.TestCase;

public class ExpandTest extends TestCase {

    static final String EX_NS = "http://example.org/";

    public ExpandTest(String testName) {
	super(testName);
    }
    
    ProvFactory pf=new org.openprovenance.prov.xml.ProvFactory();
    QualifiedName var_a=pf.newQualifiedName(VAR_NS, "a", "var");
    QualifiedName var_b=pf.newQualifiedName(VAR_NS, "b", "var");
    QualifiedName var_c=pf.newQualifiedName(VAR_NS, "c", "var");
    QualifiedName var_d=pf.newQualifiedName(VAR_NS, "d", "var");
    QualifiedName var_e=pf.newQualifiedName(VAR_NS, "e", "var");
    QualifiedName var_ag=pf.newQualifiedName(VAR_NS, "ag", "var");
    QualifiedName var_pl=pf.newQualifiedName(VAR_NS, "pl", "var");
 

    public  void expander(String in, String inBindings, String out) throws IOException, Throwable {
	System.out.println("expander ==========================================> " + in);
	
	Document doc= new Utility().readDocument(in, pf);
	Document docBindings= new Utility().readDocument(inBindings,pf);
	Bindings bindings1=Bindings.fromDocument(docBindings);
	
	NamedBundle bun=(NamedBundle) doc.getStatementOrBundle().get(0);

	Groupings grp1=Groupings.fromDocument(doc);

	System.out.println("Found groupings " + grp1);
	
	NamedBundle bun1=(NamedBundle) new Expand().expand(bun, bindings1, grp1).get(0);
	Document doc1=pf.newDocument();
	doc1.getStatementOrBundle().add(bun1);
	
	
	bun1.setNamespace(Namespace.gatherNamespaces(bun1));

	doc1.setNamespace(bun1.getNamespace());
	
	new Utility().writeDocument(doc1, out, pf);
	//InteropFramework inf=new InteropFramework();
	//inf.writeDocument(out, doc1);
	
	System.out.println("expander ==========================================> ");
    }
    

    public void expander (String in,
                          String out,
                          Bindings bindings1,
                          String outBindings) throws IOException, Throwable {
	System.out.println("expander ==========================================> " + in);
	
	Document doc= new Utility().readDocument(in, pf);
	
	NamedBundle bun=(NamedBundle) doc.getStatementOrBundle().get(0);

	Groupings grp1=Groupings.fromDocument(doc);

	System.out.println("Found groupings " + grp1);
	
	NamedBundle bun1=(NamedBundle) new Expand().expand(bun, bindings1, grp1).get(0);
	Document doc1=pf.newDocument();
	doc1.getStatementOrBundle().add(bun1);
	
	
	bun1.setNamespace(Namespace.gatherNamespaces(bun1));

	doc1.setNamespace(bun1.getNamespace());
	
	//System.out.println(bun1.getNamespace());
		
	
	Namespace.withThreadNamespace(doc1.getNamespace());
	new Utility().writeDocument(doc1, out, pf);	
	Document doc2=bindings1.toDocument();
	Namespace.withThreadNamespace(doc2.getNamespace());
	new Utility().writeDocument(doc2,outBindings,pf);
	
	System.out.println("expander ==========================================> ");

    }

    

    public void testExpand1() throws IOException, Throwable {
	
	Bindings bindings1=new Bindings();

	bindings1.addVariable(var_a,
	                      pf.newQualifiedName(EX_NS, "av1", "ex"));
	bindings1.addVariable(var_a,
	                      pf.newQualifiedName(EX_NS, "av2", "ex"));
	bindings1.addVariable(var_a,
	                      pf.newQualifiedName(EX_NS, "av3", "ex"));
	
	bindings1.addVariable(var_b,
	                      pf.newQualifiedName(EX_NS, "bv1", "ex"));
	bindings1.addVariable(var_b,
	                      pf.newQualifiedName(EX_NS, "bv2", "ex"));

	
	expander("src/test/resources/template1.provn",
	         "target/expanded1.provn",
	         bindings1,
	         "target/bindings1.provn");
	
	
    }
    
    public void testExpand2() throws IOException, Throwable {
	
	Bindings bindings1=new Bindings();

	bindings1.addVariable(var_a,
	                      pf.newQualifiedName(EX_NS, "av1", "ex"));
	bindings1.addVariable(var_a,
	                      pf.newQualifiedName(EX_NS, "av2", "ex"));
	
	bindings1.addVariable(var_b,
	                      pf.newQualifiedName(EX_NS, "bv1", "ex"));
	bindings1.addVariable(var_b,
	                      pf.newQualifiedName(EX_NS, "bv2", "ex"));

	
	expander("src/test/resources/template2.provn",
	         "target/expanded2.provn",
	         bindings1,
	         "target/bindings2.provn");
	
	
    }
    
    public void testExpand3() throws IOException, Throwable {
	
 	Bindings bindings1=new Bindings();

 	bindings1.addVariable(var_a,
 	                      pf.newQualifiedName(EX_NS, "av1", "ex"));
 	bindings1.addVariable(var_a,
 	                      pf.newQualifiedName(EX_NS, "av2", "ex"));
 	bindings1.addVariable(var_a,
 	                      pf.newQualifiedName(EX_NS, "av3", "ex"));
 	
 	bindings1.addVariable(var_b,
 	                      pf.newQualifiedName(EX_NS, "bv1", "ex"));
 	bindings1.addVariable(var_b,
 	                      pf.newQualifiedName(EX_NS, "bv2", "ex"));
 	
 	bindings1.addVariable(var_c,
 	                      pf.newQualifiedName(EX_NS, "cv1", "ex"));
 	bindings1.addVariable(var_c,
 	                      pf.newQualifiedName(EX_NS, "cv2", "ex"));
 	
  	
 	expander("src/test/resources/template3.provn",
 	         "target/expanded3.provn",
 	         bindings1,
 	        "target/bindings3.provn");
 	
 	
     }
    

    
    
    public void testExpand20() throws IOException, Throwable {
        
        Bindings bindings1=new Bindings();

        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av1", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av2", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av3", "ex"));
        
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv1", "ex"));
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv2", "ex"));
        
        bindings1.addVariable(var_c,
                              pf.newQualifiedName(EX_NS, "cv1", "ex"));
        bindings1.addVariable(var_c,
                              pf.newQualifiedName(EX_NS, "cv2", "ex"));
        
        List<TypedValue> ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "m22@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);
    

        

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc2@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
    
        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc3@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
      
        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc4@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
       
        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc5@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
       
        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc6@example", pf.getName().XSD_STRING));
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc7@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
      
        
        
  
        expander("src/test/resources/template20.provn",
                 "target/expanded20.provn",
                 bindings1,
                 "target/bindings20.provn");
        
        
    }
    
    
    
    
    public void testExpand21() throws IOException, Throwable {
        
        Bindings bindings1=new Bindings();

        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av1", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av2", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av3", "ex"));
        
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv1", "ex"));
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv2", "ex"));
        
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv3", "ex"));
        
        
        List<TypedValue> ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me2@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);
    

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me3@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);
    

        

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc2@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
    
      
        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc6@example", pf.getName().XSD_STRING));
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc7@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
      
        
        
  
        expander("src/test/resources/template21.provn",
                 "target/expanded21.provn",
                 bindings1,
                 "target/bindings21.provn");
        
        
    }
    
    
    
    
    
    public void testExpand22() throws IOException, Throwable {
        
        Bindings bindings1=new Bindings();

        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av1", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av2", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av3", "ex"));
        
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv1", "ex"));
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv2", "ex"));
        
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv3", "ex"));
        
        
        List<TypedValue> ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me2@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);
    

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me3@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);
    

        

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc2@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
    
      
        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc6@example", pf.getName().XSD_STRING));
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc7@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
      
        
        
  
        expander("src/test/resources/template22.provn",
                 "target/expanded22.provn",
                 bindings1,
                 "target/bindings22.provn");
        
        
    }
    
    

    
    
    public void testExpand23() throws IOException, Throwable {
        
        Bindings bindings1=new Bindings();

        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av1", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av2", "ex"));
        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av3", "ex"));
        
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv1", "ex"));
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv2", "ex"));
        
        bindings1.addVariable(var_b,
                              pf.newQualifiedName(EX_NS, "bv3", "ex"));
        
        
        List<TypedValue> ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me2@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);
    

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me3@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_d, ll);
    

        

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc1@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);

        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc2@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
    
      
        ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc6@example", pf.getName().XSD_STRING));
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "Luc7@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);
      
        
        
  
        expander("src/test/resources/template23.provn",
                 "target/expanded23.provn",
                 bindings1,
                 "target/bindings23.provn");
        
        
    }
    
    

    
    public void testExpand10() throws IOException, Throwable {
        
        Bindings bindings1=new Bindings();

        bindings1.addVariable(var_a,
                              pf.newQualifiedName(EX_NS, "av1", "ex"));
        
        
        bindings1.addVariable(var_e,
                              pf.newQualifiedName(EX_NS, "ev1", "ex"));
        
        bindings1.addVariable(var_ag,
                              pf.newQualifiedName(EX_NS, "agv1", "ex"));
        bindings1.addVariable(var_pl,
                              pf.newQualifiedName(EX_NS, "pl1", "ex"));
        
        
        List<TypedValue> ll=new LinkedList<TypedValue>();
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "me1@example", pf.getName().XSD_STRING));
        ll.add(pf.newOther(pf.newQualifiedName(TMPL_NS, "ignore", "app"), "m22@example", pf.getName().XSD_STRING));
        bindings1.addAttribute(var_e, ll);

    

  
  
        expander("src/test/resources/template10.provn",
                 "src/test/resources/bindings10.provn",
                 "target/expanded10.provn");
        
        
    }



    
    
    

}
