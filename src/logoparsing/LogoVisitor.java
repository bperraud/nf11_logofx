// Generated from C:/Users/bapti/IdeaProjects/nf11_logofx/grammar\Logo.g4 by ANTLR 4.6

  package logoparsing;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LogoParser#programme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramme(LogoParser.ProgrammeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#liste_instructions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListe_instructions(LogoParser.Liste_instructionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code av}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAv(LogoParser.AvContext ctx);
	/**
	 * Visit a parse tree produced by the {@code td}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTd(LogoParser.TdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tg}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTg(LogoParser.TgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLc(LogoParser.LcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBc(LogoParser.BcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ve}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVe(LogoParser.VeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code re}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe(LogoParser.ReContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fpos}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFpos(LogoParser.FposContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fcc}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFcc(LogoParser.FccContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repete}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepete(LogoParser.RepeteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code donne}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDonne(LogoParser.DonneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code si}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSi(LogoParser.SiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tantque}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTantque(LogoParser.TantqueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code break}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak(LogoParser.BreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code procedurecall}
	 * labeled alternative in {@link LogoParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedurecall(LogoParser.ProcedurecallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#bloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloc(LogoParser.BlocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code random}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRandom(LogoParser.RandomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqrt}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqrt(LogoParser.SqrtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(LogoParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(LogoParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loop}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(LogoParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functioncall}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctioncall(LogoParser.FunctioncallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getvar}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetvar(LogoParser.GetvarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthese}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthese(LogoParser.ParentheseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sum}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(LogoParser.SumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link LogoParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(LogoParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleancomposite}
	 * labeled alternative in {@link LogoParser#booleanexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleancomposite(LogoParser.BooleancompositeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanatom}
	 * labeled alternative in {@link LogoParser#booleanexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanatom(LogoParser.BooleanatomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#def_procedures}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef_procedures(LogoParser.Def_proceduresContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#def_procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef_procedure(LogoParser.Def_procedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogoParser#rend_instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRend_instruction(LogoParser.Rend_instructionContext ctx);
}