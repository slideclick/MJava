public class Parser extends Object {

   private Scanner scanner;
   private String  completionMessage;
   private boolean parseSuccessful;

//=============================================================================

   public Parser (){
      parseSuccessful = false;
      completionMessage = "Parsing error -- nothing has been parsed yet";
   }

//=============================================================================

   public boolean successful(){
      return parseSuccessful;
   } 

//=============================================================================

   public String toString(){
      return completionMessage;
   }

//=============================================================================

   public void parse (String str){
      if (str == null || str.length() == 0){
         parseSuccessful = false;
         completionMessage = "Parsing error -- expression null or empty";
         throw new RuntimeException (completionMessage);
      }
      completionMessage = "No errors";
      parseSuccessful = true;
      scanner = new Scanner (str);
      scanner.first();
      expression();
      accept (scanner.get(), Token.EOE, "symbol after end of expression");
   }

   private void accept (Token token, int expected, String errorMessage){
      if (token.type != expected)
         fatalError (token, errorMessage);
   }

   private void fatalError (Token token, String errorMessage){
      parseSuccessful = false;
      completionMessage = "Parsing error -- " + errorMessage +
                          "\nToken causing error = '" + token + "'" +
                          "\nExpression string up to point of failure = " + 
                          "\n'" + scanner.stringUpToCurrentToken() + "'";
      throw new RuntimeException (completionMessage);
   }

   private void expression(){
      term();
      Token token = scanner.get();
      while (token.type == Token.PLUS || token.type == Token.MINUS){
         scanner.next();
         term();
         token = scanner.get();
      }
   }

   private void term(){
      primary();
      Token token = scanner.get();
      while (token.type == Token.MUL || token.type == Token.DIV){
          scanner.next();
          primary();
          token = scanner.get();
      }
   }

   private void primary(){
      Token token = scanner.get();
      switch (token.type){
         case Token.INT:
            scanner.next();
            break;
         case Token.L_PAR:
            scanner.next();
            expression();
            accept (scanner.get(), Token.R_PAR, "')' expected");
            scanner.next();
            break;
         default:
            fatalError (token, "bad primary");
      }
   }
}



