package org.openprovenance.prov.xml;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/** An interface for constructing Java Beans for the PROV data model */

public interface BeanConstructor {
    
    public Entity newEntity(QName id, List<Attribute> attributes);
    public Activity newActivity(QName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, List<Attribute> attributes);
    public Agent newAgent(QName id, List<Attribute> attributes);
    public Used newUsed(QName id, QName activity, QName entity, XMLGregorianCalendar time, List<Attribute> attributes);
    public WasGeneratedBy newWasGeneratedBy(QName id, QName entity, QName activity, XMLGregorianCalendar time, List<Attribute> attributes);
    public WasInvalidatedBy newWasInvalidatedBy(QName id, QName entity, QName activity, XMLGregorianCalendar time, List<Attribute> attributes);
    public WasStartedBy newWasStartedBy(QName id, QName activity, QName trigger, QName starter, XMLGregorianCalendar time, List<Attribute> attributes);
    public WasEndedBy newWasEndedBy(QName id, QName activity, QName trigger, QName ender, XMLGregorianCalendar time, List<Attribute> attributes);
    public WasDerivedFrom newWasDerivedFrom(QName id, QName e2, QName e1, QName activity, QName generation, QName usage,  List<Attribute> attributes);
    public WasAssociatedWith newWasAssociatedWith(QName id, QName a, QName ag, QName plan, List<Attribute> attributes);
    public WasAttributedTo newWasAttributedTo(QName id, QName e, QName ag,  List<Attribute> attributes);
    public ActedOnBehalfOf newActedOnBehalfOf(QName id, QName ag2, QName ag1, QName a, List<Attribute> attributes);
    public WasInformedBy newWasInformedBy(QName id, QName a2, QName a1, List<Attribute> attributes);
    public WasInfluencedBy newWasInfluencedBy(QName id, QName a2, QName a1, List<Attribute> attributes);
    public AlternateOf newAlternateOf(QName e2, QName e1);
    public SpecializationOf newSpecializationOf(QName e2, QName e1);
    public MentionOf newMentionOf(QName e2, QName e1, QName b);

    public HadMember newHadMember(QName c, List<QName> e);

    public Document newDocument(Hashtable<String, String> namespaces,
                                Collection<Statement> statements,
                                Collection<NamedBundle> bundles);
    public NamedBundle newNamedBundle(QName id, 
                                      Hashtable<String,String> namespaces, 
                                      Collection<Statement> statements);

}
