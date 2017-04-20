grammar Logo; 

@header {
  package logoparsing;
}

INT : '0' | [1-9][0-9]* ;
WS : [ \t\r\n]+ -> skip ;

programme : liste_instructions 
;
liste_instructions :
  (instruction)+   
;

instruction :
    'av'       exp         # av
  | 'td'       exp         # td
  | 'tg'       exp         # tg
  | 'lc'                   # lc
  | 'bc'                   # bc
  | 've'                   # ve
  | 're'       exp         # re
  | 'fpos' '[' exp exp ']' #fpos
  | 'fcc'      exp exp exp #fcc
  | 'repete'   INT bloc    #repete
;

bloc : '[' liste_instructions ']'
;

exp :
    'hasard' INT        #random
  | exp ('*' | '/') exp #mul
  | exp ('+' | '-') exp #sum
  | INT                 #int
  | 'LOOP'              #loop
  | '(' exp ')'         #parenthese
;