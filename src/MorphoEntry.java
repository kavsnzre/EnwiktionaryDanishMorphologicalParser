package it.uniroma1.lcl.wimmp;


public class MorphoEntry
{
	public enum POS
	{
		NOUN("NOUN"),
		VERB("VERB"),
		ADJECTIVE("ADJ"),
		ADVERB("ADV"),
		PRONOUN("PRON"),
		DETERMINER("DET"),
		ARTICLE("ART"),
		CONJUNCTION("CONJ"),
		INTERJECTION("INTER"),
		PREPOSITION("ADP"),
                // custom added POS:
                PROPER_NOUN("PROPER NOUN"),
                NUMERAL("NUMERAL"),
                PARTICLE("PARTICLE"),
                LETTER("LETTER"),
                ACRONYM("ACRONYM"),
                ABBREVIATION("ABBR"),
                PREFIX("PREFIX"),
                SUFFIX("SUFFIX");
		
		private final String pos;
		
		POS(String pos) { this.pos = pos; }
                
                @Override
		public String toString() { return pos; }
	}
	
	private final String lemma;
	private final POS partOfSpeech;
	private boolean regular; // never used
	private final MorphoRule rule;
	
	public MorphoEntry(String lemma, POS partOfSpeech, MorphoRule rule)
	{
		this.lemma = lemma;
		this.partOfSpeech = partOfSpeech;
		this.rule = rule;
	}
	
	public String getLemma(){ return lemma; }
	public POS getPOS() {return partOfSpeech;}
	public boolean isRegular() {return regular;}
	public MorphoRule getRule() {return rule;}
}
