// Generated from /home/baptiste/IdeaProjects/nf11_logofx/grammar/Logo.g4 by ANTLR 4.6

  package logoparsing;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogoParser}.
 */
public interface LogoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogoParser#programme}.
	 * @param ctx the parse tree
	 */
	void enterProgramme(LogoParser.ProgrammeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#programme}.
	 * @param ctx the parse tree
	 */
	void exitProgramme(LogoParser.ProgrammeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoParser#liste_instructions}.
	 * @param ctx the parse tree
	 */
	void enterListe_instructions(LogoParser.Liste_instructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#liste_instructions}.
	 * @param ctx the parse tree
	 */
	void exitListe_instructions(LogoParser.Liste_instructionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code av}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterAv(LogoParser.AvContext ctx);
	/**
	 * Exit a parse tree produced by the {@code av}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitAv(LogoParser.AvContext ctx);
	/**
	 * Enter a parse tree produced by the {@code td}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterTd(LogoParser.TdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code td}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitTd(LogoParser.TdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tg}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterTg(LogoParser.TgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tg}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitTg(LogoParser.TgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLc(LogoParser.LcContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLc(LogoParser.LcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterBc(LogoParser.BcContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitBc(LogoParser.BcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ve}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterVe(LogoParser.VeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ve}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitVe(LogoParser.VeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code re}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterRe(LogoParser.ReContext ctx);
	/**
	 * Exit a parse tree produced by the {@code re}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitRe(LogoParser.ReContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fpos}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterFpos(LogoParser.FposContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fpos}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitFpos(LogoParser.FposContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fcc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterFcc(LogoParser.FccContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fcc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitFcc(LogoParser.FccContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repete}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterRepete(LogoParser.RepeteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repete}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitRepete(LogoParser.RepeteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code donne}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterDonne(LogoParser.DonneContext ctx);
	/**
	 * Exit a parse tree produced by the {@code donne}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitDonne(LogoParser.DonneContext ctx);
	/**
	 * Enter a parse tree produced by the {@code si}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterSi(LogoParser.SiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code si}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitSi(LogoParser.SiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tantque}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterTantque(LogoParser.TantqueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tantque}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitTantque(LogoParser.TantqueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code break}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterBreak(LogoParser.BreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code break}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitBreak(LogoParser.BreakContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogoParser#bloc}.
	 * @param ctx the parse tree
	 */
	void enterBloc(LogoParser.BlocContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogoParser#bloc}.
	 * @param ctx the parse tree
	 */
	void exitBloc(LogoParser.BlocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code random}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterRandom(LogoParser.RandomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code random}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitRandom(LogoParser.RandomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mul}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMul(LogoParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMul(LogoParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code loop}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterLoop(LogoParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code loop}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitLoop(LogoParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getvar}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterGetvar(LogoParser.GetvarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getvar}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitGetvar(LogoParser.GetvarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthese}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterParenthese(LogoParser.ParentheseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthese}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitParenthese(LogoParser.ParentheseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sum}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterSum(LogoParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sum}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitSum(LogoParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterInt(LogoParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitInt(LogoParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleancomposite}
	 * labeled alternative in {@link LogoParser#booleanexpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleancomposite(LogoParser.BooleancompositeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleancomposite}
	 * labeled alternative in {@link LogoParser#booleanexpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleancomposite(LogoParser.BooleancompositeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanatom}
	 * labeled alternative in {@link LogoParser#booleanexpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanatom(LogoParser.BooleanatomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanatom}
	 * labeled alternative in {@link LogoParser#booleanexpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanatom(LogoParser.BooleanatomContext ctx);
}