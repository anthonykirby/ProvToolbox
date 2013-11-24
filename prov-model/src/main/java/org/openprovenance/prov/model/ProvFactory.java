package org.openprovenance.prov.model;

import java.util.Collection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javax.xml.bind.JAXBElement;
import java.util.GregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConfigurationException;




/** A stateless factory for PROV objects. */


public abstract class ProvFactory implements LiteralConstructor, ModelConstructor, QNameExport {

    public static final String DEFAULT_NS = "_";
 
    public static final String packageList = "org.openprovenance.prov.xml:org.openprovenance.prov.xml.validation";

    private static String fileName = "toolbox.properties";
    
    private static final String toolboxVersion = getPropertiesFromClasspath(fileName).getProperty("toolbox.version");

    private static Properties getPropertiesFromClasspath(String propFileName) {
        Properties props = new Properties();
        InputStream inputStream = ProvFactory.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream == null) {
            return null;
        }
        try {
            props.load(inputStream);
        } catch (IOException ee) {
            return null;
        }
        return props;   
    }

    public static String printURI(java.net.URI u) {
	return u.toString();
    }

  
    protected DatatypeFactory dataFactory;



    final protected ObjectFactory of;
    public ProvFactory(ObjectFactory of) {
	this.of = of;
	init();
    }



    public void addAttribute(HasOther a, Other o) {
	a.getOther().add(o);
    }



    public ActedOnBehalfOf addAttributes(ActedOnBehalfOf from,
					 ActedOnBehalfOf to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getOther().addAll(from.getOther());
	return to;
    }

   
    public Activity addAttributes(Activity from, Activity to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getLocation().addAll(from.getLocation());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public Agent addAttributes(Agent from, Agent to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	// to.getLocation().addAll(from.getLocation());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public Entity addAttributes(Entity from, Entity to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getLocation().addAll(from.getLocation());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public Used addAttributes(Used from, Used to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getLocation().addAll(from.getLocation());
	to.getRole().addAll(from.getRole());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasAssociatedWith addAttributes(WasAssociatedWith from,
					   WasAssociatedWith to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getRole().addAll(from.getRole());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasAttributedTo addAttributes(WasAttributedTo from,
					 WasAttributedTo to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasDerivedFrom addAttributes(WasDerivedFrom from, WasDerivedFrom to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasEndedBy addAttributes(WasEndedBy from, WasEndedBy to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getLocation().addAll(from.getLocation());
	to.getRole().addAll(from.getRole());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasGeneratedBy addAttributes(WasGeneratedBy from, WasGeneratedBy to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getLocation().addAll(from.getLocation());
	to.getRole().addAll(from.getRole());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasInfluencedBy addAttributes(WasInfluencedBy from,
					 WasInfluencedBy to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasInformedBy addAttributes(WasInformedBy from, WasInformedBy to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasInvalidatedBy addAttributes(WasInvalidatedBy from,
					  WasInvalidatedBy to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getLocation().addAll(from.getLocation());
	to.getRole().addAll(from.getRole());
	to.getOther().addAll(from.getOther());
	return to;
    }

    public WasStartedBy addAttributes(WasStartedBy from, WasStartedBy to) {
	to.getLabel().addAll(from.getLabel());
	to.getType().addAll(from.getType());
	to.getLocation().addAll(from.getLocation());
	to.getRole().addAll(from.getRole());
	to.getOther().addAll(from.getOther());	
	return to;
    }

    public void addLabel(HasLabel a, String label) {
	a.getLabel().add(newInternationalizedString(label));
    }

    public void addLabel(HasLabel a, String label, String language) {
	a.getLabel().add(newInternationalizedString(label, language));
    }

    public void addPrimarySourceType(HasType a) {
	a.getType().add(newType(Name.QNAME_PROV_PRIMARY_SOURCE,Name.QNAME_XSD_QNAME));
    }

    public void addQuotationType(HasType a) {
	a.getType().add(newType(Name.QNAME_PROV_QUOTATION,Name.QNAME_XSD_QNAME));
    }

    public void addRevisionType(HasType a) {
	a.getType().add(newType(Name.QNAME_PROV_REVISION,Name.QNAME_XSD_QNAME));
    }

    public void addBundleType(HasType a) {
	a.getType().add(newType(Name.QNAME_PROV_BUNDLE,Name.QNAME_XSD_QNAME));
    }

    public void addRole(HasRole a, Role role) {
	if (role != null) {
	    a.getRole().add(role);
	}
    }

 
    public void addType(HasType a, Object o, QName type) {
	a.getType().add(newType(o,type));
    }

    public void addType(HasType a, Type type) {

	a.getType().add(type);
    }

    public void addType(HasType a, URI type) {
	URIWrapper u = new URIWrapper();
	u.setValue(type);
	a.getType().add(newType(u,Name.QNAME_XSD_ANY_URI));
    }

    public byte [] base64Decoding(String s) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(s);
    }

 
 


    public String base64Encoding(byte [] b) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(b);
    }

    abstract public IDRef createIDRef();

    /* Return the first label, it it exists */
    public String getLabel(HasOther e) {

	List<InternationalizedString> labels = ((HasLabel) e).getLabel();
	if ((labels == null) || (labels.isEmpty()))
	    return null;
	if (e instanceof HasLabel)
	    return labels.get(0).getValue();
	return "pFact: label TODO";
    }


    public ObjectFactory getObjectFactory() {
	return of;
    }

    public String getPackageList() {
	return packageList;
    }

  

    public String getRole(HasOther e) {
	return "pFact: role TODO";
    }

    public List<Type> getType(HasOther e) {
	if (e instanceof HasType)
	    return ((HasType) e).getType();
	List<Type> res = new LinkedList<Type>();
	res.add(newType("pFact: type TODO",Name.QNAME_XSD_STRING));
	return res;
    }

    public String getVersion() {
        return toolboxVersion;
    }

    public byte [] hexDecoding(String s) {
        try {
            return org.apache.commons.codec.binary.Hex.decodeHex(s.toCharArray());
        } catch  (Exception e) {
            return s.getBytes(); // fall back, but obviously, this is not converted
        }
     
    }
    public String hexEncoding(byte [] b) {
        return org.apache.commons.codec.binary.Hex.encodeHexString(b);
    }

    
    
    protected void init() {
	try {
	    dataFactory = DatatypeFactory.newInstance();
	} catch (DatatypeConfigurationException ex) {
	    throw new RuntimeException(ex);
	}
    }

    public ActedOnBehalfOf newActedOnBehalfOf(ActedOnBehalfOf u) {
	ActedOnBehalfOf u1 = newActedOnBehalfOf(u.getId(),
						u.getDelegate(),
						u.getResponsible(),
						u.getActivity());
	u1.getOther().addAll(u.getOther());
	return u1;
    }

    public ActedOnBehalfOf newActedOnBehalfOf(QName id, IDRef delegate,
					      IDRef responsible,
					      IDRef eid2) {
	ActedOnBehalfOf res = of.createActedOnBehalfOf();
	res.setId(id);
	res.setActivity(eid2);
	res.setDelegate(delegate);
	res.setResponsible(responsible);
	return res;
    }

    public ActedOnBehalfOf newActedOnBehalfOf(QName id, QName ag2, QName ag1) {
        ActedOnBehalfOf res=newActedOnBehalfOf(id, ag2, ag1, null,null);
        return res;
    }

    public ActedOnBehalfOf newActedOnBehalfOf(QName id, QName ag2, QName ag1, QName a, Collection<Attribute> attributes) {
        IDRef agid2=(ag2==null)? null : newIDRef(ag2);
        IDRef agid1=(ag1==null)? null : newIDRef(ag1);
        IDRef aid=(a==null)? null : newIDRef(a);
        ActedOnBehalfOf res=newActedOnBehalfOf(id, agid2, agid1, aid);
        setAttributes(res, attributes);
        return res;
    }


    public Activity newActivity(Activity a) {
	Activity res = newActivity(a.getId());
	res.getType().addAll(a.getType());
	res.getLabel().addAll(a.getLabel());
	res.getLocation().addAll(a.getLocation());
	res.setStartTime(a.getStartTime());
	res.setEndTime(a.getEndTime());
	return res;
    }

    public Activity newActivity(QName a) {
	Activity res = of.createActivity();
	res.setId(a);
	return res;
    }

    public Activity newActivity(QName q, String label) {
	Activity res = newActivity(q);
	if (label != null)
	    res.getLabel().add(newInternationalizedString(label));
	return res;
    }

    public Activity newActivity(QName id, 
                                XMLGregorianCalendar startTime,
				XMLGregorianCalendar endTime,
				Collection<Attribute> attributes) {
	Activity res = newActivity(id);
	res.setStartTime(startTime);
	res.setEndTime(endTime);
	setAttributes(res, attributes);
	return res;

    }

    public Agent newAgent(Agent a) {
	Agent res = newAgent(a.getId());
	res.getType().addAll(a.getType());
	res.getLabel().addAll(a.getLabel());
	return res;
    }

    public Agent newAgent(QName ag) {
	Agent res = of.createAgent();
	res.setId(ag);
	return res;
    }

    public Agent newAgent(QName id, Collection<Attribute> attributes) {
	Agent res = newAgent(id);
	setAttributes(res, attributes);
	return res;
    }

    public Agent newAgent(QName ag, String label) {
	Agent res = newAgent(ag);
	if (label != null)
	    res.getLabel().add(newInternationalizedString(label));
	return res;
    }
    

    public AlternateOf newAlternateOf(IDRef eid2, IDRef eid1) {
	AlternateOf res = of.createAlternateOf();
	res.setAlternate1(eid2);
	res.setAlternate2(eid1);
	return res;
    }


    public AlternateOf newAlternateOf(QName e2, QName e1) {
        IDRef eid2 = (e2==null)? null: newIDRef(e2);
        IDRef eid1 = (e1==null)? null: newIDRef(e1);
        return newAlternateOf(eid2, eid1);
    }

   abstract public Attribute newAttribute(Attribute.AttributeKind kind, Object value, QName type);
   

   abstract public Attribute newAttribute(QName qname, Object value, QName type);


   
 
    public Attribute newAttribute(String namespace, String localName,
				  String prefix, Object value, QName type) {
	Attribute res;
	if (prefix==null) {
	    res = newAttribute(new QName(namespace, localName),
	                       value, type); 
	} else {
	    res = newAttribute(new QName(namespace, localName, prefix),
                               value, type);
	}
	return res;
    }

    public DerivedByInsertionFrom newDerivedByInsertionFrom(QName id,
							    IDRef after,
							    IDRef before,
							    List<Entry> keyEntitySet,
							    Collection<Attribute> attributes) {
	DerivedByInsertionFrom res = of.createDerivedByInsertionFrom();
	res.setId(id);
	res.setNewDictionary(after);
	res.setOldDictionary(before);
	if (keyEntitySet != null)
	    res.getKeyEntityPair().addAll(keyEntitySet);
	setAttributes(res, attributes);
	return res;
    }

    public DerivedByInsertionFrom newDerivedByInsertionFrom(QName id,
                                                            QName after,
                                                            QName before,
                                                            List<KeyQNamePair> keyEntitySet,
                                                            Collection<Attribute> attributes) {
    	IDRef aa=createIDRef();
    	aa.setRef(after);
    	IDRef ab=createIDRef();
    	ab.setRef(before);
    	List<Entry> entries=new LinkedList<Entry>();
    	if (keyEntitySet!=null) {
    	for (KeyQNamePair p: keyEntitySet) {
    		Entry e=of.createEntry();
    		e.setKey(p.key);
        	IDRef ac=createIDRef();
        	ac.setRef(p.name);
    		e.setEntity(ac);
    		entries.add(e);
    	}
    	}
    	return newDerivedByInsertionFrom(id, aa, ab, entries, attributes);
    }

    public DerivedByRemovalFrom newDerivedByRemovalFrom(QName id,
							IDRef after,
							IDRef before,
							List<Key> keys,
							Collection<Attribute> attributes) {
	DerivedByRemovalFrom res = of.createDerivedByRemovalFrom();
	res.setId(id);
	res.setNewDictionary(after);
	res.setOldDictionary(before);
	if (keys != null)
	    res.getKey().addAll(keys);
	setAttributes(res, attributes);
	return res;
    }

    
    public DerivedByRemovalFrom newDerivedByRemovalFrom(QName id,
                                                            QName after,
                                                            QName before,
                                                            List<Key> keys,
                                                            Collection<Attribute> attributes) {
    	IDRef aa=createIDRef();
    	aa.setRef(after);
    	IDRef ab=createIDRef();
    	ab.setRef(before);
    	return newDerivedByRemovalFrom(id, aa, ab, keys, attributes);
    }

    public DictionaryMembership newDictionaryMembership(QName id, IDRef dict,
						    List<Entry> entitySet) {
	DictionaryMembership res = of.createDictionaryMembership();
	//res.setId(id);  TODO: no id?
	res.setDictionary(dict);
	if (entitySet != null)
	    res.getKeyEntityPair().addAll(entitySet);
	return res;
    }

    public DictionaryMembership newDictionaryMembership(QName id, List<KeyQNamePair> keyEntitySet) {
	DictionaryMembership res = of.createDictionaryMembership();
    	IDRef idr=createIDRef();
    	idr.setRef(id);
	res.setDictionary(idr);
	
	List<Entry> entries=new LinkedList<Entry>();
    	if (keyEntitySet!=null) {
    	    for (KeyQNamePair p: keyEntitySet) {
    		Entry e=of.createEntry();
    		e.setKey(p.key);
    		IDRef ac=createIDRef();
    		ac.setRef(p.name);
    		e.setEntity(ac);
    		entries.add(e);
    	    }
    	}
	res.getKeyEntityPair().addAll(entries);
	return res;
    }

    public Document newDocument() {
	Document res = of.createDocument();
	return res;
    }

    public Document newDocument(Activity[] ps, Entity[] as, Agent[] ags,
				Statement[] lks) {

	return newDocument(((ps == null) ? null : Arrays.asList(ps)),
			   ((as == null) ? null : Arrays.asList(as)),
			   ((ags == null) ? null : Arrays.asList(ags)),
			   ((lks == null) ? null : Arrays.asList(lks)));
    }
    
    public Document newDocument(Collection<Activity> ps, Collection<Entity> as,
				Collection<Agent> ags, Collection<Statement> lks) {
	Document res = of.createDocument();
	res.getStatementOrBundle().addAll(ps);
	res.getStatementOrBundle().addAll(as);
	res.getStatementOrBundle().addAll(ags);
	res.getStatementOrBundle().addAll(lks);
	return res;
    }
    
    public Document newDocument(Document graph) {
	Document res = of.createDocument();
	res.getStatementOrBundle()
	   .addAll(graph.getStatementOrBundle());
	return res;
    }


    public Document newDocument(Namespace namespace,
                                Collection<Statement> statements,
                                Collection<NamedBundle> bundles) {
	Document res = of.createDocument();
	res.setNamespace(namespace);
	res.getStatementOrBundle()
	   .addAll(statements);
	res.getStatementOrBundle()
	   .addAll(bundles);
	return res;
    }

    public Duration newDuration(int durationInMilliSeconds) {
        Duration dur=dataFactory.newDuration(durationInMilliSeconds);
        return dur;
    }
    

    public Duration newDuration(String lexicalRepresentation) {
        Duration dur=dataFactory.newDuration(lexicalRepresentation);
        return dur;
    }



    public JAXBElement<ActedOnBehalfOf> newElement(ActedOnBehalfOf u) {
	return of.createActedOnBehalfOf(u);
    }

    public JAXBElement<Activity> newElement(Activity u) {
	return of.createActivity(u);
    }

    public JAXBElement<Agent> newElement(Agent u) {
	return of.createAgent(u);
    }
    public JAXBElement<Entity> newElement(Entity u) {
	return of.createEntity(u);
    }

    public JAXBElement<MentionOf> newElement(MentionOf u) {
	return of.createMentionOf(u);
    }

    public JAXBElement<Used> newElement(Used u) {
	return of.createUsed(u);
    }

    public JAXBElement<WasAssociatedWith> newElement(WasAssociatedWith u) {
	return of.createWasAssociatedWith(u);
    }

    public JAXBElement<WasAttributedTo> newElement(WasAttributedTo u) {
	return of.createWasAttributedTo(u);
    }

    public JAXBElement<WasDerivedFrom> newElement(WasDerivedFrom u) {
	return of.createWasDerivedFrom(u);
    }

    public JAXBElement<WasEndedBy> newElement(WasEndedBy u) {
	return of.createWasEndedBy(u);
    }

    public JAXBElement<WasGeneratedBy> newElement(WasGeneratedBy u) {
	return of.createWasGeneratedBy(u);
    }

    public JAXBElement<WasInfluencedBy> newElement(WasInfluencedBy u) {
	return of.createWasInfluencedBy(u);
    }

    public JAXBElement<WasInformedBy> newElement(WasInformedBy u) {
	return of.createWasInformedBy(u);
    }

    public JAXBElement<WasInvalidatedBy> newElement(WasInvalidatedBy u) {
	return of.createWasInvalidatedBy(u);
    }

    public JAXBElement<WasStartedBy> newElement(WasStartedBy u) {
	return of.createWasStartedBy(u);
    }

    public Entity newEntity(Entity e) {
	Entity res = newEntity(e.getId());
	res.getType().addAll(e.getType());
	res.getLabel().addAll(e.getLabel());
	res.getLocation().addAll(e.getLocation());
	return res;
    }

    public Entity newEntity(QName id) {
	Entity res = of.createEntity();
	res.setId(id);
	return res;
    }

    public Entity newEntity(QName id, Collection<Attribute> attributes) {
	Entity res = newEntity(id);
	setAttributes(res, attributes);
	return res;
    }

    public Entity newEntity(QName id, String label) {
	Entity res = newEntity(id);
	if (label != null)
	    res.getLabel().add(newInternationalizedString(label));
	return res;
    }

    public Entry newEntry(Key key, IDRef entity) {
	Entry res = of.createEntry();
	res.setKey(key);
	res.setEntity(entity);
	return res;
    }

    public XMLGregorianCalendar newGDay(int day) {
        XMLGregorianCalendar cal=dataFactory.newXMLGregorianCalendar();
        cal.setDay(day);
        return cal;
    }

    public XMLGregorianCalendar newGMonth(int month) {
        XMLGregorianCalendar cal=dataFactory.newXMLGregorianCalendar();
        cal.setMonth(month);
        return cal;
    }

    public XMLGregorianCalendar newGMonthDay(int month, int day) {
        XMLGregorianCalendar cal=dataFactory.newXMLGregorianCalendar();
        cal.setMonth(month);
        cal.setDay(day);
        return cal;
    }

    public XMLGregorianCalendar newGYear(int year) {
        XMLGregorianCalendar cal=dataFactory.newXMLGregorianCalendar();
        cal.setYear(year);
        return cal;
    }

    public HadMember newHadMember(IDRef collection, IDRef... entities) {
	HadMember res = of.createHadMember();
	res.setCollection(collection);
	if (entities != null) {
	    res.getEntity().addAll(Arrays.asList(entities));
	}
	return res;
    }

    public HadMember newHadMember(QName c, Collection<QName> e) {
        IDRef cid=(c==null)? null: newIDRef(c);
        List<IDRef> ll=new LinkedList<IDRef>();
        if (e!=null) {
            for (QName q: e) {
        	IDRef eid=newIDRef(q);
        	ll.add(eid);
            }
        }
        HadMember res = of.createHadMember();
        res.setCollection(cid);
        res.getEntity().addAll(ll);
        return res;
    }

    public IDRef newIDRef(Activity p) {
	IDRef res = of.createIDRef();
	res.setRef(p.getId());
	return res;
    }

    public IDRef newIDRef(Agent a) {
	IDRef res = of.createIDRef();
	res.setRef(a.getId());
	return res;
    }

    public IDRef newIDRef(Entity a) {
	IDRef res = of.createIDRef();
	res.setRef(a.getId());
	return res;
    }

    public IDRef newIDRef(QName id) {
	IDRef res = of.createIDRef();
	res.setRef(id);
	return res;
    }
    

    public IDRef newIDRef(Used edge) {
	IDRef res = of.createIDRef();
	res.setRef(edge.getId());
	return res;
    }

    public IDRef newIDRef(WasGeneratedBy edge) {
	IDRef res = of.createIDRef();
	res.setRef(edge.getId());
	return res;
    }

    public InternationalizedString newInternationalizedString(String s) {
	InternationalizedString res = of.createInternationalizedString();
	res.setValue(s);
	return res;
    }

    public InternationalizedString newInternationalizedString(String s,
							      String lang) {
	InternationalizedString res = of.createInternationalizedString();
	res.setValue(s);
	res.setLang(lang);
	return res;
    }

    public XMLGregorianCalendar newISOTime(String time) {
        return newTime(javax.xml.bind.DatatypeConverter.parseDateTime(time)
                                                       .getTime());
    }

    public Key newKey(Object o, QName type) {
    	Key res=of.createKey();
    	res.setType(type);
    	res.setValueAsObject(o);
    	return res;
       }


    public Location newLocation(Object value, QName type) {
            Location res =  of.createLocation();
            res.setType(type);
            res.setValueAsObject(value);
            return res;
          }

    public MentionOf newMentionOf(Entity infra, Entity supra, Entity bundle) {
	return newMentionOf((infra == null) ? null : newIDRef(infra),
			    (supra == null) ? null : newIDRef(supra),
			    (bundle == null) ? null : newIDRef(bundle));
    }

   
    public MentionOf newMentionOf(IDRef infra, IDRef supra,
				  IDRef bundle) {
	MentionOf res = of.createMentionOf();
	res.setSpecificEntity(infra);
	res.setBundle(bundle);
	res.setGeneralEntity(supra);
	return res;
    }
    
    
    public MentionOf newMentionOf(MentionOf r) {
	MentionOf res = of.createMentionOf();
	res.setSpecificEntity(r.getSpecificEntity());
	res.setBundle(r.getBundle());
	res.setGeneralEntity(r.getGeneralEntity());
	return res;
    }
    public MentionOf newMentionOf(QName e2, QName e1, QName b) {
        IDRef eid2 = (e2==null)? null: newIDRef(e2);
        IDRef eid1 = (e1==null)? null: newIDRef(e1);
        IDRef bid = (b==null)? null: newIDRef(b);
        return newMentionOf(eid2, eid1, bid);
    }
    
    public NamedBundle newNamedBundle(QName id, 
                                      Collection<Activity> ps,
				      Collection<Entity> as,
				      Collection<Agent> ags,
				      Collection<Statement> lks) {
	NamedBundle res = of.createNamedBundle();
	res.setId(id);
	
	if (ps != null) {
	    res.getStatement().addAll(ps);
	}
	if (as != null) {
	    res.getStatement().addAll(as);
	}
	if (ags != null) {
	    res.getStatement().addAll(ags);
	}
	if (lks != null) {
	    res.getStatement().addAll(lks);
	}
	return res;
    }

  
    public NamedBundle newNamedBundle(QName id, Collection<Statement> lks) {
	NamedBundle res = of.createNamedBundle();
	res.setId(id);

	if (lks != null) {
	    res.getStatement().addAll(lks);
	}
	return res;
    }
    public NamedBundle newNamedBundle(QName id, Namespace namespace, Collection<Statement> statements) {
	NamedBundle res = of.createNamedBundle();
	res.setId(id);
	res.setNamespace(namespace);
	if (statements != null) {
	    res.getStatement().addAll(statements);
	}
	return res;
    }

    

    public Other newOther(QName elementName, Object value, QName type) {
	if (value==null) return null;
        Other res =  of.createOther();
        res.setType(type);
        res.setValueAsObject(value);
        res.setElementName(elementName);
        return res;
      }

    public Other newOther(String namespace, String local, String prefix,  Object value, QName type) {
	QName elementName=new QName(namespace,local,prefix);
        return newOther(elementName,value,type);
      }

    /* What's the difference with stringToQName? */
    
    /*
    public QName newQName(String qnameAsString) {
	int index = qnameAsString.indexOf(':');
	String prefix;
	String local;

	if (index == -1) {
	    prefix = "";
	    local = qnameAsString;
	} else {
	    prefix = qnameAsString.substring(0, index);
	    local = qnameAsString.substring(index + 1, qnameAsString.length());
	}
	return new QName(getNamespace(prefix), local, prefix);
    }
    */
    
    public Role newRole(Object value, QName type) {
	if (value==null) return null;
        Role res =  of.createRole();
        res.setType(type);
        res.setValueAsObject(value);
        return res;
      }

    public SpecializationOf newSpecializationOf(IDRef eid2, IDRef eid1) {
	SpecializationOf res = of.createSpecializationOf();
	res.setSpecificEntity(eid2);
	res.setGeneralEntity(eid1);
	return res;
    }

    public SpecializationOf newSpecializationOf(QName e2, QName e1) {
        IDRef eid2 = (e2==null)? null: newIDRef(e2);
        IDRef eid1 = (e1==null)? null: newIDRef(e1);
        return newSpecializationOf(eid2, eid1);
    }


    public XMLGregorianCalendar newTime(Date date) {
	GregorianCalendar gc = new GregorianCalendar();
	gc.setTime(date);
	return newXMLGregorianCalendar(gc);
    }

    public XMLGregorianCalendar newTimeNow() {
	return newTime(new Date());
    }
  
    public Type newType(Object value, QName type) {
	if (value==null) return null;
        Type res =  of.createType();
        res.setType(type);
        res.setValueAsObject(value);
        return res;
    }

    public Type newType(Object value, ValueConverter vconv) {
	return newType(value, vconv.getXsdType(value));
    }


    public Used newUsed(QName id) {
	Used res = of.createUsed();
	res.setId(id);
	return res;
    }

    public Used newUsed(QName id, IDRef aid, String role, IDRef eid) {
	Used res = newUsed(id);
	res.setActivity(aid);
	if (role!=null)
	addRole(res, newRole(role,Name.QNAME_XSD_STRING));
	res.setEntity(eid);
	return res;
    }
    
    /** A factory method to create an instance of a usage {@link Used}
     * @param id an optional identifier for a usage
     * @param activity the identifier  of the <a href="http://www.w3.org/TR/prov-dm/#usage.activity">activity</a> that used an entity
     * @param entity an optional identifier for the <a href="http://www.w3.org/TR/prov-dm/#usage.entity">entity</a> being used
     * @return an instance of {@link Used}
     */    

    public Used newUsed(QName id, QName activity, QName entity) {
 	IDRef aid = (activity == null) ? null : newIDRef(activity);
 	IDRef eid = (entity == null) ? null : newIDRef(entity);
 	Used res = newUsed(id, aid, null, eid);
 	return res;
     }

    public Used newUsed(QName activity, QName entity) {
 	IDRef aid = (activity == null) ? null : newIDRef(activity);
 	IDRef eid = (entity == null) ? null : newIDRef(entity);
 	Used res = newUsed(null, aid, null, eid);
 	return res;
     }

     /* (non-Javadoc)
     * @see org.openprovenance.prov.model.ModelConstructor#newUsed(javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.datatype.XMLGregorianCalendar, java.util.Collection)
     */
    public Used newUsed(QName id, QName activity, QName entity,
			XMLGregorianCalendar time,
			Collection<Attribute> attributes) {
	IDRef aid = (activity == null) ? null : newIDRef(activity);
	IDRef eid = (entity == null) ? null : newIDRef(entity);
	Used res = newUsed(id, aid, null, eid);
	res.setTime(time);
	setAttributes(res, attributes);
	return res;
    }


    

    public Used newUsed(Used u) {
	Used u1 = newUsed(u.getId(), u.getActivity(), null, u.getEntity());
	u1.getOther().addAll(u.getOther());
	u1.setTime(u.getTime());
	u1.getType().addAll(u.getType());
	u1.getLabel().addAll(u.getLabel());
	u1.getLocation().addAll(u.getLocation());
	return u1;
    }

    
    

    public Value newValue(Object value, QName type) {
	if (value==null) return null;
        Value res =  of.createValue();
        res.setType(type);
        res.setValueAsObject(value);
        return res;
      }

    public Value newValue(Object value, ValueConverter vconv) {
        return newValue(value,vconv.getXsdType(value));
      }

    public WasAssociatedWith newWasAssociatedWith(QName id) {
	return newWasAssociatedWith(id, (QName)null,(QName)null);
    }
    public WasAssociatedWith newWasAssociatedWith(QName id, Activity eid2,
						  Agent eid1) {
	return newWasAssociatedWith(id, newIDRef(eid2.getId()),
				    newIDRef(eid1.getId()));
    }

    public WasAssociatedWith newWasAssociatedWith(QName id, IDRef eid2,
						  IDRef eid1) {
	WasAssociatedWith res = of.createWasAssociatedWith();
	res.setId(id);
	res.setActivity(eid2);
	res.setAgent(eid1);
	return res;
    }

    public WasAssociatedWith  newWasAssociatedWith(QName id, 
                                                   QName a, 
                                                   QName ag) {
	IDRef aid=(a==null)? null: newIDRef(a);
	IDRef agid=(ag==null)? null: newIDRef(ag);
	WasAssociatedWith res= newWasAssociatedWith(id,aid,agid);
	return res;
    }
    public WasAssociatedWith  newWasAssociatedWith(QName id, 
                                                   QName a, 
                                                   QName ag, 
                                                   QName plan, 
                                                   Collection<Attribute> attributes) {
	IDRef aid=(a==null)? null: newIDRef(a);
	IDRef eid=(plan==null)? null: newIDRef(plan);
	IDRef agid=(ag==null)? null: newIDRef(ag);
	WasAssociatedWith res= newWasAssociatedWith(id,aid,agid);
	res.setPlan(eid);
	setAttributes(res, attributes);
	return res;
    }
    
    public WasAssociatedWith newWasAssociatedWith(WasAssociatedWith u) {
	WasAssociatedWith u1 = newWasAssociatedWith(u.getId(), u.getActivity(),
						    u.getAgent());
	u1.getOther().addAll(u.getOther());
	u1.setPlan(u.getPlan());
	u1.getType().addAll(u.getType());
	u1.getLabel().addAll(u.getLabel());
	return u1;
    }

    public WasAttributedTo newWasAttributedTo(QName id, IDRef eid,
					      IDRef agid) {
	WasAttributedTo res = of.createWasAttributedTo();
	res.setId(id);
	res.setEntity(eid);
	res.setAgent(agid);
	return res;
    }

    
    public WasAttributedTo newWasAttributedTo(QName id, QName e, QName ag,  Collection<Attribute> attributes) {
        IDRef eid=(e==null)? null : newIDRef(e);
        IDRef agid=(ag==null)? null : newIDRef(ag);
        WasAttributedTo res=newWasAttributedTo(id, eid, agid);
        setAttributes(res, attributes);
        return res;
    }

    public WasAttributedTo newWasAttributedTo(WasAttributedTo u) {
	WasAttributedTo u1 = newWasAttributedTo(u.getId(), u.getEntity(),
						u.getAgent());
	u1.getOther().addAll(u.getOther());
	u1.getType().addAll(u.getType());
	u1.getLabel().addAll(u.getLabel());
	return u1;
    }
    public WasDerivedFrom newWasDerivedFrom(QName id, IDRef aid1,
					    IDRef aid2) {
	WasDerivedFrom res = of.createWasDerivedFrom();
	res.setId(id);
	res.setUsedEntity(aid2);
	res.setGeneratedEntity(aid1);
	return res;
    }
    public WasDerivedFrom newWasDerivedFrom(QName aid1,
					    QName aid2) {
	WasDerivedFrom res = of.createWasDerivedFrom();
	res.setUsedEntity(newIDRef(aid2));
	res.setGeneratedEntity(newIDRef(aid1));
	return res;
    }

    public WasDerivedFrom newWasDerivedFrom(QName id, IDRef aid1,
					    IDRef aid2, IDRef aid,
					    IDRef did1, IDRef did2) {
	WasDerivedFrom res = of.createWasDerivedFrom();
	res.setId(id);
	res.setUsedEntity(aid2);
	res.setGeneratedEntity(aid1);
	res.setActivity(aid);
	res.setGeneration(did1);
	res.setUsage(did2);
	return res;
    }

    /** A factory method to create an instance of a derivation {@link WasDerivedFrom}
     * @param id an optional identifier for a derivation
     * @param e2 the identifier  of the <a href="http://www.w3.org/TR/prov-dm/#derivation.generatedEntity">entity generated</a> by the derivation 
     * @param e1 the identifier  of the <a href="http://www.w3.org/TR/prov-dm/#derivation.usedEntity">entity used</a> by the derivation
     * @return an instance of {@link WasDerivedFrom}
     */
    public WasDerivedFrom newWasDerivedFrom(QName id, QName e2, QName e1) {
	IDRef eid1 = (e1==null)? null: newIDRef(e1);
	IDRef eid2 = (e2==null)? null: newIDRef(e2);
	WasDerivedFrom res=newWasDerivedFrom(id, eid2, eid1);
	return res;
    }

    public WasDerivedFrom newWasDerivedFrom(QName id, QName e2, QName e1, QName a, QName g, QName u,  Collection<Attribute> attributes) {
	IDRef eid1 = (e1==null)? null: newIDRef(e1);
	IDRef eid2 = (e2==null)? null: newIDRef(e2);
	IDRef aid = (a==null)? null : newIDRef(a);
	IDRef gid = (g==null)? null: newIDRef(g);
	IDRef uid = (u==null) ? null : newIDRef(u);
	WasDerivedFrom res=newWasDerivedFrom(id, eid2, eid1, aid, gid, uid);
	setAttributes(res, attributes);
	return res;
    }



    public WasDerivedFrom newWasDerivedFrom(WasDerivedFrom d) {
	WasDerivedFrom wdf = newWasDerivedFrom(d.getId(),
					       d.getGeneratedEntity(),
					       d.getUsedEntity());
	wdf.setGeneration(d.getGeneration());
	wdf.setUsage(d.getUsage());
	wdf.getOther().addAll(d.getOther());
	wdf.getType().addAll(d.getType());
	wdf.getLabel().addAll(d.getLabel());
	return wdf;
    }
    
    public WasEndedBy newWasEndedBy(QName id) {
	WasEndedBy res = of.createWasEndedBy();
	res.setId(id);
	return res;
    }

    public WasEndedBy newWasEndedBy(QName id, IDRef aid, IDRef eid) {
	WasEndedBy res = of.createWasEndedBy();
	res.setId(id);
	res.setActivity(aid);
	res.setTrigger(eid);
	return res;
    }
    
    
    /** A factory method to create an instance of an end {@link WasEndedBy}
     * @param id
     * @param activity an identifier for the ended <a href="http://www.w3.org/TR/prov-dm/#end.activity">activity</a>
     * @param trigger an optional identifier for the <a href="http://www.w3.org/TR/prov-dm/#end.trigger">entity triggering</a> the activity ending
     * @param ender an optional identifier for the <a href="http://www.w3.org/TR/prov-dm/#end.ender">activity</a> that generated the (possibly unspecified) entity
     * @param time the optional <a href="http://www.w3.org/TR/prov-dm/#end.time">time</a>  at which the activity was ended
     * @param attributes an optional set of <a href="http://www.w3.org/TR/prov-dm/#end.attributes">attribute-value pairs</a> representing additional information about this activity end
     * @return an instance of {@link WasStartedBy}
     */
    public WasEndedBy newWasEndedBy(QName id, QName activity, QName trigger, QName ender) {
   	IDRef aid = (activity==null)? null: newIDRef(activity);
      	IDRef eid = (trigger==null)? null: newIDRef(trigger);
      	IDRef sid = (ender==null)? null: newIDRef(ender);
      	WasEndedBy res=newWasEndedBy(id,aid,eid);	
      	res.setEnder(sid);
      	return res;
    }

    /* (non-Javadoc)
     * @see org.openprovenance.prov.model.ModelConstructor#newWasEndedBy(javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.datatype.XMLGregorianCalendar, java.util.Collection)
     */
    public WasEndedBy newWasEndedBy(QName id, QName activity, QName trigger, QName ender, XMLGregorianCalendar time, Collection<Attribute> attributes) {
   	IDRef aid = (activity==null)? null: newIDRef(activity);
      	IDRef eid = (trigger==null)? null: newIDRef(trigger);
      	IDRef sid = (ender==null)? null: newIDRef(ender);
      	WasEndedBy res=newWasEndedBy(id,aid,eid);	
      	res.setTime(time);
      	res.setEnder(sid);
	setAttributes(res, attributes);
      	return res;
    }

    public WasEndedBy newWasEndedBy(WasEndedBy u) {
	WasEndedBy u1 = newWasEndedBy(u.getId(), u.getActivity(),
				      u.getTrigger());
	u1.setEnder(u.getEnder());
	u1.setTime(u.getTime());
	u1.getType().addAll(u.getType());
	u1.getLabel().addAll(u.getLabel());
	u1.getLocation().addAll(u.getLocation());
	u1.getOther().addAll(u.getOther());
	return u1;
    }

    public WasGeneratedBy newWasGeneratedBy(Entity a, String role, Activity p) {
	return newWasGeneratedBy((QName) null, a, role, p);
    }

    public WasGeneratedBy newWasGeneratedBy(QName id) {
	WasGeneratedBy res = of.createWasGeneratedBy();
	res.setId(id);
	return res;
	
    }

    public WasGeneratedBy newWasGeneratedBy(QName id, Entity a, String role,
					    Activity p) {
	IDRef aid = newIDRef(a);
	IDRef pid = newIDRef(p);
	return newWasGeneratedBy(id, aid, role, pid);
    }

    public WasGeneratedBy newWasGeneratedBy(QName id, IDRef aid,
					    String role, IDRef pid) {
	WasGeneratedBy res = of.createWasGeneratedBy();
	res.setId(id);
	res.setActivity(pid);
	res.setEntity(aid);
	if (role!=null) addRole(res, newRole(role,Name.QNAME_XSD_STRING));
	return res;
    }

    /** A factory method to create an instance of a generation {@link WasGeneratedBy}
     * @param id an optional identifier for a usage
     * @param entity an identifier for the created <a href="http://www.w3.org/TR/prov-dm/#generation.entity">entity</a>
     * @param activity an optional identifier  for the <a href="http://www.w3.org/TR/prov-dm/#generation.activity">activity</a> that creates the entity
     * @return an instance of {@link WasGeneratedBy}
     */    

    public WasGeneratedBy newWasGeneratedBy(QName id, QName entity, QName activity) {
   	IDRef aid = (activity==null)? null: newIDRef(activity);
   	IDRef eid = (entity==null)? null: newIDRef(entity);
   	WasGeneratedBy res=newWasGeneratedBy(id,eid,null,aid);	
   	return res;
    }

    /* (non-Javadoc)
     * @see org.openprovenance.prov.model.ModelConstructor#newWasGeneratedBy(javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.datatype.XMLGregorianCalendar, java.util.Collection)
     */
    public WasGeneratedBy newWasGeneratedBy(QName id, QName entity, QName activity, XMLGregorianCalendar time, Collection<Attribute> attributes) {
   	IDRef aid = (activity==null)? null: newIDRef(activity);
   	IDRef eid = (entity==null)? null: newIDRef(entity);
   	WasGeneratedBy res=newWasGeneratedBy(id,eid,null,aid);	
   	res.setTime(time);
	setAttributes(res, attributes);
   	return res;
    }



    public WasGeneratedBy newWasGeneratedBy(WasGeneratedBy g) {
	WasGeneratedBy wgb = newWasGeneratedBy(g.getId(), g.getEntity(), null,
					       g.getActivity());
	wgb.setId(g.getId());
	wgb.setTime(g.getTime());
	wgb.getOther().addAll(g.getOther());
	wgb.getType().addAll(g.getType());
	wgb.getLabel().addAll(g.getLabel());
	wgb.getLocation().addAll(g.getLocation());
	return wgb;
    }

    public WasInfluencedBy newWasInfluencedBy(QName id, IDRef influencee,
					      IDRef influencer) {
	WasInfluencedBy res = of.createWasInfluencedBy();
	res.setId(id);
	res.setInfluencee(influencee);
	res.setInfluencer(influencer);
	return res;
    }

    public WasInfluencedBy newWasInfluencedBy(QName id, QName a2, QName a1, Collection<Attribute> attributes) {
        IDRef aid2 = (a2==null) ? null: newIDRef(a2);
        IDRef aid1 = (a1==null) ? null: newIDRef(a1);
        WasInfluencedBy res=newWasInfluencedBy(id,aid2,aid1);   
        setAttributes(res, attributes);
        return res;
    }
    
    
    public WasInfluencedBy newWasInfluencedBy(WasInfluencedBy in) {
	WasInfluencedBy out = newWasInfluencedBy(in.getId(),
						 in.getInfluencee(),
						 in.getInfluencer());
	out.setId(in.getId());
	out.getOther().addAll(in.getOther());
	out.getType().addAll(in.getType());
	out.getLabel().addAll(in.getLabel());
	return out;
    }

    public WasInformedBy newWasInformedBy(Activity p1, Activity p2) {
	return newWasInformedBy(null, p1, p2);
    }

    public WasInformedBy newWasInformedBy(QName id, Activity p1, Activity p2,
					  String type) {
	WasInformedBy wtb = newWasInformedBy(p1, p2);
	wtb.setId(id);
	addType(wtb, newType(type,Name.QNAME_XSD_STRING));
	return wtb;
    }

    public WasInformedBy newWasInformedBy(QName id, IDRef pid1,
					  IDRef pid2) {
	WasInformedBy res = of.createWasInformedBy();
	res.setId(id);
	res.setInformed(pid1);
	res.setInformant(pid2);
	return res;
    }

    public WasInformedBy newWasInformedBy(QName id, QName a2, QName a1, Collection<Attribute> attributes) {
        IDRef aid2 = (a2==null) ? null: newIDRef(a2);
        IDRef aid1 = (a1==null) ? null: newIDRef(a1);
        WasInformedBy res=newWasInformedBy(id,aid2,aid1);   
        setAttributes(res, attributes);
        return res;
    }

    public WasInformedBy newWasInformedBy(QName id, Activity p1, Activity p2) {
	IDRef pid1 = newIDRef(p1);
	IDRef pid2 = newIDRef(p2);
	return newWasInformedBy(id, pid1, pid2);
    }


    public WasInformedBy newWasInformedBy(WasInformedBy d) {
	WasInformedBy wtb = newWasInformedBy(d.getId(), 
					     d.getInformed(),
					     d.getInformant());
	wtb.setId(d.getId());
	wtb.getOther().addAll(d.getOther());
	wtb.getType().addAll(d.getType());
	wtb.getLabel().addAll(d.getLabel());
	return wtb;
    }

    public WasInvalidatedBy newWasInvalidatedBy(QName id, IDRef eid,
						IDRef aid) {
	WasInvalidatedBy res = of.createWasInvalidatedBy();
	res.setId(id);
	res.setEntity(eid);
	res.setActivity(aid);
	return res;
    }
    public WasInvalidatedBy newWasInvalidatedBy(QName eid,
						QName aid) {
	WasInvalidatedBy res = of.createWasInvalidatedBy();
	res.setEntity(newIDRef(eid));
	res.setActivity(newIDRef(aid));
	return res;
    }

    /** A factory method to create an instance of an invalidation {@link WasInvalidatedBy}
     * @param id an optional identifier for a usage
     * @param entity an identifier for the created <a href="http://www.w3.org/TR/prov-dm/#invalidation.entity">entity</a>
     * @param activity an optional identifier  for the <a href="http://www.w3.org/TR/prov-dm/#invalidation.activity">activity</a> that creates the entity
     * @return an instance of {@link WasInvalidatedBy}
     */    

    public WasInvalidatedBy newWasInvalidatedBy(QName id, QName entity, QName activity) {
   	IDRef aid = (activity==null) ? null: newIDRef(activity);
   	IDRef eid = (entity==null)? null: newIDRef(entity);
   	WasInvalidatedBy res=newWasInvalidatedBy(id,eid,aid);	
   	return res;
    }
    
    /* (non-Javadoc)
     * @see org.openprovenance.prov.model.ModelConstructor#newWasInvalidatedBy(javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.datatype.XMLGregorianCalendar, java.util.Collection)
     */
    public WasInvalidatedBy newWasInvalidatedBy(QName id, QName entity, QName activity, XMLGregorianCalendar time, Collection<Attribute> attributes) {
   	IDRef aid = (activity==null) ? null: newIDRef(activity);
   	IDRef eid = (entity==null)? null: newIDRef(entity);
   	WasInvalidatedBy res=newWasInvalidatedBy(id,eid,aid);	
   	res.setTime(time);
	setAttributes(res, attributes);
   	return res;
    }


    public WasInvalidatedBy newWasInvalidatedBy(WasInvalidatedBy u) {
	WasInvalidatedBy u1 = newWasInvalidatedBy(u.getId(), u.getEntity(),
						  u.getActivity());
	u1.setTime(u.getTime());
	u1.getOther().addAll(u.getOther());
	u1.getType().addAll(u.getType());
	u1.getLabel().addAll(u.getLabel());
	u1.getLocation().addAll(u.getLocation());
	return u1;
    }
    
    /** A factory method to create an instance of a start {@link WasStartedBy}
     * @param id
     * @return an instance of {@link WasStartedBy}
     */
       
    public WasStartedBy newWasStartedBy(QName id) {
   	WasStartedBy res = of.createWasStartedBy();
   	res.setId(id);
   	return res;
    }

    public WasStartedBy newWasStartedBy(QName id, IDRef aid, IDRef eid) {
	WasStartedBy res = of.createWasStartedBy();
	res.setId(id);
	res.setActivity(aid);
	res.setTrigger(eid);
	return res;
    }
    
    /** A factory method to create an instance of a start {@link WasStartedBy}
     * @param id
     * @param activity an identifier for the started <a href="http://www.w3.org/TR/prov-dm/#start.activity">activity</a>
     * @param trigger an optional identifier for the <a href="http://www.w3.org/TR/prov-dm/#start.trigger">entity triggering</a> the activity
     * @param starter an optional identifier for the <a href="http://www.w3.org/TR/prov-dm/#start.starter">activity</a> that generated the (possibly unspecified) entity
     * @return an instance of {@link WasStartedBy}
     */
    
    public WasStartedBy newWasStartedBy(QName id, QName activity, QName trigger, QName starter) {
   	IDRef aid = (activity==null)? null: newIDRef(activity);
      	IDRef eid = (trigger==null)? null: newIDRef(trigger);
      	IDRef sid = (starter==null)? null: newIDRef(starter);
      	WasStartedBy res=newWasStartedBy(id,aid,eid);	
      	res.setStarter(sid);
      	return res;
    }

    /* (non-Javadoc)
     * @see org.openprovenance.prov.model.ModelConstructor#newWasStartedBy(javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.namespace.QName, javax.xml.datatype.XMLGregorianCalendar, java.util.Collection)
     */
    public WasStartedBy newWasStartedBy(QName id, QName activity, QName trigger, QName starter, XMLGregorianCalendar time, Collection<Attribute> attributes) {
   	IDRef aid = (activity==null)? null: newIDRef(activity);
      	IDRef eid = (trigger==null)? null: newIDRef(trigger);
      	IDRef sid = (starter==null)? null: newIDRef(starter);
      	WasStartedBy res=newWasStartedBy(id,aid,eid);	
      	res.setTime(time);
      	res.setStarter(sid);
	setAttributes(res, attributes);
      	return res;
    }


    public WasStartedBy newWasStartedBy(WasStartedBy u) {
	WasStartedBy u1 = newWasStartedBy(u.getId(), u.getActivity(),
					  u.getTrigger());
	u1.setStarter(u.getStarter());
	u1.setTime(u.getTime());
	u1.getType().addAll(u.getType());
	u1.getLabel().addAll(u.getLabel());
	u1.getLocation().addAll(u.getLocation());
	u1.getOther().addAll(u.getOther());

	return u1;
    }
    public XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar gc) {
	return dataFactory.newXMLGregorianCalendar(gc);
    }

    public XMLGregorianCalendar newYear(int year) {
        XMLGregorianCalendar res=dataFactory.newXMLGregorianCalendar();
        res.setYear(year);
        return res;
    }

 
    public String qnameToString(QName qname) {
	return DOMProcessing.qnameToString(qname);
    }


    public void setAttributes(HasOther res, Collection<Attribute> attributes) {
	if (attributes==null) return;
	HasType typ=(res instanceof HasType)? (HasType)res : null;
	HasLocation loc=(res instanceof HasLocation)? (HasLocation)res : null;
	HasLabel lab=(res instanceof HasLabel)? (HasLabel)res : null;
	HasValue aval=(res instanceof HasValue)? (HasValue)res : null;
	HasRole rol=(res instanceof HasRole)? (HasRole)res : null;

	for (Attribute attr: attributes) {
	    Object aValue=attr.getValue();
	    switch (attr.getKind()) {
	    case PROV_LABEL:
		if (lab!=null) {
		    if (aValue instanceof InternationalizedString) {
			lab.getLabel().add((InternationalizedString) aValue);		
		    } else {
			lab.getLabel().add(newInternationalizedString(aValue.toString()));
		    }
		}
		break;
	    case PROV_LOCATION:
		if (loc!=null) {
		    loc.getLocation().add(newLocation(aValue,attr.getType()));
		}
		break;
	    case PROV_ROLE:
		if (rol!=null) {
		    rol.getRole().add(newRole(aValue,attr.getType()));
		}
		break;
	    case PROV_TYPE: 
		if (typ!=null) {
		    typ.getType().add(newType(aValue,attr.getType()));
		}
		break;
	    case PROV_VALUE:
		if (aval!=null) {
		    aval.setValue(newValue(aValue,attr.getType()));
		}
		break;
	    case OTHER:
		res.getOther().add(newOther(attr.getElementName(), aValue, attr.getType()));
		break;
	    
	    default:
		break;
	    
	    }
	    
	}
    }

    @Override
    public void startBundle(QName bundleId, Namespace namespaces) {
      
    }
    
    /* Uses the xsd:type to java:type mapping of JAXB */

    @Override
    public void startDocument(Namespace namespace) {
        
    }

}