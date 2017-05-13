grammar Logo; 

@header {
  package logoparsing;
}

INT : '0' | '1'..'9' ('0'..'9')* ;
WS : [ \t\r\n]+ -> skip ;
fragment ID : [a-zA-Z][a-zA-Z0-9]*;
GETVAR : ':'ID;
SETVAR : '"'ID;
PROCNAME : ID;
OPERATOR : [<>]|[<>!=]'=' ;

programme :
    def_procedures liste_instructions
;

liste_instructions :
  (instruction)+   
;

instruction :
    'av'        exp                             #av
  | 'td'        exp                             #td
  | 'tg'        exp                             #tg
  | 'lc'                                        #lc
  | 'bc'                                        #bc
  | 've'                                        #ve
  | 're'        exp                             #re
  | 'fpos' '['  exp exp ']'                     #fpos
  | 'fcc'       exp exp exp                     #fcc
  | 'repete'    exp bloc                        #repete
  | 'donne'     SETVAR exp                      #donne
  | 'si'        booleanexpression bloc bloc?    #si
  | 'tantque'   booleanexpression bloc          #tantque
  | 'STOP'                                      #break
  | PROCNAME    exp*                            #procedurecall
;

bloc : '[' liste_instructions ']'
;

exp :
    'hasard' exp            #random
  | exp ('*' | '/') exp     #mul
  | exp ('+' | '-') exp     #sum
  | INT                     #int
  | GETVAR                  #getvar
  | 'LOOP'                  #loop
  | '(' exp ')'             #parenthese
  | PROCNAME '(' exp* ')'   #functioncall
;

booleanexpression :
    exp OPERATOR exp    #booleancomposite
  | exp                 #booleanatom
;

def_procedures :
    def_procedure*
;

def_procedure :
    'pour' PROCNAME GETVAR* liste_instructions? rend_instruction? 'fin'
;

rend_instruction :
    'rends' exp
;