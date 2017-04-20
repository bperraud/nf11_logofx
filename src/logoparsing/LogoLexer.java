// Generated from C:/Users/Baptiste/IdeaProjects/nf11_logofx/grammar\Logo.g4 by ANTLR 4.6

  package logoparsing;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, INT=21, WS=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "INT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'av'", "'td'", "'tg'", "'lc'", "'bc'", "'ve'", "'re'", "'fpos'", 
		"'['", "']'", "'fcc'", "'repete'", "'hasard'", "'*'", "'/'", "'+'", "'-'", 
		"'LOOP'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "INT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LogoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Logo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u0081\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3"+
		"\26\3\26\7\26t\n\26\f\26\16\26w\13\26\5\26y\n\26\3\27\6\27|\n\27\r\27"+
		"\16\27}\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\5\3\2\63"+
		";\3\2\62;\5\2\13\f\17\17\"\"\u0083\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\62\3\2\2\2\7\65\3\2\2\2\t8\3\2\2"+
		"\2\13;\3\2\2\2\r>\3\2\2\2\17A\3\2\2\2\21D\3\2\2\2\23I\3\2\2\2\25K\3\2"+
		"\2\2\27M\3\2\2\2\31Q\3\2\2\2\33X\3\2\2\2\35_\3\2\2\2\37a\3\2\2\2!c\3\2"+
		"\2\2#e\3\2\2\2%g\3\2\2\2\'l\3\2\2\2)n\3\2\2\2+x\3\2\2\2-{\3\2\2\2/\60"+
		"\7c\2\2\60\61\7x\2\2\61\4\3\2\2\2\62\63\7v\2\2\63\64\7f\2\2\64\6\3\2\2"+
		"\2\65\66\7v\2\2\66\67\7i\2\2\67\b\3\2\2\289\7n\2\29:\7e\2\2:\n\3\2\2\2"+
		";<\7d\2\2<=\7e\2\2=\f\3\2\2\2>?\7x\2\2?@\7g\2\2@\16\3\2\2\2AB\7t\2\2B"+
		"C\7g\2\2C\20\3\2\2\2DE\7h\2\2EF\7r\2\2FG\7q\2\2GH\7u\2\2H\22\3\2\2\2I"+
		"J\7]\2\2J\24\3\2\2\2KL\7_\2\2L\26\3\2\2\2MN\7h\2\2NO\7e\2\2OP\7e\2\2P"+
		"\30\3\2\2\2QR\7t\2\2RS\7g\2\2ST\7r\2\2TU\7g\2\2UV\7v\2\2VW\7g\2\2W\32"+
		"\3\2\2\2XY\7j\2\2YZ\7c\2\2Z[\7u\2\2[\\\7c\2\2\\]\7t\2\2]^\7f\2\2^\34\3"+
		"\2\2\2_`\7,\2\2`\36\3\2\2\2ab\7\61\2\2b \3\2\2\2cd\7-\2\2d\"\3\2\2\2e"+
		"f\7/\2\2f$\3\2\2\2gh\7N\2\2hi\7Q\2\2ij\7Q\2\2jk\7R\2\2k&\3\2\2\2lm\7*"+
		"\2\2m(\3\2\2\2no\7+\2\2o*\3\2\2\2py\7\62\2\2qu\t\2\2\2rt\t\3\2\2sr\3\2"+
		"\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vy\3\2\2\2wu\3\2\2\2xp\3\2\2\2xq\3\2"+
		"\2\2y,\3\2\2\2z|\t\4\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\177"+
		"\3\2\2\2\177\u0080\b\27\2\2\u0080.\3\2\2\2\6\2ux}\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}