package automata;

class DFA extends Automaton {
   //deterministic finite automaton 
   
   static enum State {S, P, q,Y,B,M} //states define
   public static Class getStates() { return State.class; } 

    public State delta(State q, char c) {//assignment state to q and char to c
        if (q==State.S && c=='s') return State.P;//If q matches c('s'), P statet is passed
        if (q==State.S && c=='t') return State.P;//If q matches c('t'), P statet is passed
                                                  
        if (q==State.P && c=='x') return State.P;//If q matches c('x'), then P statetine repeats
        if (q==State.P && c=='y') return State.P;//If q matches c('y'), then P statetine repeats
        if (q==State.P && c=='r') return State.P;//If q matches c('r'), then P statetine repeats
        if (q==State.P && c=='e') return State.q;//If q matches c('e'), q statet is passed
        if (q==State.P && c=='u') return State.q;//If q matches c('u'), q statet is passed
        
        if (q==State.q && c=='l') return State.Y;//If q matches c('l'), Y statet is passed
        if (q==State.q && c=='r') return State.Y;//If q matches c('r'), Y statet is passed
        if (q==State.q && c=='l') return State.q;//two different patterns can not be defined in the one char !
        
        if (q==State.Y && c=='i') return State.B;//If q matches c('i'), B statet is passed
        if (q==State.Y && c=='a') return State.B;//If q matches c('a'), B statet is passed
        
        if (q==State.B && c=='m') return State.M;//If q matches c('m'), M statet is passed
        if (q==State.B && c=='l') return State.M;//If q matches c('m'), M statet is passed
        
        
        
        return null;  //default is null -- no transition
        //all matches except for the rules defined above provide null definition
    }
   public boolean accept(String w) { //we define the rules for acceptance
      State q = State.S;  //initial State, start state define
      System.out.printf("  %s", q);
      for (int j=0; j<w.length(); j++) {//a loop is created for the length of the given pattern
         char c = w.charAt(j);//patternin chars compared in order
         State t = delta(q, c);//
         //System.out.println(j+": ("+q+", "+c+") -> "+t);
         System.out.printf(" -> %s", t);
         if (t == null) return false;//if the t(char) is not in the rule, return null
         q = t;//if t is in the rule, q=t
      }
      return (q==State.M);  //acceptable?
   } 

   static final String[] DEFAULT = {"sxl", "sellim", "tural","txural"};//we define sample patterns
   public static final Automaton dfa = new DFA();// define to dfa
   public static void main(String[] args) {//define to main
      dfa.test(args, DEFAULT); // we process the given pattern patterns with dfa
   }
}
